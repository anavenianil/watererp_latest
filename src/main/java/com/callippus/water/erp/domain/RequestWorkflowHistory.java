package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A RequestWorkflowHistory.
 */
@Entity
@Table(name = "request_workflow_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestWorkflowHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "request_stage")
    private Integer requestStage;
    
    @Column(name = "assigned_date")
    private ZonedDateTime assignedDate;
    
    @Column(name = "actioned_date")
    private ZonedDateTime actionedDate;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "ip_address")
    private String ipAddress;
    
    @Column(name = "assigned_role")
    private Integer assignedRole;
    
    @Column(name = "domain_object")
    private Long domainObject;
    
    @ManyToOne
    @JoinColumn(name = "assigned_from_id")
    private User assignedFrom;

    @ManyToOne
    @JoinColumn(name = "assigned_to_id")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    @ManyToOne
    @JoinColumn(name = "request_master_id")
    private RequestMaster requestMaster;

    @ManyToOne
    @JoinColumn(name = "workflow_master_id")
    private WorkflowMaster workflowMaster;

    @ManyToOne
    @JoinColumn(name = "workflow_stage_master_id")
    private WorkflowStageMaster workflowStageMaster;

    @ManyToOne
    @JoinColumn(name = "applied_by_id")
    private User appliedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRequestStage() {
        return requestStage;
    }
    
    public void setRequestStage(Integer requestStage) {
        this.requestStage = requestStage;
    }

    public ZonedDateTime getAssignedDate() {
        return assignedDate;
    }
    
    public void setAssignedDate(ZonedDateTime assignedDate) {
        this.assignedDate = assignedDate;
    }

    public ZonedDateTime getActionedDate() {
        return actionedDate;
    }
    
    public void setActionedDate(ZonedDateTime actionedDate) {
        this.actionedDate = actionedDate;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getAssignedRole() {
        return assignedRole;
    }
    
    public void setAssignedRole(Integer assignedRole) {
        this.assignedRole = assignedRole;
    }

    public Long getDomainObject() {
        return domainObject;
    }
    
    public void setDomainObject(Long domainObject) {
        this.domainObject = domainObject;
    }

    public User getAssignedFrom() {
        return assignedFrom;
    }

    public void setAssignedFrom(User user) {
        this.assignedFrom = user;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User user) {
        this.assignedTo = user;
    }

    public StatusMaster getStatusMaster() {
        return statusMaster;
    }

    public void setStatusMaster(StatusMaster statusMaster) {
        this.statusMaster = statusMaster;
    }

    public RequestMaster getRequestMaster() {
        return requestMaster;
    }

    public void setRequestMaster(RequestMaster requestMaster) {
        this.requestMaster = requestMaster;
    }

    public WorkflowMaster getWorkflowMaster() {
        return workflowMaster;
    }

    public void setWorkflowMaster(WorkflowMaster workflowMaster) {
        this.workflowMaster = workflowMaster;
    }

    public WorkflowStageMaster getWorkflowStageMaster() {
        return workflowStageMaster;
    }

    public void setWorkflowStageMaster(WorkflowStageMaster workflowStageMaster) {
        this.workflowStageMaster = workflowStageMaster;
    }

    public User getAppliedBy() {
        return appliedBy;
    }

    public void setAppliedBy(User user) {
        this.appliedBy = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestWorkflowHistory requestWorkflowHistory = (RequestWorkflowHistory) o;
        if(requestWorkflowHistory.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, requestWorkflowHistory.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RequestWorkflowHistory{" +
            "id=" + id +
            ", requestStage='" + requestStage + "'" +
            ", assignedDate='" + assignedDate + "'" +
            ", actionedDate='" + actionedDate + "'" +
            ", remarks='" + remarks + "'" +
            ", ipAddress='" + ipAddress + "'" +
            ", assignedRole='" + assignedRole + "'" +
            ", domainObject='" + domainObject + "'" +
            '}';
    }
}

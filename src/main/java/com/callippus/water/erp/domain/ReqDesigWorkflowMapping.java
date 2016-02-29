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
 * A ReqDesigWorkflowMapping.
 */
@Entity
@Table(name = "req_desig_workflow_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReqDesigWorkflowMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name = "workflow_master_id")
    private WorkflowMaster workflowMaster;

    @ManyToOne
    @JoinColumn(name = "request_master_id")
    private RequestMaster requestMaster;

    @ManyToOne
    @JoinColumn(name = "designation_master_id")
    private DesignationMaster designationMaster;

    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public WorkflowMaster getWorkflowMaster() {
        return workflowMaster;
    }

    public void setWorkflowMaster(WorkflowMaster workflowMaster) {
        this.workflowMaster = workflowMaster;
    }

    public RequestMaster getRequestMaster() {
        return requestMaster;
    }

    public void setRequestMaster(RequestMaster requestMaster) {
        this.requestMaster = requestMaster;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public StatusMaster getStatusMaster() {
        return statusMaster;
    }

    public void setStatusMaster(StatusMaster statusMaster) {
        this.statusMaster = statusMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReqDesigWorkflowMapping reqDesigWorkflowMapping = (ReqDesigWorkflowMapping) o;
        if(reqDesigWorkflowMapping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, reqDesigWorkflowMapping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReqDesigWorkflowMapping{" +
            "id=" + id +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

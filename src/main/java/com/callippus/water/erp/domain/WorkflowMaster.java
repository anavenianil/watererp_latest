package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WorkflowMaster.
 */
@Entity
@Table(name = "workflow_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WorkflowMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "workflow_name", nullable = false)
    private String workflowName;
    
    @Column(name = "to_workflow")
    private Integer toWorkflow;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkflowName() {
        return workflowName;
    }
    
    public void setWorkflowName(String workflowName) {
        this.workflowName = workflowName;
    }

    public Integer getToWorkflow() {
        return toWorkflow;
    }
    
    public void setToWorkflow(Integer toWorkflow) {
        this.toWorkflow = toWorkflow;
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
        WorkflowMaster workflowMaster = (WorkflowMaster) o;
        if(workflowMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, workflowMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkflowMaster{" +
            "id=" + id +
            ", workflowName='" + workflowName + "'" +
            ", toWorkflow='" + toWorkflow + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

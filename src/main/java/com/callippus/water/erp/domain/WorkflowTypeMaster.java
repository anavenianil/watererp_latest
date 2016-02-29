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
 * A WorkflowTypeMaster.
 */
@Entity
@Table(name = "workflow_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WorkflowTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
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
        WorkflowTypeMaster workflowTypeMaster = (WorkflowTypeMaster) o;
        if(workflowTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, workflowTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkflowTypeMaster{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", description='" + description + "'" +
            '}';
    }
}

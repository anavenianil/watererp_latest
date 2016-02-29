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
 * A OrgHierarchy.
 */
@Entity
@Table(name = "org_hierarchy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgHierarchy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "hierarchy_name")
    private String hierarchyName;
    
    @Column(name = "parent_hierarchy_id")
    private Integer parentHierarchyId;
    
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

    public String getHierarchyName() {
        return hierarchyName;
    }
    
    public void setHierarchyName(String hierarchyName) {
        this.hierarchyName = hierarchyName;
    }

    public Integer getParentHierarchyId() {
        return parentHierarchyId;
    }
    
    public void setParentHierarchyId(Integer parentHierarchyId) {
        this.parentHierarchyId = parentHierarchyId;
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
        OrgHierarchy orgHierarchy = (OrgHierarchy) o;
        if(orgHierarchy.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orgHierarchy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrgHierarchy{" +
            "id=" + id +
            ", hierarchyName='" + hierarchyName + "'" +
            ", parentHierarchyId='" + parentHierarchyId + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

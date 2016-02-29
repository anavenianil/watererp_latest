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
 * A DepartmentsHierarchy.
 */
@Entity
@Table(name = "departments_hierarchy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepartmentsHierarchy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "dept_hierarchy_name")
    private String deptHierarchyName;
    
    @Column(name = "parent_dept_hierarchy_id")
    private Integer parentDeptHierarchyId;
    
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

    public String getDeptHierarchyName() {
        return deptHierarchyName;
    }
    
    public void setDeptHierarchyName(String deptHierarchyName) {
        this.deptHierarchyName = deptHierarchyName;
    }

    public Integer getParentDeptHierarchyId() {
        return parentDeptHierarchyId;
    }
    
    public void setParentDeptHierarchyId(Integer parentDeptHierarchyId) {
        this.parentDeptHierarchyId = parentDeptHierarchyId;
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
        DepartmentsHierarchy departmentsHierarchy = (DepartmentsHierarchy) o;
        if(departmentsHierarchy.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, departmentsHierarchy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepartmentsHierarchy{" +
            "id=" + id +
            ", deptHierarchyName='" + deptHierarchyName + "'" +
            ", parentDeptHierarchyId='" + parentDeptHierarchyId + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

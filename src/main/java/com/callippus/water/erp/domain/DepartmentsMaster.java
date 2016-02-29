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
 * A DepartmentsMaster.
 */
@Entity
@Table(name = "departments_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DepartmentsMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;
    
    @Column(name = "parent_deparment")
    private Integer parentDeparment;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name = "departments_hierarchy_id")
    private DepartmentsHierarchy departmentsHierarchy;

    @ManyToOne
    @JoinColumn(name = "department_type_master_id")
    private DepartmentTypeMaster departmentTypeMaster;

    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }
    
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getParentDeparment() {
        return parentDeparment;
    }
    
    public void setParentDeparment(Integer parentDeparment) {
        this.parentDeparment = parentDeparment;
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

    public DepartmentsHierarchy getDepartmentsHierarchy() {
        return departmentsHierarchy;
    }

    public void setDepartmentsHierarchy(DepartmentsHierarchy departmentsHierarchy) {
        this.departmentsHierarchy = departmentsHierarchy;
    }

    public DepartmentTypeMaster getDepartmentTypeMaster() {
        return departmentTypeMaster;
    }

    public void setDepartmentTypeMaster(DepartmentTypeMaster departmentTypeMaster) {
        this.departmentTypeMaster = departmentTypeMaster;
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
        DepartmentsMaster departmentsMaster = (DepartmentsMaster) o;
        if(departmentsMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, departmentsMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DepartmentsMaster{" +
            "id=" + id +
            ", departmentName='" + departmentName + "'" +
            ", parentDeparment='" + parentDeparment + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

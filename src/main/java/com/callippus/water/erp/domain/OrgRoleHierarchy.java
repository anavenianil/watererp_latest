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
 * A OrgRoleHierarchy.
 */
@Entity
@Table(name = "org_role_hierarchy")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgRoleHierarchy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role_hierarchy_name")
    private String roleHierarchyName;
    
    @Column(name = "parent_role_hierarchy_id")
    private Integer parentRoleHierarchyId;
    
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

    public String getRoleHierarchyName() {
        return roleHierarchyName;
    }
    
    public void setRoleHierarchyName(String roleHierarchyName) {
        this.roleHierarchyName = roleHierarchyName;
    }

    public Integer getParentRoleHierarchyId() {
        return parentRoleHierarchyId;
    }
    
    public void setParentRoleHierarchyId(Integer parentRoleHierarchyId) {
        this.parentRoleHierarchyId = parentRoleHierarchyId;
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
        OrgRoleHierarchy orgRoleHierarchy = (OrgRoleHierarchy) o;
        if(orgRoleHierarchy.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orgRoleHierarchy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrgRoleHierarchy{" +
            "id=" + id +
            ", roleHierarchyName='" + roleHierarchyName + "'" +
            ", parentRoleHierarchyId='" + parentRoleHierarchyId + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

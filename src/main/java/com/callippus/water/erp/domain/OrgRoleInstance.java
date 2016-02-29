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
 * A OrgRoleInstance.
 */
@Entity
@Table(name = "org_role_instance")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgRoleInstance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "org_role_name")
    private String orgRoleName;
    
    @Column(name = "parent_org_role_id")
    private Integer parentOrgRoleId;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "is_head")
    private ZonedDateTime isHead;
    
    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    @ManyToOne
    @JoinColumn(name = "org_role_hierarchy_id")
    private OrgRoleHierarchy orgRoleHierarchy;

    @ManyToOne
    @JoinColumn(name = "departments_master_id")
    private DepartmentsMaster departmentsMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgRoleName() {
        return orgRoleName;
    }
    
    public void setOrgRoleName(String orgRoleName) {
        this.orgRoleName = orgRoleName;
    }

    public Integer getParentOrgRoleId() {
        return parentOrgRoleId;
    }
    
    public void setParentOrgRoleId(Integer parentOrgRoleId) {
        this.parentOrgRoleId = parentOrgRoleId;
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

    public ZonedDateTime getIsHead() {
        return isHead;
    }
    
    public void setIsHead(ZonedDateTime isHead) {
        this.isHead = isHead;
    }

    public StatusMaster getStatusMaster() {
        return statusMaster;
    }

    public void setStatusMaster(StatusMaster statusMaster) {
        this.statusMaster = statusMaster;
    }

    public OrgRoleHierarchy getOrgRoleHierarchy() {
        return orgRoleHierarchy;
    }

    public void setOrgRoleHierarchy(OrgRoleHierarchy orgRoleHierarchy) {
        this.orgRoleHierarchy = orgRoleHierarchy;
    }

    public DepartmentsMaster getDepartmentsMaster() {
        return departmentsMaster;
    }

    public void setDepartmentsMaster(DepartmentsMaster departmentsMaster) {
        this.departmentsMaster = departmentsMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrgRoleInstance orgRoleInstance = (OrgRoleInstance) o;
        if(orgRoleInstance.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orgRoleInstance.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrgRoleInstance{" +
            "id=" + id +
            ", orgRoleName='" + orgRoleName + "'" +
            ", parentOrgRoleId='" + parentOrgRoleId + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", isHead='" + isHead + "'" +
            '}';
    }
}

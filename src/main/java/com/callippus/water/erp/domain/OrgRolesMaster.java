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
 * A OrgRolesMaster.
 */
@Entity
@Table(name = "org_roles_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrgRolesMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "org_role_name")
    private String orgRoleName;
    
    @Column(name = "hierarchy_id")
    private Integer hierarchyId;
    
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

    public String getOrgRoleName() {
        return orgRoleName;
    }
    
    public void setOrgRoleName(String orgRoleName) {
        this.orgRoleName = orgRoleName;
    }

    public Integer getHierarchyId() {
        return hierarchyId;
    }
    
    public void setHierarchyId(Integer hierarchyId) {
        this.hierarchyId = hierarchyId;
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
        OrgRolesMaster orgRolesMaster = (OrgRolesMaster) o;
        if(orgRolesMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, orgRolesMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OrgRolesMaster{" +
            "id=" + id +
            ", orgRoleName='" + orgRoleName + "'" +
            ", hierarchyId='" + hierarchyId + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

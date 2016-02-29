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
 * A EmpRoleMapping.
 */
@Entity
@Table(name = "emp_role_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmpRoleMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "internal_division")
    private String internalDivision;
    
    @Column(name = "internal_role")
    private String internalRole;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "parent_role_id")
    private Integer parentRoleId;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "parent_user_id")
    private User parentUser;

    @ManyToOne
    @JoinColumn(name = "org_role_instance_id")
    private OrgRoleInstance orgRoleInstance;

    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInternalDivision() {
        return internalDivision;
    }
    
    public void setInternalDivision(String internalDivision) {
        this.internalDivision = internalDivision;
    }

    public String getInternalRole() {
        return internalRole;
    }
    
    public void setInternalRole(String internalRole) {
        this.internalRole = internalRole;
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

    public Integer getParentRoleId() {
        return parentRoleId;
    }
    
    public void setParentRoleId(Integer parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getParentUser() {
        return parentUser;
    }

    public void setParentUser(User user) {
        this.parentUser = user;
    }

    public OrgRoleInstance getOrgRoleInstance() {
        return orgRoleInstance;
    }

    public void setOrgRoleInstance(OrgRoleInstance orgRoleInstance) {
        this.orgRoleInstance = orgRoleInstance;
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
        EmpRoleMapping empRoleMapping = (EmpRoleMapping) o;
        if(empRoleMapping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, empRoleMapping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmpRoleMapping{" +
            "id=" + id +
            ", internalDivision='" + internalDivision + "'" +
            ", internalRole='" + internalRole + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", parentRoleId='" + parentRoleId + "'" +
            '}';
    }
}

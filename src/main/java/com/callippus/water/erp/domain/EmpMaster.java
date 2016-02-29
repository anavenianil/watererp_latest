package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A EmpMaster.
 */
@Entity
@Table(name = "emp_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EmpMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "office_id_id")
    private OrgRoleInstance officeId;

    @ManyToOne
    @JoinColumn(name = "designation_master_id")
    private DesignationMaster designationMaster;

    @ManyToOne
    @JoinColumn(name = "directorate_id_id")
    private OrgRoleInstance directorateId;

    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrgRoleInstance getOfficeId() {
        return officeId;
    }

    public void setOfficeId(OrgRoleInstance orgRoleInstance) {
        this.officeId = orgRoleInstance;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public OrgRoleInstance getDirectorateId() {
        return directorateId;
    }

    public void setDirectorateId(OrgRoleInstance orgRoleInstance) {
        this.directorateId = orgRoleInstance;
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
        EmpMaster empMaster = (EmpMaster) o;
        if(empMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, empMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EmpMaster{" +
            "id=" + id +
            '}';
    }
}

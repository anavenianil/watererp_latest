package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

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

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "joining_date")
    private LocalDate joiningDate;
    
    @Column(name = "marital_status")
    private String maritalStatus;
    
    @Column(name = "employee_type")
    private String employeeType;
    
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

    @ManyToOne
    @JoinColumn(name = "reporting_to_id")
    private DesignationMaster reportingTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }
    
    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
    
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmployeeType() {
        return employeeType;
    }
    
    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
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

    public DesignationMaster getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(DesignationMaster designationMaster) {
        this.reportingTo = designationMaster;
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
            ", dateOfBirth='" + dateOfBirth + "'" +
            ", joiningDate='" + joiningDate + "'" +
            ", maritalStatus='" + maritalStatus + "'" +
            ", employeeType='" + employeeType + "'" +
            '}';
    }
}

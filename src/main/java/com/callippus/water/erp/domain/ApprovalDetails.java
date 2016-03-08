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
 * A ApprovalDetails.
 */
@Entity
@Table(name = "approval_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApprovalDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "approved_date")
    private ZonedDateTime approvedDate;
    
    @Column(name = "approved_emp_no")
    private String approvedEmpNo;
    
    @Column(name = "approved_emp_name")
    private String approvedEmpName;
    
    @Column(name = "approved_emp_desig")
    private String approvedEmpDesig;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "feasibility_status_id")
    private FeasibilityStatus feasibilityStatus;

    @ManyToOne
    @JoinColumn(name = "designation_master_id")
    private DesignationMaster designationMaster;

    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ZonedDateTime getApprovedDate() {
        return approvedDate;
    }
    
    public void setApprovedDate(ZonedDateTime approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedEmpNo() {
        return approvedEmpNo;
    }
    
    public void setApprovedEmpNo(String approvedEmpNo) {
        this.approvedEmpNo = approvedEmpNo;
    }

    public String getApprovedEmpName() {
        return approvedEmpName;
    }
    
    public void setApprovedEmpName(String approvedEmpName) {
        this.approvedEmpName = approvedEmpName;
    }

    public String getApprovedEmpDesig() {
        return approvedEmpDesig;
    }
    
    public void setApprovedEmpDesig(String approvedEmpDesig) {
        this.approvedEmpDesig = approvedEmpDesig;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public FeasibilityStatus getFeasibilityStatus() {
        return feasibilityStatus;
    }

    public void setFeasibilityStatus(FeasibilityStatus feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApprovalDetails approvalDetails = (ApprovalDetails) o;
        if(approvalDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, approvalDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ApprovalDetails{" +
            "id=" + id +
            ", remarks='" + remarks + "'" +
            ", approvedDate='" + approvedDate + "'" +
            ", approvedEmpNo='" + approvedEmpNo + "'" +
            ", approvedEmpName='" + approvedEmpName + "'" +
            ", approvedEmpDesig='" + approvedEmpDesig + "'" +
            '}';
    }
}

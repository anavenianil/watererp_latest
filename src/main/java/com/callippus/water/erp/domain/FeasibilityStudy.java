package com.callippus.water.erp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FeasibilityStudy.
 */
@Entity
@Table(name = "feasibility_study")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeasibilityStudy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "created_date")
    private ZonedDateTime createdDate;
    
    @Column(name = "modified_date")
    private ZonedDateTime modifiedDate;
    
    @Column(name = "prepared_date")
    private ZonedDateTime preparedDate;
    
    @Column(name = "zonal_head_approval_date")
    private ZonedDateTime zonalHeadApprovalDate;
    
    @Column(name = "dept_head_inspected_date")
    private ZonedDateTime deptHeadInspectedDate;
    
    @Column(name = "operation_mangrapprove_date")
    private ZonedDateTime operationMangrapproveDate;
    
    @Column(name = "status")
    private Integer status;
    
    @ManyToOne
    @JoinColumn(name = "division_master_id")
    private DivisionMaster divisionMaster;

    @ManyToOne
    @JoinColumn(name = "zone_master_id")
    private ZoneMaster zoneMaster;

    @ManyToOne
    @JoinColumn(name = "street_master_id")
    private StreetMaster streetMaster;

    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    @ManyToOne
    @JoinColumn(name = "prepared_by_id")
    private User preparedBy;

    @ManyToOne
    @JoinColumn(name = "approved_by_zonal_head_id")
    private User approvedByZonalHead;

    @ManyToOne
    @JoinColumn(name = "inspection_by_department_head_id")
    private User inspectionByDepartmentHead;

    @ManyToOne
    @JoinColumn(name = "approved_by_operation_manager_id")
    private User approvedByOperationManager;

    @ManyToOne
    @JoinColumn(name = "category_master_id")
    private CategoryMaster categoryMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }
    
    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ZonedDateTime getPreparedDate() {
        return preparedDate;
    }
    
    public void setPreparedDate(ZonedDateTime preparedDate) {
        this.preparedDate = preparedDate;
    }

    public ZonedDateTime getZonalHeadApprovalDate() {
        return zonalHeadApprovalDate;
    }
    
    public void setZonalHeadApprovalDate(ZonedDateTime zonalHeadApprovalDate) {
        this.zonalHeadApprovalDate = zonalHeadApprovalDate;
    }

    public ZonedDateTime getDeptHeadInspectedDate() {
        return deptHeadInspectedDate;
    }
    
    public void setDeptHeadInspectedDate(ZonedDateTime deptHeadInspectedDate) {
        this.deptHeadInspectedDate = deptHeadInspectedDate;
    }

    public ZonedDateTime getOperationMangrapproveDate() {
        return operationMangrapproveDate;
    }
    
    public void setOperationMangrapproveDate(ZonedDateTime operationMangrapproveDate) {
        this.operationMangrapproveDate = operationMangrapproveDate;
    }

    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public DivisionMaster getDivisionMaster() {
        return divisionMaster;
    }

    public void setDivisionMaster(DivisionMaster divisionMaster) {
        this.divisionMaster = divisionMaster;
    }

    public ZoneMaster getZoneMaster() {
        return zoneMaster;
    }

    public void setZoneMaster(ZoneMaster zoneMaster) {
        this.zoneMaster = zoneMaster;
    }

    public StreetMaster getStreetMaster() {
        return streetMaster;
    }

    public void setStreetMaster(StreetMaster streetMaster) {
        this.streetMaster = streetMaster;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    public User getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(User user) {
        this.preparedBy = user;
    }

    public User getApprovedByZonalHead() {
        return approvedByZonalHead;
    }

    public void setApprovedByZonalHead(User user) {
        this.approvedByZonalHead = user;
    }

    public User getInspectionByDepartmentHead() {
        return inspectionByDepartmentHead;
    }

    public void setInspectionByDepartmentHead(User user) {
        this.inspectionByDepartmentHead = user;
    }

    public User getApprovedByOperationManager() {
        return approvedByOperationManager;
    }

    public void setApprovedByOperationManager(User user) {
        this.approvedByOperationManager = user;
    }

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeasibilityStudy feasibilityStudy = (FeasibilityStudy) o;
        if(feasibilityStudy.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, feasibilityStudy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FeasibilityStudy{" +
            "id=" + id +
            ", createdDate='" + createdDate + "'" +
            ", modifiedDate='" + modifiedDate + "'" +
            ", preparedDate='" + preparedDate + "'" +
            ", zonalHeadApprovalDate='" + zonalHeadApprovalDate + "'" +
            ", deptHeadInspectedDate='" + deptHeadInspectedDate + "'" +
            ", operationMangrapproveDate='" + operationMangrapproveDate + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

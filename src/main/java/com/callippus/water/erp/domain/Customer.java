package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.callippus.water.erp.domain.enumeration.ChangeCaseStatus;

import com.callippus.water.erp.domain.enumeration.ChangeCaseType;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "prev_meter_reading")
    private Float prevMeterReading;
    
    @Column(name = "new_meter_reading")
    private Float newMeterReading;
    
    @Column(name = "organization")
    private Boolean organization;
    
    @Column(name = "organization_name")
    private String organizationName;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "deed_doc")
    private String deedDoc;
    
    @Column(name = "agreement_doc")
    private String agreementDoc;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "requested_date")
    private LocalDate requestedDate;
    
    @Column(name = "can")
    private String can;
    
    @Column(name = "previous_name")
    private String previousName;
    
    @Column(name = "previous_mobile")
    private Long previousMobile;
    
    @Column(name = "previous_email")
    private String previousEmail;
    
    @Column(name = "new_email")
    private String newEmail;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "mobile_no")
    private Long mobileNo;
    
    @Column(name = "id_number")
    private String idNumber;
    
    @Column(name = "photo")
    private String photo;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ChangeCaseStatus status;
    
    @Column(name = "changed_date")
    private LocalDate changedDate;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "change_type", nullable = false)
    private ChangeCaseType changeType;
    
    @ManyToOne
    @JoinColumn(name = "old_tariff_category_id")
    private TariffCategoryMaster oldTariffCategory;

    @ManyToOne
    @JoinColumn(name = "new_tariff_category_id")
    private TariffCategoryMaster newTariffCategory;

    @ManyToOne
    @JoinColumn(name = "new_proof_master_id")
    private IdProofMaster newProofMaster;

    @ManyToOne
    @JoinColumn(name = "old_pipe_size_master_id")
    private PipeSizeMaster oldPipeSizeMaster;

    @ManyToOne
    @JoinColumn(name = "requested_pipe_size_master_id")
    private PipeSizeMaster requestedPipeSizeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrevMeterReading() {
        return prevMeterReading;
    }
    
    public void setPrevMeterReading(Float prevMeterReading) {
        this.prevMeterReading = prevMeterReading;
    }

    public Float getNewMeterReading() {
        return newMeterReading;
    }
    
    public void setNewMeterReading(Float newMeterReading) {
        this.newMeterReading = newMeterReading;
    }

    public Boolean getOrganization() {
        return organization;
    }
    
    public void setOrganization(Boolean organization) {
        this.organization = organization;
    }

    public String getOrganizationName() {
        return organizationName;
    }
    
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDeedDoc() {
        return deedDoc;
    }
    
    public void setDeedDoc(String deedDoc) {
        this.deedDoc = deedDoc;
    }

    public String getAgreementDoc() {
        return agreementDoc;
    }
    
    public void setAgreementDoc(String agreementDoc) {
        this.agreementDoc = agreementDoc;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }
    
    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getCan() {
        return can;
    }
    
    public void setCan(String can) {
        this.can = can;
    }

    public String getPreviousName() {
        return previousName;
    }
    
    public void setPreviousName(String previousName) {
        this.previousName = previousName;
    }

    public Long getPreviousMobile() {
        return previousMobile;
    }
    
    public void setPreviousMobile(Long previousMobile) {
        this.previousMobile = previousMobile;
    }

    public String getPreviousEmail() {
        return previousEmail;
    }
    
    public void setPreviousEmail(String previousEmail) {
        this.previousEmail = previousEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }
    
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }
    
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getIdNumber() {
        return idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ChangeCaseStatus getStatus() {
        return status;
    }
    
    public void setStatus(ChangeCaseStatus status) {
        this.status = status;
    }

    public LocalDate getChangedDate() {
        return changedDate;
    }
    
    public void setChangedDate(LocalDate changedDate) {
        this.changedDate = changedDate;
    }

    public ChangeCaseType getChangeType() {
        return changeType;
    }
    
    public void setChangeType(ChangeCaseType changeType) {
        this.changeType = changeType;
    }

    public TariffCategoryMaster getOldTariffCategory() {
        return oldTariffCategory;
    }

    public void setOldTariffCategory(TariffCategoryMaster tariffCategoryMaster) {
        this.oldTariffCategory = tariffCategoryMaster;
    }

    public TariffCategoryMaster getNewTariffCategory() {
        return newTariffCategory;
    }

    public void setNewTariffCategory(TariffCategoryMaster tariffCategoryMaster) {
        this.newTariffCategory = tariffCategoryMaster;
    }

    public IdProofMaster getNewProofMaster() {
        return newProofMaster;
    }

    public void setNewProofMaster(IdProofMaster idProofMaster) {
        this.newProofMaster = idProofMaster;
    }

    public PipeSizeMaster getOldPipeSizeMaster() {
        return oldPipeSizeMaster;
    }

    public void setOldPipeSizeMaster(PipeSizeMaster pipeSizeMaster) {
        this.oldPipeSizeMaster = pipeSizeMaster;
    }

    public PipeSizeMaster getRequestedPipeSizeMaster() {
        return requestedPipeSizeMaster;
    }

    public void setRequestedPipeSizeMaster(PipeSizeMaster pipeSizeMaster) {
        this.requestedPipeSizeMaster = pipeSizeMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if(customer.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + id +
            ", prevMeterReading='" + prevMeterReading + "'" +
            ", newMeterReading='" + newMeterReading + "'" +
            ", organization='" + organization + "'" +
            ", organizationName='" + organizationName + "'" +
            ", designation='" + designation + "'" +
            ", deedDoc='" + deedDoc + "'" +
            ", agreementDoc='" + agreementDoc + "'" +
            ", remarks='" + remarks + "'" +
            ", requestedDate='" + requestedDate + "'" +
            ", can='" + can + "'" +
            ", previousName='" + previousName + "'" +
            ", previousMobile='" + previousMobile + "'" +
            ", previousEmail='" + previousEmail + "'" +
            ", newEmail='" + newEmail + "'" +
            ", firstName='" + firstName + "'" +
            ", middleName='" + middleName + "'" +
            ", lastName='" + lastName + "'" +
            ", mobileNo='" + mobileNo + "'" +
            ", idNumber='" + idNumber + "'" +
            ", photo='" + photo + "'" +
            ", status='" + status + "'" +
            ", changedDate='" + changedDate + "'" +
            ", changeType='" + changeType + "'" +
            '}';
    }
}

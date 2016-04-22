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
 * A ApplicationTxn.
 */
@Entity
@Table(name = "application_txn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationTxn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "organization")
    private Boolean organization;
    
    @Column(name = "organization_name")
    private String organizationName;
    
    @Column(name = "designation")
    private String designation;
    
    @Column(name = "mobile_no")
    private Long mobileNo;
    
    @Column(name = "office_no")
    private Long officeNo;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "plot_no")
    private String plotNo;
    
    @Column(name = "block_no")
    private String blockNo;
    
    @Column(name = "tanesco_meter")
    private String tanescoMeter;
    
    @Column(name = "water_connection_use")
    private String waterConnectionUse;
    
    @Column(name = "b_street")
    private String bStreet;
    
    @Column(name = "ward")
    private String ward;
    
    @Column(name = "dma")
    private String dma;
    
    @Column(name = "b_plot_no")
    private String bPlotNo;
    
    @Column(name = "registered_mobile")
    private Long registeredMobile;
    
    @Column(name = "attached_doc_type")
    private String attachedDocType;
    
    @Column(name = "id_number")
    private String idNumber;
    
    @Column(name = "property_doc")
    private String propertyDoc;
    
    @Column(name = "can")
    private String can;
    
    @Column(name = "photo")
    private String photo;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "meter_reading")
    private Float meterReading;
    
    @Column(name = "requested_date")
    private LocalDate requestedDate;
    
    @Column(name = "connection_date")
    private LocalDate connectionDate;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "meter_no")
    private String meterNo;
    
    @Column(name = "approved_date")
    private LocalDate approvedDate;
    
    @ManyToOne
    @JoinColumn(name = "tariff_category_master_id")
    private TariffCategoryMaster tariffCategoryMaster;

    @ManyToOne
    @JoinColumn(name = "meter_details_id")
    private MeterDetails meterDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "request_at_id")
    private User requestAt;

    @ManyToOne
    @JoinColumn(name = "division_master_id")
    private DivisionMaster divisionMaster;

    @ManyToOne
    @JoinColumn(name = "street_master_id")
    private StreetMaster streetMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Long getOfficeNo() {
        return officeNo;
    }
    
    public void setOfficeNo(Long officeNo) {
        this.officeNo = officeNo;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPlotNo() {
        return plotNo;
    }
    
    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getBlockNo() {
        return blockNo;
    }
    
    public void setBlockNo(String blockNo) {
        this.blockNo = blockNo;
    }

    public String getTanescoMeter() {
        return tanescoMeter;
    }
    
    public void setTanescoMeter(String tanescoMeter) {
        this.tanescoMeter = tanescoMeter;
    }

    public String getWaterConnectionUse() {
        return waterConnectionUse;
    }
    
    public void setWaterConnectionUse(String waterConnectionUse) {
        this.waterConnectionUse = waterConnectionUse;
    }

    public String getbStreet() {
        return bStreet;
    }
    
    public void setbStreet(String bStreet) {
        this.bStreet = bStreet;
    }

    public String getWard() {
        return ward;
    }
    
    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDma() {
        return dma;
    }
    
    public void setDma(String dma) {
        this.dma = dma;
    }

    public String getbPlotNo() {
        return bPlotNo;
    }
    
    public void setbPlotNo(String bPlotNo) {
        this.bPlotNo = bPlotNo;
    }

    public Long getRegisteredMobile() {
        return registeredMobile;
    }
    
    public void setRegisteredMobile(Long registeredMobile) {
        this.registeredMobile = registeredMobile;
    }

    public String getAttachedDocType() {
        return attachedDocType;
    }
    
    public void setAttachedDocType(String attachedDocType) {
        this.attachedDocType = attachedDocType;
    }

    public String getIdNumber() {
        return idNumber;
    }
    
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPropertyDoc() {
        return propertyDoc;
    }
    
    public void setPropertyDoc(String propertyDoc) {
        this.propertyDoc = propertyDoc;
    }

    public String getCan() {
        return can;
    }
    
    public void setCan(String can) {
        this.can = can;
    }

    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getMeterReading() {
        return meterReading;
    }
    
    public void setMeterReading(Float meterReading) {
        this.meterReading = meterReading;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }
    
    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDate getConnectionDate() {
        return connectionDate;
    }
    
    public void setConnectionDate(LocalDate connectionDate) {
        this.connectionDate = connectionDate;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMeterNo() {
        return meterNo;
    }
    
    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }
    
    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public TariffCategoryMaster getTariffCategoryMaster() {
        return tariffCategoryMaster;
    }

    public void setTariffCategoryMaster(TariffCategoryMaster tariffCategoryMaster) {
        this.tariffCategoryMaster = tariffCategoryMaster;
    }

    public MeterDetails getMeterDetails() {
        return meterDetails;
    }

    public void setMeterDetails(MeterDetails meterDetails) {
        this.meterDetails = meterDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRequestAt() {
        return requestAt;
    }

    public void setRequestAt(User user) {
        this.requestAt = user;
    }

    public DivisionMaster getDivisionMaster() {
        return divisionMaster;
    }

    public void setDivisionMaster(DivisionMaster divisionMaster) {
        this.divisionMaster = divisionMaster;
    }

    public StreetMaster getStreetMaster() {
        return streetMaster;
    }

    public void setStreetMaster(StreetMaster streetMaster) {
        this.streetMaster = streetMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationTxn applicationTxn = (ApplicationTxn) o;
        if(applicationTxn.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, applicationTxn.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ApplicationTxn{" +
            "id=" + id +
            ", firstName='" + firstName + "'" +
            ", middleName='" + middleName + "'" +
            ", lastName='" + lastName + "'" +
            ", organization='" + organization + "'" +
            ", organizationName='" + organizationName + "'" +
            ", designation='" + designation + "'" +
            ", mobileNo='" + mobileNo + "'" +
            ", officeNo='" + officeNo + "'" +
            ", email='" + email + "'" +
            ", street='" + street + "'" +
            ", plotNo='" + plotNo + "'" +
            ", blockNo='" + blockNo + "'" +
            ", tanescoMeter='" + tanescoMeter + "'" +
            ", waterConnectionUse='" + waterConnectionUse + "'" +
            ", bStreet='" + bStreet + "'" +
            ", ward='" + ward + "'" +
            ", dma='" + dma + "'" +
            ", bPlotNo='" + bPlotNo + "'" +
            ", registeredMobile='" + registeredMobile + "'" +
            ", attachedDocType='" + attachedDocType + "'" +
            ", idNumber='" + idNumber + "'" +
            ", propertyDoc='" + propertyDoc + "'" +
            ", can='" + can + "'" +
            ", photo='" + photo + "'" +
            ", status='" + status + "'" +
            ", meterReading='" + meterReading + "'" +
            ", requestedDate='" + requestedDate + "'" +
            ", connectionDate='" + connectionDate + "'" +
            ", remarks='" + remarks + "'" +
            ", meterNo='" + meterNo + "'" +
            ", approvedDate='" + approvedDate + "'" +
            '}';
    }
}

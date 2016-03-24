package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "full_name")
    private String fullName;
    
    @Column(name = "home_or_ofice_number")
    private Integer homeOrOficeNumber;
    
    @Column(name = "regional_number")
    private Integer regionalNumber;
    
    @Column(name = "fax_number")
    private Integer faxNumber;
    
    @Column(name = "plot_number")
    private String plotNumber;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "village_executive_office")
    private String villageExecutiveOffice;
    
    @Column(name = "village_executive_office_number")
    private String villageExecutiveOfficeNumber;
    
    @Column(name = "house")
    private String house;
    
    @Column(name = "institution")
    private String institution;
    
    @Column(name = "business")
    private String business;
    
    @Column(name = "industry")
    private String industry;
    
    @Column(name = "po_box")
    private String poBox;
    
    @Column(name = "requested_date")
    private ZonedDateTime requestedDate;
    
    @Column(name = "photo")
    private String photo;
    
    @Column(name = "file_number")
    private String fileNumber;
    
    @Column(name = "created_date")
    private ZonedDateTime createdDate;
    
    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;
    
    @Column(name = "status")
    private Integer status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getHomeOrOficeNumber() {
        return homeOrOficeNumber;
    }
    
    public void setHomeOrOficeNumber(Integer homeOrOficeNumber) {
        this.homeOrOficeNumber = homeOrOficeNumber;
    }

    public Integer getRegionalNumber() {
        return regionalNumber;
    }
    
    public void setRegionalNumber(Integer regionalNumber) {
        this.regionalNumber = regionalNumber;
    }

    public Integer getFaxNumber() {
        return faxNumber;
    }
    
    public void setFaxNumber(Integer faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getPlotNumber() {
        return plotNumber;
    }
    
    public void setPlotNumber(String plotNumber) {
        this.plotNumber = plotNumber;
    }

    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    public String getVillageExecutiveOffice() {
        return villageExecutiveOffice;
    }
    
    public void setVillageExecutiveOffice(String villageExecutiveOffice) {
        this.villageExecutiveOffice = villageExecutiveOffice;
    }

    public String getVillageExecutiveOfficeNumber() {
        return villageExecutiveOfficeNumber;
    }
    
    public void setVillageExecutiveOfficeNumber(String villageExecutiveOfficeNumber) {
        this.villageExecutiveOfficeNumber = villageExecutiveOfficeNumber;
    }

    public String getHouse() {
        return house;
    }
    
    public void setHouse(String house) {
        this.house = house;
    }

    public String getInstitution() {
        return institution;
    }
    
    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getBusiness() {
        return business;
    }
    
    public void setBusiness(String business) {
        this.business = business;
    }

    public String getIndustry() {
        return industry;
    }
    
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getPoBox() {
        return poBox;
    }
    
    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public ZonedDateTime getRequestedDate() {
        return requestedDate;
    }
    
    public void setRequestedDate(ZonedDateTime requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getPhoto() {
        return photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFileNumber() {
        return fileNumber;
    }
    
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
            ", fullName='" + fullName + "'" +
            ", homeOrOficeNumber='" + homeOrOficeNumber + "'" +
            ", regionalNumber='" + regionalNumber + "'" +
            ", faxNumber='" + faxNumber + "'" +
            ", plotNumber='" + plotNumber + "'" +
            ", area='" + area + "'" +
            ", street='" + street + "'" +
            ", villageExecutiveOffice='" + villageExecutiveOffice + "'" +
            ", villageExecutiveOfficeNumber='" + villageExecutiveOfficeNumber + "'" +
            ", house='" + house + "'" +
            ", institution='" + institution + "'" +
            ", business='" + business + "'" +
            ", industry='" + industry + "'" +
            ", poBox='" + poBox + "'" +
            ", requestedDate='" + requestedDate + "'" +
            ", photo='" + photo + "'" +
            ", fileNumber='" + fileNumber + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

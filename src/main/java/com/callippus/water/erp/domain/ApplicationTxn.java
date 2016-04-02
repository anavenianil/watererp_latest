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
    
    @Column(name = "home_or_office_number")
    private Long homeOrOfficeNumber;
    
    @Column(name = "regional_number")
    private Long regionalNumber;
    
    @Column(name = "fax_number")
    private Long faxNumber;
    
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
    
    @Column(name = "detail_address")
    private String detailAddress;
    
    @ManyToOne
    @JoinColumn(name = "category_master_id")
    private CategoryMaster categoryMaster;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

    public Long getHomeOrOfficeNumber() {
        return homeOrOfficeNumber;
    }
    
    public void setHomeOrOfficeNumber(Long homeOrOfficeNumber) {
        this.homeOrOfficeNumber = homeOrOfficeNumber;
    }

    public Long getRegionalNumber() {
        return regionalNumber;
    }
    
    public void setRegionalNumber(Long regionalNumber) {
        this.regionalNumber = regionalNumber;
    }

    public Long getFaxNumber() {
        return faxNumber;
    }
    
    public void setFaxNumber(Long faxNumber) {
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

    public String getDetailAddress() {
        return detailAddress;
    }
    
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
            ", homeOrOfficeNumber='" + homeOrOfficeNumber + "'" +
            ", regionalNumber='" + regionalNumber + "'" +
            ", faxNumber='" + faxNumber + "'" +
            ", plotNumber='" + plotNumber + "'" +
            ", area='" + area + "'" +
            ", street='" + street + "'" +
            ", villageExecutiveOffice='" + villageExecutiveOffice + "'" +
            ", villageExecutiveOfficeNumber='" + villageExecutiveOfficeNumber + "'" +
            ", poBox='" + poBox + "'" +
            ", requestedDate='" + requestedDate + "'" +
            ", photo='" + photo + "'" +
            ", fileNumber='" + fileNumber + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", status='" + status + "'" +
            ", detailAddress='" + detailAddress + "'" +
            '}';
    }
}

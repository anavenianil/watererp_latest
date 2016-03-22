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

    @Column(name = "s_house_no")
    private String sHouseNo;
    
    @Column(name = "govt_official_no")
    private String govtOfficialNo;
    
    @Column(name = "ward")
    private String ward;
    
    @Column(name = "street")
    private String street;
    
    @Column(name = "pincode")
    private String pincode;
    
    @Column(name = "block")
    private String block;
    
    @Column(name = "area")
    private String area;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "constituency")
    private String constituency;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "telephone_number")
    private String telephoneNumber;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "scan_plan")
    private Float scanPlan;
    
    @Column(name = "scan_plan1")
    private Float scanPlan1;
    
    @Column(name = "sale_deed")
    private Float saleDeed;
    
    @Column(name = "sale_deed1")
    private Float saleDeed1;
    
    @Column(name = "total_plinth_area")
    private Float totalPlinthArea;
    
    @Column(name = "created_date")
    private ZonedDateTime createdDate;
    
    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "file_number")
    private String fileNumber;
    
    @ManyToOne
    @JoinColumn(name = "application_type_master_id")
    private ApplicationTypeMaster applicationTypeMaster;

    @ManyToOne
    @JoinColumn(name = "connection_type_master_id")
    private ConnectionTypeMaster connectionTypeMaster;

    @ManyToOne
    @JoinColumn(name = "category_master_id")
    private CategoryMaster categoryMaster;

    @ManyToOne
    @JoinColumn(name = "pipe_size_master_id")
    private PipeSizeMaster pipeSizeMaster;

    @ManyToOne
    @JoinColumn(name = "sewer_size_id")
    private SewerSize sewerSize;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getsHouseNo() {
        return sHouseNo;
    }
    
    public void setsHouseNo(String sHouseNo) {
        this.sHouseNo = sHouseNo;
    }

    public String getGovtOfficialNo() {
        return govtOfficialNo;
    }
    
    public void setGovtOfficialNo(String govtOfficialNo) {
        this.govtOfficialNo = govtOfficialNo;
    }

    public String getWard() {
        return ward;
    }
    
    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getStreet() {
        return street;
    }
    
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPincode() {
        return pincode;
    }
    
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getBlock() {
        return block;
    }
    
    public void setBlock(String block) {
        this.block = block;
    }

    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }

    public String getSection() {
        return section;
    }
    
    public void setSection(String section) {
        this.section = section;
    }

    public String getConstituency() {
        return constituency;
    }
    
    public void setConstituency(String constituency) {
        this.constituency = constituency;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }
    
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Float getScanPlan() {
        return scanPlan;
    }
    
    public void setScanPlan(Float scanPlan) {
        this.scanPlan = scanPlan;
    }

    public Float getScanPlan1() {
        return scanPlan1;
    }
    
    public void setScanPlan1(Float scanPlan1) {
        this.scanPlan1 = scanPlan1;
    }

    public Float getSaleDeed() {
        return saleDeed;
    }
    
    public void setSaleDeed(Float saleDeed) {
        this.saleDeed = saleDeed;
    }

    public Float getSaleDeed1() {
        return saleDeed1;
    }
    
    public void setSaleDeed1(Float saleDeed1) {
        this.saleDeed1 = saleDeed1;
    }

    public Float getTotalPlinthArea() {
        return totalPlinthArea;
    }
    
    public void setTotalPlinthArea(Float totalPlinthArea) {
        this.totalPlinthArea = totalPlinthArea;
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

    public String getFileNumber() {
        return fileNumber;
    }
    
    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public ApplicationTypeMaster getApplicationTypeMaster() {
        return applicationTypeMaster;
    }

    public void setApplicationTypeMaster(ApplicationTypeMaster applicationTypeMaster) {
        this.applicationTypeMaster = applicationTypeMaster;
    }

    public ConnectionTypeMaster getConnectionTypeMaster() {
        return connectionTypeMaster;
    }

    public void setConnectionTypeMaster(ConnectionTypeMaster connectionTypeMaster) {
        this.connectionTypeMaster = connectionTypeMaster;
    }

    public CategoryMaster getCategoryMaster() {
        return categoryMaster;
    }

    public void setCategoryMaster(CategoryMaster categoryMaster) {
        this.categoryMaster = categoryMaster;
    }

    public PipeSizeMaster getPipeSizeMaster() {
        return pipeSizeMaster;
    }

    public void setPipeSizeMaster(PipeSizeMaster pipeSizeMaster) {
        this.pipeSizeMaster = pipeSizeMaster;
    }

    public SewerSize getSewerSize() {
        return sewerSize;
    }

    public void setSewerSize(SewerSize sewerSize) {
        this.sewerSize = sewerSize;
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
            ", sHouseNo='" + sHouseNo + "'" +
            ", govtOfficialNo='" + govtOfficialNo + "'" +
            ", ward='" + ward + "'" +
            ", street='" + street + "'" +
            ", pincode='" + pincode + "'" +
            ", block='" + block + "'" +
            ", area='" + area + "'" +
            ", section='" + section + "'" +
            ", constituency='" + constituency + "'" +
            ", email='" + email + "'" +
            ", telephoneNumber='" + telephoneNumber + "'" +
            ", mobile='" + mobile + "'" +
            ", scanPlan='" + scanPlan + "'" +
            ", scanPlan1='" + scanPlan1 + "'" +
            ", saleDeed='" + saleDeed + "'" +
            ", saleDeed1='" + saleDeed1 + "'" +
            ", totalPlinthArea='" + totalPlinthArea + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", status='" + status + "'" +
            ", fileNumber='" + fileNumber + "'" +
            '}';
    }
}

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
 * A Customer.
 */
@Entity
@Table(name = "customer")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "request_date")
    private ZonedDateTime requestDate;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "house_no")
    private String houseNo;
    
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
    
    @Column(name = "tel_office")
    private String telOffice;
    
    @Column(name = "tel_home")
    private String telHome;
    
    @Column(name = "mobile")
    private String mobile;
    
    @ManyToOne
    @JoinColumn(name = "file_number_id")
    private FileNumber fileNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getRequestDate() {
        return requestDate;
    }
    
    public void setRequestDate(ZonedDateTime requestDate) {
        this.requestDate = requestDate;
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

    public String getHouseNo() {
        return houseNo;
    }
    
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
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

    public String getTelOffice() {
        return telOffice;
    }
    
    public void setTelOffice(String telOffice) {
        this.telOffice = telOffice;
    }

    public String getTelHome() {
        return telHome;
    }
    
    public void setTelHome(String telHome) {
        this.telHome = telHome;
    }

    public String getMobile() {
        return mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public FileNumber getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(FileNumber fileNumber) {
        this.fileNumber = fileNumber;
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
            ", requestDate='" + requestDate + "'" +
            ", firstName='" + firstName + "'" +
            ", middleName='" + middleName + "'" +
            ", lastName='" + lastName + "'" +
            ", houseNo='" + houseNo + "'" +
            ", govtOfficialNo='" + govtOfficialNo + "'" +
            ", ward='" + ward + "'" +
            ", street='" + street + "'" +
            ", pincode='" + pincode + "'" +
            ", block='" + block + "'" +
            ", area='" + area + "'" +
            ", section='" + section + "'" +
            ", constituency='" + constituency + "'" +
            ", email='" + email + "'" +
            ", telOffice='" + telOffice + "'" +
            ", telHome='" + telHome + "'" +
            ", mobile='" + mobile + "'" +
            '}';
    }
}

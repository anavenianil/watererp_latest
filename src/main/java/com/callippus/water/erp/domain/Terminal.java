package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Terminal.
 */
@Entity
@Table(name = "terminal")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Terminal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Float amount;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "mr_code")
    private String mrCode;
    
    @Column(name = "sec_code")
    private String secCode;
    
    @Column(name = "div_code")
    private String divCode;
    
    @Column(name = "sec_name")
    private String secName;
    
    @Column(name = "user_name")
    private String userName;
    
    @Column(name = "mobile_no")
    private String mobileNo;
    
    @Column(name = "ver")
    private String ver;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }
    
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMrCode() {
        return mrCode;
    }
    
    public void setMrCode(String mrCode) {
        this.mrCode = mrCode;
    }

    public String getSecCode() {
        return secCode;
    }
    
    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getDivCode() {
        return divCode;
    }
    
    public void setDivCode(String divCode) {
        this.divCode = divCode;
    }

    public String getSecName() {
        return secName;
    }
    
    public void setSecName(String secName) {
        this.secName = secName;
    }

    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getVer() {
        return ver;
    }
    
    public void setVer(String ver) {
        this.ver = ver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Terminal terminal = (Terminal) o;
        if(terminal.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, terminal.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Terminal{" +
            "id=" + id +
            ", amount='" + amount + "'" +
            ", status='" + status + "'" +
            ", userId='" + userId + "'" +
            ", mrCode='" + mrCode + "'" +
            ", secCode='" + secCode + "'" +
            ", divCode='" + divCode + "'" +
            ", secName='" + secName + "'" +
            ", userName='" + userName + "'" +
            ", mobileNo='" + mobileNo + "'" +
            ", ver='" + ver + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SibEntry.
 */
@Entity
@Table(name = "sib_entry")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SibEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sib_id")
    private Long sibId;
    
    @Column(name = "so_no")
    private String soNo;
    
    @Column(name = "so_date")
    private ZonedDateTime soDate;
    
    @Column(name = "demand_date")
    private ZonedDateTime demandDate;
    
    @Column(name = "dir")
    private String dir;
    
    @Column(name = "div_name")
    private String divName;
    
    @Column(name = "inv_no")
    private Long invNo;
    
    @Column(name = "sib_date")
    private ZonedDateTime sibDate;
    
    @Column(name = "sib_no")
    private String sibNo;
    
    @Column(name = "ir_date")
    private ZonedDateTime irDate;
    
    @Column(name = "ir_no")
    private String irNo;
    
    @Column(name = "vendor_code")
    private String vendorCode;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "to_user")
    private ZonedDateTime toUser;
    
    @Column(name = "from_user")
    private ZonedDateTime fromUser;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "dc_no")
    private String dcNo;
    
    @Column(name = "dc_date")
    private ZonedDateTime dcDate;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSibId() {
        return sibId;
    }
    
    public void setSibId(Long sibId) {
        this.sibId = sibId;
    }

    public String getSoNo() {
        return soNo;
    }
    
    public void setSoNo(String soNo) {
        this.soNo = soNo;
    }

    public ZonedDateTime getSoDate() {
        return soDate;
    }
    
    public void setSoDate(ZonedDateTime soDate) {
        this.soDate = soDate;
    }

    public ZonedDateTime getDemandDate() {
        return demandDate;
    }
    
    public void setDemandDate(ZonedDateTime demandDate) {
        this.demandDate = demandDate;
    }

    public String getDir() {
        return dir;
    }
    
    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDivName() {
        return divName;
    }
    
    public void setDivName(String divName) {
        this.divName = divName;
    }

    public Long getInvNo() {
        return invNo;
    }
    
    public void setInvNo(Long invNo) {
        this.invNo = invNo;
    }

    public ZonedDateTime getSibDate() {
        return sibDate;
    }
    
    public void setSibDate(ZonedDateTime sibDate) {
        this.sibDate = sibDate;
    }

    public String getSibNo() {
        return sibNo;
    }
    
    public void setSibNo(String sibNo) {
        this.sibNo = sibNo;
    }

    public ZonedDateTime getIrDate() {
        return irDate;
    }
    
    public void setIrDate(ZonedDateTime irDate) {
        this.irDate = irDate;
    }

    public String getIrNo() {
        return irNo;
    }
    
    public void setIrNo(String irNo) {
        this.irNo = irNo;
    }

    public String getVendorCode() {
        return vendorCode;
    }
    
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ZonedDateTime getToUser() {
        return toUser;
    }
    
    public void setToUser(ZonedDateTime toUser) {
        this.toUser = toUser;
    }

    public ZonedDateTime getFromUser() {
        return fromUser;
    }
    
    public void setFromUser(ZonedDateTime fromUser) {
        this.fromUser = fromUser;
    }

    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDcNo() {
        return dcNo;
    }
    
    public void setDcNo(String dcNo) {
        this.dcNo = dcNo;
    }

    public ZonedDateTime getDcDate() {
        return dcDate;
    }
    
    public void setDcDate(ZonedDateTime dcDate) {
        this.dcDate = dcDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SibEntry sibEntry = (SibEntry) o;
        if(sibEntry.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, sibEntry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SibEntry{" +
            "id=" + id +
            ", sibId='" + sibId + "'" +
            ", soNo='" + soNo + "'" +
            ", soDate='" + soDate + "'" +
            ", demandDate='" + demandDate + "'" +
            ", dir='" + dir + "'" +
            ", divName='" + divName + "'" +
            ", invNo='" + invNo + "'" +
            ", sibDate='" + sibDate + "'" +
            ", sibNo='" + sibNo + "'" +
            ", irDate='" + irDate + "'" +
            ", irNo='" + irNo + "'" +
            ", vendorCode='" + vendorCode + "'" +
            ", remarks='" + remarks + "'" +
            ", toUser='" + toUser + "'" +
            ", fromUser='" + fromUser + "'" +
            ", status='" + status + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", dcNo='" + dcNo + "'" +
            ", dcDate='" + dcDate + "'" +
            '}';
    }
}

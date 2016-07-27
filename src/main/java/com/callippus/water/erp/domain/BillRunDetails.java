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
 * A BillRunDetails.
 */
@Entity
@Table(name = "bill_run_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BillRunDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "can")
    private String can;

    @Column(name = "from_dt")
    private ZonedDateTime fromDt;

    @Column(name = "to_dt")
    private ZonedDateTime toDt;

    @Column(name = "status")
    private Integer status;

    @Column(name = "remarks")
    private String remarks;

    @ManyToOne
    @JoinColumn(name = "bill_full_details_id")
    private BillFullDetails billFullDetails;

    @ManyToOne
    @JoinColumn(name = "bill_run_master_id")
    private BillRunMaster billRunMaster;

    @ManyToOne
    @JoinColumn(name = "bill_details_id")
    private BillDetails billDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public ZonedDateTime getFromDt() {
        return fromDt;
    }

    public void setFromDt(ZonedDateTime fromDt) {
        this.fromDt = fromDt;
    }

    public ZonedDateTime getToDt() {
        return toDt;
    }

    public void setToDt(ZonedDateTime toDt) {
        this.toDt = toDt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BillFullDetails getBillFullDetails() {
        return billFullDetails;
    }

    public void setBillFullDetails(BillFullDetails billFullDetails) {
        this.billFullDetails = billFullDetails;
    }

    public BillRunMaster getBillRunMaster() {
        return billRunMaster;
    }

    public void setBillRunMaster(BillRunMaster billRunMaster) {
        this.billRunMaster = billRunMaster;
    }

    public BillDetails getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(BillDetails billDetails) {
        this.billDetails = billDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BillRunDetails billRunDetails = (BillRunDetails) o;
        return Objects.equals(id, billRunDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BillRunDetails{" +
            "id=" + id +
            ", can='" + can + "'" +
            ", fromDt='" + fromDt + "'" +
            ", toDt='" + toDt + "'" +
            ", status='" + status + "'" +
            ", remarks='" + remarks + "'" +
            '}';
    }
}

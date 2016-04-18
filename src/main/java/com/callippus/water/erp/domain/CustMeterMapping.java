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

/**
 * A CustMeterMapping.
 */
@Entity
@Table(name = "cust_meter_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustMeterMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "from_date", nullable = false)
    private LocalDate fromDate;
    
    @Column(name = "to_date")
    private LocalDate toDate;
    
    @ManyToOne
    @JoinColumn(name = "cust_details_id")
    private CustDetails custDetails;

    @ManyToOne
    @JoinColumn(name = "meter_details_id")
    private MeterDetails meterDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }
    
    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }
    
    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public CustDetails getCustDetails() {
        return custDetails;
    }

    public void setCustDetails(CustDetails custDetails) {
        this.custDetails = custDetails;
    }

    public MeterDetails getMeterDetails() {
        return meterDetails;
    }

    public void setMeterDetails(MeterDetails meterDetails) {
        this.meterDetails = meterDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustMeterMapping custMeterMapping = (CustMeterMapping) o;
        if(custMeterMapping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, custMeterMapping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CustMeterMapping{" +
            "id=" + id +
            ", fromDate='" + fromDate + "'" +
            ", toDate='" + toDate + "'" +
            '}';
    }
}

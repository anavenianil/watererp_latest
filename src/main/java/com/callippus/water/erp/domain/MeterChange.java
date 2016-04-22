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
 * A MeterChange.
 */
@Entity
@Table(name = "meter_change")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MeterChange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "can")
    private String can;
    
    @Column(name = "reason_for_change")
    private String reasonForChange;
    
    @Column(name = "existing_meter_number")
    private String existingMeterNumber;
    
    @Column(name = "existing_meter_reading")
    private Float existingMeterReading;
    
    @Column(name = "new_meter_number")
    private String newMeterNumber;
    
    @Column(name = "new_meter_reading")
    private Float newMeterReading;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "approved_date")
    private LocalDate approvedDate;
    
    @ManyToOne
    @JoinColumn(name = "cust_details_id")
    private CustDetails custDetails;

    @ManyToOne
    @JoinColumn(name = "meter_details_id")
    private MeterDetails meterDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    public String getReasonForChange() {
        return reasonForChange;
    }
    
    public void setReasonForChange(String reasonForChange) {
        this.reasonForChange = reasonForChange;
    }

    public String getExistingMeterNumber() {
        return existingMeterNumber;
    }
    
    public void setExistingMeterNumber(String existingMeterNumber) {
        this.existingMeterNumber = existingMeterNumber;
    }

    public Float getExistingMeterReading() {
        return existingMeterReading;
    }
    
    public void setExistingMeterReading(Float existingMeterReading) {
        this.existingMeterReading = existingMeterReading;
    }

    public String getNewMeterNumber() {
        return newMeterNumber;
    }
    
    public void setNewMeterNumber(String newMeterNumber) {
        this.newMeterNumber = newMeterNumber;
    }

    public Float getNewMeterReading() {
        return newMeterReading;
    }
    
    public void setNewMeterReading(Float newMeterReading) {
        this.newMeterReading = newMeterReading;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }
    
    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MeterChange meterChange = (MeterChange) o;
        if(meterChange.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, meterChange.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MeterChange{" +
            "id=" + id +
            ", can='" + can + "'" +
            ", reasonForChange='" + reasonForChange + "'" +
            ", existingMeterNumber='" + existingMeterNumber + "'" +
            ", existingMeterReading='" + existingMeterReading + "'" +
            ", newMeterNumber='" + newMeterNumber + "'" +
            ", newMeterReading='" + newMeterReading + "'" +
            ", remarks='" + remarks + "'" +
            ", approvedDate='" + approvedDate + "'" +
            '}';
    }
}

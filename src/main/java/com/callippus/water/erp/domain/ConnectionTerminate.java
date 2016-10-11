package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.callippus.water.erp.domain.enumeration.TerminationType;

import com.callippus.water.erp.domain.enumeration.MeterCondition;

import com.callippus.water.erp.domain.enumeration.TerminationStatus;

/**
 * A ConnectionTerminate.
 */
@Entity
@Table(name = "connection_terminate")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConnectionTerminate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "can")
    private String can;
    
    @Column(name = "request_date")
    private LocalDate requestDate;
    
    @Column(name = "meter_recovered")
    private Boolean meterRecovered;
    
    @Column(name = "last_meter_reading", precision=10, scale=2)
    private BigDecimal lastMeterReading;
    
    @Column(name = "meter_recovered_date")
    private LocalDate meterRecoveredDate;
    
    @Column(name = "metered_connection")
    private Boolean meteredConnection;
    
    @Column(name = "reason")
    private String reason;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "termination_type")
    private TerminationType terminationType;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "meter_condition")
    private MeterCondition meterCondition;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TerminationStatus status;
    
    @ManyToOne
    @JoinColumn(name = "meter_details_id")
    private MeterDetails meterDetails;

    @ManyToOne
    @JoinColumn(name = "tariff_category_master_id")
    private TariffCategoryMaster tariffCategoryMaster;

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

    public LocalDate getRequestDate() {
        return requestDate;
    }
    
    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public Boolean getMeterRecovered() {
        return meterRecovered;
    }
    
    public void setMeterRecovered(Boolean meterRecovered) {
        this.meterRecovered = meterRecovered;
    }

    public BigDecimal getLastMeterReading() {
        return lastMeterReading;
    }
    
    public void setLastMeterReading(BigDecimal lastMeterReading) {
        this.lastMeterReading = lastMeterReading;
    }

    public LocalDate getMeterRecoveredDate() {
        return meterRecoveredDate;
    }
    
    public void setMeterRecoveredDate(LocalDate meterRecoveredDate) {
        this.meterRecoveredDate = meterRecoveredDate;
    }

    public Boolean getMeteredConnection() {
        return meteredConnection;
    }
    
    public void setMeteredConnection(Boolean meteredConnection) {
        this.meteredConnection = meteredConnection;
    }

    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }

    public TerminationType getTerminationType() {
        return terminationType;
    }
    
    public void setTerminationType(TerminationType terminationType) {
        this.terminationType = terminationType;
    }

    public MeterCondition getMeterCondition() {
        return meterCondition;
    }
    
    public void setMeterCondition(MeterCondition meterCondition) {
        this.meterCondition = meterCondition;
    }

    public TerminationStatus getStatus() {
        return status;
    }
    
    public void setStatus(TerminationStatus status) {
        this.status = status;
    }

    public MeterDetails getMeterDetails() {
        return meterDetails;
    }

    public void setMeterDetails(MeterDetails meterDetails) {
        this.meterDetails = meterDetails;
    }

    public TariffCategoryMaster getTariffCategoryMaster() {
        return tariffCategoryMaster;
    }

    public void setTariffCategoryMaster(TariffCategoryMaster tariffCategoryMaster) {
        this.tariffCategoryMaster = tariffCategoryMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectionTerminate connectionTerminate = (ConnectionTerminate) o;
        if(connectionTerminate.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, connectionTerminate.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ConnectionTerminate{" +
            "id=" + id +
            ", can='" + can + "'" +
            ", requestDate='" + requestDate + "'" +
            ", meterRecovered='" + meterRecovered + "'" +
            ", lastMeterReading='" + lastMeterReading + "'" +
            ", meterRecoveredDate='" + meterRecoveredDate + "'" +
            ", meteredConnection='" + meteredConnection + "'" +
            ", reason='" + reason + "'" +
            ", terminationType='" + terminationType + "'" +
            ", meterCondition='" + meterCondition + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

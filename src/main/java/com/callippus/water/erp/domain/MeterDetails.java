package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MeterDetails.
 */
@Entity
@Table(name = "meter_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MeterDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "meter_id", nullable = false)
    private String meterId;
    
    @Column(name = "meter_type")
    private String meterType;
    
    @Column(name = "meter_make")
    private String meterMake;
    
    @Column(name = "min")
    private Float min;
    
    @Column(name = "max")
    private Float max;
    
    @Column(name = "status")
    private Integer status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterId() {
        return meterId;
    }
    
    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getMeterType() {
        return meterType;
    }
    
    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

    public String getMeterMake() {
        return meterMake;
    }
    
    public void setMeterMake(String meterMake) {
        this.meterMake = meterMake;
    }

    public Float getMin() {
        return min;
    }
    
    public void setMin(Float min) {
        this.min = min;
    }

    public Float getMax() {
        return max;
    }
    
    public void setMax(Float max) {
        this.max = max;
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
        MeterDetails meterDetails = (MeterDetails) o;
        if(meterDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, meterDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MeterDetails{" +
            "id=" + id +
            ", meterId='" + meterId + "'" +
            ", meterType='" + meterType + "'" +
            ", meterMake='" + meterMake + "'" +
            ", min='" + min + "'" +
            ", max='" + max + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

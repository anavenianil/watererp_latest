package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PercentageMaster.
 */
@Entity
@Table(name = "percentage_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PercentageMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "percent_type")
    private String percentType;
    
    @Column(name = "percent_value")
    private Double percentValue;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPercentType() {
        return percentType;
    }
    
    public void setPercentType(String percentType) {
        this.percentType = percentType;
    }

    public Double getPercentValue() {
        return percentValue;
    }
    
    public void setPercentValue(Double percentValue) {
        this.percentValue = percentValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PercentageMaster percentageMaster = (PercentageMaster) o;
        if(percentageMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, percentageMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PercentageMaster{" +
            "id=" + id +
            ", percentType='" + percentType + "'" +
            ", percentValue='" + percentValue + "'" +
            '}';
    }
}

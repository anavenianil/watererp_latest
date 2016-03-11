package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TariffCategoryMaster.
 */
@Entity
@Table(name = "tariff_category_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TariffCategoryMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "tariff_name", nullable = false)
    private String tariffName;
    
    @NotNull
    @Column(name = "tariff_unit", nullable = false)
    private Integer tariffUnit;
    
    @NotNull
    @Column(name = "tariff_value", nullable = false)
    private Float tariffValue;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }
    
    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Integer getTariffUnit() {
        return tariffUnit;
    }
    
    public void setTariffUnit(Integer tariffUnit) {
        this.tariffUnit = tariffUnit;
    }

    public Float getTariffValue() {
        return tariffValue;
    }
    
    public void setTariffValue(Float tariffValue) {
        this.tariffValue = tariffValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TariffCategoryMaster tariffCategoryMaster = (TariffCategoryMaster) o;
        if(tariffCategoryMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tariffCategoryMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TariffCategoryMaster{" +
            "id=" + id +
            ", tariffName='" + tariffName + "'" +
            ", tariffUnit='" + tariffUnit + "'" +
            ", tariffValue='" + tariffValue + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TariffTypeMaster.
 */
@Entity
@Table(name = "tariff_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TariffTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "tariff_type", nullable = false)
    private String tariffType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffType() {
        return tariffType;
    }
    
    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TariffTypeMaster tariffTypeMaster = (TariffTypeMaster) o;
        if(tariffTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tariffTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TariffTypeMaster{" +
            "id=" + id +
            ", tariffType='" + tariffType + "'" +
            '}';
    }
}

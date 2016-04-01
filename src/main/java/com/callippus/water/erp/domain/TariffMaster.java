package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TariffMaster.
 */
@Entity
@Table(name = "tariff_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TariffMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "tariff_name", nullable = false)
    private String tariffName;
    
    @NotNull
    @Column(name = "valid_from", nullable = false)
    private ZonedDateTime validFrom;
    
    @NotNull
    @Column(name = "valid_to", nullable = false)
    private ZonedDateTime validTo;
    
    @NotNull
    @Column(name = "active", nullable = false)
    private String active;
    
    @ManyToOne
    @JoinColumn(name = "tariff_category_master_id")
    private TariffCategoryMaster tariffCategoryMaster;

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

    public ZonedDateTime getValidFrom() {
        return validFrom;
    }
    
    public void setValidFrom(ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public ZonedDateTime getValidTo() {
        return validTo;
    }
    
    public void setValidTo(ZonedDateTime validTo) {
        this.validTo = validTo;
    }

    public String getActive() {
        return active;
    }
    
    public void setActive(String active) {
        this.active = active;
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
        TariffMaster tariffMaster = (TariffMaster) o;
        if(tariffMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, tariffMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TariffMaster{" +
            "id=" + id +
            ", tariffName='" + tariffName + "'" +
            ", validFrom='" + validFrom + "'" +
            ", validTo='" + validTo + "'" +
            ", active='" + active + "'" +
            '}';
    }
}

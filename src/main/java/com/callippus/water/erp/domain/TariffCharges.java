package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TariffCharges.
 */
@Entity
@Table(name = "tariff_charges")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TariffCharges implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "tariff_desc", nullable = false)
    private String tariffDesc;

    @NotNull
    @Column(name = "slab_min", nullable = false)
    private Integer slabMin;

    @NotNull
    @Column(name = "slab_max", nullable = false)
    private Integer slabMax;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Float rate;

    @NotNull
    @Column(name = "min_kl", nullable = false)
    private Float minKL;

    @ManyToOne
    @JoinColumn(name = "tariff_master_id")
    private TariffMaster tariffMaster;

    @ManyToOne
    @JoinColumn(name = "tariff_type_master_id")
    private TariffTypeMaster tariffTypeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffDesc() {
        return tariffDesc;
    }

    public void setTariffDesc(String tariffDesc) {
        this.tariffDesc = tariffDesc;
    }

    public Integer getSlabMin() {
        return slabMin;
    }

    public void setSlabMin(Integer slabMin) {
        this.slabMin = slabMin;
    }

    public Integer getSlabMax() {
        return slabMax;
    }

    public void setSlabMax(Integer slabMax) {
        this.slabMax = slabMax;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Float getMinKL() {
        return minKL;
    }

    public void setMinKL(Float minKL) {
        this.minKL = minKL;
    }

    public TariffMaster getTariffMaster() {
        return tariffMaster;
    }

    public void setTariffMaster(TariffMaster tariffMaster) {
        this.tariffMaster = tariffMaster;
    }

    public TariffTypeMaster getTariffTypeMaster() {
        return tariffTypeMaster;
    }

    public void setTariffTypeMaster(TariffTypeMaster tariffTypeMaster) {
        this.tariffTypeMaster = tariffTypeMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TariffCharges tariffCharges = (TariffCharges) o;
        return Objects.equals(id, tariffCharges.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TariffCharges{" +
            "id=" + id +
            ", tariffDesc='" + tariffDesc + "'" +
            ", slabMin='" + slabMin + "'" +
            ", slabMax='" + slabMax + "'" +
            ", rate='" + rate + "'" +
            ", minKL='" + minKL + "'" +
            '}';
    }
}

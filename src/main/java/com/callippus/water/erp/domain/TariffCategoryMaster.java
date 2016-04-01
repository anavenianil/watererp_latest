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
    @Column(name = "tariff_category", nullable = false)
    private String tariffCategory;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffCategory() {
        return tariffCategory;
    }
    
    public void setTariffCategory(String tariffCategory) {
        this.tariffCategory = tariffCategory;
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
            ", tariffCategory='" + tariffCategory + "'" +
            '}';
    }
}

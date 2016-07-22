package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A RevenueTypeMaster.
 */
@Entity
@Table(name = "revenue_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RevenueTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "revenue_type", nullable = false)
    private String revenueType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRevenueType() {
        return revenueType;
    }
    
    public void setRevenueType(String revenueType) {
        this.revenueType = revenueType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RevenueTypeMaster revenueTypeMaster = (RevenueTypeMaster) o;
        if(revenueTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, revenueTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "RevenueTypeMaster{" +
            "id=" + id +
            ", revenueType='" + revenueType + "'" +
            '}';
    }
}

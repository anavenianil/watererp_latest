package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SchemeMaster.
 */
@Entity
@Table(name = "scheme_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SchemeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "scheme_name", nullable = false)
    private String schemeName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchemeName() {
        return schemeName;
    }
    
    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SchemeMaster schemeMaster = (SchemeMaster) o;
        if(schemeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, schemeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SchemeMaster{" +
            "id=" + id +
            ", schemeName='" + schemeName + "'" +
            '}';
    }
}

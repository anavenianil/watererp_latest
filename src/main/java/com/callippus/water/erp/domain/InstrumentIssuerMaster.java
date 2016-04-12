package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A InstrumentIssuerMaster.
 */
@Entity
@Table(name = "instrument_issuer_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InstrumentIssuerMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "instrument_issuer", nullable = false)
    private String instrumentIssuer;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInstrumentIssuer() {
        return instrumentIssuer;
    }
    
    public void setInstrumentIssuer(String instrumentIssuer) {
        this.instrumentIssuer = instrumentIssuer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InstrumentIssuerMaster instrumentIssuerMaster = (InstrumentIssuerMaster) o;
        if(instrumentIssuerMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, instrumentIssuerMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InstrumentIssuerMaster{" +
            "id=" + id +
            ", instrumentIssuer='" + instrumentIssuer + "'" +
            '}';
    }
}

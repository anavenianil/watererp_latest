package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A IdProofMaster.
 */
@Entity
@Table(name = "id_proof_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class IdProofMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_proof")
    private String idProof;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdProof() {
        return idProof;
    }
    
    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IdProofMaster idProofMaster = (IdProofMaster) o;
        if(idProofMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, idProofMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "IdProofMaster{" +
            "id=" + id +
            ", idProof='" + idProof + "'" +
            '}';
    }
}

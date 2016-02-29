package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TransactionTypeMaster.
 */
@Entity
@Table(name = "transaction_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TransactionTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "type_of_txn", nullable = false)
    private String typeOfTxn;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfTxn() {
        return typeOfTxn;
    }
    
    public void setTypeOfTxn(String typeOfTxn) {
        this.typeOfTxn = typeOfTxn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionTypeMaster transactionTypeMaster = (TransactionTypeMaster) o;
        if(transactionTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, transactionTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TransactionTypeMaster{" +
            "id=" + id +
            ", typeOfTxn='" + typeOfTxn + "'" +
            '}';
    }
}

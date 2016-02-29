package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CashBookMaster.
 */
@Entity
@Table(name = "cash_book_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CashBookMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "cash_book_entry_type", nullable = false)
    private String cashBookEntryType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCashBookEntryType() {
        return cashBookEntryType;
    }
    
    public void setCashBookEntryType(String cashBookEntryType) {
        this.cashBookEntryType = cashBookEntryType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CashBookMaster cashBookMaster = (CashBookMaster) o;
        if(cashBookMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, cashBookMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CashBookMaster{" +
            "id=" + id +
            ", cashBookEntryType='" + cashBookEntryType + "'" +
            '}';
    }
}

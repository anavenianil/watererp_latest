package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A InvoicePayments.
 */
@Entity
@Table(name = "invoice_payments")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvoicePayments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "amount", precision=20, scale=2, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "cust_details_id")
    private CustDetails custDetails;

    @ManyToOne
    @JoinColumn(name = "bill_full_details_id")
    private BillFullDetails billFullDetails;

    @ManyToOne
    @JoinColumn(name = "coll_details_id")
    private CollDetails collDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CustDetails getCustDetails() {
        return custDetails;
    }

    public void setCustDetails(CustDetails custDetails) {
        this.custDetails = custDetails;
    }

    public BillFullDetails getBillFullDetails() {
        return billFullDetails;
    }

    public void setBillFullDetails(BillFullDetails billFullDetails) {
        this.billFullDetails = billFullDetails;
    }

    public CollDetails getCollDetails() {
        return collDetails;
    }

    public void setCollDetails(CollDetails collDetails) {
        this.collDetails = collDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoicePayments invoicePayments = (InvoicePayments) o;
        return Objects.equals(id, invoicePayments.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "InvoicePayments{" +
            "id=" + id +
            ", amount='" + amount + "'" +
            '}';
    }
}

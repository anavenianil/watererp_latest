package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.callippus.water.erp.domain.enumeration.TxnStatus;

/**
 * A Adjustments.
 */
@Entity
@Table(name = "adjustments")
public class Adjustments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "can")
    private String can;

    @Column(name = "amount", precision=20, scale=3)
    private BigDecimal amount;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "txn_time")
    private ZonedDateTime txnTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TxnStatus status;

    @ManyToOne
    @JoinColumn(name = "cust_details_id")
    private CustDetails custDetails;

    @ManyToOne
    @JoinColumn(name = "bill_full_details_id")
    private BillFullDetails billFullDetails;

    @ManyToOne
    @JoinColumn(name = "transaction_type_master_id")
    private TransactionTypeMaster transactionTypeMaster;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "customer_complaints_id")
    private CustomerComplaints customerComplaints;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public ZonedDateTime getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(ZonedDateTime txnTime) {
        this.txnTime = txnTime;
    }

    public TxnStatus getStatus() {
        return status;
    }

    public void setStatus(TxnStatus status) {
        this.status = status;
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

    public TransactionTypeMaster getTransactionTypeMaster() {
        return transactionTypeMaster;
    }

    public void setTransactionTypeMaster(TransactionTypeMaster transactionTypeMaster) {
        this.transactionTypeMaster = transactionTypeMaster;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CustomerComplaints getCustomerComplaints() {
        return customerComplaints;
    }

    public void setCustomerComplaints(CustomerComplaints customerComplaints) {
        this.customerComplaints = customerComplaints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Adjustments adjustments = (Adjustments) o;
        return Objects.equals(id, adjustments.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Adjustments{" +
            "id=" + id +
            ", can='" + can + "'" +
            ", amount='" + amount + "'" +
            ", remarks='" + remarks + "'" +
            ", txnTime='" + txnTime + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

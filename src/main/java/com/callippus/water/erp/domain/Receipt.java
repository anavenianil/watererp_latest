package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Receipt.
 */
@Entity
@Table(name = "receipt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Receipt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Double amount;
    
    @Column(name = "bank_name")
    private String bankName;
    
    @Column(name = "branch_name")
    private String branchName;
    
    @Column(name = "check_or_dd_date")
    private LocalDate checkOrDdDate;
    
    @Column(name = "check_or_dd_no")
    private String checkOrDdNo;
    
    @Column(name = "receipt_date")
    private LocalDate receiptDate;
    
    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    @ManyToOne
    @JoinColumn(name = "payment_types_id")
    private PaymentTypes paymentTypes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBankName() {
        return bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }
    
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public LocalDate getCheckOrDdDate() {
        return checkOrDdDate;
    }
    
    public void setCheckOrDdDate(LocalDate checkOrDdDate) {
        this.checkOrDdDate = checkOrDdDate;
    }

    public String getCheckOrDdNo() {
        return checkOrDdNo;
    }
    
    public void setCheckOrDdNo(String checkOrDdNo) {
        this.checkOrDdNo = checkOrDdNo;
    }

    public LocalDate getReceiptDate() {
        return receiptDate;
    }
    
    public void setReceiptDate(LocalDate receiptDate) {
        this.receiptDate = receiptDate;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Receipt receipt = (Receipt) o;
        if(receipt.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, receipt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Receipt{" +
            "id=" + id +
            ", amount='" + amount + "'" +
            ", bankName='" + bankName + "'" +
            ", branchName='" + branchName + "'" +
            ", checkOrDdDate='" + checkOrDdDate + "'" +
            ", checkOrDdNo='" + checkOrDdNo + "'" +
            ", receiptDate='" + receiptDate + "'" +
            '}';
    }
}

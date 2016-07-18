package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ExpenseDetails.
 */
@Entity
@Table(name = "expense_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ExpenseDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "expense_no")
    private String expenseNo;
    
    @Column(name = "expense_amt", precision=20, scale=3)
    private BigDecimal expenseAmt;
    
    @Column(name = "expense_dt")
    private ZonedDateTime expenseDt;
    
    @Column(name = "instr_no")
    private String instrNo;
    
    @Column(name = "instr_dt")
    private LocalDate instrDt;
    
    @ManyToOne
    @JoinColumn(name = "payment_types_id")
    private PaymentTypes paymentTypes;

    @ManyToOne
    @JoinColumn(name = "bank_master_id")
    private BankMaster bankMaster;

    @ManyToOne
    @JoinColumn(name = "collection_type_master_id")
    private CollectionTypeMaster collectionTypeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseNo() {
        return expenseNo;
    }
    
    public void setExpenseNo(String expenseNo) {
        this.expenseNo = expenseNo;
    }

    public BigDecimal getExpenseAmt() {
        return expenseAmt;
    }
    
    public void setExpenseAmt(BigDecimal expenseAmt) {
        this.expenseAmt = expenseAmt;
    }

    public ZonedDateTime getExpenseDt() {
        return expenseDt;
    }
    
    public void setExpenseDt(ZonedDateTime expenseDt) {
        this.expenseDt = expenseDt;
    }

    public String getInstrNo() {
        return instrNo;
    }
    
    public void setInstrNo(String instrNo) {
        this.instrNo = instrNo;
    }

    public LocalDate getInstrDt() {
        return instrDt;
    }
    
    public void setInstrDt(LocalDate instrDt) {
        this.instrDt = instrDt;
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public BankMaster getBankMaster() {
        return bankMaster;
    }

    public void setBankMaster(BankMaster bankMaster) {
        this.bankMaster = bankMaster;
    }

    public CollectionTypeMaster getCollectionTypeMaster() {
        return collectionTypeMaster;
    }

    public void setCollectionTypeMaster(CollectionTypeMaster collectionTypeMaster) {
        this.collectionTypeMaster = collectionTypeMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpenseDetails expenseDetails = (ExpenseDetails) o;
        if(expenseDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, expenseDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ExpenseDetails{" +
            "id=" + id +
            ", expenseNo='" + expenseNo + "'" +
            ", expenseAmt='" + expenseAmt + "'" +
            ", expenseDt='" + expenseDt + "'" +
            ", instrNo='" + instrNo + "'" +
            ", instrDt='" + instrDt + "'" +
            '}';
    }
}

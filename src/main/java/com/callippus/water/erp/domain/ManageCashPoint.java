package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ManageCashPoint.
 */
@Entity
@Table(name = "manage_cash_point")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ManageCashPoint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "today_date")
    private ZonedDateTime todayDate;
    
    @Column(name = "payee_name")
    private String payeeName;
    
    @Column(name = "txn_amount")
    private Float txnAmount;
    
    @Column(name = "open_bal")
    private Float openBal;
    
    @Column(name = "avail_bal")
    private Float availBal;
    
    @Column(name = "total_receipts")
    private Integer totalReceipts;
    
    @Column(name = "location_code")
    private String locationCode;
    
    @ManyToOne
    @JoinColumn(name = "transaction_type_master_id")
    private TransactionTypeMaster transactionTypeMaster;

    @ManyToOne
    @JoinColumn(name = "cash_book_master_id")
    private CashBookMaster cashBookMaster;

    @ManyToOne
    @JoinColumn(name = "payment_types_id")
    private PaymentTypes paymentTypes;

    @ManyToOne
    @JoinColumn(name = "file_number_id")
    private FileNumber fileNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTodayDate() {
        return todayDate;
    }
    
    public void setTodayDate(ZonedDateTime todayDate) {
        this.todayDate = todayDate;
    }

    public String getPayeeName() {
        return payeeName;
    }
    
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public Float getTxnAmount() {
        return txnAmount;
    }
    
    public void setTxnAmount(Float txnAmount) {
        this.txnAmount = txnAmount;
    }

    public Float getOpenBal() {
        return openBal;
    }
    
    public void setOpenBal(Float openBal) {
        this.openBal = openBal;
    }

    public Float getAvailBal() {
        return availBal;
    }
    
    public void setAvailBal(Float availBal) {
        this.availBal = availBal;
    }

    public Integer getTotalReceipts() {
        return totalReceipts;
    }
    
    public void setTotalReceipts(Integer totalReceipts) {
        this.totalReceipts = totalReceipts;
    }

    public String getLocationCode() {
        return locationCode;
    }
    
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public TransactionTypeMaster getTransactionTypeMaster() {
        return transactionTypeMaster;
    }

    public void setTransactionTypeMaster(TransactionTypeMaster transactionTypeMaster) {
        this.transactionTypeMaster = transactionTypeMaster;
    }

    public CashBookMaster getCashBookMaster() {
        return cashBookMaster;
    }

    public void setCashBookMaster(CashBookMaster cashBookMaster) {
        this.cashBookMaster = cashBookMaster;
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public FileNumber getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(FileNumber fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ManageCashPoint manageCashPoint = (ManageCashPoint) o;
        if(manageCashPoint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, manageCashPoint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ManageCashPoint{" +
            "id=" + id +
            ", todayDate='" + todayDate + "'" +
            ", payeeName='" + payeeName + "'" +
            ", txnAmount='" + txnAmount + "'" +
            ", openBal='" + openBal + "'" +
            ", availBal='" + availBal + "'" +
            ", totalReceipts='" + totalReceipts + "'" +
            ", locationCode='" + locationCode + "'" +
            '}';
    }
}

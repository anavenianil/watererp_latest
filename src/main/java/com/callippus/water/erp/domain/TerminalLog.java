package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TerminalLog.
 */
@Entity
@Table(name = "terminal_log")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TerminalLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "amount")
    private Float amount;
    
    @Column(name = "last_modified")
    private ZonedDateTime lastModified;
    
    @Column(name = "modified_by")
    private String modifiedBy;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "bank_deposit_date")
    private LocalDate bankDepositDate;
    
    @Column(name = "before_update")
    private String beforeUpdate;
    
    @Column(name = "after_update")
    private String afterUpdate;
    
    @Column(name = "mr_code")
    private String mrCode;
    
    @Column(name = "remark")
    private String remark;
    
    @Column(name = "txn_type")
    private String txnType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }
    
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public ZonedDateTime getLastModified() {
        return lastModified;
    }
    
    public void setLastModified(ZonedDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
    
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getBankDepositDate() {
        return bankDepositDate;
    }
    
    public void setBankDepositDate(LocalDate bankDepositDate) {
        this.bankDepositDate = bankDepositDate;
    }

    public String getBeforeUpdate() {
        return beforeUpdate;
    }
    
    public void setBeforeUpdate(String beforeUpdate) {
        this.beforeUpdate = beforeUpdate;
    }

    public String getAfterUpdate() {
        return afterUpdate;
    }
    
    public void setAfterUpdate(String afterUpdate) {
        this.afterUpdate = afterUpdate;
    }

    public String getMrCode() {
        return mrCode;
    }
    
    public void setMrCode(String mrCode) {
        this.mrCode = mrCode;
    }

    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTxnType() {
        return txnType;
    }
    
    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TerminalLog terminalLog = (TerminalLog) o;
        if(terminalLog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, terminalLog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "TerminalLog{" +
            "id=" + id +
            ", amount='" + amount + "'" +
            ", lastModified='" + lastModified + "'" +
            ", modifiedBy='" + modifiedBy + "'" +
            ", userId='" + userId + "'" +
            ", bankDepositDate='" + bankDepositDate + "'" +
            ", beforeUpdate='" + beforeUpdate + "'" +
            ", afterUpdate='" + afterUpdate + "'" +
            ", mrCode='" + mrCode + "'" +
            ", remark='" + remark + "'" +
            ", txnType='" + txnType + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.callippus.water.erp.domain.enumeration.TransactionType;

/**
 * A CollectionTypeMaster.
 */
@Entity
@Table(name = "collection_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CollectionTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "coll_name")
    private String collName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "txn_type", nullable = false)
    private TransactionType txnType;

    @Column(name = "has_account_no")
    private Boolean hasAccountNo;

    @Column(name = "account_code")
    private String accountCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollName() {
        return collName;
    }

    public void setCollName(String collName) {
        this.collName = collName;
    }

    public TransactionType getTxnType() {
        return txnType;
    }

    public void setTxnType(TransactionType txnType) {
        this.txnType = txnType;
    }

    public Boolean getHasAccountNo() {
        return hasAccountNo;
    }

    public void setHasAccountNo(Boolean hasAccountNo) {
        this.hasAccountNo = hasAccountNo;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionTypeMaster collectionTypeMaster = (CollectionTypeMaster) o;
        return Objects.equals(id, collectionTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CollectionTypeMaster{" +
            "id=" + id +
            ", collName='" + collName + "'" +
            ", txnType='" + txnType + "'" +
            ", hasAccountNo='" + hasAccountNo + "'" +
            ", accountCode='" + accountCode + "'" +
            '}';
    }
}

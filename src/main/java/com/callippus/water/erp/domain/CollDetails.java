package com.callippus.water.erp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CollDetails.
 */
@Entity
@Table(name = "coll_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CollDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "reversal_ref")
    private String reversalRef;
    
    @Column(name = "receipt_no")
    private String receiptNo;
    
    @Column(name = "receipt_amt")
    private Float receiptAmt;
    
    @Column(name = "receipt_dt")
    private ZonedDateTime receiptDt;
    
    @Column(name = "receipt_mode")
    private String receiptMode;
    
    @Column(name = "instr_no")
    private String instrNo;
    
    @Column(name = "instr_dt")
    private LocalDate instrDt;
    
    @Column(name = "instr_issuer")
    private String instrIssuer;
    
    @Column(name = "svr_status")
    private String svrStatus;
    
    @Column(name = "can")
    private String can;
    
    @Column(name = "cons_name")
    private String consName;
    
    @Column(name = "terminal_id")
    private String terminalId;
    
    @Column(name = "coll_time")
    private ZonedDateTime collTime;
    
    @Column(name = "txn_status")
    private String txnStatus;
    
    @Column(name = "meter_reader_id")
    private String meterReaderId;
    
    @Column(name = "user_id")
    private String userId;
    
    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "settlement_id")
    private String settlementId;
    
    @Column(name = "ext_settlement_id")
    private String extSettlementId;
    
    @Column(name = "lat")
    private String lat;
    
    @Column(name = "long_i")
    private String longI;
    
    @ManyToOne
    @JoinColumn(name = "payment_types_id")
    private PaymentTypes paymentTypes;

    @ManyToOne
    @JoinColumn(name = "instrument_issuer_master_id")
    private InstrumentIssuerMaster instrumentIssuerMaster;

    @ManyToOne
    @JoinColumn(name = "collection_type_master_id")
    private CollectionTypeMaster collectionTypeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReversalRef() {
        return reversalRef;
    }
    
    public void setReversalRef(String reversalRef) {
        this.reversalRef = reversalRef;
    }

    public String getReceiptNo() {
        return receiptNo;
    }
    
    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public Float getReceiptAmt() {
        return receiptAmt;
    }
    
    public void setReceiptAmt(Float receiptAmt) {
        this.receiptAmt = receiptAmt;
    }

    public ZonedDateTime getReceiptDt() {
        return receiptDt;
    }
    
    public void setReceiptDt(ZonedDateTime receiptDt) {
        this.receiptDt = receiptDt;
    }

    public String getReceiptMode() {
        return receiptMode;
    }
    
    public void setReceiptMode(String receiptMode) {
        this.receiptMode = receiptMode;
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

    public String getInstrIssuer() {
        return instrIssuer;
    }
    
    public void setInstrIssuer(String instrIssuer) {
        this.instrIssuer = instrIssuer;
    }

    public String getSvrStatus() {
        return svrStatus;
    }
    
    public void setSvrStatus(String svrStatus) {
        this.svrStatus = svrStatus;
    }

    public String getCan() {
        return can;
    }
    
    public void setCan(String can) {
        this.can = can;
    }

    public String getConsName() {
        return consName;
    }
    
    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getTerminalId() {
        return terminalId;
    }
    
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public ZonedDateTime getCollTime() {
        return collTime;
    }
    
    public void setCollTime(ZonedDateTime collTime) {
        this.collTime = collTime;
    }

    public String getTxnStatus() {
        return txnStatus;
    }
    
    public void setTxnStatus(String txnStatus) {
        this.txnStatus = txnStatus;
    }

    public String getMeterReaderId() {
        return meterReaderId;
    }
    
    public void setMeterReaderId(String meterReaderId) {
        this.meterReaderId = meterReaderId;
    }

    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSettlementId() {
        return settlementId;
    }
    
    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String getExtSettlementId() {
        return extSettlementId;
    }
    
    public void setExtSettlementId(String extSettlementId) {
        this.extSettlementId = extSettlementId;
    }

    public String getLat() {
        return lat;
    }
    
    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongI() {
        return longI;
    }
    
    public void setLongI(String longI) {
        this.longI = longI;
    }

    public PaymentTypes getPaymentTypes() {
        return paymentTypes;
    }

    public void setPaymentTypes(PaymentTypes paymentTypes) {
        this.paymentTypes = paymentTypes;
    }

    public InstrumentIssuerMaster getInstrumentIssuerMaster() {
        return instrumentIssuerMaster;
    }

    public void setInstrumentIssuerMaster(InstrumentIssuerMaster instrumentIssuerMaster) {
        this.instrumentIssuerMaster = instrumentIssuerMaster;
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
        CollDetails collDetails = (CollDetails) o;
        if(collDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, collDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CollDetails{" +
            "id=" + id +
            ", reversalRef='" + reversalRef + "'" +
            ", receiptNo='" + receiptNo + "'" +
            ", receiptAmt='" + receiptAmt + "'" +
            ", receiptDt='" + receiptDt + "'" +
            ", receiptMode='" + receiptMode + "'" +
            ", instrNo='" + instrNo + "'" +
            ", instrDt='" + instrDt + "'" +
            ", instrIssuer='" + instrIssuer + "'" +
            ", svrStatus='" + svrStatus + "'" +
            ", can='" + can + "'" +
            ", consName='" + consName + "'" +
            ", terminalId='" + terminalId + "'" +
            ", collTime='" + collTime + "'" +
            ", txnStatus='" + txnStatus + "'" +
            ", meterReaderId='" + meterReaderId + "'" +
            ", userId='" + userId + "'" +
            ", remarks='" + remarks + "'" +
            ", settlementId='" + settlementId + "'" +
            ", extSettlementId='" + extSettlementId + "'" +
            ", lat='" + lat + "'" +
            ", longI='" + longI + "'" +
            '}';
    }
}

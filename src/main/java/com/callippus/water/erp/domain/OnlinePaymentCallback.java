package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OnlinePaymentCallback.
 */
@Entity
@Table(name = "online_payment_callback")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OnlinePaymentCallback implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "currency")
    private String currency;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "message")
    private String message;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "total_amount_paid", precision=20, scale=3)
    private BigDecimal totalAmountPaid;

    @Column(name = "user_defined_field")
    private String userDefinedField;

    @Column(name = "merchant_txn_ref")
    private String merchantTxnRef;

    @ManyToOne
    @JoinColumn(name = "merchant_master_id")
    private MerchantMaster merchantMaster;

    @ManyToOne
    @JoinColumn(name = "online_payment_order_id")
    private OnlinePaymentOrder onlinePaymentOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getUserDefinedField() {
        return userDefinedField;
    }

    public void setUserDefinedField(String userDefinedField) {
        this.userDefinedField = userDefinedField;
    }

    public String getMerchantTxnRef() {
        return merchantTxnRef;
    }

    public void setMerchantTxnRef(String merchantTxnRef) {
        this.merchantTxnRef = merchantTxnRef;
    }

    public MerchantMaster getMerchantMaster() {
        return merchantMaster;
    }

    public void setMerchantMaster(MerchantMaster merchantMaster) {
        this.merchantMaster = merchantMaster;
    }

    public OnlinePaymentOrder getOnlinePaymentOrder() {
        return onlinePaymentOrder;
    }

    public void setOnlinePaymentOrder(OnlinePaymentOrder onlinePaymentOrder) {
        this.onlinePaymentOrder = onlinePaymentOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OnlinePaymentCallback onlinePaymentCallback = (OnlinePaymentCallback) o;
        return Objects.equals(id, onlinePaymentCallback.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OnlinePaymentCallback{" +
            "id=" + id +
            ", currency='" + currency + "'" +
            ", paymentMode='" + paymentMode + "'" +
            ", serviceCode='" + serviceCode + "'" +
            ", message='" + message + "'" +
            ", responseCode='" + responseCode + "'" +
            ", totalAmountPaid='" + totalAmountPaid + "'" +
            ", userDefinedField='" + userDefinedField + "'" +
            ", merchantTxnRef='" + merchantTxnRef + "'" +
            '}';
    }
}

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
 * A OnlinePaymentResponse.
 */
@Entity
@Table(name = "online_payment_response")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OnlinePaymentResponse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "response_code")
    private String responseCode;

    @Column(name = "response_time")
    private ZonedDateTime responseTime;

    @Column(name = "redirect_url")
    private String redirectUrl;

    @Column(name = "merchant_txn_ref")
    private String merchantTxnRef;

    @ManyToOne
    @JoinColumn(name = "online_payment_order_id")
    private OnlinePaymentOrder onlinePaymentOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public ZonedDateTime getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(ZonedDateTime responseTime) {
        this.responseTime = responseTime;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getMerchantTxnRef() {
        return merchantTxnRef;
    }

    public void setMerchantTxnRef(String merchantTxnRef) {
        this.merchantTxnRef = merchantTxnRef;
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
        OnlinePaymentResponse onlinePaymentResponse = (OnlinePaymentResponse) o;
        return Objects.equals(id, onlinePaymentResponse.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OnlinePaymentResponse{" +
            "id=" + id +
            ", responseCode='" + responseCode + "'" +
            ", responseTime='" + responseTime + "'" +
            ", redirectUrl='" + redirectUrl + "'" +
            ", merchantTxnRef='" + merchantTxnRef + "'" +
            '}';
    }
}

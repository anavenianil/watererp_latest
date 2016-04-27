package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A OnlinePaymentOrder.
 */
@Entity
@Table(name = "online_payment_order")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OnlinePaymentOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "service_code")
    private String serviceCode;

    @Column(name = "amount")
    private String amount;

    @Column(name = "pay_by")
    private String payBy;

    @Column(name = "user_defined_field")
    private String userDefinedField;

    @ManyToOne
    @JoinColumn(name = "merchant_master_id")
    private MerchantMaster merchantMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPayBy() {
        return payBy;
    }

    public void setPayBy(String payBy) {
        this.payBy = payBy;
    }

    public String getUserDefinedField() {
        return userDefinedField;
    }

    public void setUserDefinedField(String userDefinedField) {
        this.userDefinedField = userDefinedField;
    }

    public MerchantMaster getMerchantMaster() {
        return merchantMaster;
    }

    public void setMerchantMaster(MerchantMaster merchantMaster) {
        this.merchantMaster = merchantMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OnlinePaymentOrder onlinePaymentOrder = (OnlinePaymentOrder) o;
        return Objects.equals(id, onlinePaymentOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OnlinePaymentOrder{" +
            "id=" + id +
            ", serviceCode='" + serviceCode + "'" +
            ", amount='" + amount + "'" +
            ", payBy='" + payBy + "'" +
            ", userDefinedField='" + userDefinedField + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.callippus.water.erp.domain.enumeration.PaymentMode;

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

    @NotNull
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{6,10}$")
    @Column(name = "service_code", nullable = false)
    private String serviceCode;

    @NotNull
    @Min(value = 1)
    @Max(value = 10000000)
    @Column(name = "amount", nullable = false)
    private Float amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pay_by", nullable = false)
    private PaymentMode payBy;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]{6,10}$")
    @Column(name = "user_defined_field", nullable = false)
    private String userDefinedField;

    @NotNull
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Min(value = 99999999)
    @Max(value = 9999999999L)
    @Column(name = "phone", nullable = false)
    private Long phone;

    @Column(name = "order_time")
    private ZonedDateTime orderTime;

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public PaymentMode getPayBy() {
        return payBy;
    }

    public void setPayBy(PaymentMode payBy) {
        this.payBy = payBy;
    }

    public String getUserDefinedField() {
        return userDefinedField;
    }

    public void setUserDefinedField(String userDefinedField) {
        this.userDefinedField = userDefinedField;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public ZonedDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(ZonedDateTime orderTime) {
        this.orderTime = orderTime;
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
            ", email='" + email + "'" +
            ", phone='" + phone + "'" +
            ", orderTime='" + orderTime + "'" +
            '}';
    }
}

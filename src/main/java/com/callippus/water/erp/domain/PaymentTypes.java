package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PaymentTypes.
 */
@Entity
@Table(name = "payment_types")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PaymentTypes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "payment_mode", nullable = false)
    private String paymentMode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }
    
    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentTypes paymentTypes = (PaymentTypes) o;
        if(paymentTypes.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, paymentTypes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaymentTypes{" +
            "id=" + id +
            ", paymentMode='" + paymentMode + "'" +
            '}';
    }
}

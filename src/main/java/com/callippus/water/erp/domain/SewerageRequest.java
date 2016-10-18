package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A SewerageRequest.
 */
@Entity
@Table(name = "sewerage_request")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SewerageRequest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "mobile_no")
    private String mobileNo;
    
    @Column(name = "requested_date")
    private LocalDate requestedDate;
    
    @Column(name = "receipt_no")
    private Long receiptNo;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "amount", precision=20, scale=3)
    private BigDecimal amount;
    
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    
    @Column(name = "vehicle_no")
    private String vehicleNo;
    
    @Column(name = "driver")
    private String driver;
    
    @Column(name = "completion_date")
    private LocalDate completionDate;
    
    @ManyToOne
    @JoinColumn(name = "tariff_category_master_id")
    private TariffCategoryMaster tariffCategoryMaster;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobileNo() {
        return mobileNo;
    }
    
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }
    
    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public Long getReceiptNo() {
        return receiptNo;
    }
    
    public void setReceiptNo(Long receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }
    
    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getDriver() {
        return driver;
    }
    
    public void setDriver(String driver) {
        this.driver = driver;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }
    
    public void setCompletionDate(LocalDate completionDate) {
        this.completionDate = completionDate;
    }

    public TariffCategoryMaster getTariffCategoryMaster() {
        return tariffCategoryMaster;
    }

    public void setTariffCategoryMaster(TariffCategoryMaster tariffCategoryMaster) {
        this.tariffCategoryMaster = tariffCategoryMaster;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SewerageRequest sewerageRequest = (SewerageRequest) o;
        if(sewerageRequest.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, sewerageRequest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SewerageRequest{" +
            "id=" + id +
            ", customerName='" + customerName + "'" +
            ", mobileNo='" + mobileNo + "'" +
            ", requestedDate='" + requestedDate + "'" +
            ", receiptNo='" + receiptNo + "'" +
            ", address='" + address + "'" +
            ", amount='" + amount + "'" +
            ", paymentDate='" + paymentDate + "'" +
            ", vehicleNo='" + vehicleNo + "'" +
            ", driver='" + driver + "'" +
            ", completionDate='" + completionDate + "'" +
            '}';
    }
}

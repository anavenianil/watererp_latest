package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Proceedings.
 */
@Entity
@Table(name = "proceedings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Proceedings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "water_supply_connection_charges", nullable = false)
    private Float waterSupplyConnectionCharges;
    
    @Column(name = "water_other_charges")
    private Float waterOtherCharges;
    
    @NotNull
    @Column(name = "sixty_days_consumption_charges", nullable = false)
    private Float sixtyDaysConsumptionCharges;
    
    @Column(name = "water_supply_improvement_charges")
    private Float waterSupplyImprovementCharges;
    
    @Column(name = "meter_cost")
    private Float meterCost;
    
    @Column(name = "green_brigade_charges")
    private Float greenBrigadeCharges;
    
    @Column(name = "rwhs_charges")
    private Float rwhsCharges;
    
    @Column(name = "total_water_charges")
    private Float totalWaterCharges;
    
    @Column(name = "sewerage_connection_charges")
    private Float sewerageConnectionCharges;
    
    @Column(name = "sewerage_other_charges")
    private Float sewerageOtherCharges;
    
    @Column(name = "sewerge_improvement_charges")
    private Float sewergeImprovementCharges;
    
    @Column(name = "total_sewerage_charges")
    private Float totalSewerageCharges;
    
    @Column(name = "total_amount")
    private Float totalAmount;
    
    @Column(name = "total_deduction")
    private Float totalDeduction;
    
    @Column(name = "balance")
    private Float balance;
    
    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getWaterSupplyConnectionCharges() {
        return waterSupplyConnectionCharges;
    }
    
    public void setWaterSupplyConnectionCharges(Float waterSupplyConnectionCharges) {
        this.waterSupplyConnectionCharges = waterSupplyConnectionCharges;
    }

    public Float getWaterOtherCharges() {
        return waterOtherCharges;
    }
    
    public void setWaterOtherCharges(Float waterOtherCharges) {
        this.waterOtherCharges = waterOtherCharges;
    }

    public Float getSixtyDaysConsumptionCharges() {
        return sixtyDaysConsumptionCharges;
    }
    
    public void setSixtyDaysConsumptionCharges(Float sixtyDaysConsumptionCharges) {
        this.sixtyDaysConsumptionCharges = sixtyDaysConsumptionCharges;
    }

    public Float getWaterSupplyImprovementCharges() {
        return waterSupplyImprovementCharges;
    }
    
    public void setWaterSupplyImprovementCharges(Float waterSupplyImprovementCharges) {
        this.waterSupplyImprovementCharges = waterSupplyImprovementCharges;
    }

    public Float getMeterCost() {
        return meterCost;
    }
    
    public void setMeterCost(Float meterCost) {
        this.meterCost = meterCost;
    }

    public Float getGreenBrigadeCharges() {
        return greenBrigadeCharges;
    }
    
    public void setGreenBrigadeCharges(Float greenBrigadeCharges) {
        this.greenBrigadeCharges = greenBrigadeCharges;
    }

    public Float getRwhsCharges() {
        return rwhsCharges;
    }
    
    public void setRwhsCharges(Float rwhsCharges) {
        this.rwhsCharges = rwhsCharges;
    }

    public Float getTotalWaterCharges() {
        return totalWaterCharges;
    }
    
    public void setTotalWaterCharges(Float totalWaterCharges) {
        this.totalWaterCharges = totalWaterCharges;
    }

    public Float getSewerageConnectionCharges() {
        return sewerageConnectionCharges;
    }
    
    public void setSewerageConnectionCharges(Float sewerageConnectionCharges) {
        this.sewerageConnectionCharges = sewerageConnectionCharges;
    }

    public Float getSewerageOtherCharges() {
        return sewerageOtherCharges;
    }
    
    public void setSewerageOtherCharges(Float sewerageOtherCharges) {
        this.sewerageOtherCharges = sewerageOtherCharges;
    }

    public Float getSewergeImprovementCharges() {
        return sewergeImprovementCharges;
    }
    
    public void setSewergeImprovementCharges(Float sewergeImprovementCharges) {
        this.sewergeImprovementCharges = sewergeImprovementCharges;
    }

    public Float getTotalSewerageCharges() {
        return totalSewerageCharges;
    }
    
    public void setTotalSewerageCharges(Float totalSewerageCharges) {
        this.totalSewerageCharges = totalSewerageCharges;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Float getTotalDeduction() {
        return totalDeduction;
    }
    
    public void setTotalDeduction(Float totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public Float getBalance() {
        return balance;
    }
    
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Proceedings proceedings = (Proceedings) o;
        if(proceedings.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, proceedings.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Proceedings{" +
            "id=" + id +
            ", waterSupplyConnectionCharges='" + waterSupplyConnectionCharges + "'" +
            ", waterOtherCharges='" + waterOtherCharges + "'" +
            ", sixtyDaysConsumptionCharges='" + sixtyDaysConsumptionCharges + "'" +
            ", waterSupplyImprovementCharges='" + waterSupplyImprovementCharges + "'" +
            ", meterCost='" + meterCost + "'" +
            ", greenBrigadeCharges='" + greenBrigadeCharges + "'" +
            ", rwhsCharges='" + rwhsCharges + "'" +
            ", totalWaterCharges='" + totalWaterCharges + "'" +
            ", sewerageConnectionCharges='" + sewerageConnectionCharges + "'" +
            ", sewerageOtherCharges='" + sewerageOtherCharges + "'" +
            ", sewergeImprovementCharges='" + sewergeImprovementCharges + "'" +
            ", totalSewerageCharges='" + totalSewerageCharges + "'" +
            ", totalAmount='" + totalAmount + "'" +
            ", totalDeduction='" + totalDeduction + "'" +
            ", balance='" + balance + "'" +
            '}';
    }
}

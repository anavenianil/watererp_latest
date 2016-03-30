package com.callippus.water.erp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "sub_total_a")
    private Double subTotalA;
    
    @Column(name = "supervision_charge")
    private Double supervisionCharge;
    
    @Column(name = "labour_charge")
    private Double labourCharge;
    
    @Column(name = "site_survey")
    private Double siteSurvey;
    
    @Column(name = "sub_total_b")
    private Double subTotalB;
    
    @Column(name = "connection_fee")
    private Double connectionFee;
    
    @Column(name = "water_meter_shs")
    private Double waterMeterShs;
    
    @Column(name = "application_form_fee")
    private Double applicationFormFee;
    
    @Column(name = "grand_total")
    private Double grandTotal;
    
    @ManyToOne
    @JoinColumn(name = "supervision_percent_id")
    private PercentageMaster supervisionPercent;

    @ManyToOne
    @JoinColumn(name = "labour_charge_percent_id")
    private PercentageMaster labourChargePercent;

    @ManyToOne
    @JoinColumn(name = "site_survey_percent_id")
    private PercentageMaster siteSurveyPercent;

    @ManyToOne
    @JoinColumn(name = "connection_fee_percent_id")
    private PercentageMaster connectionFeePercent;

    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    //@OneToMany(mappedBy = "proceedings", cascade = CascadeType.ALL)
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="proceedings_id", referencedColumnName="id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<ItemRequired> itemRequireds = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSubTotalA() {
        return subTotalA;
    }
    
    public void setSubTotalA(Double subTotalA) {
        this.subTotalA = subTotalA;
    }

    public Double getSupervisionCharge() {
        return supervisionCharge;
    }
    
    public void setSupervisionCharge(Double supervisionCharge) {
        this.supervisionCharge = supervisionCharge;
    }

    public Double getLabourCharge() {
        return labourCharge;
    }
    
    public void setLabourCharge(Double labourCharge) {
        this.labourCharge = labourCharge;
    }

    public Double getSiteSurvey() {
        return siteSurvey;
    }
    
    public void setSiteSurvey(Double siteSurvey) {
        this.siteSurvey = siteSurvey;
    }

    public Double getSubTotalB() {
        return subTotalB;
    }
    
    public void setSubTotalB(Double subTotalB) {
        this.subTotalB = subTotalB;
    }

    public Double getConnectionFee() {
        return connectionFee;
    }
    
    public void setConnectionFee(Double connectionFee) {
        this.connectionFee = connectionFee;
    }

    public Double getWaterMeterShs() {
        return waterMeterShs;
    }
    
    public void setWaterMeterShs(Double waterMeterShs) {
        this.waterMeterShs = waterMeterShs;
    }

    public Double getApplicationFormFee() {
        return applicationFormFee;
    }
    
    public void setApplicationFormFee(Double applicationFormFee) {
        this.applicationFormFee = applicationFormFee;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }
    
    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public PercentageMaster getSupervisionPercent() {
        return supervisionPercent;
    }

    public void setSupervisionPercent(PercentageMaster percentageMaster) {
        this.supervisionPercent = percentageMaster;
    }

    public PercentageMaster getLabourChargePercent() {
        return labourChargePercent;
    }

    public void setLabourChargePercent(PercentageMaster percentageMaster) {
        this.labourChargePercent = percentageMaster;
    }

    public PercentageMaster getSiteSurveyPercent() {
        return siteSurveyPercent;
    }

    public void setSiteSurveyPercent(PercentageMaster percentageMaster) {
        this.siteSurveyPercent = percentageMaster;
    }

    public PercentageMaster getConnectionFeePercent() {
        return connectionFeePercent;
    }

    public void setConnectionFeePercent(PercentageMaster percentageMaster) {
        this.connectionFeePercent = percentageMaster;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    public List<ItemRequired> getItemRequireds() {
        return itemRequireds;
    }

    public void setItemRequireds(List<ItemRequired> itemRequireds) {
        this.itemRequireds = itemRequireds;
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
            ", subTotalA='" + subTotalA + "'" +
            ", supervisionCharge='" + supervisionCharge + "'" +
            ", labourCharge='" + labourCharge + "'" +
            ", siteSurvey='" + siteSurvey + "'" +
            ", subTotalB='" + subTotalB + "'" +
            ", connectionFee='" + connectionFee + "'" +
            ", waterMeterShs='" + waterMeterShs + "'" +
            ", applicationFormFee='" + applicationFormFee + "'" +
            ", grandTotal='" + grandTotal + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "sub_total_a", precision=20, scale=3)
    private BigDecimal subTotalA;
    
    @Column(name = "supervision_charge", precision=20, scale=3)
    private BigDecimal supervisionCharge;
    
    @Column(name = "labour_charge", precision=20, scale=3)
    private BigDecimal labourCharge;
    
    @Column(name = "site_survey", precision=20, scale=3)
    private BigDecimal siteSurvey;
    
    @Column(name = "sub_total_b", precision=20, scale=3)
    private BigDecimal subTotalB;
    
    @Column(name = "connection_fee", precision=20, scale=3)
    private BigDecimal connectionFee;
    
    @Column(name = "water_meter_shs", precision=20, scale=3)
    private BigDecimal waterMeterShs;
    
    @Column(name = "application_form_fee", precision=20, scale=3)
    private BigDecimal applicationFormFee;
    
    @Column(name = "grand_total", precision=20, scale=3)
    private BigDecimal grandTotal;
    
    @Column(name = "supervision_percent", precision=20, scale=3)
    private BigDecimal supervisionPercent;
    
    @Column(name = "labour_charge_percent", precision=20, scale=3)
    private BigDecimal labourChargePercent;
    
    @Column(name = "site_survey_percent", precision=20, scale=3)
    private BigDecimal siteSurveyPercent;
    
    @Column(name = "connection_fee_percent", precision=20, scale=3)
    private BigDecimal ConnectionFeePercent;
    
    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    /*@OneToMany(mappedBy = "proceedings")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ItemRequired> itemRequireds = new HashSet<>();*/
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="proceedings_id", referencedColumnName="id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<ItemRequired> itemRequireds = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "pipe_size_master_id")
    private PipeSizeMaster pipeSizeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getSubTotalA() {
        return subTotalA;
    }
    
    public void setSubTotalA(BigDecimal subTotalA) {
        this.subTotalA = subTotalA;
    }

    public BigDecimal getSupervisionCharge() {
        return supervisionCharge;
    }
    
    public void setSupervisionCharge(BigDecimal supervisionCharge) {
        this.supervisionCharge = supervisionCharge;
    }

    public BigDecimal getLabourCharge() {
        return labourCharge;
    }
    
    public void setLabourCharge(BigDecimal labourCharge) {
        this.labourCharge = labourCharge;
    }

    public BigDecimal getSiteSurvey() {
        return siteSurvey;
    }
    
    public void setSiteSurvey(BigDecimal siteSurvey) {
        this.siteSurvey = siteSurvey;
    }

    public BigDecimal getSubTotalB() {
        return subTotalB;
    }
    
    public void setSubTotalB(BigDecimal subTotalB) {
        this.subTotalB = subTotalB;
    }

    public BigDecimal getConnectionFee() {
        return connectionFee;
    }
    
    public void setConnectionFee(BigDecimal connectionFee) {
        this.connectionFee = connectionFee;
    }

    public BigDecimal getWaterMeterShs() {
        return waterMeterShs;
    }
    
    public void setWaterMeterShs(BigDecimal waterMeterShs) {
        this.waterMeterShs = waterMeterShs;
    }

    public BigDecimal getApplicationFormFee() {
        return applicationFormFee;
    }
    
    public void setApplicationFormFee(BigDecimal applicationFormFee) {
        this.applicationFormFee = applicationFormFee;
    }

    public BigDecimal getGrandTotal() {
        return grandTotal;
    }
    
    public void setGrandTotal(BigDecimal grandTotal) {
        this.grandTotal = grandTotal;
    }

    public BigDecimal getSupervisionPercent() {
        return supervisionPercent;
    }
    
    public void setSupervisionPercent(BigDecimal supervisionPercent) {
        this.supervisionPercent = supervisionPercent;
    }

    public BigDecimal getLabourChargePercent() {
        return labourChargePercent;
    }
    
    public void setLabourChargePercent(BigDecimal labourChargePercent) {
        this.labourChargePercent = labourChargePercent;
    }

    public BigDecimal getSiteSurveyPercent() {
        return siteSurveyPercent;
    }
    
    public void setSiteSurveyPercent(BigDecimal siteSurveyPercent) {
        this.siteSurveyPercent = siteSurveyPercent;
    }

    public BigDecimal getConnectionFeePercent() {
        return ConnectionFeePercent;
    }
    
    public void setConnectionFeePercent(BigDecimal ConnectionFeePercent) {
        this.ConnectionFeePercent = ConnectionFeePercent;
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

	public PipeSizeMaster getPipeSizeMaster() {
        return pipeSizeMaster;
    }

    public void setPipeSizeMaster(PipeSizeMaster pipeSizeMaster) {
        this.pipeSizeMaster = pipeSizeMaster;
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
            ", supervisionPercent='" + supervisionPercent + "'" +
            ", labourChargePercent='" + labourChargePercent + "'" +
            ", siteSurveyPercent='" + siteSurveyPercent + "'" +
            ", ConnectionFeePercent='" + ConnectionFeePercent + "'" +
            '}';
    }
}

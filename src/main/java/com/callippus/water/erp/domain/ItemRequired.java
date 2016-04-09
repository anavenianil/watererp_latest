package com.callippus.water.erp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * A ItemRequired.
 */
@Entity
@Table(name = "item_required")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemRequired implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "provided")
    private Integer provided;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "rate_per_shs", precision=10, scale=2)
    private BigDecimal ratePerShs;
    
    @Column(name = "amount", precision=10, scale=2)
    private BigDecimal amount;
    
    @ManyToOne
    @JoinColumn(name = "material_master_id")
    private MaterialMaster materialMaster;

    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    @ManyToOne
    @JoinColumn(name = "feasibility_study_id")
    private FeasibilityStudy feasibilityStudy;

    @ManyToOne
    @JoinColumn(name = "proceedings_id")
    private Proceedings proceedings;
    
    @ManyToOne
    @JoinColumn(name = "uom_id")
    private Uom uom;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProvided() {
        return provided;
    }
    
    public void setProvided(Integer provided) {
        this.provided = provided;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getRatePerShs() {
        return ratePerShs;
    }
    
    public void setRatePerShs(BigDecimal ratePerShs) {
        this.ratePerShs = ratePerShs;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public MaterialMaster getMaterialMaster() {
        return materialMaster;
    }

    public void setMaterialMaster(MaterialMaster materialMaster) {
        this.materialMaster = materialMaster;
    }

    public ApplicationTxn getApplicationTxn() {
        return applicationTxn;
    }

    public void setApplicationTxn(ApplicationTxn applicationTxn) {
        this.applicationTxn = applicationTxn;
    }

    public FeasibilityStudy getFeasibilityStudy() {
        return feasibilityStudy;
    }

    public void setFeasibilityStudy(FeasibilityStudy feasibilityStudy) {
        this.feasibilityStudy = feasibilityStudy;
    }

    public Proceedings getProceedings() {
        return proceedings;
    }

    public void setProceedings(Proceedings proceedings) {
        this.proceedings = proceedings;
    }

    public Uom getUom() {
		return uom;
	}

	public void setUom(Uom uom) {
		this.uom = uom;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemRequired itemRequired = (ItemRequired) o;
        if(itemRequired.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, itemRequired.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemRequired{" +
            "id=" + id +
            ", provided='" + provided + "'" +
            ", quantity='" + quantity + "'" +
            ", ratePerShs='" + ratePerShs + "'" +
            ", amount='" + amount + "'" +
            '}';
    }
}

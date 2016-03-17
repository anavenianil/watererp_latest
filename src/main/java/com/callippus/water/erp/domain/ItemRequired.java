package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

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

    @Column(name = "quantity")
    private Integer quantity;
    
    @ManyToOne
    @JoinColumn(name = "item_details_id")
    private ItemDetails itemDetails;

    @ManyToOne
    @JoinColumn(name = "feasibility_study_id")
    private FeasibilityStudy feasibilityStudy;

    @ManyToOne
    @JoinColumn(name = "application_txn_id")
    private ApplicationTxn applicationTxn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public FeasibilityStudy getFeasibilityStudy() {
        return feasibilityStudy;
    }

    public void setFeasibilityStudy(FeasibilityStudy feasibilityStudy) {
        this.feasibilityStudy = feasibilityStudy;
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
            ", quantity='" + quantity + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ItemDetails.
 */
@Entity
@Table(name = "item_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_code")
    private String itemCode;
    
    @Column(name = "item_name")
    private String itemName;
    
    @Column(name = "item_description")
    private String itemDescription;
    
    @Column(name = "size")
    private String size;
    
    @Column(name = "item_quantity")
    private Integer itemQuantity;
    
    @Column(name = "unit_price")
    private Float unitPrice;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }
    
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }
    
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }
    
    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemDetails itemDetails = (ItemDetails) o;
        if(itemDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, itemDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemDetails{" +
            "id=" + id +
            ", itemCode='" + itemCode + "'" +
            ", itemName='" + itemName + "'" +
            ", itemDescription='" + itemDescription + "'" +
            ", size='" + size + "'" +
            ", itemQuantity='" + itemQuantity + "'" +
            ", unitPrice='" + unitPrice + "'" +
            '}';
    }
}

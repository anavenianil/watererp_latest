package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ItemCodeMaster.
 */
@Entity
@Table(name = "item_code_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemCodeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_code")
    private String itemCode;
    
    @Column(name = "item_name")
    private String itemName;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @ManyToOne
    @JoinColumn(name = "item_category_master_id")
    private ItemCategoryMaster itemCategoryMaster;

    @ManyToOne
    @JoinColumn(name = "item_sub_category_master_id")
    private ItemSubCategoryMaster itemSubCategoryMaster;

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

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ItemCategoryMaster getItemCategoryMaster() {
        return itemCategoryMaster;
    }

    public void setItemCategoryMaster(ItemCategoryMaster itemCategoryMaster) {
        this.itemCategoryMaster = itemCategoryMaster;
    }

    public ItemSubCategoryMaster getItemSubCategoryMaster() {
        return itemSubCategoryMaster;
    }

    public void setItemSubCategoryMaster(ItemSubCategoryMaster itemSubCategoryMaster) {
        this.itemSubCategoryMaster = itemSubCategoryMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemCodeMaster itemCodeMaster = (ItemCodeMaster) o;
        if(itemCodeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, itemCodeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemCodeMaster{" +
            "id=" + id +
            ", itemCode='" + itemCode + "'" +
            ", itemName='" + itemName + "'" +
            ", description='" + description + "'" +
            ", status='" + status + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            '}';
    }
}

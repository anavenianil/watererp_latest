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
 * A ItemSubCategoryMaster.
 */
@Entity
@Table(name = "item_sub_category_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemSubCategoryMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_sub_category_code")
    private String itemSubCategoryCode;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "category_code")
    private String categoryCode;
    
    @ManyToOne
    @JoinColumn(name = "item_category_master_id")
    private ItemCategoryMaster itemCategoryMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemSubCategoryCode() {
        return itemSubCategoryCode;
    }
    
    public void setItemSubCategoryCode(String itemSubCategoryCode) {
        this.itemSubCategoryCode = itemSubCategoryCode;
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

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryCode() {
        return categoryCode;
    }
    
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public ItemCategoryMaster getItemCategoryMaster() {
        return itemCategoryMaster;
    }

    public void setItemCategoryMaster(ItemCategoryMaster itemCategoryMaster) {
        this.itemCategoryMaster = itemCategoryMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemSubCategoryMaster itemSubCategoryMaster = (ItemSubCategoryMaster) o;
        if(itemSubCategoryMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, itemSubCategoryMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemSubCategoryMaster{" +
            "id=" + id +
            ", itemSubCategoryCode='" + itemSubCategoryCode + "'" +
            ", description='" + description + "'" +
            ", status='" + status + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", name='" + name + "'" +
            ", categoryCode='" + categoryCode + "'" +
            '}';
    }
}

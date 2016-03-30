package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ItemSubCodeMaster.
 */
@Entity
@Table(name = "item_sub_code_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ItemSubCodeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_code_id")
    private Long itemCodeId;
    
    @Column(name = "item_sub_code")
    private String itemSubCode;
    
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
    
    @Column(name = "item_ccode_id")
    private Long itemCcodeId;
    
    @NotNull
    @Column(name = "item_category_id", nullable = false)
    private Long itemCategoryId;
    
    @Column(name = "item_sub_category_id")
    private Long itemSubCategoryID;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemCodeId() {
        return itemCodeId;
    }
    
    public void setItemCodeId(Long itemCodeId) {
        this.itemCodeId = itemCodeId;
    }

    public String getItemSubCode() {
        return itemSubCode;
    }
    
    public void setItemSubCode(String itemSubCode) {
        this.itemSubCode = itemSubCode;
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

    public Long getItemCcodeId() {
        return itemCcodeId;
    }
    
    public void setItemCcodeId(Long itemCcodeId) {
        this.itemCcodeId = itemCcodeId;
    }

    public Long getItemCategoryId() {
        return itemCategoryId;
    }
    
    public void setItemCategoryId(Long itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Long getItemSubCategoryID() {
        return itemSubCategoryID;
    }
    
    public void setItemSubCategoryID(Long itemSubCategoryID) {
        this.itemSubCategoryID = itemSubCategoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemSubCodeMaster itemSubCodeMaster = (ItemSubCodeMaster) o;
        if(itemSubCodeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, itemSubCodeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ItemSubCodeMaster{" +
            "id=" + id +
            ", itemCodeId='" + itemCodeId + "'" +
            ", itemSubCode='" + itemSubCode + "'" +
            ", itemName='" + itemName + "'" +
            ", description='" + description + "'" +
            ", status='" + status + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", itemCcodeId='" + itemCcodeId + "'" +
            ", itemCategoryId='" + itemCategoryId + "'" +
            ", itemSubCategoryID='" + itemSubCategoryID + "'" +
            '}';
    }
}

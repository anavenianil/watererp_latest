package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A MaterialMaster.
 */
@Entity
@Table(name = "material_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MaterialMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "material_name")
    private String materialName;
    
    @Column(name = "consumable_flag")
    private String consumableFlag;
    
    @Column(name = "uom_id")
    private String uomId;
    
    @Column(name = "category_id")
    private Long categoryId;
    
    @Column(name = "sub_category_id")
    private Long subCategoryId;
    
    @Column(name = "item_code_id")
    private Long itemCodeId;
    
    @Column(name = "item_sub_code_id")
    private Long itemSubCodeId;
    
    @Column(name = "rate_contract_flag")
    private String rateContractFlag;
    
    @Column(name = "unit_rate", precision=20, scale=3)
    private BigDecimal unitRate;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "status")
    private Integer status;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "company_code_id", precision=20, scale=3)
    private BigDecimal companyCodeId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }
    
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getConsumableFlag() {
        return consumableFlag;
    }
    
    public void setConsumableFlag(String consumableFlag) {
        this.consumableFlag = consumableFlag;
    }

    public String getUomId() {
        return uomId;
    }
    
    public void setUomId(String uomId) {
        this.uomId = uomId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }
    
    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public Long getItemCodeId() {
        return itemCodeId;
    }
    
    public void setItemCodeId(Long itemCodeId) {
        this.itemCodeId = itemCodeId;
    }

    public Long getItemSubCodeId() {
        return itemSubCodeId;
    }
    
    public void setItemSubCodeId(Long itemSubCodeId) {
        this.itemSubCodeId = itemSubCodeId;
    }

    public String getRateContractFlag() {
        return rateContractFlag;
    }
    
    public void setRateContractFlag(String rateContractFlag) {
        this.rateContractFlag = rateContractFlag;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }
    
    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
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

    public BigDecimal getCompanyCodeId() {
        return companyCodeId;
    }
    
    public void setCompanyCodeId(BigDecimal companyCodeId) {
        this.companyCodeId = companyCodeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MaterialMaster materialMaster = (MaterialMaster) o;
        if(materialMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, materialMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MaterialMaster{" +
            "id=" + id +
            ", materialName='" + materialName + "'" +
            ", consumableFlag='" + consumableFlag + "'" +
            ", uomId='" + uomId + "'" +
            ", categoryId='" + categoryId + "'" +
            ", subCategoryId='" + subCategoryId + "'" +
            ", itemCodeId='" + itemCodeId + "'" +
            ", itemSubCodeId='" + itemSubCodeId + "'" +
            ", rateContractFlag='" + rateContractFlag + "'" +
            ", unitRate='" + unitRate + "'" +
            ", description='" + description + "'" +
            ", status='" + status + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", companyCodeId='" + companyCodeId + "'" +
            '}';
    }
}

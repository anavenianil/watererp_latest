package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A JobCardItemRequirement.
 */
@Entity
@Table(name = "job_card_item_requirement")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobCardItemRequirement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "replace_length")
    private String replaceLength;
    
    @Column(name = "cascade_clamp")
    private String cascadeClamp;
    
    @Column(name = "no_of_section")
    private Integer noOfSection;
    
    @Column(name = "no_of_clamps")
    private Integer noOfClamps;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "domain_object")
    private Long domainObject;
    
    @ManyToOne
    @JoinColumn(name = "material_master_id")
    private MaterialMaster materialMaster;

    @ManyToOne
    @JoinColumn(name = "uom_id")
    private Uom uom;

    @ManyToOne
    @JoinColumn(name = "water_leakage_complaint_id")
    private WaterLeakageComplaint waterLeakageComplaint;

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

    public String getReplaceLength() {
        return replaceLength;
    }
    
    public void setReplaceLength(String replaceLength) {
        this.replaceLength = replaceLength;
    }

    public String getCascadeClamp() {
        return cascadeClamp;
    }
    
    public void setCascadeClamp(String cascadeClamp) {
        this.cascadeClamp = cascadeClamp;
    }

    public Integer getNoOfSection() {
        return noOfSection;
    }
    
    public void setNoOfSection(Integer noOfSection) {
        this.noOfSection = noOfSection;
    }

    public Integer getNoOfClamps() {
        return noOfClamps;
    }
    
    public void setNoOfClamps(Integer noOfClamps) {
        this.noOfClamps = noOfClamps;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Long getDomainObject() {
        return domainObject;
    }
    
    public void setDomainObject(Long domainObject) {
        this.domainObject = domainObject;
    }

    public MaterialMaster getMaterialMaster() {
        return materialMaster;
    }

    public void setMaterialMaster(MaterialMaster materialMaster) {
        this.materialMaster = materialMaster;
    }

    public Uom getUom() {
        return uom;
    }

    public void setUom(Uom uom) {
        this.uom = uom;
    }

    public WaterLeakageComplaint getWaterLeakageComplaint() {
        return waterLeakageComplaint;
    }

    public void setWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint) {
        this.waterLeakageComplaint = waterLeakageComplaint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JobCardItemRequirement jobCardItemRequirement = (JobCardItemRequirement) o;
        if(jobCardItemRequirement.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, jobCardItemRequirement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobCardItemRequirement{" +
            "id=" + id +
            ", quantity='" + quantity + "'" +
            ", replaceLength='" + replaceLength + "'" +
            ", cascadeClamp='" + cascadeClamp + "'" +
            ", noOfSection='" + noOfSection + "'" +
            ", noOfClamps='" + noOfClamps + "'" +
            ", type='" + type + "'" +
            ", domainObject='" + domainObject + "'" +
            '}';
    }
}

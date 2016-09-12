package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A JobCardSiteStatus.
 */
@Entity
@Table(name = "job_card_site_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobCardSiteStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "tar_patching")
    private String tarPatching;
    
    @Column(name = "tar_patching_length")
    private String tarPatchingLength;
    
    @Column(name = "tar_patching_breadth")
    private String tarPatchingBreadth;
    
    @Column(name = "clean_site")
    private String cleanSite;
    
    @Column(name = "back_fill")
    private String backFill;
    
    @Column(name = "back_fill_length")
    private String backFillLength;
    
    @Column(name = "back_fill_breadth")
    private String backFillBreadth;
    
    @Column(name = "brick_layer")
    private String brickLayer;
    
    @Column(name = "paving")
    private String paving;
    
    @Column(name = "paving_length")
    private String pavingLength;
    
    @NotNull
    @Column(name = "paving_breadth", nullable = false)
    private String pavingBreadth;
    
    @Column(name = "unable_to_locate")
    private String unableToLocate;
    
    @ManyToOne
    @JoinColumn(name = "water_leakage_complaint_id")
    private WaterLeakageComplaint waterLeakageComplaint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTarPatching() {
        return tarPatching;
    }
    
    public void setTarPatching(String tarPatching) {
        this.tarPatching = tarPatching;
    }

    public String getTarPatchingLength() {
        return tarPatchingLength;
    }
    
    public void setTarPatchingLength(String tarPatchingLength) {
        this.tarPatchingLength = tarPatchingLength;
    }

    public String getTarPatchingBreadth() {
        return tarPatchingBreadth;
    }
    
    public void setTarPatchingBreadth(String tarPatchingBreadth) {
        this.tarPatchingBreadth = tarPatchingBreadth;
    }

    public String getCleanSite() {
        return cleanSite;
    }
    
    public void setCleanSite(String cleanSite) {
        this.cleanSite = cleanSite;
    }

    public String getBackFill() {
        return backFill;
    }
    
    public void setBackFill(String backFill) {
        this.backFill = backFill;
    }

    public String getBackFillLength() {
        return backFillLength;
    }
    
    public void setBackFillLength(String backFillLength) {
        this.backFillLength = backFillLength;
    }

    public String getBackFillBreadth() {
        return backFillBreadth;
    }
    
    public void setBackFillBreadth(String backFillBreadth) {
        this.backFillBreadth = backFillBreadth;
    }

    public String getBrickLayer() {
        return brickLayer;
    }
    
    public void setBrickLayer(String brickLayer) {
        this.brickLayer = brickLayer;
    }

    public String getPaving() {
        return paving;
    }
    
    public void setPaving(String paving) {
        this.paving = paving;
    }

    public String getPavingLength() {
        return pavingLength;
    }
    
    public void setPavingLength(String pavingLength) {
        this.pavingLength = pavingLength;
    }

    public String getPavingBreadth() {
        return pavingBreadth;
    }
    
    public void setPavingBreadth(String pavingBreadth) {
        this.pavingBreadth = pavingBreadth;
    }

    public String getUnableToLocate() {
        return unableToLocate;
    }
    
    public void setUnableToLocate(String unableToLocate) {
        this.unableToLocate = unableToLocate;
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
        JobCardSiteStatus jobCardSiteStatus = (JobCardSiteStatus) o;
        if(jobCardSiteStatus.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, jobCardSiteStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "JobCardSiteStatus{" +
            "id=" + id +
            ", tarPatching='" + tarPatching + "'" +
            ", tarPatchingLength='" + tarPatchingLength + "'" +
            ", tarPatchingBreadth='" + tarPatchingBreadth + "'" +
            ", cleanSite='" + cleanSite + "'" +
            ", backFill='" + backFill + "'" +
            ", backFillLength='" + backFillLength + "'" +
            ", backFillBreadth='" + backFillBreadth + "'" +
            ", brickLayer='" + brickLayer + "'" +
            ", paving='" + paving + "'" +
            ", pavingLength='" + pavingLength + "'" +
            ", pavingBreadth='" + pavingBreadth + "'" +
            ", unableToLocate='" + unableToLocate + "'" +
            '}';
    }
}

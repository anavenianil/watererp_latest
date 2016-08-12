package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A BurstComplaint.
 */
@Entity
@Table(name = "burst_complaint")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BurstComplaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "meter_location")
    private String meterLocation;
    
    @Column(name = "burst_pipe")
    private String burstPipe;
    
    @Column(name = "from_left")
    private String fromLeft;
    
    @Column(name = "from_sb")
    private String fromSb;
    
    @Column(name = "burst_area")
    private String burstArea;
    
    @Column(name = "pipe_type")
    private String pipeType;
    
    @Column(name = "pipe_size")
    private Integer pipeSize;
    
    @Column(name = "hole_size")
    private Integer holeSize;
    
    @Column(name = "repair_code")
    private String repairCode;
    
    @ManyToOne
    @JoinColumn(name = "water_leakage_complaint_id")
    private WaterLeakageComplaint waterLeakageComplaint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeterLocation() {
        return meterLocation;
    }
    
    public void setMeterLocation(String meterLocation) {
        this.meterLocation = meterLocation;
    }

    public String getBurstPipe() {
        return burstPipe;
    }
    
    public void setBurstPipe(String burstPipe) {
        this.burstPipe = burstPipe;
    }

    public String getFromLeft() {
        return fromLeft;
    }
    
    public void setFromLeft(String fromLeft) {
        this.fromLeft = fromLeft;
    }

    public String getFromSb() {
        return fromSb;
    }
    
    public void setFromSb(String fromSb) {
        this.fromSb = fromSb;
    }

    public String getBurstArea() {
        return burstArea;
    }
    
    public void setBurstArea(String burstArea) {
        this.burstArea = burstArea;
    }

    public String getPipeType() {
        return pipeType;
    }
    
    public void setPipeType(String pipeType) {
        this.pipeType = pipeType;
    }

    public Integer getPipeSize() {
        return pipeSize;
    }
    
    public void setPipeSize(Integer pipeSize) {
        this.pipeSize = pipeSize;
    }

    public Integer getHoleSize() {
        return holeSize;
    }
    
    public void setHoleSize(Integer holeSize) {
        this.holeSize = holeSize;
    }

    public String getRepairCode() {
        return repairCode;
    }
    
    public void setRepairCode(String repairCode) {
        this.repairCode = repairCode;
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
        BurstComplaint burstComplaint = (BurstComplaint) o;
        if(burstComplaint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, burstComplaint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BurstComplaint{" +
            "id=" + id +
            ", meterLocation='" + meterLocation + "'" +
            ", burstPipe='" + burstPipe + "'" +
            ", fromLeft='" + fromLeft + "'" +
            ", fromSb='" + fromSb + "'" +
            ", burstArea='" + burstArea + "'" +
            ", pipeType='" + pipeType + "'" +
            ", pipeSize='" + pipeSize + "'" +
            ", holeSize='" + holeSize + "'" +
            ", repairCode='" + repairCode + "'" +
            '}';
    }
}

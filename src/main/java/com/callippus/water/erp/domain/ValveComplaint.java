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
 * A ValveComplaint.
 */
@Entity
@Table(name = "valve_complaint")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ValveComplaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "closed_time")
    private ZonedDateTime closedTime;
    
    @Column(name = "open_time")
    private ZonedDateTime openTime;
    
    @Column(name = "colour_code")
    private String colourCode;
    
    @Column(name = "side")
    private String side;
    
    @Column(name = "no_of_turns")
    private Integer noOfTurns;
    
    @Column(name = "valve_size")
    private Float valveSize;
    
    @Column(name = "valve_no")
    private Integer valveNo;
    
    @Column(name = "repair_code")
    private String repairCode;
    
    @Column(name = "distance_left")
    private String distanceLeft;
    
    @Column(name = "distance_sb")
    private String distanceSb;
    
    @Column(name = "distance_z")
    private String distanceZ;
    
    @ManyToOne
    @JoinColumn(name = "water_leakage_complaint_id")
    private WaterLeakageComplaint waterLeakageComplaint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getClosedTime() {
        return closedTime;
    }
    
    public void setClosedTime(ZonedDateTime closedTime) {
        this.closedTime = closedTime;
    }

    public ZonedDateTime getOpenTime() {
        return openTime;
    }
    
    public void setOpenTime(ZonedDateTime openTime) {
        this.openTime = openTime;
    }

    public String getColourCode() {
        return colourCode;
    }
    
    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getSide() {
        return side;
    }
    
    public void setSide(String side) {
        this.side = side;
    }

    public Integer getNoOfTurns() {
        return noOfTurns;
    }
    
    public void setNoOfTurns(Integer noOfTurns) {
        this.noOfTurns = noOfTurns;
    }

    public Float getValveSize() {
        return valveSize;
    }
    
    public void setValveSize(Float valveSize) {
        this.valveSize = valveSize;
    }

    public Integer getValveNo() {
        return valveNo;
    }
    
    public void setValveNo(Integer valveNo) {
        this.valveNo = valveNo;
    }

    public String getRepairCode() {
        return repairCode;
    }
    
    public void setRepairCode(String repairCode) {
        this.repairCode = repairCode;
    }

    public String getDistanceLeft() {
        return distanceLeft;
    }
    
    public void setDistanceLeft(String distanceLeft) {
        this.distanceLeft = distanceLeft;
    }

    public String getDistanceSb() {
        return distanceSb;
    }
    
    public void setDistanceSb(String distanceSb) {
        this.distanceSb = distanceSb;
    }

    public String getDistanceZ() {
        return distanceZ;
    }
    
    public void setDistanceZ(String distanceZ) {
        this.distanceZ = distanceZ;
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
        ValveComplaint valveComplaint = (ValveComplaint) o;
        if(valveComplaint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, valveComplaint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ValveComplaint{" +
            "id=" + id +
            ", closedTime='" + closedTime + "'" +
            ", openTime='" + openTime + "'" +
            ", colourCode='" + colourCode + "'" +
            ", side='" + side + "'" +
            ", noOfTurns='" + noOfTurns + "'" +
            ", valveSize='" + valveSize + "'" +
            ", valveNo='" + valveNo + "'" +
            ", repairCode='" + repairCode + "'" +
            ", distanceLeft='" + distanceLeft + "'" +
            ", distanceSb='" + distanceSb + "'" +
            ", distanceZ='" + distanceZ + "'" +
            '}';
    }
}

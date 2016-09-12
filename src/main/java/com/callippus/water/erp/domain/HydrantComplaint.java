package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A HydrantComplaint.
 */
@Entity
@Table(name = "hydrant_complaint")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class HydrantComplaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "from_left")
    private String fromLeft;
    
    @Column(name = "from_sb")
    private String fromSb;
    
    @Column(name = "problem_at")
    private String problemAt;
    
    @Column(name = "activity_type")
    private String activityType;
    
    @Column(name = "pressure")
    private String pressure;
    
    @Column(name = "pressure_time")
    private Integer pressureTime;
    
    @Column(name = "flow")
    private String flow;
    
    @Column(name = "flow_time")
    private Integer flowTime;
    
    @ManyToOne
    @JoinColumn(name = "water_leakage_complaint_id")
    private WaterLeakageComplaint waterLeakageComplaint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProblemAt() {
        return problemAt;
    }
    
    public void setProblemAt(String problemAt) {
        this.problemAt = problemAt;
    }

    public String getActivityType() {
        return activityType;
    }
    
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getPressure() {
        return pressure;
    }
    
    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public Integer getPressureTime() {
        return pressureTime;
    }
    
    public void setPressureTime(Integer pressureTime) {
        this.pressureTime = pressureTime;
    }

    public String getFlow() {
        return flow;
    }
    
    public void setFlow(String flow) {
        this.flow = flow;
    }

    public Integer getFlowTime() {
        return flowTime;
    }
    
    public void setFlowTime(Integer flowTime) {
        this.flowTime = flowTime;
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
        HydrantComplaint hydrantComplaint = (HydrantComplaint) o;
        if(hydrantComplaint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, hydrantComplaint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "HydrantComplaint{" +
            "id=" + id +
            ", fromLeft='" + fromLeft + "'" +
            ", fromSb='" + fromSb + "'" +
            ", problemAt='" + problemAt + "'" +
            ", activityType='" + activityType + "'" +
            ", pressure='" + pressure + "'" +
            ", pressureTime='" + pressureTime + "'" +
            ", flow='" + flow + "'" +
            ", flowTime='" + flowTime + "'" +
            '}';
    }
}

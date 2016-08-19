package com.callippus.water.erp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Objects;

/**
 * A WaterLeakageComplaint.
 */
@Entity
@Table(name = "water_leakage_complaint")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaterLeakageComplaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "leakage_type")
    private String leakageType;
    
    @Column(name = "coordinate_x")
    private String coordinateX;
    
    @Column(name = "coordinate_y")
    private String coordinateY;
    
    @Column(name = "leak_magnitude")
    private String leakMagnitude;
    
    @Column(name = "complaint_date_time")
    private ZonedDateTime complaintDateTime;
    
    @Column(name = "days_required")
    private Integer daysRequired;
    
    @Column(name = "staff_required")
    private Integer staffRequired;
    
    @Column(name = "complaint_by")
    private String complaintBy;
    
    @Column(name = "status")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "division_master_id")
    private DivisionMaster divisionMaster;

    @ManyToOne
    @JoinColumn(name = "street_master_id")
    private StreetMaster streetMaster;

    /*@OneToMany(mappedBy = "waterLeakageComplaint")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobCardItemRequirement> jobCardItemRequirements = new HashSet<>();*/
    
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="water_leakage_complaint_id", referencedColumnName="id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<JobCardItemRequirement> jobCardItemRequirements = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeakageType() {
        return leakageType;
    }
    
    public void setLeakageType(String leakageType) {
        this.leakageType = leakageType;
    }

    public String getCoordinateX() {
        return coordinateX;
    }
    
    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }
    
    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getLeakMagnitude() {
        return leakMagnitude;
    }
    
    public void setLeakMagnitude(String leakMagnitude) {
        this.leakMagnitude = leakMagnitude;
    }

    public ZonedDateTime getComplaintDateTime() {
        return complaintDateTime;
    }
    
    public void setComplaintDateTime(ZonedDateTime complaintDateTime) {
        this.complaintDateTime = complaintDateTime;
    }

    public Integer getDaysRequired() {
        return daysRequired;
    }
    
    public void setDaysRequired(Integer daysRequired) {
        this.daysRequired = daysRequired;
    }

    public Integer getStaffRequired() {
        return staffRequired;
    }
    
    public void setStaffRequired(Integer staffRequired) {
        this.staffRequired = staffRequired;
    }

    public String getComplaintBy() {
        return complaintBy;
    }
    
    public void setComplaintBy(String complaintBy) {
        this.complaintBy = complaintBy;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public DivisionMaster getDivisionMaster() {
        return divisionMaster;
    }

    public void setDivisionMaster(DivisionMaster divisionMaster) {
        this.divisionMaster = divisionMaster;
    }

    public StreetMaster getStreetMaster() {
        return streetMaster;
    }

    public void setStreetMaster(StreetMaster streetMaster) {
        this.streetMaster = streetMaster;
    }

    public List<JobCardItemRequirement> getJobCardItemRequirements() {
        return jobCardItemRequirements;
    }

    public void setJobCardItemRequirements(List<JobCardItemRequirement> jobCardItemRequirements) {
        this.jobCardItemRequirements = jobCardItemRequirements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WaterLeakageComplaint waterLeakageComplaint = (WaterLeakageComplaint) o;
        if(waterLeakageComplaint.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, waterLeakageComplaint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WaterLeakageComplaint{" +
            "id=" + id +
            ", leakageType='" + leakageType + "'" +
            ", coordinateX='" + coordinateX + "'" +
            ", coordinateY='" + coordinateY + "'" +
            ", leakMagnitude='" + leakMagnitude + "'" +
            ", complaintDateTime='" + complaintDateTime + "'" +
            ", daysRequired='" + daysRequired + "'" +
            ", staffRequired='" + staffRequired + "'" +
            ", complaintBy='" + complaintBy + "'" +
            ", status='" + status + "'" +
            '}';
    }
}

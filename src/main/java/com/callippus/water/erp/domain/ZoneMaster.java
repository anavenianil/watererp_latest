package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ZoneMaster.
 */
@Entity
@Table(name = "zone_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ZoneMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "zone_name")
    private String zoneName;
    
    @Column(name = "zone_code")
    private String zoneCode;
    
    @ManyToOne
    @JoinColumn(name = "division_master_id")
    private DivisionMaster divisionMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoneName() {
        return zoneName;
    }
    
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneCode() {
        return zoneCode;
    }
    
    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public DivisionMaster getDivisionMaster() {
        return divisionMaster;
    }

    public void setDivisionMaster(DivisionMaster divisionMaster) {
        this.divisionMaster = divisionMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ZoneMaster zoneMaster = (ZoneMaster) o;
        if(zoneMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, zoneMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ZoneMaster{" +
            "id=" + id +
            ", zoneName='" + zoneName + "'" +
            ", zoneCode='" + zoneCode + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A StreetMaster.
 */
@Entity
@Table(name = "street_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StreetMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "street_name")
    private String streetName;
    
    @Column(name = "street_code")
    private String streetCode;
    
    @ManyToOne
    @JoinColumn(name = "zone_master_id")
    private ZoneMaster zoneMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }
    
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetCode() {
        return streetCode;
    }
    
    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public ZoneMaster getZoneMaster() {
        return zoneMaster;
    }

    public void setZoneMaster(ZoneMaster zoneMaster) {
        this.zoneMaster = zoneMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StreetMaster streetMaster = (StreetMaster) o;
        if(streetMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, streetMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StreetMaster{" +
            "id=" + id +
            ", streetName='" + streetName + "'" +
            ", streetCode='" + streetCode + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DivisionMaster.
 */
@Entity
@Table(name = "division_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DivisionMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "division_name")
    private String divisionName;
    
    @Column(name = "division_code")
    private String divisionCode;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }
    
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionCode() {
        return divisionCode;
    }
    
    public void setDivisionCode(String divisionCode) {
        this.divisionCode = divisionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionMaster divisionMaster = (DivisionMaster) o;
        if(divisionMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, divisionMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DivisionMaster{" +
            "id=" + id +
            ", divisionName='" + divisionName + "'" +
            ", divisionCode='" + divisionCode + "'" +
            '}';
    }
}

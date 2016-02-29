package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FeasibilityStatus.
 */
@Entity
@Table(name = "feasibility_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FeasibilityStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "status")
    private String status;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FeasibilityStatus feasibilityStatus = (FeasibilityStatus) o;
        if(feasibilityStatus.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, feasibilityStatus.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FeasibilityStatus{" +
            "id=" + id +
            ", status='" + status + "'" +
            '}';
    }
}

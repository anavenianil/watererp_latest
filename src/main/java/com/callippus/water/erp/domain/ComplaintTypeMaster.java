package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ComplaintTypeMaster.
 */
@Entity
@Table(name = "complaint_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ComplaintTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "complaint_type")
    private String complaintType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComplaintType() {
        return complaintType;
    }
    
    public void setComplaintType(String complaintType) {
        this.complaintType = complaintType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ComplaintTypeMaster complaintTypeMaster = (ComplaintTypeMaster) o;
        if(complaintTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, complaintTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ComplaintTypeMaster{" +
            "id=" + id +
            ", complaintType='" + complaintType + "'" +
            '}';
    }
}

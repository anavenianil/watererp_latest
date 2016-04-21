package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CustomerComplaints.
 */
@Entity
@Table(name = "customer_complaints")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CustomerComplaints implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "remarks")
    private String remarks;
    
    @Column(name = "relevant_doc")
    private String relevantDoc;
    
    @Column(name = "complaint_by")
    private String complaintBy;
    
    @Column(name = "complaint_date")
    private LocalDate complaintDate;
    
    @Column(name = "can")
    private String can;
    
    @ManyToOne
    @JoinColumn(name = "complaint_type_master_id")
    private ComplaintTypeMaster complaintTypeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRelevantDoc() {
        return relevantDoc;
    }
    
    public void setRelevantDoc(String relevantDoc) {
        this.relevantDoc = relevantDoc;
    }

    public String getComplaintBy() {
        return complaintBy;
    }
    
    public void setComplaintBy(String complaintBy) {
        this.complaintBy = complaintBy;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }
    
    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getCan() {
        return can;
    }
    
    public void setCan(String can) {
        this.can = can;
    }

    public ComplaintTypeMaster getComplaintTypeMaster() {
        return complaintTypeMaster;
    }

    public void setComplaintTypeMaster(ComplaintTypeMaster complaintTypeMaster) {
        this.complaintTypeMaster = complaintTypeMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerComplaints customerComplaints = (CustomerComplaints) o;
        if(customerComplaints.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, customerComplaints.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CustomerComplaints{" +
            "id=" + id +
            ", remarks='" + remarks + "'" +
            ", relevantDoc='" + relevantDoc + "'" +
            ", complaintBy='" + complaintBy + "'" +
            ", complaintDate='" + complaintDate + "'" +
            ", can='" + can + "'" +
            '}';
    }
}

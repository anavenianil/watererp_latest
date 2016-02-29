package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ReAllotment.
 */
@Entity
@Table(name = "re_allotment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReAllotment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "file_number_id")
    private FileNumber fileNumber;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "feasibility_status_id")
    private FeasibilityStatus feasibilityStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FileNumber getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(FileNumber fileNumber) {
        this.fileNumber = fileNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public FeasibilityStatus getFeasibilityStatus() {
        return feasibilityStatus;
    }

    public void setFeasibilityStatus(FeasibilityStatus feasibilityStatus) {
        this.feasibilityStatus = feasibilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReAllotment reAllotment = (ReAllotment) o;
        if(reAllotment.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, reAllotment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReAllotment{" +
            "id=" + id +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ReversalDetails.
 */
@Entity
@Table(name = "reversal_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReversalDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "cancelled_date", nullable = false)
    private LocalDate cancelledDate;
    
    @NotNull
    @Column(name = "remarks", nullable = false)
    private String remarks;
    
    @ManyToOne
    @JoinColumn(name = "coll_details_id")
    private CollDetails collDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCancelledDate() {
        return cancelledDate;
    }
    
    public void setCancelledDate(LocalDate cancelledDate) {
        this.cancelledDate = cancelledDate;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public CollDetails getCollDetails() {
        return collDetails;
    }

    public void setCollDetails(CollDetails collDetails) {
        this.collDetails = collDetails;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReversalDetails reversalDetails = (ReversalDetails) o;
        if(reversalDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, reversalDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ReversalDetails{" +
            "id=" + id +
            ", cancelledDate='" + cancelledDate + "'" +
            ", remarks='" + remarks + "'" +
            '}';
    }
}

package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ApplicationTypeMaster.
 */
@Entity
@Table(name = "application_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "application_type")
    private String applicationType;
    
    @Column(name = "created_date")
    private ZonedDateTime createdDate;
    
    @Column(name = "updated_date")
    private ZonedDateTime updatedDate;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "updated_by")
    private String updatedBy;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationType() {
        return applicationType;
    }
    
    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationTypeMaster applicationTypeMaster = (ApplicationTypeMaster) o;
        if(applicationTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, applicationTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ApplicationTypeMaster{" +
            "id=" + id +
            ", applicationType='" + applicationType + "'" +
            ", createdDate='" + createdDate + "'" +
            ", updatedDate='" + updatedDate + "'" +
            ", status='" + status + "'" +
            ", createdBy='" + createdBy + "'" +
            ", updatedBy='" + updatedBy + "'" +
            '}';
    }
}

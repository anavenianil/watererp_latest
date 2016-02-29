package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import java.time.ZonedDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DesignationMaster.
 */
@Entity
@Table(name = "designation_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DesignationMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "creation_date")
    private ZonedDateTime creationDate;
    
    @Column(name = "last_modified_date")
    private ZonedDateTime lastModifiedDate;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "order_no")
    private Integer orderNo;
    
    @Column(name = "service_type")
    private String serviceType;
    
    @Column(name = "code")
    private String code;
    
    @Column(name = "desigalias")
    private String desigalias;
    
    @ManyToOne
    @JoinColumn(name = "status_master_id")
    private StatusMaster statusMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
    
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }
    
    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderNo() {
        return orderNo;
    }
    
    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getServiceType() {
        return serviceType;
    }
    
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getDesigalias() {
        return desigalias;
    }
    
    public void setDesigalias(String desigalias) {
        this.desigalias = desigalias;
    }

    public StatusMaster getStatusMaster() {
        return statusMaster;
    }

    public void setStatusMaster(StatusMaster statusMaster) {
        this.statusMaster = statusMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DesignationMaster designationMaster = (DesignationMaster) o;
        if(designationMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, designationMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DesignationMaster{" +
            "id=" + id +
            ", name='" + name + "'" +
            ", creationDate='" + creationDate + "'" +
            ", lastModifiedDate='" + lastModifiedDate + "'" +
            ", description='" + description + "'" +
            ", orderNo='" + orderNo + "'" +
            ", serviceType='" + serviceType + "'" +
            ", code='" + code + "'" +
            ", desigalias='" + desigalias + "'" +
            '}';
    }
}

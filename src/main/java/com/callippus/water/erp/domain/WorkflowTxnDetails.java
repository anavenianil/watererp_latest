package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A WorkflowTxnDetails.
 */
@Entity
@Table(name = "workflow_txn_details")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WorkflowTxnDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "request_id")
    private Integer requestId;
    
    @Column(name = "reference_number")
    private String referenceNumber;
    
    @Column(name = "row_number")
    private Integer rowNumber;
    
    @Column(name = "column_name")
    private String columnName;
    
    @Column(name = "previous_value")
    private String previousValue;
    
    @Column(name = "new_value")
    private String newValue;
    
    @Column(name = "ip_address")
    private String ipAddress;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "request_master_id")
    private RequestMaster requestMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRequestId() {
        return requestId;
    }
    
    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }
    
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }
    
    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getColumnName() {
        return columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPreviousValue() {
        return previousValue;
    }
    
    public void setPreviousValue(String previousValue) {
        this.previousValue = previousValue;
    }

    public String getNewValue() {
        return newValue;
    }
    
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getIpAddress() {
        return ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public RequestMaster getRequestMaster() {
        return requestMaster;
    }

    public void setRequestMaster(RequestMaster requestMaster) {
        this.requestMaster = requestMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkflowTxnDetails workflowTxnDetails = (WorkflowTxnDetails) o;
        if(workflowTxnDetails.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, workflowTxnDetails.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WorkflowTxnDetails{" +
            "id=" + id +
            ", requestId='" + requestId + "'" +
            ", referenceNumber='" + referenceNumber + "'" +
            ", rowNumber='" + rowNumber + "'" +
            ", columnName='" + columnName + "'" +
            ", previousValue='" + previousValue + "'" +
            ", newValue='" + newValue + "'" +
            ", ipAddress='" + ipAddress + "'" +
            ", description='" + description + "'" +
            '}';
    }
}

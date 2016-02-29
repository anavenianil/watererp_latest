package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A ConnectionTypeMaster.
 */
@Entity
@Table(name = "connection_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ConnectionTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "connection_type")
    private String connectionType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConnectionType() {
        return connectionType;
    }
    
    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ConnectionTypeMaster connectionTypeMaster = (ConnectionTypeMaster) o;
        if(connectionTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, connectionTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ConnectionTypeMaster{" +
            "id=" + id +
            ", connectionType='" + connectionType + "'" +
            '}';
    }
}

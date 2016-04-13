package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CollectionTypeMaster.
 */
@Entity
@Table(name = "collection_type_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CollectionTypeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "coll_name")
    private String collName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCollName() {
        return collName;
    }
    
    public void setCollName(String collName) {
        this.collName = collName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CollectionTypeMaster collectionTypeMaster = (CollectionTypeMaster) o;
        if(collectionTypeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, collectionTypeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CollectionTypeMaster{" +
            "id=" + id +
            ", collName='" + collName + "'" +
            '}';
    }
}

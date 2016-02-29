package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SewerSize.
 */
@Entity
@Table(name = "sewer_size")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SewerSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "sewer_size", nullable = false)
    private String sewerSize;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSewerSize() {
        return sewerSize;
    }
    
    public void setSewerSize(String sewerSize) {
        this.sewerSize = sewerSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SewerSize sewerSize = (SewerSize) o;
        if(sewerSize.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, sewerSize.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "SewerSize{" +
            "id=" + id +
            ", sewerSize='" + sewerSize + "'" +
            '}';
    }
}

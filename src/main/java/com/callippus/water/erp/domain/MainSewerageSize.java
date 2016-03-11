package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MainSewerageSize.
 */
@Entity
@Table(name = "main_sewerage_size")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MainSewerageSize implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "size", nullable = false)
    private Float size;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSize() {
        return size;
    }
    
    public void setSize(Float size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MainSewerageSize mainSewerageSize = (MainSewerageSize) o;
        if(mainSewerageSize.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, mainSewerageSize.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MainSewerageSize{" +
            "id=" + id +
            ", size='" + size + "'" +
            '}';
    }
}

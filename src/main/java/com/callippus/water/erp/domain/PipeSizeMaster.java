package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A PipeSizeMaster.
 */
@Entity
@Table(name = "pipe_size_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PipeSizeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "pipe_size", nullable = false)
    private Float pipeSize;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPipeSize() {
        return pipeSize;
    }
    
    public void setPipeSize(Float pipeSize) {
        this.pipeSize = pipeSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PipeSizeMaster pipeSizeMaster = (PipeSizeMaster) o;
        if(pipeSizeMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pipeSizeMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PipeSizeMaster{" +
            "id=" + id +
            ", pipeSize='" + pipeSize + "'" +
            '}';
    }
}

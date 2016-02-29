package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A CategoryPipeSizeMapping.
 */
@Entity
@Table(name = "category_pipe_size_mapping")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CategoryPipeSizeMapping implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ctegory_master_id")
    private CtegoryMaster ctegoryMaster;

    @ManyToOne
    @JoinColumn(name = "pipe_size_master_id")
    private PipeSizeMaster pipeSizeMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CtegoryMaster getCtegoryMaster() {
        return ctegoryMaster;
    }

    public void setCtegoryMaster(CtegoryMaster ctegoryMaster) {
        this.ctegoryMaster = ctegoryMaster;
    }

    public PipeSizeMaster getPipeSizeMaster() {
        return pipeSizeMaster;
    }

    public void setPipeSizeMaster(PipeSizeMaster pipeSizeMaster) {
        this.pipeSizeMaster = pipeSizeMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryPipeSizeMapping categoryPipeSizeMapping = (CategoryPipeSizeMapping) o;
        if(categoryPipeSizeMapping.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, categoryPipeSizeMapping.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CategoryPipeSizeMapping{" +
            "id=" + id +
            '}';
    }
}

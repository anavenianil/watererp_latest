package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DesignationMappings.
 */
@Entity
@Table(name = "designation_mappings")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DesignationMappings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "desig_category_master_id")
    private DesigCategoryMaster desigCategoryMaster;

    @ManyToOne
    @JoinColumn(name = "sub_desig_category_master_id")
    private SubDesigCategoryMaster subDesigCategoryMaster;

    @ManyToOne
    @JoinColumn(name = "designation_master_id")
    private DesignationMaster designationMaster;

    @ManyToOne
    @JoinColumn(name = "group_master_id")
    private GroupMaster groupMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public DesigCategoryMaster getDesigCategoryMaster() {
        return desigCategoryMaster;
    }

    public void setDesigCategoryMaster(DesigCategoryMaster desigCategoryMaster) {
        this.desigCategoryMaster = desigCategoryMaster;
    }

    public SubDesigCategoryMaster getSubDesigCategoryMaster() {
        return subDesigCategoryMaster;
    }

    public void setSubDesigCategoryMaster(SubDesigCategoryMaster subDesigCategoryMaster) {
        this.subDesigCategoryMaster = subDesigCategoryMaster;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public GroupMaster getGroupMaster() {
        return groupMaster;
    }

    public void setGroupMaster(GroupMaster groupMaster) {
        this.groupMaster = groupMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DesignationMappings designationMappings = (DesignationMappings) o;
        if(designationMappings.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, designationMappings.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DesignationMappings{" +
            "id=" + id +
            ", type='" + type + "'" +
            '}';
    }
}

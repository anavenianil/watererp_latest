package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MakeOfPipe.
 */
@Entity
@Table(name = "make_of_pipe")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MakeOfPipe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "make_name", nullable = false)
    private String makeName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMakeName() {
        return makeName;
    }
    
    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MakeOfPipe makeOfPipe = (MakeOfPipe) o;
        if(makeOfPipe.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, makeOfPipe.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "MakeOfPipe{" +
            "id=" + id +
            ", makeName='" + makeName + "'" +
            '}';
    }
}

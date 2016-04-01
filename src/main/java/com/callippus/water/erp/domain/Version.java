package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Version.
 */
@Entity
@Table(name = "version")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Version implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "version_low")
    private String versionLow;
    
    @Column(name = "version_high")
    private String versionHigh;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionLow() {
        return versionLow;
    }
    
    public void setVersionLow(String versionLow) {
        this.versionLow = versionLow;
    }

    public String getVersionHigh() {
        return versionHigh;
    }
    
    public void setVersionHigh(String versionHigh) {
        this.versionHigh = versionHigh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Version version = (Version) o;
        if(version.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, version.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Version{" +
            "id=" + id +
            ", versionLow='" + versionLow + "'" +
            ", versionHigh='" + versionHigh + "'" +
            '}';
    }
}

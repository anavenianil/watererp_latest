package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FileNumber.
 */
@Entity
@Table(name = "file_number")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileNumber implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "file_no", nullable = false)
    private String fileNo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileNo() {
        return fileNo;
    }
    
    public void setFileNo(String fileNo) {
        this.fileNo = fileNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileNumber fileNumber = (FileNumber) o;
        if(fileNumber.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, fileNumber.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FileNumber{" +
            "id=" + id +
            ", fileNo='" + fileNo + "'" +
            '}';
    }
}

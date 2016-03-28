package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A FileUploadMaster.
 */
@Entity
@Table(name = "file_upload_master")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileUploadMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(name = "photo")
    private byte[] photo;
    
    @Column(name = "photo_content_type")        private String photoContentType;
    @Lob
    @Column(name = "text_file")
    private String textFile;
    
    @Lob
    @Column(name = "binary_file")
    private byte[] binaryFile;
    
    @Column(name = "binary_file_content_type")        private String binaryFileContentType;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }
    
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getTextFile() {
        return textFile;
    }
    
    public void setTextFile(String textFile) {
        this.textFile = textFile;
    }

    public byte[] getBinaryFile() {
        return binaryFile;
    }
    
    public void setBinaryFile(byte[] binaryFile) {
        this.binaryFile = binaryFile;
    }

    public String getBinaryFileContentType() {
        return binaryFileContentType;
    }

    public void setBinaryFileContentType(String binaryFileContentType) {
        this.binaryFileContentType = binaryFileContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileUploadMaster fileUploadMaster = (FileUploadMaster) o;
        if(fileUploadMaster.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, fileUploadMaster.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "FileUploadMaster{" +
            "id=" + id +
            ", photo='" + photo + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", textFile='" + textFile + "'" +
            ", binaryFile='" + binaryFile + "'" +
            ", binaryFileContentType='" + binaryFileContentType + "'" +
            '}';
    }
}

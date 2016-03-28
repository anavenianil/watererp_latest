package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.FileUploadMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FileUploadMaster entity.
 */
public interface FileUploadMasterRepository extends JpaRepository<FileUploadMaster,Long> {

}

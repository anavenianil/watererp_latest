package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.FileNumber;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FileNumber entity.
 */
public interface FileNumberRepository extends JpaRepository<FileNumber,Long> {

}

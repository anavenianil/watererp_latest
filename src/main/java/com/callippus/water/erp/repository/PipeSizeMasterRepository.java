package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.PipeSizeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PipeSizeMaster entity.
 */
public interface PipeSizeMasterRepository extends JpaRepository<PipeSizeMaster,Long> {
	PipeSizeMaster findByPipeSize(Float pipeSize);

}

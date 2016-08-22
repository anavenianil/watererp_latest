package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DesignationMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DesignationMaster entity.
 */
public interface DesignationMasterRepository extends JpaRepository<DesignationMaster,Long> {
	
	List<DesignationMaster> findAllByOrderByNameAsc();

}

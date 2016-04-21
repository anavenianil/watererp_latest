package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ZoneMaster;

/**
 * Spring Data JPA repository for the ZoneMaster entity.
 */
public interface ZoneMasterRepository extends JpaRepository<ZoneMaster,Long> {
	
	//Page<ZoneMaster>findByDivisionMaster(Pageable pageable, DivisionMaster divisionId);

}

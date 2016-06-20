package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.TariffCategoryMaster;

/**
 * Spring Data JPA repository for the TariffCategoryMaster entity.
 */
public interface TariffCategoryMasterRepository extends JpaRepository<TariffCategoryMaster,Long> {
	
	//@Query("SELECT tc FROM TariffCategoryMaster tc where tc.type=:type")
	Page<TariffCategoryMaster> findByType(Pageable pageable, String type);

}

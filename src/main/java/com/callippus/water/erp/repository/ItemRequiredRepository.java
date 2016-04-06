package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.ItemRequired;

/**
 * Spring Data JPA repository for the ItemRequired entity.
 */
public interface ItemRequiredRepository extends JpaRepository<ItemRequired,Long> {
	
	@Query("Select ir from ItemRequired ir where ir.proceedings.id=:proceedingsId")
	Page<ItemRequired> findByProceedings(Pageable pageable, @Param("proceedingsId") Long proceedingsId);

}

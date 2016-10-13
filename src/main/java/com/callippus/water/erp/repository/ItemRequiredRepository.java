package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.ItemRequired;
import com.callippus.water.erp.domain.Proceedings;

/**
 * Spring Data JPA repository for the ItemRequired entity.
 */
public interface ItemRequiredRepository extends JpaRepository<ItemRequired,Long> {
	
	/*@Query("Select ir from ItemRequired ir where ir.proceedings.id=:proceedingsId")
	Page<ItemRequired> findByProceedings(Pageable pageable, Long proceedingsId);*/
	
	//@Query("Select ir from ItemRequired ir where ir.applicationTxn.id=:applicationTxnId")
	Page<ItemRequired> findByApplicationTxn(Pageable pageable, ApplicationTxn applicationTxn);
	
	List<ItemRequired> findByProceedingsAndPrividedFromStores(Proceedings proceedings, Boolean prividedFromStores);

}

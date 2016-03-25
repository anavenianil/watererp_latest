package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;

/**
 * Spring Data JPA repository for the ApplicationTxn entity.
 */
public interface ApplicationTxnRepository extends JpaRepository<ApplicationTxn,Long> {
	
	//Long countByStatus(Integer status);
	

}

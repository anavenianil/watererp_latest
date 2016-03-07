package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;

/**
 * Spring Data JPA repository for the ApplicationTxn entity.
 */
public interface ApplicationTxnRepository extends JpaRepository<ApplicationTxn,Long> {
	
	Page<ApplicationTxn>findByStatus(Pageable pageable, String status);

}

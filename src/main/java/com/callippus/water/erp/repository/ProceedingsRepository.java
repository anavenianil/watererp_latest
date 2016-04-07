package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.Proceedings;

/**
 * Spring Data JPA repository for the Proceedings entity.
 */
public interface ProceedingsRepository extends JpaRepository<Proceedings,Long> {
	
	public Proceedings findByApplicationTxn(ApplicationTxn applicationTxn);

}

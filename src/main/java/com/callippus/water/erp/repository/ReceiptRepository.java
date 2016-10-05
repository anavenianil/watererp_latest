package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.Receipt;

/**
 * Spring Data JPA repository for the Receipt entity.
 */
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {
	
	Receipt findByApplicationTxn(ApplicationTxn applicationTxn);

}

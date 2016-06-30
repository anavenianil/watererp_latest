package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.CollectionTypeMaster;
import com.callippus.water.erp.domain.enumeration.TransactionType;

/**
 * Spring Data JPA repository for the CollectionTypeMaster entity.
 */
public interface CollectionTypeMasterRepository extends JpaRepository<CollectionTypeMaster,Long> {
	
	Page<CollectionTypeMaster> findByTxnType(Pageable pageable, TransactionType txnType);

}

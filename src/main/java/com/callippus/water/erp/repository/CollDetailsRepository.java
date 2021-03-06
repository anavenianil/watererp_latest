package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.CollectionTypeMaster;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the CollDetails entity.
 */
public interface CollDetailsRepository extends JpaRepository<CollDetails,Long> {
	Page<CollDetails> findByTxnStatusOrderByIdDesc(Pageable pageable, String txnStatus);

	Page<CollDetails> findByCan(Pageable pageable, String can);
	
	List<CollDetails> findTop10ByCanAndReversalRefOrderByIdDesc(String can, String reversalRef);
	List<CollDetails> findTop10ByCanOrderByIdDesc(String can);
	
}

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
	Page<CollDetails> findByTxnStatus(Pageable pageable, String txnStatus);

	Page<CollDetails> findByCan(Pageable pageable, String can);
	
	//List<CollDetails> findTop10ByCanAndReversalRefOrderByIdDesc(String can, String reversalRef);
	
	@Query("select cd from CollDetails cd where cd.can=:can and cd.reversalRef !=''")
	List<CollDetails> findTop10ByCanOrderByIdDesc(@Param("can") String can);
}

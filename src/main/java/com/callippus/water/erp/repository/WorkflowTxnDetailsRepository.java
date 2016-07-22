package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.WorkflowTxnDetails;

/**
 * Spring Data JPA repository for the WorkflowTxnDetails entity.
 */
public interface WorkflowTxnDetailsRepository extends JpaRepository<WorkflowTxnDetails,Long> {

	Page<WorkflowTxnDetails> findByRequestId(Pageable pageable, Integer requestId);
}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WorkflowTxnDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkflowTxnDetails entity.
 */
public interface WorkflowTxnDetailsRepository extends JpaRepository<WorkflowTxnDetails,Long> {

}

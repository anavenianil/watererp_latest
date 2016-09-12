package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.StatusMaster;
import com.callippus.water.erp.domain.WorkflowRelations;

/**
 * Spring Data JPA repository for the WorkflowRelations entity.
 */
public interface WorkflowRelationsRepository extends JpaRepository<WorkflowRelations,Long> {
	
	List<WorkflowRelations> findByStatusMaster(StatusMaster statusMaster);

}

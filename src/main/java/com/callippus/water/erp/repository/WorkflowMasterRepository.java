package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.StatusMaster;
import com.callippus.water.erp.domain.WorkflowMaster;

/**
 * Spring Data JPA repository for the WorkflowMaster entity.
 */
public interface WorkflowMasterRepository extends JpaRepository<WorkflowMaster,Long> {
	
	 Page<WorkflowMaster> findByStatusMaster(Pageable pageable, StatusMaster statusMaster);

}

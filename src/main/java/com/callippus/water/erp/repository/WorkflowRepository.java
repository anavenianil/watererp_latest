package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.Workflow;
import com.callippus.water.erp.domain.WorkflowMaster;

/**
 * Spring Data JPA repository for the Workflow entity.
 */
public interface WorkflowRepository extends JpaRepository<Workflow,Long> {
	

	
	@Query("Select w from Workflow w where w.workflowMaster.id=:workflowMasterId")
	public Page<Workflow> findByWorkflowMaster(Pageable pageable, @Param("workflowMasterId")Long workflowMasterId);
	
	public Workflow findByWorkflowMasterAndStageId(WorkflowMaster workflowMaster, Integer stageId);
	//List<Workflow> findByWorkflowMaster(WorkflowMaster workflowMaster);

}

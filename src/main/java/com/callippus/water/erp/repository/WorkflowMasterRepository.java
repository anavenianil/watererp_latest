package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WorkflowMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkflowMaster entity.
 */
public interface WorkflowMasterRepository extends JpaRepository<WorkflowMaster,Long> {

}

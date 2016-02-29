package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WorkflowStageMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkflowStageMaster entity.
 */
public interface WorkflowStageMasterRepository extends JpaRepository<WorkflowStageMaster,Long> {

}

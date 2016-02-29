package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WorkflowTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkflowTypeMaster entity.
 */
public interface WorkflowTypeMasterRepository extends JpaRepository<WorkflowTypeMaster,Long> {

}

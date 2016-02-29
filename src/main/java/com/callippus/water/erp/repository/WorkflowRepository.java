package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Workflow;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Workflow entity.
 */
public interface WorkflowRepository extends JpaRepository<Workflow,Long> {

}

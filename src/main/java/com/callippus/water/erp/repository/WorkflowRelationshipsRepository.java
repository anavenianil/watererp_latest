package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WorkflowRelationships;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkflowRelationships entity.
 */
public interface WorkflowRelationshipsRepository extends JpaRepository<WorkflowRelationships,Long> {

}

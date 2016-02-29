package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WorkflowRelations;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WorkflowRelations entity.
 */
public interface WorkflowRelationsRepository extends JpaRepository<WorkflowRelations,Long> {

}

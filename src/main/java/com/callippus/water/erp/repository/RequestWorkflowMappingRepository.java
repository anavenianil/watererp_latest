package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.RequestWorkflowMapping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RequestWorkflowMapping entity.
 */
public interface RequestWorkflowMappingRepository extends JpaRepository<RequestWorkflowMapping,Long> {

}

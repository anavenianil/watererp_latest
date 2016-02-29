package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ReqOrgWorkflowMapping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ReqOrgWorkflowMapping entity.
 */
public interface ReqOrgWorkflowMappingRepository extends JpaRepository<ReqOrgWorkflowMapping,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ReqDesigWorkflowMapping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ReqDesigWorkflowMapping entity.
 */
public interface ReqDesigWorkflowMappingRepository extends JpaRepository<ReqDesigWorkflowMapping,Long> {

}

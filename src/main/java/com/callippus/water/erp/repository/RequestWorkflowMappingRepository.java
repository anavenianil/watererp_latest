package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.RequestMaster;
import com.callippus.water.erp.domain.RequestWorkflowMapping;

/**
 * Spring Data JPA repository for the RequestWorkflowMapping entity.
 */
public interface RequestWorkflowMappingRepository extends JpaRepository<RequestWorkflowMapping,Long> {

	RequestWorkflowMapping findByRequestMaster(RequestMaster requestMaster);
}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.RoleWorkflowMapping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RoleWorkflowMapping entity.
 */
public interface RoleWorkflowMappingRepository extends JpaRepository<RoleWorkflowMapping,Long> {

}

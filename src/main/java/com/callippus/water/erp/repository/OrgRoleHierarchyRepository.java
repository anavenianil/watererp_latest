package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OrgRoleHierarchy;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrgRoleHierarchy entity.
 */
public interface OrgRoleHierarchyRepository extends JpaRepository<OrgRoleHierarchy,Long> {
	
	List<OrgRoleHierarchy> findAllByOrderByRoleHierarchyNameAsc();

}

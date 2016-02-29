package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OrgHierarchy;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrgHierarchy entity.
 */
public interface OrgHierarchyRepository extends JpaRepository<OrgHierarchy,Long> {

}

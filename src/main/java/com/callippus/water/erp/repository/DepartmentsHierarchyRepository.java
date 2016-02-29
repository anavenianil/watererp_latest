package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DepartmentsHierarchy;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DepartmentsHierarchy entity.
 */
public interface DepartmentsHierarchyRepository extends JpaRepository<DepartmentsHierarchy,Long> {

}

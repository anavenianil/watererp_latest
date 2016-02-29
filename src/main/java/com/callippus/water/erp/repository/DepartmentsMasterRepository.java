package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DepartmentsMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DepartmentsMaster entity.
 */
public interface DepartmentsMasterRepository extends JpaRepository<DepartmentsMaster,Long> {

}

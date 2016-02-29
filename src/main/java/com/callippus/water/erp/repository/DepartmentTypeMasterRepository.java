package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DepartmentTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DepartmentTypeMaster entity.
 */
public interface DepartmentTypeMasterRepository extends JpaRepository<DepartmentTypeMaster,Long> {

}

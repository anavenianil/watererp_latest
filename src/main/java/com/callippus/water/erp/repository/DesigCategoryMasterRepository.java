package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DesigCategoryMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DesigCategoryMaster entity.
 */
public interface DesigCategoryMasterRepository extends JpaRepository<DesigCategoryMaster,Long> {

}

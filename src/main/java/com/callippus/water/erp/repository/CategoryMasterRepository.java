package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CategoryMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CategoryMaster entity.
 */
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster,Long> {

}

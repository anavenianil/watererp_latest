package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CategoryPipeSizeMapping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CategoryPipeSizeMapping entity.
 */
public interface CategoryPipeSizeMappingRepository extends JpaRepository<CategoryPipeSizeMapping,Long> {

}

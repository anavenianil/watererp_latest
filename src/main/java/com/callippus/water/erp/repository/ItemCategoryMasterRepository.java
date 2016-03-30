package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ItemCategoryMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ItemCategoryMaster entity.
 */
public interface ItemCategoryMasterRepository extends JpaRepository<ItemCategoryMaster,Long> {

}

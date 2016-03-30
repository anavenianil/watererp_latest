package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ItemSubCategoryMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ItemSubCategoryMaster entity.
 */
public interface ItemSubCategoryMasterRepository extends JpaRepository<ItemSubCategoryMaster,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ItemCodeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ItemCodeMaster entity.
 */
public interface ItemCodeMasterRepository extends JpaRepository<ItemCodeMaster,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ItemCompanyMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ItemCompanyMaster entity.
 */
public interface ItemCompanyMasterRepository extends JpaRepository<ItemCompanyMaster,Long> {

}

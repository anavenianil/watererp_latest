package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MerchantMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MerchantMaster entity.
 */
public interface MerchantMasterRepository extends JpaRepository<MerchantMaster,Long> {

}

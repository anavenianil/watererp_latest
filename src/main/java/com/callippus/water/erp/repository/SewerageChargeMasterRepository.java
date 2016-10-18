package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.SewerageChargeMaster;
import com.callippus.water.erp.domain.TariffCategoryMaster;

/**
 * Spring Data JPA repository for the SewerageChargeMaster entity.
 */
public interface SewerageChargeMasterRepository extends JpaRepository<SewerageChargeMaster,Long> {
	
	SewerageChargeMaster findByTariffCategoryMaster(TariffCategoryMaster tariffCategoryMaster);

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;

import org.springframework.data.jpa.repository.*;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Data JPA repository for the TariffMaster entity.
 */
public interface TariffMasterCustomRepository extends JpaRepository<TariffMaster,Long> {	
	List<java.util.Map<String, Object>> findTariffs(ZonedDateTime validFrom, ZonedDateTime validTo, float avgKL, int unmetered_flag);
}

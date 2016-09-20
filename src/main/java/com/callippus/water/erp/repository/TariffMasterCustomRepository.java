package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;

import org.springframework.data.jpa.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Spring Data JPA repository for the TariffMaster entity.
 */
public interface TariffMasterCustomRepository extends JpaRepository<TariffMaster,Long> {	
	List<java.util.Map<String, Object>> findTariffs(String can, LocalDate validFrom, LocalDate validTo, BigDecimal avgKL, int unMeteredFlag, int newMeterFlag, boolean isTelescopic);
	List<java.util.Map<String, Object>> getTariffs(String can, LocalDate validFrom, LocalDate validTo, BigDecimal avgKL,
			int unMeteredFlag, int newMeterFlag);
}

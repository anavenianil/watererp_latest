package com.callippus.water.erp.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.CustMeterMapping;

/**
 * Spring Data JPA repository for the CustMeterMapping entity.
 */
public interface CustMeterMappingRepository extends JpaRepository<CustMeterMapping,Long> {
	
	CustMeterMapping findByCustDetailsAndToDate(CustDetails custDetails, LocalDate localDate);

}

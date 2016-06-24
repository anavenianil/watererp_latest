package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.MeterDetails;

/**
 * Spring Data JPA repository for the MeterDetails entity.
 */
public interface MeterDetailsRepository extends JpaRepository<MeterDetails,Long> {
	
	@Query("Select md from MeterDetails md where md.meterStatus.id =:meterStatusId")

	Page<MeterDetails> findByMeterStatus(Pageable pageable, @Param("meterStatusId")Long meterStatusId);

}

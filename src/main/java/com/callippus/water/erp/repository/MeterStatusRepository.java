package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.MeterStatus;

/**
 * Spring Data JPA repository for the MeterStatus entity.
 */
public interface MeterStatusRepository extends JpaRepository<MeterStatus,Long> {
	
	MeterStatus findByStatus(String status);

}

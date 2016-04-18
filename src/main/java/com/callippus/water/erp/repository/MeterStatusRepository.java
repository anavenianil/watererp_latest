package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MeterStatus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MeterStatus entity.
 */
public interface MeterStatusRepository extends JpaRepository<MeterStatus,Long> {

}

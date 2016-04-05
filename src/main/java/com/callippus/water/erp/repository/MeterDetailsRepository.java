package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MeterDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MeterDetails entity.
 */
public interface MeterDetailsRepository extends JpaRepository<MeterDetails,Long> {

}

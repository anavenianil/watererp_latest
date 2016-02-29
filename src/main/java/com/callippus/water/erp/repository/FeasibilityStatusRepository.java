package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.FeasibilityStatus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FeasibilityStatus entity.
 */
public interface FeasibilityStatusRepository extends JpaRepository<FeasibilityStatus,Long> {

}

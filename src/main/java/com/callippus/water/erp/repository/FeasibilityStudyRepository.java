package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.FeasibilityStudy;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FeasibilityStudy entity.
 */
public interface FeasibilityStudyRepository extends JpaRepository<FeasibilityStudy,Long> {

}

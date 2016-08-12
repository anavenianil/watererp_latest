package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.JobCardItemRequirement;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the JobCardItemRequirement entity.
 */
public interface JobCardItemRequirementRepository extends JpaRepository<JobCardItemRequirement,Long> {

}

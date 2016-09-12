package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.JobCardItemRequirement;
import com.callippus.water.erp.domain.WaterLeakageComplaint;

/**
 * Spring Data JPA repository for the JobCardItemRequirement entity.
 */
public interface JobCardItemRequirementRepository extends JpaRepository<JobCardItemRequirement,Long> {
	
	List<JobCardItemRequirement> findByWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint);

}

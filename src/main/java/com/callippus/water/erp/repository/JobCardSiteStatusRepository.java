package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.JobCardSiteStatus;
import com.callippus.water.erp.domain.WaterLeakageComplaint;

/**
 * Spring Data JPA repository for the JobCardSiteStatus entity.
 */
public interface JobCardSiteStatusRepository extends JpaRepository<JobCardSiteStatus,Long> {
	
	public JobCardSiteStatus findByWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint);

}

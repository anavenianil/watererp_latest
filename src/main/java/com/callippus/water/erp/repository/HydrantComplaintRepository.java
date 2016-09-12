package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.HydrantComplaint;
import com.callippus.water.erp.domain.WaterLeakageComplaint;

/**
 * Spring Data JPA repository for the HydrantComplaint entity.
 */
public interface HydrantComplaintRepository extends JpaRepository<HydrantComplaint,Long> {
	
	
	public HydrantComplaint findByWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint);

}

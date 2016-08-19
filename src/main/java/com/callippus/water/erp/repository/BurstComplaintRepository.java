package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.BurstComplaint;
import com.callippus.water.erp.domain.WaterLeakageComplaint;

/**
 * Spring Data JPA repository for the BurstComplaint entity.
 */
public interface BurstComplaintRepository extends JpaRepository<BurstComplaint,Long> {
	
	BurstComplaint findByWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint);

}

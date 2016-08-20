package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ValveComplaint;
import com.callippus.water.erp.domain.WaterLeakageComplaint;

/**
 * Spring Data JPA repository for the ValveComplaint entity.
 */
public interface ValveComplaintRepository extends JpaRepository<ValveComplaint,Long> {
	
	public List<ValveComplaint> findByWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint);

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.WaterLeakageComplaint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the WaterLeakageComplaint entity.
 */
public interface WaterLeakageComplaintRepository extends JpaRepository<WaterLeakageComplaint,Long> {

}

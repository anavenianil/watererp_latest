package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BurstComplaint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BurstComplaint entity.
 */
public interface BurstComplaintRepository extends JpaRepository<BurstComplaint,Long> {

}

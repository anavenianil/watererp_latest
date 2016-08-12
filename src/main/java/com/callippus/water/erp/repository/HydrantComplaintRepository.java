package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.HydrantComplaint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the HydrantComplaint entity.
 */
public interface HydrantComplaintRepository extends JpaRepository<HydrantComplaint,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ValveComplaint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ValveComplaint entity.
 */
public interface ValveComplaintRepository extends JpaRepository<ValveComplaint,Long> {

}

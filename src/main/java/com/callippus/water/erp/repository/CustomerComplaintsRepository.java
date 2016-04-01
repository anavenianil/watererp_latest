package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CustomerComplaints;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CustomerComplaints entity.
 */
public interface CustomerComplaintsRepository extends JpaRepository<CustomerComplaints,Long> {

}

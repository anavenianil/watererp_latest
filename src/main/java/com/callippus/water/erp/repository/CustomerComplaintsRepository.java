package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.CustomerComplaints;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CustomerComplaints entity.
 */
public interface CustomerComplaintsRepository extends JpaRepository<CustomerComplaints,Long> {
	public List<CustomerComplaints> findByCanAndStatus(String can, Integer status);
	public Page<CustomerComplaints> findByCan(Pageable pageable, String can);	
}

package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.CustomerComplaints;

public interface CustomerComplaintsCustomRepository extends
JpaRepository<CustomerComplaints, Long> {
	   
	public List<String> searchCustomerComplaint(@Param("searchTerm") String searchTerm);
	
}

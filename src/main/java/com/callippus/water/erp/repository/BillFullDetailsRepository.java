package com.callippus.water.erp.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.BillFullDetails;

/**
 * Spring Data JPA repository for the BillFullDetails entity.
 */
public interface BillFullDetailsRepository extends JpaRepository<BillFullDetails,Long> {
	public BillFullDetails findByCanAndToMonth(String can, String toMonth);
	
	public BillFullDetails findByCanAndBillDate(String can, LocalDate billDate);
	
	public Page<BillFullDetails> findByCan(Pageable page, String can);
}

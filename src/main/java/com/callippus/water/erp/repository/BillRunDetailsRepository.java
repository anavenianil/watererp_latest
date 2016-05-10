package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the BillRunDetails entity.
 */
public interface BillRunDetailsRepository extends JpaRepository<BillRunDetails,Long> {
	@Query("SELECT brd FROM BillRunDetails brd WHERE brd.billRunMaster.id=:id")
	public Page<BillRunDetails> findByBillRunId(@Param("id") Long id, Pageable pageable); //For Front-end
	
	@Query("SELECT brd FROM BillRunDetails brd WHERE brd.billRunMaster.id=:id")
	public List<BillRunDetails> findByBillRunId(@Param("id") Long id);//For Back-end (During commit,...)
	
	@Query("SELECT brd FROM BillRunDetails brd WHERE brd.status=4 and brd.can=:can and brd.billFullDetails.toMonth=:toMonth")
	public BillRunDetails findByCanAndToMonth(@Param("can") String can, @Param("toMonth") String toMonth);
	
}

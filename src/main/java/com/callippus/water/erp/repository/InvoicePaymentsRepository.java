package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.InvoicePayments;

/**
 * Spring Data JPA repository for the InvoicePayments entity.
 */
public interface InvoicePaymentsRepository extends JpaRepository<InvoicePayments,Long> {
	
	List<InvoicePayments> findByCollDetails(CollDetails collDetails);

}

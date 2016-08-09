package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.InvoicePayments;

/**
 * Spring Data JPA repository for the InvoicePayments entity.
 */
public interface InvoicePaymentsRepository extends JpaRepository<InvoicePayments,Long> {
	
	InvoicePayments findByCollDetails(CollDetails collDetails);

}

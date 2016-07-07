package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.InvoicePayments;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InvoicePayments entity.
 */
public interface InvoicePaymentsRepository extends JpaRepository<InvoicePayments,Long> {

}

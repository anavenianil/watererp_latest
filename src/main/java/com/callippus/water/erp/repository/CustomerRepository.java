package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.Customer;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	Page<Customer> findByChangeType(Pageable pageable, String changeType);

}

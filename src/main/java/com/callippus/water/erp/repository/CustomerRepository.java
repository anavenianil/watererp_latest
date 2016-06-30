package com.callippus.water.erp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.Customer;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	Page<Customer> findByChangeType(Pageable pageable, String changeType);
	
	Page<Customer> findByCanAndChangeType(Pageable pageable, String can, String changeType);
	
	/*@Query("select c from Customer c where c.id=(SELECT max(cu.id) FROM Customer cu where cu.changeType=:changeType and cu.can="
			+ "(select cd.can from CustDetails cd where cd.can=:can and cd.status='ACTIVE'))")*/
	@Query("select c from Customer c where c.can=:can and c.status !=0 and changeType=:changeType")
	Customer findByChangeTypeAndCan(@Param("changeType")String changeType, @Param("can")String can);

}

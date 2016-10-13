package com.callippus.water.erp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.Customer;
import com.callippus.water.erp.domain.enumeration.ChangeCaseStatus;
import com.callippus.water.erp.domain.enumeration.ChangeCaseType;

/**
 * Spring Data JPA repository for the Customer entity.
 */
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	Page<Customer> findByChangeType(Pageable pageable, ChangeCaseType changeType);
	
	Page<Customer> findByCanAndChangeType(Pageable pageable, String can, ChangeCaseType changeType);
	
	/*@Query("select c from Customer c where c.id=(SELECT max(cu.id) FROM Customer cu where cu.changeType=:changeType and cu.can="
			+ "(select cd.can from CustDetails cd where cd.can=:can and cd.status='ACTIVE'))")*/
	@Query("select c from Customer c where c.can=:can and c.status !='BILLED' and changeType=:changeType")
	Customer findByChangeTypeAndCan(@Param("changeType")ChangeCaseType changeType, @Param("can")String can);
	
	List<Customer> findByCanAndStatus(String can, ChangeCaseStatus status);

	List<Customer> findByRequestedDateAndChangeTypeOrderByIdDesc(LocalDate requestedDate, ChangeCaseType changeType);
	
	List<Customer> findByCanAndChangeTypeOrderByIdDesc(String can, ChangeCaseType changeType);
}

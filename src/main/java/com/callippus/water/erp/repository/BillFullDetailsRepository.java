package com.callippus.water.erp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.BillFullDetails;

/**.
 * Spring Data JPA repository for the BillFullDetails entity.
 */
public interface BillFullDetailsRepository extends JpaRepository<BillFullDetails,Long> {
	public BillFullDetails findByCanAndBillDate(String can, LocalDate billDate);
	
	public Page<BillFullDetails> findByCan(Pageable pageable, String can);
	
	@Query("select b from BillFullDetails b where b.can=:can and b.dueAmount > 0 order by id")
	public List<BillFullDetails> findBillsDue(@Param("can") String can);
	
}

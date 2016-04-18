package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.ApplicationTxn;

/**
 * Spring Data JPA repository for the ApplicationTxn entity.
 */
public interface ApplicationTxnRepository extends JpaRepository<ApplicationTxn,Long> {

    @Query("select applicationTxn from ApplicationTxn applicationTxn where applicationTxn.user.login = ?#{principal.username}")
    List<ApplicationTxn> findByUserIsCurrentUser();

    @Query("select applicationTxn from ApplicationTxn applicationTxn where applicationTxn.requestAt.login = ?#{principal.username}")
    List<ApplicationTxn> findByRequestAtIsCurrentUser();
    
    Page<ApplicationTxn> findByStatus(Pageable pageable, Integer status);
	
	Page<ApplicationTxn> findAllByOrderByStatusAsc(Pageable pageable);
	
	//@Query("select max(SUBSTRING(can, 1, 2)) division, max(SUBSTRING(can, 3, 2)) street, max(SUBSTRING(can, 5, 8)) num  from ApplicationTxn at where SUBSTRING(can, 1,2)=:division and SUBSTRING(can, 3,2)=:street")
	@Query("select max(SUBSTRING(can, 5, 8))  "
			+ "from ApplicationTxn at where SUBSTRING(can, 1,2)=:division and SUBSTRING(can, 3,2)=:street")
	Integer findByCan(@Param("division")String division, @Param("street")String street);

}

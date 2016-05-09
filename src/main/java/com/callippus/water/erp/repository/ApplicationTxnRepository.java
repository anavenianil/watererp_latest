package com.callippus.water.erp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.User;

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
	
	
	//@Query("Select at from ApplicationTxn at where at.requestAt.login =:login")
	/*@Query("SELECT at FROM ApplicationTxn at where at.requestAt=:login and at.id in("
			+ "select rwh.domainObject FROM RequestWorkflowHistory rwh where rwh.statusMaster=:3)")*/
	@Query("SELECT at FROM ApplicationTxn at where at.requestAt.id=(select u.id from User u where u.login=:login) and at.id in"
			+ "(select rwh.domainObject FROM RequestWorkflowHistory rwh where rwh.statusMaster.id=3 and rwh.assignedTo.id="
			+ "(select u.id from User u where u.login=:login))")
	Page<ApplicationTxn> findByRequestAt(Pageable pageable, @Param("login")String login);
	
	//@Query("select max(SUBSTRING(can, 1, 2)) division, max(SUBSTRING(can, 3, 2)) street, max(SUBSTRING(can, 5, 8)) num  from ApplicationTxn at where SUBSTRING(can, 1,2)=:division and SUBSTRING(can, 3,2)=:street")
	/*@Query("select max(SUBSTRING(can, 5, 8))  "
			+ "from ApplicationTxn at where SUBSTRING(can, 1,2)=:division and SUBSTRING(can, 3,2)=:street")
	Integer findByCan(@Param("division")String division, @Param("street")String street);*/
	
	public ApplicationTxn findByCan(String can);

}

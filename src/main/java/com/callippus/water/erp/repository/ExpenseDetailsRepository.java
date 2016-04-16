package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ExpenseDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ExpenseDetails entity.
 */
public interface ExpenseDetailsRepository extends JpaRepository<ExpenseDetails,Long> {

}

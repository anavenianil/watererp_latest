package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CustDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CustDetails entity.
 */
public interface CustDetailsRepository extends JpaRepository<CustDetails,Long> {
	public CustDetails findByCan(String can);
}

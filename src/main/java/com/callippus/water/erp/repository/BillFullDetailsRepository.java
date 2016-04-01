package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillFullDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BillFullDetails entity.
 */
public interface BillFullDetailsRepository extends JpaRepository<BillFullDetails,Long> {

}

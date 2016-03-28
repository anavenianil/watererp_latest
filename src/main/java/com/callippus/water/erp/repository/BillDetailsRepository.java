package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BillDetails entity.
 */
public interface BillDetailsRepository extends JpaRepository<BillDetails,Long> {

}

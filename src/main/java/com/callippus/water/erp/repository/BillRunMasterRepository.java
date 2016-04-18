package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillRunMaster;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BillRunMaster entity.
 */
public interface BillRunMasterRepository extends JpaRepository<BillRunMaster,Long> {
}

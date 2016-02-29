package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CashBookMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CashBookMaster entity.
 */
public interface CashBookMasterRepository extends JpaRepository<CashBookMaster,Long> {

}

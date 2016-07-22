package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BankMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BankMaster entity.
 */
public interface BankMasterRepository extends JpaRepository<BankMaster,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TransactionTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TransactionTypeMaster entity.
 */
public interface TransactionTypeMasterRepository extends JpaRepository<TransactionTypeMaster,Long> {

}

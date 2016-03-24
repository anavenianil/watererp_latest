package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ApplicationTxn;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ApplicationTxn entity.
 */
public interface ApplicationTxnRepository extends JpaRepository<ApplicationTxn,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.StatusMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StatusMaster entity.
 */
public interface StatusMasterRepository extends JpaRepository<StatusMaster,Long> {
	StatusMaster findByStatus(String status);
}

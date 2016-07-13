package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.StatusMaster;

/**
 * Spring Data JPA repository for the StatusMaster entity.
 */
public interface StatusMasterRepository extends JpaRepository<StatusMaster,Long> {
	
	StatusMaster findByStatus(String status);
	
	Page<StatusMaster> findByDescriptionIn(Pageable pageable, List<String> description);
}

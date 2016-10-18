package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.SewerageRequest;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SewerageRequest entity.
 */
public interface SewerageRequestRepository extends JpaRepository<SewerageRequest,Long> {

}

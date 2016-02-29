package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ReAllotment;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ReAllotment entity.
 */
public interface ReAllotmentRepository extends JpaRepository<ReAllotment,Long> {

}

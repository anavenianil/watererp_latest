package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ConnectionTerminate;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ConnectionTerminate entity.
 */
public interface ConnectionTerminateRepository extends JpaRepository<ConnectionTerminate,Long> {
	ConnectionTerminate findByCan(String can);
}

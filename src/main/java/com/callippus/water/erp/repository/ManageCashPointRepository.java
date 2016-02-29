package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ManageCashPoint;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ManageCashPoint entity.
 */
public interface ManageCashPointRepository extends JpaRepository<ManageCashPoint,Long> {

}

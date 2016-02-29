package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.SewerSize;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SewerSize entity.
 */
public interface SewerSizeRepository extends JpaRepository<SewerSize,Long> {

}

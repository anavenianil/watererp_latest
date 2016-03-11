package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MainWaterSize;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MainWaterSize entity.
 */
public interface MainWaterSizeRepository extends JpaRepository<MainWaterSize,Long> {

}

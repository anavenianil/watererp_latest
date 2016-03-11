package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MainSewerageSize;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MainSewerageSize entity.
 */
public interface MainSewerageSizeRepository extends JpaRepository<MainSewerageSize,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DesignationMappings;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DesignationMappings entity.
 */
public interface DesignationMappingsRepository extends JpaRepository<DesignationMappings,Long> {

}

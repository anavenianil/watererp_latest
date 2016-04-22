package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Module;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Module entity.
 */
public interface ModuleRepository extends JpaRepository<Module,Long> {
}

package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.Module2MenuItem;
import com.callippus.water.erp.domain.ModuleMenuDTO;

/**
 * Spring Data JPA repository for the Module2menu_item entity.
 */
public interface Module2MenuItemCustomRepository extends JpaRepository<Module2MenuItem, Long> {

    List<ModuleMenuDTO> findAllByLoginUsingMapping(String user);
}
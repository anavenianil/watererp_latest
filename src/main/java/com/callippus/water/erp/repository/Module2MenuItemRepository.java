package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.Module;
import com.callippus.water.erp.domain.Module2MenuItem;

/**
 * Spring Data JPA repository for the Module2MenuItem entity.
 */
public interface Module2MenuItemRepository extends JpaRepository<Module2MenuItem,Long> {
	
	List<Module2MenuItem> findByModule(Module module);

}

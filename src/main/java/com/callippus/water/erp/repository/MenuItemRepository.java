package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MenuItem;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MenuItem entity.
 */
public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

}

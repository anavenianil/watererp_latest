package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ItemRequired;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ItemRequired entity.
 */
public interface ItemRequiredRepository extends JpaRepository<ItemRequired,Long> {

}

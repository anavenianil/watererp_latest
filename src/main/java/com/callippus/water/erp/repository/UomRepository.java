package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Uom;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Uom entity.
 */
public interface UomRepository extends JpaRepository<Uom,Long> {

}

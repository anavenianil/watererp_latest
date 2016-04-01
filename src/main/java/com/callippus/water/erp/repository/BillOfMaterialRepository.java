package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillOfMaterial;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BillOfMaterial entity.
 */
public interface BillOfMaterialRepository extends JpaRepository<BillOfMaterial,Long> {

}

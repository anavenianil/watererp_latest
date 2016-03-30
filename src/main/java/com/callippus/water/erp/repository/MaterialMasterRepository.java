package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MaterialMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MaterialMaster entity.
 */
public interface MaterialMasterRepository extends JpaRepository<MaterialMaster,Long> {

}

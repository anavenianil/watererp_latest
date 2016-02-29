package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CtegoryMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CtegoryMaster entity.
 */
public interface CtegoryMasterRepository extends JpaRepository<CtegoryMaster,Long> {

}

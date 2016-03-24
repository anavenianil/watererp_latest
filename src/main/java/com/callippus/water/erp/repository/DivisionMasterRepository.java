package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DivisionMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DivisionMaster entity.
 */
public interface DivisionMasterRepository extends JpaRepository<DivisionMaster,Long> {

}

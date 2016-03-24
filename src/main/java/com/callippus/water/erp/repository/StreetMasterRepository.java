package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.StreetMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StreetMaster entity.
 */
public interface StreetMasterRepository extends JpaRepository<StreetMaster,Long> {

}

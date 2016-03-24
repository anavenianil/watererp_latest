package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ZoneMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ZoneMaster entity.
 */
public interface ZoneMasterRepository extends JpaRepository<ZoneMaster,Long> {

}

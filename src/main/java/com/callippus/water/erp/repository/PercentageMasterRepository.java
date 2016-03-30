package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.PercentageMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PercentageMaster entity.
 */
public interface PercentageMasterRepository extends JpaRepository<PercentageMaster,Long> {

}

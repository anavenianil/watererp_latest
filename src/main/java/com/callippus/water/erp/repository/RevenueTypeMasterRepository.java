package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.RevenueTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RevenueTypeMaster entity.
 */
public interface RevenueTypeMasterRepository extends JpaRepository<RevenueTypeMaster,Long> {

}

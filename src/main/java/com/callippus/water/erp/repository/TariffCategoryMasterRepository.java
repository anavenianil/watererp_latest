package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffCategoryMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TariffCategoryMaster entity.
 */
public interface TariffCategoryMasterRepository extends JpaRepository<TariffCategoryMaster,Long> {

}

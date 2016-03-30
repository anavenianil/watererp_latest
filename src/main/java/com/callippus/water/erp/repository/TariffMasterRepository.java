package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TariffMaster entity.
 */
public interface TariffMasterRepository extends JpaRepository<TariffMaster,Long> {

}

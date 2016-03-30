package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TariffTypeMaster entity.
 */
public interface TariffTypeMasterRepository extends JpaRepository<TariffTypeMaster,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TariffCharges;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TariffCharges entity.
 */
public interface TariffChargesRepository extends JpaRepository<TariffCharges,Long> {

}

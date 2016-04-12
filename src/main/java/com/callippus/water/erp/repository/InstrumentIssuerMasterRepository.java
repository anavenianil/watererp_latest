package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.InstrumentIssuerMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the InstrumentIssuerMaster entity.
 */
public interface InstrumentIssuerMasterRepository extends JpaRepository<InstrumentIssuerMaster,Long> {

}

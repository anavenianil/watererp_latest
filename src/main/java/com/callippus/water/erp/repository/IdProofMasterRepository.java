package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.IdProofMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the IdProofMaster entity.
 */
public interface IdProofMasterRepository extends JpaRepository<IdProofMaster,Long> {

}

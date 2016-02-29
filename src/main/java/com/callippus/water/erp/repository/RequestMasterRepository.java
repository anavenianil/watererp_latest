package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.RequestMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the RequestMaster entity.
 */
public interface RequestMasterRepository extends JpaRepository<RequestMaster,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ConnectionTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ConnectionTypeMaster entity.
 */
public interface ConnectionTypeMasterRepository extends JpaRepository<ConnectionTypeMaster,Long> {

}

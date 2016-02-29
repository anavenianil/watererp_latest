package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ApplicationTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ApplicationTypeMaster entity.
 */
public interface ApplicationTypeMasterRepository extends JpaRepository<ApplicationTypeMaster,Long> {

}

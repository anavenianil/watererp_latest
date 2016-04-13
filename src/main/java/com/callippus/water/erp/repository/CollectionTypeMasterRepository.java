package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CollectionTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CollectionTypeMaster entity.
 */
public interface CollectionTypeMasterRepository extends JpaRepository<CollectionTypeMaster,Long> {

}

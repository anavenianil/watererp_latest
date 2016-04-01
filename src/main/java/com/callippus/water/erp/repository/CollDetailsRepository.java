package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CollDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CollDetails entity.
 */
public interface CollDetailsRepository extends JpaRepository<CollDetails,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ConfigurationDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ConfigurationDetails entity.
 */
public interface ConfigurationDetailsRepository extends JpaRepository<ConfigurationDetails,Long> {

}

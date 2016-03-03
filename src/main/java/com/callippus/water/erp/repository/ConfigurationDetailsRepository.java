package com.callippus.water.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ConfigurationDetails;

/**
 * Spring Data JPA repository for the ConfigurationDetails entity.
 */
public interface ConfigurationDetailsRepository extends JpaRepository<ConfigurationDetails,Long> {
	
	ConfigurationDetails findOneByName(String name);

}

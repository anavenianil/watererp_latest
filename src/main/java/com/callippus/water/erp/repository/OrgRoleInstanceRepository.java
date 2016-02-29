package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OrgRoleInstance;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrgRoleInstance entity.
 */
public interface OrgRoleInstanceRepository extends JpaRepository<OrgRoleInstance,Long> {

}

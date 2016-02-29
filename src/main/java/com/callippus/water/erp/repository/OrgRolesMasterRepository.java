package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OrgRolesMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OrgRolesMaster entity.
 */
public interface OrgRolesMasterRepository extends JpaRepository<OrgRolesMaster,Long> {

}

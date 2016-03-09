package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Url2Role;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Url2Role entity.
 */
public interface Url2RoleRepository extends JpaRepository<Url2Role,Long> {

}

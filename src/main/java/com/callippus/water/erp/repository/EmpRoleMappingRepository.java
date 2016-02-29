package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.EmpRoleMapping;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the EmpRoleMapping entity.
 */
public interface EmpRoleMappingRepository extends JpaRepository<EmpRoleMapping,Long> {

    @Query("select empRoleMapping from EmpRoleMapping empRoleMapping where empRoleMapping.user.login = ?#{principal.username}")
    List<EmpRoleMapping> findByUserIsCurrentUser();

    @Query("select empRoleMapping from EmpRoleMapping empRoleMapping where empRoleMapping.parentUser.login = ?#{principal.username}")
    List<EmpRoleMapping> findByParentUserIsCurrentUser();

}

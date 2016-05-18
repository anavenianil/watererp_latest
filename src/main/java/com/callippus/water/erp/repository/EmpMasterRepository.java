package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.EmpMaster;
import com.callippus.water.erp.domain.OrgRoleInstance;

/**
 * Spring Data JPA repository for the EmpMaster entity.
 */
public interface EmpMasterRepository extends JpaRepository<EmpMaster,Long> {

	@Query("select empMaster from EmpMaster empMaster where empMaster.user.login = ?#{principal.username}")
	List<EmpMaster> findByUserIsCurrentUser();

	@Query("select empMaster.officeId from EmpMaster empMaster where empMaster.user.login = ?#{principal.username}")
	// @Query("select ori.orgRoleName from OrgRoleInstance ori where ori.id=(select em.officeId from EmpMaster em where em.user.id=(select u.id from User u where u.login=?#{principal.username}))")
	OrgRoleInstance findOneOfficeId();
	

	@Query("select empMaster.officeId from EmpMaster empMaster where empMaster.statusMaster.id=2 and empMaster.user.id = :userId")
	OrgRoleInstance findActiveOfficeId(@Param("userId") Long userId);

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.EmpMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the EmpMaster entity.
 */
public interface EmpMasterRepository extends JpaRepository<EmpMaster,Long> {

    @Query("select empMaster from EmpMaster empMaster where empMaster.user.login = ?#{principal.username}")
    List<EmpMaster> findByUserIsCurrentUser();

}

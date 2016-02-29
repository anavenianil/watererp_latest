package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.GroupMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the GroupMaster entity.
 */
public interface GroupMasterRepository extends JpaRepository<GroupMaster,Long> {

}

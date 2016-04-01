package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ComplaintTypeMaster;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ComplaintTypeMaster entity.
 */
public interface ComplaintTypeMasterRepository extends JpaRepository<ComplaintTypeMaster,Long> {

}

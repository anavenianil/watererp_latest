package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ApprovalDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ApprovalDetails entity.
 */
public interface ApprovalDetailsRepository extends JpaRepository<ApprovalDetails,Long> {

}

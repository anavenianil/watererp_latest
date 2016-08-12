package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.JobCardSiteStatus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the JobCardSiteStatus entity.
 */
public interface JobCardSiteStatusRepository extends JpaRepository<JobCardSiteStatus,Long> {

}

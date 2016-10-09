package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.RequestMaster;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.WorkflowMaster;

/**
 * Spring Data JPA repository for the RequestWorkflowHistory entity.
 */
public interface RequestWorkflowHistoryRepository extends JpaRepository<RequestWorkflowHistory,Long> {

    @Query("select requestWorkflowHistory from RequestWorkflowHistory requestWorkflowHistory where requestWorkflowHistory.assignedFrom.login = ?#{principal.username}")
    List<RequestWorkflowHistory> findByAssignedFromIsCurrentUser();

    @Query("select requestWorkflowHistory from RequestWorkflowHistory requestWorkflowHistory where requestWorkflowHistory.assignedTo.login = ?#{principal.username}")
    List<RequestWorkflowHistory> findByAssignedToIsCurrentUser();

    @Query("select requestWorkflowHistory from RequestWorkflowHistory requestWorkflowHistory where requestWorkflowHistory.appliedBy.login = ?#{principal.username}")
    List<RequestWorkflowHistory> findByAppliedByIsCurrentUser();
    
    Page<RequestWorkflowHistory> findByDomainObjectAndRequestMaster(Pageable pageable, Long domainObject, RequestMaster requestMaster);
    
    Page<RequestWorkflowHistory> findByDomainObjectAndWorkflowMaster(Pageable pageable, Long domainObject, WorkflowMaster workflowMaster);
    
    
    @Query("Select rwh from RequestWorkflowHistory rwh where rwh.statusMaster.id=:statusMasterId and rwh.assignedTo.id=:assignedToId and rwh.requestMaster.id=:requestMasterId")
	List<RequestWorkflowHistory> findPendingList(@Param("statusMasterId") Long statusMasterId,
			@Param("assignedToId") Long assignedToId, @Param("requestMasterId") Long requestMasterId);
    
    
    RequestWorkflowHistory findTop1ByDomainObjectAndRequestMasterOrderByIdDesc(Long domainObject, RequestMaster requestMaster);
    /*List<RequestWorkflowHistory> findApprovedList(@Param("statuses") List<Long> statuses,
			@Param("assignedToId") Long assignedToId, @Param("requestMasterId") Long requestMasterId);*/

}

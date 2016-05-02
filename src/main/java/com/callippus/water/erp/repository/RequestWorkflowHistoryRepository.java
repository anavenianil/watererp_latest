package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.callippus.water.erp.domain.RequestMaster;
import com.callippus.water.erp.domain.RequestWorkflowHistory;

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

}

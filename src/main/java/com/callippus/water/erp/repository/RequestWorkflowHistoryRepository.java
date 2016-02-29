package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.RequestWorkflowHistory;

import org.springframework.data.jpa.repository.*;

import java.util.List;

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

}

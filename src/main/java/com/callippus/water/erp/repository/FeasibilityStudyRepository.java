package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.FeasibilityStudy;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the FeasibilityStudy entity.
 */
public interface FeasibilityStudyRepository extends JpaRepository<FeasibilityStudy,Long> {

    @Query("select feasibilityStudy from FeasibilityStudy feasibilityStudy where feasibilityStudy.preparedBy.login = ?#{principal.username}")
    List<FeasibilityStudy> findByPreparedByIsCurrentUser();

    @Query("select feasibilityStudy from FeasibilityStudy feasibilityStudy where feasibilityStudy.approvedByZonalHead.login = ?#{principal.username}")
    List<FeasibilityStudy> findByApprovedByZonalHeadIsCurrentUser();

    @Query("select feasibilityStudy from FeasibilityStudy feasibilityStudy where feasibilityStudy.inspectionByDepartmentHead.login = ?#{principal.username}")
    List<FeasibilityStudy> findByInspectionByDepartmentHeadIsCurrentUser();

    @Query("select feasibilityStudy from FeasibilityStudy feasibilityStudy where feasibilityStudy.approvedByOperationManager.login = ?#{principal.username}")
    List<FeasibilityStudy> findByApprovedByOperationManagerIsCurrentUser();

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Adjustments;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.enumeration.TxnStatus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Adjustments entity.
 */
public interface AdjustmentsRepository extends JpaRepository<Adjustments,Long> {

    @Query("select adjustments from Adjustments adjustments where adjustments.user.login = ?#{principal.username}")
    List<Adjustments> findByUserIsCurrentUser();

    List<Adjustments> findByCanAndStatusAndBillFullDetails(String can, TxnStatus status, BillFullDetails bfd);
}

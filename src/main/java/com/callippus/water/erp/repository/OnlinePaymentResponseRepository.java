package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.OnlinePaymentResponse;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the OnlinePaymentResponse entity.
 */
public interface OnlinePaymentResponseRepository extends JpaRepository<OnlinePaymentResponse,Long> {

	@Query("SELECT opr FROM OnlinePaymentResponse opr WHERE opr.onlinePaymentOrder.id=:orderId")
	public OnlinePaymentResponse findByOrder(@Param("orderId") Long orderId);
}

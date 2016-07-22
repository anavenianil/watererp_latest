package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.OnlinePaymentCallback;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.domain.OnlinePaymentResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the OnlinePaymentCallback entity.
 */
public interface OnlinePaymentCallbackRepository extends JpaRepository<OnlinePaymentCallback,Long> {
	public OnlinePaymentCallback findByMerchantTxnRef(String merchantTxnRef);
	
	@Query("SELECT opc FROM OnlinePaymentCallback opc WHERE opc.onlinePaymentOrder.id=:id")
	public OnlinePaymentCallback findByOnlinePaymentOrderId(@Param("id") Long id); 
	
}

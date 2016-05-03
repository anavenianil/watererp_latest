package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OnlinePaymentCallback;
import com.callippus.water.erp.domain.OnlinePaymentResponse;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OnlinePaymentCallback entity.
 */
public interface OnlinePaymentCallbackRepository extends JpaRepository<OnlinePaymentCallback,Long> {
	public OnlinePaymentCallback findByMerchantTxnRef(String merchantTxnRef);
}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OnlinePaymentResponse;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OnlinePaymentResponse entity.
 */
public interface OnlinePaymentResponseRepository extends JpaRepository<OnlinePaymentResponse,Long> {

}

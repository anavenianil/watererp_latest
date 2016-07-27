package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.OnlinePaymentOrder;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the OnlinePaymentOrder entity.
 */
public interface OnlinePaymentOrderRepository extends JpaRepository<OnlinePaymentOrder,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.PaymentTypes;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PaymentTypes entity.
 */
public interface PaymentTypesRepository extends JpaRepository<PaymentTypes,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ChargeBase;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ChargeBase entity.
 */
public interface ChargeBaseRepository extends JpaRepository<ChargeBase,Long> {

}

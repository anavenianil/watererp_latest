package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.DocketCode;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DocketCode entity.
 */
public interface DocketCodeRepository extends JpaRepository<DocketCode,Long> {

}

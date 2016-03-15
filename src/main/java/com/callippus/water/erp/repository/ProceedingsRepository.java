package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Proceedings;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Proceedings entity.
 */
public interface ProceedingsRepository extends JpaRepository<Proceedings,Long> {

}

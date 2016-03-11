package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MakeOfPipe;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the MakeOfPipe entity.
 */
public interface MakeOfPipeRepository extends JpaRepository<MakeOfPipe,Long> {

}

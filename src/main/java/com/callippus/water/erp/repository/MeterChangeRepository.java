package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.MeterChange;
import com.callippus.water.erp.domain.MeterDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the MeterChange entity.
 */
public interface MeterChangeRepository extends JpaRepository<MeterChange,Long> {

    @Query("select meterChange from MeterChange meterChange where meterChange.user.login = ?#{principal.username}")
    List<MeterChange> findByUserIsCurrentUser();

    MeterChange findByCanAndNewMeterNo(String can, MeterDetails newMeterNo);
    
    MeterChange findByCanAndStatus(@Param("can")  String can, @Param("status")  Integer status);
}

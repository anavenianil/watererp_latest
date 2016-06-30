package com.callippus.water.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.callippus.water.erp.domain.MeterChange;
import com.callippus.water.erp.domain.MeterDetails;
import com.callippus.water.erp.domain.enumeration.MeterChangeStatus;

/**
 * Spring Data JPA repository for the MeterChange entity.
 */
public interface MeterChangeRepository extends JpaRepository<MeterChange,Long> {

    @Query("select meterChange from MeterChange meterChange where meterChange.user.login = ?#{principal.username}")
    List<MeterChange> findByUserIsCurrentUser();

    MeterChange findByCanAndNewMeterNo(String can, MeterDetails newMeterNo);
    
    MeterChange findByCanAndStatus(String can, MeterChangeStatus status);
    
    
    /*@Query("select mc from MeterChange mc where mc.status not in(0,1) and mc.id=(select max(m.id) from MeterChange m "
    		+ "where m.can=(select cd.can from CustDetails cd where cd.status='ACTIVE' and cd.can=:can))")*/
   /* @Query("select cd from CustDetails cd where cd.status = 'ACTIVE'and cd.can=(select m.can from MeterChange m "
    		+ "where m.status not in (0,1) and m.id=(select max(mc.id) from MeterChange mc where mc.can=:can))")*/
    @Query("select m from MeterChange m where m.can=:can and status !='BILLED'")
    MeterChange findPending(@Param("can") String can);
}

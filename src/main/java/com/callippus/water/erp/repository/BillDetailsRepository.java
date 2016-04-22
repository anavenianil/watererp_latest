package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BillDetails entity.
 */
public interface BillDetailsRepository extends JpaRepository<BillDetails,Long> {

    @Query("select billDetails from BillDetails billDetails where billDetails.mtrReader.login = ?#{principal.username}")
    List<BillDetails> findByUserIsCurrentUser();
    
    public BillDetails findByCan(String can);

}

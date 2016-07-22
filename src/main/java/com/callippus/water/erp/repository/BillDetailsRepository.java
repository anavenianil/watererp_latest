package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.BillDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Spring Data JPA repository for the BillDetails entity.
 */
public interface BillDetailsRepository extends JpaRepository<BillDetails,Long> {

    @Query("select billDetails from BillDetails billDetails where billDetails.mtrReader.login = ?#{principal.username}")
    List<BillDetails> findByMtrReaderIsCurrentUser();
    
    @Query("select bd from BillDetails bd where bd.status='INITIATED' and bd.can=:can")
    public BillDetails findValidBillForCan(@Param("can") String can);
    
    @Query("select bd from BillDetails bd where bd.status='COMMITTED' and bd.can=:can and bd.toMonth=:toMonth")
    public BillDetails findValidBillForCanAndMonth(@Param("can") String can, @Param("toMonth") String toMonth);
    
    @Query("select bd from BillDetails bd where bd.status='INITIATED'")
    public List<BillDetails>  findAllInitiated();
}

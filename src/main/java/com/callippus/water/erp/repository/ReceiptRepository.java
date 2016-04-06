package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Receipt;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Receipt entity.
 */
public interface ReceiptRepository extends JpaRepository<Receipt,Long> {

}

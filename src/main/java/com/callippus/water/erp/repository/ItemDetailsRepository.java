package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ItemDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ItemDetails entity.
 */
public interface ItemDetailsRepository extends JpaRepository<ItemDetails,Long> {

}

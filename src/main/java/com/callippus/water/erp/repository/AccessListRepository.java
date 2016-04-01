package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.AccessList;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the AccessList entity.
 */
public interface AccessListRepository extends JpaRepository<AccessList,Long> {

}

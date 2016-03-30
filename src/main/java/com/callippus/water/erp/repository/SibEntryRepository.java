package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.SibEntry;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the SibEntry entity.
 */
public interface SibEntryRepository extends JpaRepository<SibEntry,Long> {

}

package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Url;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Url entity.
 */
public interface UrlRepository extends JpaRepository<Url,Long> {

}

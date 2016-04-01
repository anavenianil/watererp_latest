package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.Terminal;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Terminal entity.
 */
public interface TerminalRepository extends JpaRepository<Terminal,Long> {

}

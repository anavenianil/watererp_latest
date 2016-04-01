package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.TerminalLog;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TerminalLog entity.
 */
public interface TerminalLogRepository extends JpaRepository<TerminalLog,Long> {

}

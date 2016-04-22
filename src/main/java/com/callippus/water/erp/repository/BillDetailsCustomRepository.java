package com.callippus.water.erp.repository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.BillDetails;



public interface BillDetailsCustomRepository extends JpaRepository<BillDetails,Long>{

	public JasperPrint billDetailReports(Long id, Long testBatchId) throws JRException;
	
}


	
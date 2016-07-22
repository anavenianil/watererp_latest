package com.callippus.water.erp.repository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillAndCollectionDTO;


public interface BillDetailsCustomRepository extends JpaRepository<BillDetails,Long>{

	public JasperPrint billDetailReports(Long id, Long testBatchId) throws JRException;
	
	public List<BillAndCollectionDTO> getBillCollection(String can);	
}


	
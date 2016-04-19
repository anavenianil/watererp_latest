package com.callippus.water.erp.repository;

import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;

public interface ApplicationTxnCustomRepository extends
JpaRepository<ApplicationTxn, Long> {
	
	List<RequestCountDTO> countPendingRequests()throws Exception ;
	List<RequestCountDTO> countApprovedRequests()throws Exception ;
	//List<RequestCountDTO> countMyRequests()throws Exception ;
	
	List<RequestWorkflowHistory> listAllPendingRequests(String type) throws Exception;
	List<RequestWorkflowHistory> listAllApprovedRequests(String type) throws Exception;
	

	List<ApplicationTxn> search(String whereClause);
	

	public JasperPrint applicationTxnReports(Long id) throws JRException;

}

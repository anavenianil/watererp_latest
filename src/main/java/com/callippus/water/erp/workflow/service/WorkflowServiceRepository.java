package com.callippus.water.erp.workflow.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.callippus.water.erp.domain.ApplicationTxn;




	public interface WorkflowServiceRepository extends
			JpaRepository<ApplicationTxn, Long> {
	
		String getUserID(String login) throws Exception;
		String getLoginID(String userID) throws Exception;
		String getOfficeID(String userID) throws Exception;
	

	}

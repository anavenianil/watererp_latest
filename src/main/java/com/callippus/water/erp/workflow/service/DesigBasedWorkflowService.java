package com.callippus.water.erp.workflow.service;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.common.CPSConstants;

@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class DesigBasedWorkflowService {// extends WorkflowService{
	private static Log log = LogFactory.getLog(DesigBasedWorkflowService.class);
	
	@Inject
	private WorkflowService workfowService;
	
	
	/**
	 * This method is for finding the designation based workflow id,stage id,requester parent id for this request.
	 * 
	 * @param rb
	 * @return workflow id
	 */
	public void initWorkflow() throws Exception {
		log.debug("::<<<DesigBasedWorkflowService<<<<<<<<Method>>>>>>>>>>>>>>>initWorkflow()>>>>>>>>>");
		try {
			workfowService.setWorkflowType(CPSConstants.DESIG);
			workfowService.setWorkflowID(workfowService.decideWorkFlow());
			workfowService.setStageID("1");
			workfowService.getWorkflowAssignedSfid();
		} catch (Exception e) {
			throw e;
		}
	}

}

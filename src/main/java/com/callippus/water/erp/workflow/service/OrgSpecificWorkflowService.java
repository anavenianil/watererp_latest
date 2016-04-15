package com.callippus.water.erp.workflow.service;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.common.CPSUtils;

@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class OrgSpecificWorkflowService {//extends WorkflowService{
	private static Log log = LogFactory.getLog(OrgSpecificWorkflowService.class);
	
	@Inject
	private WorkflowService workflowService;
	
	/**
	 * This method is for finding the organization specific workflow id,stage id,requester parent id for this request.
	 * 
	 * @param rb
	 * @return workflow id
	 */
	public WorkflowService initWorkflow() throws Exception {
		log.debug("::<<<OrgSpecificWorkflowServiceMethod>>>initWorkflow()>");
		
		try {
			workflowService.setWorkflowType(CPSConstants.ORGSPECIFIC);
			workflowService.setWorkflowID(workflowService.decideWorkFlow());
			workflowService.getWorkflowID();
			if(CPSUtils.isNullOrEmpty(workflowService.getStageID())){
			workflowService.setStageID("1");}
			String parentId=workflowService.getWorkflowAssignedSfid();
			workflowService.setParentID(parentId);
		} catch (Exception e) {
			throw e;
		}
		return workflowService;
	}

}

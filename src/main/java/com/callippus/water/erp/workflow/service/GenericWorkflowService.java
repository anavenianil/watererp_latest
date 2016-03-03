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
public class GenericWorkflowService {//extends WorkflowService {
    private static Log log = LogFactory.getLog(GenericWorkflowService.class);
    
    @Inject
    private WorkflowService workflowService;

    /**
     * This method is for finding the generic workflow id,stage id,requester
     * parent id for this request.
     * 
     * @param rb
     * @return workflow id
     * */
     
    public void initWorkflow() throws Exception {
    	log.debug(" initWorkflow: {}");
        try {
        	workflowService.setWorkflowType(CPSConstants.GENERIC);
        	if(CPSUtils.isNullOrEmpty(workflowService.getStageID())){
        	workflowService.setStageID("1");}
        	workflowService.setWorkflowID(workflowService.decideWorkFlow(workflowService.getRequestTypeID(), null, null));
        	workflowService.setParentID(workflowService.getWorkflowAssignedSfid());
        } catch (Exception e) {
            throw new Exception();
        }
    
}
}

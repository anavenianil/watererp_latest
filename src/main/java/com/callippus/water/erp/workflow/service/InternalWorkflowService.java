package com.callippus.water.erp.workflow.service;


import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class InternalWorkflowService {// extends WorkflowService {
    private static Log log = LogFactory.getLog(InternalWorkflowService.class);
    
    @Inject
    private WorkflowService workflowService;
      
	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;


    /**
     * This method is for finding the parent id of that requester
     * 
     * @param rb
     * @return
     */
    public String getInternalDivisionWorkFlow() throws Exception{
    	log.debug("com.callippus.water.erp.workflow.service.InternalWorkflowService : getInternalDivisionWorkFlow(): ");
    	
    	String parentID = null;
           /**
             * This internal workflow is only for sub subordinates otherwise
             * this query will return null (Immediate subordinates & instance head should not follow internal workflow )
             */
        	
        	String getParentIDQuery = "select erm1.user_id from emp_role_mapping erm1, emp_role_mapping erm3, (select erm.user_id, emp.office_id,"
        			+ " erm.parent_user_id from emp_role_mapping erm, emp_master emp where erm.status_master_id=2 and erm.user_id= "+ workflowService.getSfID() +" "
        			+ " and emp.status_master_id=2 and emp.user_id=erm.user_id) erm2 where erm1.status_master_id=2 and erm1.user_id=erm2.parent_user_id"
        			+ " and erm3.status_master_id=2 and erm3.user_id=erm1.parent_user_id and erm3.org_role_instance_id=erm2.office_id and erm3.parent_user_id"
        			+ " is null";
        	try{
        		Integer id = jdbcTemplate.queryForObject(getParentIDQuery, Integer.class);
        		parentID = workflowService.getLoginID(id.toString());
        		
        	}
        	catch(Exception e){
        		parentID = null;
        	}
            return parentID;
    }
}

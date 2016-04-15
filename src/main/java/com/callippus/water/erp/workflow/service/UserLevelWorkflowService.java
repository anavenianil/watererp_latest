 package com.callippus.water.erp.workflow.service;


import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.common.CPSUtils;

@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class UserLevelWorkflowService {// extends WorkflowService {
    private static Log log = LogFactory.getLog(UserLevelWorkflowService.class);

    @Inject
    private WorkflowService workflowService;
	
	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;
    /*
     * This method will return the auto delegate sfid
     * 
     * @param sfid
     * @return
     * @throws Exception
     **/
     
    public void checkUserAutoDelegate() throws Exception {
    	log.debug("::UserLevelWorkflowService<<MethodcheckUserAutoDelegate()");
        String requesterType = null;
        String updateNoOfRequests = null;
        String sfid = null;
        String parentID =null;
        //setHistoryID("121");
        
        if(CPSUtils.isNull(workflowService.getRequester())) {
			String getGazType = "select dm.type, emp.user_id, emp.designation_master_id from emp_master emp, designation_mappings dm "
					+ "where emp.status_master_id=2 and emp.user_id=(select assigned_from_id from request_workflow_history where id=("
					+ "select min(id) from request_workflow_history where request_master_id="
					+ workflowService.getRequestID()
					+ ")) and emp.designation_id=dm.desig_id";
			
			List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(getGazType);
            if(rows.size() > 0){
            	HashMap<String, Object> hm = (HashMap<String, Object>)rows.get(0);
            	workflowService.setRequesterType(hm.get("type").toString());
            	workflowService.setRequester(hm.get("user_id").toString());
            	workflowService.setDesignation(hm.get("designation_master_id").toString());
            }else{
            	String s1="select dm.type, emp.user_id, emp.designation_master_id from emp_master emp, designation_mappings dm "
    					+ "where emp.status_master_id=2 and emp.user_id="+workflowService.getSfID()+" and emp.designation_master_id=dm.id";
    			
    			List<java.util.Map<String, Object>> rows1 = jdbcTemplate.queryForList(getGazType);
                if(rows1.size() > 0){
                	HashMap<String, Object> hm = (HashMap<String, Object>)rows.get(0);
                	workflowService.setRequesterType(hm.get("type").toString());
                	workflowService.setRequester(hm.get("user_id").toString());
                	workflowService.setDesignation(hm.get("designation_master_id").toString());
                }
            }
		}
	            
            sfid = workflowService.getSfID();
			parentID = workflowService.getParentID();

			checkUserNormalConfiguration(CPSConstants.AUTOMATIC);
			
			// check whether the requester & auto delegated sfid is same or not, if same we should not assign the request to the same person
			
			if (CPSUtils.compareStrings(workflowService.getParentID(), workflowService.getRequester())) {
				// same person
				workflowService.setSfID(sfid);
				workflowService.setParentID(parentID);
				workflowService.setRequestCount(null);
				checkUserNormalConfiguration(CPSConstants.ONLEAVE);
			}
			
			
			if (CPSUtils.compareStrings(parentID, workflowService.getParentID())) {
				checkUserTreeConfiguration(CPSConstants.AUTOMATIC);
				// check whether the requester & auto delegated sfid is same or not, if same we should not assign the request to the same person
				if (CPSUtils.compareStrings(workflowService.getParentID(), workflowService.getRequester())) {
					// same person
					workflowService.setSfID(sfid);
					workflowService.setParentID(parentID);
					workflowService.setRequestCount(null);
					checkUserTreeConfiguration(CPSConstants.ONLEAVE);
				}
			}
			log.debug("::Delegated from " + workflowService.getSfID() + " to " + workflowService.getParentID());
			
			if (!CPSUtils.isNullOrEmpty(workflowService.getRequestCount())) {
				// update the count
				//String updatecount = "update t_user_specific_configuration set no_of_requests=(select no_of_requests+1 from t_user_specific_configuration where id= "
					//	+ getRequestCount()+") where id="+getRequestCount();
				String updatecount = "update user_specific_configuration as usc, (select * from user_specific_configuration where id="+workflowService.getRequestCount()
						+") as usc1 set usc.no_of_requests= usc.no_of_requests+1 where usc.id=usc1.id";
				int res = jdbcTemplate.update(updatecount);
				
			} 
    }

    public void checkUserNormalConfiguration( String assignType) throws Exception {
    	log.debug("::UserLevelWorkflowService<<MethodcheckUserNormalConfiguration(String assignType)");
		
		String getDelegatedID = "select id,delegate,gazetted_type,delegation_type,case when delegation_type="
				+ CPSConstants.STATUSINSTANCE
				+ " then delegate||'' else null end roleID from (select usc.id,usc.delegate,usc.gazetted_type,usc.delegation_type from "
				+ "user_specific_configuration usc, status_master sm where usc.org_role_id="
				+ workflowService.getRoleID()
				+ "and usc.status=1 and sm.id=usc.assigned_type and "
				+ "upper(sm.status)=upper("
				+ assignType
				+ ") and usc.request_type_id="
				+ workflowService.getRequestTypeID()
				+ "and (upper(usc.gazetted_type)=upper("
				+ workflowService.getRequesterType()
				+ ") or usc.gazetted_type is null) and (usc.designation_id="
				+ workflowService.getDesignation()
				+ " or usc.designation_id=0) order by usc.no_of_requests) where rownum=1";

    	List<java.util.Map<String,Object>> rows = jdbcTemplate.queryForList(getDelegatedID);
    	
    	if(rows.size()>0){
    		HashMap<String, Object> hm = (HashMap<String, Object>)rows.get(0);
    		if (CPSUtils.isNullOrEmpty(hm.get("gazetted_type").toString()) || CPSUtils.compareStrings(workflowService.getRequesterType(), hm.get("gazetted_type").toString())) {
    			workflowService.setSfID(workflowService.getParentID());
    			if (CPSUtils.compareStrings(hm.get("delegation_type").toString(), CPSConstants.STATUSINSTANCE)) {
					// INSTANCE
    				workflowService.setParentID(getHead(hm.get("delegate").toString()));
				} else {
					workflowService.setParentID(hm.get("delegate").toString());
				}
    			workflowService.setRequestCount(hm.get("id").toString());
    			workflowService.setRoleID(hm.get("roleID").toString());
    		}
    	}
    }
			
    
    public void checkUserTreeConfiguration(String assignType) throws Exception {
    	log.debug("::UserLevelWorkflowService<<MethodcheckUserTreeConfiguration(String assignType)");
		
		/*String getDelegatedID = "select id,delegate, gazetted_type, delegation_type, case when delegation_type="+CPSConstants.STATUSINSTANCE+" then delegate||'' "
				+ "else null end roleID from (select id,delegate,gazetted_type,delegation_type from t_user_specific_configuration where id=(select case when "
				+ "(select id from org_role_instance where status_master_id=2 and id="+getRoleID()+" start with id=(select office_id from t_emp_master "
				+ "where user_id="+getSfID()+" and status_master_id=2) connect by parent_org_role_id = prior id) is not null then (select usc.id from t_user_specific_configuration usc,status_master sm "
				+ "where usc.org_role_instance_id="+getRoleID()+" and usc.status_master_id=2 and usc.assigned_type=sm.id "
				+ "and upper(sm.status)=upper("+assignType+") and usc.request_master_id="+getRequestTypeID()+" and (upper(usc.gazetted_type)=upper("
				+getRequesterType()+") or usc.gazetted_type is null) and (usc.designation_id="+getDesignation()+" or usc.designation_id=0) and rownum=1) else "
				+ "(select case when (select id from t_org_role_instance where status_master_id=2 and id="+getRoleID()
				+ "start with id=(select office_id from emp_master where user_id="+getSfID()+" and status_master_id=2) connect by id = prior parent_org_role_id) "
				+ "is not null then (select usc.id from t_user_specific_configuration usc,t_status_master sm where usc.org_role_instance_id="+getRoleID()
				+ " and usc.status_master_id=2 and usc.assigned_type=sm.id and upper(sm.status)=upper("+assignType+") and usc.request_master_id="+getRequestTypeID()+" and "
				+ "(upper(usc.gazetted_type)=upper("+getRequesterType()+") or usc.gazetted_type is null) and (usc.designation_id="+getDesignation()+" or usc.designation_id=0) and rownum=1) else null end from dual ) "
				+ "end from dual) order by no_of_requests) where rownum=1";
		
		log.debug("::SQL : getDelegatedID > > " );
		List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(getDelegatedID);
		
		if(rows.size() > 0){
			
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			if (CPSUtils.isNullOrEmpty(hm.get("gazetted_type").toString()) || CPSUtils.compareStrings(getRequesterType(), hm.get("gazetted_type").toString())) {
				setSfID(getParentID());
				if (CPSUtils.compareStrings(hm.get("delegation_type").toString(), CPSConstants.STATUSINSTANCE)) {
					// INSTANCE
					setParentID(getHead(hm.get("delegate").toString()));
				} else {
					setParentID(hm.get("delegate").toString());
				}
				setRequestCount(hm.get("id").toString());
				setRoleID(hm.get("roleID").toString());
			}
		}*/
	}
    
    public String getHead(String roleInstanceID) throws Exception {
        String parentID = null;
        String result=null;
        
            String getParentSFID = "select user_id from emp_role_mapping where status_master_id=2 and org_role_instance_id="+ roleInstanceID;
            
            List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(getParentSFID);
            if(rows.size() > 0){
            	HashMap<String, Object>  hm = (HashMap<String, Object>) rows.get(0);
            	result =hm.get("user_id").toString();
            }
            
        return parentID;
    }
}

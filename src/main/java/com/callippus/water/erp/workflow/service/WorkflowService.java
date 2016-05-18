package com.callippus.water.erp.workflow.service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.EmpMaster;
import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.User;
import com.callippus.water.erp.domain.WorkflowTxnDetails;
import com.callippus.water.erp.repository.EmpMasterRepository;
import com.callippus.water.erp.repository.OrgRoleInstanceRepository;
import com.callippus.water.erp.repository.RequestMasterRepository;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.repository.WorkflowMasterRepository;
import com.callippus.water.erp.repository.WorkflowStageMasterRepository;
import com.callippus.water.erp.security.SecurityUtils;

@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class WorkflowService {
	private final Logger log = LoggerFactory.getLogger(WorkflowService.class);

	@Inject
	WorkflowStageMasterRepository workflowStageMasterRepository;

	@Inject
	StatusMasterRepository statusMasterRepository;

	@Inject
	RequestMasterRepository requestMasterRepository;

	@Inject
	WorkflowMasterRepository workflowMasterRepository;

	@Inject
	EntityManager entityManager;

	@Inject
	UserRepository userRepository;

	@Inject
	private OrgRoleInstanceRepository orgRoleInstanceRepository;

	@Inject
	private EmpMasterRepository empMasterRepository;
	
	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;
	
	private String requesterType;
	private String requester;
	private String designation;
	private String organisationID;
	private String requestFrom;
	private String requesterRoleID;

	private String requestID; // request ID
	private String workflowID; // Workflow ID
	private String requestTypeID; // Request Type ID
	
	private int status;
	private String sfID; // Assigned from sfid
	private String requestType; // request type PIS/LEAVE
	private boolean requestInternalFlag; // request internal workflow flag
	private String parentID; // assigned to
	private String historyID; // history table unique id
	private String stageID; // workflow stage id
	private String assignedDate;
	private String actionedDate;
	private String ipAddress;
	private String remarks;
	private String message;
	private String workflowType;
	private ArrayList<RequestWorkflowHistory> workflowHistory;
	private String requesterOfficeID;
	private List<EmpMaster> empList;
	private List<OrgRoleInstance> instanceList;
	private String instanceId;
	private String param;
	private String type;
	private String name;
	private String requestIDs;
	private String changedValues;
	
	private String referenceNumber;
	
	private String prevsfID;
	private boolean flag;
	private String orgRoleID;
	private String roleID;
	private String requestCount;
	private String appliedBy;
	private String domain_object_id;
	
	private Integer requestStatus; // for requisition status
	
	private String requestAt;
	
	private Long requestWorkflowHistoryId;
	
	// =================Workflow Process=================

	

	public Long getRequestWorkflowHistoryId() {
		return requestWorkflowHistoryId;
	}

	public void setRequestWorkflowHistoryId(Long requestWorkflowHistoryId) {
		this.requestWorkflowHistoryId = requestWorkflowHistoryId;
	}

	public String getRequestAt() {
		return requestAt;
	}

	public void setRequestAt(String requestAt) {
		this.requestAt = requestAt;
	}

	public StatusMasterRepository getStatusMasterRepository() {
		return statusMasterRepository;
	}

	public String getDomain_object_id() {
		return domain_object_id;
	}

	public Integer getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(Integer requestStatus) {
		this.requestStatus = requestStatus;
	}

	public void setDomain_object_id(String domain_object_id) {
		this.domain_object_id = domain_object_id;
	}

	/**
	 * get userID
	 */
	public String getUserID(String login) throws Exception{
		log.debug("get userID: {}", login);
		Optional<User> user = userRepository.findOneByLogin(login);
		
		return user.get().getId().toString();
	}

	/**
	 * get Login
	 */
	public String getLoginID(String userID) throws Exception{
		log.debug("get LoginID: {}", userID);
		User user = userRepository.findById(Long.parseLong(userID));
		return user.getLogin();
	}

	
	/**
	 * Query that will list Hierarchy
	 */
	public String getHierarchy(String orgInstanceID) throws Exception{
		log.debug(" getHierarchy: {}", orgInstanceID);
		String user = orgInstanceID;
		String hierarchy = orgInstanceID;
		// while(user!="1")
		while (!user.equals("1")) {
			user = getParent(user);
			//hierarchy = user;
			hierarchy += "," + user;
		}
		return hierarchy;
	}

	/**
	 * Query that will get parent_org_role_id
	 */

	public String getParent(String office_id) throws Exception{
		log.debug(" getParent: {}", office_id);

		String sql = "select parent_org_role_id from  org_role_instance where status_master_id=2 and id="
				+ office_id;

		Integer o = jdbcTemplate.queryForObject(sql, Integer.class);

		return o.toString();
	}

	
	public String getHierarchyAtThirdLevel(String orgInstanceID) throws Exception{
		log.debug(" getHierarchyAtThirdLevel: {}", orgInstanceID);
		String user = orgInstanceID;
		String hierarchy = orgInstanceID;
		for (int level = 1; level < 3; level++) {
			if (user.equals("1")) {
				hierarchy = user;
			} else {
				user = getParent(user);
				hierarchy = user;
			}
		}
		return hierarchy;
	}

	/**
	 * Query that will get office Id of current login user from emp_master Table
	 */
	public String getOfficeID(String userID) throws Exception{
		log.debug(" getOfficeID: {}", userID);
//
//		String sql = "select office_id_id from  emp_master where status_master_id=2 and user_id="
//				+ userID;
//		
//		Integer o = jdbcTemplate.queryForObject(sql, Integer.class);
		
		Integer o = empMasterRepository.findActiveOfficeId(Long.parseLong(userID));
		
		if(o != null)
			return o.toString();
		else
			return null;
	}
	
	/**
	 * Query that will get directorate Id of current login user from emp_master Table
	 */
	public String getDirectorateID(String userID) throws Exception{
		log.debug(" getDirectorateID: {}", userID);

		String sql = "select directorate_id from  emp_master where status_master_id=2 and user_id="
				+ userID;

		Integer did = jdbcTemplate.queryForObject(sql, Integer.class);

		return did.toString();
	}
	
	
	/**
	 * Query that will give the designation ID of an employee from emp_master Table
	 */
	public String getDesignationID() throws Exception{
		log.debug("getDesignationID: {} userID:");
		String desigID = null;
		String sql = "select designation_master_id from emp_master where status_master_id=2 and user_id="+getSfID();
		try{
			Integer id = jdbcTemplate.queryForObject(sql, Integer.class);
			desigID = id.toString();
		}catch(Exception e){
			desigID = null;
		}
		return desigID;
	}

	
	
	/**
	 * This method will return the requester login ID from
	 * request_workflow_history
	 */
	public String getRequesterID(String domain_object_id) throws Exception{
		log.debug(" getRequesterID: {}", domain_object_id);

		String sql = "select assigned_from_id from request_workflow_history where id=(select min(id) from request_workflow_history "
				+ "where domain_object=" + domain_object_id + ")";

		Integer id = jdbcTemplate.queryForObject(sql, Integer.class);
		return getLoginID(id.toString());
	}

	/**
	 * This method will return the requester office ID
	 */
	public String getRequesterOfficeID(String requestID, String userID) throws Exception{
		log.debug(" getRequesterOfficeID: {}", requestID +", "+ userID);
		
		String requesterID = null;

		String requesterOfficeID = "select case when (select count(*) from request_workflow_history where id= "
				+ "(select min(id) from request_workflow_history where domain_object="
				+ requestID
				+ ")) !=0 then "
				+ "(select office_id_id from emp_master where user_id= (select assigned_from_id from request_workflow_history where id= "
				+ "(select min(id) from request_workflow_history where domain_object="
				+ requestID
				+ ")) and status_master_id=2) else "
				+ "(select office_id_id from emp_master where status_master_id=2 and user_id="
				+ userID + ") end officeId";
		try{
			Integer id = jdbcTemplate.queryForObject(requesterOfficeID, Integer.class);
			requesterID = id.toString();
		}
		catch(Exception e){
			requesterID = null;
		}
		return requesterID;
	}

	/**
	 * This method will return the Admin Login ID
	 */
	public String getAdminID() throws Exception{
		log.debug(" getAdminID: {}");
		String adminID=null;

		// Get the admin configured instance id from the configuration table
		// String adminID = null;
		String sql = "select concat(user_id, '#', ifnull(org_role_instance_id,'')) from emp_role_mapping where org_role_instance_id=(select value from "
				+ "configuration_details where upper(name)=upper('"
				+ CPSConstants.ADMIN + "')) and status_master_id=2";

		log.debug(" getAdminID: {}:", sql);
		adminID = jdbcTemplate.queryForObject(sql, String.class);
		
		return adminID;
	}

	/**
	 * This method will decide the BossId of the Employee
	 */
	public String getBossID(String userID, String requesterRoleID,
			String requesterOfficeID) throws Exception{
		log.debug(" getBossID: {}", userID +", "+ requesterRoleID +", "+requesterOfficeID);
		String getBossID = null;
		String orgInstance = null;
		String bossID = null;
		if (requesterRoleID == null) {
			String sql = "select org_role_instance_id from emp_role_mapping where status_master_id=2 and org_role_instance_id in ("
					+ getHierarchy(requesterOfficeID)
					+ ") and user_id="
					+ userID;

			try {
				Integer id = jdbcTemplate.queryForObject(sql, Integer.class);
				orgInstance = id.toString();
			} catch (EmptyResultDataAccessException e) {
				orgInstance = null;
			}


			if (orgInstance != null) {
				getBossID = "select concat(erm.user_id, '#', ifnull(erm.org_role_instance_id,'')) from org_role_instance ori, emp_role_mapping erm where "
						+ "ori.status_master_id=2 and ori.id="
						+ orgInstance
						+ " and erm.status_master_id=2 and "
						+ "erm.org_role_instance_id=ori.parent_org_role_id";
			} else {
				getBossID = "select case when "
						+ "(select count(*) from emp_role_mapping erm, emp_master emp where erm.status_master_id=2 and emp.status_master_id=2 and "
						+ "emp.user_id=erm.user_id and erm.org_role_instance_id=emp.office_id and erm.user_id="
						+ userID
						+ ") "
						+ ">0 then "
						+ "(select concat(user_id, '#' ,ifnull(org_role_instance_id,'')) from emp_role_mapping where status_master_id=2 and org_role_instance_id="
						+ "(select ori.parent_org_role_id from emp_role_mapping erm, emp_master emp, org_role_instance ori where "
						+ "erm.status_master_id=2 and emp.status_master_id=2 and emp.user_id=erm.user_id and "
						+ "erm.org_role_instance_id=emp.office_id and ori.status_master_id=2 and ori.id=erm.org_role_instance_id and "
						+ "erm.user_id="
						+ userID
						+ ")) "
						+ "else "
						+ "(select concat(erm.user_id, '#', ifnull(erm.org_role_instance_id, '')) from emp_role_mapping erm where erm.status_master_id=2 and erm.org_role_instance_id="
						+ getOfficeID(userID) + ") end parentID";
			}
		} else {
			getBossID = "select concat(erm.user_id, '#', ifnull(erm.org_role_instance_id,'')) parentID from org_role_instance ori, emp_role_mapping erm "
					+ "where ori.status_master_id=2 and erm.status_master_id=2 and erm.org_role_instance_id=ori.parent_org_role_id "
					+ "and ori.id=" + requesterRoleID;
		}
		
		try{
			bossID = jdbcTemplate.queryForObject(getBossID, String.class);
			
		}catch(Exception e){
			bossID= null;
		}
		return bossID;
	}

	
	/**
	 * This method will return the bosses boss id of the current sfid
	 */

	public String getBossesBossID(String userID, String requesterRoleID) throws Exception{
		log.debug(" getBossesBossID: {}", userID +", "+ requesterRoleID);
		String bossesBossID = null;
		String getBossesBossID = null;
		if (requesterRoleID == null) {
			/**
			 * First check whether the employee is an head or not If the
			 * employee is an head then get the upper tree start with office id
			 * (default role) and level is 3. If the employee is a normal
			 * employee then get his bosses boss with his office id(default
			 * role)
			 */
			getBossesBossID = "select case when (select count(*) from emp_master em, emp_role_mapping erm where "
					+ "em.status_master_id=2 and erm.status_master_id=2 and erm.user_id=em.user_id and em.office_id_id=erm.org_role_instance_id and "
					+ "erm.parent_user_id is null and em.user_id="
					+ userID
					+ ") "
					+ ">0 then "
					+ "(select concat(erm.user_id, '#', ifnull(erm.org_role_instance_id,'')) from emp_role_mapping erm where "
					+ "erm.org_role_instance_id="
					+ getHierarchyAtThirdLevel(getOfficeID(userID))
					+ " and erm.status_master_id=2)"
					+ "else"
					+ "(select concat(erm1.user_id, '#' ,ifnull(erm1.org_role_instance_id,'')) from emp_master em, "
					+ "emp_role_mapping erm, org_role_instance ori, emp_role_mapping erm1 where em.status_master_id=2 and "
					+ "erm.status_master_id=2 and ori.status_master_id=2 and erm1.status_master_id=2 and "
					+ "erm.org_role_instance_id=em.office_id_id and ori.id=erm.org_role_instance_id and "
					+ "erm1.org_role_instance_id=ori.parent_org_role_id and em.user_id="
					+ userID + ") " + "end parentID";
			
			//String result = jdbcTemplate.queryForObject(getBossesBossID, String.class);
			//bossesBossID = getLoginID(result.split("#")[0]) + "#"	+ result.split("#")[1];
			
			//bossesBossID = result;
			
		} else {
			getBossesBossID = "select concat(user_id, '#', ifnull(org_role_instance_id,'')) from emp_role_mapping  erm where org_role_instance_id="
					+ getHierarchyAtThirdLevel(getOfficeID(userID))
					+ " and erm.status_master_id=2";
		}
			try{
				bossesBossID = jdbcTemplate.queryForObject(getBossesBossID, String.class);
			}catch(Exception e){
				bossesBossID = null;
			}
			
			
		return bossesBossID;
	}

	
	/**
	 * get Instance SFID
	 */
	public String getInstanceLogin(String instanceID) throws Exception{
		log.debug(" getInstanceLogin: {}", instanceID);
		String instanceLogin = null;
		String sql = "select concat(user_id,'#',org_role_instance_id) from emp_role_mapping where status_master_id=2 and org_role_instance_id="
				+ instanceID;
		instanceLogin = jdbcTemplate.queryForObject(sql, String.class);
						
		return instanceLogin;
	}

	
	/**
	 * Query that will list Hierarchy
	 */
	public String getHierarchyRole(String orgID, String roleID) throws Exception{
		log.debug(" getHierarchyRole: {}", orgID +", "+ roleID);
		String user = orgID;
		// String hierarchy=orgID;
		String org = orgID;
		String role = null;
		while (!roleID.equals(role)) {
			user = getParentRole(org);
			// hierarchy = user;

				org = user.split("#")[0];
				role = user.split("#")[1];

		}
		return org;
	}

	
	/**
	 * Query that will get parent_org_role_id
	 */
	public String getParentRole(String orgID) throws Exception{
		log.debug(" getParentRole: {}", orgID);
		String parentId = null;
		String sql = "select id, org_role_hierarchy_id  from org_role_instance where status_master_id=2 and "
				+ "id=(select parent_org_role_id from org_role_instance where id = "
				+ orgID + ")";
		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);

		if(rows.size() >0){
			HashMap<String, Object> hm = (HashMap<String,Object>) rows.get(0);
			parentId = hm.get("id").toString() + "#"
					+ hm.get("org_role_hierarchy_id").toString();	
		}
		return parentId;
	}
	

	/**
	 * This method will return the current employee boss in the tree that the
	 * boss contains particular role
	 */
	public String getHeadLogin(String userID, String roleID,
			String requesterOfficeID, String requestFrom, String requesterRoleID) throws Exception{
		log.debug(" getHeadLogin: {}", userID, roleID, requesterOfficeID, requestFrom, requesterRoleID);
		String headLoginId = null;
		String sql = null;
		if (requestFrom != null && CPSConstants.APPROVED != null
				&& requestFrom.equalsIgnoreCase(CPSConstants.APPROVED)) {
			sql = "select user_id, org_role_instance_id from emp_role_mapping where org_role_instance_id in ("
					+ getHierarchyRole(requesterOfficeID, roleID)
					+ ") and status_master_id=2";
		} else if (requesterRoleID != null) {
			sql = "select user_id, org_role_instance_id from emp_role_mapping where org_role_instance_id in ("
					+ getHierarchyRole(requesterRoleID, roleID)
					+ ") and status_master_id=2";
		} else {
			sql = "select user_id, org_role_instance_id from emp_role_mapping where org_role_instance_id in ("
					+ getHierarchyRole(getOfficeID(userID), roleID)
					+ ") and status_master_id=2";
		}

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);
		if(rows.size() >0){
			HashMap<String, Object> hm = (HashMap<String,Object>) rows.get(0);
			headLoginId = hm.get("user_id").toString() + "#"
					+ hm.get("org_role_instance_id").toString();	
		}
		return headLoginId;
	}

	/**
	 * This method will decide the workflow id depends on workflow type(Designation)& request type(TADA)
	 * 
	 * @param workFlowType
	 * @param requestType
	 * @return
	 * @throws Exception
	 */
	public String decideWorkFlow()throws Exception{
		log.debug("::<<WorkflowProcess<<MethoddecideWorkFlow(RequestBean requestBean)");
		
		String workflowID = null;
		Integer id =null;
		String sql = null;
		try {
			
			//getUserDetails();
		
			if(CPSUtils.compareStrings(CPSConstants.DESIG, getWorkflowType())){
				if(!CPSUtils.isNullOrEmpty(getDesignation())){
					//designation based workflow
					sql = "select workflow_master_id from req_desig_workflow_mapping where status_master_id=2 and request_master_id="+getRequestTypeID()
							+" and designation_master_id ="+getDesignation();
						try{
							id = jdbcTemplate.queryForObject(sql, Integer.class);
							workflowID = id.toString();
						}
						catch(Exception e){
							workflowID = null;
						}
					}
				}
			else{
				if(!CPSUtils.isNullOrEmpty(getOrganisationID())){
					//organization specific workflow
					sql = "select workflow_master_id from req_org_workflow_mapping where status_master_id=2 and request_master_id="+getRequestTypeID()
							+" and org_role_instance_id ="+getOrganisationID();
					try{
						List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
						if (rows.size() > 0) {
							HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
							workflowID=(hm.get("workflow_master_id").toString());
						}
					}
					catch(Exception e){
						workflowID = null;
					}
					//workflowID = id.toString();
				}
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		return workflowID;
	}
	
	
	
	
	
	/**
	 * This method will decide the workflow id depends on workflow
	 * type(Generic/Role)& request type(PIS/Leave/...)
	 */

	public String decideWorkFlow(String requestType, String userID, String orgRoleID) throws Exception{
		log.debug(" decideWorkFlow: {}", requestType, userID, orgRoleID);
		String sql = null;
		Integer id = null;
		String getWorkflow = null;
		if (orgRoleID != null) {
			// Some of the employees are having two roles, In this case from
			// which role the employee is requesting
			sql = "select workflow_master_id from role_workflow_mapping where status_master_id=2 and org_role_instance_id="
					+ orgRoleID + " and request_master_id=" + requestType;
		} else if (userID == null) {
			// generic workflow
			sql = "select workflow_master_id from request_workflow_mapping where status_master_id=2 and request_master_id="
					+ requestType;
		} else {
			// role based workflow
			sql = "select workflow_master_id from role_workflow_mapping where org_role_instance_id=(select emp.office_id_id from "
					+ "emp_master emp, emp_role_mapping erm where emp.status_master_id=2 and erm.status_master_id=2 and "
					+ "erm.user_id=emp.user_id and erm.parent_user_id is null and erm.org_role_instance_id=emp.office_id_id and "
					+ "emp.user_id="
					+ userID
					+ ") and request_master_id="
					+ requestType
					+ " and status_master_id=2";
		}
		try{
			 id= jdbcTemplate.queryForObject(sql, Integer.class);
			 if(!CPSUtils.isNullOrEmpty(id)){
			 getWorkflow = id.toString();}
			 else{
				 getWorkflow= null;
			 }
		}
		catch(Exception e){
			
			getWorkflow= null;
		}
		
		
		return getWorkflow;
	}

	
	/**
	 * This method will return true, if the request was not assigned earlier,
	 * otherwise it will return false
	 */
	public boolean checkRequestAssigned(String domain_object_id, String parentID, String roleID) throws Exception{
		log.debug(" checkRequestAssigned: {}", domain_object_id, parentID, roleID);
		boolean requestFlag = false;
		
		String result = null;
		String res = null;
		String checkRequestAssigned = "select id from request_workflow_history where domain_object="
				+ domain_object_id
				+ " and assigned_to_id="
				+ parentID
				+ " and assigned_role_id=" + roleID;
		try{
			Integer id = jdbcTemplate.queryForObject(checkRequestAssigned, Integer.class);
			res = id.toString();
		}catch (EmptyResultDataAccessException e) {
			res = null;
		}
		
		if (res != null) {
			requestFlag = true;
		} else {
			// check whether the requester is same as parentID
			// If the request is from doms we can allow the request for filing
			checkRequestAssigned = "select case when (select count(*) from emp_role_mapping where status_master_id=2 and user_id="
					+ parentID
					+ " and org_role_instance_id=(select value from configuration_details where "
					+ "name='"
					+ CPSConstants.ADMIN
					+ "'))"
					+ ">0 then 'false' when "
					+ "(select assigned_from_id from request_workflow_history where id=(select min(id) from request_workflow_history"
					+ " where domain_object="
					+ domain_object_id
					+ "))="
					+ parentID
					+ " then 'true' else 'false' end result";
			
			result = jdbcTemplate.queryForObject(checkRequestAssigned, String.class);
			
			if(result != null && CPSConstants.TRUE != null && result.equalsIgnoreCase(CPSConstants.TRUE)){
				
				requestFlag = true;
				
			}
			
		}
		return requestFlag;
	}

	/**
	 * This method will find the workflow assigned login
	 */

	public String getAssignedID(String userID, String type, String requesterOfficeID, String requestFrom, String requesterRoleID, String domain_object_id) throws Exception{
		log.debug(" getAssignedID: {}", userID, type, requesterOfficeID, requestFrom, requesterRoleID, domain_object_id);
		String assignedID = null;

		if (type != null && CPSConstants.BOSSID != null
				&& type.equalsIgnoreCase(CPSConstants.BOSSID)) {
			// BOSS
			assignedID = getBossID(userID, requesterRoleID, requesterOfficeID);
		} else if (type != null && CPSConstants.BOSSESBOSS != null
				&& type.equalsIgnoreCase(CPSConstants.BOSSESBOSS)) {
			// BOSSES BOSS
			assignedID = getBossesBossID(userID, requesterRoleID);
		} else if (type != null && CPSConstants.ADMINID != null
				&& type.equalsIgnoreCase(CPSConstants.ADMINID)) {
			// ADMIN
			assignedID = getAdminID();
		} else if (type != null && CPSConstants.REQUESTERID != null
				&& type.equalsIgnoreCase(CPSConstants.REQUESTERID)) {
			// REQUESTER
			assignedID = getRequesterID(domain_object_id);
		} else {
			// 1-DIRECTOR,3-PROGRAMME DIRECTOR,5-ASSOCIATE DIRECTOR,7-PROJECT
			// MANAGER,9-TECHNICAL DIRECTORATE,13-DIVISION HEAD
			assignedID = getHeadLogin(userID, type, requesterOfficeID,
					requestFrom, requesterRoleID);
		}
		return assignedID;
	}
	
	/**
	 * This method will return an object that contains assigned sfid of a particular workflow
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	/**
	 * getWorkFlowAssignedSfid() 
	 */
	public String getWorkflowAssignedSfid() throws Exception{
		log.debug(" getWorkFlowAssignedSfid: {}" );
		/*WorkflowService ws = new WorkflowService();
		WorkflowService ws1 = new WorkflowService();*/
		String relativeToRoleId = null;
		String absoluteToRoleId = null;
		String relationship_type_id = null;
		String result = null;
		
		String currentLogin = SecurityUtils.getCurrentUserLogin();
		String userID = getUserID(currentLogin);
		setSfID(userID);
		
		/*if (CPSUtils.isNullOrEmpty(ws1.getPrevsfID())) {
			ws1.setPrevsfID(ws.getSfID());
			ws1.setFlag(true);
		}*/
	
		if (CPSUtils.isNull(getRequesterOfficeID())) {
			setRequesterOfficeID(getRequesterOfficeID(getRequestID(), getSfID()));
		}
		
		String getWorkFlowDetails ="select w.workflow_master_id, w.relative_to_role_id, w.absolute_to_role_id, w.stage_id, w.relationship_type_id, w.workflow_stage_master_id "
				+ "from workflow w where w.workflow_master_id="+getWorkflowID()+" and w.stage_id="+getStageID(); 
		
		/*String getWorkFlowDetails = "select w.workflow_id, w.relative_to_role_id, w.absolute_to_role_id, w.stage_id, w.relationship_type_id, w.stage_desc_id from t_workflow w "
				+ "where w.workflow_id=7 and w.stage_id=1";*/
		
		List<java.util.Map<String, Object>> rows = jdbcTemplate.queryForList(getWorkFlowDetails);
		
		if(rows.size()>0){
			HashMap<String,Object> hm = (HashMap<String, Object>) rows.get(0);
			try{
				relativeToRoleId = hm.get("relative_to_role_id").toString();
			}catch(Exception   e){
				relativeToRoleId = null;
			}
			try{
				if(!CPSUtils.isNull(hm.get("absolute_to_role_id")))
				absoluteToRoleId = hm.get("absolute_to_role_id").toString();
				
			}catch(Exception   e){
				absoluteToRoleId = null;
			}
			try{
				relationship_type_id = hm.get("relationship_type_id").toString();
			}catch(Exception   e){
				relationship_type_id = null;
			}
		}
		
		if(relativeToRoleId != null){
			if (CPSUtils.compareStrings(relationship_type_id, CPSConstants.WORKFLOWRELATIONRELID)) {
				//System.out.println(getAssignedID("3", "20", "18", "APPROVED", "6", "4"));
				
				
				/*String currentLogin = SecurityUtils.getCurrentLogin();//commented by mohib
				String userID = getUserID(currentLogin);
				setSfID(userID);*/
				
				
				result = getAssignedID(getSfID(), relativeToRoleId, getRequesterOfficeID(), CPSConstants.APPROVED, getOrgRoleID(), getRequestID());
				//result = getAssignedID("3", "5", "18", CPSConstants.APPROVED, "6", "121");
				
				if (!CPSUtils.isNullOrEmpty(result)) {
					setParentID(result.split("#")[0]);
					setRoleID(result.split("#")[1]);
					//System.out.println(ws.getParentID()+"=========="+ws.getRoleID());
				}
			}
		}
		if(absoluteToRoleId !=null){
			if (CPSUtils.compareStrings(relationship_type_id, CPSConstants.WORKFLOWRELATIONABSID)) {
				// ABSOLUTE
				result = getInstanceLogin(absoluteToRoleId);
				
				if (!CPSUtils.isNullOrEmpty(result)) {
					setParentID(result.split("#")[0]);
					
					setRoleID(result.split("#")[1]);
				}
			}
		}
		/**
		 * Iterate this loop till the parent ID is different and the requester & assigned employee is different.
		 */
		
		/*if (CPSUtils.isNullOrEmpty(ws1.getParentID()) || CPSUtils.compareStrings(ws1.getParentID(), ws.getParentID())
				|| checkRequestAssigned(ws.getRequestID(), ws.getParentID(), ws.getRoleID())) {
			if (CPSUtils.isNullOrEmpty(ws1.getSfID())) {
				ws1.setSfID(ws.getSfID());
				ws1.setRequesterOfficeID(ws.getRequesterOfficeID());
			}
			ws.setSfID(ws.getParentID());
			ws.setStageID(String.valueOf((Integer.parseInt(ws.getStageID()) + 1)));
	
			ws1.setParentID(ws.getParentID());
			ws1.setRoleID(ws.getRoleID());
			ws1.setStageID(String.valueOf((Integer.parseInt(ws.getStageID()))));
	
			ws.setRequesterOfficeID(ws.getRoleID());
			getWorkFlowAssignedSfid();
		} else {
			ws.setSfID(ws1.getSfID());
			ws.setRequesterOfficeID(ws1.getRequesterOfficeID());
			ws.setParentID(ws1.getParentID());
			ws.setRoleID(ws1.getRoleID());
			ws.setStageID(String.valueOf(Integer.parseInt(ws.getStageID()) - 1));
		}
	
	
		if (CPSUtils.compareStrings(ws.getSfID(), ws.getParentID())) {
			ws.setSfID(ws1.getSfID());
			ws.setRequesterOfficeID(ws1.getRequesterOfficeID());
			ws.setStageID(String.valueOf(Integer.parseInt(ws.getStageID()) - 1));
		}*/
		
		
		return getParentID();
	}//method
	
	
	/**
	 * getUserDetails and set it to workflowService
	 */
	public void getUserDetails() throws Exception{
		log.debug(" getUserDetails: {}" );
				
		String currentLogin = SecurityUtils.getCurrentUserLogin();
		String userID = getUserID(currentLogin);
		setSfID(userID);
		//setRequesterOfficeID(getOfficeID(userID));
		//setDesignation(getDesignationID(userID));
		//getDesignation();
		setOrganisationID(getOfficeID(userID));
		InetAddress addr= InetAddress.getLocalHost();
		setIpAddress(addr.getHostAddress());
	}

	public String getEmpExist() throws Exception {
		String message = "NO";
		try {
             String sql = "select count(*) from emp_master where user_id="+getSfID()+" and status_master_id=2";
             Integer id = jdbcTemplate.queryForObject(sql, Integer.class);
			if (id > 0) {
				message = "YES";
			}

		} catch (Exception e) {
			throw e;
		} 
		return message;
	}
	
	public String approvedRequest() throws Exception {
		String message = "NO";
		try {

			/**
			 * update status to approve for all the txn details those are having delegated
			 */
			changeDelegateOrPendingToApprove();

			/*checkAssignedWorkflow();
			if (CPSUtils.isNullOrEmpty(rb.getWorkflowID())) {} else {}*/

		} catch (Exception e) {
			throw e;
		} 
		return message;
	}
	
	
	
	
	
	public void changeDelegateOrPendingToApprove() throws Exception {

		log.debug("::<<TxRequestProcess<MethodchangeDelegateOrPendingToApprove(String historyID)");
		
		try {
			String qry =	"update request_workflow_history rwh2 set rwh2.status_master_id=?,rwh2.workflow_stage_master=?,rwh2.actioned_date=sysdate where rwh2.id in ( "
					+ "select rwh.id from request_workflow_history rwh, status_master sm where rwh.domain_object=(select rwh1.domain_object from "
					+ "request_workflow_history rwh1 where rwh1.id=?) and upper(sm.status) in (upper(?),upper(?)) and rwh.id!=?)"	;

			
			jdbcTemplate.update(qry);
			
		} catch (Exception e) {
			throw e;
		}
	
	}
	
	//setters and getters
	
	public String getRequesterType() {
		return requesterType;
	}

	public void setRequesterType(String requesterType) {
		this.requesterType = requesterType;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public WorkflowStageMasterRepository getWorkflowStageMasterRepository() {
		return workflowStageMasterRepository;
	}

	public void setWorkflowStageMasterRepository(
			WorkflowStageMasterRepository workflowStageMasterRepository) {
		this.workflowStageMasterRepository = workflowStageMasterRepository;
	}

	public StatusMasterRepository getStatus_masterRepository() {
		return statusMasterRepository;
	}

	public void setStatusMasterRepository(
			StatusMasterRepository statusMasterRepository) {
		this.statusMasterRepository = statusMasterRepository;
	}

	public RequestMasterRepository getRequestMasterRepository() {
		return requestMasterRepository;
	}

	public void setRequestMasterRepository(
			RequestMasterRepository requestMasterRepository) {
		this.requestMasterRepository = requestMasterRepository;
	}

	public WorkflowMasterRepository getWorkflowMasterRepository() {
		return workflowMasterRepository;
	}

	public void setWorkflowMasterRepository(
			WorkflowMasterRepository workflowMasterRepository) {
		this.workflowMasterRepository = workflowMasterRepository;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public OrgRoleInstanceRepository getOrgRoleInstanceRepository() {
		return orgRoleInstanceRepository;
	}

	public void setOrgRoleInstanceRepository(
			OrgRoleInstanceRepository orgRoleInstanceRepository) {
		this.orgRoleInstanceRepository = orgRoleInstanceRepository;
	}

	public EmpMasterRepository getEmpMasterRepository() {
		return empMasterRepository;
	}

	public void setEmpMasterRepository(
			EmpMasterRepository empMasterRepository) {
		this.empMasterRepository = empMasterRepository;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getWorkflowID() {
		return workflowID;
	}

	public void setWorkflowID(String workflowID) {
		this.workflowID = workflowID;
	}

	public String getRequestTypeID() {
		return requestTypeID;
	}

	public void setRequestTypeID(String requestTypeID) {
		this.requestTypeID = requestTypeID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSfID() {
		return sfID;
	}

	public void setSfID(String sfID) {
		this.sfID = sfID;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public boolean isRequestInternalFlag() {
		return requestInternalFlag;
	}

	public void setRequestInternalFlag(boolean requestInternalFlag) {
		this.requestInternalFlag = requestInternalFlag;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getHistoryID() {
		return historyID;
	}

	public void setHistoryID(String historyID) {
		this.historyID = historyID;
	}

	public String getStageID() {
		return stageID;
	}

	public void setStageID(String stageID) {
		this.stageID = stageID;
	}

	public String getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(String assignedDate) {
		this.assignedDate = assignedDate;
	}

	public String getActionedDate() {
		return actionedDate;
	}

	public void setActionedDate(String actionedDate) {
		this.actionedDate = actionedDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWorkflowType() {
		return workflowType;
	}

	public void setWorkflowType(String workflowType) {
		this.workflowType = workflowType;
	}

	public ArrayList<RequestWorkflowHistory> getWorkflowHistory() {
		return workflowHistory;
	}

	public void setWorkflowHistory(
			ArrayList<RequestWorkflowHistory> workflowHistory) {
		this.workflowHistory = workflowHistory;
	}

	public String getRequesterOfficeID() {
		return requesterOfficeID;
	}

	public void setRequesterOfficeID(String requesterOfficeID) {
		this.requesterOfficeID = requesterOfficeID;
	}

	public List<EmpMaster> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmpMaster> empList) {
		this.empList = empList;
	}

	public List<OrgRoleInstance> getInstanceList() {
		return instanceList;
	}

	public void setInstanceList(List<OrgRoleInstance> instanceList) {
		this.instanceList = instanceList;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getRequestIDs() {
		return requestIDs;
	}

	public void setRequestIDs(String requestIDs) {
		this.requestIDs = requestIDs;
	}

	public String getChangedValues() {
		return changedValues;
	}

	public void setChangedValues(String changedValues) {
		this.changedValues = changedValues;
	}

	public ArrayList<WorkflowTxnDetails> getTxnDetails() {
		return getTxnDetails();
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public Logger getLog() {
		return log;
	}
	
	public String getPrevsfID() {
		return prevsfID;
	}

	public void setPrevsfID(String prevsfID) {
		this.prevsfID = prevsfID;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public String getOrgRoleID() {
		return orgRoleID;
	}

	public void setOrgRoleID(String orgRoleID) {
		this.orgRoleID = orgRoleID;
	}
	
	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	public String getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(String requestCount) {
		this.requestCount = requestCount;
	}
	
	public String getOrganisationID() {
		return organisationID;
	}

	public void setOrganisationID(String organisationID) {
		this.organisationID = organisationID;
	}

	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}
	
	public String getRequesterRoleID() {
		return requesterRoleID;
	}

	public void setRequesterRoleID(String requesterRoleID) {
		this.requesterRoleID = requesterRoleID;
	}
	
	
	public String getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}
}

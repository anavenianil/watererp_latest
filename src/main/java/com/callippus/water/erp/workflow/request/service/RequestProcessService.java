package com.callippus.water.erp.workflow.request.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.RequestMaster;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.WorkflowTxnDetails;
import com.callippus.water.erp.repository.ApplicationTxnRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.RequestMasterRepository;
import com.callippus.water.erp.repository.RequestWorkflowHistoryRepository;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.repository.WorkflowMasterRepository;
import com.callippus.water.erp.repository.WorkflowStageMasterRepository;
import com.callippus.water.erp.workflow.service.DesigBasedWorkflowService;
import com.callippus.water.erp.workflow.service.GenericWorkflowService;
import com.callippus.water.erp.workflow.service.InternalWorkflowService;
import com.callippus.water.erp.workflow.service.OrgSpecificWorkflowService;
import com.callippus.water.erp.workflow.service.RoleBasedWorkflowService;
import com.callippus.water.erp.workflow.service.UserLevelWorkflowService;
import com.callippus.water.erp.workflow.service.WorkflowService;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class RequestProcessService {
	private final Logger log = LoggerFactory
			.getLogger(RequestProcessService.class);

	private String requestID;
	private String requestType;
	private String requestTypeID;
	private String param;
	private String requestedFor;
	private String sfID;
	private String designationID;

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getRequestTypeID() {
		return requestTypeID;
	}

	public void setRequestTypeID(String requestTypeID) {
		this.requestTypeID = requestTypeID;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getRequestedFor() {
		return requestedFor;
	}

	public void setRequestedFor(String requestedFor) {
		this.requestedFor = requestedFor;
	}

	public String getSfID() {
		return sfID;
	}

	public void setSfID(String sfID) {
		this.sfID = sfID;
	}

	public String getDesignationID() {
		return designationID;
	}

	public void setDesignationID(String designationID) {
		this.designationID = designationID;
	}

	@Inject
	private GenericWorkflowService genericWorkflowService;

	@Inject
	private InternalWorkflowService internalWorkflowService;

	@Inject
	private WorkflowService workflowService;

	@Inject
	private UserLevelWorkflowService userLevelWorkflowService;

	@Inject
	private RoleBasedWorkflowService roleBeasedWorkflowService;

	@Inject
	private ConfigurationDetailsRepository configurationDetailsRepository;

	@Inject
	private OrgSpecificWorkflowService orgSpecificWorkflowService;

	@Inject
	private DesigBasedWorkflowService desigBasedWorkflowService;

	@Inject
	private StatusMasterRepository statusMasterRepository;

	@Inject
	private RequestWorkflowHistoryRepository requestWorkflowHistoryRepository;

	@Inject
	private UserRepository userRepository;

	@Inject
	private RequestMasterRepository requestMasterRepository;
	@Inject
	private WorkflowMasterRepository workflowMasterRepository;

	@Inject
	private WorkflowStageMasterRepository workflowStageMasterRepository;
	
	@Inject
	private ApplicationTxnRepository applicationTxnRepository;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	public WorkflowService getWorkflowService() {
		return workflowService;
	}

	public void setWorkflowService(WorkflowService workflowService) {
		this.workflowService = workflowService;
	}

	public void decideWorkflow() throws Exception {
		try {
			// check the organization specific workflow
			orgSpecificWorkflowService.initWorkflow();
			if (CPSUtils.isNull(workflowService.getWorkflowID())) {
				// organization specific workflow not exist so follow the role
				// based workflow
				// workflowService.getRequestTypeID();
				roleBeasedWorkflowService.initWorkflow();
			}
			if (CPSUtils.isNull(workflowService.getWorkflowID())) {
				// organization specific,role based workflows not exist so
				// follow the designation based workflow

				desigBasedWorkflowService.initWorkflow();
			}
			if (CPSUtils.isNull(workflowService.getWorkflowID())) {
				// organization specific,role based,designation based workflows
				// not exist role based workflow not exists so follow the
				// generic workflow
				genericWorkflowService.initWorkflow();
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * This method will generate the unique id for a key
	 * 
	 * @return request id
	 * @throws Exception
	 */

	public String generateUniqueID(String tableName) throws Exception {

		Integer id = null;
		// List<java.util.Map<String, Object>> rows = null;
		String getRequestID = "select value from id_generator where upper(table_name)='"
				+ tableName.toUpperCase() + "'";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getRequestID);

		if (rows.size() > 0) {

			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);

			id = (Integer) hm.get("value") + 1;

			String updateID = "update id_generator set value=" + id
					+ " where upper(table_name)='" + tableName.toUpperCase()
					+ "'";

			jdbcTemplate.update(updateID);

			// List<java.util.Map<String, Object>> rows1 =
			// jdbcTemplate.queryForList(updateID);
		}
		return id.toString();
	}

	/**
	 * This method will return the request bean that contains request type id &
	 * internal flag
	 * 
	 * @param request
	 *            bean
	 * @return request bean
	 * @throws Exception
	 */
	public String getRequestTypeDetails() throws Exception {

		String getRequestTypeID = "select id,internal_flag from request_master where upper(request_type)=upper('"
				+ workflowService.getRequestType()
				+ "') and status_master_id=2";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getRequestTypeID);
		log.debug("SQL : getRequestTypeID > > " + getRequestTypeID);
		if (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			workflowService.setRequestTypeID(hm.get("id").toString());
			if (CPSUtils
					.compareStrings(hm.get("internal_flag").toString(), "1")) {
				log.debug("Internal Workflow Exists for "
						+ workflowService.getRequestType());
				workflowService.setRequestInternalFlag(true);
			} else {
				log.debug("No Internal Workflow for "
						+ workflowService.getRequestType());
				workflowService.setRequestInternalFlag(false);
			}
		}

		return workflowService.getRequestTypeID();
	}

	public WorkflowTxnDetails txnRowDetails(RequestMaster reqTypeID,
			String reqID, String refNo, String colName, String ipAddress,
			String newValue, String prevValue, String rowNo, String desc)
			throws Exception {
		log.debug("    <RequestProcess    <<Method            >>>txnRowDetails()        >");
		WorkflowTxnDetails workflowTxnDetails = null;

		workflowTxnDetails = new WorkflowTxnDetails();
		workflowTxnDetails
				.setId(Long
						.parseLong(generateUniqueID(CPSConstants.WORKFLOW_TXN_DETAILS)));
		workflowTxnDetails.setRequestMaster(reqTypeID);
		workflowTxnDetails.setRequestId(Integer.parseInt(reqID));
		workflowTxnDetails.setReferenceNumber(refNo);
		workflowTxnDetails.setColumnName(colName);
		workflowTxnDetails.setIpAddress(ipAddress);
		workflowTxnDetails.setNewValue(newValue);
		workflowTxnDetails.setPreviousValue(prevValue);
		workflowTxnDetails.setRowNumber(Integer.parseInt(rowNo));
		workflowTxnDetails.setDescription(desc);

		return workflowTxnDetails;
	}

	/**
	 * This method will insert the txn details
	 * 
	 * @param txnDetails
	 * @return
	 * @throws Exception
	 */
	public String submitTxnDetails(ArrayList<WorkflowTxnDetails> txnDetails)
			throws Exception {
		log.debug("    <RequestProcess    <<Method            >>>submitTxnDetails(RequestBean rb)        >");
		String message = null;
		Session session = null;
		Transaction tx = null;
		if (!CPSUtils.isNull(txnDetails)) {
			for (int i = 0; i < txnDetails.size(); i++) {
				WorkflowTxnDetails workflowTxnDetails = (WorkflowTxnDetails) txnDetails
						.get(i);
				if (!CPSUtils.isNullOrEmpty(workflowTxnDetails
						.getPreviousValue())
						|| !CPSUtils.isNullOrEmpty(workflowTxnDetails
								.getNewValue())) {
					tx = session.beginTransaction();
					session.saveOrUpdate(workflowTxnDetails);
					tx.commit();
				}
			}
		}
		message = CPSConstants.SUCCESS;
		return message;
	}

	/**
	 * This method will be called at the time of requesting. In this method we
	 * will decide the workflow for the current request & forwards that request
	 * to the next level (inserting details into workflow history table) And if
	 * the assigned user configured (instance user) auto delegate it will
	 * automatically forwards that request
	 * 
	 * @param request
	 *            bean
	 * @return success/failure messages
	 */
	public String initWorkflow() throws Exception {
		log.debug("    <RequestProcess    <<Method            >>>initWorkflow(RequestBean rb)        >");

		/**
		 * get the request type id & internal workflow flag
		 */
		String requestType = getRequestTypeDetails();
		workflowService.setRequestTypeID(requestType);
		// workflowService.setRequestInternalFlag(true);

		if (workflowService.isRequestInternalFlag()) {
			/**
			 * request should follow the internal division hierarchy structure
			 * so get the employee parent id
			 */
			log.debug("Request should follow the internal division hierarchy");
			// ((InternalWorkflowService)workflowService).getInternalDivisionWorkFlow();
			// //previously written code
			workflowService.setParentID(internalWorkflowService
					.getInternalDivisionWorkFlow());

			if (CPSUtils.isNullOrEmpty(workflowService.getParentID())) {
				/**
				 * If the parent ID is null mean boss is the requester or his
				 * 
				 * immediate subordinates are the requesters, so there is no
				 * concept of internal workflow
				 */
				log.debug("Internal workflow not exists for this employee, so follow the generic workflow");
				// ((GenericWorkflowService)workflowService).initWorkflow();
				decideWorkflow();
			}
		} else {
			/**
			 * request should not follow the internal division structure so
			 * check the workflow from the generic workflows for this request
			 */
			log.debug("No internal workflow for this request, so follow the generic workflow");
			// ((GenericWorkflowService)workflowService).initWorkflow();
			decideWorkflow();
		}
		log.debug("Before assiging Request");

		workflowService.setMessage(assignRequest(workflowService));

		return workflowService.getMessage();
	}

	/**
	 * In this method we will check the request assigned sfid And assigned the
	 * request to the assigned sfid Test auto delegate configuration, IF the
	 * assigned employee was configured then the request status will be updated
	 * & assigned to configured user
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public String assignRequest(WorkflowService workflowService)
			throws Exception {
		log.debug("    <RequestProcess    <<Method            >>>assignRequest()        >");

		if (!CPSUtils.isNullOrEmpty(workflowService.getParentID())) {

			if (!CPSUtils.isNullOrEmpty(workflowService.getInstanceId())) {
				workflowService.setStageID(String.valueOf((Integer
						.parseInt(workflowService.getStageID()) + 1)));
			}
			insertHistory(workflowService);
			/**
			 * Check whether the employee is having auto delegate configuration
			 * or not. If the stageID is null or empty then it is an internal
			 * workflow, so there is no concept of auto delegate
			 */
			log.debug("Check whether the employee is having auto delegate configuration or not");
			if (!CPSUtils.isNullOrEmpty(workflowService.getStageID())) {
				autoDelegateProcess();
			}
		} else {
			/**
			 * No workflow exists or completed the workflow Update the status to
			 * complete for this history id
			 */
			workflowService.setMessage(updateRequestHistory(
					workflowService.getHistoryID(), CPSConstants.COMPLETED,
					workflowService.getIpAddress(),
					workflowService.getRemarks(),
					workflowService.getApprovedDate()));
			if (CPSUtils.compareStrings(workflowService.getMessage(),
					CPSConstants.SUCCESS)) {
				workflowService.setMessage(CPSConstants.UPDATE);
				workflowService.setRequestStatus(2);//for requisition
			}

			log.debug("        <<<assignRequest()        >Result Message    "
					+ workflowService.getMessage());
		}
		return workflowService.getMessage();
	}

	/* *
	 * In this method we will check the auto delegate configurations
	 * 
	 * @param rb
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public String autoDelegateProcess() throws Exception {
		log.debug("    <RequestProcess    Method            >>>autoDelegateProcess(RequestBean rb)        >");
		String parentID = null;

		parentID = workflowService.getParentID();
		log.debug("Parent ID    >>>" + parentID);

		// userLevelWorkflowService.checkUserAutoDelegate();//added on june_30
		// by mohib

		// check the previous parentid & present parent id
		if (!CPSUtils.compareStrings(parentID, workflowService.getParentID())) {
			/**
			 * Employee has configured auto delegate configuration so update the
			 * status & delegate to his configured user
			 */
			log.debug("Employee has configured auto delegate configuration");
			workflowService.setMessage(updateRequestHistory(
					workflowService.getHistoryID(), CPSConstants.DELEGATED,
					workflowService.getIpAddress(), "", workflowService.getApprovedDate()));
			if (CPSUtils.compareStrings(CPSConstants.SUCCESS,
					workflowService.getMessage())) {
				insertHistory(workflowService);

				/**
				 * After insertion also we should check the auto delegate
				 * configuration for assigned user
				 */
				log.debug("After insertion also we should check the auto delegate");
				autoDelegateProcess();
			}

		}
		return "";
	}

	/* *
	 * In this method we will insert the history of the request
	 * 
	 * @param
	 * 
	 * @return
	 */
	public WorkflowService insertHistory(WorkflowService workflowService)
			throws Exception {
		log.debug("    <RequestProcess    Method            >>>insertHistory(WorkflowService workflowService)        >");

		RequestWorkflowHistory rwh = new RequestWorkflowHistory();

		workflowService.getRequestID();
		workflowService.getRequesterType();
		workflowService.getRequestTypeID();

		Long domainId = Long.parseLong(workflowService.getDomain_object_id());
		//Customer customer=applicationTxnRepository.findOne(domainId).getCustomer();
		//rwh.setAppliedBy(appliedBy);

		workflowService.getUserDetails();
		rwh.setRequestStage(Integer.parseInt(workflowService.getStageID()));
		//rwh.setActionedDate(new DateTime());

		rwh.setAssignedFrom(userRepository.findById(new Long(workflowService
				.getSfID())));
		rwh.setAssignedTo(userRepository.findById(new Long(workflowService
				.getParentID())));
		workflowService.setRequestAt(rwh.getAssignedTo().getId().toString());//additional Added by mohib for applicationTxn
		workflowService.getStatus();
		rwh.setStatusMaster(statusMasterRepository.findOne(new Long(3)));// 3-PENDING
		rwh.setIpAddress(workflowService.getIpAddress());
		//rwh.setRemarks(workflowService.getRemarks());
		rwh.setDomainObject(new Long(
				workflowService.getDomain_object_id()));

		rwh.setRequestMaster(requestMasterRepository.findOne(new Long(
				workflowService.getRequestTypeID())));
		rwh.setWorkflowMaster(workflowMasterRepository.findOne(new Long(
				workflowService.getWorkflowID())));
		ZonedDateTime now = ZonedDateTime.now();
		//rwh.setActionedDate(now);
		
		rwh.setAssignedDate(requestWorkflowHistoryRepository.findOne(Long.parseLong(workflowService.getHistoryID())).getActionedDate());

		RequestWorkflowHistory requestWorkflowHistory = requestWorkflowHistoryRepository.save(rwh);
		workflowService.setRequestWorkflowHistoryId(requestWorkflowHistory.getId());
		
		log.debug("hisroty ID    >>>" + workflowService.getHistoryID());
		log.debug("Assigned Date    >>>" + workflowService.getAssignedDate());
		log.debug("Stage ID    >>" + workflowService.getStageID());

		workflowService.setMessage(CPSConstants.SUCCESS);

		return workflowService;
	}

	/**
	 * This method will update the history table
	 * 
	 * @param historyID
	 * @param mode
	 * @return
	 * @throws Exception
	 */
	public String updateRequestHistory(String historyID, String mode,
			String ipAddress, String remarks, ZonedDateTime approvedDate) throws Exception {
		log.debug("<RequestProcess Method >>>updateRequestHistory(String historyID, String mode)>");
		String message = null;
		//ZonedDateTime dateTime = ZonedDateTime.parse(actionedDate);

		RequestWorkflowHistory requestWorkflowHistory = requestWorkflowHistoryRepository.findOne(Long.parseLong(historyID));
		requestWorkflowHistory.setRemarks(remarks);
		requestWorkflowHistory.setIpAddress(ipAddress);
		requestWorkflowHistory.setActionedDate(approvedDate);
		requestWorkflowHistory.setStatusMaster(statusMasterRepository.findByStatus(mode.toUpperCase()));
		requestWorkflowHistoryRepository.save(requestWorkflowHistory);
		
		/*String updateHistory = "update request_workflow_history set status_master_id=(select id from status_master where status=upper('"
				+ mode
				+ "')),ip_address='"
				+ ipAddress
				+ "',remarks='"
				+ remarks
				+ "', actioned_date= '"
				+ approvedDate.toLocalDateTime()
				+ "' where id="
				+ historyID;
		log.debug("SQL : updateHistory > > " + updateHistory);
		log.debug("Mode     " + mode);
		log.debug("History ID" + historyID);
		log.debug("IP Address    " + ipAddress);
		log.debug("Actioned Date "+approvedDate);

		jdbcTemplate.update(updateHistory);*/

		/*
		 * List<java.util.Map<String, Object>> rows = jdbcTemplate
		 * .queryForList(updateHistory); log.debug("SQL : updateHistory > > " +
		 * rows);
		 */
		message = CPSConstants.SUCCESS;

		return message;
	}

	/**
	 * Update the status of a request that contains particular history id
	 * 
	 * @param historyID
	 * @return
	 * @throws Exception
	 */
	public String declinedRequest(String historyID, String ipAddress,
			String remarks, ZonedDateTime approvedDate) throws Exception {
		log.debug("    <RequestProcess    Method            >>>declinedRequest(String historyID)        >");
		return updateRequestHistory(historyID, CPSConstants.DECLINED,
				ipAddress, remarks, approvedDate);
	}

	/**
	 * This method will be called when the user manually delegate his assigned
	 * request to his selected user
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public String delegateRequest(WorkflowService rb) throws Exception {
		log.debug("    <RequestProcess    Method            >>>delegateRequest(RequestBean rb)        >");

		/**
		 * Change the status from pending to approved status for remaining
		 * history
		 */
		changePendingToDelegate(workflowService.getHistoryID());

		/**
		 * Update the status to DELEGATE
		 */
		workflowService.setMessage(updateRequestHistory(
				workflowService.getHistoryID(), CPSConstants.DELEGATED,
				workflowService.getIpAddress(), workflowService.getRemarks(), workflowService.getApprovedDate()));// DELEGATED

		log.debug("Messge >>> " + workflowService.getMessage());

		if (CPSUtils.compareStrings(workflowService.getMessage(),
				CPSConstants.SUCCESS)) {
			/**
			 * get the details of a particular history ID
			 */
			getRequestDetails();

			/**
			 * Check whether the employee selected instance or an employee
			 */
			if (CPSUtils.isNumeric(workflowService.getParentID())) {
				workflowService.setParentID(getInstanceParentID(workflowService
						.getParentID()));
			}

			/**
			 * Assigning the request
			 */
			workflowService.setMessage(assignRequest(workflowService));
		}

		return workflowService.getMessage();
	}

	/* *
	 * If the user delegated to instance then it will return the sfid mapped to
	 * instance id
	 */
	public String getInstanceParentID(String nodeID) throws Exception {
		String parentID = null;

		String getParentID = "select user_id from emp_role_mapping where org_role_instance_id="
				+ nodeID + " and status_master_id=2";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getParentID);
		while (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			parentID = hm.get("user_id").toString();
		}
		return parentID;
	}

	/**
	 * This method will return the request stage details of a particular hisroty
	 * id
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public void getRequestDetails() throws Exception {
		log.debug("    <RequestProcess    Method            >>>getRequestDetails(RequestBean rb)        >");
		String getRequestDetails = "select domain_object,workflow_master_id,request_master_id, request_stage, assigned_to_id from request_workflow_history where id="
				+ workflowService.getHistoryID() + "";
		log.debug("SQL : getRequestDetails > > " + getRequestDetails);

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getRequestDetails);
		log.debug("SQL : getRequestDetails > > " + rows);
		while (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			workflowService.setRequestID(hm.get("domain_object").toString());
			workflowService.setWorkflowID(hm.get("workflow_master_id")
					.toString());
			workflowService.setRequestTypeID(hm.get("request_master_id")
					.toString());
			workflowService.setStageID(hm.get("request_stage").toString());
			workflowService.setSfID(hm.get("assigned_to_id").toString());
		}
		log.debug("historyID:" + workflowService.getHistoryID()
				+ ", request ID:" + workflowService.getRequestID()
				+ ", Workflow ID:" + workflowService.getWorkflowID()
				+ ", request typeID:" + workflowService.getRequestTypeID()
				+ ", stage ID:" + workflowService.getStageID()
				+ ", assigned ID:" + workflowService.getSfID());

	}

	/**
	 * This method will be called for escalating the requests
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */

	public String escalatedRequests() throws Exception {
		log.debug("    <RequestProcess    Method            >>>escalatedRequests()        >");

		String escalatedList = "";
		String requestsList = "";

		/**
		 * First get the delegated requests
		 */
		String getEscalatedRequests = "select rwh.id,rwh.domain_object,rwh.workflow_master_id,rwh.request_master_id,rwh.request_stage,assigned_to_to,assigned_date from request_workflow_history rwh "
				+ "where id in (select min(id) from request_workflow_history where status_master_id=6 and to_date(sysdate,'dd-mon-yyyy HH24:MI:SS')>=(to_date(assigned_date,'dd-mon-yyyy HH24:MI:SS')+"
				+ getConfiguredValue(CPSConstants.ESCALATED_TIME)
				+ ""
				+ "group by domain_object)";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getEscalatedRequests);
		log.debug("SQL : getEscalatedRequests1 > > " + rows);
		HashMap<String, Object> hm;
		while (rows.size() > 0) {
			hm = (HashMap<String, Object>) rows.get(0);
			escalatedList += hm.get("id").toString() + ","
					+ hm.get("domain_object").toString() + ","
					+ hm.get("workflow_master_id").toString() + ","
					+ hm.get("request_master_id").toString() + ","
					+ hm.get("request_stage") + "," + hm.get("assigned_to_id")
					+ "#";
			requestsList += hm.get("domain_object").toString() + ",";
		}

		/**
		 * get the pending requests
		 */

		getEscalatedRequests = "select rwh.id,rwh.domain_object, rwh.workflow_master_id,rwh.request_master_id,rwh.request_stage,assigned_to_id,assigned_date from request_workflow_history rwh "
				+ "where id in (select min(id) from request_workflow_history where status_master_id=3 and domain_object not in ("
				+ requestsList.subSequence(0, requestsList.length() - 1)
				+ ") "
				+ "and to_date(sysdate,'dd-mon-yyyy HH24:MI:SS')>=(to_date(assigned_date,'dd-mon-yyyy HH24:MI:SS')+"
				+ getConfiguredValue(CPSConstants.ESCALATED_TIME)
				+ ") group by request_master_id)";

		List<java.util.Map<String, Object>> rows1 = jdbcTemplate
				.queryForList(getEscalatedRequests);
		log.debug("SQL : getEscalatedRequests2 > > " + rows1);
		HashMap<String, Object> hm1;
		while (rows1.size() > 0) {
			hm1 = (HashMap<String, Object>) rows.get(0);
			escalatedList += hm1.get("id") + "," + hm1.get("domain_object")
					+ "," + hm1.get("workflow_master_id") + ","
					+ hm1.get("request_master_id") + ","
					+ hm1.get("request_stage") + ","
					+ hm1.get("assigned_to_id") + "#";
		}

		String[] escalatedArr = escalatedList.split("#");
		for (int i = 0; i < escalatedArr.length; i++) {
			String[] reqDetails = escalatedArr[i].split(",");

			/*
			 * String escalatedWorkflowDetails =
			 * "select escalation_to, escalation_relationship_type from workflow where workflow_id="
			 * + reqDetails[2] + " and stage_id=" + reqDetails[4] + " ";
			 */

			String escalatedWorkflowDetails = "select relative_escalation_to_id, absolute_escalation_to_id, escalation_relationship_type_id from workflow "
					+ "where workflow_master_id="
					+ reqDetails[2]
					+ " and stage_id=" + reqDetails[4] + " ";

			List<java.util.Map<String, Object>> rows2 = jdbcTemplate
					.queryForList(escalatedWorkflowDetails);
			log.debug("SQL : escalatedWorkflowDetails > > " + rows2);
			HashMap<String, Object> hm2;
			if (rows2.size() > 0) {
				hm2 = (HashMap<String, Object>) rows2.get(0);
				if (CPSUtils.compareStrings(
						hm2.get("escalation_relationship_type_id").toString(),
						"1")) {
					// RELATIVE
					workflowService.setParentID(getWorkflowService()
							.getAssignedID(workflowService.getSfID(),
									hm2.get("escalation_to").toString(), null,
									CPSConstants.ESCALATED, null,
									workflowService.getRequestID()));
				} else if (CPSUtils.compareStrings(
						hm2.get("escalation_relationship_type_id").toString(),
						"2")) {
					// ABSOLUTE
					workflowService.setParentID(workflowService
							.getInstanceLogin(hm2.get("escalation_to")
									.toString()));
				}

				// escalation record is exist for this workflow & stage
				workflowService.setMessage(updateRequestHistory(reqDetails[0],
						CPSConstants.ESCALATED, workflowService.getIpAddress(),
						"", workflowService.getApprovedDate()));// ESCALATED

				if (CPSUtils.compareStrings(workflowService.getMessage(),
						CPSConstants.SUCCESS)) {
					// Assigning the request
					workflowService.setMessage(assignRequest(workflowService));
				}
			}

		}
		return workflowService.getMessage();
	}

	/**
	 * This method will return the value of the configured name
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	// String configuredValue;

	public String getConfiguredValue(String name) throws Exception {

		// String getEscalatedTime =
		// "select value from t_configuration_details where name="+ name + "";

		ConfigurationDetails configurationDetails = configurationDetailsRepository
				.findOneByName(name);

		return configurationDetails.getValue();
	}

	/**
	 * This method will return the request delegated history ID & his sfid
	 * 
	 * @param historyID
	 * @return
	 * @throws Exception
	 */
	public String checkDelegatedRequestOrNot(String historyID) throws Exception {
		String delegatedDetails = null;
		log.debug("    <RequestProcess    Method            >>>checkDelegatedRequestOrNot(String historyID)        >");

		String getDelegatedUserSfid = "select rwh.assigned_to_id, rwh.id from request_workflow_history rwh, status_master sm "
				+ "where rwh.domain_object=(select domain_object from request_workflow_history where id="
				+ historyID
				+ ") "
				+ "and rwh.assigned_to_id=(select assigned_from_id from request_workflow_history where id="
				+ historyID
				+ ") and sm.id=rwh.status_master_id and upper(sm.status)=upper('delegated')";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getDelegatedUserSfid);
		log.debug("SQL : getDelegatedUserSfid > > " + rows);

		if (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			delegatedDetails = hm.get("id") + ","
					+ hm.get("assigned_to_id").toString();
		}
		log.debug("delegated details    history ID,assigned to:::"
				+ delegatedDetails);

		return delegatedDetails;
	}

	/**
	 * Check this request was delegated earlier or not, If he was delegated then
	 * we should get the history ID
	 * 
	 * @param historyID
	 * @return
	 * @throws Exception
	 */
	public String checkAlreadyDelegatedOrNot(String historyID) throws Exception {
		log.debug("    <RequestProcess    Method            >>>checkAlreadyDelegatedOrNot(String historyID)        >");

		String delegatedHistoryID = null;

		String alreadyDelegatedList = "select id from request_workflow_history where assigned_from_id=(select assigned_to_id "
				+ "from request_workflow_history rwh,status_master sm where rwh.id="
				+ historyID
				+ " and sm.id=rwh.status_master_id and "
				+ "upper(sm.status)=upper("
				+ CPSConstants.DELEGATED
				+ ")) and domain_object=(select domain_object from request_workflow_history where id="
				+ historyID + ")";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(alreadyDelegatedList);
		log.debug("SQL : alreadyDelegatedList > > " + rows);
		if (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			delegatedHistoryID = hm.get("id").toString();
		}
		log.debug("delegatedHistoryID    " + delegatedHistoryID);

		return delegatedHistoryID;
	}

	/**
	 * Change the delegated or pending status to approved status
	 * 
	 * @param historyID
	 * @return
	 * @throws Exception
	 */
	public void changeDelegateOrPendingToApprove(String historyID,
			String stageID) throws Exception {
		log.debug("::    <RequestProcess    Method            >>>changeDelegateOrPendingToApprove(String historyID)        >");
		try {

			String updateDelegatedStatus = "update request_workflow_history rwh set rwh.status_master_id="
					+ CPSConstants.STATUSAPPROVED
					+ ", rwh.request_stage="
					+ stageID + " where rwh.id=" + historyID;
			Integer i = jdbcTemplate.update(updateDelegatedStatus);
			log.debug("SQL : alreadyDelegatedList > > " + i);

		} catch (Exception e) {
			throw e;
		}
	}


	/**
	 * Change the pending status to delegated status
	 * 
	 * @param historyID
	 * @return
	 * @throws Exception
	 */
	public String changePendingToDelegate(String historyID) throws Exception {
		log.debug("    <RequestProcess    Method            >>>changePendingToDelegate(String historyID)        >");

		String message = null;

		String updateDelegatedStatus = "update request_workflow_history rwh2 set rwh2.status_master_id=6 where rwh2.id in ( select rwh.id from request_workflow_history rwh,"
				+ "status_master sm where rwh.domain_object=(select rwh1.domain_object from request_workflow_history rwh1 where rwh1.id="
				+ historyID
				+ ") and rwh.status_master_id=sm.id and upper(sm.status)=upper("
				+ CPSConstants.PENDING + ") and rwh.id!=" + historyID + ")";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(updateDelegatedStatus);
		log.debug("SQL : updateDelegatedStatus > > " + rows);

		return message;

	}

	/**
	 * This method will be called when a user approve the request that was
	 * assigned to him
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 *             //
	 */
	public WorkflowService approvedRequest() throws Exception {
		log.debug("    <RequestProcess    Method            >>>approvedRequest(RequestBean rb)        >");
		String delegatedDetails = "";

		// update status to approve for all the txn details those are having

		// changeDelegateOrPendingToApprove(workflowService.getHistoryID(),workflowService.getStageID());

		checkAssignedWorkflow(workflowService);
		// check the role based workflow
		log.debug("Checking Role Based workflow");
		// ((RoleBasedWorkflowService)workflowService).getInstanceWorkflow();
		if (CPSUtils.isNullOrEmpty(workflowService.getWorkflowID())) {
			/**
			 * Work Flow Not yet decided, till now, the request is in internal
			 * workflow
			 */
			log.debug("::workflow not yet assigned");
			// workflowService.setMessage(updateRequestHistory(workflowService.getHistoryID(),
			// CPSConstants.APPROVED, workflowService.getIpAddress(),
			// workflowService.getRemarks(), workflowService.getStageID()));
			workflowService.setMessage(updateRequestHistory(
					workflowService.getHistoryID(), CPSConstants.APPROVED,
					workflowService.getIpAddress(),
					workflowService.getRemarks(), workflowService.getApprovedDate()));

			workflowService.setMessage(initWorkflow());
		} else {
			/**
			 * work flow already started for this request so we need to follow
			 * the next stage
			 */
			log.debug("workflow already assigned");
			// workflowService.setStatus((Integer.parseInt(workflowService.)+1));
			// check whether this workflow was completed or not
			if (checkNextWorkFlowStage(workflowService)) {
				log.debug("workflow not yet completed");
				workflowService.setMessage(updateRequestHistory(
						workflowService.getHistoryID(), CPSConstants.APPROVED,
						workflowService.getIpAddress(),
						workflowService.getRemarks(),
						workflowService.getApprovedDate()));

				if (!CPSUtils.isNullOrEmpty(delegatedDetails)) {
					// If the request was delegated
					workflowService.setMessage(updateRequestHistory(
							delegatedDetails.split(",")[0],
							CPSConstants.APPROVED,
							workflowService.getIpAddress(),
							workflowService.getRemarks(),
							workflowService.getApprovedDate()));
				}

				// Next work flow stage exists
				log.debug("check next workflow stage");
				// getDetailsAndProcessRequest();
			} else {
				/**
				 * Work flow completed Check whether the last workflow handover
				 * to any other workflow or not if not then check the previous
				 * workflow that this request was followed If every thing is
				 * completed then update it in the DB
				 */
				log.debug("workflow completed");
				String handOverWorkFlow = getHandOverWorkFlow(workflowService
						.getWorkflowID());
				if (CPSUtils.isNullOrEmpty(handOverWorkFlow)
						|| CPSUtils.compareStrings(handOverWorkFlow, "0")) {

					/**
					 * There is no Hand over workflow Check the previous
					 * workflow that the current request was followed
					 */
					log.debug("There is no Hand over workflow");
					checkPreviousWorkflows();
					if (!CPSUtils.compareStrings(CPSConstants.SUCCESS,
							workflowService.getMessage())) {
						workflowService.setMessage(updateRequestHistory(
								workflowService.getHistoryID(),
								CPSConstants.COMPLETED,
								workflowService.getIpAddress(),
								workflowService.getRemarks(),
								workflowService.getApprovedDate()));
						log.debug("Workflows completed");

						if (!CPSUtils.isNullOrEmpty(delegatedDetails)) {
							// If the request was delegated
							workflowService.setMessage(updateRequestHistory(
									delegatedDetails.split(",")[0],
									CPSConstants.COMPLETED,
									workflowService.getIpAddress(),
									workflowService.getRemarks(),
									workflowService.getApprovedDate()));
						}
						/**
						 * No previous workflows were exists so update in the DB
						 */
						// getDetailsAndProcessRequest();
						log.debug("update txn details");
						workflowService.setMessage(CPSConstants.UPDATE);
					}
				} else {
					/**
					 * Workflow handover to another workflow set the stage as 1
					 */
					log.debug("Workflow handover to another workflow");
					workflowService.setStageID("1");
					workflowService.setWorkflowID(handOverWorkFlow);
					workflowService.setMessage(updateRequestHistory(
							workflowService.getHistoryID(),
							CPSConstants.APPROVED,
							workflowService.getIpAddress(),
							workflowService.getRemarks(),
							workflowService.getApprovedDate()));

					// get the next assigned parent ID
					// getDetailsAndProcessRequest();
				}
			}
		}
		workflowService.setStageID(String.valueOf((Integer
				.parseInt(workflowService.getStageID()) + 1)));

		getDetailsAndProcessRequest();
		return workflowService;
	}

	/**
	 * In this method we will get the parent ID of a particular workflow ID &
	 * stage And get the request type ID Assigns that request to next level
	 * 
	 * @param workflowService2
	 * @return
	 * @throws Exception
	 */
	public void getDetailsAndProcessRequest() throws Exception {
		log.debug("    <RequestProcess    Method            >>>getDetailsAndProcessRequest(RequestBean rb)        >");

		// Get the assigned sfid for a particular workflow & stage
		workflowService.getWorkflowAssignedSfid();
		// Get the request Type ID
		getRequestTypeDetails();
		// Assign the request
		workflowService.setMessage(assignRequest(workflowService));
	}

	/**
	 * In this method we will find the previous workflow that are already
	 * followed by the current request or not
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public void checkPreviousWorkflows() throws Exception {
		log.debug("    <RequestProcess    Method            >>>checkPreviousWorkflows(RequestBean rb)        >");

		String getPreviousWorkflows = "select tab1.id, tab1.workflow_master_id, rwh.request_stage, rwh.domain_object from (select domain_object, workflow_master_id, "
				+ "max(id) id from request_workflow_history where domain_object=(select domain_object from request_workflow_history where id= "
				+ workflowService.getHistoryID()
				+ ") group by domain_object, workflow_master_id) tab1, request_workflow_history rwh where rwh.id=tab1.id and rwh.id!="
				+ workflowService.getHistoryID() + " order by tab1.id desc";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getPreviousWorkflows);
		log.debug("SQL : getPreviousWorkflows > > " + rows);
		HashMap<String, Object> hm;
		while (rows.size() > 0) {
			hm = (HashMap<String, Object>) rows.get(0);
			if (CPSUtils.isNullOrEmpty(hm.get("workflow_master_id"))) {
				workflowService.setWorkflowID(hm.get("workflow_master_id")
						.toString());
				workflowService.setStageID(hm.get("request_stage").toString());
				workflowService.setRequestID(hm.get("domain_object")
						.toString());
				workflowService.setStageID(String.valueOf((Integer
						.parseInt(workflowService.getStageID()) + 1)));
				/**
				 * check whether this workflow stage was completed are not
				 */
				if (checkNextWorkFlowStage(workflowService)) {
					getDetailsAndProcessRequest();
				}
			}
		}
	}

	/**
	 * This method will return the hand over workflow
	 * 
	 * @param workFlowID
	 * @return
	 * @throws Exception
	 */
	public String getHandOverWorkFlow(String workFlowID) throws Exception {
		log.debug("    <RequestProcess    Method            >>>getHandOverWorkFlow(String workFlowID)        >");
		String handOverWorkflow = null;

		String getHandOverWorkFlow = "select to_workflow from workflow_master where id="
				+ workFlowID + " and status_master_id=2";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getHandOverWorkFlow);

		log.debug("SQL : getHandOverWorkFlow > > " + rows);
		HashMap<String, Object> hm;
		if (rows.size() > 0) {
			hm = (HashMap<String, Object>) rows.get(0);
			handOverWorkflow = hm.get("to_workflow").toString();
		}
		log.debug("handOverWorkflow    " + handOverWorkflow);
		return handOverWorkflow;
	}

	/**
	 * This method will check whether the request workflow next stage is exists
	 * or not
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public boolean checkNextWorkFlowStage(WorkflowService workflowService)
			throws Exception {
		log.debug("    <RequestProcess    Method            >>>checkNextWorkFlowStage(RequestBean rb)        >");
		boolean status = false;
		String checkNextStage = "select workflow_master_id from workflow where workflow_master_id="
				+ workflowService.getWorkflowID()
				+ " and stage_id="
				+ workflowService.getStageID() + "";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(checkNextStage);
		log.debug("SQL : checkNextStage > > " + checkNextStage + "\nResult:"
				+ rows);
		if (rows.size() > 0) {
			status = true;
		}
		log.debug("next workflow stage    " + status);
		return status;
	}

	/**
	 * Get the work flow history details of a particular history id
	 * 
	 * @param rb
	 * @return
	 * @return
	 * @throws Exception
	 */
	public WorkflowService checkAssignedWorkflow(WorkflowService workflowService)
			throws Exception {
		log.debug("    <RequestProcess    Method            >>>checkAssignedWorkflow(RequestBean rb)        >");

		// Request_workflow_history request_workflow_history =
		// request_workflow_historyRepository.findOne(new
		// Long(workflowService.getWorkflowID()));

		String checkWorkflow = "select workflow_master_id, request_stage, domain_object, applied_by_id from request_workflow_history where id="
				+ workflowService.getHistoryID();

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(checkWorkflow);
		log.debug("SQL : checkWorkflow > > " + rows);

		if (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);

			workflowService.setWorkflowID(hm.get("workflow_master_id")
					.toString());
			workflowService.setStageID(hm.get("request_stage").toString());
			workflowService.setRequestID(hm.get("domain_object").toString());
			//workflowService.setAppliedBy(hm.get("applied_by_id").toString());
		}
		return workflowService;
	}

	/**
	 * super admin is delegating the requests
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public String delegateRequests() throws Exception {

		String getSfid = "select erm.user_id from emp_role_mapping erm, org_role_instance ori where ori.departments_master_id="
				+ workflowService.getInstanceId()
				+ " and ori.status_master_d=2 and erm.org_role_instance_id=ori.id and erm.status_master_id=2 and ori.is_head=1";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(getSfid);

		if (rows.size() > 0) {
			HashMap<String, Object> hm = (HashMap<String, Object>) rows.get(0);
			workflowService.setParentID(hm.get("user_id").toString());

			String[] requests = workflowService.getRequestIDs().split(",");
			for (int i = 0; i < requests.length; i++) {
				workflowService.setRequestID(requests[i]);

				String getRequestDetails = "select id, workflow_master_id, request_master_id, request_stage from request_workflow_history where id="
						+ "(select max(id) from request_workflow_history where domain_object="
						+ workflowService.getRequestID() + ")";
				
				List<java.util.Map<String, Object>> rows1 = jdbcTemplate
						.queryForList(getRequestDetails);

				if (rows1.size() > 0) {
					HashMap<String, Object> hm1 = (HashMap<String, Object>) rows
							.get(0);
					workflowService.setWorkflowID(hm1.get("workflow_master_id")
							.toString());
					workflowService.setRequestTypeID(hm1.get(
							"request_master_id").toString());
					workflowService.setStageID(hm1.get("request_stage")
							.toString());

					// update the status to delegate
					String deletegate = "update request_workflow_history set status_master_id=6 where id="
							+ hm1.get("id") + "";

					List<java.util.Map<String, Object>> rows2 = jdbcTemplate
							.queryForList(deletegate);

					// workflowService.insertHistory();
				}
			}
		}
		return "";
	}

}

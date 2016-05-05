package com.callippus.water.erp.workflow.applicationtxn.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.WorkflowTxnDetails;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.repository.WorkflowStageMasterRepository;
import com.callippus.water.erp.repository.WorkflowTxnDetailsRepository;
import com.callippus.water.erp.workflow.request.service.RequestProcessService;
import com.callippus.water.erp.workflow.service.WorkflowService;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class CustDetailsChangeWorkflowService extends RequestProcessService {
	private final Logger log = LoggerFactory
			.getLogger(CustDetailsChangeWorkflowService.class);
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private WorkflowStageMasterRepository workflowStageMasterRepository;

	private String message;
	private WorkflowTxnDetails workflowTxnDetails;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public WorkflowTxnDetails getWorkflowTxnDetails() {
		return workflowTxnDetails;
	}

	public void setWorkflowTxnDetails(WorkflowTxnDetails workflowTxnDetails) {
		this.workflowTxnDetails = workflowTxnDetails;
	}



	@Inject
	EntityManager entityManager;

	@Inject
	private WorkflowService workflowService;

	@Inject
	private WorkflowTxnDetailsRepository workflowTxnDetailsRepository;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	public String createTxn(WorkflowTxnDetails workflowTxnDetails) throws Exception {
		log.debug("WorkflowTxnDetailsWorkflowService --> createTxn");
		String message = "";

		message = initWorkflow(workflowTxnDetails);

		return message;
	}

	public void validate(ApplicationTxn applicationTxn) {

		// Validation logic before saving domain object

	}



	public String initWorkflow(WorkflowTxnDetails workflowTxnDetails) throws Exception {
		log.debug("WorkflowTxnDetailsWorkflowService --> initWorkflow");
		String message = "";
		try {
			if(workflowTxnDetails.getRequestMaster().getId()==8){
				setRequestType(CPSConstants.CONNECTIONCATEGORY);
			}
			if(workflowTxnDetails.getRequestMaster().getId()==9){
				setRequestType(CPSConstants.PIPESIZE);
			}
			if(workflowTxnDetails.getRequestMaster().getId()==10){
				setRequestType(CPSConstants.CHANGENAME);
			}
			if(workflowTxnDetails.getRequestMaster().getId()==11){
				setRequestType(CPSConstants.CONNECTIONTERMINATION);
			}

			setMessage("success");

			if (getMessage().equals(CPSConstants.SUCCESS)) {

				/*
				 * inserting data into Request_workflow_history table
				 */
				workflowService.setDomain_object_id(workflowTxnDetails.getId()
						.toString());
				workflowService.setRequestID(getRequestID());
				workflowService.setRequestType(getRequestType());
				workflowService.setRequestTypeID(getRequestTypeID());

				message = super.initWorkflow();
			} else {
				setMessage(CPSConstants.FAILED);
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = CPSConstants.FAILED;
			throw e;
		}
		return message;
	}

	public void applicationTxnRequest(WorkflowTxnDetails workflowTxnDetails)
			throws Exception {
		log.debug("ApplicationTxnWorkflowDetails --> onSubmit --> param=saveRequestDetails");
		if (CPSUtils.compareStrings(message, CPSConstants.YES)) {
			setDesignationID(workflowService.getDesignationID());
			message = initWorkflow(workflowTxnDetails);
		}
	}

	/**
	 * check employee exist or not
	 */
	public String getEmpExist() throws Exception {
		try {
			message = workflowService.getEmpExist();
			if (CPSUtils.compareStrings(message, CPSConstants.NO)) {
				message = CPSConstants.INVALID;
			}
		} catch (Exception e) {
			message = CPSConstants.FAILED;
			e.printStackTrace();
			throw e;
		}
		return message;
	}

	/**
	 * for request approvel
	 **/

	@SuppressWarnings("unchecked")
	public String approvedWorkflowTxnDetailsRequest(WorkflowTxnDetails workflowTxnDetails)
			throws Exception {
		String message = null;
		List<RequestWorkflowHistory> l = null;
		try {
			Query query = entityManager.createQuery(
					"from RequestWorkflowHistory r where domainObject="
							+ workflowTxnDetails.getId(),
					RequestWorkflowHistory.class);

			l = query.getResultList();
			int i = l.size() - 1;
			workflowService.setHistoryID(l.get(i).getId().toString());
			workflowService.setDomain_object_id(l.get(i).getDomainObject()
					.toString());
			
			/*User user = workflowTxnDetails.getUser();
			if(user != null){
				workflowService.setRequestType(CPSConstants.WITHOUTMETER);
			}
			else
			{
				workflowService.setRequestType(CPSConstants.REQUISITION);
			}*/
			
			workflowService.getHistoryID();
			workflowService.setStageID(l.get(i).getRequestStage().toString());
			workflowService.setStatus(Integer.parseInt(l.get(i)
					.getStatusMaster().getId().toString()));
			approvedRequest();

		} catch (Exception e) {
			e.printStackTrace();

			message = CPSConstants.FAILED;
			throw e;
		}
		return message;
	}

	/*
	 * Decline a applicationTxn
	 */
	public void declineRequest(Long id) throws Exception {
		log.debug("ApplicationTxnWorkflowService --> declineRequest--" + id);

		DeclineApplicationTxnRequest(id);
		workflowService.getUserDetails();

		message = super.declinedRequest(workflowService.getHistoryID(),
				workflowService.getIpAddress(), workflowService.getRemarks());
	}

	@SuppressWarnings("unchecked")
	public String DeclineApplicationTxnRequest(Long id) throws Exception {
		String message = null;
		List<RequestWorkflowHistory> l = null;
		try {
			Query query = entityManager.createQuery(
					"from RequestWorkflowHistory r where domainObject="
							+ id, RequestWorkflowHistory.class);

			l = query.getResultList();
			int i = l.size() - 1;
			workflowService.setHistoryID(l.get(i).getId().toString());

		} catch (Exception e) {
			e.printStackTrace();

			message = CPSConstants.FAILED;
			throw e;
		}

		return message;
	}

	/**
	 * This wil update the ApplicationTxn to final stage
	 */
	public void updateApplicationTxn(Long id) {
		log.debug("updateApplicationTxn(): for final stage ");
		String sql = "update applicationTxn set status=2 where id=" + id;
		log.debug("Query: " + sql);
		jdbcTemplate.update(sql);
	}

	/**
	 * for request declined & cancel
	 **/
	public String declainedRequest(String requestID, String status)
			throws Exception {

		String message = null;
		/*
		 * try{ reqDTO =
		 * (MTRequestDetailsDTO)session.createCriteria(MTRequestDetailsDTO
		 * .class).add(Expression.eq("requestID", requestID)).uniqueResult();
		 * if(!CPSUtils.isNullOrEmpty(reqDTO)){
		 * if(CPSUtils.compareStrings(CPSConstants.STATUSCANCELLED, status)) {
		 * String sql =
		 * "update MTRequestDetailsDTO set status=? where requestID=?";
		 * session.createQuery(sql).setString(0,
		 * CPSConstants.STATUSCANCELLED).setString(1,
		 * requestID).executeUpdate(); message = CPSConstants.SUCCESS; }
		 * if(CPSUtils.compareStrings(CPSConstants.STATUSDECLINED, status)) {
		 * String sql =
		 * "update MTRequestDetailsDTO set status=? where requestID=?";
		 * session.createQuery(sql).setString(0,
		 * CPSConstants.STATUSDECLINED).setString(1, requestID).executeUpdate();
		 * message = CPSConstants.SUCCESS; } }
		 * 
		 * }catch (Exception e) { e.printStackTrace();
		 * hibernateUtils.rollbackTransaction(); message = CPSConstants.FAILED;
		 * throw e; }
		 */

		return message;
	}
	
	
	/*
	 * Method to approve a request
	 */
	public void approveRequest(Long id, String remarks) throws Exception{
		log.debug("ApplicationTxnCustomRepository -------- approveRequest(): {}");
		
		workflowService.getUserDetails();
	    
		WorkflowTxnDetails workflowTxnDetails = workflowTxnDetailsRepository.findOne(id);
	    workflowService.setRemarks(remarks);  
	    /*Integer status = applicationTxn.getStatus();
	    status +=1;
	    workflowTxnDetails.setStatus(status);
        workflowService.setRequestStatus(status);*/
        //applicationTxnWorkflowService.
        approvedWorkflowTxnDetailsRequest(workflowTxnDetails);

        /*if(workflowService.getRequestAt()!=null){
        	Long uid = Long.valueOf(workflowService.getRequestAt()) ;
        	workflowTxnDetails.setRequestAt(userRepository.findById(uid));
        }*/
        workflowTxnDetailsRepository.save(workflowTxnDetails);
	}

}

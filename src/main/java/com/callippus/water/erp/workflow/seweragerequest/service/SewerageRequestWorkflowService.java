package com.callippus.water.erp.workflow.seweragerequest.service;

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
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.SewerageRequest;
import com.callippus.water.erp.repository.SewerageRequestRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.workflow.request.service.RequestProcessService;
import com.callippus.water.erp.workflow.service.WorkflowService;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class SewerageRequestWorkflowService extends RequestProcessService {
	private final Logger log = LoggerFactory
			.getLogger(SewerageRequestWorkflowService.class);
	
	
	
	@Inject
	private UserRepository userRepository;

	private String message;
	private SewerageRequest sewerageRequest;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public SewerageRequest getSewerageRequest() {
		return sewerageRequest;
	}

	public void setSewerageRequest(SewerageRequest sewerageRequest) {
		this.sewerageRequest = sewerageRequest;
	}





	@Inject
	EntityManager entityManager;

	@Inject
	private WorkflowService workflowService;

	@Inject
	private SewerageRequestRepository sewerageRequestRepository;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	public String createTxn(SewerageRequest sewerageRequest) throws Exception {
		log.debug("sewerageRequestWorkflowService --> createTxn");
		String message = "";

		message = initWorkflow(sewerageRequest);

		return message;
	}



	public void saveApplicationTxn(SewerageRequest sewerageRequest) {
		sewerageRequestRepository.save(sewerageRequest);
	}

	public String initWorkflow(SewerageRequest sewerageRequest) throws Exception {
		log.debug("sewerageRequestWorkflowService --> initWorkflow");
		String message = "";
		try {
			
				setRequestType(CPSConstants.SEWERAGEREQUEST);//

			setMessage("success");

			if (getMessage().equals(CPSConstants.SUCCESS)) {

				/*
				 * inserting data into Request_workflow_history table
				 */
				workflowService.setDomain_object_id(sewerageRequest.getId()
						.toString());
				workflowService.setRequestID(getRequestID());
				workflowService.setRequestType(getRequestType());
				workflowService.setRequestTypeID(getRequestTypeID());

				message = super.initWorkflow();
					setMessage(CPSConstants.SEWERAGEREQUEST);//

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

	

	/**
	 * check employee exist or not
	 */
	/*public String getEmpExist() throws Exception {
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
	}*/

	/**
	 * for request approvel
	 **/

	@SuppressWarnings("unchecked")
	public String approvedSewerageRequest(SewerageRequest sewerageRequest)
			throws Exception {
		String message = null;
		List<RequestWorkflowHistory> l = null;
		try {
			Query query = entityManager.createQuery(
					"from RequestWorkflowHistory r where domainObject="
							+ sewerageRequest.getId(),
					RequestWorkflowHistory.class);

			l = query.getResultList();
			int i = l.size() - 1;
			workflowService.setHistoryID(l.get(i).getId().toString());
			workflowService.setDomain_object_id(l.get(i).getDomainObject()
					.toString());
			workflowService.setRequestType(CPSConstants.SEWERAGEREQUEST);
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
	 * Decline a SewerageRequest
	 */
	public void declineRequest(Long id) throws Exception {
		log.debug("sewerageRequestWorkflowService --> declineRequest--" + id);

		declineSewerageRequest(id);
		workflowService.getUserDetails();

		message = super.declinedRequest(workflowService.getHistoryID(),
				workflowService.getIpAddress(), workflowService.getRemarks(), workflowService.getApprovedDate()
				);
	}

	@SuppressWarnings("unchecked")
	public String declineSewerageRequest(Long id) throws Exception {
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


	
	
	
	/*
	 * Method to approve a request
	 */
	public void approveRequest(Long id, String remarks) throws Exception{
		log.debug("sewerageRequestWorkflowService -------- approveRequest(): {}");
		
		workflowService.getUserDetails();
	    
		SewerageRequest sewerageRequest = sewerageRequestRepository.findOne(id);
	    workflowService.setRemarks(remarks);  
	  
        approvedSewerageRequest(sewerageRequest);

       
        sewerageRequestRepository.save(sewerageRequest);
	}

}

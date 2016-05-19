package com.callippus.water.erp.workflow.meterchange.service;

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
import com.callippus.water.erp.domain.MeterChange;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.repository.MeterChangeRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.workflow.request.service.RequestProcessService;
import com.callippus.water.erp.workflow.service.WorkflowService;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class MeterChangeWorkflowService extends RequestProcessService {
	private final Logger log = LoggerFactory
			.getLogger(MeterChangeWorkflowService.class);
	
	
	
	@Inject
	private UserRepository userRepository;

	private String message;
	private MeterChange meterChange;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MeterChange getMeterChange() {
		return meterChange;
	}

	public void setMeterChange(MeterChange meterChange) {
		this.meterChange = meterChange;
	}



	@Inject
	EntityManager entityManager;

	@Inject
	private WorkflowService workflowService;

	@Inject
	private MeterChangeRepository meterChangeRepository;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	public String createTxn(MeterChange meterChange) throws Exception {
		log.debug("MeterChangeWorkflowService --> createTxn");
		String message = "";

		message = initWorkflow(meterChange);

		return message;
	}

	public void validate(MeterChange meterChange) {

		// Validation logic before saving domain object

	}

	public void saveApplicationTxn(MeterChange meterChange) {
		meterChangeRepository.save(meterChange);
	}

	public String initWorkflow(MeterChange meterChange) throws Exception {
		log.debug("MeterChangeWorkflowService --> initWorkflow");
		String message = "";
		try {
			
				setRequestType(CPSConstants.METERCHANGE);

			setMessage("success");

			if (getMessage().equals(CPSConstants.SUCCESS)) {

				/*
				 * inserting data into Request_workflow_history table
				 */
				workflowService.setDomain_object_id(meterChange.getId()
						.toString());
				workflowService.setRequestID(getRequestID());
				workflowService.setRequestType(getRequestType());
				workflowService.setRequestTypeID(getRequestTypeID());

				message = super.initWorkflow();
				if(meterChange.getApprovedDate() != null && meterChange.getUser() !=null){
					setMessage(CPSConstants.METERCHANGE);
				}

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

	/*public void meterChangeRequest(MeterChange meterChange)
			throws Exception {
		log.debug("MeterChangeWorkflowDetails --> onSubmit --> param=saveRequestDetails");
		if (CPSUtils.compareStrings(message, CPSConstants.YES)) {
			setDesignationID(workflowService.getDesignationID());
			message = initWorkflow(meterChange);
		}
	}*/

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
	public String approvedMeterChangeRequest(MeterChange meterChange)
			throws Exception {
		String message = null;
		List<RequestWorkflowHistory> l = null;
		try {
			Query query = entityManager.createQuery(
					"from RequestWorkflowHistory r where domainObject="
							+ meterChange.getId(),
					RequestWorkflowHistory.class);

			l = query.getResultList();
			int i = l.size() - 1;
			workflowService.setHistoryID(l.get(i).getId().toString());
			workflowService.setDomain_object_id(l.get(i).getDomainObject()
					.toString());
			workflowService.setRequestType(CPSConstants.METERCHANGE);
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
	 * Decline a MeterChange
	 */
	public void declineRequest(Long id) throws Exception {
		log.debug("MeterChangeWorkflowService --> declineRequest--" + id);

		declineMeterChangeRequest(id);
		workflowService.getUserDetails();

		message = super.declinedRequest(workflowService.getHistoryID(),
				workflowService.getIpAddress(), workflowService.getRemarks(), workflowService.getApprovedDate()
				);
	}

	@SuppressWarnings("unchecked")
	public String declineMeterChangeRequest(Long id) throws Exception {
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
		log.debug("meterChangeWorkflowService -------- approveRequest(): {}");
		
		workflowService.getUserDetails();
	    
		MeterChange meterChange = meterChangeRepository.findOne(id);
	    workflowService.setRemarks(remarks);  
	    /*Integer status = meterChange.getStatus();
	    status +=1;
	    meterChange.setStatus(status);
        workflowService.setRequestStatus(status);*/
        approvedMeterChangeRequest(meterChange);

        /*if(workflowService.getRequestAt()!=null){
        	Long uid = Long.valueOf(workflowService.getRequestAt()) ;
        	meterChange.setRequestAt(userRepository.findById(uid));
        }*/
        meterChangeRepository.save(meterChange);
	}

}

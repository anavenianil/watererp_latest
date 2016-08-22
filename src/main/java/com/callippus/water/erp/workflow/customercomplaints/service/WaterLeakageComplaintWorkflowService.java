package com.callippus.water.erp.workflow.customercomplaints.service;

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
import com.callippus.water.erp.domain.CustomerComplaints;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.WaterLeakageComplaint;
import com.callippus.water.erp.repository.CustomerComplaintsRepository;
import com.callippus.water.erp.workflow.request.service.RequestProcessService;
import com.callippus.water.erp.workflow.service.WorkflowService;


@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class WaterLeakageComplaintWorkflowService extends RequestProcessService{
	private final Logger log = LoggerFactory
			.getLogger(WaterLeakageComplaintWorkflowService.class);

	private String message;
	private CustomerComplaints customerComplaintsDetails;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomerComplaints getCustomerComplaintsDetails() {
		return customerComplaintsDetails;
	}

	public void setApplicationTxnDetails(CustomerComplaints customerComplaintsDetails) {
		this.customerComplaintsDetails = customerComplaintsDetails;
	}

	@Inject
	EntityManager entityManager;

	@Inject
	private WorkflowService workflowService;


	public String createTxn(WaterLeakageComplaint waterLeakageComplaint) throws Exception {
		log.debug("WaterLeakageComplaintWorkflowService --> createTxn");
		String message = "";
		message = initWorkflow(waterLeakageComplaint);
		return message;
	}


	public String initWorkflow(WaterLeakageComplaint waterLeakageComplaint) throws Exception {
		String message = "";
		try {
			setRequestType(CPSConstants.JOBCARD);
			setMessage("success");
			if (getMessage().equals(CPSConstants.SUCCESS)) {

				/*
				 * inserting data into Request_workflow_history table
				 */
				workflowService.setDomain_object_id(waterLeakageComplaint.getId().toString());
				workflowService.setRequestID(getRequestID());
				workflowService.setRequestType(getRequestType());
				workflowService.setRequestTypeID(getRequestTypeID());

				message = super.initWorkflow();

				setMessage(CPSConstants.CUSTOMERCOMPLAINTS);

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
	 * for request approvel
	 **/

	@SuppressWarnings("unchecked")
	public String approvedWaterLeakageComplaints(WaterLeakageComplaint waterLeakageComplaint)
			throws Exception {
		String message = null;
		List<RequestWorkflowHistory> l = null;
		try {
			Query query = entityManager.createQuery(
					"from RequestWorkflowHistory r where domainObject="
							+ waterLeakageComplaint.getId(),
					RequestWorkflowHistory.class);

			l = query.getResultList();
			int i = l.size() - 1;
			workflowService.setHistoryID(l.get(i).getId().toString());
			workflowService.setDomain_object_id(l.get(i).getDomainObject()
					.toString());
			
			workflowService.setRequestType(CPSConstants.JOBCARD);
			
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
	 * Decline a WaterLeakageComplaint
	 */
	public void declineRequest(Long id) throws Exception {
		log.debug("waterLeakageComplaintWorkflowService --> declineRequest--" + id);

		declineWaterLeakageComplaint(id);
		workflowService.getUserDetails();

		message = super.declinedRequest(workflowService.getHistoryID(),
				workflowService.getIpAddress(), workflowService.getRemarks(),workflowService.getApprovedDate());
	}

	
	@SuppressWarnings("unchecked")
	public String declineWaterLeakageComplaint(Long id) throws Exception {
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

}

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
import com.callippus.water.erp.repository.CustomerComplaintsRepository;
import com.callippus.water.erp.workflow.request.service.RequestProcessService;
import com.callippus.water.erp.workflow.service.WorkflowService;


@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Service
public class CustomerComplaintsWorkflowService extends RequestProcessService{
	private final Logger log = LoggerFactory
			.getLogger(CustomerComplaintsWorkflowService.class);

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

	@Inject
	private CustomerComplaintsRepository customerComplaintsRepository;

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	public String createTxn(CustomerComplaints customerComplaints) throws Exception {
		log.debug("ApplicationTxnWorkflowService --> createTxn");
		String message = "";

		message = initWorkflow(customerComplaints);

		return message;
	}

	public void validate(CustomerComplaints applicationTxn) {

		// Validation logic before saving domain object

	}

	public void saveApplicationTxn(CustomerComplaints customerComplaints) {
		customerComplaintsRepository.save(customerComplaints);
	}

	public String initWorkflow(CustomerComplaints customerComplaints) throws Exception {
		String message = "";
		try {

			String complainType = customerComplaints.getComplaintTypeMaster().getComplaintType();
			if(CPSConstants.INCORRECTBILL.equalsIgnoreCase(complainType)){
				setRequestType(CPSConstants.INCORRECTBILL);
			}
			else
			if(CPSConstants.WATERLEAKAGE.equalsIgnoreCase(complainType)){
					setRequestType(CPSConstants.WATERLEAKAGE);
			}
			else if(CPSConstants.SERVICEUNAVAILABILITY.equalsIgnoreCase(complainType)){
					setRequestType(CPSConstants.SERVICEUNAVAILABILITY);
			}
			//setRequestType(CPSConstants.CUSTOMERCOMPLAINTS);
			//setRequestTypeID(CPSConstants.CUSTOMERCOMPLAINTSID);
			

			setMessage("success");

			if (getMessage().equals(CPSConstants.SUCCESS)) {

				/*
				 * inserting data into Request_workflow_history table
				 */
				workflowService.setDomain_object_id(customerComplaints.getId().toString());
				workflowService.setRequestID(getRequestID());
				workflowService.setRequestType(getRequestType());
				workflowService.setRequestTypeID(getRequestTypeID());
				/*requisition contains login user id but there is customer in applicationtxn*/
				// workflowService.setAppliedBy(applicationTxn.getUser().getId().toString());
				

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

	public void applicationTxnRequest(CustomerComplaints customerComplaints)
			throws Exception {
		log.debug("ApplicationTxnWorkflowDetails --> onSubmit --> param=saveRequestDetails");
		if (CPSUtils.compareStrings(message, CPSConstants.YES)) {
			setDesignationID(workflowService.getDesignationID());
			message = initWorkflow(customerComplaints);
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
	public String approvedCustomerComplaintsRequest(CustomerComplaints customerComplaints)
			throws Exception {
		String message = null;
		List<RequestWorkflowHistory> l = null;
		try {
			Query query = entityManager.createQuery(
					"from RequestWorkflowHistory r where domainObject="
							+ customerComplaints.getId(),
					RequestWorkflowHistory.class);

			l = query.getResultList();
			int i = l.size() - 1;
			workflowService.setHistoryID(l.get(i).getId().toString());
			workflowService.setDomain_object_id(l.get(i).getDomainObject()
					.toString());
			
			String complainType = customerComplaints.getComplaintTypeMaster().getComplaintType();
			if(CPSConstants.INCORRECTBILL.equalsIgnoreCase(complainType)){
				workflowService.setRequestType(CPSConstants.INCORRECTBILL);
			}else
			if(CPSConstants.WATERLEAKAGE.equalsIgnoreCase(complainType)){
				workflowService.setRequestType(CPSConstants.WATERLEAKAGE);
			}else
			if(CPSConstants.SERVICEUNAVAILABILITY.equalsIgnoreCase(complainType)){
				workflowService.setRequestType(CPSConstants.SERVICEUNAVAILABILITY);
			}
			//workflowService.setRequestType(CPSConstants.CUSTOMERCOMPLAINTS);
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
				workflowService.getIpAddress(), workflowService.getRemarks(),workflowService.getApprovedDate());
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

}

package com.callippus.water.erp.domain;

import java.time.ZonedDateTime;
import java.util.List;

public class WorkflowDTO {
	
	//private WorkflowTxnDetails workflowTxnDetails;
	private List<WorkflowTxnDetails> workflowTxnDetailss;
	private RequestWorkflowHistory requestWorkflowHistory;
	private ConnectionTerminate connectionTerminate;
	private Customer customer;
	private Receipt receipt;
	private String remarks;
	private ZonedDateTime approvedDate;
	private ApplicationTxn applicationTxn;
	private String actionType;
	
	
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	/*public WorkflowTxnDetails getWorkflowTxnDetails() {
		return workflowTxnDetails;
	}
	public void setWorkflowTxnDetails(WorkflowTxnDetails workflowTxnDetails) {
		this.workflowTxnDetails = workflowTxnDetails;
	}*/
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<WorkflowTxnDetails> getWorkflowTxnDetailss() {
		return workflowTxnDetailss;
	}
	
	public void setWorkflowTxnDetailss(List<WorkflowTxnDetails> workflowTxnDetailss) {
		this.workflowTxnDetailss = workflowTxnDetailss;
	}
	
	public RequestWorkflowHistory getRequestWorkflowHistory() {
		return requestWorkflowHistory;
	}
	public void setRequestWorkflowHistory(
			RequestWorkflowHistory requestWorkflowHistory) {
		this.requestWorkflowHistory = requestWorkflowHistory;
	}
	
	public ConnectionTerminate getConnectionTerminate() {
		return connectionTerminate;
	}
	public void setConnectionTerminate(ConnectionTerminate connectionTerminate) {
		this.connectionTerminate = connectionTerminate;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public ZonedDateTime getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(ZonedDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}
	public ApplicationTxn getApplicationTxn() {
		return applicationTxn;
	}
	public void setApplicationTxn(ApplicationTxn applicationTxn) {
		this.applicationTxn = applicationTxn;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	

}

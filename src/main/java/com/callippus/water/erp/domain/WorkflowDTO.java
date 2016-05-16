package com.callippus.water.erp.domain;

import java.util.List;

public class WorkflowDTO {
	
	//private WorkflowTxnDetails workflowTxnDetails;
	private List<WorkflowTxnDetails> workflowTxnDetailss;
	private RequestWorkflowHistory requestWorkflowHistory;
	private ConnectionTerminate connectionTerminate;
	private String remarks;
	
	/*public WorkflowTxnDetails getWorkflowTxnDetails() {
		return workflowTxnDetails;
	}
	public void setWorkflowTxnDetails(WorkflowTxnDetails workflowTxnDetails) {
		this.workflowTxnDetails = workflowTxnDetails;
	}*/
	
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
	
	

}

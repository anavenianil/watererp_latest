package com.callippus.water.erp.workflow.applicationtxn.service;

public class ApplicationTxnWorkflowServiceBean {
	
	private String requestID;
	private String requestType;
	private String requestTypeID;
	private String message;
	
	
	
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}

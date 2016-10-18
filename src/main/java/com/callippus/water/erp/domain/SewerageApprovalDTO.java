package com.callippus.water.erp.domain;

import java.time.ZonedDateTime;
import java.util.List;

public class SewerageApprovalDTO {
	
	
	private String remarks;
	private ZonedDateTime approvedDate;
	private SewerageRequest sewerageRequest;
	private Receipt receipt;
	
	
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
	public SewerageRequest getSewerageRequest() {
		return sewerageRequest;
	}
	public void setSewerageRequest(SewerageRequest sewerageRequest) {
		this.sewerageRequest = sewerageRequest;
	}
	public Receipt getReceipt() {
		return receipt;
	}
	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	
	
	

}

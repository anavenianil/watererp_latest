package com.callippus.water.erp.domain;

import java.time.ZonedDateTime;


public class JobCardDTO {
	
	private Long domainId;
	private ZonedDateTime approvedDate;
	private String remarks;
	
	private BurstComplaint burstComplaint;
	
	
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public ZonedDateTime getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(ZonedDateTime approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public BurstComplaint getBurstComplaint() {
		return burstComplaint;
	}
	public void setBurstComplaint(BurstComplaint burstComplaint) {
		this.burstComplaint = burstComplaint;
	}
	
}

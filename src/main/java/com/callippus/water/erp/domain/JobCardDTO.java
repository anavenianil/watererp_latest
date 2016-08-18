package com.callippus.water.erp.domain;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;


public class JobCardDTO {
	
	private Long domainId;
	private ZonedDateTime approvedDate;
	private String remarks;
	
	private Set<JobCardItemRequirement> jobCardItemRequirements;
	private BurstComplaint burstComplaint;
	private WaterLeakageComplaint waterLeakageComplaint;
	
	
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
	public Set<JobCardItemRequirement> getJobCardItemRequirements() {
		return jobCardItemRequirements;
	}
	public void setJobCardItemRequirements(Set<JobCardItemRequirement> jobCardItemRequirements) {
		this.jobCardItemRequirements = jobCardItemRequirements;
	}
	public WaterLeakageComplaint getWaterLeakageComplaint() {
		return waterLeakageComplaint;
	}
	public void setWaterLeakageComplaint(WaterLeakageComplaint waterLeakageComplaint) {
		this.waterLeakageComplaint = waterLeakageComplaint;
	}
	
}

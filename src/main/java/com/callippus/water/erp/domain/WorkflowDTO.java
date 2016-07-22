package com.callippus.water.erp.domain;

import java.util.List;


public class WorkflowDTO {
	
	private WorkflowMaster workflowMaster;
	private Workflow workflow;
	private String workflowName;
	private List<Workflow> workflows;
	
	public WorkflowMaster getWorkflowMaster() {
		return workflowMaster;
	}
	public void setWorkflowMaster(WorkflowMaster workflowMaster) {
		this.workflowMaster = workflowMaster;
	}
	public Workflow getWorkflow() {
		return workflow;
	}
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}
	public String getWorkflowName() {
		return workflowName;
	}
	public void setWorkflowName(String workflowName) {
		this.workflowName = workflowName;
	}
	public List<Workflow> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(List<Workflow> workflows) {
		this.workflows = workflows;
	}
	
	
}

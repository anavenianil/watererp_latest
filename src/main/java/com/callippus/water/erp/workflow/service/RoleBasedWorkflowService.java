package com.callippus.water.erp.workflow.service;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.common.CPSConstants;

@Scope(value="session",proxyMode=ScopedProxyMode.TARGET_CLASS)
@Service
@Transactional
public class RoleBasedWorkflowService {//extends WorkflowService {
	private static Log log = LogFactory.getLog(RoleBasedWorkflowService.class);
	
	@Inject
	WorkflowService workflowService;
	/**
	 * This method will return workflowid of an instance
	 * 
	 * @param rb
	 * @return
	 * @throws Exception
	 */
	public void initWorkflow() throws Exception {
		log.debug("::<<<RoleBasedWorkflowService<<<<<<<<Method>>>>>>>>>>>>>>>initWorkflow()>>>>>>>>>");
		try {
			workflowService.setWorkflowType(CPSConstants.ROLE);
			workflowService.setWorkflowID(workflowService.decideWorkFlow(
					workflowService.getRequestTypeID(),
					workflowService.getSfID(), workflowService.getOrgRoleID()));
			workflowService.setStageID("1");
			workflowService.getWorkflowAssignedSfid();
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	
	
	
	/*public String getInstanceWorkflow() throws Exception {
		String instanceID = null;
		instanceID = getInstanceID();
			if (!CPSUtils.isNullOrEmpty(instanceID)) {

			String checkRoleBasedWorkflow = "select workflow_master_id from t_role_workflow_mapping rwm, t_request_master rm where rm.id=rwm.request_master_id "
					+ "and upper(rm.request_type)=upper('"+getRequestType()+"') and rwm.org_role_instance_id="+instanceID+" and rwm.status_master_id=2";

			Query query = entityManager.createQuery(checkRoleBasedWorkflow,
					String.class);

			setWorkflowID((String) query.getSingleResult());
			return getWorkflowID();
		}

		return null;
	}


	 *
	 * This method will return the instance ID of a particular SFID
	 * 
	 * @param rb
	 * 
	 * @return
	 * 
	 * @throws Exception
	 
	
	
	public String getInstanceID() throws Exception {
		String instanceID = null;
		String officeID = null;
		ArrayList<String> noOfInstanceValues = null;
		ArrayList<String> assignedEmployeeInstances = null;
		
			noOfInstanceValues = new ArrayList<String>();
			assignedEmployeeInstances = new ArrayList<String>();

			*//**
			 * Some of the users are head for two instances in this case we need
			 * to get the role based work flow depends on the requester
			 *//*
			// first check the no of instances for the current employee
			String noOfInstances = "select org_role_instance_id from t_emp_role_mapping where user_d="+getSfID()+" and parent_user_id_id is null and status_master_id=2";
			Query query = entityManager
					.createQuery(noOfInstances, String.class);
			noOfInstanceValues = (ArrayList<String>) query.getResultList();

			if (noOfInstanceValues.size() == 0) {
				*//**
				 * This employee is not an instance head so there is no concept
				 * of role based workflow
				 *//*
			instanceID = null;

			} else if (noOfInstanceValues.size() == 1) {
				// This employee is head for only one instance
				instanceID = noOfInstanceValues.get(0);

			} else {
				*//**
				 * This employee is head for more than one instance, so we need
				 * to get the assigned employee office id & decide the role
				 * instance id
				 * 
				 *//*

				for (int i = 0; i < noOfInstanceValues.size(); i++) {
					instanceID = noOfInstanceValues.get(i);

					String getRequesterOfficeID = "select office_id from t_emp_master where user_id= (select assigned_from_id from t_request_workflow_history "
							+ "where id=(select min(id) from t_request_workflow_history where requisition_id=(select requisition_id from "
							+ "t_request_workflow_history where id="+getRequestID();
					ArrayList<String> officeIdList = null;
					Query query1 = entityManager.createQuery(noOfInstances,
							String.class);
					officeIdList = (ArrayList<String>) query1.getResultList();

					if (officeIdList.size() == 1) {
						// If the requester is normal employee
						officeID = officeIdList.get(0);
					} else {
						// If requester is not a normal employee (instance
						// employee)
						ArrayList<String> orgRoles = null;

						String getRequesterInstanceIDs = "select org_role_instance_id from t_emp_role_mapping where user_id=(select assigned_from_id from "
								+ "t_request_workflow_history where id=(select min(id) from t_request_workflow_history where requisition_id="
								+ "(select requisition_id from t_request_workflow_history where id="+getRequestID()+")))";

						Query query2 = entityManager.createQuery(
								getRequesterInstanceIDs, String.class);
						orgRoles = (ArrayList<String>) query2.getResultList();

						for (String localOfficeID : orgRoles) {
							officeID = localOfficeID;
							if (CPSUtils.compareStrings(instanceID, officeID)) {
								break;
							}
						}
					}
					if (CPSUtils.compareStrings(instanceID, officeID)) {
						break;
					}
				}
				
				if (CPSUtils.isNullOrEmpty(instanceID)) {
					String ID= null;
					// Request was approved from some other instance head or his
					// delegated employee

					String assignedEmployeeType = null;
					// Check whether the request assigned by instance head or
					// his delegated employee
					String decideAssignedEmployeeType = "select case when ((select count(*) from t_emp_role_mapping where user_id=(select assigned_from_id from "
							+ "t_request_workflow_history where id="+ID+") and parent_user_id_id is null) =0) then 'normal' else 'instance' end employeetype";

					Query query1 = entityManager.createQuery(
							decideAssignedEmployeeType, String.class);

					assignedEmployeeType = (String) query1.getSingleResult();

					if (CPSUtils.compareStrings(assignedEmployeeType, "normal")) {
						// Assined employee is a normal employee so get his
						// office id from emp_master
						String getOfficeID = "select office_id from t_emp_master where user_id=(select assigned_from_id from t_request_workflow_history "
								+ "where id="+ID+") and status_master_id=2";
						Query query2 = entityManager.createQuery(getOfficeID,
								String.class);

						officeID = (String) query2.getSingleResult();

					} else {
						// assigned employee is an instance head, so get his
						// role_instance ID's and check with the present
						// employee instance ID

						String getAssignedEmployeeInstances = "select org_role_instance_id from t_emp_role_mapping where user_id=(select assigned_from_id "
								+ "from t_request_workflow_history where id="+ID+") and status_master_id=2";
						Query query2 = entityManager.createQuery(
								getAssignedEmployeeInstances, String.class);
						assignedEmployeeInstances = (ArrayList<String>) query2.getResultList();
					}
					for (int i = 0; i < noOfInstanceValues.size(); i++) {
						// Test the normal employee officeID
						if (CPSUtils.compareStrings(officeID,
								noOfInstanceValues.get(i))) {
							instanceID = noOfInstanceValues.get(i);
							break;
						}
					}

					if (CPSUtils.isNullOrEmpty(instanceID)) {
						// Assigned employee is an instance employee
						for (int i = 0; i < noOfInstanceValues.size(); i++) {
							for (int j = 0; j < assignedEmployeeInstances
									.size(); j++) {
								if (CPSUtils.compareStrings(
										assignedEmployeeInstances.get(j),
										noOfInstanceValues.get(i))) {
									instanceID = noOfInstanceValues.get(i);
									break;
								}
							}
							if (!CPSUtils.isNullOrEmpty(instanceID)) {
								break;
							}
							return instanceID;
						}
					}
				}
			}
			return instanceID;
	}*/
}

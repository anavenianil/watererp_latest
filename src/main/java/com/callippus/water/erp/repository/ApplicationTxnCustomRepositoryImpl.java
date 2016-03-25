package com.callippus.water.erp.repository;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;
import com.callippus.water.erp.workflow.service.WorkflowService;

@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ApplicationTxnCustomRepositoryImpl extends
		SimpleJpaRepository<ApplicationTxn, Long> implements
		ApplicationTxnCustomRepository {
	private final Logger log = LoggerFactory
			.getLogger(ApplicationTxnCustomRepositoryImpl.class);

	@Inject
	private WorkflowService workflowService;

	@Inject
	EntityManager entityManager;

	@Inject
	UserRepository userRepository;
	
	@Inject
	private StatusMasterRepository statusMasterRepository;
	
	@Inject
	private WorkflowMasterRepository workflowMasterRepository;
	
	@Inject
	private RequestMasterRepository requestMasterRepository;

	/*
	 * @Inject WorkflowServiceRepository workflowServiceRepository;
	 */

	@Autowired(required = true)
	private JdbcTemplate jdbcTemplate;

	@Inject
	public ApplicationTxnCustomRepositoryImpl(EntityManager entityManager) {
		super(ApplicationTxn.class, entityManager);
	}

	public ApplicationTxnCustomRepositoryImpl(
			Class<ApplicationTxn> domainClass, EntityManager em) {
		super(domainClass, em);
		// TODO Auto-generated constructor stub
		this.entityManager = entityManager;
	}

	/**
	 * List count of pending request
	 */
	public List<RequestCountDTO> countPendingRequests() throws Exception {
		log.debug("listAllPendingRequests: {}");
		workflowService.getUserDetails();
		String sql = "select id,request_type,count from ( "
				+ "	SELECT request_master_id id,(select request_type from request_master where id=a.request_master_id) request_type,count(*) count "
				+ "	FROM request_workflow_history a where status_master_id=3 and assigned_to_id="
				+ workflowService.getSfID() + " group by request_master_id "
				+ "	) a";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);
		List<RequestCountDTO> items = new ArrayList<RequestCountDTO>();

		for (Map row : rows) {
			RequestCountDTO rc = new RequestCountDTO();
			rc.setId((Long) row.get("id"));
			rc.setType((String) row.get("request_type"));
			rc.setCount((Long) row.get("count"));
			items.add(rc);
		}

		return items;
	}

	/**
	 * List count of Approved request
	 */
	public List<RequestCountDTO> countApprovedRequests() throws Exception {
		log.debug("listApprovedRequests: {}");
		workflowService.getUserDetails();
		String sql = "select id,request_type,count from ( "
				+ "			SELECT  request_master_id id,(select request_type from request_master where id=a.request_master_id) request_type,count(*) count "
				+ "			FROM request_workflow_history a where status_master_id in (5,7,9)  and assigned_to_id="
				+ workflowService.getSfID() + " group by request_master_id "
				+ "			) a";

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);
		List<RequestCountDTO> items = new ArrayList<RequestCountDTO>();

		for (Map row : rows) {
			RequestCountDTO rc = new RequestCountDTO();
			rc.setId((Long) row.get("id"));
			rc.setType((String) row.get("request_type"));
			rc.setCount((Long) row.get("count"));
			items.add(rc);
		}

		return items;
	}
	
	
	/**
	 * Query that will list all Pending Requests
	 */
	@SuppressWarnings("unchecked")
	public List<RequestWorkflowHistory> listAllPendingRequests(String type)
			throws Exception {
		log.debug("listAllPendingRequests: {}");
		workflowService.getUserDetails();

		String sql = "SELECT  r.id, r.request_stage, r.assigned_date, from_unixtime(unix_timestamp(r.actioned_date)) as A, r.remarks, r.ip_address, "
				+ "r.assigned_role, r.domain_object, r.assigned_from_id, r.assigned_to_id, r.status_master_id, r.request_master_id, r.workflow_master_id,"
				+ " r.workflow_stage_master_id, r.applied_by_id FROM  request_workflow_history r, application_txn tr where r.domain_object=tr.id  "
				+ " and r.status_master_id=3 and assigned_to_id="
				+ workflowService.getSfID()
				+ " and r.request_master_id="
				+ type;

		log.debug("listAllPendingRequests: " + sql);

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);

		List<RequestWorkflowHistory> items = new ArrayList<RequestWorkflowHistory>();

		for (Map row : rows) {
			RequestWorkflowHistory rwh = new RequestWorkflowHistory();
			rwh.setId((Long) (row.get("id")));
			rwh.setRequestStage(Integer.parseInt(row.get("request_stage")
					.toString()));

			ZonedDateTime now = ZonedDateTime.now();
			rwh.setActionedDate(now);
			rwh.setIpAddress((String) row.get("ip_address"));

			// rwh.setAssigned_role_id(Integer.parseInt((String)row.get("assigned_role_id")));
			rwh.setDomainObject((Long) row.get("domain_object_id"));

			rwh.setAssignedFrom(userRepository.findById((Long) row
					.get("assigned_from_id")));
			rwh.setAssignedTo(userRepository.findById((Long) row
					.get("assigned_to_id")));
			rwh.setStatusMaster(statusMasterRepository.findOne((Long) row
					.get("status_master_id")));
			rwh.setAssignedTo(userRepository.findById((Long) row
					.get("assigned_to_id")));
			rwh.setWorkflowMaster(workflowMasterRepository.findOne((Long) row
					.get("workflow_master_id")));
			// rwh.setStage_status(workflow_stage_masterRepository.findOne((Long)row.get("stage_status_id")));
			rwh.setRequestMaster(requestMasterRepository.findOne((Long) row
					.get("request_master_id")));
			// rwh.setApplied_by(userRepository.findById((Long)row.get("applied_by_id")));
			items.add(rwh);
		}

		return items;
	}
	
	/**
	 * Query that will list all Approved Requests
	 */
	@SuppressWarnings("unchecked")
	public List<RequestWorkflowHistory> listAllApprovedRequests(String type)
			throws Exception {
		List<RequestWorkflowHistory> list = null;
		log.debug("listAllPendingRequests: {}");
		workflowService.getUserDetails();

		String sql = "SELECT  r.id, r.request_stage, r.assigned_date, from_unixtime(unix_timestamp(r.actioned_date)) as A, r.remarks, "
				+ " r.ip_address, r.assigned_role, r.domain_object, r.assigned_from_id, r.assigned_to_id, r.status_master_id, "
				+ " r.request_master_id, r.workflow_master_id, r.workflow_stage_master_id, r.applied_by_id "
				+ " FROM  request_workflow_history r, application_txn tr where tr.id=r.domain_object  "
				+ " and   r.status_master_id in (5,7,9) and assigned_to_id="
				+ workflowService.getSfID()
				+ " and  r.request_master_id="
				+ type;
		;

		log.debug("listAllApprovedRequests: {} : " + sql);

		List<java.util.Map<String, Object>> rows = jdbcTemplate
				.queryForList(sql);

		List<RequestWorkflowHistory> items = new ArrayList<RequestWorkflowHistory>();

		for (Map row : rows) {
			RequestWorkflowHistory rwh = new RequestWorkflowHistory();
			rwh.setId((Long) (row.get("id")));
			rwh.setRequestStage(Integer.parseInt(row.get("request_stage")
					.toString()));

			ZonedDateTime obj = ZonedDateTime.now();

			rwh.setActionedDate(obj);
			rwh.setIpAddress((String) row.get("ip_address"));

			rwh.setDomainObject((Long) row.get("domain_object"));

			rwh.setAssignedFrom(userRepository.findById((Long) row
					.get("assigned_from_id")));
			rwh.setAssignedTo(userRepository.findById((Long) row
					.get("assigned_to_id")));
			rwh.setStatusMaster(statusMasterRepository.findOne((Long) row
					.get("status_master_id")));
			rwh.setAssignedTo(userRepository.findById((Long) row
					.get("assigned_to_id")));
			rwh.setWorkflowMaster(workflowMasterRepository.findOne((Long) row
					.get("workflow_master_id")));
			// rwh.setStage_status(workflow_stage_masterRepository.findOne((Long)row.get("stage_status_id")));
			rwh.setRequestMaster(requestMasterRepository.findOne((Long) row
					.get("request_master_id")));
			//rwh.setAppliedBy(userRepository.findById((Long) row.get("applied_by_id")));
			items.add(rwh);
		}

		log.debug("listAllApprovedRequests: {}:" + items);

		return items;
	}

}

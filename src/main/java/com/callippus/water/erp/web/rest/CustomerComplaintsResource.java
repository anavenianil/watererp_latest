package com.callippus.water.erp.web.rest;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.Adjustments;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.CustomerComplaints;
import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.domain.TransactionTypeMaster;
import com.callippus.water.erp.domain.enumeration.TxnStatus;
import com.callippus.water.erp.repository.AdjustmentsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.CustomerComplaintsCustomRepository;
import com.callippus.water.erp.repository.CustomerComplaintsRepository;
import com.callippus.water.erp.repository.TransactionTypeMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.customercomplaints.service.CustomerComplaintsWorkflowService;
import com.callippus.water.erp.workflow.service.WorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing CustomerComplaints.
 */
@RestController
@RequestMapping("/api")
public class CustomerComplaintsResource {

	private final Logger log = LoggerFactory.getLogger(CustomerComplaintsResource.class);

	@Inject
	private CustomerComplaintsRepository customerComplaintsRepository;

	@Inject
	private CustomerComplaintsCustomRepository customerComplaintsCustomRepository;

	@Inject
	private WorkflowService workflowService;

	@Inject
	private CustomerComplaintsWorkflowService customerComplaintsWorkflowService;

	@Inject
	private CustDetailsRepository custDetailsRepository;

	@Inject
	private AdjustmentsRepository adjustmentsRepository;

	@Inject
	private TransactionTypeMasterRepository transactionTypeMasterRepository;

	/**
	 * POST /customerComplaintss -> Create a new customerComplaints.
	 */
	@RequestMapping(value = "/customerComplaintss", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<CustomerComplaints> createCustomerComplaints(HttpServletRequest request,
			@RequestBody CustomerComplaints customerComplaints) throws URISyntaxException, Exception {
		log.debug("REST request to save CustomerComplaints : {}", customerComplaints);
		if (customerComplaints.getId() != null) {
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("customerComplaints", "idexists",
					"A new customerComplaints cannot already have an ID")).body(null);
		}

		// customerComplaints.setPhoto("");
		customerComplaints.setRelevantDoc("");
		customerComplaintsRepository.save(customerComplaints);

		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("relevantDoc", "setRelevantDoc");
		UploadDownloadResource.setValues(customerComplaints, hm, request, customerComplaints.getId());
		customerComplaints.setStatus(0);
		CustomerComplaints result = customerComplaintsRepository.save(customerComplaints);

		try {
			workflowService.getUserDetails();
			workflowService.setAssignedDate(ZonedDateTime.now().toString());
			customerComplaintsWorkflowService.createTxn(customerComplaints);
		} catch (Exception e) {
			System.out.println(e);
		}

		return ResponseEntity.created(new URI("/api/customerComplaintss/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert("customerComplaints", result.getId().toString()))
				.body(result);
	}

	/**
	 * PUT /customerComplaintss -> Updates an existing customerComplaints.
	 */
	@RequestMapping(value = "/customerComplaintss", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional
	public ResponseEntity<CustomerComplaints> updateCustomerComplaints(HttpServletRequest request,
			@RequestBody CustomerComplaints customerComplaints) throws URISyntaxException, Exception {
		log.debug("REST request to update CustomerComplaints : {}", customerComplaints);
		
		if (customerComplaints.getId() == null) {
			return createCustomerComplaints(request, customerComplaints);
		}
		
		
		
		CustomerComplaints customerComplaints1 = customerComplaintsRepository.findOne(customerComplaints.getId());
		customerComplaints1.setStatus(customerComplaints1.getStatus() + 1);
		customerComplaints1.setAdjustmentAmt(customerComplaints.getAdjustmentAmt());
		customerComplaints1.setAdjustmentBillId(customerComplaints.getAdjustmentBillId());
		CustomerComplaints result = customerComplaintsRepository.save(customerComplaints1);
		approveApplication(customerComplaints.getId(), customerComplaints.getRemarks());
		
		if (CPSConstants.UPDATE.equals(workflowService.getMessage()) && customerComplaints.getComplaintTypeMaster().getId() ==1) {
			CustDetails custDetails = custDetailsRepository.findByCanForUpdate(customerComplaints.getCan());

			Adjustments adjustments = new Adjustments();
			TransactionTypeMaster ttm = null;
			BigDecimal amount = new BigDecimal(customerComplaints.getAdjustmentAmt().toString());
			adjustments.setBillFullDetails(null);
			adjustments.setCan(customerComplaints.getCan());

			adjustments.setRemarks(customerComplaints.getRemarks());
			adjustments.setTxnTime(ZonedDateTime.now());
			adjustments.setStatus(TxnStatus.INITIATED);
			adjustments.setCustDetails(custDetails);
			adjustments.setAmount(amount.abs());
			
			/*Float amt = customerComplaints.getAdjustmentAmt(); //commented by mohib after changing float to bigdecimal

			if (amt < 0) { //Debit
				ttm = transactionTypeMasterRepository.findOne(2L);
			} else if (amt > 0) { //Credit
				ttm = transactionTypeMasterRepository.findOne(1L);
			}*/
			BigDecimal amt = customerComplaints.getAdjustmentAmt();

			int res = amt.compareTo(new BigDecimal("0"));
			if (res == -1) { //Debit
				ttm = transactionTypeMasterRepository.findOne(2L);
			} else if (res == 1) { //Credit
				ttm = transactionTypeMasterRepository.findOne(1L);
			}

			adjustments.setTransactionTypeMaster(ttm);
			adjustments.setCustomerComplaints(customerComplaints);
			adjustmentsRepository.save(adjustments);
		}
		
		return ResponseEntity.ok()
				.headers(
						HeaderUtil.createEntityUpdateAlert("customerComplaints", customerComplaints.getId().toString()))
				.body(result);
	}

	/**
	 * GET /customerComplaintss -> get all the customerComplaintss.
	 */
	@RequestMapping(value = "/customerComplaintss", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<CustomerComplaints>> getAllCustomerComplaintss(Pageable pageable,
			@RequestParam(value = "can", required = false) String can)
			throws URISyntaxException {
		log.debug("REST request to get a page of CustomerComplaintss");
		Page<CustomerComplaints> page;
		//Page<CustomerComplaints> page = customerComplaintsRepository.findAll(pageable);//Comment this line
		if(can != null) {
			page = customerComplaintsRepository.findByCan(pageable, can);
		}
		else{
			page = customerComplaintsRepository.findAll(pageable);
		}
		
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customerComplaintss");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /customerComplaintss/:id -> get the "id" customerComplaints.
	 */
	@RequestMapping(value = "/customerComplaintss/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<CustomerComplaints> getCustomerComplaints(@PathVariable Long id) {
		log.debug("REST request to get CustomerComplaints : {}", id);
		CustomerComplaints customerComplaints = customerComplaintsRepository.findOne(id);
		return Optional.ofNullable(customerComplaints).map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	

	/**
	 * GET /customerComplaintss/getByCan/:can -> get the customerComplaints for "can" and "status".
	 */
	@RequestMapping(value = "/customerComplaints/getByCan/{can}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<CustomerComplaints>> getCustomerComplaints(@PathVariable String can, @PathVariable Integer status) {
		log.debug("REST request to get CustomerComplaints for CAN : {}", can);
		List<CustomerComplaints> customerComplaints = customerComplaintsRepository.findByCanAndStatus(can,status);
		return Optional.ofNullable(customerComplaints).map(result -> new ResponseEntity<List<CustomerComplaints>>(customerComplaints, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK
						));
	}

	/**
	 * GET /customerComplaintss/searchCustomerComplaint/:searchTerm
	 */
	@RequestMapping(value = "/customerComplaintss/searchCustomerComplaint/{searchTerm}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<String>> searchCustomerComplaintLike(@PathVariable String searchTerm) {
		log.debug("REST request to get CustomerComplaints : {}", searchTerm);
		List<String> complaintList = customerComplaintsCustomRepository.searchCustomerComplaint(searchTerm);
		return Optional.ofNullable(complaintList).map(result -> new ResponseEntity<>(complaintList, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * DELETE /customerComplaintss/:id -> delete the "id" customerComplaints.
	 */
	@RequestMapping(value = "/customerComplaintss/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> deleteCustomerComplaints(@PathVariable Long id) {
		log.debug("REST request to delete CustomerComplaints : {}", id);
		customerComplaintsRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("customerComplaints", id.toString()))
				.build();
	}

	/**
	 * this will approve the Customer Complaints Request
	 */
	@RequestMapping(value = "/customerComplaints/approveCustomerCompaints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> approveApplication(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "remarks", required = false) String remarks) throws Exception {

		workflowService.getUserDetails();
		workflowService.getRequestType();
		CustomerComplaints customerComplaints = customerComplaintsRepository.findOne(id);
		workflowService.setRemarks(remarks);
		workflowService.setApprovedDate(ZonedDateTime.now());
		// Integer status = customerComplaints.getStatus();
		// status +=1;
		// customerComplaints.setStatus(status);
		// workflowService.setRequestStatus(status);
		customerComplaintsWorkflowService.approvedCustomerComplaintsRequest(customerComplaints);
		/*
		 * if(workflowService.getRequestStatus() == 2){
		 * applicationTxnWorkflowService.updateApplicationTxn(id); }
		 */
		// customerComplaintsRepository.save(customerComplaints);
		return ResponseEntity.ok().build();
	}
}

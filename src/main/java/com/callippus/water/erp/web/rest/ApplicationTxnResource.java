package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

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

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.repository.ApplicationTxnCustomRepository;
import com.callippus.water.erp.repository.ApplicationTxnRepository;
import com.callippus.water.erp.repository.RequestWorkflowHistoryRepository;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.applicationtxn.service.ApplicationTxnWorkflowService;
import com.callippus.water.erp.workflow.service.WorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing ApplicationTxn.
 */
@RestController
@RequestMapping("/api")
public class ApplicationTxnResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationTxnResource.class);
        
    @Inject
    private ApplicationTxnRepository applicationTxnRepository;
    
    @Inject
    private WorkflowService workflowService;
    
    @Inject 
    private ApplicationTxnWorkflowService applicationTxnWorkflowService;
    
    @Inject
    private ApplicationTxnCustomRepository applicationTxnCustomRepository;
    
    @Inject
    private RequestWorkflowHistoryRepository requestWorkflowHistoryRepository;
    
    /**
     * POST  /applicationTxns -> Create a new applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> createApplicationTxn(@RequestBody ApplicationTxn applicationTxn) throws URISyntaxException {
        log.debug("REST request to save ApplicationTxn : {}", applicationTxn);
        if (applicationTxn.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("applicationTxn", "idexists", "A new applicationTxn cannot already have an ID")).body(null);
        }
      //file number format(Division - Property Number - House No - Connection No) : DO4 - 064 - 1 - 1
        //String fileNumber = "D"+applicationTxn.getWard() +"-"+applicationTxn.getGovtOfficialNo()+"-"+applicationTxn.getsHouseNo();
        //applicationTxn.setFileNumber(fileNumber);
        if(applicationTxn.getStatus()==null){
        	applicationTxn.setStatus(0);
        }
        
        ZonedDateTime now = ZonedDateTime.now();
        applicationTxn.setUpdatedDate(now);
        applicationTxn.setCreatedDate(now);
        ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        
      //this is for workflow
        try{
        	workflowService.getUserDetails();
        	applicationTxnWorkflowService.createTxn(applicationTxn);
        }
        catch(Exception e){
        	System.out.println(e);
        }
        
        
        return ResponseEntity.created(new URI("/api/applicationTxns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("applicationTxn", result.getId().toString()))
            .body(result);
    }

    
    
    /**
     * PUT  /applicationTxns -> Updates an existing applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> updateApplicationTxn(@RequestBody ApplicationTxn applicationTxn) throws URISyntaxException {
        log.debug("REST request to update ApplicationTxn : {}", applicationTxn);
        if (applicationTxn.getId() == null) {
            return createApplicationTxn(applicationTxn);
        }
        ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("applicationTxn", applicationTxn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /applicationTxns -> get all the applicationTxns.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ApplicationTxn>> getAllApplicationTxns(Pageable pageable,
    		@RequestParam(value = "status", required = false) Integer status)
        throws URISyntaxException {
        log.debug("REST request to get a page of ApplicationTxns");
        //Page<ApplicationTxn> page = applicationTxnRepository.findAll(pageable); 
        Page<ApplicationTxn> page;
        if(status == null){
        	page = applicationTxnRepository.findAll(pageable);
        }
        else
        {
        	page = applicationTxnRepository.findByStatus(pageable, status);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/applicationTxns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /applicationTxns/:id -> get the "id" applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> getApplicationTxn(@PathVariable Long id) {
        log.debug("REST request to get ApplicationTxn : {}", id);
        ApplicationTxn applicationTxn = applicationTxnRepository.findOne(id);
        return Optional.ofNullable(applicationTxn)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /applicationTxns/:id -> delete the "id" applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteApplicationTxn(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationTxn : {}", id);
        applicationTxnRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("applicationTxn", id.toString())).build();
    }
    
    /**
     * this will approve the Connection Request
     */
	@RequestMapping(value = "/applicationTxns/approveRequest", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<Void> approveApplication(@RequestParam(value = "id", required = false) Long id,
						@RequestParam(value = "remarks", required = false) String remarks)throws Exception{

		workflowService.getUserDetails();
	    
		ApplicationTxn applicationTxn = applicationTxnRepository.findOne(id);
	    workflowService.setRemarks(remarks);  
	    Integer status = applicationTxn.getStatus();
	    status +=1;
	    applicationTxn.setStatus(status);
        workflowService.setRequestStatus(status);
        applicationTxnWorkflowService.approvedApplicationTxnRequest(applicationTxn);
        /*if(workflowService.getRequestStatus() == 2){
        	applicationTxnWorkflowService.updateApplicationTxn(id);        	
        }*/
        applicationTxnRepository.save(applicationTxn);
        return ResponseEntity.ok().build();
	}
	    
    
    /**
     * Decline the request
     */
    /*@RequestMapping(value = "/applicationTxns/declineRequest",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<Void> declineRequests(
			@RequestParam(value = "id", required = false) Long id, HttpServletResponse response)
			throws Exception {
		log.debug("REST request to get Requisition : {}", id);
		
		applicationTxnWorkflowService.declineRequest(id);
		return ResponseEntity.ok().build();
	}*/
    
    
    /**
     * Display count of pending request to the dashboard
     */
    
    @RequestMapping(value = "/applicationTxns/getPendingRequests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestCountDTO>> getPendingRequests(HttpServletResponse response)throws Exception {
        log.debug("REST request to get Requisition : {}");
        List<RequestCountDTO> pendingRequests = applicationTxnCustomRepository.countPendingRequests();

        return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
    }
    
    
    /**
     * Display count of approved request to the dashboard
     */
    
    @RequestMapping(value = "/applicationTxns/getApprovedRequests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestCountDTO>> getApprovedRequests(HttpServletResponse response)throws Exception {
        log.debug("REST request to get Requisition : {}");
        List<RequestCountDTO> approvedRequests = applicationTxnCustomRepository.countApprovedRequests();

        return new ResponseEntity<>(approvedRequests, HttpStatus.OK);
    }
    
    
    /**
     * Display PENDING request to the dashboard when clicked on Pending Requests button
     */
    @RequestMapping(value = "/applicationTxns/getRequests/pending/{type}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<RequestWorkflowHistory>> getAllPendingRequests(HttpServletResponse response, @PathVariable String type)
			throws Exception {
		log.debug("REST request to get Requisition : {}");
		List<RequestWorkflowHistory> requestWorkflowHistorysStatus = applicationTxnCustomRepository.listAllPendingRequests(type);

		return new ResponseEntity<>(requestWorkflowHistorysStatus,
				HttpStatus.OK);
	}
    
    
    /**
     * Display APPROVED request to the dashboard when clicked on Approved Requests button
     */
    @RequestMapping(value = "/applicationTxns/getRequests/approved/{type}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<RequestWorkflowHistory>> getAllApprovedRequests(HttpServletResponse response, @PathVariable String type)
			throws Exception {
		log.debug("REST request to get Requisition : {}");
		List<RequestWorkflowHistory> requestWorkflowHistorysStatus = applicationTxnCustomRepository
				.listAllApprovedRequests(type);

		return new ResponseEntity<>(requestWorkflowHistorysStatus,
				HttpStatus.OK);
	}

}

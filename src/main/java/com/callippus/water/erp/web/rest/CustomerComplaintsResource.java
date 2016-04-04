package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.CustomerComplaints;
import com.callippus.water.erp.repository.CustomerComplaintsRepository;
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
    private WorkflowService workflowService;
    
    @Inject
    private CustomerComplaintsWorkflowService customerComplaintsWorkflowService;
    
    /**
     * POST  /customerComplaintss -> Create a new customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustomerComplaints> createCustomerComplaints(HttpServletRequest request,
    		@RequestBody CustomerComplaints customerComplaints) throws URISyntaxException,Exception {
        log.debug("REST request to save CustomerComplaints : {}", customerComplaints);
        if (customerComplaints.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("customerComplaints", "idexists", "A new customerComplaints cannot already have an ID")).body(null);
        }
        
        //customerComplaints.setPhoto("");
        customerComplaints.setRelevantDoc("");
        customerComplaintsRepository.save(customerComplaints);
        
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("relevantDoc", "setRelevantDoc");
        UploadDownloadResource.setValues(customerComplaints, hm, request, customerComplaints.getId());
        
        CustomerComplaints result = customerComplaintsRepository.save(customerComplaints);
        
        try{
        	workflowService.getUserDetails();
        	customerComplaintsWorkflowService.createTxn(customerComplaints);
        }
        catch(Exception e){
        	System.out.println(e);
        }
        
        return ResponseEntity.created(new URI("/api/customerComplaintss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("customerComplaints", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customerComplaintss -> Updates an existing customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustomerComplaints> updateCustomerComplaints(HttpServletRequest request,
    		@RequestBody CustomerComplaints customerComplaints) throws URISyntaxException,Exception {
        log.debug("REST request to update CustomerComplaints : {}", customerComplaints);
        if (customerComplaints.getId() == null) {
            return createCustomerComplaints(request,customerComplaints);
        }
        CustomerComplaints result = customerComplaintsRepository.save(customerComplaints);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("customerComplaints", customerComplaints.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customerComplaintss -> get all the customerComplaintss.
     */
    @RequestMapping(value = "/customerComplaintss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CustomerComplaints>> getAllCustomerComplaintss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CustomerComplaintss");
        Page<CustomerComplaints> page = customerComplaintsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customerComplaintss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customerComplaintss/:id -> get the "id" customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustomerComplaints> getCustomerComplaints(@PathVariable Long id) {
        log.debug("REST request to get CustomerComplaints : {}", id);
        CustomerComplaints customerComplaints = customerComplaintsRepository.findOne(id);
        return Optional.ofNullable(customerComplaints)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /customerComplaintss/:id -> delete the "id" customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCustomerComplaints(@PathVariable Long id) {
        log.debug("REST request to delete CustomerComplaints : {}", id);
        customerComplaintsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("customerComplaints", id.toString())).build();
    }
}

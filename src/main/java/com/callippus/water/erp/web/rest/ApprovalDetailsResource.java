package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

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

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.ApprovalDetails;
import com.callippus.water.erp.repository.ApplicationTxnRepository;
import com.callippus.water.erp.repository.ApprovalDetailsRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing ApprovalDetails.
 */
@RestController
@RequestMapping("/api")
public class ApprovalDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ApprovalDetailsResource.class);
        
    @Inject
    private ApprovalDetailsRepository approvalDetailsRepository;
    
    @Inject
    private ApplicationTxnRepository applicationTxnRepository;
    
    /**
     * POST  /approvalDetailss -> Create a new approvalDetails.
     */
    @RequestMapping(value = "/approvalDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApprovalDetails> createApprovalDetails(@RequestBody ApprovalDetails approvalDetails) throws URISyntaxException {
        log.debug("REST request to save ApprovalDetails : {}", approvalDetails);
        if (approvalDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("approvalDetails", "idexists", "A new approvalDetails cannot already have an ID")).body(null);
        }
        ApplicationTxn applicationTxn = applicationTxnRepository.findOne(approvalDetails.getApplicationTxn().getId());
        String status = applicationTxn.getStatus();
        if("Pending".equals(status)){
        	applicationTxn.setStatus("In Feasibility");
        }
        else if("In Feasibility".equals(status)){
        	applicationTxn.setStatus("In Proceedings");
        }
        else if("In Proceedings".equals(status)){
        	applicationTxn.setStatus("Connection Approved");
        }
        
        applicationTxnRepository.save(applicationTxn);
        
        ApprovalDetails result = approvalDetailsRepository.save(approvalDetails);
        return ResponseEntity.created(new URI("/api/approvalDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("approvalDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /approvalDetailss -> Updates an existing approvalDetails.
     */
    @RequestMapping(value = "/approvalDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApprovalDetails> updateApprovalDetails(@RequestBody ApprovalDetails approvalDetails) throws URISyntaxException {
        log.debug("REST request to update ApprovalDetails : {}", approvalDetails);
        if (approvalDetails.getId() == null) {
            return createApprovalDetails(approvalDetails);
        }
        ApprovalDetails result = approvalDetailsRepository.save(approvalDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("approvalDetails", approvalDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /approvalDetailss -> get all the approvalDetailss.
     */
    @RequestMapping(value = "/approvalDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ApprovalDetails>> getAllApprovalDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ApprovalDetailss");
        Page<ApprovalDetails> page = approvalDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/approvalDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /approvalDetailss/:id -> get the "id" approvalDetails.
     */
    @RequestMapping(value = "/approvalDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApprovalDetails> getApprovalDetails(@PathVariable Long id) {
        log.debug("REST request to get ApprovalDetails : {}", id);
        ApprovalDetails approvalDetails = approvalDetailsRepository.findOne(id);
        return Optional.ofNullable(approvalDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /approvalDetailss/:id -> delete the "id" approvalDetails.
     */
    @RequestMapping(value = "/approvalDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteApprovalDetails(@PathVariable Long id) {
        log.debug("REST request to delete ApprovalDetails : {}", id);
        approvalDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("approvalDetails", id.toString())).build();
    }
}

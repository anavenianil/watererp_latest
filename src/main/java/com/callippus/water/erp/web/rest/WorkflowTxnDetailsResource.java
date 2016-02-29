package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.WorkflowTxnDetails;
import com.callippus.water.erp.repository.WorkflowTxnDetailsRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WorkflowTxnDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkflowTxnDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowTxnDetailsResource.class);
        
    @Inject
    private WorkflowTxnDetailsRepository workflowTxnDetailsRepository;
    
    /**
     * POST  /workflowTxnDetailss -> Create a new workflowTxnDetails.
     */
    @RequestMapping(value = "/workflowTxnDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTxnDetails> createWorkflowTxnDetails(@RequestBody WorkflowTxnDetails workflowTxnDetails) throws URISyntaxException {
        log.debug("REST request to save WorkflowTxnDetails : {}", workflowTxnDetails);
        if (workflowTxnDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflowTxnDetails", "idexists", "A new workflowTxnDetails cannot already have an ID")).body(null);
        }
        WorkflowTxnDetails result = workflowTxnDetailsRepository.save(workflowTxnDetails);
        return ResponseEntity.created(new URI("/api/workflowTxnDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflowTxnDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflowTxnDetailss -> Updates an existing workflowTxnDetails.
     */
    @RequestMapping(value = "/workflowTxnDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTxnDetails> updateWorkflowTxnDetails(@RequestBody WorkflowTxnDetails workflowTxnDetails) throws URISyntaxException {
        log.debug("REST request to update WorkflowTxnDetails : {}", workflowTxnDetails);
        if (workflowTxnDetails.getId() == null) {
            return createWorkflowTxnDetails(workflowTxnDetails);
        }
        WorkflowTxnDetails result = workflowTxnDetailsRepository.save(workflowTxnDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflowTxnDetails", workflowTxnDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflowTxnDetailss -> get all the workflowTxnDetailss.
     */
    @RequestMapping(value = "/workflowTxnDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WorkflowTxnDetails>> getAllWorkflowTxnDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of WorkflowTxnDetailss");
        Page<WorkflowTxnDetails> page = workflowTxnDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workflowTxnDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workflowTxnDetailss/:id -> get the "id" workflowTxnDetails.
     */
    @RequestMapping(value = "/workflowTxnDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTxnDetails> getWorkflowTxnDetails(@PathVariable Long id) {
        log.debug("REST request to get WorkflowTxnDetails : {}", id);
        WorkflowTxnDetails workflowTxnDetails = workflowTxnDetailsRepository.findOne(id);
        return Optional.ofNullable(workflowTxnDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflowTxnDetailss/:id -> delete the "id" workflowTxnDetails.
     */
    @RequestMapping(value = "/workflowTxnDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflowTxnDetails(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowTxnDetails : {}", id);
        workflowTxnDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflowTxnDetails", id.toString())).build();
    }
}

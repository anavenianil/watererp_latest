package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
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

import com.callippus.water.erp.domain.SewerageRequest;
import com.callippus.water.erp.repository.SewerageRequestRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.service.WorkflowService;
import com.callippus.water.erp.workflow.seweragerequest.service.SewerageRequestWorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing SewerageRequest.
 */
@RestController
@RequestMapping("/api")
public class SewerageRequestResource {

    private final Logger log = LoggerFactory.getLogger(SewerageRequestResource.class);
        
    @Inject
    private SewerageRequestRepository sewerageRequestRepository;
    
    @Inject
    private WorkflowService workflowService;
    
    @Inject
    private SewerageRequestWorkflowService sewerageRequestWorkflowService;
    
    /**
     * POST  /sewerageRequests -> Create a new sewerageRequest.
     */
    @RequestMapping(value = "/sewerageRequests",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerageRequest> createSewerageRequest(@RequestBody SewerageRequest sewerageRequest) throws URISyntaxException {
        log.debug("REST request to save SewerageRequest : {}", sewerageRequest);
        if (sewerageRequest.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sewerageRequest", "idexists", "A new sewerageRequest cannot already have an ID")).body(null);
        }
        SewerageRequest result = sewerageRequestRepository.save(sewerageRequest);
        try{
        	workflowService.getUserDetails();
        	workflowService.setAssignedDate(ZonedDateTime.now().toString());
        	sewerageRequestWorkflowService.createTxn(sewerageRequest);
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        return ResponseEntity.created(new URI("/api/sewerageRequests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sewerageRequest", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sewerageRequests -> Updates an existing sewerageRequest.
     */
    @RequestMapping(value = "/sewerageRequests",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerageRequest> updateSewerageRequest(@RequestBody SewerageRequest sewerageRequest) throws URISyntaxException {
        log.debug("REST request to update SewerageRequest : {}", sewerageRequest);
        if (sewerageRequest.getId() == null) {
            return createSewerageRequest(sewerageRequest);
        }
        SewerageRequest result = sewerageRequestRepository.save(sewerageRequest);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sewerageRequest", sewerageRequest.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sewerageRequests -> get all the sewerageRequests.
     */
    @RequestMapping(value = "/sewerageRequests",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SewerageRequest>> getAllSewerageRequests(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SewerageRequests");
        Page<SewerageRequest> page = sewerageRequestRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sewerageRequests");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sewerageRequests/:id -> get the "id" sewerageRequest.
     */
    @RequestMapping(value = "/sewerageRequests/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerageRequest> getSewerageRequest(@PathVariable Long id) {
        log.debug("REST request to get SewerageRequest : {}", id);
        SewerageRequest sewerageRequest = sewerageRequestRepository.findOne(id);
        return Optional.ofNullable(sewerageRequest)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sewerageRequests/:id -> delete the "id" sewerageRequest.
     */
    @RequestMapping(value = "/sewerageRequests/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSewerageRequest(@PathVariable Long id) {
        log.debug("REST request to delete SewerageRequest : {}", id);
        sewerageRequestRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sewerageRequest", id.toString())).build();
    }
}

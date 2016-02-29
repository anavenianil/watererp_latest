package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.WorkflowRelationships;
import com.callippus.water.erp.repository.WorkflowRelationshipsRepository;
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
 * REST controller for managing WorkflowRelationships.
 */
@RestController
@RequestMapping("/api")
public class WorkflowRelationshipsResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowRelationshipsResource.class);
        
    @Inject
    private WorkflowRelationshipsRepository workflowRelationshipsRepository;
    
    /**
     * POST  /workflowRelationshipss -> Create a new workflowRelationships.
     */
    @RequestMapping(value = "/workflowRelationshipss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowRelationships> createWorkflowRelationships(@RequestBody WorkflowRelationships workflowRelationships) throws URISyntaxException {
        log.debug("REST request to save WorkflowRelationships : {}", workflowRelationships);
        if (workflowRelationships.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflowRelationships", "idexists", "A new workflowRelationships cannot already have an ID")).body(null);
        }
        WorkflowRelationships result = workflowRelationshipsRepository.save(workflowRelationships);
        return ResponseEntity.created(new URI("/api/workflowRelationshipss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflowRelationships", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflowRelationshipss -> Updates an existing workflowRelationships.
     */
    @RequestMapping(value = "/workflowRelationshipss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowRelationships> updateWorkflowRelationships(@RequestBody WorkflowRelationships workflowRelationships) throws URISyntaxException {
        log.debug("REST request to update WorkflowRelationships : {}", workflowRelationships);
        if (workflowRelationships.getId() == null) {
            return createWorkflowRelationships(workflowRelationships);
        }
        WorkflowRelationships result = workflowRelationshipsRepository.save(workflowRelationships);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflowRelationships", workflowRelationships.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflowRelationshipss -> get all the workflowRelationshipss.
     */
    @RequestMapping(value = "/workflowRelationshipss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WorkflowRelationships>> getAllWorkflowRelationshipss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of WorkflowRelationshipss");
        Page<WorkflowRelationships> page = workflowRelationshipsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workflowRelationshipss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workflowRelationshipss/:id -> get the "id" workflowRelationships.
     */
    @RequestMapping(value = "/workflowRelationshipss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowRelationships> getWorkflowRelationships(@PathVariable Long id) {
        log.debug("REST request to get WorkflowRelationships : {}", id);
        WorkflowRelationships workflowRelationships = workflowRelationshipsRepository.findOne(id);
        return Optional.ofNullable(workflowRelationships)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflowRelationshipss/:id -> delete the "id" workflowRelationships.
     */
    @RequestMapping(value = "/workflowRelationshipss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflowRelationships(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowRelationships : {}", id);
        workflowRelationshipsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflowRelationships", id.toString())).build();
    }
}

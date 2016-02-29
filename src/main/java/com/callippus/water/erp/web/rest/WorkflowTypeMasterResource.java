package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.WorkflowTypeMaster;
import com.callippus.water.erp.repository.WorkflowTypeMasterRepository;
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
 * REST controller for managing WorkflowTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkflowTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowTypeMasterResource.class);
        
    @Inject
    private WorkflowTypeMasterRepository workflowTypeMasterRepository;
    
    /**
     * POST  /workflowTypeMasters -> Create a new workflowTypeMaster.
     */
    @RequestMapping(value = "/workflowTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTypeMaster> createWorkflowTypeMaster(@RequestBody WorkflowTypeMaster workflowTypeMaster) throws URISyntaxException {
        log.debug("REST request to save WorkflowTypeMaster : {}", workflowTypeMaster);
        if (workflowTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflowTypeMaster", "idexists", "A new workflowTypeMaster cannot already have an ID")).body(null);
        }
        WorkflowTypeMaster result = workflowTypeMasterRepository.save(workflowTypeMaster);
        return ResponseEntity.created(new URI("/api/workflowTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflowTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflowTypeMasters -> Updates an existing workflowTypeMaster.
     */
    @RequestMapping(value = "/workflowTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTypeMaster> updateWorkflowTypeMaster(@RequestBody WorkflowTypeMaster workflowTypeMaster) throws URISyntaxException {
        log.debug("REST request to update WorkflowTypeMaster : {}", workflowTypeMaster);
        if (workflowTypeMaster.getId() == null) {
            return createWorkflowTypeMaster(workflowTypeMaster);
        }
        WorkflowTypeMaster result = workflowTypeMasterRepository.save(workflowTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflowTypeMaster", workflowTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflowTypeMasters -> get all the workflowTypeMasters.
     */
    @RequestMapping(value = "/workflowTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WorkflowTypeMaster>> getAllWorkflowTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of WorkflowTypeMasters");
        Page<WorkflowTypeMaster> page = workflowTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workflowTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workflowTypeMasters/:id -> get the "id" workflowTypeMaster.
     */
    @RequestMapping(value = "/workflowTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTypeMaster> getWorkflowTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkflowTypeMaster : {}", id);
        WorkflowTypeMaster workflowTypeMaster = workflowTypeMasterRepository.findOne(id);
        return Optional.ofNullable(workflowTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflowTypeMasters/:id -> delete the "id" workflowTypeMaster.
     */
    @RequestMapping(value = "/workflowTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflowTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowTypeMaster : {}", id);
        workflowTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflowTypeMaster", id.toString())).build();
    }
}

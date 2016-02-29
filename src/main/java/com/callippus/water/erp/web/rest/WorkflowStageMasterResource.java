package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.WorkflowStageMaster;
import com.callippus.water.erp.repository.WorkflowStageMasterRepository;
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
 * REST controller for managing WorkflowStageMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkflowStageMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowStageMasterResource.class);
        
    @Inject
    private WorkflowStageMasterRepository workflowStageMasterRepository;
    
    /**
     * POST  /workflowStageMasters -> Create a new workflowStageMaster.
     */
    @RequestMapping(value = "/workflowStageMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowStageMaster> createWorkflowStageMaster(@RequestBody WorkflowStageMaster workflowStageMaster) throws URISyntaxException {
        log.debug("REST request to save WorkflowStageMaster : {}", workflowStageMaster);
        if (workflowStageMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflowStageMaster", "idexists", "A new workflowStageMaster cannot already have an ID")).body(null);
        }
        WorkflowStageMaster result = workflowStageMasterRepository.save(workflowStageMaster);
        return ResponseEntity.created(new URI("/api/workflowStageMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflowStageMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflowStageMasters -> Updates an existing workflowStageMaster.
     */
    @RequestMapping(value = "/workflowStageMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowStageMaster> updateWorkflowStageMaster(@RequestBody WorkflowStageMaster workflowStageMaster) throws URISyntaxException {
        log.debug("REST request to update WorkflowStageMaster : {}", workflowStageMaster);
        if (workflowStageMaster.getId() == null) {
            return createWorkflowStageMaster(workflowStageMaster);
        }
        WorkflowStageMaster result = workflowStageMasterRepository.save(workflowStageMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflowStageMaster", workflowStageMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflowStageMasters -> get all the workflowStageMasters.
     */
    @RequestMapping(value = "/workflowStageMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WorkflowStageMaster>> getAllWorkflowStageMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of WorkflowStageMasters");
        Page<WorkflowStageMaster> page = workflowStageMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workflowStageMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workflowStageMasters/:id -> get the "id" workflowStageMaster.
     */
    @RequestMapping(value = "/workflowStageMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowStageMaster> getWorkflowStageMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkflowStageMaster : {}", id);
        WorkflowStageMaster workflowStageMaster = workflowStageMasterRepository.findOne(id);
        return Optional.ofNullable(workflowStageMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflowStageMasters/:id -> delete the "id" workflowStageMaster.
     */
    @RequestMapping(value = "/workflowStageMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflowStageMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowStageMaster : {}", id);
        workflowStageMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflowStageMaster", id.toString())).build();
    }
}

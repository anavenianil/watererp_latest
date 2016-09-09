package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

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

import com.callippus.water.erp.domain.StatusMaster;
import com.callippus.water.erp.domain.WorkflowMaster;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.repository.WorkflowMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing WorkflowMaster.
 */
@RestController
@RequestMapping("/api")
public class WorkflowMasterResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowMasterResource.class);
        
    @Inject
    private WorkflowMasterRepository workflowMasterRepository;
    
    @Inject
    private StatusMasterRepository statusMasterRepository;
    
    /**
     * POST  /workflowMasters -> Create a new workflowMaster.
     */
    @RequestMapping(value = "/workflowMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowMaster> createWorkflowMaster(@Valid @RequestBody WorkflowMaster workflowMaster) throws URISyntaxException {
        log.debug("REST request to save WorkflowMaster : {}", workflowMaster);
        if (workflowMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflowMaster", "idexists", "A new workflowMaster cannot already have an ID")).body(null);
        }
        workflowMaster.setCreationDate(ZonedDateTime.now());
        workflowMaster.setLastModifiedDate(ZonedDateTime.now());
        workflowMaster.setToWorkflow(0);
        workflowMaster.setStatusMaster(statusMasterRepository.findOne(2l));
        WorkflowMaster result = workflowMasterRepository.save(workflowMaster);
        return ResponseEntity.created(new URI("/api/workflowMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflowMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflowMasters -> Updates an existing workflowMaster.
     */
    @RequestMapping(value = "/workflowMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowMaster> updateWorkflowMaster(@Valid @RequestBody WorkflowMaster workflowMaster) throws URISyntaxException {
        log.debug("REST request to update WorkflowMaster : {}", workflowMaster);
        if (workflowMaster.getId() == null) {
            return createWorkflowMaster(workflowMaster);
        }
        WorkflowMaster result = workflowMasterRepository.save(workflowMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflowMaster", workflowMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflowMasters -> get all the workflowMasters.
     */
    @RequestMapping(value = "/workflowMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WorkflowMaster>> getAllWorkflowMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of WorkflowMasters");
        StatusMaster statusMaster = statusMasterRepository.findOne(2l); 
        Page<WorkflowMaster> page = workflowMasterRepository.findByStatusMaster(pageable, statusMaster); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workflowMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workflowMasters/:id -> get the "id" workflowMaster.
     */
    @RequestMapping(value = "/workflowMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowMaster> getWorkflowMaster(@PathVariable Long id) {
        log.debug("REST request to get WorkflowMaster : {}", id);
        WorkflowMaster workflowMaster = workflowMasterRepository.findOne(id);
        return Optional.ofNullable(workflowMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflowMasters/:id -> delete the "id" workflowMaster.
     */
    @RequestMapping(value = "/workflowMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflowMaster(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowMaster : {}", id);
        workflowMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflowMaster", id.toString())).build();
    }
}

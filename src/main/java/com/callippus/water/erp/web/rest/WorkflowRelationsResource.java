package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.WorkflowRelations;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.repository.WorkflowRelationsRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing WorkflowRelations.
 */
@RestController
@RequestMapping("/api")
public class WorkflowRelationsResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowRelationsResource.class);
        
    @Inject
    private WorkflowRelationsRepository workflowRelationsRepository;
    
    @Inject
    private StatusMasterRepository statusMasterRepository;
    
    /**
     * POST  /workflowRelationss -> Create a new workflowRelations.
     */
    @RequestMapping(value = "/workflowRelationss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowRelations> createWorkflowRelations(@RequestBody WorkflowRelations workflowRelations) throws URISyntaxException {
        log.debug("REST request to save WorkflowRelations : {}", workflowRelations);
        if (workflowRelations.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflowRelations", "idexists", "A new workflowRelations cannot already have an ID")).body(null);
        }
        WorkflowRelations result = workflowRelationsRepository.save(workflowRelations);
        return ResponseEntity.created(new URI("/api/workflowRelationss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflowRelations", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflowRelationss -> Updates an existing workflowRelations.
     */
    @RequestMapping(value = "/workflowRelationss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowRelations> updateWorkflowRelations(@RequestBody WorkflowRelations workflowRelations) throws URISyntaxException {
        log.debug("REST request to update WorkflowRelations : {}", workflowRelations);
        if (workflowRelations.getId() == null) {
            return createWorkflowRelations(workflowRelations);
        }
        WorkflowRelations result = workflowRelationsRepository.save(workflowRelations);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflowRelations", workflowRelations.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflowRelationss -> get all the workflowRelationss.
     */
    @RequestMapping(value = "/workflowRelationss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<WorkflowRelations> getAllWorkflowRelationss() {
        log.debug("REST request to get all WorkflowRelationss");
        return workflowRelationsRepository.findByStatusMaster(statusMasterRepository.findOne(2l));
            }

    /**
     * GET  /workflowRelationss/:id -> get the "id" workflowRelations.
     */
    @RequestMapping(value = "/workflowRelationss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowRelations> getWorkflowRelations(@PathVariable Long id) {
        log.debug("REST request to get WorkflowRelations : {}", id);
        WorkflowRelations workflowRelations = workflowRelationsRepository.findOne(id);
        return Optional.ofNullable(workflowRelations)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflowRelationss/:id -> delete the "id" workflowRelations.
     */
    @RequestMapping(value = "/workflowRelationss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflowRelations(@PathVariable Long id) {
        log.debug("REST request to delete WorkflowRelations : {}", id);
        workflowRelationsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflowRelations", id.toString())).build();
    }
}

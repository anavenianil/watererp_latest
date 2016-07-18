package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.Iterator;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.ItemRequired;
import com.callippus.water.erp.domain.Workflow;
import com.callippus.water.erp.domain.WorkflowDTO;
import com.callippus.water.erp.domain.WorkflowMaster;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.repository.WorkflowMasterRepository;
import com.callippus.water.erp.repository.WorkflowRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Workflow.
 */
@RestController
@RequestMapping("/api")
public class WorkflowResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowResource.class);
        
    @Inject
    private WorkflowRepository workflowRepository;
    
    @Inject
    private WorkflowMasterRepository workflowMasterRepository;
    
    @Inject
    private StatusMasterRepository statusMasterRepository;
    
    /**
     * POST  /workflows -> Create a new workflow.
     */
    @RequestMapping(value = "/workflows",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workflow> createWorkflow(@RequestBody Workflow workflow) throws URISyntaxException {
        log.debug("REST request to save Workflow : {}", workflow);
        if (workflow.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("workflow", "idexists", "A new workflow cannot already have an ID")).body(null);
        }
        Workflow result = workflowRepository.save(workflow);
        return ResponseEntity.created(new URI("/api/workflows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("workflow", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /workflows -> Updates an existing workflow.
     */
    @RequestMapping(value = "/workflows",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workflow> updateWorkflow(@RequestBody Workflow workflow) throws URISyntaxException {
        log.debug("REST request to update Workflow : {}", workflow);
        if (workflow.getId() == null) {
            return createWorkflow(workflow);
        }
        Workflow result = workflowRepository.save(workflow);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("workflow", workflow.getId().toString()))
            .body(result);
    }

    /**
     * GET  /workflows -> get all the workflows.
     */
    @RequestMapping(value = "/workflows",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Workflow>> getAllWorkflows(Pageable pageable,
    		@RequestParam(value = "workflowMasterId", required = false) Long workflowMasterId)
        throws URISyntaxException {
        log.debug("REST request to get a page of Workflows");
        //Page<Workflow> page = workflowRepository.findAll(pageable);
        Page<Workflow> page;
        //WorkflowMaster workflowMaster = workflowMasterRepository.findOne(workflowMasterId);
        if(workflowMasterId != null){
        	page = workflowRepository.findByWorkflowMaster(pageable, workflowMasterId);
        }
        else
        {
        	page = workflowRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/workflows");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /workflows/:id -> get the "id" workflow.
     */
    @RequestMapping(value = "/workflows/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Workflow> getWorkflow(@PathVariable Long id) {
        log.debug("REST request to get Workflow : {}", id);
        Workflow workflow = workflowRepository.findOne(id);
        return Optional.ofNullable(workflow)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /workflows/:id -> delete the "id" workflow.
     */
    @RequestMapping(value = "/workflows/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWorkflow(@PathVariable Long id) {
        log.debug("REST request to delete Workflow : {}", id);
        workflowRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("workflow", id.toString())).build();
    }
    
    
    /**
     *  Create Workflow
     */
    @RequestMapping(value = "/workflows/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(rollbackFor=Exception.class)
	public ResponseEntity<WorkflowDTO> createWorkflow(
			@RequestBody WorkflowDTO workflowDTO)
			throws Exception {
    	log.debug("REST request to createWorkflow() for Workflow  : {}", workflowDTO);

    	if(workflowDTO.getWorkflowName() != null){
    		workflowDTO.getWorkflowMaster().setCreationDate(ZonedDateTime.now());
        	workflowDTO.getWorkflowMaster().setLastModifiedDate(ZonedDateTime.now());
        	workflowDTO.getWorkflowMaster().setToWorkflow(0);
        	workflowDTO.getWorkflowMaster().setStatusMaster(statusMasterRepository.findOne(2l));
        	workflowDTO.getWorkflowMaster().setWorkflowName(workflowDTO.getWorkflowName());
        	
            WorkflowMaster workflowMaster = workflowMasterRepository.save(workflowDTO.getWorkflowMaster());
            Iterator<Workflow> iterator = workflowDTO.getWorkflows().iterator();
            while(iterator.hasNext()){
            	iterator.next().setWorkflowMaster(workflowMaster);
            }
            List<Workflow> items = workflowDTO.getWorkflows();
            workflowRepository.save(items);
    	}else{
    		Iterator<Workflow> iterator = workflowDTO.getWorkflows().iterator();
            while(iterator.hasNext()){
            	iterator.next().setWorkflowMaster(workflowDTO.getWorkflowMaster());
            }
            List<Workflow> items = workflowDTO.getWorkflows();
            
            workflowRepository.save(items);
    	}

    	return Optional.ofNullable(workflowDTO)
				.map(result -> new ResponseEntity<>(workflowDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

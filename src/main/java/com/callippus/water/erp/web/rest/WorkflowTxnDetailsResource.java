package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.ItemRequired;
import com.callippus.water.erp.domain.WorkflowDTO;
import com.callippus.water.erp.domain.WorkflowTxnDetails;
import com.callippus.water.erp.repository.WorkflowTxnDetailsRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.applicationtxn.service.CustDetailsChangeWorkflowService;
import com.callippus.water.erp.workflow.service.WorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing WorkflowTxnDetails.
 */
@RestController
@RequestMapping("/api")
public class WorkflowTxnDetailsResource {

    private final Logger log = LoggerFactory.getLogger(WorkflowTxnDetailsResource.class);
        
    @Inject
    private WorkflowTxnDetailsRepository workflowTxnDetailsRepository;
    
    @Inject
    private WorkflowService workflowService;
    
    @Inject
    private CustDetailsChangeWorkflowService custDetailsChangeWorkflowService;
    
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
    public ResponseEntity<List<WorkflowTxnDetails>> getAllWorkflowTxnDetailss(Pageable pageable,
    		@RequestParam(value = "requestId", required = false) Integer requestId)
        throws URISyntaxException {
        log.debug("REST request to get a page of WorkflowTxnDetailss");
        //Page<WorkflowTxnDetails> page = workflowTxnDetailsRepository.findAll(pageable);
        Page<WorkflowTxnDetails> page;
        if(requestId == null){
        	page = workflowTxnDetailsRepository.findAll(pageable);
        }
        else{
        	page = workflowTxnDetailsRepository.findByRequestId(pageable, requestId);
        }
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
    
    
    /**
     * POST  /workflowTxnDetailss -> Create a new workflowTxnDetailsArr.
     */
    @RequestMapping(value = "/workflowTxnDetailsArr",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WorkflowTxnDetails> createWorkflowTxnDetailsArr(@RequestBody WorkflowDTO workflowDTO) throws URISyntaxException {
        log.debug("REST request to save WorkflowTxnDetails : {}", workflowDTO);
        WorkflowTxnDetails wtd = workflowDTO.getWorkflowTxnDetailss().get(0);
        List<WorkflowTxnDetails> workflowTxnDetailss = workflowTxnDetailsRepository.save(workflowDTO.getWorkflowTxnDetailss());
        
       /* try{
        	workflowService.getUserDetails();
        	custDetailsChangeWorkflowService.createTxn(wtd);
        	
            Iterator<WorkflowTxnDetails> iterator = workflowTxnDetailss.iterator();
            while(iterator.hasNext()){
              iterator.next().setRequestId(Integer.valueOf(workflowService.getRequestWorkflowHistoryId().toString()));
            }
        }
        catch(Exception e){
        	System.out.println(e);
        }*/
        workflowTxnDetailsRepository.save(workflowDTO.getWorkflowTxnDetailss());
        
        return ResponseEntity.created(new URI("/api/workflowTxnDetailss/"))
            .headers(HeaderUtil.createEntityCreationAlert("workflowTxnDetails",""))
            .body(null);
    }
    
    
    
    
   /* @RequestMapping(value = "/workflowTxnDetailsApprove",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<WorkflowTxnDetails> approveCategoryChange(@RequestBody WorkflowDTO workflowDTO) throws URISyntaxException {
            log.debug("REST request to save WorkflowTxnDetails : {}", workflowDTO);
            WorkflowTxnDetails wtd = workflowDTO.getWorkflowTxnDetailss().get(0);
            List<WorkflowTxnDetails> workflowTxnDetailss = workflowTxnDetailsRepository.save(workflowDTO.getWorkflowTxnDetailss());
            
            try{
            	workflowService.getUserDetails();
            	custDetailsChangeWorkflowService.approvedWorkflowTxnDetailsRequest(wtd);
            }
            catch(Exception e){
            	System.out.println(e);
            }
            workflowTxnDetailsRepository.save(workflowDTO.getWorkflowTxnDetailss());
            
            return ResponseEntity.created(new URI("/api/workflowTxnDetailss/"))
                .headers(HeaderUtil.createEntityCreationAlert("workflowTxnDetails",""))
                .body(null);
        }*/
}

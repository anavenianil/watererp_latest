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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.JobCardDTO;
import com.callippus.water.erp.domain.MeterChange;
import com.callippus.water.erp.domain.WaterLeakageComplaint;
import com.callippus.water.erp.repository.WaterLeakageComplaintRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.customercomplaints.service.WaterLeakageComplaintWorkflowService;
import com.callippus.water.erp.workflow.service.WorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing WaterLeakageComplaint.
 */
@RestController
@RequestMapping("/api")
public class WaterLeakageComplaintResource {

    private final Logger log = LoggerFactory.getLogger(WaterLeakageComplaintResource.class);
        
    @Inject
    private WaterLeakageComplaintRepository waterLeakageComplaintRepository;
    
    @Inject
    private WorkflowService workflowService;
    
    @Inject WaterLeakageComplaintWorkflowService waterLeakageComplaintWorkflowService;
    /**
     * POST  /waterLeakageComplaints -> Create a new waterLeakageComplaint.
     */
    @RequestMapping(value = "/waterLeakageComplaints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WaterLeakageComplaint> createWaterLeakageComplaint(@RequestBody WaterLeakageComplaint waterLeakageComplaint) throws URISyntaxException, Exception {
        log.debug("REST request to save WaterLeakageComplaint : {}", waterLeakageComplaint);
        if (waterLeakageComplaint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("waterLeakageComplaint", "idexists", "A new waterLeakageComplaint cannot already have an ID")).body(null);
        }
        WaterLeakageComplaint result = waterLeakageComplaintRepository.save(waterLeakageComplaint);
        
        workflowService.getUserDetails();
		workflowService.setAssignedDate(ZonedDateTime.now().toString());
		waterLeakageComplaintWorkflowService.createTxn(waterLeakageComplaint);
		
        return ResponseEntity.created(new URI("/api/waterLeakageComplaints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("waterLeakageComplaint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /waterLeakageComplaints -> Updates an existing waterLeakageComplaint.
     */
    @RequestMapping(value = "/waterLeakageComplaints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WaterLeakageComplaint> updateWaterLeakageComplaint(@RequestBody WaterLeakageComplaint waterLeakageComplaint) throws URISyntaxException, Exception {
        log.debug("REST request to update WaterLeakageComplaint : {}", waterLeakageComplaint);
        if (waterLeakageComplaint.getId() == null) {
            return createWaterLeakageComplaint(waterLeakageComplaint);
        }
        WaterLeakageComplaint result = waterLeakageComplaintRepository.save(waterLeakageComplaint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("waterLeakageComplaint", waterLeakageComplaint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /waterLeakageComplaints -> get all the waterLeakageComplaints.
     */
    @RequestMapping(value = "/waterLeakageComplaints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<WaterLeakageComplaint>> getAllWaterLeakageComplaints(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of WaterLeakageComplaints");
        Page<WaterLeakageComplaint> page = waterLeakageComplaintRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/waterLeakageComplaints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /waterLeakageComplaints/:id -> get the "id" waterLeakageComplaint.
     */
    @RequestMapping(value = "/waterLeakageComplaints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<WaterLeakageComplaint> getWaterLeakageComplaint(@PathVariable Long id) {
        log.debug("REST request to get WaterLeakageComplaint : {}", id);
        WaterLeakageComplaint waterLeakageComplaint = waterLeakageComplaintRepository.findOne(id);
        return Optional.ofNullable(waterLeakageComplaint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /waterLeakageComplaints/:id -> delete the "id" waterLeakageComplaint.
     */
    @RequestMapping(value = "/waterLeakageComplaints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteWaterLeakageComplaint(@PathVariable Long id) {
        log.debug("REST request to delete WaterLeakageComplaint : {}", id);
        waterLeakageComplaintRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("waterLeakageComplaint", id.toString())).build();
    }
    
    
    
    @RequestMapping(value = "/waterLeakageComplaints/forwardRequest", 
    		method = RequestMethod.POST, 
    		produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional(rollbackFor=Exception.class)
	public ResponseEntity<JobCardDTO> approveJobCard(
			@RequestBody JobCardDTO jobCardDTO) throws URISyntaxException {
		log.debug("REST request to approve JobCardDTO : {}", jobCardDTO);
		
		try {
			workflowService.setRemarks(jobCardDTO.getRemarks());
			workflowService.setApprovedDate(jobCardDTO.getApprovedDate());
			WaterLeakageComplaint waterLeakageComplaint = waterLeakageComplaintRepository.findOne(jobCardDTO.getDomainId());
			

			workflowService.getUserDetails();
			waterLeakageComplaintWorkflowService.approvedWaterLeakageComplaints(waterLeakageComplaint);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.created(new URI("/api/waterLeakageComplaints/forwardRequest/"))
				.headers(HeaderUtil.createEntityCreationAlert("jobCardDTO", ""))
				.body(null);
	}
}

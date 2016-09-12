package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.JobCardSiteStatus;
import com.callippus.water.erp.repository.JobCardSiteStatusRepository;
import com.callippus.water.erp.repository.WaterLeakageComplaintRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing JobCardSiteStatus.
 */
@RestController
@RequestMapping("/api")
public class JobCardSiteStatusResource {

    private final Logger log = LoggerFactory.getLogger(JobCardSiteStatusResource.class);
        
    @Inject
    private JobCardSiteStatusRepository jobCardSiteStatusRepository;
    
    @Inject
    private WaterLeakageComplaintRepository waterLeakageComplaintRepository;
    
    /**
     * POST  /jobCardSiteStatuss -> Create a new jobCardSiteStatus.
     */
    @RequestMapping(value = "/jobCardSiteStatuss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobCardSiteStatus> createJobCardSiteStatus(@Valid @RequestBody JobCardSiteStatus jobCardSiteStatus) throws URISyntaxException {
        log.debug("REST request to save JobCardSiteStatus : {}", jobCardSiteStatus);
        if (jobCardSiteStatus.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("jobCardSiteStatus", "idexists", "A new jobCardSiteStatus cannot already have an ID")).body(null);
        }
        JobCardSiteStatus result = jobCardSiteStatusRepository.save(jobCardSiteStatus);
        return ResponseEntity.created(new URI("/api/jobCardSiteStatuss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("jobCardSiteStatus", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jobCardSiteStatuss -> Updates an existing jobCardSiteStatus.
     */
    @RequestMapping(value = "/jobCardSiteStatuss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobCardSiteStatus> updateJobCardSiteStatus(@Valid @RequestBody JobCardSiteStatus jobCardSiteStatus) throws URISyntaxException {
        log.debug("REST request to update JobCardSiteStatus : {}", jobCardSiteStatus);
        if (jobCardSiteStatus.getId() == null) {
            return createJobCardSiteStatus(jobCardSiteStatus);
        }
        JobCardSiteStatus result = jobCardSiteStatusRepository.save(jobCardSiteStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("jobCardSiteStatus", jobCardSiteStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jobCardSiteStatuss -> get all the jobCardSiteStatuss.
     */
    @RequestMapping(value = "/jobCardSiteStatuss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<JobCardSiteStatus>> getAllJobCardSiteStatuss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of JobCardSiteStatuss");
        Page<JobCardSiteStatus> page = jobCardSiteStatusRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jobCardSiteStatuss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /jobCardSiteStatuss/:id -> get the "id" jobCardSiteStatus.
     */
    @RequestMapping(value = "/jobCardSiteStatuss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobCardSiteStatus> getJobCardSiteStatus(@PathVariable Long id) {
        log.debug("REST request to get JobCardSiteStatus : {}", id);
        JobCardSiteStatus jobCardSiteStatus = jobCardSiteStatusRepository.findOne(id);
        return Optional.ofNullable(jobCardSiteStatus)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /jobCardSiteStatuss/:id -> delete the "id" jobCardSiteStatus.
     */
    @RequestMapping(value = "/jobCardSiteStatuss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteJobCardSiteStatus(@PathVariable Long id) {
        log.debug("REST request to delete JobCardSiteStatus : {}", id);
        jobCardSiteStatusRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("jobCardSiteStatus", id.toString())).build();
    }
    
    /**
     * Get JobCardSiteStatuss by complaintId
     */
    @RequestMapping(value = "/jobCardSiteStatuss/getByComplaintId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<JobCardSiteStatus> getjobCardSiteStatussByComplaintId(@Param("waterLeakageComplaint") Long waterLeakageComplaint)
			throws Exception {
    	log.debug("REST request to JobCardSiteStatuss by complaintId : {}");
    	
    	JobCardSiteStatus jobCardSiteStatus = jobCardSiteStatusRepository.findByWaterLeakageComplaint(waterLeakageComplaintRepository.findOne(waterLeakageComplaint));
    
    	return Optional.ofNullable(jobCardSiteStatus)
				.map(result -> new ResponseEntity<>(jobCardSiteStatus, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

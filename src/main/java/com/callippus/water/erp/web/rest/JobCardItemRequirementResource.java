package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.JobCardItemRequirement;
import com.callippus.water.erp.repository.JobCardItemRequirementRepository;
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
 * REST controller for managing JobCardItemRequirement.
 */
@RestController
@RequestMapping("/api")
public class JobCardItemRequirementResource {

    private final Logger log = LoggerFactory.getLogger(JobCardItemRequirementResource.class);
        
    @Inject
    private JobCardItemRequirementRepository jobCardItemRequirementRepository;
    
    /**
     * POST  /jobCardItemRequirements -> Create a new jobCardItemRequirement.
     */
    @RequestMapping(value = "/jobCardItemRequirements",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobCardItemRequirement> createJobCardItemRequirement(@RequestBody JobCardItemRequirement jobCardItemRequirement) throws URISyntaxException {
        log.debug("REST request to save JobCardItemRequirement : {}", jobCardItemRequirement);
        if (jobCardItemRequirement.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("jobCardItemRequirement", "idexists", "A new jobCardItemRequirement cannot already have an ID")).body(null);
        }
        JobCardItemRequirement result = jobCardItemRequirementRepository.save(jobCardItemRequirement);
        return ResponseEntity.created(new URI("/api/jobCardItemRequirements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("jobCardItemRequirement", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /jobCardItemRequirements -> Updates an existing jobCardItemRequirement.
     */
    @RequestMapping(value = "/jobCardItemRequirements",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobCardItemRequirement> updateJobCardItemRequirement(@RequestBody JobCardItemRequirement jobCardItemRequirement) throws URISyntaxException {
        log.debug("REST request to update JobCardItemRequirement : {}", jobCardItemRequirement);
        if (jobCardItemRequirement.getId() == null) {
            return createJobCardItemRequirement(jobCardItemRequirement);
        }
        JobCardItemRequirement result = jobCardItemRequirementRepository.save(jobCardItemRequirement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("jobCardItemRequirement", jobCardItemRequirement.getId().toString()))
            .body(result);
    }

    /**
     * GET  /jobCardItemRequirements -> get all the jobCardItemRequirements.
     */
    @RequestMapping(value = "/jobCardItemRequirements",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<JobCardItemRequirement>> getAllJobCardItemRequirements(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of JobCardItemRequirements");
        Page<JobCardItemRequirement> page = jobCardItemRequirementRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/jobCardItemRequirements");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /jobCardItemRequirements/:id -> get the "id" jobCardItemRequirement.
     */
    @RequestMapping(value = "/jobCardItemRequirements/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<JobCardItemRequirement> getJobCardItemRequirement(@PathVariable Long id) {
        log.debug("REST request to get JobCardItemRequirement : {}", id);
        JobCardItemRequirement jobCardItemRequirement = jobCardItemRequirementRepository.findOne(id);
        return Optional.ofNullable(jobCardItemRequirement)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /jobCardItemRequirements/:id -> delete the "id" jobCardItemRequirement.
     */
    @RequestMapping(value = "/jobCardItemRequirements/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteJobCardItemRequirement(@PathVariable Long id) {
        log.debug("REST request to delete JobCardItemRequirement : {}", id);
        jobCardItemRequirementRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("jobCardItemRequirement", id.toString())).build();
    }
}

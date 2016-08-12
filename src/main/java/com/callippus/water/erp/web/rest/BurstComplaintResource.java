package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BurstComplaint;
import com.callippus.water.erp.repository.BurstComplaintRepository;
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
 * REST controller for managing BurstComplaint.
 */
@RestController
@RequestMapping("/api")
public class BurstComplaintResource {

    private final Logger log = LoggerFactory.getLogger(BurstComplaintResource.class);
        
    @Inject
    private BurstComplaintRepository burstComplaintRepository;
    
    /**
     * POST  /burstComplaints -> Create a new burstComplaint.
     */
    @RequestMapping(value = "/burstComplaints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BurstComplaint> createBurstComplaint(@RequestBody BurstComplaint burstComplaint) throws URISyntaxException {
        log.debug("REST request to save BurstComplaint : {}", burstComplaint);
        if (burstComplaint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("burstComplaint", "idexists", "A new burstComplaint cannot already have an ID")).body(null);
        }
        BurstComplaint result = burstComplaintRepository.save(burstComplaint);
        return ResponseEntity.created(new URI("/api/burstComplaints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("burstComplaint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /burstComplaints -> Updates an existing burstComplaint.
     */
    @RequestMapping(value = "/burstComplaints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BurstComplaint> updateBurstComplaint(@RequestBody BurstComplaint burstComplaint) throws URISyntaxException {
        log.debug("REST request to update BurstComplaint : {}", burstComplaint);
        if (burstComplaint.getId() == null) {
            return createBurstComplaint(burstComplaint);
        }
        BurstComplaint result = burstComplaintRepository.save(burstComplaint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("burstComplaint", burstComplaint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /burstComplaints -> get all the burstComplaints.
     */
    @RequestMapping(value = "/burstComplaints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BurstComplaint>> getAllBurstComplaints(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BurstComplaints");
        Page<BurstComplaint> page = burstComplaintRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/burstComplaints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /burstComplaints/:id -> get the "id" burstComplaint.
     */
    @RequestMapping(value = "/burstComplaints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BurstComplaint> getBurstComplaint(@PathVariable Long id) {
        log.debug("REST request to get BurstComplaint : {}", id);
        BurstComplaint burstComplaint = burstComplaintRepository.findOne(id);
        return Optional.ofNullable(burstComplaint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /burstComplaints/:id -> delete the "id" burstComplaint.
     */
    @RequestMapping(value = "/burstComplaints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBurstComplaint(@PathVariable Long id) {
        log.debug("REST request to delete BurstComplaint : {}", id);
        burstComplaintRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("burstComplaint", id.toString())).build();
    }
}

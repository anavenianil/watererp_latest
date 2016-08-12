package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.HydrantComplaint;
import com.callippus.water.erp.repository.HydrantComplaintRepository;
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
 * REST controller for managing HydrantComplaint.
 */
@RestController
@RequestMapping("/api")
public class HydrantComplaintResource {

    private final Logger log = LoggerFactory.getLogger(HydrantComplaintResource.class);
        
    @Inject
    private HydrantComplaintRepository hydrantComplaintRepository;
    
    /**
     * POST  /hydrantComplaints -> Create a new hydrantComplaint.
     */
    @RequestMapping(value = "/hydrantComplaints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HydrantComplaint> createHydrantComplaint(@RequestBody HydrantComplaint hydrantComplaint) throws URISyntaxException {
        log.debug("REST request to save HydrantComplaint : {}", hydrantComplaint);
        if (hydrantComplaint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("hydrantComplaint", "idexists", "A new hydrantComplaint cannot already have an ID")).body(null);
        }
        HydrantComplaint result = hydrantComplaintRepository.save(hydrantComplaint);
        return ResponseEntity.created(new URI("/api/hydrantComplaints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("hydrantComplaint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hydrantComplaints -> Updates an existing hydrantComplaint.
     */
    @RequestMapping(value = "/hydrantComplaints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HydrantComplaint> updateHydrantComplaint(@RequestBody HydrantComplaint hydrantComplaint) throws URISyntaxException {
        log.debug("REST request to update HydrantComplaint : {}", hydrantComplaint);
        if (hydrantComplaint.getId() == null) {
            return createHydrantComplaint(hydrantComplaint);
        }
        HydrantComplaint result = hydrantComplaintRepository.save(hydrantComplaint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("hydrantComplaint", hydrantComplaint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hydrantComplaints -> get all the hydrantComplaints.
     */
    @RequestMapping(value = "/hydrantComplaints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<HydrantComplaint>> getAllHydrantComplaints(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of HydrantComplaints");
        Page<HydrantComplaint> page = hydrantComplaintRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hydrantComplaints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /hydrantComplaints/:id -> get the "id" hydrantComplaint.
     */
    @RequestMapping(value = "/hydrantComplaints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<HydrantComplaint> getHydrantComplaint(@PathVariable Long id) {
        log.debug("REST request to get HydrantComplaint : {}", id);
        HydrantComplaint hydrantComplaint = hydrantComplaintRepository.findOne(id);
        return Optional.ofNullable(hydrantComplaint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /hydrantComplaints/:id -> delete the "id" hydrantComplaint.
     */
    @RequestMapping(value = "/hydrantComplaints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteHydrantComplaint(@PathVariable Long id) {
        log.debug("REST request to delete HydrantComplaint : {}", id);
        hydrantComplaintRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("hydrantComplaint", id.toString())).build();
    }
}

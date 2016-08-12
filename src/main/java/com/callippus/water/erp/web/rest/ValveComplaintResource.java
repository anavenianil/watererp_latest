package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ValveComplaint;
import com.callippus.water.erp.repository.ValveComplaintRepository;
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
 * REST controller for managing ValveComplaint.
 */
@RestController
@RequestMapping("/api")
public class ValveComplaintResource {

    private final Logger log = LoggerFactory.getLogger(ValveComplaintResource.class);
        
    @Inject
    private ValveComplaintRepository valveComplaintRepository;
    
    /**
     * POST  /valveComplaints -> Create a new valveComplaint.
     */
    @RequestMapping(value = "/valveComplaints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ValveComplaint> createValveComplaint(@RequestBody ValveComplaint valveComplaint) throws URISyntaxException {
        log.debug("REST request to save ValveComplaint : {}", valveComplaint);
        if (valveComplaint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("valveComplaint", "idexists", "A new valveComplaint cannot already have an ID")).body(null);
        }
        ValveComplaint result = valveComplaintRepository.save(valveComplaint);
        return ResponseEntity.created(new URI("/api/valveComplaints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("valveComplaint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /valveComplaints -> Updates an existing valveComplaint.
     */
    @RequestMapping(value = "/valveComplaints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ValveComplaint> updateValveComplaint(@RequestBody ValveComplaint valveComplaint) throws URISyntaxException {
        log.debug("REST request to update ValveComplaint : {}", valveComplaint);
        if (valveComplaint.getId() == null) {
            return createValveComplaint(valveComplaint);
        }
        ValveComplaint result = valveComplaintRepository.save(valveComplaint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("valveComplaint", valveComplaint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /valveComplaints -> get all the valveComplaints.
     */
    @RequestMapping(value = "/valveComplaints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ValveComplaint>> getAllValveComplaints(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ValveComplaints");
        Page<ValveComplaint> page = valveComplaintRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/valveComplaints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /valveComplaints/:id -> get the "id" valveComplaint.
     */
    @RequestMapping(value = "/valveComplaints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ValveComplaint> getValveComplaint(@PathVariable Long id) {
        log.debug("REST request to get ValveComplaint : {}", id);
        ValveComplaint valveComplaint = valveComplaintRepository.findOne(id);
        return Optional.ofNullable(valveComplaint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /valveComplaints/:id -> delete the "id" valveComplaint.
     */
    @RequestMapping(value = "/valveComplaints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteValveComplaint(@PathVariable Long id) {
        log.debug("REST request to delete ValveComplaint : {}", id);
        valveComplaintRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("valveComplaint", id.toString())).build();
    }
}

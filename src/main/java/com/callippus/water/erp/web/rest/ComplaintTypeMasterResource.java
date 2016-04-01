package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ComplaintTypeMaster;
import com.callippus.water.erp.repository.ComplaintTypeMasterRepository;
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
 * REST controller for managing ComplaintTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class ComplaintTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ComplaintTypeMasterResource.class);
        
    @Inject
    private ComplaintTypeMasterRepository complaintTypeMasterRepository;
    
    /**
     * POST  /complaintTypeMasters -> Create a new complaintTypeMaster.
     */
    @RequestMapping(value = "/complaintTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComplaintTypeMaster> createComplaintTypeMaster(@RequestBody ComplaintTypeMaster complaintTypeMaster) throws URISyntaxException {
        log.debug("REST request to save ComplaintTypeMaster : {}", complaintTypeMaster);
        if (complaintTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("complaintTypeMaster", "idexists", "A new complaintTypeMaster cannot already have an ID")).body(null);
        }
        ComplaintTypeMaster result = complaintTypeMasterRepository.save(complaintTypeMaster);
        return ResponseEntity.created(new URI("/api/complaintTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("complaintTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /complaintTypeMasters -> Updates an existing complaintTypeMaster.
     */
    @RequestMapping(value = "/complaintTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComplaintTypeMaster> updateComplaintTypeMaster(@RequestBody ComplaintTypeMaster complaintTypeMaster) throws URISyntaxException {
        log.debug("REST request to update ComplaintTypeMaster : {}", complaintTypeMaster);
        if (complaintTypeMaster.getId() == null) {
            return createComplaintTypeMaster(complaintTypeMaster);
        }
        ComplaintTypeMaster result = complaintTypeMasterRepository.save(complaintTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("complaintTypeMaster", complaintTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /complaintTypeMasters -> get all the complaintTypeMasters.
     */
    @RequestMapping(value = "/complaintTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ComplaintTypeMaster>> getAllComplaintTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ComplaintTypeMasters");
        Page<ComplaintTypeMaster> page = complaintTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/complaintTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /complaintTypeMasters/:id -> get the "id" complaintTypeMaster.
     */
    @RequestMapping(value = "/complaintTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ComplaintTypeMaster> getComplaintTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get ComplaintTypeMaster : {}", id);
        ComplaintTypeMaster complaintTypeMaster = complaintTypeMasterRepository.findOne(id);
        return Optional.ofNullable(complaintTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /complaintTypeMasters/:id -> delete the "id" complaintTypeMaster.
     */
    @RequestMapping(value = "/complaintTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteComplaintTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ComplaintTypeMaster : {}", id);
        complaintTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("complaintTypeMaster", id.toString())).build();
    }
}

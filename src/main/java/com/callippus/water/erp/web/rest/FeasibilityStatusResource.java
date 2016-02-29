package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.FeasibilityStatus;
import com.callippus.water.erp.repository.FeasibilityStatusRepository;
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
 * REST controller for managing FeasibilityStatus.
 */
@RestController
@RequestMapping("/api")
public class FeasibilityStatusResource {

    private final Logger log = LoggerFactory.getLogger(FeasibilityStatusResource.class);
        
    @Inject
    private FeasibilityStatusRepository feasibilityStatusRepository;
    
    /**
     * POST  /feasibilityStatuss -> Create a new feasibilityStatus.
     */
    @RequestMapping(value = "/feasibilityStatuss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FeasibilityStatus> createFeasibilityStatus(@RequestBody FeasibilityStatus feasibilityStatus) throws URISyntaxException {
        log.debug("REST request to save FeasibilityStatus : {}", feasibilityStatus);
        if (feasibilityStatus.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("feasibilityStatus", "idexists", "A new feasibilityStatus cannot already have an ID")).body(null);
        }
        FeasibilityStatus result = feasibilityStatusRepository.save(feasibilityStatus);
        return ResponseEntity.created(new URI("/api/feasibilityStatuss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("feasibilityStatus", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feasibilityStatuss -> Updates an existing feasibilityStatus.
     */
    @RequestMapping(value = "/feasibilityStatuss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FeasibilityStatus> updateFeasibilityStatus(@RequestBody FeasibilityStatus feasibilityStatus) throws URISyntaxException {
        log.debug("REST request to update FeasibilityStatus : {}", feasibilityStatus);
        if (feasibilityStatus.getId() == null) {
            return createFeasibilityStatus(feasibilityStatus);
        }
        FeasibilityStatus result = feasibilityStatusRepository.save(feasibilityStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("feasibilityStatus", feasibilityStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feasibilityStatuss -> get all the feasibilityStatuss.
     */
    @RequestMapping(value = "/feasibilityStatuss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<FeasibilityStatus>> getAllFeasibilityStatuss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of FeasibilityStatuss");
        Page<FeasibilityStatus> page = feasibilityStatusRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/feasibilityStatuss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /feasibilityStatuss/:id -> get the "id" feasibilityStatus.
     */
    @RequestMapping(value = "/feasibilityStatuss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FeasibilityStatus> getFeasibilityStatus(@PathVariable Long id) {
        log.debug("REST request to get FeasibilityStatus : {}", id);
        FeasibilityStatus feasibilityStatus = feasibilityStatusRepository.findOne(id);
        return Optional.ofNullable(feasibilityStatus)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /feasibilityStatuss/:id -> delete the "id" feasibilityStatus.
     */
    @RequestMapping(value = "/feasibilityStatuss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFeasibilityStatus(@PathVariable Long id) {
        log.debug("REST request to delete FeasibilityStatus : {}", id);
        feasibilityStatusRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("feasibilityStatus", id.toString())).build();
    }
}

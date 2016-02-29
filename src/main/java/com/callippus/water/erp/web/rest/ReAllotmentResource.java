package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ReAllotment;
import com.callippus.water.erp.repository.ReAllotmentRepository;
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
 * REST controller for managing ReAllotment.
 */
@RestController
@RequestMapping("/api")
public class ReAllotmentResource {

    private final Logger log = LoggerFactory.getLogger(ReAllotmentResource.class);
        
    @Inject
    private ReAllotmentRepository reAllotmentRepository;
    
    /**
     * POST  /reAllotments -> Create a new reAllotment.
     */
    @RequestMapping(value = "/reAllotments",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReAllotment> createReAllotment(@RequestBody ReAllotment reAllotment) throws URISyntaxException {
        log.debug("REST request to save ReAllotment : {}", reAllotment);
        if (reAllotment.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("reAllotment", "idexists", "A new reAllotment cannot already have an ID")).body(null);
        }
        ReAllotment result = reAllotmentRepository.save(reAllotment);
        return ResponseEntity.created(new URI("/api/reAllotments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("reAllotment", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reAllotments -> Updates an existing reAllotment.
     */
    @RequestMapping(value = "/reAllotments",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReAllotment> updateReAllotment(@RequestBody ReAllotment reAllotment) throws URISyntaxException {
        log.debug("REST request to update ReAllotment : {}", reAllotment);
        if (reAllotment.getId() == null) {
            return createReAllotment(reAllotment);
        }
        ReAllotment result = reAllotmentRepository.save(reAllotment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("reAllotment", reAllotment.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reAllotments -> get all the reAllotments.
     */
    @RequestMapping(value = "/reAllotments",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ReAllotment>> getAllReAllotments(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ReAllotments");
        Page<ReAllotment> page = reAllotmentRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reAllotments");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reAllotments/:id -> get the "id" reAllotment.
     */
    @RequestMapping(value = "/reAllotments/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReAllotment> getReAllotment(@PathVariable Long id) {
        log.debug("REST request to get ReAllotment : {}", id);
        ReAllotment reAllotment = reAllotmentRepository.findOne(id);
        return Optional.ofNullable(reAllotment)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reAllotments/:id -> delete the "id" reAllotment.
     */
    @RequestMapping(value = "/reAllotments/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReAllotment(@PathVariable Long id) {
        log.debug("REST request to delete ReAllotment : {}", id);
        reAllotmentRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reAllotment", id.toString())).build();
    }
}

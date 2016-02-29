package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.repository.ApplicationTxnRepository;
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
 * REST controller for managing ApplicationTxn.
 */
@RestController
@RequestMapping("/api")
public class ApplicationTxnResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationTxnResource.class);
        
    @Inject
    private ApplicationTxnRepository applicationTxnRepository;
    
    /**
     * POST  /applicationTxns -> Create a new applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> createApplicationTxn(@RequestBody ApplicationTxn applicationTxn) throws URISyntaxException {
        log.debug("REST request to save ApplicationTxn : {}", applicationTxn);
        if (applicationTxn.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("applicationTxn", "idexists", "A new applicationTxn cannot already have an ID")).body(null);
        }
        ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        return ResponseEntity.created(new URI("/api/applicationTxns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("applicationTxn", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /applicationTxns -> Updates an existing applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> updateApplicationTxn(@RequestBody ApplicationTxn applicationTxn) throws URISyntaxException {
        log.debug("REST request to update ApplicationTxn : {}", applicationTxn);
        if (applicationTxn.getId() == null) {
            return createApplicationTxn(applicationTxn);
        }
        ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("applicationTxn", applicationTxn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /applicationTxns -> get all the applicationTxns.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ApplicationTxn>> getAllApplicationTxns(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ApplicationTxns");
        Page<ApplicationTxn> page = applicationTxnRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/applicationTxns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /applicationTxns/:id -> get the "id" applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> getApplicationTxn(@PathVariable Long id) {
        log.debug("REST request to get ApplicationTxn : {}", id);
        ApplicationTxn applicationTxn = applicationTxnRepository.findOne(id);
        return Optional.ofNullable(applicationTxn)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /applicationTxns/:id -> delete the "id" applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteApplicationTxn(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationTxn : {}", id);
        applicationTxnRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("applicationTxn", id.toString())).build();
    }
}

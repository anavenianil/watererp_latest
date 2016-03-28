package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.repository.CollDetailsRepository;
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
 * REST controller for managing CollDetails.
 */
@RestController
@RequestMapping("/api")
public class CollDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CollDetailsResource.class);
        
    @Inject
    private CollDetailsRepository collDetailsRepository;
    
    /**
     * POST  /collDetailss -> Create a new collDetails.
     */
    @RequestMapping(value = "/collDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CollDetails> createCollDetails(@RequestBody CollDetails collDetails) throws URISyntaxException {
        log.debug("REST request to save CollDetails : {}", collDetails);
        if (collDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("collDetails", "idexists", "A new collDetails cannot already have an ID")).body(null);
        }
        CollDetails result = collDetailsRepository.save(collDetails);
        return ResponseEntity.created(new URI("/api/collDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("collDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /collDetailss -> Updates an existing collDetails.
     */
    @RequestMapping(value = "/collDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CollDetails> updateCollDetails(@RequestBody CollDetails collDetails) throws URISyntaxException {
        log.debug("REST request to update CollDetails : {}", collDetails);
        if (collDetails.getId() == null) {
            return createCollDetails(collDetails);
        }
        CollDetails result = collDetailsRepository.save(collDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("collDetails", collDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /collDetailss -> get all the collDetailss.
     */
    @RequestMapping(value = "/collDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CollDetails>> getAllCollDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CollDetailss");
        Page<CollDetails> page = collDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/collDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /collDetailss/:id -> get the "id" collDetails.
     */
    @RequestMapping(value = "/collDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CollDetails> getCollDetails(@PathVariable Long id) {
        log.debug("REST request to get CollDetails : {}", id);
        CollDetails collDetails = collDetailsRepository.findOne(id);
        return Optional.ofNullable(collDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /collDetailss/:id -> delete the "id" collDetails.
     */
    @RequestMapping(value = "/collDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCollDetails(@PathVariable Long id) {
        log.debug("REST request to delete CollDetails : {}", id);
        collDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("collDetails", id.toString())).build();
    }
}

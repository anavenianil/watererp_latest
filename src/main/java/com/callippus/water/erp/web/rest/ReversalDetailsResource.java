package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ReversalDetails;
import com.callippus.water.erp.repository.ReversalDetailsRepository;
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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ReversalDetails.
 */
@RestController
@RequestMapping("/api")
public class ReversalDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ReversalDetailsResource.class);
        
    @Inject
    private ReversalDetailsRepository reversalDetailsRepository;
    
    /**
     * POST  /reversalDetailss -> Create a new reversalDetails.
     */
    @RequestMapping(value = "/reversalDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReversalDetails> createReversalDetails(@Valid @RequestBody ReversalDetails reversalDetails) throws URISyntaxException {
        log.debug("REST request to save ReversalDetails : {}", reversalDetails);
        if (reversalDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("reversalDetails", "idexists", "A new reversalDetails cannot already have an ID")).body(null);
        }
        ReversalDetails result = reversalDetailsRepository.save(reversalDetails);
        return ResponseEntity.created(new URI("/api/reversalDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("reversalDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reversalDetailss -> Updates an existing reversalDetails.
     */
    @RequestMapping(value = "/reversalDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReversalDetails> updateReversalDetails(@Valid @RequestBody ReversalDetails reversalDetails) throws URISyntaxException {
        log.debug("REST request to update ReversalDetails : {}", reversalDetails);
        if (reversalDetails.getId() == null) {
            return createReversalDetails(reversalDetails);
        }
        ReversalDetails result = reversalDetailsRepository.save(reversalDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("reversalDetails", reversalDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reversalDetailss -> get all the reversalDetailss.
     */
    @RequestMapping(value = "/reversalDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ReversalDetails>> getAllReversalDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ReversalDetailss");
        Page<ReversalDetails> page = reversalDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reversalDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reversalDetailss/:id -> get the "id" reversalDetails.
     */
    @RequestMapping(value = "/reversalDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReversalDetails> getReversalDetails(@PathVariable Long id) {
        log.debug("REST request to get ReversalDetails : {}", id);
        ReversalDetails reversalDetails = reversalDetailsRepository.findOne(id);
        return Optional.ofNullable(reversalDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reversalDetailss/:id -> delete the "id" reversalDetails.
     */
    @RequestMapping(value = "/reversalDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReversalDetails(@PathVariable Long id) {
        log.debug("REST request to delete ReversalDetails : {}", id);
        reversalDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reversalDetails", id.toString())).build();
    }
}

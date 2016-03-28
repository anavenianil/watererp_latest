package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
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
 * REST controller for managing BillFullDetails.
 */
@RestController
@RequestMapping("/api")
public class BillFullDetailsResource {

    private final Logger log = LoggerFactory.getLogger(BillFullDetailsResource.class);
        
    @Inject
    private BillFullDetailsRepository billFullDetailsRepository;
    
    /**
     * POST  /billFullDetailss -> Create a new billFullDetails.
     */
    @RequestMapping(value = "/billFullDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillFullDetails> createBillFullDetails(@Valid @RequestBody BillFullDetails billFullDetails) throws URISyntaxException {
        log.debug("REST request to save BillFullDetails : {}", billFullDetails);
        if (billFullDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("billFullDetails", "idexists", "A new billFullDetails cannot already have an ID")).body(null);
        }
        BillFullDetails result = billFullDetailsRepository.save(billFullDetails);
        return ResponseEntity.created(new URI("/api/billFullDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("billFullDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /billFullDetailss -> Updates an existing billFullDetails.
     */
    @RequestMapping(value = "/billFullDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillFullDetails> updateBillFullDetails(@Valid @RequestBody BillFullDetails billFullDetails) throws URISyntaxException {
        log.debug("REST request to update BillFullDetails : {}", billFullDetails);
        if (billFullDetails.getId() == null) {
            return createBillFullDetails(billFullDetails);
        }
        BillFullDetails result = billFullDetailsRepository.save(billFullDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("billFullDetails", billFullDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /billFullDetailss -> get all the billFullDetailss.
     */
    @RequestMapping(value = "/billFullDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BillFullDetails>> getAllBillFullDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BillFullDetailss");
        Page<BillFullDetails> page = billFullDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/billFullDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /billFullDetailss/:id -> get the "id" billFullDetails.
     */
    @RequestMapping(value = "/billFullDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillFullDetails> getBillFullDetails(@PathVariable Long id) {
        log.debug("REST request to get BillFullDetails : {}", id);
        BillFullDetails billFullDetails = billFullDetailsRepository.findOne(id);
        return Optional.ofNullable(billFullDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /billFullDetailss/:id -> delete the "id" billFullDetails.
     */
    @RequestMapping(value = "/billFullDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBillFullDetails(@PathVariable Long id) {
        log.debug("REST request to delete BillFullDetails : {}", id);
        billFullDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("billFullDetails", id.toString())).build();
    }
}

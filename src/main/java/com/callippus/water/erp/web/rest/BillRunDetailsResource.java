package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
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
 * REST controller for managing BillRunDetails.
 */
@RestController
@RequestMapping("/api")
public class BillRunDetailsResource {

    private final Logger log = LoggerFactory.getLogger(BillRunDetailsResource.class);
        
    @Inject
    private BillRunDetailsRepository billRunDetailsRepository;
    
    /**
     * POST  /billRunDetailss -> Create a new billRunDetails.
     */
    @RequestMapping(value = "/billRunDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillRunDetails> createBillRunDetails(@RequestBody BillRunDetails billRunDetails) throws URISyntaxException {
        log.debug("REST request to save BillRunDetails : {}", billRunDetails);
        if (billRunDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("billRunDetails", "idexists", "A new billRunDetails cannot already have an ID")).body(null);
        }
        BillRunDetails result = billRunDetailsRepository.save(billRunDetails);
        return ResponseEntity.created(new URI("/api/billRunDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("billRunDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /billRunDetailss -> Updates an existing billRunDetails.
     */
    @RequestMapping(value = "/billRunDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillRunDetails> updateBillRunDetails(@RequestBody BillRunDetails billRunDetails) throws URISyntaxException {
        log.debug("REST request to update BillRunDetails : {}", billRunDetails);
        if (billRunDetails.getId() == null) {
            return createBillRunDetails(billRunDetails);
        }
        BillRunDetails result = billRunDetailsRepository.save(billRunDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("billRunDetails", billRunDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /billRunDetailss -> get all the billRunDetailss.
     */
    @RequestMapping(value = "/billRunDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BillRunDetails>> getAllBillRunDetailss(Pageable pageable,
    		@RequestParam(value = "can", required = false) String can,
    		@RequestParam(value = "status", required = false) Integer status)
        throws URISyntaxException {
        log.debug("REST request to get a page of BillRunDetailss");
        //Page<BillRunDetails> page = billRunDetailsRepository.findAll(pageable);
        Page<BillRunDetails> page;
        if(can == null){
        	page = billRunDetailsRepository.findAll(pageable);
        }else{
        	page = billRunDetailsRepository.findTop3ByCanAndStatusOrderByIdDesc(pageable, can, status);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/billRunDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /billRunDetailss/:id -> get the "id" billRunDetails.
     */
    @RequestMapping(value = "/billRunDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillRunDetails> getBillRunDetails(@PathVariable Long id) {
        log.debug("REST request to get BillRunDetails : {}", id);
        BillRunDetails billRunDetails = billRunDetailsRepository.findOne(id);
        return Optional.ofNullable(billRunDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /billRunDetailss/billRun/:id -> get the "id" billRunMaster.
     */
    @RequestMapping(value = "/billRunDetailss/billRun/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BillRunDetails>> getBillRunDetailsByBillRunId(@PathVariable Long id, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BillRunDetailss");
        Page<BillRunDetails> page = billRunDetailsRepository.findByBillRunId(id, pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/billRunDetailss/billRun/" + id);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * DELETE  /billRunDetailss/:id -> delete the "id" billRunDetails.
     */
    @RequestMapping(value = "/billRunDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBillRunDetails(@PathVariable Long id) {
        log.debug("REST request to delete BillRunDetails : {}", id);
        billRunDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("billRunDetails", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ExpenseDetails;
import com.callippus.water.erp.repository.ExpenseDetailsRepository;
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
 * REST controller for managing ExpenseDetails.
 */
@RestController
@RequestMapping("/api")
public class ExpenseDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ExpenseDetailsResource.class);
        
    @Inject
    private ExpenseDetailsRepository expenseDetailsRepository;
    
    /**
     * POST  /expenseDetailss -> Create a new expenseDetails.
     */
    @RequestMapping(value = "/expenseDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ExpenseDetails> createExpenseDetails(@RequestBody ExpenseDetails expenseDetails) throws URISyntaxException {
        log.debug("REST request to save ExpenseDetails : {}", expenseDetails);
        if (expenseDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("expenseDetails", "idexists", "A new expenseDetails cannot already have an ID")).body(null);
        }
        ExpenseDetails result = expenseDetailsRepository.save(expenseDetails);
        return ResponseEntity.created(new URI("/api/expenseDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("expenseDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /expenseDetailss -> Updates an existing expenseDetails.
     */
    @RequestMapping(value = "/expenseDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ExpenseDetails> updateExpenseDetails(@RequestBody ExpenseDetails expenseDetails) throws URISyntaxException {
        log.debug("REST request to update ExpenseDetails : {}", expenseDetails);
        if (expenseDetails.getId() == null) {
            return createExpenseDetails(expenseDetails);
        }
        ExpenseDetails result = expenseDetailsRepository.save(expenseDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("expenseDetails", expenseDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /expenseDetailss -> get all the expenseDetailss.
     */
    @RequestMapping(value = "/expenseDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ExpenseDetails>> getAllExpenseDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ExpenseDetailss");
        Page<ExpenseDetails> page = expenseDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/expenseDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /expenseDetailss/:id -> get the "id" expenseDetails.
     */
    @RequestMapping(value = "/expenseDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ExpenseDetails> getExpenseDetails(@PathVariable Long id) {
        log.debug("REST request to get ExpenseDetails : {}", id);
        ExpenseDetails expenseDetails = expenseDetailsRepository.findOne(id);
        return Optional.ofNullable(expenseDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /expenseDetailss/:id -> delete the "id" expenseDetails.
     */
    @RequestMapping(value = "/expenseDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteExpenseDetails(@PathVariable Long id) {
        log.debug("REST request to delete ExpenseDetails : {}", id);
        expenseDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("expenseDetails", id.toString())).build();
    }
}

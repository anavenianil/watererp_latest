package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.TransactionTypeMaster;
import com.callippus.water.erp.repository.TransactionTypeMasterRepository;
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
 * REST controller for managing TransactionTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class TransactionTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(TransactionTypeMasterResource.class);
        
    @Inject
    private TransactionTypeMasterRepository transactionTypeMasterRepository;
    
    /**
     * POST  /transactionTypeMasters -> Create a new transactionTypeMaster.
     */
    @RequestMapping(value = "/transactionTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TransactionTypeMaster> createTransactionTypeMaster(@Valid @RequestBody TransactionTypeMaster transactionTypeMaster) throws URISyntaxException {
        log.debug("REST request to save TransactionTypeMaster : {}", transactionTypeMaster);
        if (transactionTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("transactionTypeMaster", "idexists", "A new transactionTypeMaster cannot already have an ID")).body(null);
        }
        TransactionTypeMaster result = transactionTypeMasterRepository.save(transactionTypeMaster);
        return ResponseEntity.created(new URI("/api/transactionTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("transactionTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /transactionTypeMasters -> Updates an existing transactionTypeMaster.
     */
    @RequestMapping(value = "/transactionTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TransactionTypeMaster> updateTransactionTypeMaster(@Valid @RequestBody TransactionTypeMaster transactionTypeMaster) throws URISyntaxException {
        log.debug("REST request to update TransactionTypeMaster : {}", transactionTypeMaster);
        if (transactionTypeMaster.getId() == null) {
            return createTransactionTypeMaster(transactionTypeMaster);
        }
        TransactionTypeMaster result = transactionTypeMasterRepository.save(transactionTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("transactionTypeMaster", transactionTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /transactionTypeMasters -> get all the transactionTypeMasters.
     */
    @RequestMapping(value = "/transactionTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TransactionTypeMaster>> getAllTransactionTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of TransactionTypeMasters");
        Page<TransactionTypeMaster> page = transactionTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/transactionTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /transactionTypeMasters/:id -> get the "id" transactionTypeMaster.
     */
    @RequestMapping(value = "/transactionTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TransactionTypeMaster> getTransactionTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get TransactionTypeMaster : {}", id);
        TransactionTypeMaster transactionTypeMaster = transactionTypeMasterRepository.findOne(id);
        return Optional.ofNullable(transactionTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /transactionTypeMasters/:id -> delete the "id" transactionTypeMaster.
     */
    @RequestMapping(value = "/transactionTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTransactionTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete TransactionTypeMaster : {}", id);
        transactionTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("transactionTypeMaster", id.toString())).build();
    }
}

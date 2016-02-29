package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CashBookMaster;
import com.callippus.water.erp.repository.CashBookMasterRepository;
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
 * REST controller for managing CashBookMaster.
 */
@RestController
@RequestMapping("/api")
public class CashBookMasterResource {

    private final Logger log = LoggerFactory.getLogger(CashBookMasterResource.class);
        
    @Inject
    private CashBookMasterRepository cashBookMasterRepository;
    
    /**
     * POST  /cashBookMasters -> Create a new cashBookMaster.
     */
    @RequestMapping(value = "/cashBookMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CashBookMaster> createCashBookMaster(@Valid @RequestBody CashBookMaster cashBookMaster) throws URISyntaxException {
        log.debug("REST request to save CashBookMaster : {}", cashBookMaster);
        if (cashBookMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("cashBookMaster", "idexists", "A new cashBookMaster cannot already have an ID")).body(null);
        }
        CashBookMaster result = cashBookMasterRepository.save(cashBookMaster);
        return ResponseEntity.created(new URI("/api/cashBookMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("cashBookMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /cashBookMasters -> Updates an existing cashBookMaster.
     */
    @RequestMapping(value = "/cashBookMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CashBookMaster> updateCashBookMaster(@Valid @RequestBody CashBookMaster cashBookMaster) throws URISyntaxException {
        log.debug("REST request to update CashBookMaster : {}", cashBookMaster);
        if (cashBookMaster.getId() == null) {
            return createCashBookMaster(cashBookMaster);
        }
        CashBookMaster result = cashBookMasterRepository.save(cashBookMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("cashBookMaster", cashBookMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /cashBookMasters -> get all the cashBookMasters.
     */
    @RequestMapping(value = "/cashBookMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CashBookMaster>> getAllCashBookMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CashBookMasters");
        Page<CashBookMaster> page = cashBookMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/cashBookMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /cashBookMasters/:id -> get the "id" cashBookMaster.
     */
    @RequestMapping(value = "/cashBookMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CashBookMaster> getCashBookMaster(@PathVariable Long id) {
        log.debug("REST request to get CashBookMaster : {}", id);
        CashBookMaster cashBookMaster = cashBookMasterRepository.findOne(id);
        return Optional.ofNullable(cashBookMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /cashBookMasters/:id -> delete the "id" cashBookMaster.
     */
    @RequestMapping(value = "/cashBookMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCashBookMaster(@PathVariable Long id) {
        log.debug("REST request to delete CashBookMaster : {}", id);
        cashBookMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("cashBookMaster", id.toString())).build();
    }
}

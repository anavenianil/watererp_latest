package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BankMaster;
import com.callippus.water.erp.repository.BankMasterRepository;
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
 * REST controller for managing BankMaster.
 */
@RestController
@RequestMapping("/api")
public class BankMasterResource {

    private final Logger log = LoggerFactory.getLogger(BankMasterResource.class);
        
    @Inject
    private BankMasterRepository bankMasterRepository;
    
    /**
     * POST  /bankMasters -> Create a new bankMaster.
     */
    @RequestMapping(value = "/bankMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BankMaster> createBankMaster(@Valid @RequestBody BankMaster bankMaster) throws URISyntaxException {
        log.debug("REST request to save BankMaster : {}", bankMaster);
        if (bankMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("bankMaster", "idexists", "A new bankMaster cannot already have an ID")).body(null);
        }
        BankMaster result = bankMasterRepository.save(bankMaster);
        return ResponseEntity.created(new URI("/api/bankMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("bankMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bankMasters -> Updates an existing bankMaster.
     */
    @RequestMapping(value = "/bankMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BankMaster> updateBankMaster(@Valid @RequestBody BankMaster bankMaster) throws URISyntaxException {
        log.debug("REST request to update BankMaster : {}", bankMaster);
        if (bankMaster.getId() == null) {
            return createBankMaster(bankMaster);
        }
        BankMaster result = bankMasterRepository.save(bankMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("bankMaster", bankMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bankMasters -> get all the bankMasters.
     */
    @RequestMapping(value = "/bankMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BankMaster>> getAllBankMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BankMasters");
        Page<BankMaster> page = bankMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bankMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /bankMasters/:id -> get the "id" bankMaster.
     */
    @RequestMapping(value = "/bankMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BankMaster> getBankMaster(@PathVariable Long id) {
        log.debug("REST request to get BankMaster : {}", id);
        BankMaster bankMaster = bankMasterRepository.findOne(id);
        return Optional.ofNullable(bankMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /bankMasters/:id -> delete the "id" bankMaster.
     */
    @RequestMapping(value = "/bankMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBankMaster(@PathVariable Long id) {
        log.debug("REST request to delete BankMaster : {}", id);
        bankMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("bankMaster", id.toString())).build();
    }
}

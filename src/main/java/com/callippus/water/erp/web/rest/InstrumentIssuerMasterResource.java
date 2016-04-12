package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.InstrumentIssuerMaster;
import com.callippus.water.erp.repository.InstrumentIssuerMasterRepository;
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
 * REST controller for managing InstrumentIssuerMaster.
 */
@RestController
@RequestMapping("/api")
public class InstrumentIssuerMasterResource {

    private final Logger log = LoggerFactory.getLogger(InstrumentIssuerMasterResource.class);
        
    @Inject
    private InstrumentIssuerMasterRepository instrumentIssuerMasterRepository;
    
    /**
     * POST  /instrumentIssuerMasters -> Create a new instrumentIssuerMaster.
     */
    @RequestMapping(value = "/instrumentIssuerMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InstrumentIssuerMaster> createInstrumentIssuerMaster(@Valid @RequestBody InstrumentIssuerMaster instrumentIssuerMaster) throws URISyntaxException {
        log.debug("REST request to save InstrumentIssuerMaster : {}", instrumentIssuerMaster);
        if (instrumentIssuerMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("instrumentIssuerMaster", "idexists", "A new instrumentIssuerMaster cannot already have an ID")).body(null);
        }
        InstrumentIssuerMaster result = instrumentIssuerMasterRepository.save(instrumentIssuerMaster);
        return ResponseEntity.created(new URI("/api/instrumentIssuerMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("instrumentIssuerMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /instrumentIssuerMasters -> Updates an existing instrumentIssuerMaster.
     */
    @RequestMapping(value = "/instrumentIssuerMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InstrumentIssuerMaster> updateInstrumentIssuerMaster(@Valid @RequestBody InstrumentIssuerMaster instrumentIssuerMaster) throws URISyntaxException {
        log.debug("REST request to update InstrumentIssuerMaster : {}", instrumentIssuerMaster);
        if (instrumentIssuerMaster.getId() == null) {
            return createInstrumentIssuerMaster(instrumentIssuerMaster);
        }
        InstrumentIssuerMaster result = instrumentIssuerMasterRepository.save(instrumentIssuerMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("instrumentIssuerMaster", instrumentIssuerMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /instrumentIssuerMasters -> get all the instrumentIssuerMasters.
     */
    @RequestMapping(value = "/instrumentIssuerMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<InstrumentIssuerMaster>> getAllInstrumentIssuerMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of InstrumentIssuerMasters");
        Page<InstrumentIssuerMaster> page = instrumentIssuerMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/instrumentIssuerMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /instrumentIssuerMasters/:id -> get the "id" instrumentIssuerMaster.
     */
    @RequestMapping(value = "/instrumentIssuerMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InstrumentIssuerMaster> getInstrumentIssuerMaster(@PathVariable Long id) {
        log.debug("REST request to get InstrumentIssuerMaster : {}", id);
        InstrumentIssuerMaster instrumentIssuerMaster = instrumentIssuerMasterRepository.findOne(id);
        return Optional.ofNullable(instrumentIssuerMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /instrumentIssuerMasters/:id -> delete the "id" instrumentIssuerMaster.
     */
    @RequestMapping(value = "/instrumentIssuerMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInstrumentIssuerMaster(@PathVariable Long id) {
        log.debug("REST request to delete InstrumentIssuerMaster : {}", id);
        instrumentIssuerMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("instrumentIssuerMaster", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.SchemeMaster;
import com.callippus.water.erp.repository.SchemeMasterRepository;
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
 * REST controller for managing SchemeMaster.
 */
@RestController
@RequestMapping("/api")
public class SchemeMasterResource {

    private final Logger log = LoggerFactory.getLogger(SchemeMasterResource.class);
        
    @Inject
    private SchemeMasterRepository schemeMasterRepository;
    
    /**
     * POST  /schemeMasters -> Create a new schemeMaster.
     */
    @RequestMapping(value = "/schemeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SchemeMaster> createSchemeMaster(@Valid @RequestBody SchemeMaster schemeMaster) throws URISyntaxException {
        log.debug("REST request to save SchemeMaster : {}", schemeMaster);
        if (schemeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("schemeMaster", "idexists", "A new schemeMaster cannot already have an ID")).body(null);
        }
        SchemeMaster result = schemeMasterRepository.save(schemeMaster);
        return ResponseEntity.created(new URI("/api/schemeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("schemeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /schemeMasters -> Updates an existing schemeMaster.
     */
    @RequestMapping(value = "/schemeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SchemeMaster> updateSchemeMaster(@Valid @RequestBody SchemeMaster schemeMaster) throws URISyntaxException {
        log.debug("REST request to update SchemeMaster : {}", schemeMaster);
        if (schemeMaster.getId() == null) {
            return createSchemeMaster(schemeMaster);
        }
        SchemeMaster result = schemeMasterRepository.save(schemeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("schemeMaster", schemeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /schemeMasters -> get all the schemeMasters.
     */
    @RequestMapping(value = "/schemeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SchemeMaster>> getAllSchemeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SchemeMasters");
        Page<SchemeMaster> page = schemeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/schemeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /schemeMasters/:id -> get the "id" schemeMaster.
     */
    @RequestMapping(value = "/schemeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SchemeMaster> getSchemeMaster(@PathVariable Long id) {
        log.debug("REST request to get SchemeMaster : {}", id);
        SchemeMaster schemeMaster = schemeMasterRepository.findOne(id);
        return Optional.ofNullable(schemeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /schemeMasters/:id -> delete the "id" schemeMaster.
     */
    @RequestMapping(value = "/schemeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSchemeMaster(@PathVariable Long id) {
        log.debug("REST request to delete SchemeMaster : {}", id);
        schemeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("schemeMaster", id.toString())).build();
    }
}

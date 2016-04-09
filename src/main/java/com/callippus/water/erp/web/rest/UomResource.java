package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.Uom;
import com.callippus.water.erp.repository.UomRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing Uom.
 */
@RestController
@RequestMapping("/api")
public class UomResource {

    private final Logger log = LoggerFactory.getLogger(UomResource.class);
        
    @Inject
    private UomRepository uomRepository;
    
    /**
     * POST  /uoms -> Create a new uom.
     */
    @RequestMapping(value = "/uoms",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Uom> createUom(@Valid @RequestBody Uom uom) throws URISyntaxException {
        log.debug("REST request to save Uom : {}", uom);
        if (uom.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("uom", "idexists", "A new uom cannot already have an ID")).body(null);
        }
        Uom result = uomRepository.save(uom);
        return ResponseEntity.created(new URI("/api/uoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("uom", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uoms -> Updates an existing uom.
     */
    @RequestMapping(value = "/uoms",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Uom> updateUom(@Valid @RequestBody Uom uom) throws URISyntaxException {
        log.debug("REST request to update Uom : {}", uom);
        if (uom.getId() == null) {
            return createUom(uom);
        }
        Uom result = uomRepository.save(uom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("uom", uom.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uoms -> get all the uoms.
     */
    @RequestMapping(value = "/uoms",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Uom> getAllUoms() {
        log.debug("REST request to get all Uoms");
        return uomRepository.findAll();
            }

    /**
     * GET  /uoms/:id -> get the "id" uom.
     */
    @RequestMapping(value = "/uoms/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Uom> getUom(@PathVariable Long id) {
        log.debug("REST request to get Uom : {}", id);
        Uom uom = uomRepository.findOne(id);
        return Optional.ofNullable(uom)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /uoms/:id -> delete the "id" uom.
     */
    @RequestMapping(value = "/uoms/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUom(@PathVariable Long id) {
        log.debug("REST request to delete Uom : {}", id);
        uomRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("uom", id.toString())).build();
    }
}

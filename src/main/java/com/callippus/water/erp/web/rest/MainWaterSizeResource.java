package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MainWaterSize;
import com.callippus.water.erp.repository.MainWaterSizeRepository;
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
 * REST controller for managing MainWaterSize.
 */
@RestController
@RequestMapping("/api")
public class MainWaterSizeResource {

    private final Logger log = LoggerFactory.getLogger(MainWaterSizeResource.class);
        
    @Inject
    private MainWaterSizeRepository mainWaterSizeRepository;
    
    /**
     * POST  /mainWaterSizes -> Create a new mainWaterSize.
     */
    @RequestMapping(value = "/mainWaterSizes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MainWaterSize> createMainWaterSize(@Valid @RequestBody MainWaterSize mainWaterSize) throws URISyntaxException {
        log.debug("REST request to save MainWaterSize : {}", mainWaterSize);
        if (mainWaterSize.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("mainWaterSize", "idexists", "A new mainWaterSize cannot already have an ID")).body(null);
        }
        MainWaterSize result = mainWaterSizeRepository.save(mainWaterSize);
        return ResponseEntity.created(new URI("/api/mainWaterSizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("mainWaterSize", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mainWaterSizes -> Updates an existing mainWaterSize.
     */
    @RequestMapping(value = "/mainWaterSizes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MainWaterSize> updateMainWaterSize(@Valid @RequestBody MainWaterSize mainWaterSize) throws URISyntaxException {
        log.debug("REST request to update MainWaterSize : {}", mainWaterSize);
        if (mainWaterSize.getId() == null) {
            return createMainWaterSize(mainWaterSize);
        }
        MainWaterSize result = mainWaterSizeRepository.save(mainWaterSize);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("mainWaterSize", mainWaterSize.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mainWaterSizes -> get all the mainWaterSizes.
     */
    @RequestMapping(value = "/mainWaterSizes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MainWaterSize>> getAllMainWaterSizes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MainWaterSizes");
        Page<MainWaterSize> page = mainWaterSizeRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mainWaterSizes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /mainWaterSizes/:id -> get the "id" mainWaterSize.
     */
    @RequestMapping(value = "/mainWaterSizes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MainWaterSize> getMainWaterSize(@PathVariable Long id) {
        log.debug("REST request to get MainWaterSize : {}", id);
        MainWaterSize mainWaterSize = mainWaterSizeRepository.findOne(id);
        return Optional.ofNullable(mainWaterSize)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /mainWaterSizes/:id -> delete the "id" mainWaterSize.
     */
    @RequestMapping(value = "/mainWaterSizes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMainWaterSize(@PathVariable Long id) {
        log.debug("REST request to delete MainWaterSize : {}", id);
        mainWaterSizeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("mainWaterSize", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MainSewerageSize;
import com.callippus.water.erp.repository.MainSewerageSizeRepository;
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
 * REST controller for managing MainSewerageSize.
 */
@RestController
@RequestMapping("/api")
public class MainSewerageSizeResource {

    private final Logger log = LoggerFactory.getLogger(MainSewerageSizeResource.class);
        
    @Inject
    private MainSewerageSizeRepository mainSewerageSizeRepository;
    
    /**
     * POST  /mainSewerageSizes -> Create a new mainSewerageSize.
     */
    @RequestMapping(value = "/mainSewerageSizes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MainSewerageSize> createMainSewerageSize(@Valid @RequestBody MainSewerageSize mainSewerageSize) throws URISyntaxException {
        log.debug("REST request to save MainSewerageSize : {}", mainSewerageSize);
        if (mainSewerageSize.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("mainSewerageSize", "idexists", "A new mainSewerageSize cannot already have an ID")).body(null);
        }
        MainSewerageSize result = mainSewerageSizeRepository.save(mainSewerageSize);
        return ResponseEntity.created(new URI("/api/mainSewerageSizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("mainSewerageSize", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mainSewerageSizes -> Updates an existing mainSewerageSize.
     */
    @RequestMapping(value = "/mainSewerageSizes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MainSewerageSize> updateMainSewerageSize(@Valid @RequestBody MainSewerageSize mainSewerageSize) throws URISyntaxException {
        log.debug("REST request to update MainSewerageSize : {}", mainSewerageSize);
        if (mainSewerageSize.getId() == null) {
            return createMainSewerageSize(mainSewerageSize);
        }
        MainSewerageSize result = mainSewerageSizeRepository.save(mainSewerageSize);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("mainSewerageSize", mainSewerageSize.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mainSewerageSizes -> get all the mainSewerageSizes.
     */
    @RequestMapping(value = "/mainSewerageSizes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MainSewerageSize>> getAllMainSewerageSizes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MainSewerageSizes");
        Page<MainSewerageSize> page = mainSewerageSizeRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mainSewerageSizes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /mainSewerageSizes/:id -> get the "id" mainSewerageSize.
     */
    @RequestMapping(value = "/mainSewerageSizes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MainSewerageSize> getMainSewerageSize(@PathVariable Long id) {
        log.debug("REST request to get MainSewerageSize : {}", id);
        MainSewerageSize mainSewerageSize = mainSewerageSizeRepository.findOne(id);
        return Optional.ofNullable(mainSewerageSize)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /mainSewerageSizes/:id -> delete the "id" mainSewerageSize.
     */
    @RequestMapping(value = "/mainSewerageSizes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMainSewerageSize(@PathVariable Long id) {
        log.debug("REST request to delete MainSewerageSize : {}", id);
        mainSewerageSizeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("mainSewerageSize", id.toString())).build();
    }
}

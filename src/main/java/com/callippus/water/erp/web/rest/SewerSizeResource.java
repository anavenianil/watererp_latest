package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.SewerSize;
import com.callippus.water.erp.repository.SewerSizeRepository;
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
 * REST controller for managing SewerSize.
 */
@RestController
@RequestMapping("/api")
public class SewerSizeResource {

    private final Logger log = LoggerFactory.getLogger(SewerSizeResource.class);
        
    @Inject
    private SewerSizeRepository sewerSizeRepository;
    
    /**
     * POST  /sewerSizes -> Create a new sewerSize.
     */
    @RequestMapping(value = "/sewerSizes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerSize> createSewerSize(@Valid @RequestBody SewerSize sewerSize) throws URISyntaxException {
        log.debug("REST request to save SewerSize : {}", sewerSize);
        if (sewerSize.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sewerSize", "idexists", "A new sewerSize cannot already have an ID")).body(null);
        }
        SewerSize result = sewerSizeRepository.save(sewerSize);
        return ResponseEntity.created(new URI("/api/sewerSizes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sewerSize", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sewerSizes -> Updates an existing sewerSize.
     */
    @RequestMapping(value = "/sewerSizes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerSize> updateSewerSize(@Valid @RequestBody SewerSize sewerSize) throws URISyntaxException {
        log.debug("REST request to update SewerSize : {}", sewerSize);
        if (sewerSize.getId() == null) {
            return createSewerSize(sewerSize);
        }
        SewerSize result = sewerSizeRepository.save(sewerSize);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sewerSize", sewerSize.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sewerSizes -> get all the sewerSizes.
     */
    @RequestMapping(value = "/sewerSizes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SewerSize>> getAllSewerSizes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SewerSizes");
        Page<SewerSize> page = sewerSizeRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sewerSizes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sewerSizes/:id -> get the "id" sewerSize.
     */
    @RequestMapping(value = "/sewerSizes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerSize> getSewerSize(@PathVariable Long id) {
        log.debug("REST request to get SewerSize : {}", id);
        SewerSize sewerSize = sewerSizeRepository.findOne(id);
        return Optional.ofNullable(sewerSize)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sewerSizes/:id -> delete the "id" sewerSize.
     */
    @RequestMapping(value = "/sewerSizes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSewerSize(@PathVariable Long id) {
        log.debug("REST request to delete SewerSize : {}", id);
        sewerSizeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sewerSize", id.toString())).build();
    }
}

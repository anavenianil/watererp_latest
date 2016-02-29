package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.PipeSizeMaster;
import com.callippus.water.erp.repository.PipeSizeMasterRepository;
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
 * REST controller for managing PipeSizeMaster.
 */
@RestController
@RequestMapping("/api")
public class PipeSizeMasterResource {

    private final Logger log = LoggerFactory.getLogger(PipeSizeMasterResource.class);
        
    @Inject
    private PipeSizeMasterRepository pipeSizeMasterRepository;
    
    /**
     * POST  /pipeSizeMasters -> Create a new pipeSizeMaster.
     */
    @RequestMapping(value = "/pipeSizeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PipeSizeMaster> createPipeSizeMaster(@Valid @RequestBody PipeSizeMaster pipeSizeMaster) throws URISyntaxException {
        log.debug("REST request to save PipeSizeMaster : {}", pipeSizeMaster);
        if (pipeSizeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("pipeSizeMaster", "idexists", "A new pipeSizeMaster cannot already have an ID")).body(null);
        }
        PipeSizeMaster result = pipeSizeMasterRepository.save(pipeSizeMaster);
        return ResponseEntity.created(new URI("/api/pipeSizeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("pipeSizeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pipeSizeMasters -> Updates an existing pipeSizeMaster.
     */
    @RequestMapping(value = "/pipeSizeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PipeSizeMaster> updatePipeSizeMaster(@Valid @RequestBody PipeSizeMaster pipeSizeMaster) throws URISyntaxException {
        log.debug("REST request to update PipeSizeMaster : {}", pipeSizeMaster);
        if (pipeSizeMaster.getId() == null) {
            return createPipeSizeMaster(pipeSizeMaster);
        }
        PipeSizeMaster result = pipeSizeMasterRepository.save(pipeSizeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("pipeSizeMaster", pipeSizeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pipeSizeMasters -> get all the pipeSizeMasters.
     */
    @RequestMapping(value = "/pipeSizeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PipeSizeMaster>> getAllPipeSizeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PipeSizeMasters");
        Page<PipeSizeMaster> page = pipeSizeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pipeSizeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pipeSizeMasters/:id -> get the "id" pipeSizeMaster.
     */
    @RequestMapping(value = "/pipeSizeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PipeSizeMaster> getPipeSizeMaster(@PathVariable Long id) {
        log.debug("REST request to get PipeSizeMaster : {}", id);
        PipeSizeMaster pipeSizeMaster = pipeSizeMasterRepository.findOne(id);
        return Optional.ofNullable(pipeSizeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /pipeSizeMasters/:id -> delete the "id" pipeSizeMaster.
     */
    @RequestMapping(value = "/pipeSizeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePipeSizeMaster(@PathVariable Long id) {
        log.debug("REST request to delete PipeSizeMaster : {}", id);
        pipeSizeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("pipeSizeMaster", id.toString())).build();
    }
}

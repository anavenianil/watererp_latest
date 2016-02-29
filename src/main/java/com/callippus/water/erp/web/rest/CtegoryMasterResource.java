package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CtegoryMaster;
import com.callippus.water.erp.repository.CtegoryMasterRepository;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CtegoryMaster.
 */
@RestController
@RequestMapping("/api")
public class CtegoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(CtegoryMasterResource.class);
        
    @Inject
    private CtegoryMasterRepository ctegoryMasterRepository;
    
    /**
     * POST  /ctegoryMasters -> Create a new ctegoryMaster.
     */
    @RequestMapping(value = "/ctegoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CtegoryMaster> createCtegoryMaster(@RequestBody CtegoryMaster ctegoryMaster) throws URISyntaxException {
        log.debug("REST request to save CtegoryMaster : {}", ctegoryMaster);
        if (ctegoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ctegoryMaster", "idexists", "A new ctegoryMaster cannot already have an ID")).body(null);
        }
        CtegoryMaster result = ctegoryMasterRepository.save(ctegoryMaster);
        return ResponseEntity.created(new URI("/api/ctegoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("ctegoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ctegoryMasters -> Updates an existing ctegoryMaster.
     */
    @RequestMapping(value = "/ctegoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CtegoryMaster> updateCtegoryMaster(@RequestBody CtegoryMaster ctegoryMaster) throws URISyntaxException {
        log.debug("REST request to update CtegoryMaster : {}", ctegoryMaster);
        if (ctegoryMaster.getId() == null) {
            return createCtegoryMaster(ctegoryMaster);
        }
        CtegoryMaster result = ctegoryMasterRepository.save(ctegoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("ctegoryMaster", ctegoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ctegoryMasters -> get all the ctegoryMasters.
     */
    @RequestMapping(value = "/ctegoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CtegoryMaster>> getAllCtegoryMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CtegoryMasters");
        Page<CtegoryMaster> page = ctegoryMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ctegoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ctegoryMasters/:id -> get the "id" ctegoryMaster.
     */
    @RequestMapping(value = "/ctegoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CtegoryMaster> getCtegoryMaster(@PathVariable Long id) {
        log.debug("REST request to get CtegoryMaster : {}", id);
        CtegoryMaster ctegoryMaster = ctegoryMasterRepository.findOne(id);
        return Optional.ofNullable(ctegoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ctegoryMasters/:id -> delete the "id" ctegoryMaster.
     */
    @RequestMapping(value = "/ctegoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCtegoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete CtegoryMaster : {}", id);
        ctegoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ctegoryMaster", id.toString())).build();
    }
}

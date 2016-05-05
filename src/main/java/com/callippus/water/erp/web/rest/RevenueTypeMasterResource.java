package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.RevenueTypeMaster;
import com.callippus.water.erp.repository.RevenueTypeMasterRepository;
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
 * REST controller for managing RevenueTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class RevenueTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(RevenueTypeMasterResource.class);
        
    @Inject
    private RevenueTypeMasterRepository revenueTypeMasterRepository;
    
    /**
     * POST  /revenueTypeMasters -> Create a new revenueTypeMaster.
     */
    @RequestMapping(value = "/revenueTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RevenueTypeMaster> createRevenueTypeMaster(@Valid @RequestBody RevenueTypeMaster revenueTypeMaster) throws URISyntaxException {
        log.debug("REST request to save RevenueTypeMaster : {}", revenueTypeMaster);
        if (revenueTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("revenueTypeMaster", "idexists", "A new revenueTypeMaster cannot already have an ID")).body(null);
        }
        RevenueTypeMaster result = revenueTypeMasterRepository.save(revenueTypeMaster);
        return ResponseEntity.created(new URI("/api/revenueTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("revenueTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /revenueTypeMasters -> Updates an existing revenueTypeMaster.
     */
    @RequestMapping(value = "/revenueTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RevenueTypeMaster> updateRevenueTypeMaster(@Valid @RequestBody RevenueTypeMaster revenueTypeMaster) throws URISyntaxException {
        log.debug("REST request to update RevenueTypeMaster : {}", revenueTypeMaster);
        if (revenueTypeMaster.getId() == null) {
            return createRevenueTypeMaster(revenueTypeMaster);
        }
        RevenueTypeMaster result = revenueTypeMasterRepository.save(revenueTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("revenueTypeMaster", revenueTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /revenueTypeMasters -> get all the revenueTypeMasters.
     */
    @RequestMapping(value = "/revenueTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RevenueTypeMaster>> getAllRevenueTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RevenueTypeMasters");
        Page<RevenueTypeMaster> page = revenueTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/revenueTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /revenueTypeMasters/:id -> get the "id" revenueTypeMaster.
     */
    @RequestMapping(value = "/revenueTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RevenueTypeMaster> getRevenueTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get RevenueTypeMaster : {}", id);
        RevenueTypeMaster revenueTypeMaster = revenueTypeMasterRepository.findOne(id);
        return Optional.ofNullable(revenueTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /revenueTypeMasters/:id -> delete the "id" revenueTypeMaster.
     */
    @RequestMapping(value = "/revenueTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRevenueTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete RevenueTypeMaster : {}", id);
        revenueTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("revenueTypeMaster", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.PercentageMaster;
import com.callippus.water.erp.repository.PercentageMasterRepository;
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
 * REST controller for managing PercentageMaster.
 */
@RestController
@RequestMapping("/api")
public class PercentageMasterResource {

    private final Logger log = LoggerFactory.getLogger(PercentageMasterResource.class);
        
    @Inject
    private PercentageMasterRepository percentageMasterRepository;
    
    /**
     * POST  /percentageMasters -> Create a new percentageMaster.
     */
    @RequestMapping(value = "/percentageMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PercentageMaster> createPercentageMaster(@RequestBody PercentageMaster percentageMaster) throws URISyntaxException {
        log.debug("REST request to save PercentageMaster : {}", percentageMaster);
        if (percentageMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("percentageMaster", "idexists", "A new percentageMaster cannot already have an ID")).body(null);
        }
        PercentageMaster result = percentageMasterRepository.save(percentageMaster);
        return ResponseEntity.created(new URI("/api/percentageMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("percentageMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /percentageMasters -> Updates an existing percentageMaster.
     */
    @RequestMapping(value = "/percentageMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PercentageMaster> updatePercentageMaster(@RequestBody PercentageMaster percentageMaster) throws URISyntaxException {
        log.debug("REST request to update PercentageMaster : {}", percentageMaster);
        if (percentageMaster.getId() == null) {
            return createPercentageMaster(percentageMaster);
        }
        PercentageMaster result = percentageMasterRepository.save(percentageMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("percentageMaster", percentageMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /percentageMasters -> get all the percentageMasters.
     */
    @RequestMapping(value = "/percentageMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PercentageMaster>> getAllPercentageMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PercentageMasters");
        Page<PercentageMaster> page = percentageMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/percentageMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /percentageMasters/:id -> get the "id" percentageMaster.
     */
    @RequestMapping(value = "/percentageMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PercentageMaster> getPercentageMaster(@PathVariable Long id) {
        log.debug("REST request to get PercentageMaster : {}", id);
        PercentageMaster percentageMaster = percentageMasterRepository.findOne(id);
        return Optional.ofNullable(percentageMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /percentageMasters/:id -> delete the "id" percentageMaster.
     */
    @RequestMapping(value = "/percentageMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePercentageMaster(@PathVariable Long id) {
        log.debug("REST request to delete PercentageMaster : {}", id);
        percentageMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("percentageMaster", id.toString())).build();
    }
}

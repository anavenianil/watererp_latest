package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ApplicationTypeMaster;
import com.callippus.water.erp.repository.ApplicationTypeMasterRepository;
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
 * REST controller for managing ApplicationTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class ApplicationTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationTypeMasterResource.class);
        
    @Inject
    private ApplicationTypeMasterRepository applicationTypeMasterRepository;
    
    /**
     * POST  /applicationTypeMasters -> Create a new applicationTypeMaster.
     */
    @RequestMapping(value = "/applicationTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTypeMaster> createApplicationTypeMaster(@RequestBody ApplicationTypeMaster applicationTypeMaster) throws URISyntaxException {
        log.debug("REST request to save ApplicationTypeMaster : {}", applicationTypeMaster);
        if (applicationTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("applicationTypeMaster", "idexists", "A new applicationTypeMaster cannot already have an ID")).body(null);
        }
        ApplicationTypeMaster result = applicationTypeMasterRepository.save(applicationTypeMaster);
        return ResponseEntity.created(new URI("/api/applicationTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("applicationTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /applicationTypeMasters -> Updates an existing applicationTypeMaster.
     */
    @RequestMapping(value = "/applicationTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTypeMaster> updateApplicationTypeMaster(@RequestBody ApplicationTypeMaster applicationTypeMaster) throws URISyntaxException {
        log.debug("REST request to update ApplicationTypeMaster : {}", applicationTypeMaster);
        if (applicationTypeMaster.getId() == null) {
            return createApplicationTypeMaster(applicationTypeMaster);
        }
        ApplicationTypeMaster result = applicationTypeMasterRepository.save(applicationTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("applicationTypeMaster", applicationTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /applicationTypeMasters -> get all the applicationTypeMasters.
     */
    @RequestMapping(value = "/applicationTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ApplicationTypeMaster>> getAllApplicationTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ApplicationTypeMasters");
        Page<ApplicationTypeMaster> page = applicationTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/applicationTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /applicationTypeMasters/:id -> get the "id" applicationTypeMaster.
     */
    @RequestMapping(value = "/applicationTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTypeMaster> getApplicationTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get ApplicationTypeMaster : {}", id);
        ApplicationTypeMaster applicationTypeMaster = applicationTypeMasterRepository.findOne(id);
        return Optional.ofNullable(applicationTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /applicationTypeMasters/:id -> delete the "id" applicationTypeMaster.
     */
    @RequestMapping(value = "/applicationTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteApplicationTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationTypeMaster : {}", id);
        applicationTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("applicationTypeMaster", id.toString())).build();
    }
}

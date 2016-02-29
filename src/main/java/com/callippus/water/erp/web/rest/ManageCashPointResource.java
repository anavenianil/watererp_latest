package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ManageCashPoint;
import com.callippus.water.erp.repository.ManageCashPointRepository;
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
 * REST controller for managing ManageCashPoint.
 */
@RestController
@RequestMapping("/api")
public class ManageCashPointResource {

    private final Logger log = LoggerFactory.getLogger(ManageCashPointResource.class);
        
    @Inject
    private ManageCashPointRepository manageCashPointRepository;
    
    /**
     * POST  /manageCashPoints -> Create a new manageCashPoint.
     */
    @RequestMapping(value = "/manageCashPoints",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ManageCashPoint> createManageCashPoint(@RequestBody ManageCashPoint manageCashPoint) throws URISyntaxException {
        log.debug("REST request to save ManageCashPoint : {}", manageCashPoint);
        if (manageCashPoint.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("manageCashPoint", "idexists", "A new manageCashPoint cannot already have an ID")).body(null);
        }
        ManageCashPoint result = manageCashPointRepository.save(manageCashPoint);
        return ResponseEntity.created(new URI("/api/manageCashPoints/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("manageCashPoint", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /manageCashPoints -> Updates an existing manageCashPoint.
     */
    @RequestMapping(value = "/manageCashPoints",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ManageCashPoint> updateManageCashPoint(@RequestBody ManageCashPoint manageCashPoint) throws URISyntaxException {
        log.debug("REST request to update ManageCashPoint : {}", manageCashPoint);
        if (manageCashPoint.getId() == null) {
            return createManageCashPoint(manageCashPoint);
        }
        ManageCashPoint result = manageCashPointRepository.save(manageCashPoint);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("manageCashPoint", manageCashPoint.getId().toString()))
            .body(result);
    }

    /**
     * GET  /manageCashPoints -> get all the manageCashPoints.
     */
    @RequestMapping(value = "/manageCashPoints",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ManageCashPoint>> getAllManageCashPoints(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ManageCashPoints");
        Page<ManageCashPoint> page = manageCashPointRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/manageCashPoints");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /manageCashPoints/:id -> get the "id" manageCashPoint.
     */
    @RequestMapping(value = "/manageCashPoints/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ManageCashPoint> getManageCashPoint(@PathVariable Long id) {
        log.debug("REST request to get ManageCashPoint : {}", id);
        ManageCashPoint manageCashPoint = manageCashPointRepository.findOne(id);
        return Optional.ofNullable(manageCashPoint)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /manageCashPoints/:id -> delete the "id" manageCashPoint.
     */
    @RequestMapping(value = "/manageCashPoints/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteManageCashPoint(@PathVariable Long id) {
        log.debug("REST request to delete ManageCashPoint : {}", id);
        manageCashPointRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("manageCashPoint", id.toString())).build();
    }
}

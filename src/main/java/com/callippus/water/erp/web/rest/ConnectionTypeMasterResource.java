package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ConnectionTypeMaster;
import com.callippus.water.erp.repository.ConnectionTypeMasterRepository;
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
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

/**
 * REST controller for managing ConnectionTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class ConnectionTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ConnectionTypeMasterResource.class);
        
    @Inject
    private ConnectionTypeMasterRepository connectionTypeMasterRepository;
    /**
     * POST  /connectionTypeMasters -> Create a new connectionTypeMaster.
     */
    @RequestMapping(value = "/connectionTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ConnectionTypeMaster> createConnectionTypeMaster(@RequestBody ConnectionTypeMaster connectionTypeMaster) throws URISyntaxException {
        log.debug("REST request to save ConnectionTypeMaster : {}", connectionTypeMaster);
        if (connectionTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("connectionTypeMaster", "idexists", "A new connectionTypeMaster cannot already have an ID")).body(null);
        }
        ConnectionTypeMaster result = connectionTypeMasterRepository.save(connectionTypeMaster);
        return ResponseEntity.created(new URI("/api/connectionTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("connectionTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /connectionTypeMasters -> Updates an existing connectionTypeMaster.
     */
    @RequestMapping(value = "/connectionTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ConnectionTypeMaster> updateConnectionTypeMaster(@RequestBody ConnectionTypeMaster connectionTypeMaster) throws URISyntaxException {
        log.debug("REST request to update ConnectionTypeMaster : {}", connectionTypeMaster);
        if (connectionTypeMaster.getId() == null) {
            return createConnectionTypeMaster(connectionTypeMaster);
        }
        ConnectionTypeMaster result = connectionTypeMasterRepository.save(connectionTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("connectionTypeMaster", connectionTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /connectionTypeMasters -> get all the connectionTypeMasters.
     */
    @RequestMapping(value = "/connectionTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ConnectionTypeMaster>> getAllConnectionTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ConnectionTypeMasters");
        Page<ConnectionTypeMaster> page = connectionTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/connectionTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /connectionTypeMasters/:id -> get the "id" connectionTypeMaster.
     */
    @RequestMapping(value = "/connectionTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ConnectionTypeMaster> getConnectionTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get ConnectionTypeMaster : {}", id);
        ConnectionTypeMaster connectionTypeMaster = connectionTypeMasterRepository.findOne(id);
        return Optional.ofNullable(connectionTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /connectionTypeMasters/:id -> delete the "id" connectionTypeMaster.
     */
    @RequestMapping(value = "/connectionTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteConnectionTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ConnectionTypeMaster : {}", id);
        connectionTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("connectionTypeMaster", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DivisionMaster;
import com.callippus.water.erp.repository.DivisionMasterRepository;
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
 * REST controller for managing DivisionMaster.
 */
@RestController
@RequestMapping("/api")
public class DivisionMasterResource {

    private final Logger log = LoggerFactory.getLogger(DivisionMasterResource.class);
        
    @Inject
    private DivisionMasterRepository divisionMasterRepository;
    
    /**
     * POST  /divisionMasters -> Create a new divisionMaster.
     */
    @RequestMapping(value = "/divisionMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DivisionMaster> createDivisionMaster(@RequestBody DivisionMaster divisionMaster) throws URISyntaxException {
        log.debug("REST request to save DivisionMaster : {}", divisionMaster);
        if (divisionMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("divisionMaster", "idexists", "A new divisionMaster cannot already have an ID")).body(null);
        }
        DivisionMaster result = divisionMasterRepository.save(divisionMaster);
        return ResponseEntity.created(new URI("/api/divisionMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("divisionMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /divisionMasters -> Updates an existing divisionMaster.
     */
    @RequestMapping(value = "/divisionMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DivisionMaster> updateDivisionMaster(@RequestBody DivisionMaster divisionMaster) throws URISyntaxException {
        log.debug("REST request to update DivisionMaster : {}", divisionMaster);
        if (divisionMaster.getId() == null) {
            return createDivisionMaster(divisionMaster);
        }
        DivisionMaster result = divisionMasterRepository.save(divisionMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("divisionMaster", divisionMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /divisionMasters -> get all the divisionMasters.
     */
    @RequestMapping(value = "/divisionMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DivisionMaster>> getAllDivisionMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DivisionMasters");
        Page<DivisionMaster> page = divisionMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/divisionMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /divisionMasters/:id -> get the "id" divisionMaster.
     */
    @RequestMapping(value = "/divisionMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DivisionMaster> getDivisionMaster(@PathVariable Long id) {
        log.debug("REST request to get DivisionMaster : {}", id);
        DivisionMaster divisionMaster = divisionMasterRepository.findOne(id);
        return Optional.ofNullable(divisionMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /divisionMasters/:id -> delete the "id" divisionMaster.
     */
    @RequestMapping(value = "/divisionMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDivisionMaster(@PathVariable Long id) {
        log.debug("REST request to delete DivisionMaster : {}", id);
        divisionMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("divisionMaster", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DivisionMaster;
import com.callippus.water.erp.domain.StreetMaster;
import com.callippus.water.erp.domain.ZoneMaster;
import com.callippus.water.erp.repository.StreetMasterRepository;
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
 * REST controller for managing StreetMaster.
 */
@RestController
@RequestMapping("/api")
public class StreetMasterResource {

    private final Logger log = LoggerFactory.getLogger(StreetMasterResource.class);
        
    @Inject
    private StreetMasterRepository streetMasterRepository;
    
    /**
     * POST  /streetMasters -> Create a new streetMaster.
     */
    @RequestMapping(value = "/streetMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StreetMaster> createStreetMaster(@RequestBody StreetMaster streetMaster) throws URISyntaxException {
        log.debug("REST request to save StreetMaster : {}", streetMaster);
        if (streetMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("streetMaster", "idexists", "A new streetMaster cannot already have an ID")).body(null);
        }
        StreetMaster result = streetMasterRepository.save(streetMaster);
        return ResponseEntity.created(new URI("/api/streetMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("streetMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /streetMasters -> Updates an existing streetMaster.
     */
    @RequestMapping(value = "/streetMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StreetMaster> updateStreetMaster(@RequestBody StreetMaster streetMaster) throws URISyntaxException {
        log.debug("REST request to update StreetMaster : {}", streetMaster);
        if (streetMaster.getId() == null) {
            return createStreetMaster(streetMaster);
        }
        StreetMaster result = streetMasterRepository.save(streetMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("streetMaster", streetMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /streetMasters -> get all the streetMasters.
     */
    @RequestMapping(value = "/streetMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<StreetMaster>> getAllStreetMasters(Pageable pageable,
    		@RequestParam(value = "divisionId", required = false) DivisionMaster divisionId)
        throws URISyntaxException {
        log.debug("REST request to get a page of StreetMasters");
        //Page<StreetMaster> page = streetMasterRepository.findAll(pageable);
        Page<StreetMaster> page;
        if(divisionId == null){
        	page = streetMasterRepository.findAll(pageable);
        }
        else
        {
        	page = streetMasterRepository.findByDivisionMaster(pageable, divisionId);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/streetMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /streetMasters/:id -> get the "id" streetMaster.
     */
    @RequestMapping(value = "/streetMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StreetMaster> getStreetMaster(@PathVariable Long id) {
        log.debug("REST request to get StreetMaster : {}", id);
        StreetMaster streetMaster = streetMasterRepository.findOne(id);
        return Optional.ofNullable(streetMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /streetMasters/:id -> delete the "id" streetMaster.
     */
    @RequestMapping(value = "/streetMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteStreetMaster(@PathVariable Long id) {
        log.debug("REST request to delete StreetMaster : {}", id);
        streetMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("streetMaster", id.toString())).build();
    }
}

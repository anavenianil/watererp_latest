package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.StatusMaster;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing StatusMaster.
 */
@RestController
@RequestMapping("/api")
public class StatusMasterResource {

    private final Logger log = LoggerFactory.getLogger(StatusMasterResource.class);
        
    @Inject
    private StatusMasterRepository statusMasterRepository;
    
    /**
     * POST  /statusMasters -> Create a new statusMaster.
     */
    @RequestMapping(value = "/statusMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StatusMaster> createStatusMaster(@RequestBody StatusMaster statusMaster) throws URISyntaxException {
        log.debug("REST request to save StatusMaster : {}", statusMaster);
        if (statusMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("statusMaster", "idexists", "A new statusMaster cannot already have an ID")).body(null);
        }
        StatusMaster result = statusMasterRepository.save(statusMaster);
        return ResponseEntity.created(new URI("/api/statusMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("statusMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /statusMasters -> Updates an existing statusMaster.
     */
    @RequestMapping(value = "/statusMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StatusMaster> updateStatusMaster(@RequestBody StatusMaster statusMaster) throws URISyntaxException {
        log.debug("REST request to update StatusMaster : {}", statusMaster);
        if (statusMaster.getId() == null) {
            return createStatusMaster(statusMaster);
        }
        StatusMaster result = statusMasterRepository.save(statusMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("statusMaster", statusMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /statusMasters -> get all the statusMasters.
     */
    @RequestMapping(value = "/statusMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<StatusMaster>> getAllStatusMasters(Pageable pageable,
    		@RequestParam(value = "description1", required = false) String description1,
    		@RequestParam(value = "description2", required = false) String description2)
        throws URISyntaxException {
        log.debug("REST request to get a page of StatusMasters");
        //Page<StatusMaster> page = statusMasterRepository.findAll(pageable);
        Page<StatusMaster> page;
        List<String> descriptions = new ArrayList();
        if(description1 !=null ||description2 != null){
        	if(description1 !=null){
        		descriptions.add(description1);
        		if(description2 !=null){
        			descriptions.add(description2);
        		}
        	}
        	page = statusMasterRepository.findByDescriptionIn(pageable, descriptions);
        }else{
        	page = statusMasterRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/statusMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /statusMasters/:id -> get the "id" statusMaster.
     */
    @RequestMapping(value = "/statusMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StatusMaster> getStatusMaster(@PathVariable Long id) {
        log.debug("REST request to get StatusMaster : {}", id);
        StatusMaster statusMaster = statusMasterRepository.findOne(id);
        return Optional.ofNullable(statusMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /statusMasters/:id -> delete the "id" statusMaster.
     */
    @RequestMapping(value = "/statusMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteStatusMaster(@PathVariable Long id) {
        log.debug("REST request to delete StatusMaster : {}", id);
        statusMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("statusMaster", id.toString())).build();
    }
}

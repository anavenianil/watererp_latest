package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.callippus.water.erp.domain.DivisionMaster;
import com.callippus.water.erp.domain.ZoneMaster;
import com.callippus.water.erp.repository.ZoneMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing ZoneMaster.
 */
@RestController
@RequestMapping("/api")
public class ZoneMasterResource {

    private final Logger log = LoggerFactory.getLogger(ZoneMasterResource.class);
        
    @Inject
    private ZoneMasterRepository zoneMasterRepository;
    
    /**
     * POST  /zoneMasters -> Create a new zoneMaster.
     */
    @RequestMapping(value = "/zoneMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ZoneMaster> createZoneMaster(@RequestBody ZoneMaster zoneMaster) throws URISyntaxException {
        log.debug("REST request to save ZoneMaster : {}", zoneMaster);
        if (zoneMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("zoneMaster", "idexists", "A new zoneMaster cannot already have an ID")).body(null);
        }
        ZoneMaster result = zoneMasterRepository.save(zoneMaster);
        return ResponseEntity.created(new URI("/api/zoneMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("zoneMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /zoneMasters -> Updates an existing zoneMaster.
     */
    @RequestMapping(value = "/zoneMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ZoneMaster> updateZoneMaster(@RequestBody ZoneMaster zoneMaster) throws URISyntaxException {
        log.debug("REST request to update ZoneMaster : {}", zoneMaster);
        if (zoneMaster.getId() == null) {
            return createZoneMaster(zoneMaster);
        }
        ZoneMaster result = zoneMasterRepository.save(zoneMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("zoneMaster", zoneMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /zoneMasters -> get all the zoneMasters.
     */
    @RequestMapping(value = "/zoneMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ZoneMaster>> getAllZoneMasters(Pageable pageable,
    		@RequestParam(value = "divisionId", required = false) DivisionMaster divisionId)
        throws URISyntaxException {
        log.debug("REST request to get a page of ZoneMasters");
        //Page<ZoneMaster> page = zoneMasterRepository.findAll(pageable); 
        Page<ZoneMaster> page;
        if(divisionId == null){
        	page = zoneMasterRepository.findAll(pageable);
        }
        else
        {
        	page = zoneMasterRepository.findByDivisionMaster(pageable, divisionId);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/zoneMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /zoneMasters/:id -> get the "id" zoneMaster.
     */
    @RequestMapping(value = "/zoneMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ZoneMaster> getZoneMaster(@PathVariable Long id) {
        log.debug("REST request to get ZoneMaster : {}", id);
        ZoneMaster zoneMaster = zoneMasterRepository.findOne(id);
        return Optional.ofNullable(zoneMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /zoneMasters/:id -> delete the "id" zoneMaster.
     */
    @RequestMapping(value = "/zoneMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteZoneMaster(@PathVariable Long id) {
        log.debug("REST request to delete ZoneMaster : {}", id);
        zoneMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("zoneMaster", id.toString())).build();
    }
}

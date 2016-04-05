package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CustMeterMapping;
import com.callippus.water.erp.repository.CustMeterMappingRepository;
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
 * REST controller for managing CustMeterMapping.
 */
@RestController
@RequestMapping("/api")
public class CustMeterMappingResource {

    private final Logger log = LoggerFactory.getLogger(CustMeterMappingResource.class);
        
    @Inject
    private CustMeterMappingRepository custMeterMappingRepository;
    
    /**
     * POST  /custMeterMappings -> Create a new custMeterMapping.
     */
    @RequestMapping(value = "/custMeterMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustMeterMapping> createCustMeterMapping(@Valid @RequestBody CustMeterMapping custMeterMapping) throws URISyntaxException {
        log.debug("REST request to save CustMeterMapping : {}", custMeterMapping);
        if (custMeterMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("custMeterMapping", "idexists", "A new custMeterMapping cannot already have an ID")).body(null);
        }
        CustMeterMapping result = custMeterMappingRepository.save(custMeterMapping);
        return ResponseEntity.created(new URI("/api/custMeterMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("custMeterMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /custMeterMappings -> Updates an existing custMeterMapping.
     */
    @RequestMapping(value = "/custMeterMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustMeterMapping> updateCustMeterMapping(@Valid @RequestBody CustMeterMapping custMeterMapping) throws URISyntaxException {
        log.debug("REST request to update CustMeterMapping : {}", custMeterMapping);
        if (custMeterMapping.getId() == null) {
            return createCustMeterMapping(custMeterMapping);
        }
        CustMeterMapping result = custMeterMappingRepository.save(custMeterMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("custMeterMapping", custMeterMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /custMeterMappings -> get all the custMeterMappings.
     */
    @RequestMapping(value = "/custMeterMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CustMeterMapping>> getAllCustMeterMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CustMeterMappings");
        Page<CustMeterMapping> page = custMeterMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/custMeterMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /custMeterMappings/:id -> get the "id" custMeterMapping.
     */
    @RequestMapping(value = "/custMeterMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustMeterMapping> getCustMeterMapping(@PathVariable Long id) {
        log.debug("REST request to get CustMeterMapping : {}", id);
        CustMeterMapping custMeterMapping = custMeterMappingRepository.findOne(id);
        return Optional.ofNullable(custMeterMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /custMeterMappings/:id -> delete the "id" custMeterMapping.
     */
    @RequestMapping(value = "/custMeterMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCustMeterMapping(@PathVariable Long id) {
        log.debug("REST request to delete CustMeterMapping : {}", id);
        custMeterMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("custMeterMapping", id.toString())).build();
    }
}

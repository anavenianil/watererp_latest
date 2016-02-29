package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.SubDesigCategoryMaster;
import com.callippus.water.erp.repository.SubDesigCategoryMasterRepository;
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
 * REST controller for managing SubDesigCategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class SubDesigCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(SubDesigCategoryMasterResource.class);
        
    @Inject
    private SubDesigCategoryMasterRepository subDesigCategoryMasterRepository;
    
    /**
     * POST  /subDesigCategoryMasters -> Create a new subDesigCategoryMaster.
     */
    @RequestMapping(value = "/subDesigCategoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubDesigCategoryMaster> createSubDesigCategoryMaster(@RequestBody SubDesigCategoryMaster subDesigCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save SubDesigCategoryMaster : {}", subDesigCategoryMaster);
        if (subDesigCategoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("subDesigCategoryMaster", "idexists", "A new subDesigCategoryMaster cannot already have an ID")).body(null);
        }
        SubDesigCategoryMaster result = subDesigCategoryMasterRepository.save(subDesigCategoryMaster);
        return ResponseEntity.created(new URI("/api/subDesigCategoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("subDesigCategoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /subDesigCategoryMasters -> Updates an existing subDesigCategoryMaster.
     */
    @RequestMapping(value = "/subDesigCategoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubDesigCategoryMaster> updateSubDesigCategoryMaster(@RequestBody SubDesigCategoryMaster subDesigCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update SubDesigCategoryMaster : {}", subDesigCategoryMaster);
        if (subDesigCategoryMaster.getId() == null) {
            return createSubDesigCategoryMaster(subDesigCategoryMaster);
        }
        SubDesigCategoryMaster result = subDesigCategoryMasterRepository.save(subDesigCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("subDesigCategoryMaster", subDesigCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /subDesigCategoryMasters -> get all the subDesigCategoryMasters.
     */
    @RequestMapping(value = "/subDesigCategoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SubDesigCategoryMaster>> getAllSubDesigCategoryMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SubDesigCategoryMasters");
        Page<SubDesigCategoryMaster> page = subDesigCategoryMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/subDesigCategoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /subDesigCategoryMasters/:id -> get the "id" subDesigCategoryMaster.
     */
    @RequestMapping(value = "/subDesigCategoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubDesigCategoryMaster> getSubDesigCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get SubDesigCategoryMaster : {}", id);
        SubDesigCategoryMaster subDesigCategoryMaster = subDesigCategoryMasterRepository.findOne(id);
        return Optional.ofNullable(subDesigCategoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /subDesigCategoryMasters/:id -> delete the "id" subDesigCategoryMaster.
     */
    @RequestMapping(value = "/subDesigCategoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSubDesigCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete SubDesigCategoryMaster : {}", id);
        subDesigCategoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("subDesigCategoryMaster", id.toString())).build();
    }
}

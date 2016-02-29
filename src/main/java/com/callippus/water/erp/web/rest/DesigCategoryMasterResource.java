package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DesigCategoryMaster;
import com.callippus.water.erp.repository.DesigCategoryMasterRepository;
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
 * REST controller for managing DesigCategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class DesigCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(DesigCategoryMasterResource.class);
        
    @Inject
    private DesigCategoryMasterRepository desigCategoryMasterRepository;
    
    /**
     * POST  /desigCategoryMasters -> Create a new desigCategoryMaster.
     */
    @RequestMapping(value = "/desigCategoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesigCategoryMaster> createDesigCategoryMaster(@RequestBody DesigCategoryMaster desigCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save DesigCategoryMaster : {}", desigCategoryMaster);
        if (desigCategoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("desigCategoryMaster", "idexists", "A new desigCategoryMaster cannot already have an ID")).body(null);
        }
        DesigCategoryMaster result = desigCategoryMasterRepository.save(desigCategoryMaster);
        return ResponseEntity.created(new URI("/api/desigCategoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("desigCategoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /desigCategoryMasters -> Updates an existing desigCategoryMaster.
     */
    @RequestMapping(value = "/desigCategoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesigCategoryMaster> updateDesigCategoryMaster(@RequestBody DesigCategoryMaster desigCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update DesigCategoryMaster : {}", desigCategoryMaster);
        if (desigCategoryMaster.getId() == null) {
            return createDesigCategoryMaster(desigCategoryMaster);
        }
        DesigCategoryMaster result = desigCategoryMasterRepository.save(desigCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("desigCategoryMaster", desigCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /desigCategoryMasters -> get all the desigCategoryMasters.
     */
    @RequestMapping(value = "/desigCategoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DesigCategoryMaster>> getAllDesigCategoryMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DesigCategoryMasters");
        Page<DesigCategoryMaster> page = desigCategoryMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/desigCategoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /desigCategoryMasters/:id -> get the "id" desigCategoryMaster.
     */
    @RequestMapping(value = "/desigCategoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesigCategoryMaster> getDesigCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get DesigCategoryMaster : {}", id);
        DesigCategoryMaster desigCategoryMaster = desigCategoryMasterRepository.findOne(id);
        return Optional.ofNullable(desigCategoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /desigCategoryMasters/:id -> delete the "id" desigCategoryMaster.
     */
    @RequestMapping(value = "/desigCategoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDesigCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete DesigCategoryMaster : {}", id);
        desigCategoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("desigCategoryMaster", id.toString())).build();
    }
}

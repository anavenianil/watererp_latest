package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MaterialMaster;
import com.callippus.water.erp.repository.MaterialMasterRepository;
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
 * REST controller for managing MaterialMaster.
 */
@RestController
@RequestMapping("/api")
public class MaterialMasterResource {

    private final Logger log = LoggerFactory.getLogger(MaterialMasterResource.class);
        
    @Inject
    private MaterialMasterRepository materialMasterRepository;
    
    /**
     * POST  /materialMasters -> Create a new materialMaster.
     */
    @RequestMapping(value = "/materialMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MaterialMaster> createMaterialMaster(@RequestBody MaterialMaster materialMaster) throws URISyntaxException {
        log.debug("REST request to save MaterialMaster : {}", materialMaster);
        if (materialMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("materialMaster", "idexists", "A new materialMaster cannot already have an ID")).body(null);
        }
        MaterialMaster result = materialMasterRepository.save(materialMaster);
        return ResponseEntity.created(new URI("/api/materialMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("materialMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /materialMasters -> Updates an existing materialMaster.
     */
    @RequestMapping(value = "/materialMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MaterialMaster> updateMaterialMaster(@RequestBody MaterialMaster materialMaster) throws URISyntaxException {
        log.debug("REST request to update MaterialMaster : {}", materialMaster);
        if (materialMaster.getId() == null) {
            return createMaterialMaster(materialMaster);
        }
        MaterialMaster result = materialMasterRepository.save(materialMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("materialMaster", materialMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /materialMasters -> get all the materialMasters.
     */
    @RequestMapping(value = "/materialMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MaterialMaster>> getAllMaterialMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MaterialMasters");
        Page<MaterialMaster> page = materialMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/materialMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /materialMasters/:id -> get the "id" materialMaster.
     */
    @RequestMapping(value = "/materialMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MaterialMaster> getMaterialMaster(@PathVariable Long id) {
        log.debug("REST request to get MaterialMaster : {}", id);
        MaterialMaster materialMaster = materialMasterRepository.findOne(id);
        return Optional.ofNullable(materialMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /materialMasters/:id -> delete the "id" materialMaster.
     */
    @RequestMapping(value = "/materialMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMaterialMaster(@PathVariable Long id) {
        log.debug("REST request to delete MaterialMaster : {}", id);
        materialMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("materialMaster", id.toString())).build();
    }
}

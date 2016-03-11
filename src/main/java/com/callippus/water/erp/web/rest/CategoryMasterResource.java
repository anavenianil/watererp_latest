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
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.CategoryMaster;
import com.callippus.water.erp.repository.CategoryMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing CategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class CategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(CategoryMasterResource.class);
        
    @Inject
    private CategoryMasterRepository categoryMasterRepository;
    
    /**
     * POST  /categoryMasters -> Create a new categoryMaster.
     */
    @RequestMapping(value = "/categoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoryMaster> createCategoryMaster(@RequestBody CategoryMaster categoryMaster) throws URISyntaxException {
        log.debug("REST request to save CategoryMaster : {}", categoryMaster);
        if (categoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("categoryMaster", "idexists", "A new categoryMaster cannot already have an ID")).body(null);
        }
        CategoryMaster result = categoryMasterRepository.save(categoryMaster);
        return ResponseEntity.created(new URI("/api/categoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("categoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categoryMasters -> Updates an existing categoryMaster.
     */
    @RequestMapping(value = "/categoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoryMaster> updateCategoryMaster(@RequestBody CategoryMaster categoryMaster) throws URISyntaxException {
        log.debug("REST request to update CategoryMaster : {}", categoryMaster);
        if (categoryMaster.getId() == null) {
            return createCategoryMaster(categoryMaster);
        }
        CategoryMaster result = categoryMasterRepository.save(categoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("categoryMaster", categoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categoryMasters -> get all the categoryMasters.
     */
    @RequestMapping(value = "/categoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CategoryMaster>> getAllCategoryMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CategoryMasters");
        Page<CategoryMaster> page = categoryMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/categoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /categoryMasters/:id -> get the "id" categoryMaster.
     */
    @RequestMapping(value = "/categoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoryMaster> getCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get CategoryMaster : {}", id);
        CategoryMaster categoryMaster = categoryMasterRepository.findOne(id);
        return Optional.ofNullable(categoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /categoryMasters/:id -> delete the "id" categoryMaster.
     */
    @RequestMapping(value = "/categoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete CategoryMaster : {}", id);
        categoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("categoryMaster", id.toString())).build();
    }
}

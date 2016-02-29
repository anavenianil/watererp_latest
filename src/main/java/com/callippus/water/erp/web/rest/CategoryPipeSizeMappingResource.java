package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CategoryPipeSizeMapping;
import com.callippus.water.erp.repository.CategoryPipeSizeMappingRepository;
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
 * REST controller for managing CategoryPipeSizeMapping.
 */
@RestController
@RequestMapping("/api")
public class CategoryPipeSizeMappingResource {

    private final Logger log = LoggerFactory.getLogger(CategoryPipeSizeMappingResource.class);
        
    @Inject
    private CategoryPipeSizeMappingRepository categoryPipeSizeMappingRepository;
    
    /**
     * POST  /categoryPipeSizeMappings -> Create a new categoryPipeSizeMapping.
     */
    @RequestMapping(value = "/categoryPipeSizeMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoryPipeSizeMapping> createCategoryPipeSizeMapping(@RequestBody CategoryPipeSizeMapping categoryPipeSizeMapping) throws URISyntaxException {
        log.debug("REST request to save CategoryPipeSizeMapping : {}", categoryPipeSizeMapping);
        if (categoryPipeSizeMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("categoryPipeSizeMapping", "idexists", "A new categoryPipeSizeMapping cannot already have an ID")).body(null);
        }
        CategoryPipeSizeMapping result = categoryPipeSizeMappingRepository.save(categoryPipeSizeMapping);
        return ResponseEntity.created(new URI("/api/categoryPipeSizeMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("categoryPipeSizeMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /categoryPipeSizeMappings -> Updates an existing categoryPipeSizeMapping.
     */
    @RequestMapping(value = "/categoryPipeSizeMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoryPipeSizeMapping> updateCategoryPipeSizeMapping(@RequestBody CategoryPipeSizeMapping categoryPipeSizeMapping) throws URISyntaxException {
        log.debug("REST request to update CategoryPipeSizeMapping : {}", categoryPipeSizeMapping);
        if (categoryPipeSizeMapping.getId() == null) {
            return createCategoryPipeSizeMapping(categoryPipeSizeMapping);
        }
        CategoryPipeSizeMapping result = categoryPipeSizeMappingRepository.save(categoryPipeSizeMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("categoryPipeSizeMapping", categoryPipeSizeMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /categoryPipeSizeMappings -> get all the categoryPipeSizeMappings.
     */
    @RequestMapping(value = "/categoryPipeSizeMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CategoryPipeSizeMapping>> getAllCategoryPipeSizeMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CategoryPipeSizeMappings");
        Page<CategoryPipeSizeMapping> page = categoryPipeSizeMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/categoryPipeSizeMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /categoryPipeSizeMappings/:id -> get the "id" categoryPipeSizeMapping.
     */
    @RequestMapping(value = "/categoryPipeSizeMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CategoryPipeSizeMapping> getCategoryPipeSizeMapping(@PathVariable Long id) {
        log.debug("REST request to get CategoryPipeSizeMapping : {}", id);
        CategoryPipeSizeMapping categoryPipeSizeMapping = categoryPipeSizeMappingRepository.findOne(id);
        return Optional.ofNullable(categoryPipeSizeMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /categoryPipeSizeMappings/:id -> delete the "id" categoryPipeSizeMapping.
     */
    @RequestMapping(value = "/categoryPipeSizeMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCategoryPipeSizeMapping(@PathVariable Long id) {
        log.debug("REST request to delete CategoryPipeSizeMapping : {}", id);
        categoryPipeSizeMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("categoryPipeSizeMapping", id.toString())).build();
    }
}

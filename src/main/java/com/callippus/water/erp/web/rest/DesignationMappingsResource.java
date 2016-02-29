package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DesignationMappings;
import com.callippus.water.erp.repository.DesignationMappingsRepository;
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
 * REST controller for managing DesignationMappings.
 */
@RestController
@RequestMapping("/api")
public class DesignationMappingsResource {

    private final Logger log = LoggerFactory.getLogger(DesignationMappingsResource.class);
        
    @Inject
    private DesignationMappingsRepository designationMappingsRepository;
    
    /**
     * POST  /designationMappingss -> Create a new designationMappings.
     */
    @RequestMapping(value = "/designationMappingss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesignationMappings> createDesignationMappings(@RequestBody DesignationMappings designationMappings) throws URISyntaxException {
        log.debug("REST request to save DesignationMappings : {}", designationMappings);
        if (designationMappings.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("designationMappings", "idexists", "A new designationMappings cannot already have an ID")).body(null);
        }
        DesignationMappings result = designationMappingsRepository.save(designationMappings);
        return ResponseEntity.created(new URI("/api/designationMappingss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("designationMappings", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /designationMappingss -> Updates an existing designationMappings.
     */
    @RequestMapping(value = "/designationMappingss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesignationMappings> updateDesignationMappings(@RequestBody DesignationMappings designationMappings) throws URISyntaxException {
        log.debug("REST request to update DesignationMappings : {}", designationMappings);
        if (designationMappings.getId() == null) {
            return createDesignationMappings(designationMappings);
        }
        DesignationMappings result = designationMappingsRepository.save(designationMappings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("designationMappings", designationMappings.getId().toString()))
            .body(result);
    }

    /**
     * GET  /designationMappingss -> get all the designationMappingss.
     */
    @RequestMapping(value = "/designationMappingss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DesignationMappings>> getAllDesignationMappingss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DesignationMappingss");
        Page<DesignationMappings> page = designationMappingsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/designationMappingss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /designationMappingss/:id -> get the "id" designationMappings.
     */
    @RequestMapping(value = "/designationMappingss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesignationMappings> getDesignationMappings(@PathVariable Long id) {
        log.debug("REST request to get DesignationMappings : {}", id);
        DesignationMappings designationMappings = designationMappingsRepository.findOne(id);
        return Optional.ofNullable(designationMappings)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /designationMappingss/:id -> delete the "id" designationMappings.
     */
    @RequestMapping(value = "/designationMappingss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDesignationMappings(@PathVariable Long id) {
        log.debug("REST request to delete DesignationMappings : {}", id);
        designationMappingsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("designationMappings", id.toString())).build();
    }
}

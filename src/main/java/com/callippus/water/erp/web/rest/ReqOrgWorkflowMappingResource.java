package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ReqOrgWorkflowMapping;
import com.callippus.water.erp.repository.ReqOrgWorkflowMappingRepository;
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
 * REST controller for managing ReqOrgWorkflowMapping.
 */
@RestController
@RequestMapping("/api")
public class ReqOrgWorkflowMappingResource {

    private final Logger log = LoggerFactory.getLogger(ReqOrgWorkflowMappingResource.class);
        
    @Inject
    private ReqOrgWorkflowMappingRepository reqOrgWorkflowMappingRepository;
    
    /**
     * POST  /reqOrgWorkflowMappings -> Create a new reqOrgWorkflowMapping.
     */
    @RequestMapping(value = "/reqOrgWorkflowMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReqOrgWorkflowMapping> createReqOrgWorkflowMapping(@RequestBody ReqOrgWorkflowMapping reqOrgWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to save ReqOrgWorkflowMapping : {}", reqOrgWorkflowMapping);
        if (reqOrgWorkflowMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("reqOrgWorkflowMapping", "idexists", "A new reqOrgWorkflowMapping cannot already have an ID")).body(null);
        }
        ReqOrgWorkflowMapping result = reqOrgWorkflowMappingRepository.save(reqOrgWorkflowMapping);
        return ResponseEntity.created(new URI("/api/reqOrgWorkflowMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("reqOrgWorkflowMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reqOrgWorkflowMappings -> Updates an existing reqOrgWorkflowMapping.
     */
    @RequestMapping(value = "/reqOrgWorkflowMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReqOrgWorkflowMapping> updateReqOrgWorkflowMapping(@RequestBody ReqOrgWorkflowMapping reqOrgWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to update ReqOrgWorkflowMapping : {}", reqOrgWorkflowMapping);
        if (reqOrgWorkflowMapping.getId() == null) {
            return createReqOrgWorkflowMapping(reqOrgWorkflowMapping);
        }
        ReqOrgWorkflowMapping result = reqOrgWorkflowMappingRepository.save(reqOrgWorkflowMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("reqOrgWorkflowMapping", reqOrgWorkflowMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reqOrgWorkflowMappings -> get all the reqOrgWorkflowMappings.
     */
    @RequestMapping(value = "/reqOrgWorkflowMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ReqOrgWorkflowMapping>> getAllReqOrgWorkflowMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ReqOrgWorkflowMappings");
        Page<ReqOrgWorkflowMapping> page = reqOrgWorkflowMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reqOrgWorkflowMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reqOrgWorkflowMappings/:id -> get the "id" reqOrgWorkflowMapping.
     */
    @RequestMapping(value = "/reqOrgWorkflowMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReqOrgWorkflowMapping> getReqOrgWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to get ReqOrgWorkflowMapping : {}", id);
        ReqOrgWorkflowMapping reqOrgWorkflowMapping = reqOrgWorkflowMappingRepository.findOne(id);
        return Optional.ofNullable(reqOrgWorkflowMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reqOrgWorkflowMappings/:id -> delete the "id" reqOrgWorkflowMapping.
     */
    @RequestMapping(value = "/reqOrgWorkflowMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReqOrgWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to delete ReqOrgWorkflowMapping : {}", id);
        reqOrgWorkflowMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reqOrgWorkflowMapping", id.toString())).build();
    }
}

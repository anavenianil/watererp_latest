package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ReqDesigWorkflowMapping;
import com.callippus.water.erp.repository.ReqDesigWorkflowMappingRepository;
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
 * REST controller for managing ReqDesigWorkflowMapping.
 */
@RestController
@RequestMapping("/api")
public class ReqDesigWorkflowMappingResource {

    private final Logger log = LoggerFactory.getLogger(ReqDesigWorkflowMappingResource.class);
        
    @Inject
    private ReqDesigWorkflowMappingRepository reqDesigWorkflowMappingRepository;
    
    /**
     * POST  /reqDesigWorkflowMappings -> Create a new reqDesigWorkflowMapping.
     */
    @RequestMapping(value = "/reqDesigWorkflowMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReqDesigWorkflowMapping> createReqDesigWorkflowMapping(@RequestBody ReqDesigWorkflowMapping reqDesigWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to save ReqDesigWorkflowMapping : {}", reqDesigWorkflowMapping);
        if (reqDesigWorkflowMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("reqDesigWorkflowMapping", "idexists", "A new reqDesigWorkflowMapping cannot already have an ID")).body(null);
        }
        ReqDesigWorkflowMapping result = reqDesigWorkflowMappingRepository.save(reqDesigWorkflowMapping);
        return ResponseEntity.created(new URI("/api/reqDesigWorkflowMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("reqDesigWorkflowMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /reqDesigWorkflowMappings -> Updates an existing reqDesigWorkflowMapping.
     */
    @RequestMapping(value = "/reqDesigWorkflowMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReqDesigWorkflowMapping> updateReqDesigWorkflowMapping(@RequestBody ReqDesigWorkflowMapping reqDesigWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to update ReqDesigWorkflowMapping : {}", reqDesigWorkflowMapping);
        if (reqDesigWorkflowMapping.getId() == null) {
            return createReqDesigWorkflowMapping(reqDesigWorkflowMapping);
        }
        ReqDesigWorkflowMapping result = reqDesigWorkflowMappingRepository.save(reqDesigWorkflowMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("reqDesigWorkflowMapping", reqDesigWorkflowMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /reqDesigWorkflowMappings -> get all the reqDesigWorkflowMappings.
     */
    @RequestMapping(value = "/reqDesigWorkflowMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ReqDesigWorkflowMapping>> getAllReqDesigWorkflowMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ReqDesigWorkflowMappings");
        Page<ReqDesigWorkflowMapping> page = reqDesigWorkflowMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/reqDesigWorkflowMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /reqDesigWorkflowMappings/:id -> get the "id" reqDesigWorkflowMapping.
     */
    @RequestMapping(value = "/reqDesigWorkflowMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ReqDesigWorkflowMapping> getReqDesigWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to get ReqDesigWorkflowMapping : {}", id);
        ReqDesigWorkflowMapping reqDesigWorkflowMapping = reqDesigWorkflowMappingRepository.findOne(id);
        return Optional.ofNullable(reqDesigWorkflowMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /reqDesigWorkflowMappings/:id -> delete the "id" reqDesigWorkflowMapping.
     */
    @RequestMapping(value = "/reqDesigWorkflowMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteReqDesigWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to delete ReqDesigWorkflowMapping : {}", id);
        reqDesigWorkflowMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("reqDesigWorkflowMapping", id.toString())).build();
    }
}

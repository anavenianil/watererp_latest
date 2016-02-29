package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.RequestWorkflowMapping;
import com.callippus.water.erp.repository.RequestWorkflowMappingRepository;
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
 * REST controller for managing RequestWorkflowMapping.
 */
@RestController
@RequestMapping("/api")
public class RequestWorkflowMappingResource {

    private final Logger log = LoggerFactory.getLogger(RequestWorkflowMappingResource.class);
        
    @Inject
    private RequestWorkflowMappingRepository requestWorkflowMappingRepository;
    
    /**
     * POST  /requestWorkflowMappings -> Create a new requestWorkflowMapping.
     */
    @RequestMapping(value = "/requestWorkflowMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestWorkflowMapping> createRequestWorkflowMapping(@RequestBody RequestWorkflowMapping requestWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to save RequestWorkflowMapping : {}", requestWorkflowMapping);
        if (requestWorkflowMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("requestWorkflowMapping", "idexists", "A new requestWorkflowMapping cannot already have an ID")).body(null);
        }
        RequestWorkflowMapping result = requestWorkflowMappingRepository.save(requestWorkflowMapping);
        return ResponseEntity.created(new URI("/api/requestWorkflowMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("requestWorkflowMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /requestWorkflowMappings -> Updates an existing requestWorkflowMapping.
     */
    @RequestMapping(value = "/requestWorkflowMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestWorkflowMapping> updateRequestWorkflowMapping(@RequestBody RequestWorkflowMapping requestWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to update RequestWorkflowMapping : {}", requestWorkflowMapping);
        if (requestWorkflowMapping.getId() == null) {
            return createRequestWorkflowMapping(requestWorkflowMapping);
        }
        RequestWorkflowMapping result = requestWorkflowMappingRepository.save(requestWorkflowMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("requestWorkflowMapping", requestWorkflowMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /requestWorkflowMappings -> get all the requestWorkflowMappings.
     */
    @RequestMapping(value = "/requestWorkflowMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestWorkflowMapping>> getAllRequestWorkflowMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RequestWorkflowMappings");
        Page<RequestWorkflowMapping> page = requestWorkflowMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/requestWorkflowMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /requestWorkflowMappings/:id -> get the "id" requestWorkflowMapping.
     */
    @RequestMapping(value = "/requestWorkflowMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestWorkflowMapping> getRequestWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to get RequestWorkflowMapping : {}", id);
        RequestWorkflowMapping requestWorkflowMapping = requestWorkflowMappingRepository.findOne(id);
        return Optional.ofNullable(requestWorkflowMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /requestWorkflowMappings/:id -> delete the "id" requestWorkflowMapping.
     */
    @RequestMapping(value = "/requestWorkflowMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRequestWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to delete RequestWorkflowMapping : {}", id);
        requestWorkflowMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("requestWorkflowMapping", id.toString())).build();
    }
}

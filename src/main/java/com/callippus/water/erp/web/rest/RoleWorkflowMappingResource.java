package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.RoleWorkflowMapping;
import com.callippus.water.erp.repository.RoleWorkflowMappingRepository;
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
 * REST controller for managing RoleWorkflowMapping.
 */
@RestController
@RequestMapping("/api")
public class RoleWorkflowMappingResource {

    private final Logger log = LoggerFactory.getLogger(RoleWorkflowMappingResource.class);
        
    @Inject
    private RoleWorkflowMappingRepository roleWorkflowMappingRepository;
    
    /**
     * POST  /roleWorkflowMappings -> Create a new roleWorkflowMapping.
     */
    @RequestMapping(value = "/roleWorkflowMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RoleWorkflowMapping> createRoleWorkflowMapping(@RequestBody RoleWorkflowMapping roleWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to save RoleWorkflowMapping : {}", roleWorkflowMapping);
        if (roleWorkflowMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("roleWorkflowMapping", "idexists", "A new roleWorkflowMapping cannot already have an ID")).body(null);
        }
        RoleWorkflowMapping result = roleWorkflowMappingRepository.save(roleWorkflowMapping);
        return ResponseEntity.created(new URI("/api/roleWorkflowMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("roleWorkflowMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /roleWorkflowMappings -> Updates an existing roleWorkflowMapping.
     */
    @RequestMapping(value = "/roleWorkflowMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RoleWorkflowMapping> updateRoleWorkflowMapping(@RequestBody RoleWorkflowMapping roleWorkflowMapping) throws URISyntaxException {
        log.debug("REST request to update RoleWorkflowMapping : {}", roleWorkflowMapping);
        if (roleWorkflowMapping.getId() == null) {
            return createRoleWorkflowMapping(roleWorkflowMapping);
        }
        RoleWorkflowMapping result = roleWorkflowMappingRepository.save(roleWorkflowMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("roleWorkflowMapping", roleWorkflowMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /roleWorkflowMappings -> get all the roleWorkflowMappings.
     */
    @RequestMapping(value = "/roleWorkflowMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RoleWorkflowMapping>> getAllRoleWorkflowMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RoleWorkflowMappings");
        Page<RoleWorkflowMapping> page = roleWorkflowMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/roleWorkflowMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /roleWorkflowMappings/:id -> get the "id" roleWorkflowMapping.
     */
    @RequestMapping(value = "/roleWorkflowMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RoleWorkflowMapping> getRoleWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to get RoleWorkflowMapping : {}", id);
        RoleWorkflowMapping roleWorkflowMapping = roleWorkflowMappingRepository.findOne(id);
        return Optional.ofNullable(roleWorkflowMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /roleWorkflowMappings/:id -> delete the "id" roleWorkflowMapping.
     */
    @RequestMapping(value = "/roleWorkflowMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRoleWorkflowMapping(@PathVariable Long id) {
        log.debug("REST request to delete RoleWorkflowMapping : {}", id);
        roleWorkflowMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("roleWorkflowMapping", id.toString())).build();
    }
}

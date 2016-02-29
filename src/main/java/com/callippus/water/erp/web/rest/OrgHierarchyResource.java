package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.OrgHierarchy;
import com.callippus.water.erp.repository.OrgHierarchyRepository;
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
 * REST controller for managing OrgHierarchy.
 */
@RestController
@RequestMapping("/api")
public class OrgHierarchyResource {

    private final Logger log = LoggerFactory.getLogger(OrgHierarchyResource.class);
        
    @Inject
    private OrgHierarchyRepository orgHierarchyRepository;
    
    /**
     * POST  /orgHierarchys -> Create a new orgHierarchy.
     */
    @RequestMapping(value = "/orgHierarchys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgHierarchy> createOrgHierarchy(@RequestBody OrgHierarchy orgHierarchy) throws URISyntaxException {
        log.debug("REST request to save OrgHierarchy : {}", orgHierarchy);
        if (orgHierarchy.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("orgHierarchy", "idexists", "A new orgHierarchy cannot already have an ID")).body(null);
        }
        OrgHierarchy result = orgHierarchyRepository.save(orgHierarchy);
        return ResponseEntity.created(new URI("/api/orgHierarchys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("orgHierarchy", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orgHierarchys -> Updates an existing orgHierarchy.
     */
    @RequestMapping(value = "/orgHierarchys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgHierarchy> updateOrgHierarchy(@RequestBody OrgHierarchy orgHierarchy) throws URISyntaxException {
        log.debug("REST request to update OrgHierarchy : {}", orgHierarchy);
        if (orgHierarchy.getId() == null) {
            return createOrgHierarchy(orgHierarchy);
        }
        OrgHierarchy result = orgHierarchyRepository.save(orgHierarchy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("orgHierarchy", orgHierarchy.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orgHierarchys -> get all the orgHierarchys.
     */
    @RequestMapping(value = "/orgHierarchys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<OrgHierarchy>> getAllOrgHierarchys(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OrgHierarchys");
        Page<OrgHierarchy> page = orgHierarchyRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orgHierarchys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /orgHierarchys/:id -> get the "id" orgHierarchy.
     */
    @RequestMapping(value = "/orgHierarchys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgHierarchy> getOrgHierarchy(@PathVariable Long id) {
        log.debug("REST request to get OrgHierarchy : {}", id);
        OrgHierarchy orgHierarchy = orgHierarchyRepository.findOne(id);
        return Optional.ofNullable(orgHierarchy)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /orgHierarchys/:id -> delete the "id" orgHierarchy.
     */
    @RequestMapping(value = "/orgHierarchys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOrgHierarchy(@PathVariable Long id) {
        log.debug("REST request to delete OrgHierarchy : {}", id);
        orgHierarchyRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("orgHierarchy", id.toString())).build();
    }
}

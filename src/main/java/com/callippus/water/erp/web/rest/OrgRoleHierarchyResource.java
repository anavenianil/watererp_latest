package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.OrgRoleHierarchy;
import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.repository.OrgRoleHierarchyRepository;
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
 * REST controller for managing OrgRoleHierarchy.
 */
@RestController
@RequestMapping("/api")
public class OrgRoleHierarchyResource {

    private final Logger log = LoggerFactory.getLogger(OrgRoleHierarchyResource.class);
        
    @Inject
    private OrgRoleHierarchyRepository orgRoleHierarchyRepository;
    
    /**
     * POST  /orgRoleHierarchys -> Create a new orgRoleHierarchy.
     */
    @RequestMapping(value = "/orgRoleHierarchys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleHierarchy> createOrgRoleHierarchy(@RequestBody OrgRoleHierarchy orgRoleHierarchy) throws URISyntaxException {
        log.debug("REST request to save OrgRoleHierarchy : {}", orgRoleHierarchy);
        if (orgRoleHierarchy.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("orgRoleHierarchy", "idexists", "A new orgRoleHierarchy cannot already have an ID")).body(null);
        }
        OrgRoleHierarchy result = orgRoleHierarchyRepository.save(orgRoleHierarchy);
        return ResponseEntity.created(new URI("/api/orgRoleHierarchys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("orgRoleHierarchy", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orgRoleHierarchys -> Updates an existing orgRoleHierarchy.
     */
    @RequestMapping(value = "/orgRoleHierarchys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleHierarchy> updateOrgRoleHierarchy(@RequestBody OrgRoleHierarchy orgRoleHierarchy) throws URISyntaxException {
        log.debug("REST request to update OrgRoleHierarchy : {}", orgRoleHierarchy);
        if (orgRoleHierarchy.getId() == null) {
            return createOrgRoleHierarchy(orgRoleHierarchy);
        }
        OrgRoleHierarchy result = orgRoleHierarchyRepository.save(orgRoleHierarchy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("orgRoleHierarchy", orgRoleHierarchy.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orgRoleHierarchys -> get all the orgRoleHierarchys.
     */
    @RequestMapping(value = "/orgRoleHierarchys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<OrgRoleHierarchy>> getAllOrgRoleHierarchys(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OrgRoleHierarchys");
        Page<OrgRoleHierarchy> page = orgRoleHierarchyRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orgRoleHierarchys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /orgRoleHierarchys/:id -> get the "id" orgRoleHierarchy.
     */
    @RequestMapping(value = "/orgRoleHierarchys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleHierarchy> getOrgRoleHierarchy(@PathVariable Long id) {
        log.debug("REST request to get OrgRoleHierarchy : {}", id);
        OrgRoleHierarchy orgRoleHierarchy = orgRoleHierarchyRepository.findOne(id);
        return Optional.ofNullable(orgRoleHierarchy)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /orgRoleHierarchys/:id -> delete the "id" orgRoleHierarchy.
     */
    @RequestMapping(value = "/orgRoleHierarchys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOrgRoleHierarchy(@PathVariable Long id) {
        log.debug("REST request to delete OrgRoleHierarchy : {}", id);
        orgRoleHierarchyRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("orgRoleHierarchy", id.toString())).build();
    }
    
    /**
     * Get All orgRoleHierarchys
     */
    @RequestMapping(value = "/orgRoleHierarchys/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<OrgRoleHierarchy>> getAllOrgRoleHierarchys()
			throws Exception {
    	log.debug("REST request to getAllOrgRoleHierarchys : {}");
    	
    	List<OrgRoleHierarchy>  orgRoleHierarchy = orgRoleHierarchyRepository.findAll();
    
    	return Optional.ofNullable(orgRoleHierarchy)
				.map(result -> new ResponseEntity<>(orgRoleHierarchy, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

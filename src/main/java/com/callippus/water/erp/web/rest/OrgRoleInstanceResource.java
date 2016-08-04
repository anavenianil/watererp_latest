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

import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.domain.StatusMaster;
import com.callippus.water.erp.repository.OrgRoleInstanceRepository;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing OrgRoleInstance.
 */
@RestController
@RequestMapping("/api")
public class OrgRoleInstanceResource {

    private final Logger log = LoggerFactory.getLogger(OrgRoleInstanceResource.class);
        
    @Inject
    private OrgRoleInstanceRepository orgRoleInstanceRepository;
    
    @Inject
    private StatusMasterRepository statusMasterRepository;
    
    /**
     * POST  /orgRoleInstances -> Create a new orgRoleInstance.
     */
    @RequestMapping(value = "/orgRoleInstances",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleInstance> createOrgRoleInstance(@RequestBody OrgRoleInstance orgRoleInstance) throws URISyntaxException {
        log.debug("REST request to save OrgRoleInstance : {}", orgRoleInstance);
        if (orgRoleInstance.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("orgRoleInstance", "idexists", "A new orgRoleInstance cannot already have an ID")).body(null);
        }
        OrgRoleInstance result = orgRoleInstanceRepository.save(orgRoleInstance);
        return ResponseEntity.created(new URI("/api/orgRoleInstances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("orgRoleInstance", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orgRoleInstances -> Updates an existing orgRoleInstance.
     */
    @RequestMapping(value = "/orgRoleInstances",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleInstance> updateOrgRoleInstance(@RequestBody OrgRoleInstance orgRoleInstance) throws URISyntaxException {
        log.debug("REST request to update OrgRoleInstance : {}", orgRoleInstance);
        if (orgRoleInstance.getId() == null) {
            return createOrgRoleInstance(orgRoleInstance);
        }
        OrgRoleInstance result = orgRoleInstanceRepository.save(orgRoleInstance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("orgRoleInstance", orgRoleInstance.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orgRoleInstances -> get all the orgRoleInstances.
     */
    @RequestMapping(value = "/orgRoleInstances",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<OrgRoleInstance>> getAllOrgRoleInstances(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OrgRoleInstances");
        Page<OrgRoleInstance> page = orgRoleInstanceRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orgRoleInstances");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /orgRoleInstances/:id -> get the "id" orgRoleInstance.
     */
    @RequestMapping(value = "/orgRoleInstances/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleInstance> getOrgRoleInstance(@PathVariable Long id) {
        log.debug("REST request to get OrgRoleInstance : {}", id);
        OrgRoleInstance orgRoleInstance = orgRoleInstanceRepository.findOne(id);
        return Optional.ofNullable(orgRoleInstance)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /orgRoleInstances/:id -> delete the "id" orgRoleInstance.
     */
    @RequestMapping(value = "/orgRoleInstances/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOrgRoleInstance(@PathVariable Long id) {
        log.debug("REST request to delete OrgRoleInstance : {}", id);
        //orgRoleInstanceRepository.delete(id);
        OrgRoleInstance orgRoleInstance = orgRoleInstanceRepository.findOne(id);
        StatusMaster statusMaster = statusMasterRepository.findOne(1l);
        orgRoleInstance.setStatusMaster(statusMaster);
        orgRoleInstanceRepository.save(orgRoleInstance);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("orgRoleInstance", id.toString())).build();
    }
    
    /**
     * Get All OrgRoleInstance
     */
    @RequestMapping(value = "/orgRoleInstances/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<OrgRoleInstance>> getAllOrgRoleInstances()
			throws Exception {
    	log.debug("REST request to getAllOrgRoleInstances : {}");
    	
    	List<OrgRoleInstance> orgRoleInstances = orgRoleInstanceRepository.findAll();
    
    	return Optional.ofNullable(orgRoleInstances)
				.map(result -> new ResponseEntity<>(orgRoleInstances, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

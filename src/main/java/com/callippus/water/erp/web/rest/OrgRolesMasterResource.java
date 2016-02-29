package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.OrgRolesMaster;
import com.callippus.water.erp.repository.OrgRolesMasterRepository;
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
 * REST controller for managing OrgRolesMaster.
 */
@RestController
@RequestMapping("/api")
public class OrgRolesMasterResource {

    private final Logger log = LoggerFactory.getLogger(OrgRolesMasterResource.class);
        
    @Inject
    private OrgRolesMasterRepository orgRolesMasterRepository;
    
    /**
     * POST  /orgRolesMasters -> Create a new orgRolesMaster.
     */
    @RequestMapping(value = "/orgRolesMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRolesMaster> createOrgRolesMaster(@RequestBody OrgRolesMaster orgRolesMaster) throws URISyntaxException {
        log.debug("REST request to save OrgRolesMaster : {}", orgRolesMaster);
        if (orgRolesMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("orgRolesMaster", "idexists", "A new orgRolesMaster cannot already have an ID")).body(null);
        }
        OrgRolesMaster result = orgRolesMasterRepository.save(orgRolesMaster);
        return ResponseEntity.created(new URI("/api/orgRolesMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("orgRolesMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /orgRolesMasters -> Updates an existing orgRolesMaster.
     */
    @RequestMapping(value = "/orgRolesMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRolesMaster> updateOrgRolesMaster(@RequestBody OrgRolesMaster orgRolesMaster) throws URISyntaxException {
        log.debug("REST request to update OrgRolesMaster : {}", orgRolesMaster);
        if (orgRolesMaster.getId() == null) {
            return createOrgRolesMaster(orgRolesMaster);
        }
        OrgRolesMaster result = orgRolesMasterRepository.save(orgRolesMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("orgRolesMaster", orgRolesMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /orgRolesMasters -> get all the orgRolesMasters.
     */
    @RequestMapping(value = "/orgRolesMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<OrgRolesMaster>> getAllOrgRolesMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OrgRolesMasters");
        Page<OrgRolesMaster> page = orgRolesMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/orgRolesMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /orgRolesMasters/:id -> get the "id" orgRolesMaster.
     */
    @RequestMapping(value = "/orgRolesMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRolesMaster> getOrgRolesMaster(@PathVariable Long id) {
        log.debug("REST request to get OrgRolesMaster : {}", id);
        OrgRolesMaster orgRolesMaster = orgRolesMasterRepository.findOne(id);
        return Optional.ofNullable(orgRolesMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /orgRolesMasters/:id -> delete the "id" orgRolesMaster.
     */
    @RequestMapping(value = "/orgRolesMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOrgRolesMaster(@PathVariable Long id) {
        log.debug("REST request to delete OrgRolesMaster : {}", id);
        orgRolesMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("orgRolesMaster", id.toString())).build();
    }
}

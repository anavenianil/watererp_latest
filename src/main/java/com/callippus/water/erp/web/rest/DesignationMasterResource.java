package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DesignationMaster;
import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.repository.DesignationMasterRepository;
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
 * REST controller for managing DesignationMaster.
 */
@RestController
@RequestMapping("/api")
public class DesignationMasterResource {

    private final Logger log = LoggerFactory.getLogger(DesignationMasterResource.class);
        
    @Inject
    private DesignationMasterRepository designationMasterRepository;
    
    /**
     * POST  /designationMasters -> Create a new designationMaster.
     */
    @RequestMapping(value = "/designationMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesignationMaster> createDesignationMaster(@RequestBody DesignationMaster designationMaster) throws URISyntaxException {
        log.debug("REST request to save DesignationMaster : {}", designationMaster);
        if (designationMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("designationMaster", "idexists", "A new designationMaster cannot already have an ID")).body(null);
        }
        DesignationMaster result = designationMasterRepository.save(designationMaster);
        return ResponseEntity.created(new URI("/api/designationMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("designationMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /designationMasters -> Updates an existing designationMaster.
     */
    @RequestMapping(value = "/designationMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesignationMaster> updateDesignationMaster(@RequestBody DesignationMaster designationMaster) throws URISyntaxException {
        log.debug("REST request to update DesignationMaster : {}", designationMaster);
        if (designationMaster.getId() == null) {
            return createDesignationMaster(designationMaster);
        }
        DesignationMaster result = designationMasterRepository.save(designationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("designationMaster", designationMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /designationMasters -> get all the designationMasters.
     */
    @RequestMapping(value = "/designationMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DesignationMaster>> getAllDesignationMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DesignationMasters");
        Page<DesignationMaster> page = designationMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/designationMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /designationMasters/:id -> get the "id" designationMaster.
     */
    @RequestMapping(value = "/designationMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DesignationMaster> getDesignationMaster(@PathVariable Long id) {
        log.debug("REST request to get DesignationMaster : {}", id);
        DesignationMaster designationMaster = designationMasterRepository.findOne(id);
        return Optional.ofNullable(designationMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /designationMasters/:id -> delete the "id" designationMaster.
     */
    @RequestMapping(value = "/designationMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDesignationMaster(@PathVariable Long id) {
        log.debug("REST request to delete DesignationMaster : {}", id);
        designationMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("designationMaster", id.toString())).build();
    }
    
    /**
     * Get All DesignationMaster
     */
    @RequestMapping(value = "/designationMasters/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<DesignationMaster>> getAllDesignationMaster()
			throws Exception {
    	log.debug("REST request to getAllOrgRoleInstances : {}");
    	
    	List<DesignationMaster> designationMasters = designationMasterRepository.findAllByOrderByNameAsc();
    
    	return Optional.ofNullable(designationMasters)
				.map(result -> new ResponseEntity<>(designationMasters, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

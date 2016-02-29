package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DepartmentsMaster;
import com.callippus.water.erp.repository.DepartmentsMasterRepository;
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
 * REST controller for managing DepartmentsMaster.
 */
@RestController
@RequestMapping("/api")
public class DepartmentsMasterResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentsMasterResource.class);
        
    @Inject
    private DepartmentsMasterRepository departmentsMasterRepository;
    
    /**
     * POST  /departmentsMasters -> Create a new departmentsMaster.
     */
    @RequestMapping(value = "/departmentsMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentsMaster> createDepartmentsMaster(@RequestBody DepartmentsMaster departmentsMaster) throws URISyntaxException {
        log.debug("REST request to save DepartmentsMaster : {}", departmentsMaster);
        if (departmentsMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("departmentsMaster", "idexists", "A new departmentsMaster cannot already have an ID")).body(null);
        }
        DepartmentsMaster result = departmentsMasterRepository.save(departmentsMaster);
        return ResponseEntity.created(new URI("/api/departmentsMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("departmentsMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /departmentsMasters -> Updates an existing departmentsMaster.
     */
    @RequestMapping(value = "/departmentsMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentsMaster> updateDepartmentsMaster(@RequestBody DepartmentsMaster departmentsMaster) throws URISyntaxException {
        log.debug("REST request to update DepartmentsMaster : {}", departmentsMaster);
        if (departmentsMaster.getId() == null) {
            return createDepartmentsMaster(departmentsMaster);
        }
        DepartmentsMaster result = departmentsMasterRepository.save(departmentsMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("departmentsMaster", departmentsMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /departmentsMasters -> get all the departmentsMasters.
     */
    @RequestMapping(value = "/departmentsMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DepartmentsMaster>> getAllDepartmentsMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DepartmentsMasters");
        Page<DepartmentsMaster> page = departmentsMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/departmentsMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /departmentsMasters/:id -> get the "id" departmentsMaster.
     */
    @RequestMapping(value = "/departmentsMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentsMaster> getDepartmentsMaster(@PathVariable Long id) {
        log.debug("REST request to get DepartmentsMaster : {}", id);
        DepartmentsMaster departmentsMaster = departmentsMasterRepository.findOne(id);
        return Optional.ofNullable(departmentsMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /departmentsMasters/:id -> delete the "id" departmentsMaster.
     */
    @RequestMapping(value = "/departmentsMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDepartmentsMaster(@PathVariable Long id) {
        log.debug("REST request to delete DepartmentsMaster : {}", id);
        departmentsMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("departmentsMaster", id.toString())).build();
    }
}

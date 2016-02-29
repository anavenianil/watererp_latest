package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DepartmentTypeMaster;
import com.callippus.water.erp.repository.DepartmentTypeMasterRepository;
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
 * REST controller for managing DepartmentTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class DepartmentTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentTypeMasterResource.class);
        
    @Inject
    private DepartmentTypeMasterRepository departmentTypeMasterRepository;
    
    /**
     * POST  /departmentTypeMasters -> Create a new departmentTypeMaster.
     */
    @RequestMapping(value = "/departmentTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentTypeMaster> createDepartmentTypeMaster(@RequestBody DepartmentTypeMaster departmentTypeMaster) throws URISyntaxException {
        log.debug("REST request to save DepartmentTypeMaster : {}", departmentTypeMaster);
        if (departmentTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("departmentTypeMaster", "idexists", "A new departmentTypeMaster cannot already have an ID")).body(null);
        }
        DepartmentTypeMaster result = departmentTypeMasterRepository.save(departmentTypeMaster);
        return ResponseEntity.created(new URI("/api/departmentTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("departmentTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /departmentTypeMasters -> Updates an existing departmentTypeMaster.
     */
    @RequestMapping(value = "/departmentTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentTypeMaster> updateDepartmentTypeMaster(@RequestBody DepartmentTypeMaster departmentTypeMaster) throws URISyntaxException {
        log.debug("REST request to update DepartmentTypeMaster : {}", departmentTypeMaster);
        if (departmentTypeMaster.getId() == null) {
            return createDepartmentTypeMaster(departmentTypeMaster);
        }
        DepartmentTypeMaster result = departmentTypeMasterRepository.save(departmentTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("departmentTypeMaster", departmentTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /departmentTypeMasters -> get all the departmentTypeMasters.
     */
    @RequestMapping(value = "/departmentTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DepartmentTypeMaster>> getAllDepartmentTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DepartmentTypeMasters");
        Page<DepartmentTypeMaster> page = departmentTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/departmentTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /departmentTypeMasters/:id -> get the "id" departmentTypeMaster.
     */
    @RequestMapping(value = "/departmentTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentTypeMaster> getDepartmentTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get DepartmentTypeMaster : {}", id);
        DepartmentTypeMaster departmentTypeMaster = departmentTypeMasterRepository.findOne(id);
        return Optional.ofNullable(departmentTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /departmentTypeMasters/:id -> delete the "id" departmentTypeMaster.
     */
    @RequestMapping(value = "/departmentTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDepartmentTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete DepartmentTypeMaster : {}", id);
        departmentTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("departmentTypeMaster", id.toString())).build();
    }
}

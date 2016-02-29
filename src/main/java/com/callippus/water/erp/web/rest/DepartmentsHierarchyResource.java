package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DepartmentsHierarchy;
import com.callippus.water.erp.repository.DepartmentsHierarchyRepository;
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
 * REST controller for managing DepartmentsHierarchy.
 */
@RestController
@RequestMapping("/api")
public class DepartmentsHierarchyResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentsHierarchyResource.class);
        
    @Inject
    private DepartmentsHierarchyRepository departmentsHierarchyRepository;
    
    /**
     * POST  /departmentsHierarchys -> Create a new departmentsHierarchy.
     */
    @RequestMapping(value = "/departmentsHierarchys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentsHierarchy> createDepartmentsHierarchy(@RequestBody DepartmentsHierarchy departmentsHierarchy) throws URISyntaxException {
        log.debug("REST request to save DepartmentsHierarchy : {}", departmentsHierarchy);
        if (departmentsHierarchy.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("departmentsHierarchy", "idexists", "A new departmentsHierarchy cannot already have an ID")).body(null);
        }
        DepartmentsHierarchy result = departmentsHierarchyRepository.save(departmentsHierarchy);
        return ResponseEntity.created(new URI("/api/departmentsHierarchys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("departmentsHierarchy", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /departmentsHierarchys -> Updates an existing departmentsHierarchy.
     */
    @RequestMapping(value = "/departmentsHierarchys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentsHierarchy> updateDepartmentsHierarchy(@RequestBody DepartmentsHierarchy departmentsHierarchy) throws URISyntaxException {
        log.debug("REST request to update DepartmentsHierarchy : {}", departmentsHierarchy);
        if (departmentsHierarchy.getId() == null) {
            return createDepartmentsHierarchy(departmentsHierarchy);
        }
        DepartmentsHierarchy result = departmentsHierarchyRepository.save(departmentsHierarchy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("departmentsHierarchy", departmentsHierarchy.getId().toString()))
            .body(result);
    }

    /**
     * GET  /departmentsHierarchys -> get all the departmentsHierarchys.
     */
    @RequestMapping(value = "/departmentsHierarchys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DepartmentsHierarchy>> getAllDepartmentsHierarchys(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DepartmentsHierarchys");
        Page<DepartmentsHierarchy> page = departmentsHierarchyRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/departmentsHierarchys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /departmentsHierarchys/:id -> get the "id" departmentsHierarchy.
     */
    @RequestMapping(value = "/departmentsHierarchys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DepartmentsHierarchy> getDepartmentsHierarchy(@PathVariable Long id) {
        log.debug("REST request to get DepartmentsHierarchy : {}", id);
        DepartmentsHierarchy departmentsHierarchy = departmentsHierarchyRepository.findOne(id);
        return Optional.ofNullable(departmentsHierarchy)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /departmentsHierarchys/:id -> delete the "id" departmentsHierarchy.
     */
    @RequestMapping(value = "/departmentsHierarchys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDepartmentsHierarchy(@PathVariable Long id) {
        log.debug("REST request to delete DepartmentsHierarchy : {}", id);
        departmentsHierarchyRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("departmentsHierarchy", id.toString())).build();
    }
}

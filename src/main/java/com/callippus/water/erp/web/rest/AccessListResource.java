package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.AccessList;
import com.callippus.water.erp.repository.AccessListRepository;
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
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AccessList.
 */
@RestController
@RequestMapping("/api")
public class AccessListResource {

    private final Logger log = LoggerFactory.getLogger(AccessListResource.class);
        
    @Inject
    private AccessListRepository accessListRepository;
    
    /**
     * POST  /accessLists -> Create a new accessList.
     */
    @RequestMapping(value = "/accessLists",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AccessList> createAccessList(@Valid @RequestBody AccessList accessList) throws URISyntaxException {
        log.debug("REST request to save AccessList : {}", accessList);
        if (accessList.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("accessList", "idexists", "A new accessList cannot already have an ID")).body(null);
        }
        AccessList result = accessListRepository.save(accessList);
        return ResponseEntity.created(new URI("/api/accessLists/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("accessList", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /accessLists -> Updates an existing accessList.
     */
    @RequestMapping(value = "/accessLists",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AccessList> updateAccessList(@Valid @RequestBody AccessList accessList) throws URISyntaxException {
        log.debug("REST request to update AccessList : {}", accessList);
        if (accessList.getId() == null) {
            return createAccessList(accessList);
        }
        AccessList result = accessListRepository.save(accessList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("accessList", accessList.getId().toString()))
            .body(result);
    }

    /**
     * GET  /accessLists -> get all the accessLists.
     */
    @RequestMapping(value = "/accessLists",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<AccessList>> getAllAccessLists(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of AccessLists");
        Page<AccessList> page = accessListRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/accessLists");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /accessLists/:id -> get the "id" accessList.
     */
    @RequestMapping(value = "/accessLists/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<AccessList> getAccessList(@PathVariable Long id) {
        log.debug("REST request to get AccessList : {}", id);
        AccessList accessList = accessListRepository.findOne(id);
        return Optional.ofNullable(accessList)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /accessLists/:id -> delete the "id" accessList.
     */
    @RequestMapping(value = "/accessLists/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAccessList(@PathVariable Long id) {
        log.debug("REST request to delete AccessList : {}", id);
        accessListRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("accessList", id.toString())).build();
    }
}

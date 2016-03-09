package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.Url2Role;
import com.callippus.water.erp.repository.Url2RoleRepository;
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
 * REST controller for managing Url2Role.
 */
@RestController
@RequestMapping("/api")
public class Url2RoleResource {

    private final Logger log = LoggerFactory.getLogger(Url2RoleResource.class);
        
    @Inject
    private Url2RoleRepository url2RoleRepository;
    
    /**
     * POST  /url2Roles -> Create a new url2Role.
     */
    @RequestMapping(value = "/url2Roles",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Url2Role> createUrl2Role(@RequestBody Url2Role url2Role) throws URISyntaxException {
        log.debug("REST request to save Url2Role : {}", url2Role);
        if (url2Role.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("url2Role", "idexists", "A new url2Role cannot already have an ID")).body(null);
        }
        Url2Role result = url2RoleRepository.save(url2Role);
        return ResponseEntity.created(new URI("/api/url2Roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("url2Role", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /url2Roles -> Updates an existing url2Role.
     */
    @RequestMapping(value = "/url2Roles",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Url2Role> updateUrl2Role(@RequestBody Url2Role url2Role) throws URISyntaxException {
        log.debug("REST request to update Url2Role : {}", url2Role);
        if (url2Role.getId() == null) {
            return createUrl2Role(url2Role);
        }
        Url2Role result = url2RoleRepository.save(url2Role);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("url2Role", url2Role.getId().toString()))
            .body(result);
    }

    /**
     * GET  /url2Roles -> get all the url2Roles.
     */
    @RequestMapping(value = "/url2Roles",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Url2Role>> getAllUrl2Roles(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Url2Roles");
        Page<Url2Role> page = url2RoleRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/url2Roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /url2Roles/:id -> get the "id" url2Role.
     */
    @RequestMapping(value = "/url2Roles/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Url2Role> getUrl2Role(@PathVariable Long id) {
        log.debug("REST request to get Url2Role : {}", id);
        Url2Role url2Role = url2RoleRepository.findOne(id);
        return Optional.ofNullable(url2Role)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /url2Roles/:id -> delete the "id" url2Role.
     */
    @RequestMapping(value = "/url2Roles/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUrl2Role(@PathVariable Long id) {
        log.debug("REST request to delete Url2Role : {}", id);
        url2RoleRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("url2Role", id.toString())).build();
    }
}

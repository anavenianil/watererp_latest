package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.Authority;
import com.callippus.water.erp.domain.Module;
import com.callippus.water.erp.repository.AuthorityRepository;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Authority.
 */
@RestController
@RequestMapping("/api")
public class AuthorityResource {

    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);

    @Inject
    private AuthorityRepository authorityRepository;

    /**
     * POST  /authoritys -> Create a new authority.
     */
    @RequestMapping(value = "/authoritys",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> create(@RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authority);
        if (authority.getName() != null) {
            return ResponseEntity.badRequest().header("Failure", "A new authority cannot already have an Name").build();
        }
        authorityRepository.save(authority);
        return ResponseEntity.created(new URI("/api/authoritys/" + authority.getName())).build();
    }

    /**
     * PUT  /authoritys -> Updates an existing authority.
     */
    @RequestMapping(value = "/authoritys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> update(@RequestBody Authority authority) throws URISyntaxException {
        log.debug("REST request to update Authority : {}", authority);
        if (authority.getName() == null) {
            return create(authority);
        }
        authorityRepository.save(authority);
        return ResponseEntity.ok().build();
    }

    /**
     * GET  /authoritys -> get all the authoritys.
     */
    @RequestMapping(value = "/authoritys",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Authority>> getAll(Pageable pageable)
        throws URISyntaxException {
        Page<Authority> page = authorityRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/authoritys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        
    }

    /**
     * GET  /authoritys/:name -> get the "name" authority.
     */
    @RequestMapping(value = "/authoritys/{name}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Authority> get(@PathVariable String name, HttpServletResponse response) {
        log.debug("REST request to get Authority : {}", name);
        Authority authority = authorityRepository.findOne(name);
        if (authority == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(authority, HttpStatus.OK);
    }

    /**
     * DELETE  /authoritys/:name -> delete the "name" authority.
     */
    @RequestMapping(value = "/authoritys/{name}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable String name) {
        log.debug("REST request to delete Authority : {}", name);
        authorityRepository.delete(name);
    }
}

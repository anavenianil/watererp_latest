package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CurrentUsers;
import com.callippus.water.erp.repository.CurrentUsersRepository;
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
 * REST controller for managing CurrentUsers.
 */
@RestController
@RequestMapping("/api")
public class CurrentUsersResource {

    private final Logger log = LoggerFactory.getLogger(CurrentUsersResource.class);
        
    @Inject
    private CurrentUsersRepository currentUsersRepository;
    
    /**
     * POST  /currentUserss -> Create a new currentUsers.
     */
    @RequestMapping(value = "/currentUserss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CurrentUsers> createCurrentUsers(@RequestBody CurrentUsers currentUsers) throws URISyntaxException {
        log.debug("REST request to save CurrentUsers : {}", currentUsers);
        if (currentUsers.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("currentUsers", "idexists", "A new currentUsers cannot already have an ID")).body(null);
        }
        CurrentUsers result = currentUsersRepository.save(currentUsers);
        return ResponseEntity.created(new URI("/api/currentUserss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("currentUsers", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /currentUserss -> Updates an existing currentUsers.
     */
    @RequestMapping(value = "/currentUserss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CurrentUsers> updateCurrentUsers(@RequestBody CurrentUsers currentUsers) throws URISyntaxException {
        log.debug("REST request to update CurrentUsers : {}", currentUsers);
        if (currentUsers.getId() == null) {
            return createCurrentUsers(currentUsers);
        }
        CurrentUsers result = currentUsersRepository.save(currentUsers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("currentUsers", currentUsers.getId().toString()))
            .body(result);
    }

    /**
     * GET  /currentUserss -> get all the currentUserss.
     */
    @RequestMapping(value = "/currentUserss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CurrentUsers>> getAllCurrentUserss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CurrentUserss");
        Page<CurrentUsers> page = currentUsersRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/currentUserss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /currentUserss/:id -> get the "id" currentUsers.
     */
    @RequestMapping(value = "/currentUserss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CurrentUsers> getCurrentUsers(@PathVariable Long id) {
        log.debug("REST request to get CurrentUsers : {}", id);
        CurrentUsers currentUsers = currentUsersRepository.findOne(id);
        return Optional.ofNullable(currentUsers)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /currentUserss/:id -> delete the "id" currentUsers.
     */
    @RequestMapping(value = "/currentUserss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCurrentUsers(@PathVariable Long id) {
        log.debug("REST request to delete CurrentUsers : {}", id);
        currentUsersRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("currentUsers", id.toString())).build();
    }
}

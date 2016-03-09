package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MenuItem2Url;
import com.callippus.water.erp.repository.MenuItem2UrlRepository;
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
 * REST controller for managing MenuItem2Url.
 */
@RestController
@RequestMapping("/api")
public class MenuItem2UrlResource {

    private final Logger log = LoggerFactory.getLogger(MenuItem2UrlResource.class);
        
    @Inject
    private MenuItem2UrlRepository menuItem2UrlRepository;
    
    /**
     * POST  /menuItem2Urls -> Create a new menuItem2Url.
     */
    @RequestMapping(value = "/menuItem2Urls",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuItem2Url> createMenuItem2Url(@RequestBody MenuItem2Url menuItem2Url) throws URISyntaxException {
        log.debug("REST request to save MenuItem2Url : {}", menuItem2Url);
        if (menuItem2Url.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("menuItem2Url", "idexists", "A new menuItem2Url cannot already have an ID")).body(null);
        }
        MenuItem2Url result = menuItem2UrlRepository.save(menuItem2Url);
        return ResponseEntity.created(new URI("/api/menuItem2Urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("menuItem2Url", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menuItem2Urls -> Updates an existing menuItem2Url.
     */
    @RequestMapping(value = "/menuItem2Urls",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuItem2Url> updateMenuItem2Url(@RequestBody MenuItem2Url menuItem2Url) throws URISyntaxException {
        log.debug("REST request to update MenuItem2Url : {}", menuItem2Url);
        if (menuItem2Url.getId() == null) {
            return createMenuItem2Url(menuItem2Url);
        }
        MenuItem2Url result = menuItem2UrlRepository.save(menuItem2Url);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("menuItem2Url", menuItem2Url.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menuItem2Urls -> get all the menuItem2Urls.
     */
    @RequestMapping(value = "/menuItem2Urls",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MenuItem2Url>> getAllMenuItem2Urls(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MenuItem2Urls");
        Page<MenuItem2Url> page = menuItem2UrlRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menuItem2Urls");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /menuItem2Urls/:id -> get the "id" menuItem2Url.
     */
    @RequestMapping(value = "/menuItem2Urls/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuItem2Url> getMenuItem2Url(@PathVariable Long id) {
        log.debug("REST request to get MenuItem2Url : {}", id);
        MenuItem2Url menuItem2Url = menuItem2UrlRepository.findOne(id);
        return Optional.ofNullable(menuItem2Url)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /menuItem2Urls/:id -> delete the "id" menuItem2Url.
     */
    @RequestMapping(value = "/menuItem2Urls/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMenuItem2Url(@PathVariable Long id) {
        log.debug("REST request to delete MenuItem2Url : {}", id);
        menuItem2UrlRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("menuItem2Url", id.toString())).build();
    }
}

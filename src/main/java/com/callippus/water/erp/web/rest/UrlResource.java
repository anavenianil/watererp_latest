package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.Url;
import com.callippus.water.erp.repository.UrlRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * REST controller for managing Url.
 */
@RestController
@RequestMapping("/api")
public class UrlResource {

    private final Logger log = LoggerFactory.getLogger(UrlResource.class);
        
    @Inject
    private UrlRepository urlRepository;
    
    /**
     * POST  /urls -> Create a new url.
     */
    @RequestMapping(value = "/urls",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Url> createUrl(@Valid @RequestBody Url url) throws URISyntaxException {
        log.debug("REST request to save Url : {}", url);
        if (url.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("url", "idexists", "A new url cannot already have an ID")).body(null);
        }
        Url result = urlRepository.save(url);
        return ResponseEntity.created(new URI("/api/urls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("url", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /urls -> Updates an existing url.
     */
    @RequestMapping(value = "/urls",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Url> updateUrl(@Valid @RequestBody Url url) throws URISyntaxException {
        log.debug("REST request to update Url : {}", url);
        if (url.getId() == null) {
            return createUrl(url);
        }
        Url result = urlRepository.save(url);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("url", url.getId().toString()))
            .body(result);
    }

    /**
     * GET  /urls -> get all the urls.
     */
    @RequestMapping(value = "/urls",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Url>> getAllUrls(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Urls");
        Page<Url> page = urlRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/urls");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /urls/:id -> get the "id" url.
     */
    @RequestMapping(value = "/urls/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Url> getUrl(@PathVariable Long id) {
        log.debug("REST request to get Url : {}", id);
        Url url = urlRepository.findOne(id);
        return Optional.ofNullable(url)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /urls/:id -> delete the "id" url.
     */
    @RequestMapping(value = "/urls/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUrl(@PathVariable Long id) {
        log.debug("REST request to delete Url : {}", id);
        urlRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("url", id.toString())).build();
    }
}

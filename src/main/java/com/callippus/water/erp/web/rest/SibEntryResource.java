package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.SibEntry;
import com.callippus.water.erp.repository.SibEntryRepository;
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
 * REST controller for managing SibEntry.
 */
@RestController
@RequestMapping("/api")
public class SibEntryResource {

    private final Logger log = LoggerFactory.getLogger(SibEntryResource.class);
        
    @Inject
    private SibEntryRepository sibEntryRepository;
    
    /**
     * POST  /sibEntrys -> Create a new sibEntry.
     */
    @RequestMapping(value = "/sibEntrys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SibEntry> createSibEntry(@RequestBody SibEntry sibEntry) throws URISyntaxException {
        log.debug("REST request to save SibEntry : {}", sibEntry);
        if (sibEntry.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sibEntry", "idexists", "A new sibEntry cannot already have an ID")).body(null);
        }
        SibEntry result = sibEntryRepository.save(sibEntry);
        return ResponseEntity.created(new URI("/api/sibEntrys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sibEntry", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sibEntrys -> Updates an existing sibEntry.
     */
    @RequestMapping(value = "/sibEntrys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SibEntry> updateSibEntry(@RequestBody SibEntry sibEntry) throws URISyntaxException {
        log.debug("REST request to update SibEntry : {}", sibEntry);
        if (sibEntry.getId() == null) {
            return createSibEntry(sibEntry);
        }
        SibEntry result = sibEntryRepository.save(sibEntry);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sibEntry", sibEntry.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sibEntrys -> get all the sibEntrys.
     */
    @RequestMapping(value = "/sibEntrys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SibEntry>> getAllSibEntrys(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SibEntrys");
        Page<SibEntry> page = sibEntryRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sibEntrys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sibEntrys/:id -> get the "id" sibEntry.
     */
    @RequestMapping(value = "/sibEntrys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SibEntry> getSibEntry(@PathVariable Long id) {
        log.debug("REST request to get SibEntry : {}", id);
        SibEntry sibEntry = sibEntryRepository.findOne(id);
        return Optional.ofNullable(sibEntry)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sibEntrys/:id -> delete the "id" sibEntry.
     */
    @RequestMapping(value = "/sibEntrys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSibEntry(@PathVariable Long id) {
        log.debug("REST request to delete SibEntry : {}", id);
        sibEntryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sibEntry", id.toString())).build();
    }
}

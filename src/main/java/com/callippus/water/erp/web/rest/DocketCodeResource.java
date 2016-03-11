package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.DocketCode;
import com.callippus.water.erp.repository.DocketCodeRepository;
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
 * REST controller for managing DocketCode.
 */
@RestController
@RequestMapping("/api")
public class DocketCodeResource {

    private final Logger log = LoggerFactory.getLogger(DocketCodeResource.class);
        
    @Inject
    private DocketCodeRepository docketCodeRepository;
    
    /**
     * POST  /docketCodes -> Create a new docketCode.
     */
    @RequestMapping(value = "/docketCodes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DocketCode> createDocketCode(@Valid @RequestBody DocketCode docketCode) throws URISyntaxException {
        log.debug("REST request to save DocketCode : {}", docketCode);
        if (docketCode.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("docketCode", "idexists", "A new docketCode cannot already have an ID")).body(null);
        }
        DocketCode result = docketCodeRepository.save(docketCode);
        return ResponseEntity.created(new URI("/api/docketCodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("docketCode", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /docketCodes -> Updates an existing docketCode.
     */
    @RequestMapping(value = "/docketCodes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DocketCode> updateDocketCode(@Valid @RequestBody DocketCode docketCode) throws URISyntaxException {
        log.debug("REST request to update DocketCode : {}", docketCode);
        if (docketCode.getId() == null) {
            return createDocketCode(docketCode);
        }
        DocketCode result = docketCodeRepository.save(docketCode);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("docketCode", docketCode.getId().toString()))
            .body(result);
    }

    /**
     * GET  /docketCodes -> get all the docketCodes.
     */
    @RequestMapping(value = "/docketCodes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<DocketCode>> getAllDocketCodes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of DocketCodes");
        Page<DocketCode> page = docketCodeRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/docketCodes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /docketCodes/:id -> get the "id" docketCode.
     */
    @RequestMapping(value = "/docketCodes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<DocketCode> getDocketCode(@PathVariable Long id) {
        log.debug("REST request to get DocketCode : {}", id);
        DocketCode docketCode = docketCodeRepository.findOne(id);
        return Optional.ofNullable(docketCode)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /docketCodes/:id -> delete the "id" docketCode.
     */
    @RequestMapping(value = "/docketCodes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteDocketCode(@PathVariable Long id) {
        log.debug("REST request to delete DocketCode : {}", id);
        docketCodeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("docketCode", id.toString())).build();
    }
}

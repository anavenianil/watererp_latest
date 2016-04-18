package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MeterStatus;
import com.callippus.water.erp.repository.MeterStatusRepository;
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
 * REST controller for managing MeterStatus.
 */
@RestController
@RequestMapping("/api")
public class MeterStatusResource {

    private final Logger log = LoggerFactory.getLogger(MeterStatusResource.class);
        
    @Inject
    private MeterStatusRepository meterStatusRepository;
    
    /**
     * POST  /meterStatuss -> Create a new meterStatus.
     */
    @RequestMapping(value = "/meterStatuss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MeterStatus> createMeterStatus(@RequestBody MeterStatus meterStatus) throws URISyntaxException {
        log.debug("REST request to save MeterStatus : {}", meterStatus);
        if (meterStatus.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("meterStatus", "idexists", "A new meterStatus cannot already have an ID")).body(null);
        }
        MeterStatus result = meterStatusRepository.save(meterStatus);
        return ResponseEntity.created(new URI("/api/meterStatuss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("meterStatus", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /meterStatuss -> Updates an existing meterStatus.
     */
    @RequestMapping(value = "/meterStatuss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MeterStatus> updateMeterStatus(@RequestBody MeterStatus meterStatus) throws URISyntaxException {
        log.debug("REST request to update MeterStatus : {}", meterStatus);
        if (meterStatus.getId() == null) {
            return createMeterStatus(meterStatus);
        }
        MeterStatus result = meterStatusRepository.save(meterStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("meterStatus", meterStatus.getId().toString()))
            .body(result);
    }

    /**
     * GET  /meterStatuss -> get all the meterStatuss.
     */
    @RequestMapping(value = "/meterStatuss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MeterStatus>> getAllMeterStatuss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MeterStatuss");
        Page<MeterStatus> page = meterStatusRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/meterStatuss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /meterStatuss/:id -> get the "id" meterStatus.
     */
    @RequestMapping(value = "/meterStatuss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MeterStatus> getMeterStatus(@PathVariable Long id) {
        log.debug("REST request to get MeterStatus : {}", id);
        MeterStatus meterStatus = meterStatusRepository.findOne(id);
        return Optional.ofNullable(meterStatus)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /meterStatuss/:id -> delete the "id" meterStatus.
     */
    @RequestMapping(value = "/meterStatuss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMeterStatus(@PathVariable Long id) {
        log.debug("REST request to delete MeterStatus : {}", id);
        meterStatusRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("meterStatus", id.toString())).build();
    }
}

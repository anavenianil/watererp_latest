package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MeterDetails;
import com.callippus.water.erp.repository.MeterDetailsRepository;
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
 * REST controller for managing MeterDetails.
 */
@RestController
@RequestMapping("/api")
public class MeterDetailsResource {

    private final Logger log = LoggerFactory.getLogger(MeterDetailsResource.class);
        
    @Inject
    private MeterDetailsRepository meterDetailsRepository;
    
    /**
     * POST  /meterDetailss -> Create a new meterDetails.
     */
    @RequestMapping(value = "/meterDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MeterDetails> createMeterDetails(@Valid @RequestBody MeterDetails meterDetails) throws URISyntaxException {
        log.debug("REST request to save MeterDetails : {}", meterDetails);
        if (meterDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("meterDetails", "idexists", "A new meterDetails cannot already have an ID")).body(null);
        }
        MeterDetails result = meterDetailsRepository.save(meterDetails);
        return ResponseEntity.created(new URI("/api/meterDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("meterDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /meterDetailss -> Updates an existing meterDetails.
     */
    @RequestMapping(value = "/meterDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MeterDetails> updateMeterDetails(@Valid @RequestBody MeterDetails meterDetails) throws URISyntaxException {
        log.debug("REST request to update MeterDetails : {}", meterDetails);
        if (meterDetails.getId() == null) {
            return createMeterDetails(meterDetails);
        }
        MeterDetails result = meterDetailsRepository.save(meterDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("meterDetails", meterDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /meterDetailss -> get all the meterDetailss.
     */
    @RequestMapping(value = "/meterDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MeterDetails>> getAllMeterDetailss(Pageable pageable,
    		@RequestParam(value = "meterStatusId", required = false) Long meterStatusId)
        throws URISyntaxException {
        log.debug("REST request to get a page of MeterDetailss");
       //Page<MeterDetails> page = meterDetailsRepository.findAll(pageable);
        Page<MeterDetails> page;
        if(meterStatusId != null){
        	page = meterDetailsRepository.findByMeterStatus(pageable, meterStatusId);
        }
        else
        {
        	page = meterDetailsRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/meterDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /meterDetailss/:id -> get the "id" meterDetails.
     */
    @RequestMapping(value = "/meterDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MeterDetails> getMeterDetails(@PathVariable Long id) {
        log.debug("REST request to get MeterDetails : {}", id);
        MeterDetails meterDetails = meterDetailsRepository.findOne(id);
        return Optional.ofNullable(meterDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /meterDetailss/:id -> delete the "id" meterDetails.
     */
    @RequestMapping(value = "/meterDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMeterDetails(@PathVariable Long id) {
        log.debug("REST request to delete MeterDetails : {}", id);
        meterDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("meterDetails", id.toString())).build();
    }
}

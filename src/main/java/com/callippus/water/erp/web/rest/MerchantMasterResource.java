package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MerchantMaster;
import com.callippus.water.erp.repository.MerchantMasterRepository;
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
 * REST controller for managing MerchantMaster.
 */
@RestController
@RequestMapping("/api")
public class MerchantMasterResource {

    private final Logger log = LoggerFactory.getLogger(MerchantMasterResource.class);
        
    @Inject
    private MerchantMasterRepository merchantMasterRepository;
    
    /**
     * POST  /merchantMasters -> Create a new merchantMaster.
     */
    @RequestMapping(value = "/merchantMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MerchantMaster> createMerchantMaster(@RequestBody MerchantMaster merchantMaster) throws URISyntaxException {
        log.debug("REST request to save MerchantMaster : {}", merchantMaster);
        if (merchantMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("merchantMaster", "idexists", "A new merchantMaster cannot already have an ID")).body(null);
        }
        MerchantMaster result = merchantMasterRepository.save(merchantMaster);
        return ResponseEntity.created(new URI("/api/merchantMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("merchantMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /merchantMasters -> Updates an existing merchantMaster.
     */
    @RequestMapping(value = "/merchantMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MerchantMaster> updateMerchantMaster(@RequestBody MerchantMaster merchantMaster) throws URISyntaxException {
        log.debug("REST request to update MerchantMaster : {}", merchantMaster);
        if (merchantMaster.getId() == null) {
            return createMerchantMaster(merchantMaster);
        }
        MerchantMaster result = merchantMasterRepository.save(merchantMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("merchantMaster", merchantMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /merchantMasters -> get all the merchantMasters.
     */
    @RequestMapping(value = "/merchantMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MerchantMaster>> getAllMerchantMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MerchantMasters");
        Page<MerchantMaster> page = merchantMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/merchantMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /merchantMasters/:id -> get the "id" merchantMaster.
     */
    @RequestMapping(value = "/merchantMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MerchantMaster> getMerchantMaster(@PathVariable Long id) {
        log.debug("REST request to get MerchantMaster : {}", id);
        MerchantMaster merchantMaster = merchantMasterRepository.findOne(id);
        return Optional.ofNullable(merchantMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /merchantMasters/:id -> delete the "id" merchantMaster.
     */
    @RequestMapping(value = "/merchantMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMerchantMaster(@PathVariable Long id) {
        log.debug("REST request to delete MerchantMaster : {}", id);
        merchantMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("merchantMaster", id.toString())).build();
    }
}

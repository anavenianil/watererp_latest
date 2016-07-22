package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ChargeBase;
import com.callippus.water.erp.repository.ChargeBaseRepository;
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
 * REST controller for managing ChargeBase.
 */
@RestController
@RequestMapping("/api")
public class ChargeBaseResource {

    private final Logger log = LoggerFactory.getLogger(ChargeBaseResource.class);
        
    @Inject
    private ChargeBaseRepository chargeBaseRepository;
    
    /**
     * POST  /chargeBases -> Create a new chargeBase.
     */
    @RequestMapping(value = "/chargeBases",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChargeBase> createChargeBase(@Valid @RequestBody ChargeBase chargeBase) throws URISyntaxException {
        log.debug("REST request to save ChargeBase : {}", chargeBase);
        if (chargeBase.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("chargeBase", "idexists", "A new chargeBase cannot already have an ID")).body(null);
        }
        ChargeBase result = chargeBaseRepository.save(chargeBase);
        return ResponseEntity.created(new URI("/api/chargeBases/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("chargeBase", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chargeBases -> Updates an existing chargeBase.
     */
    @RequestMapping(value = "/chargeBases",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChargeBase> updateChargeBase(@Valid @RequestBody ChargeBase chargeBase) throws URISyntaxException {
        log.debug("REST request to update ChargeBase : {}", chargeBase);
        if (chargeBase.getId() == null) {
            return createChargeBase(chargeBase);
        }
        ChargeBase result = chargeBaseRepository.save(chargeBase);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("chargeBase", chargeBase.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chargeBases -> get all the chargeBases.
     */
    @RequestMapping(value = "/chargeBases",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ChargeBase>> getAllChargeBases(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ChargeBases");
        Page<ChargeBase> page = chargeBaseRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/chargeBases");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /chargeBases/:id -> get the "id" chargeBase.
     */
    @RequestMapping(value = "/chargeBases/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ChargeBase> getChargeBase(@PathVariable Long id) {
        log.debug("REST request to get ChargeBase : {}", id);
        ChargeBase chargeBase = chargeBaseRepository.findOne(id);
        return Optional.ofNullable(chargeBase)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /chargeBases/:id -> delete the "id" chargeBase.
     */
    @RequestMapping(value = "/chargeBases/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteChargeBase(@PathVariable Long id) {
        log.debug("REST request to delete ChargeBase : {}", id);
        chargeBaseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("chargeBase", id.toString())).build();
    }
}

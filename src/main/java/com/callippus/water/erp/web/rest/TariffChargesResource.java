package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.TariffCharges;
import com.callippus.water.erp.repository.TariffChargesRepository;
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
 * REST controller for managing TariffCharges.
 */
@RestController
@RequestMapping("/api")
public class TariffChargesResource {

    private final Logger log = LoggerFactory.getLogger(TariffChargesResource.class);
        
    @Inject
    private TariffChargesRepository tariffChargesRepository;
    
    /**
     * POST  /tariffChargess -> Create a new tariffCharges.
     */
    @RequestMapping(value = "/tariffChargess",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffCharges> createTariffCharges(@Valid @RequestBody TariffCharges tariffCharges) throws URISyntaxException {
        log.debug("REST request to save TariffCharges : {}", tariffCharges);
        if (tariffCharges.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("tariffCharges", "idexists", "A new tariffCharges cannot already have an ID")).body(null);
        }
        TariffCharges result = tariffChargesRepository.save(tariffCharges);
        return ResponseEntity.created(new URI("/api/tariffChargess/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("tariffCharges", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tariffChargess -> Updates an existing tariffCharges.
     */
    @RequestMapping(value = "/tariffChargess",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffCharges> updateTariffCharges(@Valid @RequestBody TariffCharges tariffCharges) throws URISyntaxException {
        log.debug("REST request to update TariffCharges : {}", tariffCharges);
        if (tariffCharges.getId() == null) {
            return createTariffCharges(tariffCharges);
        }
        TariffCharges result = tariffChargesRepository.save(tariffCharges);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("tariffCharges", tariffCharges.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tariffChargess -> get all the tariffChargess.
     */
    @RequestMapping(value = "/tariffChargess",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TariffCharges>> getAllTariffChargess(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of TariffChargess");
        Page<TariffCharges> page = tariffChargesRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariffChargess");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tariffChargess/:id -> get the "id" tariffCharges.
     */
    @RequestMapping(value = "/tariffChargess/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffCharges> getTariffCharges(@PathVariable Long id) {
        log.debug("REST request to get TariffCharges : {}", id);
        TariffCharges tariffCharges = tariffChargesRepository.findOne(id);
        return Optional.ofNullable(tariffCharges)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /tariffChargess/:id -> delete the "id" tariffCharges.
     */
    @RequestMapping(value = "/tariffChargess/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTariffCharges(@PathVariable Long id) {
        log.debug("REST request to delete TariffCharges : {}", id);
        tariffChargesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tariffCharges", id.toString())).build();
    }
}

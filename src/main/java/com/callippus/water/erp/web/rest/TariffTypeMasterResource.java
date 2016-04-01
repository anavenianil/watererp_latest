package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.TariffTypeMaster;
import com.callippus.water.erp.repository.TariffTypeMasterRepository;
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
 * REST controller for managing TariffTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class TariffTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(TariffTypeMasterResource.class);
        
    @Inject
    private TariffTypeMasterRepository tariffTypeMasterRepository;
    
    /**
     * POST  /tariffTypeMasters -> Create a new tariffTypeMaster.
     */
    @RequestMapping(value = "/tariffTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffTypeMaster> createTariffTypeMaster(@Valid @RequestBody TariffTypeMaster tariffTypeMaster) throws URISyntaxException {
        log.debug("REST request to save TariffTypeMaster : {}", tariffTypeMaster);
        if (tariffTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("tariffTypeMaster", "idexists", "A new tariffTypeMaster cannot already have an ID")).body(null);
        }
        TariffTypeMaster result = tariffTypeMasterRepository.save(tariffTypeMaster);
        return ResponseEntity.created(new URI("/api/tariffTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("tariffTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tariffTypeMasters -> Updates an existing tariffTypeMaster.
     */
    @RequestMapping(value = "/tariffTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffTypeMaster> updateTariffTypeMaster(@Valid @RequestBody TariffTypeMaster tariffTypeMaster) throws URISyntaxException {
        log.debug("REST request to update TariffTypeMaster : {}", tariffTypeMaster);
        if (tariffTypeMaster.getId() == null) {
            return createTariffTypeMaster(tariffTypeMaster);
        }
        TariffTypeMaster result = tariffTypeMasterRepository.save(tariffTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("tariffTypeMaster", tariffTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tariffTypeMasters -> get all the tariffTypeMasters.
     */
    @RequestMapping(value = "/tariffTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TariffTypeMaster>> getAllTariffTypeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of TariffTypeMasters");
        Page<TariffTypeMaster> page = tariffTypeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariffTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tariffTypeMasters/:id -> get the "id" tariffTypeMaster.
     */
    @RequestMapping(value = "/tariffTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffTypeMaster> getTariffTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get TariffTypeMaster : {}", id);
        TariffTypeMaster tariffTypeMaster = tariffTypeMasterRepository.findOne(id);
        return Optional.ofNullable(tariffTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /tariffTypeMasters/:id -> delete the "id" tariffTypeMaster.
     */
    @RequestMapping(value = "/tariffTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTariffTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete TariffTypeMaster : {}", id);
        tariffTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tariffTypeMaster", id.toString())).build();
    }
}

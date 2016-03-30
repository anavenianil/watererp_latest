package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.TariffMaster;
import com.callippus.water.erp.repository.TariffMasterRepository;
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
 * REST controller for managing TariffMaster.
 */
@RestController
@RequestMapping("/api")
public class TariffMasterResource {

    private final Logger log = LoggerFactory.getLogger(TariffMasterResource.class);
        
    @Inject
    private TariffMasterRepository tariffMasterRepository;
    
    /**
     * POST  /tariffMasters -> Create a new tariffMaster.
     */
    @RequestMapping(value = "/tariffMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffMaster> createTariffMaster(@Valid @RequestBody TariffMaster tariffMaster) throws URISyntaxException {
        log.debug("REST request to save TariffMaster : {}", tariffMaster);
        if (tariffMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("tariffMaster", "idexists", "A new tariffMaster cannot already have an ID")).body(null);
        }
        TariffMaster result = tariffMasterRepository.save(tariffMaster);
        return ResponseEntity.created(new URI("/api/tariffMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("tariffMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tariffMasters -> Updates an existing tariffMaster.
     */
    @RequestMapping(value = "/tariffMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffMaster> updateTariffMaster(@Valid @RequestBody TariffMaster tariffMaster) throws URISyntaxException {
        log.debug("REST request to update TariffMaster : {}", tariffMaster);
        if (tariffMaster.getId() == null) {
            return createTariffMaster(tariffMaster);
        }
        TariffMaster result = tariffMasterRepository.save(tariffMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("tariffMaster", tariffMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tariffMasters -> get all the tariffMasters.
     */
    @RequestMapping(value = "/tariffMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TariffMaster>> getAllTariffMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of TariffMasters");
        Page<TariffMaster> page = tariffMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariffMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tariffMasters/:id -> get the "id" tariffMaster.
     */
    @RequestMapping(value = "/tariffMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffMaster> getTariffMaster(@PathVariable Long id) {
        log.debug("REST request to get TariffMaster : {}", id);
        TariffMaster tariffMaster = tariffMasterRepository.findOne(id);
        return Optional.ofNullable(tariffMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /tariffMasters/:id -> delete the "id" tariffMaster.
     */
    @RequestMapping(value = "/tariffMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTariffMaster(@PathVariable Long id) {
        log.debug("REST request to delete TariffMaster : {}", id);
        tariffMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tariffMaster", id.toString())).build();
    }
}

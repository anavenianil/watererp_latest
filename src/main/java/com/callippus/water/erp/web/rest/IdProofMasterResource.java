package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.IdProofMaster;
import com.callippus.water.erp.repository.IdProofMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing IdProofMaster.
 */
@RestController
@RequestMapping("/api")
public class IdProofMasterResource {

    private final Logger log = LoggerFactory.getLogger(IdProofMasterResource.class);
        
    @Inject
    private IdProofMasterRepository idProofMasterRepository;
    
    /**
     * POST  /idProofMasters -> Create a new idProofMaster.
     */
    @RequestMapping(value = "/idProofMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<IdProofMaster> createIdProofMaster(@RequestBody IdProofMaster idProofMaster) throws URISyntaxException {
        log.debug("REST request to save IdProofMaster : {}", idProofMaster);
        if (idProofMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("idProofMaster", "idexists", "A new idProofMaster cannot already have an ID")).body(null);
        }
        IdProofMaster result = idProofMasterRepository.save(idProofMaster);
        return ResponseEntity.created(new URI("/api/idProofMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("idProofMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /idProofMasters -> Updates an existing idProofMaster.
     */
    @RequestMapping(value = "/idProofMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<IdProofMaster> updateIdProofMaster(@RequestBody IdProofMaster idProofMaster) throws URISyntaxException {
        log.debug("REST request to update IdProofMaster : {}", idProofMaster);
        if (idProofMaster.getId() == null) {
            return createIdProofMaster(idProofMaster);
        }
        IdProofMaster result = idProofMasterRepository.save(idProofMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("idProofMaster", idProofMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /idProofMasters -> get all the idProofMasters.
     */
    @RequestMapping(value = "/idProofMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<IdProofMaster> getAllIdProofMasters() {
        log.debug("REST request to get all IdProofMasters");
        return idProofMasterRepository.findAll();
            }

    /**
     * GET  /idProofMasters/:id -> get the "id" idProofMaster.
     */
    @RequestMapping(value = "/idProofMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<IdProofMaster> getIdProofMaster(@PathVariable Long id) {
        log.debug("REST request to get IdProofMaster : {}", id);
        IdProofMaster idProofMaster = idProofMasterRepository.findOne(id);
        return Optional.ofNullable(idProofMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /idProofMasters/:id -> delete the "id" idProofMaster.
     */
    @RequestMapping(value = "/idProofMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteIdProofMaster(@PathVariable Long id) {
        log.debug("REST request to delete IdProofMaster : {}", id);
        idProofMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("idProofMaster", id.toString())).build();
    }
}

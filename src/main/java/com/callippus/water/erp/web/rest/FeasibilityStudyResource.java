package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.FeasibilityStudy;
import com.callippus.water.erp.repository.FeasibilityStudyRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing FeasibilityStudy.
 */
@RestController
@RequestMapping("/api")
public class FeasibilityStudyResource {

    private final Logger log = LoggerFactory.getLogger(FeasibilityStudyResource.class);
        
    @Inject
    private FeasibilityStudyRepository feasibilityStudyRepository;
    
    /**
     * POST  /feasibilityStudys -> Create a new feasibilityStudy.
     */
    @RequestMapping(value = "/feasibilityStudys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FeasibilityStudy> createFeasibilityStudy(@RequestBody FeasibilityStudy feasibilityStudy) throws URISyntaxException {
        log.debug("REST request to save FeasibilityStudy : {}", feasibilityStudy);
        if (feasibilityStudy.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("feasibilityStudy", "idexists", "A new feasibilityStudy cannot already have an ID")).body(null);
        }
        ZonedDateTime now = ZonedDateTime.now();
        feasibilityStudy.setCreatedDate(now);
        feasibilityStudy.setModifiedDate(now);
        feasibilityStudy.setStatus(0);
        FeasibilityStudy result = feasibilityStudyRepository.save(feasibilityStudy);
        
        return ResponseEntity.created(new URI("/api/feasibilityStudys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("feasibilityStudy", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /feasibilityStudys -> Updates an existing feasibilityStudy.
     */
    @RequestMapping(value = "/feasibilityStudys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FeasibilityStudy> updateFeasibilityStudy(@RequestBody FeasibilityStudy feasibilityStudy) throws URISyntaxException {
        log.debug("REST request to update FeasibilityStudy : {}", feasibilityStudy);
        if (feasibilityStudy.getId() == null) {
            return createFeasibilityStudy(feasibilityStudy);
        }
        ZonedDateTime now = ZonedDateTime.now();
        feasibilityStudy.setModifiedDate(now);
        FeasibilityStudy result = feasibilityStudyRepository.save(feasibilityStudy);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("feasibilityStudy", feasibilityStudy.getId().toString()))
            .body(result);
    }

    /**
     * GET  /feasibilityStudys -> get all the feasibilityStudys.
     */
    @RequestMapping(value = "/feasibilityStudys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<FeasibilityStudy> getAllFeasibilityStudys() {
        log.debug("REST request to get all FeasibilityStudys");
        return feasibilityStudyRepository.findAll();
            }

    /**
     * GET  /feasibilityStudys/:id -> get the "id" feasibilityStudy.
     */
    @RequestMapping(value = "/feasibilityStudys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FeasibilityStudy> getFeasibilityStudy(@PathVariable Long id) {
        log.debug("REST request to get FeasibilityStudy : {}", id);
        FeasibilityStudy feasibilityStudy = feasibilityStudyRepository.findOne(id);
        return Optional.ofNullable(feasibilityStudy)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /feasibilityStudys/:id -> delete the "id" feasibilityStudy.
     */
    @RequestMapping(value = "/feasibilityStudys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFeasibilityStudy(@PathVariable Long id) {
        log.debug("REST request to delete FeasibilityStudy : {}", id);
        feasibilityStudyRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("feasibilityStudy", id.toString())).build();
    }
}

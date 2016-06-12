package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.Adjustments;
import com.callippus.water.erp.repository.AdjustmentsRepository;
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
 * REST controller for managing Adjustments.
 */
@RestController
@RequestMapping("/api")
public class AdjustmentsResource {

    private final Logger log = LoggerFactory.getLogger(AdjustmentsResource.class);
        
    @Inject
    private AdjustmentsRepository adjustmentsRepository;
    
    /**
     * POST  /adjustmentss -> Create a new adjustments.
     */
    @RequestMapping(value = "/adjustmentss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Adjustments> createAdjustments(@Valid @RequestBody Adjustments adjustments) throws URISyntaxException {
        log.debug("REST request to save Adjustments : {}", adjustments);
        if (adjustments.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("adjustments", "idexists", "A new adjustments cannot already have an ID")).body(null);
        }
        Adjustments result = adjustmentsRepository.save(adjustments);
        return ResponseEntity.created(new URI("/api/adjustmentss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("adjustments", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /adjustmentss -> Updates an existing adjustments.
     */
    @RequestMapping(value = "/adjustmentss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Adjustments> updateAdjustments(@Valid @RequestBody Adjustments adjustments) throws URISyntaxException {
        log.debug("REST request to update Adjustments : {}", adjustments);
        if (adjustments.getId() == null) {
            return createAdjustments(adjustments);
        }
        Adjustments result = adjustmentsRepository.save(adjustments);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("adjustments", adjustments.getId().toString()))
            .body(result);
    }

    /**
     * GET  /adjustmentss -> get all the adjustmentss.
     */
    @RequestMapping(value = "/adjustmentss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Adjustments>> getAllAdjustmentss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Adjustmentss");
        Page<Adjustments> page = adjustmentsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/adjustmentss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /adjustmentss/:id -> get the "id" adjustments.
     */
    @RequestMapping(value = "/adjustmentss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Adjustments> getAdjustments(@PathVariable Long id) {
        log.debug("REST request to get Adjustments : {}", id);
        Adjustments adjustments = adjustmentsRepository.findOne(id);
        return Optional.ofNullable(adjustments)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /adjustmentss/:id -> delete the "id" adjustments.
     */
    @RequestMapping(value = "/adjustmentss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteAdjustments(@PathVariable Long id) {
        log.debug("REST request to delete Adjustments : {}", id);
        adjustmentsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("adjustments", id.toString())).build();
    }
}

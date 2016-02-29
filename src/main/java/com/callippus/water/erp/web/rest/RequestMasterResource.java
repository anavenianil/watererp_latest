package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.RequestMaster;
import com.callippus.water.erp.repository.RequestMasterRepository;
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
 * REST controller for managing RequestMaster.
 */
@RestController
@RequestMapping("/api")
public class RequestMasterResource {

    private final Logger log = LoggerFactory.getLogger(RequestMasterResource.class);
        
    @Inject
    private RequestMasterRepository requestMasterRepository;
    
    /**
     * POST  /requestMasters -> Create a new requestMaster.
     */
    @RequestMapping(value = "/requestMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestMaster> createRequestMaster(@RequestBody RequestMaster requestMaster) throws URISyntaxException {
        log.debug("REST request to save RequestMaster : {}", requestMaster);
        if (requestMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("requestMaster", "idexists", "A new requestMaster cannot already have an ID")).body(null);
        }
        RequestMaster result = requestMasterRepository.save(requestMaster);
        return ResponseEntity.created(new URI("/api/requestMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("requestMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /requestMasters -> Updates an existing requestMaster.
     */
    @RequestMapping(value = "/requestMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestMaster> updateRequestMaster(@RequestBody RequestMaster requestMaster) throws URISyntaxException {
        log.debug("REST request to update RequestMaster : {}", requestMaster);
        if (requestMaster.getId() == null) {
            return createRequestMaster(requestMaster);
        }
        RequestMaster result = requestMasterRepository.save(requestMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("requestMaster", requestMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /requestMasters -> get all the requestMasters.
     */
    @RequestMapping(value = "/requestMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestMaster>> getAllRequestMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of RequestMasters");
        Page<RequestMaster> page = requestMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/requestMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /requestMasters/:id -> get the "id" requestMaster.
     */
    @RequestMapping(value = "/requestMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestMaster> getRequestMaster(@PathVariable Long id) {
        log.debug("REST request to get RequestMaster : {}", id);
        RequestMaster requestMaster = requestMasterRepository.findOne(id);
        return Optional.ofNullable(requestMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /requestMasters/:id -> delete the "id" requestMaster.
     */
    @RequestMapping(value = "/requestMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRequestMaster(@PathVariable Long id) {
        log.debug("REST request to delete RequestMaster : {}", id);
        requestMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("requestMaster", id.toString())).build();
    }
}

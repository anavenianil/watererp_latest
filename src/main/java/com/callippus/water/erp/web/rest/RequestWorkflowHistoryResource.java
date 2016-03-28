package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.repository.RequestWorkflowHistoryRepository;
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
 * REST controller for managing RequestWorkflowHistory.
 */
@RestController
@RequestMapping("/api")
public class RequestWorkflowHistoryResource {

    private final Logger log = LoggerFactory.getLogger(RequestWorkflowHistoryResource.class);
        
    @Inject
    private RequestWorkflowHistoryRepository requestWorkflowHistoryRepository;
    
    /**
     * POST  /requestWorkflowHistorys -> Create a new requestWorkflowHistory.
     */
    @RequestMapping(value = "/requestWorkflowHistorys",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestWorkflowHistory> createRequestWorkflowHistory(@RequestBody RequestWorkflowHistory requestWorkflowHistory) throws URISyntaxException {
        log.debug("REST request to save RequestWorkflowHistory : {}", requestWorkflowHistory);
        if (requestWorkflowHistory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("requestWorkflowHistory", "idexists", "A new requestWorkflowHistory cannot already have an ID")).body(null);
        }
        RequestWorkflowHistory result = requestWorkflowHistoryRepository.save(requestWorkflowHistory);
        return ResponseEntity.created(new URI("/api/requestWorkflowHistorys/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("requestWorkflowHistory", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /requestWorkflowHistorys -> Updates an existing requestWorkflowHistory.
     */
    @RequestMapping(value = "/requestWorkflowHistorys",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestWorkflowHistory> updateRequestWorkflowHistory(@RequestBody RequestWorkflowHistory requestWorkflowHistory) throws URISyntaxException {
        log.debug("REST request to update RequestWorkflowHistory : {}", requestWorkflowHistory);
        if (requestWorkflowHistory.getId() == null) {
            return createRequestWorkflowHistory(requestWorkflowHistory);
        }
        RequestWorkflowHistory result = requestWorkflowHistoryRepository.save(requestWorkflowHistory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("requestWorkflowHistory", requestWorkflowHistory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /requestWorkflowHistorys -> get all the requestWorkflowHistorys.
     */
    @RequestMapping(value = "/requestWorkflowHistorys",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestWorkflowHistory>> getAllRequestWorkflowHistorys(Pageable pageable,
    		@RequestParam(value = "dimainObjectId", required = false) Long domainObject)
        throws URISyntaxException {
        log.debug("REST request to get a page of RequestWorkflowHistorys");
        //Page<RequestWorkflowHistory> page = requestWorkflowHistoryRepository.findAll(pageable);
        Page<RequestWorkflowHistory> page;
        if(domainObject == null){
        	page = requestWorkflowHistoryRepository.findAll(pageable);
        }
        else
        {
        	page = requestWorkflowHistoryRepository.findByDomainObject(pageable, domainObject);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/requestWorkflowHistorys");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /requestWorkflowHistorys/:id -> get the "id" requestWorkflowHistory.
     */
    @RequestMapping(value = "/requestWorkflowHistorys/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<RequestWorkflowHistory> getRequestWorkflowHistory(@PathVariable Long id) {
        log.debug("REST request to get RequestWorkflowHistory : {}", id);
        RequestWorkflowHistory requestWorkflowHistory = requestWorkflowHistoryRepository.findOne(id);
        return Optional.ofNullable(requestWorkflowHistory)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /requestWorkflowHistorys/:id -> delete the "id" requestWorkflowHistory.
     */
    @RequestMapping(value = "/requestWorkflowHistorys/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteRequestWorkflowHistory(@PathVariable Long id) {
        log.debug("REST request to delete RequestWorkflowHistory : {}", id);
        requestWorkflowHistoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("requestWorkflowHistory", id.toString())).build();
    }
}

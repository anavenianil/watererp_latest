package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.OnlinePaymentCallback;
import com.callippus.water.erp.repository.OnlinePaymentCallbackRepository;
import com.callippus.water.erp.service.OnlinePaymentService;
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
 * REST controller for managing OnlinePaymentCallback.
 */
@RestController
@RequestMapping("/api")
public class OnlinePaymentCallbackResource {

    private final Logger log = LoggerFactory.getLogger(OnlinePaymentCallbackResource.class);
        
    @Inject
    private OnlinePaymentCallbackRepository onlinePaymentCallbackRepository;
    
    @Inject
    private OnlinePaymentService onlinePaymentService;
    
    /**
     * POST  /onlinePaymentCallbacks -> Create a new onlinePaymentCallback.
     */
    @RequestMapping(value = "/unifiedPGResponse",
        method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE},
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<String> createOnlinePaymentCallback(@RequestBody String xml) throws URISyntaxException {
    	
    	String response = onlinePaymentService.processPGResponse(xml);
        return ResponseEntity.ok().
        		body(response);
    }

    @RequestMapping(value = "/onlinePaymentCallbacks",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
        @Timed
        public ResponseEntity<OnlinePaymentCallback> createOnlinePaymentCallback(@RequestBody OnlinePaymentCallback onlinePaymentCallback) throws URISyntaxException {
            log.debug("REST request to save OnlinePaymentCallback : {}", onlinePaymentCallback);
            if (onlinePaymentCallback.getId() != null) {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("onlinePaymentCallback", "idexists", "A new onlinePaymentCallback cannot already have an ID")).body(null);
            }
            OnlinePaymentCallback result = onlinePaymentCallbackRepository.save(onlinePaymentCallback);
            return ResponseEntity.created(new URI("/api/onlinePaymentCallbacks/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("onlinePaymentCallback", result.getId().toString()))
                .body(result);
        }

    
    /**
     * PUT  /onlinePaymentCallbacks -> Updates an existing onlinePaymentCallback.
     */
    @RequestMapping(value = "/onlinePaymentCallbacks",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OnlinePaymentCallback> updateOnlinePaymentCallback(@RequestBody OnlinePaymentCallback onlinePaymentCallback) throws URISyntaxException {
        log.debug("REST request to update OnlinePaymentCallback : {}", onlinePaymentCallback);
        if (onlinePaymentCallback.getId() == null) {
            return createOnlinePaymentCallback(onlinePaymentCallback);
        }
        OnlinePaymentCallback result = onlinePaymentCallbackRepository.save(onlinePaymentCallback);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("onlinePaymentCallback", onlinePaymentCallback.getId().toString()))
            .body(result);
    }

    /**
     * GET  /onlinePaymentCallbacks -> get all the onlinePaymentCallbacks.
     */
    @RequestMapping(value = "/onlinePaymentCallbacks",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<OnlinePaymentCallback>> getAllOnlinePaymentCallbacks(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OnlinePaymentCallbacks");
        Page<OnlinePaymentCallback> page = onlinePaymentCallbackRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/onlinePaymentCallbacks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /onlinePaymentCallbacks/:id -> get the "id" onlinePaymentCallback.
     */
    @RequestMapping(value = "/onlinePaymentCallbacks/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OnlinePaymentCallback> getOnlinePaymentCallback(@PathVariable Long id) {
        log.debug("REST request to get OnlinePaymentCallback : {}", id);
        OnlinePaymentCallback onlinePaymentCallback = onlinePaymentCallbackRepository.findOne(id);
        return Optional.ofNullable(onlinePaymentCallback)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /onlinePaymentCallbacks/:id -> delete the "id" onlinePaymentCallback.
     */
    @RequestMapping(value = "/onlinePaymentCallbacks/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOnlinePaymentCallback(@PathVariable Long id) {
        log.debug("REST request to delete OnlinePaymentCallback : {}", id);
        onlinePaymentCallbackRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("onlinePaymentCallback", id.toString())).build();
    }
}

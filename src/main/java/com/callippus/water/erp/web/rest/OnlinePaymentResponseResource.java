package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.OnlinePaymentResponse;
import com.callippus.water.erp.repository.OnlinePaymentResponseRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing OnlinePaymentResponse.
 */
@RestController
@RequestMapping("/api")
public class OnlinePaymentResponseResource {

    private final Logger log = LoggerFactory.getLogger(OnlinePaymentResponseResource.class);
        
    @Inject
    private OnlinePaymentResponseRepository onlinePaymentResponseRepository;
    
    /**
     * POST  /onlinePaymentResponses -> Create a new onlinePaymentResponse.
     */
    @RequestMapping(value = "/onlinePaymentResponses",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OnlinePaymentResponse> createOnlinePaymentResponse(@RequestBody OnlinePaymentResponse onlinePaymentResponse) throws URISyntaxException {
        log.debug("REST request to save OnlinePaymentResponse : {}", onlinePaymentResponse);
        if (onlinePaymentResponse.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("onlinePaymentResponse", "idexists", "A new onlinePaymentResponse cannot already have an ID")).body(null);
        }
        OnlinePaymentResponse result = onlinePaymentResponseRepository.save(onlinePaymentResponse);
        return ResponseEntity.created(new URI("/api/onlinePaymentResponses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("onlinePaymentResponse", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /onlinePaymentResponses -> Updates an existing onlinePaymentResponse.
     */
    @RequestMapping(value = "/onlinePaymentResponses",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OnlinePaymentResponse> updateOnlinePaymentResponse(@RequestBody OnlinePaymentResponse onlinePaymentResponse) throws URISyntaxException {
        log.debug("REST request to update OnlinePaymentResponse : {}", onlinePaymentResponse);
        if (onlinePaymentResponse.getId() == null) {
            return createOnlinePaymentResponse(onlinePaymentResponse);
        }
        OnlinePaymentResponse result = onlinePaymentResponseRepository.save(onlinePaymentResponse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("onlinePaymentResponse", onlinePaymentResponse.getId().toString()))
            .body(result);
    }

    /**
     * GET  /onlinePaymentResponses -> get all the onlinePaymentResponses.
     */
    @RequestMapping(value = "/onlinePaymentResponses",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<OnlinePaymentResponse>> getAllOnlinePaymentResponses(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of OnlinePaymentResponses");
        Page<OnlinePaymentResponse> page = onlinePaymentResponseRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/onlinePaymentResponses");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /onlinePaymentResponses/:id -> get the "id" onlinePaymentResponse.
     */
    @RequestMapping(value = "/onlinePaymentResponses/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OnlinePaymentResponse> getOnlinePaymentResponse(@PathVariable Long id) {
        log.debug("REST request to get OnlinePaymentResponse : {}", id);
        OnlinePaymentResponse onlinePaymentResponse = onlinePaymentResponseRepository.findOne(id);
        return Optional.ofNullable(onlinePaymentResponse)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * DELETE  /onlinePaymentResponses/:id -> delete the "id" onlinePaymentResponse.
     */
    @RequestMapping(value = "/onlinePaymentResponses/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteOnlinePaymentResponse(@PathVariable Long id) {
        log.debug("REST request to delete OnlinePaymentResponse : {}", id);
        onlinePaymentResponseRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("onlinePaymentResponse", id.toString())).build();
    }
    

    @RequestMapping(value = "/onlinePaymentResponses", method = RequestMethod.GET,
            params = {"orderId"})
		    public ResponseEntity<OnlinePaymentResponse> getOnlinePaymentResponseByOrderId(@RequestParam(value = "orderId") Long orderId) {
		        log.debug("REST request to getOnlinePaymentResponse : {}, orderId: {}", orderId);
		        OnlinePaymentResponse opr = onlinePaymentResponseRepository.findByOrder(orderId);
		        return Optional.ofNullable(opr)
		            .map(result -> new ResponseEntity<>(
		                result,
		                HttpStatus.OK))
		            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		    }
        
    
}

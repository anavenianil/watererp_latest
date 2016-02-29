package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.PaymentTypes;
import com.callippus.water.erp.repository.PaymentTypesRepository;
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
 * REST controller for managing PaymentTypes.
 */
@RestController
@RequestMapping("/api")
public class PaymentTypesResource {

    private final Logger log = LoggerFactory.getLogger(PaymentTypesResource.class);
        
    @Inject
    private PaymentTypesRepository paymentTypesRepository;
    
    /**
     * POST  /paymentTypess -> Create a new paymentTypes.
     */
    @RequestMapping(value = "/paymentTypess",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTypes> createPaymentTypes(@Valid @RequestBody PaymentTypes paymentTypes) throws URISyntaxException {
        log.debug("REST request to save PaymentTypes : {}", paymentTypes);
        if (paymentTypes.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("paymentTypes", "idexists", "A new paymentTypes cannot already have an ID")).body(null);
        }
        PaymentTypes result = paymentTypesRepository.save(paymentTypes);
        return ResponseEntity.created(new URI("/api/paymentTypess/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("paymentTypes", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /paymentTypess -> Updates an existing paymentTypes.
     */
    @RequestMapping(value = "/paymentTypess",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTypes> updatePaymentTypes(@Valid @RequestBody PaymentTypes paymentTypes) throws URISyntaxException {
        log.debug("REST request to update PaymentTypes : {}", paymentTypes);
        if (paymentTypes.getId() == null) {
            return createPaymentTypes(paymentTypes);
        }
        PaymentTypes result = paymentTypesRepository.save(paymentTypes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("paymentTypes", paymentTypes.getId().toString()))
            .body(result);
    }

    /**
     * GET  /paymentTypess -> get all the paymentTypess.
     */
    @RequestMapping(value = "/paymentTypess",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<PaymentTypes>> getAllPaymentTypess(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of PaymentTypess");
        Page<PaymentTypes> page = paymentTypesRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/paymentTypess");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /paymentTypess/:id -> get the "id" paymentTypes.
     */
    @RequestMapping(value = "/paymentTypess/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PaymentTypes> getPaymentTypes(@PathVariable Long id) {
        log.debug("REST request to get PaymentTypes : {}", id);
        PaymentTypes paymentTypes = paymentTypesRepository.findOne(id);
        return Optional.ofNullable(paymentTypes)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /paymentTypess/:id -> delete the "id" paymentTypes.
     */
    @RequestMapping(value = "/paymentTypess/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePaymentTypes(@PathVariable Long id) {
        log.debug("REST request to delete PaymentTypes : {}", id);
        paymentTypesRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("paymentTypes", id.toString())).build();
    }
}

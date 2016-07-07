package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.InvoicePayments;
import com.callippus.water.erp.repository.InvoicePaymentsRepository;
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
 * REST controller for managing InvoicePayments.
 */
@RestController
@RequestMapping("/api")
public class InvoicePaymentsResource {

    private final Logger log = LoggerFactory.getLogger(InvoicePaymentsResource.class);
        
    @Inject
    private InvoicePaymentsRepository invoicePaymentsRepository;
    
    /**
     * POST  /invoicePaymentss -> Create a new invoicePayments.
     */
    @RequestMapping(value = "/invoicePaymentss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InvoicePayments> createInvoicePayments(@Valid @RequestBody InvoicePayments invoicePayments) throws URISyntaxException {
        log.debug("REST request to save InvoicePayments : {}", invoicePayments);
        if (invoicePayments.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("invoicePayments", "idexists", "A new invoicePayments cannot already have an ID")).body(null);
        }
        InvoicePayments result = invoicePaymentsRepository.save(invoicePayments);
        return ResponseEntity.created(new URI("/api/invoicePaymentss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("invoicePayments", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /invoicePaymentss -> Updates an existing invoicePayments.
     */
    @RequestMapping(value = "/invoicePaymentss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InvoicePayments> updateInvoicePayments(@Valid @RequestBody InvoicePayments invoicePayments) throws URISyntaxException {
        log.debug("REST request to update InvoicePayments : {}", invoicePayments);
        if (invoicePayments.getId() == null) {
            return createInvoicePayments(invoicePayments);
        }
        InvoicePayments result = invoicePaymentsRepository.save(invoicePayments);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("invoicePayments", invoicePayments.getId().toString()))
            .body(result);
    }

    /**
     * GET  /invoicePaymentss -> get all the invoicePaymentss.
     */
    @RequestMapping(value = "/invoicePaymentss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<InvoicePayments>> getAllInvoicePaymentss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of InvoicePaymentss");
        Page<InvoicePayments> page = invoicePaymentsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/invoicePaymentss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /invoicePaymentss/:id -> get the "id" invoicePayments.
     */
    @RequestMapping(value = "/invoicePaymentss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<InvoicePayments> getInvoicePayments(@PathVariable Long id) {
        log.debug("REST request to get InvoicePayments : {}", id);
        InvoicePayments invoicePayments = invoicePaymentsRepository.findOne(id);
        return Optional.ofNullable(invoicePayments)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /invoicePaymentss/:id -> delete the "id" invoicePayments.
     */
    @RequestMapping(value = "/invoicePaymentss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteInvoicePayments(@PathVariable Long id) {
        log.debug("REST request to delete InvoicePayments : {}", id);
        invoicePaymentsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("invoicePayments", id.toString())).build();
    }
}

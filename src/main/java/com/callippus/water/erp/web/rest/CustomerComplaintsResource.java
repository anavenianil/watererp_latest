package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.CustomerComplaints;
import com.callippus.water.erp.repository.CustomerComplaintsRepository;
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
 * REST controller for managing CustomerComplaints.
 */
@RestController
@RequestMapping("/api")
public class CustomerComplaintsResource {

    private final Logger log = LoggerFactory.getLogger(CustomerComplaintsResource.class);
        
    @Inject
    private CustomerComplaintsRepository customerComplaintsRepository;
    
    /**
     * POST  /customerComplaintss -> Create a new customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustomerComplaints> createCustomerComplaints(@RequestBody CustomerComplaints customerComplaints) throws URISyntaxException {
        log.debug("REST request to save CustomerComplaints : {}", customerComplaints);
        if (customerComplaints.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("customerComplaints", "idexists", "A new customerComplaints cannot already have an ID")).body(null);
        }
        CustomerComplaints result = customerComplaintsRepository.save(customerComplaints);
        return ResponseEntity.created(new URI("/api/customerComplaintss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("customerComplaints", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customerComplaintss -> Updates an existing customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustomerComplaints> updateCustomerComplaints(@RequestBody CustomerComplaints customerComplaints) throws URISyntaxException {
        log.debug("REST request to update CustomerComplaints : {}", customerComplaints);
        if (customerComplaints.getId() == null) {
            return createCustomerComplaints(customerComplaints);
        }
        CustomerComplaints result = customerComplaintsRepository.save(customerComplaints);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("customerComplaints", customerComplaints.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customerComplaintss -> get all the customerComplaintss.
     */
    @RequestMapping(value = "/customerComplaintss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CustomerComplaints>> getAllCustomerComplaintss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of CustomerComplaintss");
        Page<CustomerComplaints> page = customerComplaintsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customerComplaintss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customerComplaintss/:id -> get the "id" customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustomerComplaints> getCustomerComplaints(@PathVariable Long id) {
        log.debug("REST request to get CustomerComplaints : {}", id);
        CustomerComplaints customerComplaints = customerComplaintsRepository.findOne(id);
        return Optional.ofNullable(customerComplaints)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /customerComplaintss/:id -> delete the "id" customerComplaints.
     */
    @RequestMapping(value = "/customerComplaintss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCustomerComplaints(@PathVariable Long id) {
        log.debug("REST request to delete CustomerComplaints : {}", id);
        customerComplaintsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("customerComplaints", id.toString())).build();
    }
}
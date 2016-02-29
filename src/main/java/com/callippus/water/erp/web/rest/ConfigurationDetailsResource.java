package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
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
 * REST controller for managing ConfigurationDetails.
 */
@RestController
@RequestMapping("/api")
public class ConfigurationDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ConfigurationDetailsResource.class);
        
    @Inject
    private ConfigurationDetailsRepository configurationDetailsRepository;
    
    /**
     * POST  /configurationDetailss -> Create a new configurationDetails.
     */
    @RequestMapping(value = "/configurationDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ConfigurationDetails> createConfigurationDetails(@Valid @RequestBody ConfigurationDetails configurationDetails) throws URISyntaxException {
        log.debug("REST request to save ConfigurationDetails : {}", configurationDetails);
        if (configurationDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("configurationDetails", "idexists", "A new configurationDetails cannot already have an ID")).body(null);
        }
        ConfigurationDetails result = configurationDetailsRepository.save(configurationDetails);
        return ResponseEntity.created(new URI("/api/configurationDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("configurationDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /configurationDetailss -> Updates an existing configurationDetails.
     */
    @RequestMapping(value = "/configurationDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ConfigurationDetails> updateConfigurationDetails(@Valid @RequestBody ConfigurationDetails configurationDetails) throws URISyntaxException {
        log.debug("REST request to update ConfigurationDetails : {}", configurationDetails);
        if (configurationDetails.getId() == null) {
            return createConfigurationDetails(configurationDetails);
        }
        ConfigurationDetails result = configurationDetailsRepository.save(configurationDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("configurationDetails", configurationDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /configurationDetailss -> get all the configurationDetailss.
     */
    @RequestMapping(value = "/configurationDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ConfigurationDetails>> getAllConfigurationDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ConfigurationDetailss");
        Page<ConfigurationDetails> page = configurationDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/configurationDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /configurationDetailss/:id -> get the "id" configurationDetails.
     */
    @RequestMapping(value = "/configurationDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ConfigurationDetails> getConfigurationDetails(@PathVariable Long id) {
        log.debug("REST request to get ConfigurationDetails : {}", id);
        ConfigurationDetails configurationDetails = configurationDetailsRepository.findOne(id);
        return Optional.ofNullable(configurationDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /configurationDetailss/:id -> delete the "id" configurationDetails.
     */
    @RequestMapping(value = "/configurationDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteConfigurationDetails(@PathVariable Long id) {
        log.debug("REST request to delete ConfigurationDetails : {}", id);
        configurationDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("configurationDetails", id.toString())).build();
    }
}

package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.ItemRequired;
import com.callippus.water.erp.domain.Proceedings;
import com.callippus.water.erp.repository.ApplicationTxnRepository;
import com.callippus.water.erp.repository.ItemRequiredRepository;
import com.callippus.water.erp.repository.ProceedingsRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.applicationtxn.service.ApplicationTxnWorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Proceedings.
 */
@RestController
@RequestMapping("/api")
public class ProceedingsResource {

    private final Logger log = LoggerFactory.getLogger(ProceedingsResource.class);
        
    @Inject
    private ProceedingsRepository proceedingsRepository;
    
    @Inject
    private ItemRequiredRepository itemRequiredRepository;
    
    @Inject
    private ApplicationTxnRepository applicationTxnRepository;
    
    @Inject
    private ApplicationTxnWorkflowService applicationTxnWorkflowService;
    
    
    /**
     * POST  /proceedingss -> Create a new proceedings.
     */
    @RequestMapping(value = "/proceedingss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Proceedings> createProceedings(@RequestBody Proceedings proceedings) throws URISyntaxException {
        log.debug("REST request to save Proceedings : {}", proceedings);
        if (proceedings.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("proceedings", "idexists", "A new proceedings cannot already have an ID")).body(null);
        }
        ApplicationTxn applicationTxn = proceedings.getApplicationTxn();
        List<ItemRequired> itemRequireds = proceedings.getItemRequireds();
        Iterator<ItemRequired> iterator = itemRequireds.iterator();
        while(iterator.hasNext()){
          iterator.next().setApplicationTxn(applicationTxn);
        }
        
        Proceedings result = proceedingsRepository.save(proceedings);
        try{
        	applicationTxnWorkflowService.approveRequest(applicationTxn.getId(), applicationTxn.getRemarks());
        }
        catch(Exception e){
        	System.out.println(e);
        }
        return ResponseEntity.created(new URI("/api/proceedingss/" + result.getId()))
            .headers(HeaderUtil.createAlert("New Proceedings initiated", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /proceedingss -> Updates an existing proceedings.
     */
    @RequestMapping(value = "/proceedingss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Proceedings> updateProceedings(@RequestBody Proceedings proceedings) throws URISyntaxException {
        log.debug("REST request to update Proceedings : {}", proceedings);
        if (proceedings.getId() == null) {
            return createProceedings(proceedings);
        }
        Proceedings result = proceedingsRepository.save(proceedings);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("proceedings", proceedings.getId().toString()))
            .body(result);
    }

    /**
     * GET  /proceedingss -> get all the proceedingss.
     */
    @RequestMapping(value = "/proceedingss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Proceedings>> getAllProceedingss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Proceedingss");
        Page<Proceedings> page = proceedingsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/proceedingss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /proceedingss/:id -> get the "id" proceedings.
     */
    @RequestMapping(value = "/proceedingss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Proceedings> getProceedings(@PathVariable Long id) {
        log.debug("REST request to get Proceedings : {}", id);
        Proceedings proceedings = proceedingsRepository.findOne(id);
        return Optional.ofNullable(proceedings)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /proceedingss/:id -> delete the "id" proceedings.
     */
    @RequestMapping(value = "/proceedingss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteProceedings(@PathVariable Long id) {
        log.debug("REST request to delete Proceedings : {}", id);
        proceedingsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("proceedings", id.toString())).build();
    }
    
    
    /**
     * GET  /proceedingss/:applicationTxn -> get the "applicationTxn" proceedings.
     */
    @RequestMapping(value = "/proceedingss/custom/{applicationTxnId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Proceedings> getProceedingsByApplicationTxn(@PathVariable Long applicationTxnId) {
        log.debug("REST request to get Proceedings : {}", applicationTxnId);
        ApplicationTxn applicationTxn = applicationTxnRepository.getOne(applicationTxnId);
        Proceedings proceedings = proceedingsRepository.findByApplicationTxn(applicationTxn);
        List<ItemRequired> itemRequireds = itemRequiredRepository.findByProceedings(proceedings);
        proceedings.setItemRequireds(itemRequireds);
        return Optional.ofNullable(proceedings)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * GET  /feasibilityStudys/:applicationTxnId -> get the "applicationTxn" feasibilityStudy.
     */
    @RequestMapping(value = "/proceedingss/forAppTxn/{applicationTxnId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Proceedings> getProceedingsByApplication(@PathVariable Long applicationTxnId) {
        log.debug("REST request to get Proceedings : {}", applicationTxnId);
        ApplicationTxn applicationTxn = applicationTxnRepository.getOne(applicationTxnId);
        Proceedings proceedings = proceedingsRepository.findByApplicationTxn(applicationTxn);
        return Optional.ofNullable(proceedings)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

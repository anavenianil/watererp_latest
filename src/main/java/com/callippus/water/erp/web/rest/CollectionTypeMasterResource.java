package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.CollectionTypeMaster;
import com.callippus.water.erp.domain.enumeration.TransactionType;
import com.callippus.water.erp.repository.CollectionTypeMasterCustomRepository;
import com.callippus.water.erp.repository.CollectionTypeMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing CollectionTypeMaster.
 */
@RestController
@RequestMapping("/api")
public class CollectionTypeMasterResource {

    private final Logger log = LoggerFactory.getLogger(CollectionTypeMasterResource.class);
        
    @Inject
    private CollectionTypeMasterRepository collectionTypeMasterRepository;
    
    @Inject
    private CollectionTypeMasterCustomRepository collectionTypeMasterCustomRepository;
    
    /**
     * POST  /collectionTypeMasters -> Create a new collectionTypeMaster.
     */
    @RequestMapping(value = "/collectionTypeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CollectionTypeMaster> createCollectionTypeMaster(@RequestBody CollectionTypeMaster collectionTypeMaster) throws URISyntaxException {
        log.debug("REST request to save CollectionTypeMaster : {}", collectionTypeMaster);
        if (collectionTypeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("collectionTypeMaster", "idexists", "A new collectionTypeMaster cannot already have an ID")).body(null);
        }
        CollectionTypeMaster result = collectionTypeMasterRepository.save(collectionTypeMaster);
        return ResponseEntity.created(new URI("/api/collectionTypeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("collectionTypeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /collectionTypeMasters -> Updates an existing collectionTypeMaster.
     */
    @RequestMapping(value = "/collectionTypeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CollectionTypeMaster> updateCollectionTypeMaster(@RequestBody CollectionTypeMaster collectionTypeMaster) throws URISyntaxException {
        log.debug("REST request to update CollectionTypeMaster : {}", collectionTypeMaster);
        if (collectionTypeMaster.getId() == null) {
            return createCollectionTypeMaster(collectionTypeMaster);
        }
        CollectionTypeMaster result = collectionTypeMasterRepository.save(collectionTypeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("collectionTypeMaster", collectionTypeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /collectionTypeMasters -> get all the collectionTypeMasters.
     */
    @RequestMapping(value = "/collectionTypeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CollectionTypeMaster>> getAllCollectionTypeMasters(Pageable pageable,
    		@RequestParam(value = "txnType", required = false) TransactionType txnType)
        throws URISyntaxException {
        log.debug("REST request to get a page of CollectionTypeMasters");
        //Page<CollectionTypeMaster> page = collectionTypeMasterRepository.findAll(pageable); 
        Page<CollectionTypeMaster> page;
        if(txnType == null){
        	 page = collectionTypeMasterRepository.findAll(pageable);
        }
        else{
        	page = collectionTypeMasterRepository.findByTxnType(pageable, txnType);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/collectionTypeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /collectionTypeMasters/:id -> get the "id" collectionTypeMaster.
     */
    @RequestMapping(value = "/collectionTypeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CollectionTypeMaster> getCollectionTypeMaster(@PathVariable Long id) {
        log.debug("REST request to get CollectionTypeMaster : {}", id);
        CollectionTypeMaster collectionTypeMaster = collectionTypeMasterRepository.findOne(id);
        return Optional.ofNullable(collectionTypeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * GET  /custDetailss/searchCAN/:searchTerm
     */
    @RequestMapping(value = "/collectionTypeMasters/searchEXP/{searchTerm}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CollectionTypeMaster>> searchEXPLike(@PathVariable String searchTerm) {
        log.debug("REST request to get CollectionTypeMasters : {}", searchTerm);
        List<CollectionTypeMaster> expList = collectionTypeMasterCustomRepository.searchEXP(searchTerm);
        return Optional.ofNullable(expList)
            .map(result -> new ResponseEntity<>(
            		expList,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * DELETE  /collectionTypeMasters/:id -> delete the "id" collectionTypeMaster.
     */
    @RequestMapping(value = "/collectionTypeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCollectionTypeMaster(@PathVariable Long id) {
        log.debug("REST request to delete CollectionTypeMaster : {}", id);
        collectionTypeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("collectionTypeMaster", id.toString())).build();
    }
}

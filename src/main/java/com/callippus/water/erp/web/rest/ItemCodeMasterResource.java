package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemCodeMaster;
import com.callippus.water.erp.repository.ItemCodeMasterRepository;
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
 * REST controller for managing ItemCodeMaster.
 */
@RestController
@RequestMapping("/api")
public class ItemCodeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ItemCodeMasterResource.class);
        
    @Inject
    private ItemCodeMasterRepository itemCodeMasterRepository;
    
    /**
     * POST  /itemCodeMasters -> Create a new itemCodeMaster.
     */
    @RequestMapping(value = "/itemCodeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCodeMaster> createItemCodeMaster(@RequestBody ItemCodeMaster itemCodeMaster) throws URISyntaxException {
        log.debug("REST request to save ItemCodeMaster : {}", itemCodeMaster);
        if (itemCodeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemCodeMaster", "idexists", "A new itemCodeMaster cannot already have an ID")).body(null);
        }
        ItemCodeMaster result = itemCodeMasterRepository.save(itemCodeMaster);
        return ResponseEntity.created(new URI("/api/itemCodeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemCodeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemCodeMasters -> Updates an existing itemCodeMaster.
     */
    @RequestMapping(value = "/itemCodeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCodeMaster> updateItemCodeMaster(@RequestBody ItemCodeMaster itemCodeMaster) throws URISyntaxException {
        log.debug("REST request to update ItemCodeMaster : {}", itemCodeMaster);
        if (itemCodeMaster.getId() == null) {
            return createItemCodeMaster(itemCodeMaster);
        }
        ItemCodeMaster result = itemCodeMasterRepository.save(itemCodeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemCodeMaster", itemCodeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemCodeMasters -> get all the itemCodeMasters.
     */
    @RequestMapping(value = "/itemCodeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemCodeMaster>> getAllItemCodeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemCodeMasters");
        Page<ItemCodeMaster> page = itemCodeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemCodeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemCodeMasters/:id -> get the "id" itemCodeMaster.
     */
    @RequestMapping(value = "/itemCodeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCodeMaster> getItemCodeMaster(@PathVariable Long id) {
        log.debug("REST request to get ItemCodeMaster : {}", id);
        ItemCodeMaster itemCodeMaster = itemCodeMasterRepository.findOne(id);
        return Optional.ofNullable(itemCodeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemCodeMasters/:id -> delete the "id" itemCodeMaster.
     */
    @RequestMapping(value = "/itemCodeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemCodeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ItemCodeMaster : {}", id);
        itemCodeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemCodeMaster", id.toString())).build();
    }
}

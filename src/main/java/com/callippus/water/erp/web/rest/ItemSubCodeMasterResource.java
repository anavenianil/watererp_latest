package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemSubCodeMaster;
import com.callippus.water.erp.repository.ItemSubCodeMasterRepository;
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
 * REST controller for managing ItemSubCodeMaster.
 */
@RestController
@RequestMapping("/api")
public class ItemSubCodeMasterResource {

    private final Logger log = LoggerFactory.getLogger(ItemSubCodeMasterResource.class);
        
    @Inject
    private ItemSubCodeMasterRepository itemSubCodeMasterRepository;
    
    /**
     * POST  /itemSubCodeMasters -> Create a new itemSubCodeMaster.
     */
    @RequestMapping(value = "/itemSubCodeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemSubCodeMaster> createItemSubCodeMaster(@Valid @RequestBody ItemSubCodeMaster itemSubCodeMaster) throws URISyntaxException {
        log.debug("REST request to save ItemSubCodeMaster : {}", itemSubCodeMaster);
        if (itemSubCodeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemSubCodeMaster", "idexists", "A new itemSubCodeMaster cannot already have an ID")).body(null);
        }
        ItemSubCodeMaster result = itemSubCodeMasterRepository.save(itemSubCodeMaster);
        return ResponseEntity.created(new URI("/api/itemSubCodeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemSubCodeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemSubCodeMasters -> Updates an existing itemSubCodeMaster.
     */
    @RequestMapping(value = "/itemSubCodeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemSubCodeMaster> updateItemSubCodeMaster(@Valid @RequestBody ItemSubCodeMaster itemSubCodeMaster) throws URISyntaxException {
        log.debug("REST request to update ItemSubCodeMaster : {}", itemSubCodeMaster);
        if (itemSubCodeMaster.getId() == null) {
            return createItemSubCodeMaster(itemSubCodeMaster);
        }
        ItemSubCodeMaster result = itemSubCodeMasterRepository.save(itemSubCodeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemSubCodeMaster", itemSubCodeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemSubCodeMasters -> get all the itemSubCodeMasters.
     */
    @RequestMapping(value = "/itemSubCodeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemSubCodeMaster>> getAllItemSubCodeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemSubCodeMasters");
        Page<ItemSubCodeMaster> page = itemSubCodeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemSubCodeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemSubCodeMasters/:id -> get the "id" itemSubCodeMaster.
     */
    @RequestMapping(value = "/itemSubCodeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemSubCodeMaster> getItemSubCodeMaster(@PathVariable Long id) {
        log.debug("REST request to get ItemSubCodeMaster : {}", id);
        ItemSubCodeMaster itemSubCodeMaster = itemSubCodeMasterRepository.findOne(id);
        return Optional.ofNullable(itemSubCodeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemSubCodeMasters/:id -> delete the "id" itemSubCodeMaster.
     */
    @RequestMapping(value = "/itemSubCodeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemSubCodeMaster(@PathVariable Long id) {
        log.debug("REST request to delete ItemSubCodeMaster : {}", id);
        itemSubCodeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemSubCodeMaster", id.toString())).build();
    }
}

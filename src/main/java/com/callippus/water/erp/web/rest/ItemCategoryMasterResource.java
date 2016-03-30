package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemCategoryMaster;
import com.callippus.water.erp.repository.ItemCategoryMasterRepository;
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
 * REST controller for managing ItemCategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class ItemCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(ItemCategoryMasterResource.class);
        
    @Inject
    private ItemCategoryMasterRepository itemCategoryMasterRepository;
    
    /**
     * POST  /itemCategoryMasters -> Create a new itemCategoryMaster.
     */
    @RequestMapping(value = "/itemCategoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCategoryMaster> createItemCategoryMaster(@RequestBody ItemCategoryMaster itemCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save ItemCategoryMaster : {}", itemCategoryMaster);
        if (itemCategoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemCategoryMaster", "idexists", "A new itemCategoryMaster cannot already have an ID")).body(null);
        }
        ItemCategoryMaster result = itemCategoryMasterRepository.save(itemCategoryMaster);
        return ResponseEntity.created(new URI("/api/itemCategoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemCategoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemCategoryMasters -> Updates an existing itemCategoryMaster.
     */
    @RequestMapping(value = "/itemCategoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCategoryMaster> updateItemCategoryMaster(@RequestBody ItemCategoryMaster itemCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update ItemCategoryMaster : {}", itemCategoryMaster);
        if (itemCategoryMaster.getId() == null) {
            return createItemCategoryMaster(itemCategoryMaster);
        }
        ItemCategoryMaster result = itemCategoryMasterRepository.save(itemCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemCategoryMaster", itemCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemCategoryMasters -> get all the itemCategoryMasters.
     */
    @RequestMapping(value = "/itemCategoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemCategoryMaster>> getAllItemCategoryMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemCategoryMasters");
        Page<ItemCategoryMaster> page = itemCategoryMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemCategoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemCategoryMasters/:id -> get the "id" itemCategoryMaster.
     */
    @RequestMapping(value = "/itemCategoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCategoryMaster> getItemCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get ItemCategoryMaster : {}", id);
        ItemCategoryMaster itemCategoryMaster = itemCategoryMasterRepository.findOne(id);
        return Optional.ofNullable(itemCategoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemCategoryMasters/:id -> delete the "id" itemCategoryMaster.
     */
    @RequestMapping(value = "/itemCategoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete ItemCategoryMaster : {}", id);
        itemCategoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemCategoryMaster", id.toString())).build();
    }
}

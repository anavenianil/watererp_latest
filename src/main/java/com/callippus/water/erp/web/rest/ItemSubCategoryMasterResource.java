package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemSubCategoryMaster;
import com.callippus.water.erp.repository.ItemSubCategoryMasterRepository;
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
 * REST controller for managing ItemSubCategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class ItemSubCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(ItemSubCategoryMasterResource.class);
        
    @Inject
    private ItemSubCategoryMasterRepository itemSubCategoryMasterRepository;
    
    /**
     * POST  /itemSubCategoryMasters -> Create a new itemSubCategoryMaster.
     */
    @RequestMapping(value = "/itemSubCategoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemSubCategoryMaster> createItemSubCategoryMaster(@RequestBody ItemSubCategoryMaster itemSubCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save ItemSubCategoryMaster : {}", itemSubCategoryMaster);
        if (itemSubCategoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemSubCategoryMaster", "idexists", "A new itemSubCategoryMaster cannot already have an ID")).body(null);
        }
        ItemSubCategoryMaster result = itemSubCategoryMasterRepository.save(itemSubCategoryMaster);
        return ResponseEntity.created(new URI("/api/itemSubCategoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemSubCategoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemSubCategoryMasters -> Updates an existing itemSubCategoryMaster.
     */
    @RequestMapping(value = "/itemSubCategoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemSubCategoryMaster> updateItemSubCategoryMaster(@RequestBody ItemSubCategoryMaster itemSubCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update ItemSubCategoryMaster : {}", itemSubCategoryMaster);
        if (itemSubCategoryMaster.getId() == null) {
            return createItemSubCategoryMaster(itemSubCategoryMaster);
        }
        ItemSubCategoryMaster result = itemSubCategoryMasterRepository.save(itemSubCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemSubCategoryMaster", itemSubCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemSubCategoryMasters -> get all the itemSubCategoryMasters.
     */
    @RequestMapping(value = "/itemSubCategoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemSubCategoryMaster>> getAllItemSubCategoryMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemSubCategoryMasters");
        Page<ItemSubCategoryMaster> page = itemSubCategoryMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemSubCategoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemSubCategoryMasters/:id -> get the "id" itemSubCategoryMaster.
     */
    @RequestMapping(value = "/itemSubCategoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemSubCategoryMaster> getItemSubCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get ItemSubCategoryMaster : {}", id);
        ItemSubCategoryMaster itemSubCategoryMaster = itemSubCategoryMasterRepository.findOne(id);
        return Optional.ofNullable(itemSubCategoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemSubCategoryMasters/:id -> delete the "id" itemSubCategoryMaster.
     */
    @RequestMapping(value = "/itemSubCategoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemSubCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete ItemSubCategoryMaster : {}", id);
        itemSubCategoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemSubCategoryMaster", id.toString())).build();
    }
}

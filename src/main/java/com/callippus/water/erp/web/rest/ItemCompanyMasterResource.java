package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemCompanyMaster;
import com.callippus.water.erp.repository.ItemCompanyMasterRepository;
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
 * REST controller for managing ItemCompanyMaster.
 */
@RestController
@RequestMapping("/api")
public class ItemCompanyMasterResource {

    private final Logger log = LoggerFactory.getLogger(ItemCompanyMasterResource.class);
        
    @Inject
    private ItemCompanyMasterRepository itemCompanyMasterRepository;
    
    /**
     * POST  /itemCompanyMasters -> Create a new itemCompanyMaster.
     */
    @RequestMapping(value = "/itemCompanyMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCompanyMaster> createItemCompanyMaster(@RequestBody ItemCompanyMaster itemCompanyMaster) throws URISyntaxException {
        log.debug("REST request to save ItemCompanyMaster : {}", itemCompanyMaster);
        if (itemCompanyMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemCompanyMaster", "idexists", "A new itemCompanyMaster cannot already have an ID")).body(null);
        }
        ItemCompanyMaster result = itemCompanyMasterRepository.save(itemCompanyMaster);
        return ResponseEntity.created(new URI("/api/itemCompanyMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemCompanyMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemCompanyMasters -> Updates an existing itemCompanyMaster.
     */
    @RequestMapping(value = "/itemCompanyMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCompanyMaster> updateItemCompanyMaster(@RequestBody ItemCompanyMaster itemCompanyMaster) throws URISyntaxException {
        log.debug("REST request to update ItemCompanyMaster : {}", itemCompanyMaster);
        if (itemCompanyMaster.getId() == null) {
            return createItemCompanyMaster(itemCompanyMaster);
        }
        ItemCompanyMaster result = itemCompanyMasterRepository.save(itemCompanyMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemCompanyMaster", itemCompanyMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemCompanyMasters -> get all the itemCompanyMasters.
     */
    @RequestMapping(value = "/itemCompanyMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemCompanyMaster>> getAllItemCompanyMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemCompanyMasters");
        Page<ItemCompanyMaster> page = itemCompanyMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemCompanyMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemCompanyMasters/:id -> get the "id" itemCompanyMaster.
     */
    @RequestMapping(value = "/itemCompanyMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemCompanyMaster> getItemCompanyMaster(@PathVariable Long id) {
        log.debug("REST request to get ItemCompanyMaster : {}", id);
        ItemCompanyMaster itemCompanyMaster = itemCompanyMasterRepository.findOne(id);
        return Optional.ofNullable(itemCompanyMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemCompanyMasters/:id -> delete the "id" itemCompanyMaster.
     */
    @RequestMapping(value = "/itemCompanyMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemCompanyMaster(@PathVariable Long id) {
        log.debug("REST request to delete ItemCompanyMaster : {}", id);
        itemCompanyMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemCompanyMaster", id.toString())).build();
    }
}

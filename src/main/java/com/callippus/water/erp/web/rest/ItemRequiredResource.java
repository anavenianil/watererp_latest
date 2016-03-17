package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemRequired;
import com.callippus.water.erp.repository.ItemRequiredRepository;
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
 * REST controller for managing ItemRequired.
 */
@RestController
@RequestMapping("/api")
public class ItemRequiredResource {

    private final Logger log = LoggerFactory.getLogger(ItemRequiredResource.class);
        
    @Inject
    private ItemRequiredRepository itemRequiredRepository;
    
    /**
     * POST  /itemRequireds -> Create a new itemRequired.
     */
    @RequestMapping(value = "/itemRequireds",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemRequired> createItemRequired(@RequestBody ItemRequired itemRequired) throws URISyntaxException {
        log.debug("REST request to save ItemRequired : {}", itemRequired);
        if (itemRequired.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemRequired", "idexists", "A new itemRequired cannot already have an ID")).body(null);
        }
        ItemRequired result = itemRequiredRepository.save(itemRequired);
        return ResponseEntity.created(new URI("/api/itemRequireds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemRequired", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemRequireds -> Updates an existing itemRequired.
     */
    @RequestMapping(value = "/itemRequireds",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemRequired> updateItemRequired(@RequestBody ItemRequired itemRequired) throws URISyntaxException {
        log.debug("REST request to update ItemRequired : {}", itemRequired);
        if (itemRequired.getId() == null) {
            return createItemRequired(itemRequired);
        }
        ItemRequired result = itemRequiredRepository.save(itemRequired);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemRequired", itemRequired.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemRequireds -> get all the itemRequireds.
     */
    @RequestMapping(value = "/itemRequireds",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemRequired>> getAllItemRequireds(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemRequireds");
        Page<ItemRequired> page = itemRequiredRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemRequireds");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemRequireds/:id -> get the "id" itemRequired.
     */
    @RequestMapping(value = "/itemRequireds/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemRequired> getItemRequired(@PathVariable Long id) {
        log.debug("REST request to get ItemRequired : {}", id);
        ItemRequired itemRequired = itemRequiredRepository.findOne(id);
        return Optional.ofNullable(itemRequired)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemRequireds/:id -> delete the "id" itemRequired.
     */
    @RequestMapping(value = "/itemRequireds/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemRequired(@PathVariable Long id) {
        log.debug("REST request to delete ItemRequired : {}", id);
        itemRequiredRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemRequired", id.toString())).build();
    }
}

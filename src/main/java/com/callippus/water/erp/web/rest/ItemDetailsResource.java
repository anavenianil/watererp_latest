package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.ItemDetails;
import com.callippus.water.erp.repository.ItemDetailsRepository;
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
 * REST controller for managing ItemDetails.
 */
@RestController
@RequestMapping("/api")
public class ItemDetailsResource {

    private final Logger log = LoggerFactory.getLogger(ItemDetailsResource.class);
        
    @Inject
    private ItemDetailsRepository itemDetailsRepository;
    
    /**
     * POST  /itemDetailss -> Create a new itemDetails.
     */
    @RequestMapping(value = "/itemDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemDetails> createItemDetails(@RequestBody ItemDetails itemDetails) throws URISyntaxException {
        log.debug("REST request to save ItemDetails : {}", itemDetails);
        if (itemDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("itemDetails", "idexists", "A new itemDetails cannot already have an ID")).body(null);
        }
        ItemDetails result = itemDetailsRepository.save(itemDetails);
        return ResponseEntity.created(new URI("/api/itemDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("itemDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /itemDetailss -> Updates an existing itemDetails.
     */
    @RequestMapping(value = "/itemDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemDetails> updateItemDetails(@RequestBody ItemDetails itemDetails) throws URISyntaxException {
        log.debug("REST request to update ItemDetails : {}", itemDetails);
        if (itemDetails.getId() == null) {
            return createItemDetails(itemDetails);
        }
        ItemDetails result = itemDetailsRepository.save(itemDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("itemDetails", itemDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /itemDetailss -> get all the itemDetailss.
     */
    @RequestMapping(value = "/itemDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ItemDetails>> getAllItemDetailss(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of ItemDetailss");
        Page<ItemDetails> page = itemDetailsRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/itemDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /itemDetailss/:id -> get the "id" itemDetails.
     */
    @RequestMapping(value = "/itemDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ItemDetails> getItemDetails(@PathVariable Long id) {
        log.debug("REST request to get ItemDetails : {}", id);
        ItemDetails itemDetails = itemDetailsRepository.findOne(id);
        return Optional.ofNullable(itemDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /itemDetailss/:id -> delete the "id" itemDetails.
     */
    @RequestMapping(value = "/itemDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteItemDetails(@PathVariable Long id) {
        log.debug("REST request to delete ItemDetails : {}", id);
        itemDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("itemDetails", id.toString())).build();
    }
}

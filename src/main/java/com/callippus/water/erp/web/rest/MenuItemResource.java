package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MenuItem;
import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.repository.MenuItemRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
 * REST controller for managing MenuItem.
 */
@RestController
@RequestMapping("/api")
public class MenuItemResource {

    private final Logger log = LoggerFactory.getLogger(MenuItemResource.class);
        
    @Inject
    private MenuItemRepository menuItemRepository;
    
    /**
     * POST  /menuItems -> Create a new menuItem.
     */
    @RequestMapping(value = "/menuItems",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuItem> createMenuItem(@Valid @RequestBody MenuItem menuItem) throws URISyntaxException {
        log.debug("REST request to save MenuItem : {}", menuItem);
        if (menuItem.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("menuItem", "idexists", "A new menuItem cannot already have an ID")).body(null);
        }
        MenuItem result = menuItemRepository.save(menuItem);
        return ResponseEntity.created(new URI("/api/menuItems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("menuItem", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /menuItems -> Updates an existing menuItem.
     */
    @RequestMapping(value = "/menuItems",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuItem> updateMenuItem(@Valid @RequestBody MenuItem menuItem) throws URISyntaxException {
        log.debug("REST request to update MenuItem : {}", menuItem);
        if (menuItem.getId() == null) {
            return createMenuItem(menuItem);
        }
        MenuItem result = menuItemRepository.save(menuItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("menuItem", menuItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /menuItems -> get all the menuItems.
     */
    @RequestMapping(value = "/menuItems",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MenuItem>> getAllMenuItems(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MenuItems");
        Page<MenuItem> page = menuItemRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/menuItems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /menuItems/:id -> get the "id" menuItem.
     */
    @RequestMapping(value = "/menuItems/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MenuItem> getMenuItem(@PathVariable Long id) {
        log.debug("REST request to get MenuItem : {}", id);
        MenuItem menuItem = menuItemRepository.findOne(id);
        return Optional.ofNullable(menuItem)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /menuItems/:id -> delete the "id" menuItem.
     */
    @RequestMapping(value = "/menuItems/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        log.debug("REST request to delete MenuItem : {}", id);
        menuItemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("menuItem", id.toString())).build();
    }
    
    /**
     * Get All MenuItems
     */
    @RequestMapping(value = "/menuItems/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<MenuItem>> getAllMenuItems()
			throws Exception {
    	log.debug("REST request to getAllMenuItems : {}");
    	
    	List<MenuItem> menuItems = menuItemRepository.findAll();
    
    	return Optional.ofNullable(menuItems)
				.map(result -> new ResponseEntity<>(menuItems, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

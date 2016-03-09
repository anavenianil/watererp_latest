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
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.Module2MenuItem;
import com.callippus.water.erp.domain.ModuleMenuDTO;
import com.callippus.water.erp.repository.Module2MenuItemCustomRepository;
import com.callippus.water.erp.repository.Module2MenuItemRepository;
import com.callippus.water.erp.security.SecurityUtils;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing Module2MenuItem.
 */
@RestController
@RequestMapping("/api")
public class Module2MenuItemResource {

    private final Logger log = LoggerFactory.getLogger(Module2MenuItemResource.class);
        
    @Inject
    private Module2MenuItemRepository module2MenuItemRepository;
    
    @Inject
    private Module2MenuItemCustomRepository module2MenuItemCustomRepository;
    
    /**
     * POST  /module2MenuItems -> Create a new module2MenuItem.
     */
    @RequestMapping(value = "/module2MenuItems",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Module2MenuItem> createModule2MenuItem(@RequestBody Module2MenuItem module2MenuItem) throws URISyntaxException {
        log.debug("REST request to save Module2MenuItem : {}", module2MenuItem);
        if (module2MenuItem.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("module2MenuItem", "idexists", "A new module2MenuItem cannot already have an ID")).body(null);
        }
        Module2MenuItem result = module2MenuItemRepository.save(module2MenuItem);
        return ResponseEntity.created(new URI("/api/module2MenuItems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("module2MenuItem", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /module2MenuItems -> Updates an existing module2MenuItem.
     */
    @RequestMapping(value = "/module2MenuItems",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Module2MenuItem> updateModule2MenuItem(@RequestBody Module2MenuItem module2MenuItem) throws URISyntaxException {
        log.debug("REST request to update Module2MenuItem : {}", module2MenuItem);
        if (module2MenuItem.getId() == null) {
            return createModule2MenuItem(module2MenuItem);
        }
        Module2MenuItem result = module2MenuItemRepository.save(module2MenuItem);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("module2MenuItem", module2MenuItem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /module2MenuItems -> get all the module2MenuItems.
     */
    @RequestMapping(value = "/module2MenuItems",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Module2MenuItem>> getAllModule2MenuItems(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Module2MenuItems");
        Page<Module2MenuItem> page = module2MenuItemRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/module2MenuItems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /module2MenuItems/:id -> get the "id" module2MenuItem.
     */
    @RequestMapping(value = "/module2MenuItems/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Module2MenuItem> getModule2MenuItem(@PathVariable Long id) {
        log.debug("REST request to get Module2MenuItem : {}", id);
        Module2MenuItem module2MenuItem = module2MenuItemRepository.findOne(id);
        return Optional.ofNullable(module2MenuItem)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /module2MenuItems/:id -> delete the "id" module2MenuItem.
     */
    @RequestMapping(value = "/module2MenuItems/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteModule2MenuItem(@PathVariable Long id) {
        log.debug("REST request to delete Module2MenuItem : {}", id);
        module2MenuItemRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("module2MenuItem", id.toString())).build();
    }
    
    /**
     * GET  /rest/module2menu_items -> get all the module2menu_items.
     */
    @RequestMapping(value = "/rest/module2MenuItems/role",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<ModuleMenuDTO> getAllForUser() {
        log.debug("REST request to get all Module2MenuItems");
        
        //String user = SecurityUtils.getCurrentLogin();
        String user = SecurityUtils.getCurrentUserLogin();
        return module2MenuItemCustomRepository.findAllByLoginUsingMapping (user) ;

    }
}

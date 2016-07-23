package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.Customer;
import com.callippus.water.erp.domain.DivisionMaster;
import com.callippus.water.erp.domain.TariffCategoryMaster;
import com.callippus.water.erp.repository.TariffCategoryMasterRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TariffCategoryMaster.
 */
@RestController
@RequestMapping("/api")
public class TariffCategoryMasterResource {

    private final Logger log = LoggerFactory.getLogger(TariffCategoryMasterResource.class);
        
    @Inject
    private TariffCategoryMasterRepository tariffCategoryMasterRepository;
    
    /**
     * POST  /tariffCategoryMasters -> Create a new tariffCategoryMaster.
     */
    @RequestMapping(value = "/tariffCategoryMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffCategoryMaster> createTariffCategoryMaster(@Valid @RequestBody TariffCategoryMaster tariffCategoryMaster) throws URISyntaxException {
        log.debug("REST request to save TariffCategoryMaster : {}", tariffCategoryMaster);
        if (tariffCategoryMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("tariffCategoryMaster", "idexists", "A new tariffCategoryMaster cannot already have an ID")).body(null);
        }
        TariffCategoryMaster result = tariffCategoryMasterRepository.save(tariffCategoryMaster);
        return ResponseEntity.created(new URI("/api/tariffCategoryMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("tariffCategoryMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tariffCategoryMasters -> Updates an existing tariffCategoryMaster.
     */
    @RequestMapping(value = "/tariffCategoryMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffCategoryMaster> updateTariffCategoryMaster(@Valid @RequestBody TariffCategoryMaster tariffCategoryMaster) throws URISyntaxException {
        log.debug("REST request to update TariffCategoryMaster : {}", tariffCategoryMaster);
        if (tariffCategoryMaster.getId() == null) {
            return createTariffCategoryMaster(tariffCategoryMaster);
        }
        TariffCategoryMaster result = tariffCategoryMasterRepository.save(tariffCategoryMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("tariffCategoryMaster", tariffCategoryMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tariffCategoryMasters -> get all the tariffCategoryMasters.
     */
    @RequestMapping(value = "/tariffCategoryMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TariffCategoryMaster>> getAllTariffCategoryMasters(Pageable pageable,
    		@RequestParam(value = "type", required = false) String type)
        throws URISyntaxException {
        log.debug("REST request to get a page of TariffCategoryMasters");
        //Page<TariffCategoryMaster> page = tariffCategoryMasterRepository.findAll(pageable);
        Page<TariffCategoryMaster> page;
        if(type != null){
			page = tariffCategoryMasterRepository.findByType(pageable, type);
		}
		else 
		{
			page = tariffCategoryMasterRepository.findAll(pageable);
		}
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tariffCategoryMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tariffCategoryMasters/:id -> get the "id" tariffCategoryMaster.
     */
    @RequestMapping(value = "/tariffCategoryMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TariffCategoryMaster> getTariffCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to get TariffCategoryMaster : {}", id);
        TariffCategoryMaster tariffCategoryMaster = tariffCategoryMasterRepository.findOne(id);
        return Optional.ofNullable(tariffCategoryMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /tariffCategoryMasters/:id -> delete the "id" tariffCategoryMaster.
     */
    @RequestMapping(value = "/tariffCategoryMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTariffCategoryMaster(@PathVariable Long id) {
        log.debug("REST request to delete TariffCategoryMaster : {}", id);
        tariffCategoryMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("tariffCategoryMaster", id.toString())).build();
    }
    
    /**
     * Get All TariffCategoryMaster
     */
    @RequestMapping(value = "/tariffCategoryMasters/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<TariffCategoryMaster>> getAllTariffCategoryMasters()
			throws Exception {
    	log.debug("REST request to getAllTariffCategoryMasters : {}");
    	
    	TariffCategoryMaster tcm = new TariffCategoryMaster();
    	tcm.setId(0l);
    	tcm.setTariffCategory("All");;
    	List<TariffCategoryMaster> tariffCategoryMasters = new ArrayList<TariffCategoryMaster>();
    	tariffCategoryMasters.add(tcm);
    	List<TariffCategoryMaster> tariffCategoryMaster1= tariffCategoryMasterRepository.findAll();
    	tariffCategoryMasters.addAll(tariffCategoryMaster1);
    	
    	return Optional.ofNullable(tariffCategoryMasters)
				.map(result -> new ResponseEntity<>(tariffCategoryMasters, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}

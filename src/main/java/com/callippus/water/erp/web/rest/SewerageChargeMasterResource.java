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
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.SewerageChargeMaster;
import com.callippus.water.erp.repository.SewerageChargeMasterRepository;
import com.callippus.water.erp.repository.TariffCategoryMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing SewerageChargeMaster.
 */
@RestController
@RequestMapping("/api")
public class SewerageChargeMasterResource {

    private final Logger log = LoggerFactory.getLogger(SewerageChargeMasterResource.class);
        
    @Inject
    private SewerageChargeMasterRepository sewerageChargeMasterRepository;
    
    @Inject
    private TariffCategoryMasterRepository tariffCategoryMasterRepository;
    
    /**
     * POST  /sewerageChargeMasters -> Create a new sewerageChargeMaster.
     */
    @RequestMapping(value = "/sewerageChargeMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerageChargeMaster> createSewerageChargeMaster(@RequestBody SewerageChargeMaster sewerageChargeMaster) throws URISyntaxException {
        log.debug("REST request to save SewerageChargeMaster : {}", sewerageChargeMaster);
        if (sewerageChargeMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("sewerageChargeMaster", "idexists", "A new sewerageChargeMaster cannot already have an ID")).body(null);
        }
        SewerageChargeMaster result = sewerageChargeMasterRepository.save(sewerageChargeMaster);
        return ResponseEntity.created(new URI("/api/sewerageChargeMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("sewerageChargeMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sewerageChargeMasters -> Updates an existing sewerageChargeMaster.
     */
    @RequestMapping(value = "/sewerageChargeMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerageChargeMaster> updateSewerageChargeMaster(@RequestBody SewerageChargeMaster sewerageChargeMaster) throws URISyntaxException {
        log.debug("REST request to update SewerageChargeMaster : {}", sewerageChargeMaster);
        if (sewerageChargeMaster.getId() == null) {
            return createSewerageChargeMaster(sewerageChargeMaster);
        }
        SewerageChargeMaster result = sewerageChargeMasterRepository.save(sewerageChargeMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("sewerageChargeMaster", sewerageChargeMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sewerageChargeMasters -> get all the sewerageChargeMasters.
     */
    @RequestMapping(value = "/sewerageChargeMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SewerageChargeMaster>> getAllSewerageChargeMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SewerageChargeMasters");
        Page<SewerageChargeMaster> page = sewerageChargeMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sewerageChargeMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sewerageChargeMasters/:id -> get the "id" sewerageChargeMaster.
     */
    @RequestMapping(value = "/sewerageChargeMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SewerageChargeMaster> getSewerageChargeMaster(@PathVariable Long id) {
        log.debug("REST request to get SewerageChargeMaster : {}", id);
        SewerageChargeMaster sewerageChargeMaster = sewerageChargeMasterRepository.findOne(id);
        return Optional.ofNullable(sewerageChargeMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sewerageChargeMasters/:id -> delete the "id" sewerageChargeMaster.
     */
    @RequestMapping(value = "/sewerageChargeMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSewerageChargeMaster(@PathVariable Long id) {
        log.debug("REST request to delete SewerageChargeMaster : {}", id);
        sewerageChargeMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("sewerageChargeMaster", id.toString())).build();
    }
    
    /**
     * Get SewerageChargeMaster by categoryId
     */
    @RequestMapping(value = "/sewerageChargeMasters/getChargeByCategoryId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<SewerageChargeMaster> getChargeBycategoryId(@Param("categoryId") Long categoryId)
			throws Exception {
    	log.debug("REST request to SewerageChargeMaster by categoryId : {}");
    	
    	SewerageChargeMaster sewerageChargeMaster = sewerageChargeMasterRepository.findByTariffCategoryMaster(tariffCategoryMasterRepository.findOne(categoryId));
    
    	return Optional.ofNullable(sewerageChargeMaster)
				.map(result -> new ResponseEntity<>(sewerageChargeMaster, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}
}

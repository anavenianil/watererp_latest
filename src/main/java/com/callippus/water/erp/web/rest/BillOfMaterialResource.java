package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BillOfMaterial;
import com.callippus.water.erp.repository.BillOfMaterialRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * REST controller for managing BillOfMaterial.
 */
@RestController
@RequestMapping("/api")
public class BillOfMaterialResource {

    private final Logger log = LoggerFactory.getLogger(BillOfMaterialResource.class);
        
    @Inject
    private BillOfMaterialRepository billOfMaterialRepository;
    
    /**
     * POST  /billOfMaterials -> Create a new billOfMaterial.
     */
    @RequestMapping(value = "/billOfMaterials",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillOfMaterial> createBillOfMaterial(@RequestBody BillOfMaterial billOfMaterial) throws URISyntaxException {
        log.debug("REST request to save BillOfMaterial : {}", billOfMaterial);
        if (billOfMaterial.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("billOfMaterial", "idexists", "A new billOfMaterial cannot already have an ID")).body(null);
        }
        BillOfMaterial result = billOfMaterialRepository.save(billOfMaterial);
        return ResponseEntity.created(new URI("/api/billOfMaterials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("billOfMaterial", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /billOfMaterials -> Updates an existing billOfMaterial.
     */
    @RequestMapping(value = "/billOfMaterials",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillOfMaterial> updateBillOfMaterial(@RequestBody BillOfMaterial billOfMaterial) throws URISyntaxException {
        log.debug("REST request to update BillOfMaterial : {}", billOfMaterial);
        if (billOfMaterial.getId() == null) {
            return createBillOfMaterial(billOfMaterial);
        }
        BillOfMaterial result = billOfMaterialRepository.save(billOfMaterial);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("billOfMaterial", billOfMaterial.getId().toString()))
            .body(result);
    }

    /**
     * GET  /billOfMaterials -> get all the billOfMaterials.
     */
    @RequestMapping(value = "/billOfMaterials",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<BillOfMaterial> getAllBillOfMaterials() {
        log.debug("REST request to get all BillOfMaterials");
        return billOfMaterialRepository.findAll();
            }

    /**
     * GET  /billOfMaterials/:id -> get the "id" billOfMaterial.
     */
    @RequestMapping(value = "/billOfMaterials/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillOfMaterial> getBillOfMaterial(@PathVariable Long id) {
        log.debug("REST request to get BillOfMaterial : {}", id);
        BillOfMaterial billOfMaterial = billOfMaterialRepository.findOne(id);
        return Optional.ofNullable(billOfMaterial)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /billOfMaterials/:id -> delete the "id" billOfMaterial.
     */
    @RequestMapping(value = "/billOfMaterials/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBillOfMaterial(@PathVariable Long id) {
        log.debug("REST request to delete BillOfMaterial : {}", id);
        billOfMaterialRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("billOfMaterial", id.toString())).build();
    }
}

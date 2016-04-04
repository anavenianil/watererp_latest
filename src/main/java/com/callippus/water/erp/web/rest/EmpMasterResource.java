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

import com.callippus.water.erp.domain.EmpMaster;
import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.repository.EmpMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing EmpMaster.
 */
@RestController
@RequestMapping("/api")
public class EmpMasterResource {

    private final Logger log = LoggerFactory.getLogger(EmpMasterResource.class);
        
    @Inject
    private EmpMasterRepository empMasterRepository;
    
    /**
     * POST  /empMasters -> Create a new empMaster.
     */
    @RequestMapping(value = "/empMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmpMaster> createEmpMaster(@RequestBody EmpMaster empMaster) throws URISyntaxException {
        log.debug("REST request to save EmpMaster : {}", empMaster);
        if (empMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("empMaster", "idexists", "A new empMaster cannot already have an ID")).body(null);
        }
        EmpMaster result = empMasterRepository.save(empMaster);
        return ResponseEntity.created(new URI("/api/empMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("empMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /empMasters -> Updates an existing empMaster.
     */
    @RequestMapping(value = "/empMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmpMaster> updateEmpMaster(@RequestBody EmpMaster empMaster) throws URISyntaxException {
        log.debug("REST request to update EmpMaster : {}", empMaster);
        if (empMaster.getId() == null) {
            return createEmpMaster(empMaster);
        }
        EmpMaster result = empMasterRepository.save(empMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("empMaster", empMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /empMasters -> get all the empMasters.
     */
    @RequestMapping(value = "/empMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<EmpMaster>> getAllEmpMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of EmpMasters");
        Page<EmpMaster> page = empMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/empMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /empMasters/:id -> get the "id" empMaster.
     */
    @RequestMapping(value = "/empMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmpMaster> getEmpMaster(@PathVariable Long id) {
        log.debug("REST request to get EmpMaster : {}", id);
        EmpMaster empMaster = empMasterRepository.findOne(id);
        return Optional.ofNullable(empMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /empMasters/:id -> delete the "id" empMaster.
     */
    @RequestMapping(value = "/empMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEmpMaster(@PathVariable Long id) {
        log.debug("REST request to delete EmpMaster : {}", id);
        empMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("empMaster", id.toString())).build();
    }
    
    /**
     * GET  /empMastersForOrgRole/:id -> get the "id" empMaster.
     */
    @RequestMapping(value = "/empMastersForOrgRole",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<OrgRoleInstance> getorgRoleForLogin() {
        log.debug("REST request to get EmpMaster : {}");
        //EmpMaster empMaster = empMasterRepository.findOneOfficeId();
        OrgRoleInstance empMaster = empMasterRepository.findOneOfficeId();
        return Optional.ofNullable(empMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
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

import com.callippus.water.erp.domain.EmpRoleMapping;
import com.callippus.water.erp.repository.EmpRoleMappingRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing EmpRoleMapping.
 */
@RestController
@RequestMapping("/api")
public class EmpRoleMappingResource {

    private final Logger log = LoggerFactory.getLogger(EmpRoleMappingResource.class);
        
    @Inject
    private EmpRoleMappingRepository empRoleMappingRepository;
    
    @Inject
    private UserRepository userRepository;
    
    /**
     * POST  /empRoleMappings -> Create a new empRoleMapping.
     */
    @RequestMapping(value = "/empRoleMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmpRoleMapping> createEmpRoleMapping(@RequestBody EmpRoleMapping empRoleMapping) throws URISyntaxException {
        log.debug("REST request to save EmpRoleMapping : {}", empRoleMapping);
        if (empRoleMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("empRoleMapping", "idexists", "A new empRoleMapping cannot already have an ID")).body(null);
        }
        empRoleMapping.setCreationDate(ZonedDateTime.now());
        EmpRoleMapping result = empRoleMappingRepository.save(empRoleMapping);
        return ResponseEntity.created(new URI("/api/empRoleMappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("empRoleMapping", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /empRoleMappings -> Updates an existing empRoleMapping.
     */
    @RequestMapping(value = "/empRoleMappings",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmpRoleMapping> updateEmpRoleMapping(@RequestBody EmpRoleMapping empRoleMapping) throws URISyntaxException {
        log.debug("REST request to update EmpRoleMapping : {}", empRoleMapping);
        if (empRoleMapping.getId() == null) {
            return createEmpRoleMapping(empRoleMapping);
        }
        empRoleMapping.setLastModifiedDate(ZonedDateTime.now());
        EmpRoleMapping result = empRoleMappingRepository.save(empRoleMapping);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("empRoleMapping", empRoleMapping.getId().toString()))
            .body(result);
    }

    /**
     * GET  /empRoleMappings -> get all the empRoleMappings.
     */
    @RequestMapping(value = "/empRoleMappings",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<EmpRoleMapping>> getAllEmpRoleMappings(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of EmpRoleMappings");
        Page<EmpRoleMapping> page = empRoleMappingRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/empRoleMappings");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /empRoleMappings/:id -> get the "id" empRoleMapping.
     */
    @RequestMapping(value = "/empRoleMappings/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EmpRoleMapping> getEmpRoleMapping(@PathVariable Long id) {
        log.debug("REST request to get EmpRoleMapping : {}", id);
        EmpRoleMapping empRoleMapping = empRoleMappingRepository.findOne(id);
        return Optional.ofNullable(empRoleMapping)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /empRoleMappings/:id -> delete the "id" empRoleMapping.
     */
    @RequestMapping(value = "/empRoleMappings/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEmpRoleMapping(@PathVariable Long id) {
        log.debug("REST request to delete EmpRoleMapping : {}", id);
        empRoleMappingRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("empRoleMapping", id.toString())).build();
    }
    
    /**
     * Get EmpRoleMapping by UserId
     */
    @RequestMapping(value = "/empRoleMappings/getByUserId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<EmpRoleMapping> getEmpRoleMappingByUserId(@Param("userId") Long userId)
			throws Exception {
    	log.debug("REST request to EmpRoleMapping by userId : {}");
    	
    	EmpRoleMapping empRoleMapping = empRoleMappingRepository.findByUser(userRepository.findOne(userId));
    
    	return Optional.ofNullable(empRoleMapping)
				.map(result -> new ResponseEntity<>(empRoleMapping, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}
}

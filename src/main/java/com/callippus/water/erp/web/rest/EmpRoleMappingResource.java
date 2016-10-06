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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.EmpRoleMapping;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.domain.RequestWorkflowMapping;
import com.callippus.water.erp.domain.Workflow;
import com.callippus.water.erp.repository.EmpRoleMappingRepository;
import com.callippus.water.erp.repository.OrgRoleInstanceRepository;
import com.callippus.water.erp.repository.RequestMasterRepository;
import com.callippus.water.erp.repository.RequestWorkflowHistoryRepository;
import com.callippus.water.erp.repository.RequestWorkflowMappingRepository;
import com.callippus.water.erp.repository.StatusMasterRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.repository.WorkflowRepository;
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
    
    @Inject
    private StatusMasterRepository statusMasterRepository;
    
    @Inject
    private RequestWorkflowHistoryRepository requestWorkflowHistoryRepository;
    
    @Inject
    private RequestMasterRepository requestMasterRepository;
    
    @Inject
    private RequestWorkflowMappingRepository requestWorkflowMappingRepository;
    
    @Inject
    private WorkflowRepository workflowRepository;
    
    @Inject
    private OrgRoleInstanceRepository orgRoleInstanceRepository;
    
    /**
     * POST  /empRoleMappings -> Create a new empRoleMapping.
     */
    @RequestMapping(value = "/empRoleMappings",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(rollbackFor=Exception.class)
    public ResponseEntity<EmpRoleMapping> createEmpRoleMapping(@RequestBody EmpRoleMapping empRoleMapping) throws URISyntaxException {
        log.debug("REST request to save EmpRoleMapping : {}", empRoleMapping);
        if (empRoleMapping.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("empRoleMapping", "idexists", "A new empRoleMapping cannot already have an ID")).body(null);
        }
        empRoleMapping.setCreationDate(ZonedDateTime.now());
        empRoleMapping.setLastModifiedDate(ZonedDateTime.now());
        
        EmpRoleMapping empRoleMappingOld = empRoleMappingRepository.findByStatusMasterAndOrgRoleInstance(2l, 
        		empRoleMapping.getOrgRoleInstance().getId());
        if(empRoleMappingOld != null){
        	empRoleMappingOld.setStatusMaster(statusMasterRepository.findOne(1l));
            empRoleMappingRepository.save(empRoleMappingOld);
        }
        
        empRoleMapping.setStatusMaster(statusMasterRepository.findOne(2l));
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
        EmpRoleMapping erm = empRoleMappingRepository.findOne(id);
        erm.setStatusMaster(statusMasterRepository.findOne(1l));
        empRoleMappingRepository.save(erm);
        //empRoleMappingRepository.delete(id);
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
    
    /**
     * Get EmpRoleMapping by UserId
     */
    @RequestMapping(value = "/empRoleMappings/getMappingsByUserId",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<EmpRoleMapping>> getEmpRoleMappingsByUserId(@Param("userId") Long userId)
			throws Exception {
    	log.debug("REST request to EmpRoleMappings by userId : {}");
    	
    	List<EmpRoleMapping> empRoleMappings = empRoleMappingRepository.findByUserAndStatusMaster(userRepository.findOne(userId), statusMasterRepository.findOne(2l));
    
    	return Optional.ofNullable(empRoleMappings)
				.map(result -> new ResponseEntity<>(empRoleMappings, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}
    
   /* *//**
     * Get EmpRoleMapping by Login
     */
    @RequestMapping(value = "/empRoleMappings/getMappingsByWorkflow",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<EmpRoleMapping> getEmpRoleMappingsByWorkflow(@Param("domainObjectId") Long domainObjectId, @Param("requestTypeId") Long requestTypeId)
			throws Exception {
    	log.debug("REST request to EmpRoleMappings by Workflow : {}");
    	
    	RequestWorkflowMapping requestWorkflowMapping = requestWorkflowMappingRepository.findByRequestMaster(requestMasterRepository.findOne(requestTypeId));
    	RequestWorkflowHistory rwh = requestWorkflowHistoryRepository.findTop1ByDomainObjectAndRequestMasterOrderByIdDesc(domainObjectId, requestMasterRepository.findOne(requestTypeId));
    	Workflow workflow = workflowRepository.findByWorkflowMasterAndStageId(requestWorkflowMapping.getWorkflowMaster(), rwh.getRequestStage());
    	EmpRoleMapping empRoleMapping = empRoleMappingRepository.findByStatusMasterAndOrgRoleInstance(2l, workflow.getAbsoluteToRole().getId());
    	
    	return Optional.ofNullable(empRoleMapping)
				.map(result -> new ResponseEntity<>(empRoleMapping, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}
    
    
    /**
     * Get EmpRoleMapping by Login
     */
    @RequestMapping(value = "/empRoleMappings/getMappingsByLogin",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<EmpRoleMapping> getEmpRoleMappingsByLogin()
			throws Exception {
    	log.debug("REST request to EmpRoleMappings by login : {}");
    	
    	EmpRoleMapping byLoggidIn = empRoleMappingRepository.findByStatusMasterAndUser();
    
    	return Optional.ofNullable(byLoggidIn)
				.map(result -> new ResponseEntity<>(byLoggidIn, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}
    
    /**
     * Get EmpRoleMapping by OrgRoleInstance
     */
    @RequestMapping(value = "/empRoleMappings/getMappingsByOrgRoleInstance",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<EmpRoleMapping> getMappingsByOrgRoleInstance(@Param("orgRoleInstanceId") Long orgRoleInstanceId)
			throws Exception {
    	log.debug("REST request to EmpRoleMappings by orgRoleInstanceId : {}");
    	
    	EmpRoleMapping empRoleMapping = empRoleMappingRepository.findByStatusMasterAndOrgRoleInstance(2l, orgRoleInstanceId);
    	
    	return Optional.ofNullable(empRoleMapping)
				.map(result -> new ResponseEntity<>(empRoleMapping, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}
    
    
}

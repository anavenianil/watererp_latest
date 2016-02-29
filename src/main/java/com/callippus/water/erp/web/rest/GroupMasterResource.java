package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.GroupMaster;
import com.callippus.water.erp.repository.GroupMasterRepository;
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
 * REST controller for managing GroupMaster.
 */
@RestController
@RequestMapping("/api")
public class GroupMasterResource {

    private final Logger log = LoggerFactory.getLogger(GroupMasterResource.class);
        
    @Inject
    private GroupMasterRepository groupMasterRepository;
    
    /**
     * POST  /groupMasters -> Create a new groupMaster.
     */
    @RequestMapping(value = "/groupMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GroupMaster> createGroupMaster(@RequestBody GroupMaster groupMaster) throws URISyntaxException {
        log.debug("REST request to save GroupMaster : {}", groupMaster);
        if (groupMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("groupMaster", "idexists", "A new groupMaster cannot already have an ID")).body(null);
        }
        GroupMaster result = groupMasterRepository.save(groupMaster);
        return ResponseEntity.created(new URI("/api/groupMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("groupMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /groupMasters -> Updates an existing groupMaster.
     */
    @RequestMapping(value = "/groupMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GroupMaster> updateGroupMaster(@RequestBody GroupMaster groupMaster) throws URISyntaxException {
        log.debug("REST request to update GroupMaster : {}", groupMaster);
        if (groupMaster.getId() == null) {
            return createGroupMaster(groupMaster);
        }
        GroupMaster result = groupMasterRepository.save(groupMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("groupMaster", groupMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /groupMasters -> get all the groupMasters.
     */
    @RequestMapping(value = "/groupMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<GroupMaster>> getAllGroupMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of GroupMasters");
        Page<GroupMaster> page = groupMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/groupMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /groupMasters/:id -> get the "id" groupMaster.
     */
    @RequestMapping(value = "/groupMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<GroupMaster> getGroupMaster(@PathVariable Long id) {
        log.debug("REST request to get GroupMaster : {}", id);
        GroupMaster groupMaster = groupMasterRepository.findOne(id);
        return Optional.ofNullable(groupMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /groupMasters/:id -> delete the "id" groupMaster.
     */
    @RequestMapping(value = "/groupMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteGroupMaster(@PathVariable Long id) {
        log.debug("REST request to delete GroupMaster : {}", id);
        groupMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("groupMaster", id.toString())).build();
    }
}

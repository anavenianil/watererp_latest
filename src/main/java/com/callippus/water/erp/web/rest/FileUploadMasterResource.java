package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.FileUploadMaster;
import com.callippus.water.erp.repository.FileUploadMasterRepository;
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
 * REST controller for managing FileUploadMaster.
 */
@RestController
@RequestMapping("/api")
public class FileUploadMasterResource {

    private final Logger log = LoggerFactory.getLogger(FileUploadMasterResource.class);
        
    @Inject
    private FileUploadMasterRepository fileUploadMasterRepository;
    
    /**
     * POST  /fileUploadMasters -> Create a new fileUploadMaster.
     */
    @RequestMapping(value = "/fileUploadMasters",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FileUploadMaster> createFileUploadMaster(@RequestBody FileUploadMaster fileUploadMaster) throws URISyntaxException {
        log.debug("REST request to save FileUploadMaster : {}", fileUploadMaster);
        if (fileUploadMaster.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("fileUploadMaster", "idexists", "A new fileUploadMaster cannot already have an ID")).body(null);
        }
        FileUploadMaster result = fileUploadMasterRepository.save(fileUploadMaster);
        return ResponseEntity.created(new URI("/api/fileUploadMasters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("fileUploadMaster", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fileUploadMasters -> Updates an existing fileUploadMaster.
     */
    @RequestMapping(value = "/fileUploadMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FileUploadMaster> updateFileUploadMaster(@RequestBody FileUploadMaster fileUploadMaster) throws URISyntaxException {
        log.debug("REST request to update FileUploadMaster : {}", fileUploadMaster);
        if (fileUploadMaster.getId() == null) {
            return createFileUploadMaster(fileUploadMaster);
        }
        FileUploadMaster result = fileUploadMasterRepository.save(fileUploadMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("fileUploadMaster", fileUploadMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fileUploadMasters -> get all the fileUploadMasters.
     */
    @RequestMapping(value = "/fileUploadMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<FileUploadMaster> getAllFileUploadMasters() {
        log.debug("REST request to get all FileUploadMasters");
        return fileUploadMasterRepository.findAll();
            }

    /**
     * GET  /fileUploadMasters/:id -> get the "id" fileUploadMaster.
     */
    @RequestMapping(value = "/fileUploadMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FileUploadMaster> getFileUploadMaster(@PathVariable Long id) {
        log.debug("REST request to get FileUploadMaster : {}", id);
        FileUploadMaster fileUploadMaster = fileUploadMasterRepository.findOne(id);
        return Optional.ofNullable(fileUploadMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /fileUploadMasters/:id -> delete the "id" fileUploadMaster.
     */
    @RequestMapping(value = "/fileUploadMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFileUploadMaster(@PathVariable Long id) {
        log.debug("REST request to delete FileUploadMaster : {}", id);
        fileUploadMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("fileUploadMaster", id.toString())).build();
    }
}

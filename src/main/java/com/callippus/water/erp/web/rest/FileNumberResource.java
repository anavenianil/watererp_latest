package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.FileNumber;
import com.callippus.water.erp.repository.FileNumberRepository;
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
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FileNumber.
 */
@RestController
@RequestMapping("/api")
public class FileNumberResource {

    private final Logger log = LoggerFactory.getLogger(FileNumberResource.class);
        
    @Inject
    private FileNumberRepository fileNumberRepository;
    
    /**
     * POST  /fileNumbers -> Create a new fileNumber.
     */
    @RequestMapping(value = "/fileNumbers",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FileNumber> createFileNumber(@Valid @RequestBody FileNumber fileNumber) throws URISyntaxException {
        log.debug("REST request to save FileNumber : {}", fileNumber);
        if (fileNumber.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("fileNumber", "idexists", "A new fileNumber cannot already have an ID")).body(null);
        }
        FileNumber result = fileNumberRepository.save(fileNumber);
        return ResponseEntity.created(new URI("/api/fileNumbers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("fileNumber", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /fileNumbers -> Updates an existing fileNumber.
     */
    @RequestMapping(value = "/fileNumbers",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FileNumber> updateFileNumber(@Valid @RequestBody FileNumber fileNumber) throws URISyntaxException {
        log.debug("REST request to update FileNumber : {}", fileNumber);
        if (fileNumber.getId() == null) {
            return createFileNumber(fileNumber);
        }
        FileNumber result = fileNumberRepository.save(fileNumber);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("fileNumber", fileNumber.getId().toString()))
            .body(result);
    }

    /**
     * GET  /fileNumbers -> get all the fileNumbers.
     */
    @RequestMapping(value = "/fileNumbers",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<FileNumber>> getAllFileNumbers(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of FileNumbers");
        Page<FileNumber> page = fileNumberRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/fileNumbers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /fileNumbers/:id -> get the "id" fileNumber.
     */
    @RequestMapping(value = "/fileNumbers/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<FileNumber> getFileNumber(@PathVariable Long id) {
        log.debug("REST request to get FileNumber : {}", id);
        FileNumber fileNumber = fileNumberRepository.findOne(id);
        return Optional.ofNullable(fileNumber)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /fileNumbers/:id -> delete the "id" fileNumber.
     */
    @RequestMapping(value = "/fileNumbers/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteFileNumber(@PathVariable Long id) {
        log.debug("REST request to delete FileNumber : {}", id);
        fileNumberRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("fileNumber", id.toString())).build();
    }
}

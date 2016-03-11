package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.MakeOfPipe;
import com.callippus.water.erp.repository.MakeOfPipeRepository;
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
 * REST controller for managing MakeOfPipe.
 */
@RestController
@RequestMapping("/api")
public class MakeOfPipeResource {

    private final Logger log = LoggerFactory.getLogger(MakeOfPipeResource.class);
        
    @Inject
    private MakeOfPipeRepository makeOfPipeRepository;
    
    /**
     * POST  /makeOfPipes -> Create a new makeOfPipe.
     */
    @RequestMapping(value = "/makeOfPipes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MakeOfPipe> createMakeOfPipe(@Valid @RequestBody MakeOfPipe makeOfPipe) throws URISyntaxException {
        log.debug("REST request to save MakeOfPipe : {}", makeOfPipe);
        if (makeOfPipe.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("makeOfPipe", "idexists", "A new makeOfPipe cannot already have an ID")).body(null);
        }
        MakeOfPipe result = makeOfPipeRepository.save(makeOfPipe);
        return ResponseEntity.created(new URI("/api/makeOfPipes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("makeOfPipe", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /makeOfPipes -> Updates an existing makeOfPipe.
     */
    @RequestMapping(value = "/makeOfPipes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MakeOfPipe> updateMakeOfPipe(@Valid @RequestBody MakeOfPipe makeOfPipe) throws URISyntaxException {
        log.debug("REST request to update MakeOfPipe : {}", makeOfPipe);
        if (makeOfPipe.getId() == null) {
            return createMakeOfPipe(makeOfPipe);
        }
        MakeOfPipe result = makeOfPipeRepository.save(makeOfPipe);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("makeOfPipe", makeOfPipe.getId().toString()))
            .body(result);
    }

    /**
     * GET  /makeOfPipes -> get all the makeOfPipes.
     */
    @RequestMapping(value = "/makeOfPipes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<MakeOfPipe>> getAllMakeOfPipes(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of MakeOfPipes");
        Page<MakeOfPipe> page = makeOfPipeRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/makeOfPipes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /makeOfPipes/:id -> get the "id" makeOfPipe.
     */
    @RequestMapping(value = "/makeOfPipes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<MakeOfPipe> getMakeOfPipe(@PathVariable Long id) {
        log.debug("REST request to get MakeOfPipe : {}", id);
        MakeOfPipe makeOfPipe = makeOfPipeRepository.findOne(id);
        return Optional.ofNullable(makeOfPipe)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /makeOfPipes/:id -> delete the "id" makeOfPipe.
     */
    @RequestMapping(value = "/makeOfPipes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteMakeOfPipe(@PathVariable Long id) {
        log.debug("REST request to delete MakeOfPipe : {}", id);
        makeOfPipeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("makeOfPipe", id.toString())).build();
    }
}

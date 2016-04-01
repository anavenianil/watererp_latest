package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.Terminal;
import com.callippus.water.erp.repository.TerminalRepository;
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
 * REST controller for managing Terminal.
 */
@RestController
@RequestMapping("/api")
public class TerminalResource {

    private final Logger log = LoggerFactory.getLogger(TerminalResource.class);
        
    @Inject
    private TerminalRepository terminalRepository;
    
    /**
     * POST  /terminals -> Create a new terminal.
     */
    @RequestMapping(value = "/terminals",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Terminal> createTerminal(@RequestBody Terminal terminal) throws URISyntaxException {
        log.debug("REST request to save Terminal : {}", terminal);
        if (terminal.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("terminal", "idexists", "A new terminal cannot already have an ID")).body(null);
        }
        Terminal result = terminalRepository.save(terminal);
        return ResponseEntity.created(new URI("/api/terminals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("terminal", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /terminals -> Updates an existing terminal.
     */
    @RequestMapping(value = "/terminals",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Terminal> updateTerminal(@RequestBody Terminal terminal) throws URISyntaxException {
        log.debug("REST request to update Terminal : {}", terminal);
        if (terminal.getId() == null) {
            return createTerminal(terminal);
        }
        Terminal result = terminalRepository.save(terminal);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("terminal", terminal.getId().toString()))
            .body(result);
    }

    /**
     * GET  /terminals -> get all the terminals.
     */
    @RequestMapping(value = "/terminals",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<Terminal>> getAllTerminals(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Terminals");
        Page<Terminal> page = terminalRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/terminals");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /terminals/:id -> get the "id" terminal.
     */
    @RequestMapping(value = "/terminals/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Terminal> getTerminal(@PathVariable Long id) {
        log.debug("REST request to get Terminal : {}", id);
        Terminal terminal = terminalRepository.findOne(id);
        return Optional.ofNullable(terminal)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /terminals/:id -> delete the "id" terminal.
     */
    @RequestMapping(value = "/terminals/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTerminal(@PathVariable Long id) {
        log.debug("REST request to delete Terminal : {}", id);
        terminalRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("terminal", id.toString())).build();
    }
}

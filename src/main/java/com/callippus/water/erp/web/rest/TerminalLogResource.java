package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.TerminalLog;
import com.callippus.water.erp.repository.TerminalLogRepository;
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
 * REST controller for managing TerminalLog.
 */
@RestController
@RequestMapping("/api")
public class TerminalLogResource {

    private final Logger log = LoggerFactory.getLogger(TerminalLogResource.class);
        
    @Inject
    private TerminalLogRepository terminalLogRepository;
    
    /**
     * POST  /terminalLogs -> Create a new terminalLog.
     */
    @RequestMapping(value = "/terminalLogs",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TerminalLog> createTerminalLog(@RequestBody TerminalLog terminalLog) throws URISyntaxException {
        log.debug("REST request to save TerminalLog : {}", terminalLog);
        if (terminalLog.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("terminalLog", "idexists", "A new terminalLog cannot already have an ID")).body(null);
        }
        TerminalLog result = terminalLogRepository.save(terminalLog);
        return ResponseEntity.created(new URI("/api/terminalLogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("terminalLog", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /terminalLogs -> Updates an existing terminalLog.
     */
    @RequestMapping(value = "/terminalLogs",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TerminalLog> updateTerminalLog(@RequestBody TerminalLog terminalLog) throws URISyntaxException {
        log.debug("REST request to update TerminalLog : {}", terminalLog);
        if (terminalLog.getId() == null) {
            return createTerminalLog(terminalLog);
        }
        TerminalLog result = terminalLogRepository.save(terminalLog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("terminalLog", terminalLog.getId().toString()))
            .body(result);
    }

    /**
     * GET  /terminalLogs -> get all the terminalLogs.
     */
    @RequestMapping(value = "/terminalLogs",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<TerminalLog>> getAllTerminalLogs(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of TerminalLogs");
        Page<TerminalLog> page = terminalLogRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/terminalLogs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /terminalLogs/:id -> get the "id" terminalLog.
     */
    @RequestMapping(value = "/terminalLogs/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<TerminalLog> getTerminalLog(@PathVariable Long id) {
        log.debug("REST request to get TerminalLog : {}", id);
        TerminalLog terminalLog = terminalLogRepository.findOne(id);
        return Optional.ofNullable(terminalLog)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /terminalLogs/:id -> delete the "id" terminalLog.
     */
    @RequestMapping(value = "/terminalLogs/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteTerminalLog(@PathVariable Long id) {
        log.debug("REST request to delete TerminalLog : {}", id);
        terminalLogRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("terminalLog", id.toString())).build();
    }
}

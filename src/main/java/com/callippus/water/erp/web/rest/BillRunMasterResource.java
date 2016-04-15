package com.callippus.water.erp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.service.BillingService;
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
 * REST controller for managing BillRunMaster.
 */
@RestController
@RequestMapping("/api")
public class BillRunMasterResource {

    private final Logger log = LoggerFactory.getLogger(BillRunMasterResource.class);
        
    @Inject
    private BillRunMasterRepository billRunMasterRepository;

	@Inject
	private BillingService billingService;

	/**
	 * POST /billRunMasters -> Create a new billRunMaster.
	 */
	@RequestMapping(value = "/billRunMasters", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<BillRunMaster> createBillRunMaster(
			@RequestBody BillRunMaster billRunMaster) throws URISyntaxException {
		log.debug("REST request to save BillRunMaster : {}", billRunMaster);
		
		BillRunMaster result =  null;
		if (billRunMaster.getId() != null) {
			return ResponseEntity
					.badRequest()
					.headers(
							HeaderUtil
									.createFailureAlert("billRunMaster",
											"idexists",
											"A new billRunMaster cannot already have an ID"))
					.body(null);
		}

		if (billRunMaster.getArea() == null) {
			result = billingService.generateBill();

		} else {
			String param = billRunMaster.getArea().toString();

			if (param.length() < 3) // It's an area
			{
				result = billingService.generateBill();
			} else if (param.length() == 9) {
				result = billingService.generateSingleBill(param);
			}
		}
		return ResponseEntity
				.ok()
				.headers(
						HeaderUtil.createEntityUpdateAlert("billRunMaster",
								result.getId().toString())).body(result);
	}

    /**
     * PUT  /billRunMasters -> Updates an existing billRunMaster.
     */
    @RequestMapping(value = "/billRunMasters",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillRunMaster> updateBillRunMaster(@RequestBody BillRunMaster billRunMaster) throws URISyntaxException {
        log.debug("REST request to update BillRunMaster : {}", billRunMaster);
        
        String status = null;
        
        if (billRunMaster.getId() == null) {
            return createBillRunMaster(billRunMaster);
        }
        
        if(billRunMaster.getStatus().equalsIgnoreCase("cancel")){
        	status = billingService.cancelBillRun(billRunMaster.getId());
        }
        
        
        if(billRunMaster.getStatus().equalsIgnoreCase("commit")){
        	status = billingService.commitBillRun(billRunMaster.getId());
        }
        
        BillRunMaster result = billRunMasterRepository.findOne(billRunMaster.getId()); //Get updated values
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("billRunMaster:", billRunMaster.getId().toString()+", status:" + status))
            .body(result);
    }

    /**
     * GET  /billRunMasters -> get all the billRunMasters.
     */
    @RequestMapping(value = "/billRunMasters",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<BillRunMaster>> getAllBillRunMasters(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BillRunMasters");
        Page<BillRunMaster> page = billRunMasterRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/billRunMasters");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /billRunMasters/:id -> get the "id" billRunMaster.
     */
    @RequestMapping(value = "/billRunMasters/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<BillRunMaster> getBillRunMaster(@PathVariable Long id) {
        log.debug("REST request to get BillRunMaster : {}", id);
        BillRunMaster billRunMaster = billRunMasterRepository.findOne(id);
        return Optional.ofNullable(billRunMaster)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /billRunMasters/:id -> delete the "id" billRunMaster.
     */
    @RequestMapping(value = "/billRunMasters/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteBillRunMaster(@PathVariable Long id) {
        log.debug("REST request to delete BillRunMaster : {}", id);
        billRunMasterRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("billRunMaster", id.toString())).build();
    }
}

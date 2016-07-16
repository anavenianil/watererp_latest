package com.callippus.water.erp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.CustDetailsReportDTO;
import com.callippus.water.erp.domain.DivisionMaster;
import com.callippus.water.erp.domain.TariffCategoryMaster;
import com.callippus.water.erp.repository.CustDetailsCustomRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.DivisionMasterRepository;
import com.callippus.water.erp.repository.TariffCategoryMasterRepository;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing CustDetails.
 */
@RestController
@RequestMapping("/api")
public class CustDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CustDetailsResource.class);
        
    @Inject
    private CustDetailsRepository custDetailsRepository;
    
	@Inject
	private CustDetailsCustomRepository custDetailsCustomRepository;
	
	@Inject
	private TariffCategoryMasterRepository tariffCategoryMasterRepository;
	
	@Inject
	private DivisionMasterRepository divisionMasterRepository;
    /**
     * POST  /custDetailss -> Create a new custDetails.
     */
    @RequestMapping(value = "/custDetailss",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustDetails> createCustDetails(@Valid @RequestBody CustDetails custDetails) throws URISyntaxException {
        log.debug("REST request to save CustDetails : {}", custDetails);
        if (custDetails.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("custDetails", "idexists", "A new custDetails cannot already have an ID")).body(null);
        }
        CustDetails result = custDetailsRepository.save(custDetails);
        return ResponseEntity.created(new URI("/api/custDetailss/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("custDetails", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /custDetailss -> Updates an existing custDetails.
     */
    @RequestMapping(value = "/custDetailss",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustDetails> updateCustDetails(@Valid @RequestBody CustDetails custDetails) throws URISyntaxException {
        log.debug("REST request to update CustDetails : {}", custDetails);
        if (custDetails.getId() == null) {
            return createCustDetails(custDetails);
        }
        CustDetails result = custDetailsRepository.save(custDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("custDetails", custDetails.getId().toString()))
            .body(result);
    }

    /**
     * GET  /custDetailss -> get all the custDetailss.
     */
    @RequestMapping(value = "/custDetailss",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<CustDetails>> getAllCustDetailss(Pageable pageable,
    		@RequestParam(value = "categoryId", required = false) Long categoryId)
        throws URISyntaxException {
        log.debug("REST request to get a page of CustDetailss");
        //Page<CustDetails> page = custDetailsRepository.findAll(pageable);
        Page<CustDetails> page;
        if(categoryId !=null){
        	TariffCategoryMaster tariffCategoryMaster = tariffCategoryMasterRepository.findOne(categoryId);
        	page = custDetailsRepository.findByTariffCategoryMaster(pageable, tariffCategoryMaster);
        	
        }else{
        	page = custDetailsRepository.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/custDetailss");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /custDetailss/:id -> get the "id" custDetails.
     */
    @RequestMapping(value = "/custDetailss/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustDetails> getCustDetails(@PathVariable Long id) {
        log.debug("REST request to get CustDetails : {}", id);
        CustDetails custDetails = custDetailsRepository.findOne(id);
        return Optional.ofNullable(custDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    /**
     * GET  /custDetailss/:id -> get the "id" custDetails.
     */
    @RequestMapping(value = "/custDetailss/search/{can}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustDetails> getCustDetails1(@PathVariable String can) {
        log.debug("REST request to get CustDetails : {}", can);
        CustDetails custDetails = custDetailsRepository.findByCan(can);
        return Optional.ofNullable(custDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /custDetailss/searchCAN/:searchTerm
     */
    @RequestMapping(value = "/custDetailss/searchCAN/{searchTerm}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<String>> searchCANLike(@PathVariable String searchTerm) {
        log.debug("REST request to get CustDetails : {}", searchTerm);
        List<String> canList = custDetailsCustomRepository.searchCAN(searchTerm);
        return Optional.ofNullable(canList)
            .map(result -> new ResponseEntity<>(
            		canList,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * DELETE  /custDetailss/:id -> delete the "id" custDetails.
     */
    @RequestMapping(value = "/custDetailss/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteCustDetails(@PathVariable Long id) {
        log.debug("REST request to delete CustDetails : {}", id);
        custDetailsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("custDetails", id.toString())).build();
    }
    
    
    
    /**
     * Get By Category Count
     */
    /*@RequestMapping(value = "/custDetails/getAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<CustDetails>> getCategoryCount(@RequestBody Long dmaId)
			throws Exception {
    	log.debug("REST request to getCaterogoryCount : {}");
    	
    	//List<TariffCategoryMaster> lists = custDetailsRepository.countByTariffCategorMaster(dmaId);
    	
    	List<CustDetails> categoryCount = custDetailsRepository.findAll();
    
    	return Optional.ofNullable(categoryCount)
				.map(result -> new ResponseEntity<>(categoryCount, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}*/
    
    
    @RequestMapping(value = "/custDetails/getAll", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<CustDetailsReportDTO> getCustDetailsReport(
			@RequestBody CustDetailsReportDTO custDetailsReportDTO) throws URISyntaxException {
		log.debug("REST request to save Customer : {}", custDetailsReportDTO);
		
			Long totalConnection = custDetailsRepository.count();
			custDetailsReportDTO.setTotalConnection(totalConnection);
			if(custDetailsReportDTO.getDmaId()!=null){
				DivisionMaster divisionMaster = divisionMasterRepository.findOne(custDetailsReportDTO.getDmaId());
				Long totalInOneDma = custDetailsRepository.countByDivisionMaster(divisionMaster);
				custDetailsReportDTO.setDmaName(divisionMaster.getDivisionName());
				custDetailsReportDTO.setTotalInOneDma(totalInOneDma);

				List<CustDetails> custDetailss = custDetailsRepository.findByDivisionMaster(divisionMaster);
				Set<TariffCategoryMaster> items = new HashSet<TariffCategoryMaster>();
		        Iterator<CustDetails> iterator = custDetailss.iterator();
		        TariffCategoryMaster tariffCategoryMaster = null;
		        while(iterator.hasNext()){
		        	tariffCategoryMaster = iterator.next().getTariffCategoryMaster();
		        	items.add(tariffCategoryMaster);
		         }
		        HashMap<String,Integer> hm=new HashMap<String,Integer>();
	
				for (TariffCategoryMaster row : items) {
					String key = row.getTariffCategory();
					TariffCategoryMaster tcm = tariffCategoryMasterRepository.findOne(row.getId());
					Integer value = custDetailsRepository.countByTariffCategoryMasterAndDivisionMaster(tcm, divisionMaster);
					hm.put(key, value);
				}
	        
	        custDetailsReportDTO.setTariffCategoryMasters(items);
	        custDetailsReportDTO.setTariffsCounts(hm);
		}
		return Optional.ofNullable(custDetailsReportDTO)
				.map(result -> new ResponseEntity<>(custDetailsReportDTO, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
    
    
    
    
    
/*    *//**
     * GET  /custDetailss/:can -> get the "can" custDetails.
     *//*
    @RequestMapping(value = "/custDetailss/search",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<CustDetails> getCustDetailsCan(@RequestParam(value = "can", required = false) String can) {
        log.debug("REST request to get CustDetails : {}", can);
        CustDetails custDetails = custDetailsRepository.findByCan(can);
        return Optional.ofNullable(custDetails)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/
}

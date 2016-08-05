package com.callippus.water.erp.web.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.TariffCategoryMaster;
import com.callippus.water.erp.repository.CustDetailsCustomRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.DivisionMasterRepository;
import com.callippus.water.erp.repository.ReportsCustomRepository;
import com.callippus.water.erp.repository.TariffCategoryMasterRepository;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

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
	
	@Inject
    private ReportsCustomRepository reportsRepository;
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
     * Get By Category PDF 
     */
    @RequestMapping(value = "/custDetailss/report/{dmaId}/{categoryId}", method = RequestMethod.GET)
	@ResponseBody
	public void getCustDetailsReport(HttpServletResponse response,
			@PathVariable Long dmaId, @PathVariable Long categoryId) throws JRException,
			IOException {
		log.debug("REST request to save Customer : {}", categoryId);
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("dmaId", dmaId);
		params.put("categoryId", categoryId);
		JasperPrint jasperPrint = null;
		/*if(dmaId == 0 && categoryId == 0){*/
			 jasperPrint = reportsRepository
					.generateReport("/reports/DivisionCategory.jasper", params);
		/*}
		
		else if(dmaId != 0 && categoryId == 0){
			 jasperPrint = reportsRepository
					.generateReport("/reports/DivisionCategoryDW.jasper", params);
		}
		else if(dmaId == 0 && categoryId != 0){
			 jasperPrint = reportsRepository
					.generateReport("/reports/DivisionCategoryTCW.jasper", params);
		
		}
		else if(dmaId != 0 && categoryId != 0){
			 jasperPrint = reportsRepository
					.generateReport("/reports/DivisionCategoryDivCat.jasper", params);
			 
		}*/
		
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=DivisionCategory_Report_" + categoryId + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
    
    
    
    /**
     * Get By Category PDF2 
     * @throws ParseException 
     */
    @RequestMapping(value = "/custDetailss/report/{dmaId}/{categoryId}/{fromDate}/{toDate}", method = RequestMethod.GET)
	@ResponseBody
	public void getCustDetailsReport1(HttpServletResponse response,
			@PathVariable Long dmaId, @PathVariable Long categoryId , @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate fromDate , @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate toDate) throws JRException,
			IOException, ParseException {
    	log.debug("REST request to save Customer : {}", categoryId);
   	
    	
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("dmaId", dmaId);
		params.put("categoryId", categoryId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		JasperPrint jasperPrint = null;
		
		 /*if(dmaId == 0 && categoryId == 0 && fromDate != null &&  toDate != null){*/
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReport.jasper", params);
			 
		/*}
		 else if(dmaId != 0 && categoryId != 0 && fromDate != null &&  toDate != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReport.jasper", params);
			 
		}	
		 else if(dmaId == 0 && categoryId != 0 && fromDate != null &&  toDate != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportCategory.jasper", params);
			 
		}
		 else if(dmaId != 0 && categoryId == 0 && fromDate != null &&  toDate != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportDMA.jasper", params);
			 
		}*/
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=BillCollectionReport.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
     
    
    
    /**
     * Get By Category PDF3 
     * @throws ParseException 
     */
    @RequestMapping(value = "/custDetailss/report/detailByCan/{dmaId}/{categoryId}/{fromDate}/{toDate}", method = RequestMethod.GET)
	@ResponseBody
	public void getCustDetailsReportDetails(HttpServletResponse response,
			@PathVariable Long dmaId, @PathVariable Long categoryId , @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate fromDate , @PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  LocalDate toDate) throws JRException,
			IOException, ParseException {
    	log.debug("REST request to save Customer : {}", categoryId);
   	
    	
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("dmaId", dmaId);
		params.put("categoryId", categoryId);
		params.put("fromDate", fromDate);
		params.put("toDate", toDate);
		JasperPrint jasperPrint = null;
		
		 /*if(dmaId == 0 && categoryId == 0 && fromDate != null &&  toDate != null){*/
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportDetail.jasper", params);
			 
		/*}
		 else if(dmaId != 0 && categoryId != 0 && fromDate != null &&  toDate != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportDetail.jasper", params);
			 
		}	
		 else if(dmaId == 0 && categoryId != 0 && fromDate != null &&  toDate != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportDetailCategory.jasper", params);
			 
		}
		 else if(dmaId != 0 && categoryId == 0 && fromDate != null &&  toDate != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportDetailDMA.jasper", params);
			 
		}*/
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=BillCollectionReportDetail.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
    
    
    
    
    /**
     * Get By Category PDF4
     * @throws ParseException 
     */
    @RequestMapping(value = "/collDetailss/reports/{dmaId}/{categoryId}/{year}/{month}", method = RequestMethod.GET)
	@ResponseBody
	public void getCollectionDetailsYearlyReport(HttpServletResponse response,
			@PathVariable Long dmaId, @PathVariable Long categoryId , @PathVariable String year , @PathVariable String month) throws JRException,
			IOException, ParseException {
    	log.debug("REST request to save Customer : {}", categoryId);
   	
    	
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("dmaId", dmaId);
		params.put("categoryId", categoryId);
		params.put("year", year);
		params.put("month", month);
		JasperPrint jasperPrint = null;
		
		/* if(dmaId == 0 && categoryId == 0 && year != null &&   month != null){*/
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReport.jasper", params);
			 
/*		}
		 else if(dmaId != 0 && categoryId != 0 && year != null &&  month != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReport.jasper", params);
			 
		}	
		 else if(dmaId == 0 && categoryId != 0 && year != null &&  month != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionReportYearlyCategory.jasper", params);
			 
		}
		 else if(dmaId != 0 && categoryId == 0 && year != null &&   month != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReportDMA.jasper", params);
			 
		}*/
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=BillCollectionYearlyReport.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
     
    /**
     * Get By Category PDF4
     * @throws ParseException 
     */
    @RequestMapping(value = "/collDetailss/reportDetails/{dmaId}/{categoryId}/{year}/{month}", method = RequestMethod.GET)
	@ResponseBody
	public void getCollectionDetailsYearlyReportDetails(HttpServletResponse response,
			@PathVariable Long dmaId, @PathVariable Long categoryId , @PathVariable String year , @PathVariable String month) throws JRException,
			IOException, ParseException {
    	log.debug("REST request to save Customer : {}", categoryId);
   	
    	
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("dmaId", dmaId);
		params.put("categoryId", categoryId);
		params.put("year", year);
		params.put("month", month);
		JasperPrint jasperPrint = null;
		
		/* if(dmaId == 0 && categoryId == 0 && year != null &&   month != null){*/
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReportDetail.jasper", params);
			 
/*		}
		 else if(dmaId != 0 && categoryId != 0 && year != null &&  month != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReportDetail.jasper", params);
			 
		}	
		 else if(dmaId == 0 && categoryId != 0 && year != null &&  month != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReportDetailCategory.jasper", params);
			 
		}
		 else if(dmaId != 0 && categoryId == 0 && year != null &&   month != null){
			 jasperPrint = reportsRepository
					.generateReport("/reports/BillCollectionYearlyReportDetailDMA.jasper", params);
			 
		}*/
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=BillCollectionYearlyReportDetails.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
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

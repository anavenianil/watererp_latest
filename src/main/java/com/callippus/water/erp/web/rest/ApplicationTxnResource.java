package com.callippus.water.erp.web.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.CustMeterMapping;
import com.callippus.water.erp.domain.FeasibilityStudy;
import com.callippus.water.erp.domain.MeterDetails;
import com.callippus.water.erp.domain.MeterStatus;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.mappings.CustDetailsMapper;
import com.callippus.water.erp.repository.ApplicationTxnCustomRepository;
import com.callippus.water.erp.repository.ApplicationTxnRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.CustMeterMappingRepository;
import com.callippus.water.erp.repository.FeasibilityStudyRepository;
import com.callippus.water.erp.repository.MeterDetailsRepository;
import com.callippus.water.erp.repository.MeterStatusRepository;
import com.callippus.water.erp.repository.ProceedingsRepository;
import com.callippus.water.erp.repository.ReportsCustomRepository;
import com.callippus.water.erp.repository.TariffCategoryMasterRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.web.rest.dto.RequestCountDTO;
import com.callippus.water.erp.web.rest.util.HeaderUtil;
import com.callippus.water.erp.web.rest.util.PaginationUtil;
import com.callippus.water.erp.workflow.applicationtxn.service.ApplicationTxnWorkflowService;
import com.callippus.water.erp.workflow.service.WorkflowService;
import com.codahale.metrics.annotation.Timed;

/**
 * REST controller for managing ApplicationTxn.
 */
@RestController
@RequestMapping("/api")
public class ApplicationTxnResource {

    private final Logger log = LoggerFactory.getLogger(ApplicationTxnResource.class);
        
    @Inject
    private ApplicationTxnRepository applicationTxnRepository;
    
    @Inject
    private WorkflowService workflowService;
    
    @Inject 
    private ApplicationTxnWorkflowService applicationTxnWorkflowService;
    
    @Inject
    private ApplicationTxnCustomRepository applicationTxnCustomRepository;
    
    @Inject
    private FeasibilityStudyRepository feasibilityStudyRepository;
    
    @Inject
    private CustDetailsRepository custDetailsRepository;
    
    @Inject
    private TariffCategoryMasterRepository tariffCategoryMasterRepository;
    
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private ReportsCustomRepository reportsRepository;
    
    @Inject
    private CustMeterMappingRepository custMeterMappingRepository;
    
    @Inject
    private ConfigurationDetailsRepository configurationDetailsRepository;
    
    @Inject
    private ProceedingsRepository proceedingsRepository;
    
    @Inject
    private MeterDetailsRepository meterDetailsRepository;
    
    @Inject
    private MeterStatusRepository meterStatusRepository;
    
    /**
     * POST  /applicationTxns -> Create a new applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<ApplicationTxn> createApplicationTxn(HttpServletRequest request,
    		@RequestBody ApplicationTxn applicationTxn) throws URISyntaxException, Exception {
        log.debug("REST request to save ApplicationTxn : {}", applicationTxn);
        if (applicationTxn.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("applicationTxn", "idexists", "A new applicationTxn cannot already have an ID")).body(null);
        }
        if(applicationTxn.getStatus()==null){
        	applicationTxn.setStatus(0);
        }
        applicationTxn.setPhoto("");
        applicationTxnRepository.save(applicationTxn);
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("photo", "setPhoto");
        UploadDownloadResource.setValues(applicationTxn, hm, request, applicationTxn.getId());
        
        //ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        //this is for workflow for new request
        try{
        	workflowService.getUserDetails();
        	applicationTxnWorkflowService.createTxn(applicationTxn);
        }
        catch(Exception e){
        	System.out.println(e);
        }
        Long uid = Long.valueOf(workflowService.getRequestAt()) ;
        applicationTxn.setRequestAt(userRepository.findById(uid));
        ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        
        return ResponseEntity.created(new URI("/api/applicationTxns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("applicationTxn", result.getId().toString()))
            .body(result);
    }

    
    
    /**
     * PUT  /applicationTxns -> Updates an existing applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional
    public ResponseEntity<ApplicationTxn> updateApplicationTxn(HttpServletRequest request,
    		@RequestBody ApplicationTxn applicationTxn) throws URISyntaxException, Exception {
        log.debug("REST request to update ApplicationTxn : {}", applicationTxn);
        if (applicationTxn.getId() == null) {
            return createApplicationTxn(request, applicationTxn);
        }
        if(applicationTxn.getMeterDetails()!=null){
        	applicationTxn.setMeterNo(applicationTxn.getMeterDetails().getMeterId());
        	MeterDetails meterDetails = applicationTxn.getMeterDetails();
        		meterDetails.setMeterStatus(meterStatusRepository.findByStatus("Allotted"));
        		meterDetailsRepository.save(meterDetails);
        }
        
        ApplicationTxn result = applicationTxnRepository.save(applicationTxn);
        
        if(applicationTxn.getStatus()==7){
        	CustDetails custDetails = CustDetailsMapper.INSTANCE.appTxnToCustDetails(applicationTxn);            
            custDetails.setId(null);
            custDetails.setConsName(applicationTxn.getFirstName()+" "+applicationTxn.getMiddleName()+" "+applicationTxn.getLastName());
            custDetails.setSecName(applicationTxn.getDivisionMaster().getDivisionName()+" "+applicationTxn.getStreetMaster().getStreetName());
            
            custDetails.setBoardMeter(configurationDetailsRepository.findOneByName("BOARD_METER").getValue());
            custDetails.setCity(configurationDetailsRepository.findOneByName("CITY").getValue());
            custDetails.setPinCode(configurationDetailsRepository.findOneByName("PIN").getValue());
            custDetails.setSewerage(configurationDetailsRepository.findOneByName("SEWERAGE_CONN").getValue());
            
            custDetails.setPipeSize(proceedingsRepository.findByApplicationTxn(applicationTxn).getPipeSizeMaster().getPipeSize());
            
            CustDetails cd = custDetailsRepository.save(custDetails);
            
            //saving to CustMetermMapping
            CustMeterMapping custMeterMapping =new CustMeterMapping();
            custMeterMapping.setId(null);
            custMeterMapping.setCustDetails(cd);
            custMeterMapping.setMeterDetails(applicationTxn.getMeterDetails());
            custMeterMapping.setFromDate(applicationTxn.getConnectionDate());
            custMeterMappingRepository.save(custMeterMapping);
        }
        if(applicationTxn.getStatus() == 6|| applicationTxn.getStatus()==7){
        	 try{
             	applicationTxnWorkflowService.approveRequest(applicationTxn.getId(), applicationTxn.getRemarks());
             }
             catch(Exception e){
             	System.out.println(e);
             }
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("applicationTxn", applicationTxn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /applicationTxns -> get all the applicationTxns.
     */
    @RequestMapping(value = "/applicationTxns",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<ApplicationTxn>> getAllApplicationTxns(Pageable pageable,
    		@RequestParam(value = "login", required = false) String login)
        throws URISyntaxException {
        log.debug("REST request to get a page of ApplicationTxns");
        //Page<ApplicationTxn> page = applicationTxnRepository.findAll(pageable); 
        Page<ApplicationTxn> page;
        if(login == null){
        	page = applicationTxnRepository.findAllByOrderByStatusAsc(pageable);
        }
        else
        {
        	page = applicationTxnRepository.findByRequestAt(pageable, login);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/applicationTxns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /applicationTxns/:id -> get the "id" applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<ApplicationTxn> getApplicationTxn(@PathVariable Long id) {
        log.debug("REST request to get ApplicationTxn : {}", id);
        ApplicationTxn applicationTxn = applicationTxnRepository.findOne(id);
        return Optional.ofNullable(applicationTxn)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /applicationTxns/:id -> delete the "id" applicationTxn.
     */
    @RequestMapping(value = "/applicationTxns/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteApplicationTxn(@PathVariable Long id) {
        log.debug("REST request to delete ApplicationTxn : {}", id);
        applicationTxnRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("applicationTxn", id.toString())).build();
    }
    
    /**
     * this will approve the Connection Request
     */
	@RequestMapping(value = "/applicationTxns/approveRequest", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@Transactional
	public ResponseEntity<Void> approveApplication(@RequestParam(value = "id", required = false) Long id,
						@RequestParam(value = "remarks", required = false) String remarks)throws Exception{
		workflowService.getUserDetails();
		ApplicationTxn applicationTxn = applicationTxnRepository.findOne(id);
	    workflowService.setRemarks(remarks);  
	    Integer status = applicationTxn.getStatus();
	    status +=1;
	    applicationTxn.setStatus(status);
        workflowService.setRequestStatus(status);
        applicationTxnWorkflowService.approvedApplicationTxnRequest(applicationTxn);
        /*if(workflowService.getRequestStatus() == 2){
        	applicationTxnWorkflowService.updateApplicationTxn(id);
        }*/
        if(workflowService.getRequestAt()!=null){
        	Long uid = Long.valueOf(workflowService.getRequestAt()) ;
            applicationTxn.setRequestAt(userRepository.findById(uid));
        }
        applicationTxnRepository.save(applicationTxn);
        return ResponseEntity.ok().build();
	}
	    
    
    /**
     * Decline the request
     */
    @RequestMapping(value = "/applicationTxns/declineRequest",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<Void> declineRequests(
			@RequestParam(value = "id", required = false) Long id, HttpServletResponse response)
			throws Exception {
		log.debug("REST request to get Requisition : {}", id);
		
		applicationTxnWorkflowService.declineRequest(id);
		return ResponseEntity.ok().build();
	}
    
    
    /**
     * Display count of pending request to the dashboard
     */
    
    @RequestMapping(value = "/applicationTxns/getPendingRequests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestCountDTO>> getPendingRequests(HttpServletResponse response)throws Exception {
        log.debug("REST request to get Requisition : {}");
        List<RequestCountDTO> pendingRequests = applicationTxnCustomRepository.countPendingRequests();

        return new ResponseEntity<>(pendingRequests, HttpStatus.OK);
    }
    
    
    /**
     * Display count of approved request to the dashboard
     */
    
    @RequestMapping(value = "/applicationTxns/getApprovedRequests",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<RequestCountDTO>> getApprovedRequests(HttpServletResponse response)throws Exception {
        log.debug("REST request to get Requisition : {}");
        List<RequestCountDTO> approvedRequests = applicationTxnCustomRepository.countApprovedRequests();

        return new ResponseEntity<>(approvedRequests, HttpStatus.OK);
    }
    
    
    /**
     * Display PENDING request to the dashboard when clicked on Pending Requests button
     */
    @RequestMapping(value = "/applicationTxns/getRequests/pending/{type}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<RequestWorkflowHistory>> getAllPendingRequests(HttpServletResponse response, @PathVariable String type)
			throws Exception {
		log.debug("REST request to get Requisition : {}");
		List<RequestWorkflowHistory> requestWorkflowHistorysStatus = applicationTxnCustomRepository.listAllPendingRequests(type);

		return new ResponseEntity<>(requestWorkflowHistorysStatus,
				HttpStatus.OK);
	}
    
    
    /**
     * Display APPROVED request to the dashboard when clicked on Approved Requests button
     */
    @RequestMapping(value = "/applicationTxns/getRequests/approved/{type}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
	public ResponseEntity<List<RequestWorkflowHistory>> getAllApprovedRequests(HttpServletResponse response, @PathVariable String type)
			throws Exception {
		log.debug("REST request to get getApproved Request : {}");
		List<RequestWorkflowHistory> requestWorkflowHistorysStatus = applicationTxnCustomRepository
				.listAllApprovedRequests(type);

		return new ResponseEntity<>(requestWorkflowHistorysStatus,
				HttpStatus.OK);
	}

    /**
     * this will generate can
     */
	@RequestMapping(value = "/applicationTxns/can", 
			method = RequestMethod.GET, 
			produces = MediaType.TEXT_PLAIN_VALUE)
	@Timed
	public ResponseEntity<String> generateCan(@RequestParam(value = "feasibilityId", required = false) Long feasibilityId)
			throws Exception{
		log.debug("REST request to get CAN : {}");
		FeasibilityStudy feasibility = feasibilityStudyRepository.findOne(feasibilityId);
		String division = feasibility.getDivisionMaster().getDivisionCode();
		String street = feasibility.getStreetMaster().getStreetNo();
		//String can = division.substring(0, 2) + "-" +street.substring(0, 2);
		Integer can = applicationTxnRepository.findByCan(division, street);
		if(can == null){
			can =1;
		}
		else{
			can = can+1;
		}
		String s1 = String.format("%04d", can);
		String newCan = division + street + s1.toString();
        return new ResponseEntity<String>(
        		newCan,
                    HttpStatus.OK);
		
	} 
	
	
	@RequestMapping(value = "/applicationTxns/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ApplicationTxn>> search(
			@RequestParam(value = "applicationTxnNo", required = false) String applicationTxnNo,
			@RequestParam(value = "applicationTxnDt", required = false) String applicationTxnDt,
			@RequestParam(value = "statusSearch", required = false) String statusSearch,
			@RequestParam(value = "typeSearch", required = false) String typeSearch
			)
			throws URISyntaxException, Exception {
		log.debug("ApplicationTxn -------- search: {}");
		List<ApplicationTxn> applicationTxns;
		
		/*workflowService.getUserDetails();
		Long userId = Long.parseLong(workflowService.getSfID());

		String whereClause = "requestAt.id=" + userId +" ";*/
		String whereClause = null;
		
		if(applicationTxnNo != null && !applicationTxnNo.equals(""))
			whereClause = " at.id =" + applicationTxnNo ;

		if(applicationTxnDt != null && !applicationTxnDt.equals(""))
			whereClause = " date(requested_date) = '" + applicationTxnDt  +"' ";
		
		if(statusSearch != null && !statusSearch.equals(""))
			whereClause = " status = " + statusSearch +" ";
		
		if(typeSearch != null && !typeSearch.equals("")){
			if(typeSearch.equals("0")){
				whereClause = " user_id is null ";
			}
			else{
				whereClause = " user_id is not null ";
			}
		}

		applicationTxns = applicationTxnCustomRepository.search(
					whereClause);
		
		return new ResponseEntity<List<ApplicationTxn>>(applicationTxns,
				 HttpStatus.OK);
	}
	
	
	/**
	 * Display Requisition report
	 */
	@RequestMapping(value = "/applicationTxns/report/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void getApplicationTxnReport(HttpServletResponse response, @PathVariable Long id) throws JRException,
			IOException {
		
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("id",id);

		JasperPrint jasperPrint = reportsRepository
				.generateReport("/reports/Application_txn.jasper", params);
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition",
				"inline; filename=ApplicationTxn_Report_" + id + ".pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
}

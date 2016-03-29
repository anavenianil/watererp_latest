package com.callippus.water.erp.service;

import com.callippus.water.erp.domain.Authority;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.PersistentToken;
import com.callippus.water.erp.domain.User;
import com.callippus.water.erp.repository.AuthorityRepository;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.PersistentTokenRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.security.SecurityUtils;
import com.callippus.water.erp.service.util.RandomUtil;
import com.callippus.water.erp.web.rest.dto.ManagedUserDTO;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.LocalDate;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

import javax.inject.Inject;

import java.util.*;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class BillingService {

    private final Logger log = LoggerFactory.getLogger(BillingService.class);

    @Inject
    private BillDetailsRepository billDetailsRepository;
    
    @Inject
    private CustDetailsRepository custDetailsRepository;

    enum Status {SUCCESS, FAILURE};
    
    enum CustValidation {ALREADY_BILLED, INVALID_METER_READING, INVALID_PIPESIZE, INVALID_CATEGORY, NOT_IMPLEMENTED, INVALID_PREV_BILL_MONTH, CUSTOMER_DOES_NOT_EXIST, SUCCESS};
    
    List<String> categories = Arrays.asList("D", "DS", "N");
    
    public void generateBill() {
    	
    	List<BillDetails> bd = billDetailsRepository.findAll(); 
    	processBills(bd);
    }
    
    public void processBills(List<BillDetails> bd)
    {
    	bd.forEach(bill_details -> process_bill(bill_details));
    }
    
    public void process_bill(BillDetails bill_details)
    {
    	float avgKL;
    	
    	CustDetails customer = custDetailsRepository.findByCan(bill_details.getCan());    	
    	CustValidation retVal =  getCustInfo(customer, bill_details);
    	
    	if(retVal != CustValidation.SUCCESS){
    		//Unable to process customer
        	log.debug("Unable to process customer:" + customer.getId() +", getCustInfo returned::" + retVal.name());
        	return;
    	}
    	
    	try
    	{	
	    	Date from = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(bill_details.getFrom_month()+"01");
	    	Date to = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(bill_details.getTo_month()+"01");
	    	
	    	DateTime jFrom = new DateTime(from);
	    	DateTime jTo = new DateTime(to);
	    	
	    	Months d = Months.monthsBetween( jTo, jFrom);
	    	int monthsDiff = d.getMonths() + 1;
	    	
	    	log.debug("Customer Info:" + customer.toString());
	    	log.debug("Months:" + monthsDiff);
	    	
	    	if(customer.getPrevBillType().equals("L") && bill_details.getCurrent_bill_type().equals("M")){
	    		avgKL = (Integer.parseInt(bill_details.getPresent_reading()) - Integer.parseInt(bill_details.getInitial_reading()))/1000;
	    		log.debug("avgKL:" + avgKL);
	    	}
	    		
    	}
    	catch(Exception e)
    	{
    		log.debug("Invalid From or To Date:" + bill_details.getFrom_month() +"::::" + bill_details.getTo_month());
    		return;
    	}
    	
    }
    
    public CustValidation getCustInfo(CustDetails customer, BillDetails bill_details){
    	
    	if(Float.parseFloat(customer.getPipeSize()) < 0.5f)
    		return CustValidation.INVALID_PIPESIZE;
    	
    	if(customer.getPrevBillMonth() == null || customer.getPrevBillMonth().equals(""))
    		return CustValidation.INVALID_PREV_BILL_MONTH;
    	
    	if(Integer.parseInt(customer.getPrevBillMonth()) < 20050101)
    		return CustValidation.NOT_IMPLEMENTED;
    	
    	if(!categories.contains(customer.getCategory().trim()))
    		return CustValidation.INVALID_CATEGORY;
    	
    	if(Integer.parseInt(bill_details.getPresent_reading()) < Integer.parseInt(bill_details.getInitial_reading()))
    			return CustValidation.INVALID_METER_READING;
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONTH, -1);
    	
    	if(cal.get(Calendar.MONTH) != Integer.parseInt(customer.getPrevBillMonth().substring(4, 5))
    			|| cal.get(Calendar.YEAR) != Integer.parseInt(customer.getPrevBillMonth().substring(1, 4)))    		
    		return CustValidation.ALREADY_BILLED;
    	
    	
    	return CustValidation.SUCCESS;
    }
    
}

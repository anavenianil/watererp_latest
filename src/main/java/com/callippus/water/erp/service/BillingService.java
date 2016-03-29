package com.callippus.water.erp.service;

import com.callippus.water.erp.domain.Authority;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
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
        
    @Inject
    private BillFullDetails bfd;

    enum Status {SUCCESS, FAILURE};
    
    enum CustValidation {ALREADY_BILLED, INVALID_BILL_TYPE, INVALID_METER_READING, INVALID_PIPESIZE, INVALID_CATEGORY, NOT_IMPLEMENTED, INVALID_PREV_BILL_MONTH, CUSTOMER_DOES_NOT_EXIST, SUCCESS};
    
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
    	float avgKL = 0.0f;
    	float factor = 0.0f;
    	float prevAvgKL = 0.0f;
    	long units = 0;
    	String monthUpto;
    	
    	log.debug("Process customer with CAN:" + bill_details.getCan());
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
	    	
	    	Months d = Months.monthsBetween( jFrom, jTo);
	    	int monthsDiff = d.getMonths() + 1;
	    	
	    	log.debug("Customer Info:" + customer.toString());
	    	log.debug("Months:" + monthsDiff);
	    	
	    	if(!customer.getPrevReading().equals("0") && monthsDiff != 0)
	    		avgKL = (Integer.parseInt(bill_details.getPresent_reading()) - Integer.parseInt(bill_details.getInitial_reading()))/monthsDiff;
	    	
	    	prevAvgKL = (Float.parseFloat(customer.getPrevAvgKl()) < 1.0f ? 1.0f:Float.parseFloat(customer.getPrevAvgKl()));
	    	
	    	factor = avgKL/prevAvgKL;
	    	
	    	if (factor > 4.0f || factor < 0.25f) {
	    		//Unable to process customer
	        	log.debug("Meter reading for:" + customer.getId() +": is ::" + bill_details.getPresent_reading()+". This is too high or too low.");
	        	return;
	    	}
	    	
	    	if(!bill_details.getCurrent_bill_type().equals("M"))
	    		bfd.setPresentReading(customer.getPrevReading());
	    		    	
	    	//Previously Metered or Locked and currently Metered
	    	if((customer.getPrevBillType().equals("L") || customer.getPrevBillType().equals("M") ) && bill_details.getCurrent_bill_type().equals("M")){
	    		units = Long.parseLong(bill_details.getPresent_reading()) - Long.parseLong(bill_details.getInitial_reading());
	    		log.debug("units:" + units);
	    	}
	    	
	    	monthUpto = getPrevMonthStart();
	    	
//	        d->hassewer = (strcmp(c->sewerage, "T") == 0 ? TRUE : FALSE);
//	        d->pipesize = atof(c->pipesize);
//	        d->resunits = atoi(c->resUnits);
//	        syslog(LOG_INFO, "Resunits=%d\n", d->resunits);
//	        d->agreedqty = 0;
//	        d->kl = 31;			// Will be calculated in getCurrReading.
//	        d->water = 0;
//	        d->sewerage = 0;
//	        d->surcharge = 0;
//	        d->totalcess = 0;
//	        strcpy(d->category, c->category);
//	        d->from = 0;
//	        d->upto = 0;
//	        d->mths = 0;
//	        d->avgkl = atof(c->prevAvgKL);
//	        d->errCode = '0';
//	        d->svrStatus = '0';
	    		
    	}
    	catch(Exception e)
    	{
    		log.debug("Invalid From or To Date:" + bill_details.getFrom_month() +"::::" + bill_details.getTo_month());
    		return;
    	}
    	
    }
    
    public String getPrevMonthStart()
    {
    	Calendar aCalendar = Calendar.getInstance();
    	// add -1 month to current month
    	aCalendar.add(Calendar.MONTH, -1);
    	// set DATE to 1, so first date of previous month
    	aCalendar.set(Calendar.DATE, 1);

    	java.util.Date date = aCalendar.getTime();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    	
    	return formatter.format(date);
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
    	
    	if(cal.get(Calendar.MONTH) == Integer.parseInt(customer.getPrevBillMonth().substring(4, 5))
    			&& cal.get(Calendar.YEAR) == Integer.parseInt(customer.getPrevBillMonth().substring(0, 4)))   		
    		return CustValidation.ALREADY_BILLED;
    	
    	if((customer.getPrevBillType().equals("M") || customer.getPrevBillType().equals("L")) && bill_details.getCurrent_bill_type().equals("U"))
    		return CustValidation.INVALID_BILL_TYPE;
    	
    	if(customer.getPrevBillType().equals("R") && !bill_details.getCurrent_bill_type().equals("R"))
    		return CustValidation.INVALID_BILL_TYPE;    	
    	
    	if(customer.getPrevBillType().equals("U") && !bill_details.getCurrent_bill_type().equals("U"))
    		return CustValidation.INVALID_BILL_TYPE; 
    	
    	if(customer.getMetReadingMo().equals("") && bill_details.getCurrent_bill_type().equals("M"))
    		return CustValidation.INVALID_PREV_BILL_MONTH;
    				
    	return CustValidation.SUCCESS;
    }
    
}

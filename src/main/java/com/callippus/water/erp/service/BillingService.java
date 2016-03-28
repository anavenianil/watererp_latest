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
    
    enum CustValidation {ALREADY_BILLED, INVALID_PIPESIZE, INVALID_CATEGORY, NOT_IMPLEMENTED, INVALID_PREV_BILL_MONTH, CUSTOMER_DOES_NOT_EXIST, SUCCESS};
    
    List<String> categories = Arrays.asList("D", "DS", "N");
    
    public void generateBill() {
    	
    	List<BillDetails> bd = billDetailsRepository.findAll(); 
    	processBills(bd);
    }
    
    public void processBills(List<BillDetails> bd)
    {
    	List<CustDetails> cd = custDetailsRepository.findAll();
    	cd.forEach(customer -> process_bill(customer));
    }
    
    public void process_bill(CustDetails customer)
    {
    	CustValidation retVal =  getCustInfo(customer);
    }
    
    public CustValidation getCustInfo(CustDetails customer){
    	
    	if(Float.parseFloat(customer.getPipeSize()) < 0.5f)
    		return CustValidation.INVALID_PIPESIZE;
    	
    	if(customer.getPrevBillMonth() == null || customer.getPrevBillMonth().equals(""))
    		return CustValidation.INVALID_PREV_BILL_MONTH;
    	
    	if(Integer.parseInt(customer.getPrevBillMonth()) < 20050101)
    		return CustValidation.NOT_IMPLEMENTED;
    	
    	if(!categories.contains(customer.getCategory().trim()))
    		return CustValidation.INVALID_CATEGORY;
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    	Calendar cal = Calendar.getInstance();
    	cal.add(Calendar.MONTH, -1);
    	
    	if(cal.get(Calendar.MONTH) != Integer.parseInt(customer.getPrevBillMonth().substring(4, 5))
    			|| cal.get(Calendar.YEAR) != Integer.parseInt(customer.getPrevBillMonth().substring(1, 4)))    		
    		return CustValidation.ALREADY_BILLED;
    	
    	
    	return CustValidation.SUCCESS;
    }
    
}

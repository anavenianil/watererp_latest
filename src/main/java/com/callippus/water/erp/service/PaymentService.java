package com.callippus.water.erp.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.enumeration.CustStatus;
import com.callippus.water.erp.repository.CustDetailsRepository;

@Service
@Transactional
public class PaymentService {

	private static final Logger log = LoggerFactory
			.getLogger(PaymentService.class);

	@Inject
	private CustDetailsRepository custDetailsRepository;
	
	public boolean postPayment(String can, BigDecimal amount)
	{
		CustDetails customer = custDetailsRepository.findByCanForUpdate(can);
		
		log.debug("***** Customer Arrears before payment:" + customer.getArrears());
		
		log.debug("***** Customer Payment Amount:" + amount);
		
		customer.setArrears(customer.getArrears().subtract(amount));
						
		if(customer.getStatus()==CustStatus.DISCONNECTED && customer.getArrears().compareTo(new BigDecimal("0.00")) <= 0){
			customer.setStatus(CustStatus.DEACTIVE);
		}
		
		custDetailsRepository.save(customer);
		
		customer = custDetailsRepository.findOne(customer.getId());
		
		log.debug("***** Customer Arrears after payment:" + customer.getArrears());
		
		return true;
	}
}

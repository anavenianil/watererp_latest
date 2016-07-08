package com.callippus.water.erp.service;

import java.math.BigDecimal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.enumeration.CustStatus;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.InvoicePaymentsRepository;

@Service
@Transactional
public class PaymentService {

	private static final Logger log = LoggerFactory
			.getLogger(PaymentService.class);

	@Inject
	private CustDetailsRepository custDetailsRepository;
	
	@Inject
	private InvoicePaymentsRepository invoicePaymentsRepository;
	
	public boolean postPayment(CollDetails collDetails, BigDecimal amount)
	{
		CustDetails customer = custDetailsRepository.findByCanForUpdate(collDetails.getCan());
		
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

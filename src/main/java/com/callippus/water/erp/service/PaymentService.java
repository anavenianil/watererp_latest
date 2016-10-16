package com.callippus.water.erp.service;


import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.InvoicePayments;
import com.callippus.water.erp.domain.ReversalDetails;
import com.callippus.water.erp.domain.enumeration.CustStatus;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.CollDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.InvoicePaymentsRepository;


@Service
@Transactional
public class PaymentService {

	private static final Logger log = LoggerFactory
			.getLogger(PaymentService.class);

	@Autowired
	private CustDetailsRepository custDetailsRepository;
	@Autowired
	private BillFullDetailsRepository billFullDetailsRepository;
	@Autowired
	private InvoicePaymentsRepository invoicePaymentsRepository;
	
	public boolean postPayment(CollDetails collDetails, BigDecimal amount) throws Exception
	{
		List<InvoicePayments> ipList = invoicePaymentsRepository.findByCollDetails(collDetails);
		
		if(ipList.size() != 0){
			log.debug("Payment already posted:" + collDetails.toString());
			throw new Exception("Payment already posted");
		}
					
		CustDetails customer = custDetailsRepository.findByCanForUpdate(collDetails.getCan());
		
		log.debug("***** Customer Arrears before payment:" + customer.getArrears());
		
		log.debug("***** Customer Payment Amount:" + amount);
		
		customer.setArrears(customer.getArrears().subtract(amount));
						
		if(customer.getStatus()==CustStatus.DISCONNECTED && customer.getArrears().compareTo(new BigDecimal("0.00")) <= 0){
			customer.setStatus(CustStatus.DEACTIVE);
		}
		
		custDetailsRepository.save(customer);
		
		customer = custDetailsRepository.findOne(customer.getId());
		
		List<BillFullDetails> invoices = billFullDetailsRepository.findBillsDue(customer.getCan());
		
		BigDecimal remPayment = amount;
		for(BillFullDetails invoice: invoices)
		{
			BigDecimal dueAmount = invoice.getDueAmount();
			InvoicePayments ip = new InvoicePayments();
			ip.setBillFullDetails(invoice);
			ip.setCollDetails(collDetails);
			ip.setCustDetails(customer);
			
			if(dueAmount.subtract(remPayment).compareTo(CPSConstants.ZERO) >= 0)
			{
				invoice.setDueAmount(dueAmount.subtract(remPayment));
				ip.setAmount(remPayment);
				remPayment = CPSConstants.ZERO;
				invoicePaymentsRepository.save(ip);
				billFullDetailsRepository.save(invoice);
				log.debug("Saving Invoice Payments:" + ip.toString());
				break;
			}
			else
			{
				invoice.setDueAmount(CPSConstants.ZERO);
				remPayment = remPayment.subtract(dueAmount);
				ip.setAmount(dueAmount);
				invoicePaymentsRepository.save(ip);
				billFullDetailsRepository.save(invoice);
				log.debug("Saving Invoice Payments:" + ip.toString());
			}
		}
		
		log.debug("Remaining Payment amount left:" + remPayment);
		log.debug("***** Customer Arrears after payment:" + customer.getArrears());
		
		return true;
	}
	
	
	
	/**
	 * Method to knock off the payments(method called from createReversalDetails() method of ReversalDetailsResource )
	 * tables to check(billFullDetails, custDetails, collDetails, InvoicePayment)
	 */
	public boolean knockOff(ReversalDetails reversalDetails){
		log.debug(" Reversal Details when knockOff:" + reversalDetails);

		
		BigDecimal receiptAmount = reversalDetails.getCollDetails().getReceiptAmt();//cancelled amount
		List<InvoicePayments> ipList = invoicePaymentsRepository.findByCollDetails(reversalDetails.getCollDetails());

		for(InvoicePayments invoicePayments:ipList)
		{
			BillFullDetails billFullDetails = invoicePayments.getBillFullDetails();
			CustDetails custDetails = invoicePayments.getCustDetails();
			
			BigDecimal duesAmount = invoicePayments.getBillFullDetails().getArrears();
			BigDecimal newArrears = duesAmount.add(receiptAmount);
			billFullDetails.setArrears(newArrears);//arrears after cancellation
			custDetails.setArrears(newArrears);
			
			billFullDetailsRepository.save(billFullDetails);
			custDetailsRepository.save(custDetails);
		}
			
		return true;
	}
}

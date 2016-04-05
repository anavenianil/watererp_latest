package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.mappings.BillMapper;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private ConfigurationDetailsRepository configurationDetailsRepository;

	@Inject
	private TariffMasterCustomRepository tariffMasterCustomRepository;

	@Inject
	private BillFullDetailsRepository bfdRepository;

	enum Status {
		SUCCESS, FAILURE
	};

	enum CustValidation {
		ALREADY_BILLED, INVALID_BILL_TYPE, INVALID_METER_READING, INVALID_PIPESIZE, INVALID_CATEGORY, NOT_IMPLEMENTED, INVALID_PREV_BILL_MONTH, CUSTOMER_DOES_NOT_EXIST, SUCCESS
	};

	List<String> categories = Arrays.asList("D", "DS", "N");

	float avgKL = 0.0f;
	float factor = 0.0f;
	float prevAvgKL = 0.0f;
	float units = 0;
	float unitsKL = 0.0f;
	String monthUpto;
	boolean hasSewer;
	float ewura = 0.0f;
	int monthsDiff = 0;
	LocalDate dFrom = null;
	LocalDate dTo = null;
	int newMeterFlag = 0;
	int unMeteredFlag = 0;
	
	ConfigurationDetails cd = null;

	float total_amount = 0.0f, net_payable_amount = 0.0f, surcharge = 0.0f,
			total_cess = 0.0f, kl = 0.0f;

	public void generateBill() {

		List<BillDetails> bd = billDetailsRepository.findAll();
		processBills(bd);
	}

	public void init() {
		avgKL = 0.0f;
		factor = 0.0f;
		prevAvgKL = 0.0f;
		units = 0;
		unitsKL = 0.0f;
		hasSewer = false;
		ewura = 0.0f;
		monthsDiff = 0;
		dFrom = null;
		dTo = null;
		newMeterFlag = 0;
		unMeteredFlag = 0;

		total_amount = 0.0f;
		net_payable_amount = 0.0f;
		surcharge = 0.0f;
		total_cess = 0.0f;
		kl = 0.0f;
		
	}

	public void processBills(List<BillDetails> bd) {
		bd.forEach(bill_details -> process_bill(bill_details));
	}

	public void process_bill(BillDetails bill_details) {
		init();
		log.debug("Process customer with CAN:" + bill_details.getCan());
		CustDetails customer = custDetailsRepository.findByCan(bill_details
				.getCan());
		CustValidation retVal = getCustInfo(customer, bill_details);

		if (retVal != CustValidation.SUCCESS) {
			// Unable to process customer
			log.debug("Unable to process customer:" + customer.getId()
					+ ", getCustInfo returned::" + retVal.name());
			return;
		}

		try {

			if (!bill_details.getCurrentBillType().equals("M"))
				bill_details.setPresentReading(customer.getPrevReading());

			// Previously Metered or Locked and currently Metered
			if ((customer.getPrevBillType().equals("L") || customer
					.getPrevBillType().equals("M"))
					&& bill_details.getCurrentBillType().equals("M")) {

				dFrom = customer.getMetReadingMo();
				dTo = LocalDate.now();

				long days = ChronoUnit.DAYS.between(customer.getMeterFixDate(), customer.getMetReadingDt());
				
				newMeterFlag = (days < 15? 1:0);
				
				log.debug("########################################");
				log.debug("          METER BILL CASE");
				log.debug("########################################");
				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom.toString() + ", To:" + dTo.toString());

				long monthsDiff = ChronoUnit.MONTHS.between(dFrom, dTo);

				log.debug("Months:" + monthsDiff);

				if (!customer.getPrevReading().equals("0") && monthsDiff != 0) {
					units = bill_details.getPresentReading()
							- bill_details.getInitialReading();

					unitsKL = (float) units / 1000.0f;

					avgKL = unitsKL / monthsDiff;

					prevAvgKL = customer.getPrevAvgKl() < 1.0f ? 1.0f
							: customer.getPrevAvgKl();

					factor = avgKL / prevAvgKL;

					log.debug("units:" + units + ", unitsKL=" + unitsKL
							+ ", avgKL=" + avgKL + ", prevAvgKL=" + prevAvgKL
							+ ", factor=" + factor);

					if (factor > 4.0f || factor < 0.25f) {
						// Unable to process customer
						log.debug("Meter reading for:" + customer.getId()
								+ ": is ::" + bill_details.getPresentReading()
								+ ". This is too high or too low.");
						return;
					}
				}

				kl = (float) (units / 1000.0);
			} else if (bill_details.getCurrentBillType().equals("L")
					|| bill_details.getCurrentBillType().equals("R")) {

				log.debug("########################################");
				log.debug("          LOCK BILL CASE");
				log.debug("########################################");

				dFrom = customer.getPrevBillMonth();
				dTo = LocalDate.now();

				long monthsDiff = ChronoUnit.MONTHS.between(dFrom, dTo);

				log.debug("Months:" + monthsDiff);

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = customer.getPrevAvgKl();
				unitsKL = (float) (avgKL * monthsDiff);
				units = (float) (avgKL * monthsDiff * 1000.0f);
				log.debug("Units:" + units + " based on avgKL:" + avgKL
						+ " for " + monthsDiff + " months.");
			} else if (bill_details.getCurrentBillType().equals("U")) {

				log.debug("########################################");
				log.debug("          UNMETERED BILL CASE");
				log.debug("########################################");

				dFrom = customer.getPrevBillMonth();
				dTo = LocalDate.now();

				long monthsDiff = ChronoUnit.MONTHS.between(dFrom, dTo);

				log.debug("Months:" + monthsDiff);

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = customer.getPrevAvgKl();
				unitsKL = (float) (avgKL * monthsDiff);
				units = (float) (avgKL * monthsDiff * 1000.0f);
				log.debug("Units:" + units + " based on avgKL:" + avgKL
						+ " for " + monthsDiff + " months.");
			}

			unMeteredFlag = (bill_details
					.getCurrentBillType().equals("U") ? 1 : 0);
			
			List<java.util.Map<String, Object>> charges = tariffMasterCustomRepository
					.findTariffs(dFrom, dTo, avgKL, unMeteredFlag, newMeterFlag);

			BillFullDetails bfd = BillMapper.INSTANCE.bdToBfd(bill_details,
					customer);
			bfd.setId(null);

			// Subtract Avg Water charges in case of Lock Bill scenario
			for (Map<String, Object> charge : charges) {
				if (((Long) charge.get("tariff_type_master_id")) == 1) {
					log.debug("Usage Charge:" + (Double) charge.get("amount"));
					bfd.setWaterCess(((Double) charge.get("amount"))
							.floatValue());
				} else if (((Long) charge.get("tariff_type_master_id")) == 2) {
					log.debug("Meter Rent:" + (Double) charge.get("amount"));
					bfd.setMeterServiceCharge(((Double) charge.get("amount"))
							.floatValue());
				} else if (((Long) charge.get("tariff_type_master_id")) == 3) {
					log.debug("Service Charge:" + (Double) charge.get("amount"));
					bfd.setServiceCharge(((Double) charge.get("amount"))
							.floatValue());
				}
			}

			hasSewer = (customer.getSewerage().equals("T") ? true : false);

			cd = configurationDetailsRepository.findOneByName("SEWERAGE");

			log.debug("This is the SEWERAGE Charge Configuration:"
					+ cd.toString());

			if (hasSewer)
				bfd.setSewerageCess(Float.parseFloat(cd.getValue())
						* bfd.getWaterCess() / 100.0f);
			
			cd = configurationDetailsRepository
					.findOneByName("EWURA");

			log.debug("This is the EWURA Configuration:" + cd.toString());
			float ewura = ((bfd.getWaterCess() + bfd.getSewerageCess()) * Float.parseFloat(cd.getValue())) / 100.0f;
						
			bfd.setSurcharge(CPSUtils.round(ewura, 2));
			
			Float total = bfd.getWaterCess() + bfd.getMeterServiceCharge()
					+ bfd.getServiceCharge() + bfd.getSewerageCess() + bfd.getSurcharge() + bfd.getOtherCharges();

			bfd.setTotalAmount(CPSUtils.round(total.floatValue(), 2));

			Float netPayable = bfd.getTotalAmount() + bfd.getIntOnArrears()
					+ bfd.getArrears();
			bfd.setNetPayableAmount(CPSUtils.round(netPayable.floatValue(), 2));

			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmmss");

			bfd.setBillDate(date.toLocalDate());
			bfd.setBillTime(date.format(formatter));

			bfd.setUnits(units);

			log.debug("This is the BillFullDetails:" + bfd);

			bfdRepository.save(bfd);

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Invalid From or To Date:" + bill_details.getFromMonth()
					+ "::::" + bill_details.getToMonth());
			return;
		}
	}

	public String getPrevMonthStart() {
		Calendar aCalendar = Calendar.getInstance();
		// add -1 month to current month
		aCalendar.add(Calendar.MONTH, -1);
		// set DATE to 1, so first date of previous month
		aCalendar.set(Calendar.DATE, 1);

		java.util.Date date = aCalendar.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

		return formatter.format(date);
	}

	public CustValidation getCustInfo(CustDetails customer,
			BillDetails bill_details) {

		if (customer.getPipeSize() < 0.5f)
			return CustValidation.INVALID_PIPESIZE;

		if (customer.getPrevBillMonth() == null
				|| customer.getPrevBillMonth().equals(""))
			return CustValidation.INVALID_PREV_BILL_MONTH;

		if (customer.getPrevBillMonth().isBefore(LocalDate.of(2005, 01, 01)))
			return CustValidation.NOT_IMPLEMENTED;

		if (!categories.contains(customer.getCategory().trim()))
			return CustValidation.INVALID_CATEGORY;

		if (bill_details.getPresentReading() < bill_details.getInitialReading())
			return CustValidation.INVALID_METER_READING;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		if (getPrevMonthStart().equals(customer.getPrevBillMonth()))
			return CustValidation.ALREADY_BILLED;

		if ((customer.getPrevBillType().equals("M") || customer
				.getPrevBillType().equals("L"))
				&& bill_details.getCurrentBillType().equals("U"))
			return CustValidation.INVALID_BILL_TYPE;

		if (customer.getPrevBillType().equals("R")
				&& !bill_details.getCurrentBillType().equals("R"))
			return CustValidation.INVALID_BILL_TYPE;

		if (customer.getPrevBillType().equals("U")
				&& !bill_details.getCurrentBillType().equals("U"))
			return CustValidation.INVALID_BILL_TYPE;

		if (customer.getMetReadingMo() == null
				&& bill_details.getCurrentBillType().equals("M"))
			return CustValidation.INVALID_PREV_BILL_MONTH;

		return CustValidation.SUCCESS;
	}

}

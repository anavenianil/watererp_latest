package com.callippus.water.erp.service;

import com.callippus.water.erp.domain.Authority;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.PersistentToken;
import com.callippus.water.erp.domain.TariffMaster;
import com.callippus.water.erp.domain.User;
import com.callippus.water.erp.repository.AuthorityRepository;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.PersistentTokenRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;
import com.callippus.water.erp.repository.UserRepository;
import com.callippus.water.erp.security.SecurityUtils;
import com.callippus.water.erp.service.util.RandomUtil;
import com.callippus.water.erp.web.rest.dto.ManagedUserDTO;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
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
	private TariffMasterCustomRepository tariffMasterCustomRepository;

	// @Inject
	// private BillFullDetails bfd;

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
	long units = 0;
	float unitsKL = 0.0f;
	String monthUpto;
	boolean hasSewer;

	float water, sewerage, service_charge, total_amount, net_payable_amount,
			surcharge, total_cess, from, upto, mths, avgkl, kl;

	String mc_met_reader_code, bill_flag;

	public void generateBill() {

		List<BillDetails> bd = billDetailsRepository.findAll();
		processBills(bd);
	}

	public void processBills(List<BillDetails> bd) {
		bd.forEach(bill_details -> process_bill(bill_details));
	}

	public void process_bill(BillDetails bill_details) {

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

			// if (!bill_details.getCurrent_bill_type().equals("M"))
			// bfd.setPresentReading(customer.getPrevReading());

			// Previously Metered or Locked and currently Metered
			if ((customer.getPrevBillType().equals("L") || customer
					.getPrevBillType().equals("M"))
					&& bill_details.getCurrent_bill_type().equals("M")) {

				Date d_from = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH)
						.parse(customer.getMetReadingMo());

				Calendar date = Calendar.getInstance();
				date.set(Calendar.DAY_OF_MONTH, 1);

				Date d_to = date.getTime();

				DateTime jFrom = new DateTime(d_from);
				DateTime jTo = new DateTime(d_to);

				ZonedDateTime zFrom = d_from.toInstant().atZone(
						ZoneId.systemDefault());
				ZonedDateTime zTo = d_to.toInstant().atZone(
						ZoneId.systemDefault());

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + zFrom.toString() + ", To:" + zTo.toString());

				Months d = Months.monthsBetween(jFrom, jTo);
				int monthsDiff = d.getMonths() + 1;

				log.debug("Months:" + monthsDiff);

				if (!customer.getPrevReading().equals("0") && monthsDiff != 0) {
					units = Long.parseLong(bill_details.getPresent_reading())
							- Long.parseLong(bill_details.getInitial_reading());
					
					unitsKL = (float) units / 1000.0f;
					
					avgKL = unitsKL / monthsDiff;

					prevAvgKL = (Float.parseFloat(customer.getPrevAvgKl()) < 1.0f ? 1.0f
							: Float.parseFloat(customer.getPrevAvgKl()));

					factor = avgKL / prevAvgKL;

					log.debug("units:" + units + ", unitsKL=" + unitsKL + ", avgKL="+ avgKL + ", prevAvgKL=" + prevAvgKL+", factor="+factor);
					
					if (factor > 4.0f || factor < 0.25f) {
						// Unable to process customer
						log.debug("Meter reading for:" + customer.getId()
								+ ": is ::" + bill_details.getPresent_reading()
								+ ". This is too high or too low.");
						return;
					}
				}

				List<java.util.Map<String, Object>> charges = tariffMasterCustomRepository
						.findTariffs(zFrom, zTo, avgKL);
				
				kl = (float) (units / 1000.0);
			} else {
//				avgKL = Float.parseFloat(customer.getPrevAvgKl());
//				units = (long) (avgKL * monthsDiff * 1000.0f);
//				log.debug("Units:" + units + " based on avgKL:" + avgKL
//						+ " for " + monthsDiff + " months.");
			}
			monthUpto = getPrevMonthStart();

			hasSewer = (customer.getSewerage().equals("T") ? true : false);

		} catch (Exception e) {
			log.debug("Invalid From or To Date:" + bill_details.getFrom_month()
					+ "::::" + bill_details.getTo_month());
			return;
		}

	}

	void calc() {
		// ldm->upto = datetomthnum(ldm->mthupto);
		//
		// mths = Upto - Feb2014 + 1;
		// syslog(LOG_INFO, "Inside clcFeb2014: mths=%d, Upto=%d, Feb2014=%d\n",
		// mths, Upto, Feb2014);
		//
		// if (mths > Mths)
		// mths = Mths;
		//
		// kl = ldm->avgkl;
		// billedKL = kl;
		//
		// bMSB = ismsb(ldm->ctgnum);
		//
		// if (bMSB) {
		// if (ldm->resunits == 0)
		// ldm->resunits = 1;
		//
		// kl = (kl / (float) ldm->resunits);
		//
		// billedKL = kl;
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: resunits=%d, kl=%f, bMSB=%d\n",
		// ldm->resunits, kl, bMSB);
		//
		// water = wfromarray(kl, tbldec2011dn);
		//
		// water *= (float) ldm->resunits;
		//
		// syslog(LOG_INFO, "Inside clcFeb2014: water tariff=%f\n", water);
		//
		// if (ldm->resunits > 4) {
		//
		// // TODO: Calculate KL here
		// billedKL1 = 0.6F * (float) ldm->resunits;
		// minimum = 150.0F * billedKL1;
		//
		// if (water < minimum) {
		// billedKL = billedKL1;
		// water = minimum;
		// }
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: after resunits=%d, min=%f, water=%f\n",
		// ldm->resunits, minimum, water);
		// }
		//
		// minkl = sizetokl(ldm->pipesize);
		// billedKL1 = minkl;
		//
		// minimum = wfromarray(minkl, tbldec2011dn);
		//
		// if (water < minimum) {
		// billedKL = billedKL1;
		// water = minimum;
		// }
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: after pipesize=%f, min=%f, minkl=%f, water=%f\n",
		// ldm->pipesize, minimum, minkl, water);
		//
		// } else {
		// if (ldm->resunits == 0)
		// ldm->resunits = 1;
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: resunits=%d, kl=%f, bMSB=%d\n",
		// ldm->resunits, kl, bMSB);
		//
		// switch (ldm->ctgnum) {
		// case 21316:
		// water = wfromarray(kl, tbldec2011ds);
		// break;
		// case 8260:
		// case 19780:
		// case 20551:
		// case 21328:
		// case 8277:
		// case 17234:
		// case 13389:
		// water = wfromarray(kl, tbldec2011d);
		// break;
		//
		// case 8259:
		// case 13133:
		// case 8270:
		// water = wfromarray(kl, tblfeb2014c);
		// break;
		// case 8265:
		// case 12617:
		// water = wfromarray(kl, tblfeb2014i);
		// break;
		// }
		// syslog(LOG_INFO, "Inside clcFeb2014: water tariff=%f\n", water);
		//
		// // Apply this min. flats logic only for non-commercial and
		// // non-industrial customers
		// if (!
		// (ldm->ctgnum == 8259 || ldm->ctgnum == 13133
		// || ldm->ctgnum == 8270 || ldm->ctgnum == 8265
		// || ldm->ctgnum == 12617)) {
		// if (ldm->resunits > 4) {
		// minimum = 150.0F * (float) ldm->resunits;
		//
		// if (water < minimum)
		// water = minimum;
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: after resunits=%d, min=%f, water=%f\n",
		// ldm->resunits, minimum, water);
		// }
		// }
		// minkl = sizetokl(ldm->pipesize);
		//
		// switch (ldm->ctgnum) {
		// case 21316:
		// minimum = wfromarray(minkl, tbldec2011ds);
		// break;
		// case 8260:
		// case 19780:
		// case 20551:
		// case 21328:
		// case 8277:
		// case 17234:
		// case 13389:
		// minimum = wfromarray(minkl, tbldec2011d);
		// break;
		// case 8259:
		// case 13133:
		// case 8270:
		// minimum = wfromarray(minkl, tbldec2011c);
		// break;
		// case 8265:
		// case 12617:
		// minimum = wfromarray(minkl, tbldec2011i);
		// break;
		// }
		//
		// if (water < minimum) {
		// billedKL = minkl;
		// water = minimum;
		// }
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: before min pipesize=%f, min=%f, minkl=%f, water=%f\n",
		// ldm->pipesize, minimum, minkl, water);
		//
		// minimum = sizetominfeb2014(ldm->pipesize, Upto, ldm->ctgnum);
		//
		// if (water < minimum)
		// water = minimum;
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: after pipesize=%f, min=%f, minkl=%f, water=%f\n",
		// ldm->pipesize, minimum, minkl, water);
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: water after months=%f, mths=%d\n",
		// water, mths);
		// }
		//
		// if (ldm->hassewer) {
		// sewer = water * 0.35F;
		// }
		//
		// normal_mths = mths;
		//
		// if (strcmp(c->oc_flag, "T") == 0) {
		// if (strcmp(c->oc_date, "") == 0 || strcmp(c->oc_date, "0") == 0
		// || strcmp(c->oc_date, "00010101") == 0) {
		//
		// oc_mths = mths;
		// } else {
		// oc_dt = datetomthnum(c->oc_date);
		//
		// from = ldm->from;
		//
		// if (ldm->from < Feb2014)
		// from = Feb2014;
		//
		// oc_mths = oc_dt - from;
		// syslog(LOG_INFO, "oc_dt: %d, from dt: %d, oc_mths: %d\n",
		// oc_dt, from, oc_mths);
		// }
		//
		// if (oc_mths > 0) {
		// syslog(LOG_INFO, "***************************\n");
		// syslog(LOG_INFO,
		// "Water: %.2f\nOC Months:%d\nOC Penalty Water Cess %.2f times applied!\n",
		// water, oc_mths, 3.0 * water * oc_mths);
		// syslog(LOG_INFO,
		// "Sewerage: %.2f\nOC Months:%d\nOC Penalty Sewerage Cess %.2f times applied!\n",
		// sewer, oc_mths, 3.0 * sewer * oc_mths);
		// syslog(LOG_INFO, "***************************\n");
		//
		// oc_water = 3.0 * water;
		// oc_sewer = 3.0 * sewer;
		//
		// normal_mths = mths - oc_mths;
		//
		// }
		// }
		//
		// ldm->water += water * (float) normal_mths;
		//
		// if (ldm->hassewer) {
		// ldm->sewerage += sewer * (float) normal_mths;
		// }
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: Surcharge before=%f, amt=%.2f, sc=%.2f\n",
		// ldm->surcharge, water + sewer,
		// clcsurcharge_new(water + sewer, tbljul2010sc));
		//
		// if (penal_flag == TRUE && ldm->pipesize > 0.50F)
		// sprintf(ldm->nometer_amt, "%.2f", (water + sewer) * penal_mths);
		// else
		// strcpy(ldm->nometer_amt, "0.00");
		//
		// syslog(LOG_INFO,
		// "clcFeb2014: This is the nometer amt:%s, pipesize:%.2f\n",
		// ldm->nometer_amt, ldm->pipesize);
		//
		// surcharge = clcsurcharge_new(water + sewer, tbljul2010sc);
		// syslog(LOG_INFO, "Inside clcFeb2014: Surcharge after=%f\n",
		// ldm->surcharge);
		//
		// ldm->surcharge += surcharge * (float) normal_mths;
		//
		// if (oc_mths > 0) {
		// ldm->water += oc_water * (float) oc_mths;
		// ldm->sewerage += oc_sewer * (float) oc_mths;
		//
		// syslog(LOG_INFO,
		// "Inside clcFeb2014: OC Surcharge before=%f, amt=%.2f, sc=%.2f\n",
		// ldm->surcharge, oc_water + oc_sewer,
		// clcsurcharge_new(oc_water + oc_sewer, tbljul2010sc));
		//
		// surcharge = clcsurcharge_new(oc_water + oc_sewer, tbljul2010sc);
		// syslog(LOG_INFO, "Inside clcFeb2014: Surcharge after=%f\n",
		// ldm->surcharge);
		//
		// ldm->surcharge += surcharge * (float) oc_mths;
		// }
		//
		// if (ldm->from < Feb2014)
		// clcDec2011(ldm, Feb2014 - 1, Mths - mths);
		//
		// return;
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

		if (Float.parseFloat(customer.getPipeSize()) < 0.5f)
			return CustValidation.INVALID_PIPESIZE;

		if (customer.getPrevBillMonth() == null
				|| customer.getPrevBillMonth().equals(""))
			return CustValidation.INVALID_PREV_BILL_MONTH;

		if (Integer.parseInt(customer.getPrevBillMonth()) < 20050101)
			return CustValidation.NOT_IMPLEMENTED;

		if (!categories.contains(customer.getCategory().trim()))
			return CustValidation.INVALID_CATEGORY;

		if (Integer.parseInt(bill_details.getPresent_reading()) < Integer
				.parseInt(bill_details.getInitial_reading()))
			return CustValidation.INVALID_METER_READING;

		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		if (getPrevMonthStart().equals(customer.getPrevBillMonth()))
			return CustValidation.ALREADY_BILLED;

		if ((customer.getPrevBillType().equals("M") || customer
				.getPrevBillType().equals("L"))
				&& bill_details.getCurrent_bill_type().equals("U"))
			return CustValidation.INVALID_BILL_TYPE;

		if (customer.getPrevBillType().equals("R")
				&& !bill_details.getCurrent_bill_type().equals("R"))
			return CustValidation.INVALID_BILL_TYPE;

		if (customer.getPrevBillType().equals("U")
				&& !bill_details.getCurrent_bill_type().equals("U"))
			return CustValidation.INVALID_BILL_TYPE;

		if (customer.getMetReadingMo().equals("")
				&& bill_details.getCurrent_bill_type().equals("M"))
			return CustValidation.INVALID_PREV_BILL_MONTH;

		return CustValidation.SUCCESS;
	}

}

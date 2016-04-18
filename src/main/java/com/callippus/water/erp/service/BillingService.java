package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.mappings.BillMapper;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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
	private BillRunMasterRepository billRunMasterRepository;

	@Inject
	private BillRunDetailsRepository billRunDetailsRepository;

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

	enum BrdStatus {
		INIT (0), FAILED (1), SUCCESS(2), FAILED_COMMIT(3), COMMITTED(4);
		
	    private int _value;

	    BrdStatus(int Value) {
	        this._value = Value;
	    }

	    public int getValue() {
	            return _value;
	    }

	    public static BrdStatus fromInt(int i) {
	        for (BrdStatus b : BrdStatus .values()) {
	            if (b.getValue() == i) { return b; }
	        }
	        return null;
	    }
	}

	List<Long> categories = Arrays.asList(1L, 2L, 3L);

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
	int successRecords = 0;
	int failedRecords = 0;
	BillRunDetails brd = null;
	BillRunMaster br = null;

	ConfigurationDetails cd = null;

	float total_amount = 0.0f, net_payable_amount = 0.0f, surcharge = 0.0f,
			total_cess = 0.0f, kl = 0.0f;

	public BillRunMaster generateBill() {
		initBillRun();

		List<BillDetails> bd = billDetailsRepository.findAll();

		processBills(bd);

		if (failedRecords > 0)
			br.setStatus("Completed with Errors");
		else
			br.setStatus("Completed Successfully");
		
		billRunMasterRepository.save(br);

		return br;
	}

	public BillRunMaster generateSingleBill(String can) {
		initBillRun();

		process_bill(can);

		if (failedRecords > 0)
			br.setStatus("Completed with Errors");
		else
			br.setStatus("Completed Successfully");
		
		billRunMasterRepository.save(br);

		return br;
	}

	public String cancelBillRun(long billRunId) {
		try {
			BillRunMaster brm = billRunMasterRepository.findOne(billRunId);

			if(brm.getStatus().equalsIgnoreCase("COMMITTED"))
				return "Bill run already COMMITTED";
			
			if(brm.getStatus().equalsIgnoreCase("CANCELLED"))
				return "Bill run already CANCELLED";
						
			if(brm.getStatus().equalsIgnoreCase("IN PROCESS"))
				return "Cannot COMMIT IN PROCESS Bill run";
									
			brm.setStatus("CANCELLED");
			
			return "CANCEL Success!";
		} catch (Exception e) {
			e.printStackTrace();
			return "CANCEL Failed:" + e.getMessage();
		}
	}
	
	public String commitBillRun(long billRunId) {
		try {
			BillRunMaster brm = billRunMasterRepository.findOne(billRunId);

			if(brm.getStatus().equalsIgnoreCase("COMMITTED"))
				return "Bill run already COMMITTED";
			
			if(brm.getStatus().equalsIgnoreCase("CANCELLED"))
				return "Bill run already CANCELLED";
						
			if(brm.getStatus().equalsIgnoreCase("IN PROCESS"))
				return "Cannot COMMIT IN PROCESS Bill run";
													
			billRunDetailsRepository.findByBillRunId(billRunId).forEach(
					bill_run_detail -> commit(bill_run_detail));


			brm.setStatus("COMMITTED");
			
			return "COMMIT Success!";
		} catch (Exception e) {
			e.printStackTrace();
			return "COMMIT Failed:" + e.getMessage();
		}
	}

	public void commit(BillRunDetails brd) {
		try {
			CustDetails customer = custDetailsRepository
					.findByCan(brd.getCan());
			BillFullDetails bfd = brd.getBillFullDetails();

			customer.setPrevBillType(bfd.getCurrentBillType());
			customer.setPrevBillMonth(bfd.getBillDate().withDayOfMonth(1));
			customer.setArrears(CPSUtils.round(bfd.getNetPayableAmount()
					.floatValue(), 2));
			customer.setPrevReading(bfd.getPresentReading());
			customer.setMetReadingDt(bfd.getMetReadingDt());
			customer.setMetReadingMo(bfd.getMetReadingDt().withDayOfMonth(1));

			custDetailsRepository.save(customer);

			brd.setStatus(BrdStatus.COMMITTED.getValue());
			billRunDetailsRepository.save(brd);
		} catch (Exception e) {
			brd.setRemarks(CPSUtils.stackTraceToString(e).substring(0,254));
			brd.setStatus(BrdStatus.FAILED_COMMIT.getValue());
			billRunDetailsRepository.save(brd);
		}
	}

	public void initBillRun() {
		br = new BillRunMaster();
		br.setArea("0");
		br.setDate(ZonedDateTime.now());
		br.setSuccess(0);
		br.setFailed(0);
		br.setStatus("In Process");

		billRunMasterRepository.save(br);
	}

	public void initBill(String can) {
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

		brd = new BillRunDetails();
		brd.setCan(can);
		brd.setFromDt(ZonedDateTime.now());
		brd.setStatus(9);
		brd.setRemarks("In Process");
		brd.setBillRunMaster(br);
	}

	public void processBills(List<BillDetails> bd) {
		bd.forEach(bill_details -> process_bill(bill_details));
	}

	public void process_bill(String can) {
		BillDetails bill_details = billDetailsRepository.findByCan(can);
		process_bill(bill_details);
	}

	public void process_bill(BillDetails bill_details) {
		if (bill_details == null)
			return;

		initBill(bill_details.getCan());

		log.debug("Process customer with CAN:" + bill_details.getCan());
		CustDetails customer = custDetailsRepository.findByCan(bill_details
				.getCan());

		if (customer == null) {
			log.debug("Customer not found in CUST_DETAILS for CAN:"
					+ bill_details.getCan());

			brd.setToDt(ZonedDateTime.now());
			brd.setStatus(0);
			brd.setRemarks("Failed with error:"
					+ "Customer not found in CUST_DETAILS for CAN:"
					+ bill_details.getCan());
			billRunDetailsRepository.save(brd);

			br.setFailed(++failedRecords);
			billRunMasterRepository.save(br);

			return;
		}

		CustValidation retVal = getCustInfo(customer, bill_details);

		if (retVal != CustValidation.SUCCESS) {
			// Unable to process customer
			log.debug("Unable to process customer:" + customer.getId()
					+ ", getCustInfo returned::" + retVal.name());

			brd.setToDt(ZonedDateTime.now());
			brd.setStatus(BrdStatus.FAILED.getValue());
			brd.setRemarks("Failed with error:" + retVal.name());
			billRunDetailsRepository.save(brd);

			br.setFailed(++failedRecords);
			billRunMasterRepository.save(br);

			return;
		}

		if (bfdRepository.findByCanAndToMonth(
				customer.getCan(),
				customer.getPrevBillMonth().format(
						DateTimeFormatter.ofPattern("yyyyMM"))) != null) {
			log.debug("Unable to process customer:" + customer.getId()
					+ ", getCustInfo returned::"
					+ CustValidation.ALREADY_BILLED.name());

			brd.setToDt(ZonedDateTime.now());
			brd.setStatus(BrdStatus.FAILED.getValue());
			brd.setRemarks("Failed with error:"
					+ CustValidation.ALREADY_BILLED.name());
			billRunDetailsRepository.save(brd);

			br.setFailed(++failedRecords);
			billRunMasterRepository.save(br);

			return;
		}

		try {
			if (!bill_details.getCurrentBillType().equals("M"))
				bill_details.setPresentReading(customer.getPrevReading());

			dFrom = customer.getPrevBillMonth();
			dTo = bill_details.getBillDate().withDayOfMonth(1);

			// Previously Metered or Locked and currently Metered
			if ((customer.getPrevBillType().equals("L") || customer
					.getPrevBillType().equals("M"))
					&& bill_details.getCurrentBillType().equals("M")) {

				long days = ChronoUnit.DAYS.between(customer.getMeterFixDate(),
						customer.getMetReadingDt());

				newMeterFlag = (days < 15 ? 1 : 0);

				if (newMeterFlag == 1) {
					log.debug("########################################");
					log.debug("          NEW METER BILL CASE (" + days
							+ " days)");
					log.debug("########################################");
				} else {
					log.debug("########################################");
					log.debug("          METER BILL CASE (" + days + " days)");
					log.debug("########################################");
				}
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

			unMeteredFlag = (bill_details.getCurrentBillType().equals("U") ? 1
					: 0);

			List<java.util.Map<String, Object>> charges = tariffMasterCustomRepository
					.findTariffs(bill_details.getCan(), dFrom, dTo, avgKL,
							unMeteredFlag, newMeterFlag);

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

			cd = configurationDetailsRepository.findOneByName("EWURA");

			log.debug("This is the EWURA Configuration:" + cd.toString());
			float ewura = ((bfd.getWaterCess() + bfd.getSewerageCess()) * Float
					.parseFloat(cd.getValue())) / 100.0f;

			bfd.setSurcharge(CPSUtils.round(ewura, 2));

			Float total = bfd.getWaterCess() + bfd.getMeterServiceCharge()
					+ bfd.getServiceCharge() + bfd.getSewerageCess()
					+ bfd.getSurcharge() + bfd.getOtherCharges();

			bfd.setTotalAmount(CPSUtils.round(total.floatValue(), 2));

			Float netPayable = bfd.getTotalAmount() + bfd.getIntOnArrears()
					+ bfd.getArrears();
			bfd.setNetPayableAmount(CPSUtils.round(netPayable.floatValue(), 2));

			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmmss");

			bfd.setBillDate(date.toLocalDate());
			bfd.setBillTime(date.format(formatter));

			if (bill_details.getCurrentBillType().equals("M")) {
				bfd.setMetReadingMo(bill_details.getMetReadingDt()
						.withDayOfMonth(1));
				bfd.setMetAvgKl(avgKL);
			}

			bfd.setMeterStatus(bill_details.getCurrentBillType());

			bfd.setUnits(units);
			bfd.setFromMonth(dFrom.format(DateTimeFormatter.ofPattern("yyyyMM")));
			bfd.setToMonth(dTo.format(DateTimeFormatter.ofPattern("yyyyMM")));

			log.debug("This is the BillFullDetails:" + bfd);

			bfdRepository.save(bfd);

			brd.setToDt(ZonedDateTime.now());
			brd.setStatus(BrdStatus.SUCCESS.getValue());
			brd.setRemarks("Success");
			brd.setBillFullDetails(bfd);
			billRunDetailsRepository.save(brd);

			br.setSuccess(++successRecords);
			br.setStatus("Completed Successfully");
			billRunMasterRepository.save(br);

		} catch (Exception e) {
			e.printStackTrace();
			log.debug("Invalid From or To Date:" + bill_details.getFromMonth()
					+ "::::" + bill_details.getToMonth());

			brd.setToDt(ZonedDateTime.now());
			brd.setStatus(BrdStatus.FAILED.getValue());
			brd.setRemarks("Failed with error:"
					+ CPSUtils.stackTraceToString(e));
			billRunDetailsRepository.save(brd);

			br.setFailed(++failedRecords);
			billRunMasterRepository.save(br);

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

		if (!categories.contains(customer.getTariffCategoryMaster().getId()))
			return CustValidation.INVALID_CATEGORY;

		if (bill_details.getPresentReading() < bill_details.getInitialReading())
			return CustValidation.INVALID_METER_READING;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

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

package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.Adjustments;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.MeterChange;
import com.callippus.water.erp.domain.enumeration.BillingStatus;
import com.callippus.water.erp.domain.enumeration.CustStatus;
import com.callippus.water.erp.domain.enumeration.TxnStatus;
import com.callippus.water.erp.mappings.BillMapper;
import com.callippus.water.erp.repository.AdjustmentsRepository;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.MeterChangeRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.*;

/**
 * Service class for managing users.
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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

	@Inject
	private MeterChangeRepository meterChangeRepository;

	@Inject
	private AdjustmentsRepository adjustmentsRepository;

	enum Status {
		SUCCESS, FAILURE
	};

	enum CustValidation {
		ALREADY_BILLED, INVALID_BILL_TYPE, INVALID_METER_READING, INVALID_METER_READING_MONTH, INVALID_PIPESIZE, INVALID_CATEGORY, NOT_IMPLEMENTED, INVALID_PREV_BILL_MONTH, CUSTOMER_DOES_NOT_EXIST, SUCCESS
	};

	enum MeterChangeStatus {
		APPROVED(2), BILLED(3);

		private int _value;

		MeterChangeStatus(int Value) {
			this._value = Value;
		}

		public int getValue() {
			return _value;
		}

		public static MeterChangeStatus fromInt(int i) {
			for (MeterChangeStatus b : MeterChangeStatus.values()) {
				if (b.getValue() == i) {
					return b;
				}
			}
			return null;
		}
	}

	enum BrdStatus {
		INIT(0), FAILED(1), SUCCESS(2), FAILED_COMMIT(3), COMMITTED(4);

		private int _value;

		BrdStatus(int Value) {
			this._value = Value;
		}

		public int getValue() {
			return _value;
		}

		public static BrdStatus fromInt(int i) {
			for (BrdStatus b : BrdStatus.values()) {
				if (b.getValue() == i) {
					return b;
				}
			}
			return null;
		}
	}

	float avgKL = 0.0f;
	float factor = 0.0f;
	float prevAvgKL = 0.0f;
	float units = 0;
	float unitsKL = 0.0f;
	float billedUnitsKL = 0.0f;
	float partialUnitsKL = 0.0f;
	float remUnitsKL = 0.0f;
	float minAvgKL = 0.0f;
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
	boolean multipleTariffs = false;

	MeterChange meterChange = null;

	ConfigurationDetails cd = null;

	float total_amount = 0.0f, net_payable_amount = 0.0f, surcharge = 0.0f, total_cess = 0.0f, kl = 0.0f;

	public static void main(String[] args) throws Exception {
		LocalDate dFrom = null;
		LocalDate dTo = null;

		dFrom = LocalDate.of(2016, 05, 24);
		dTo = LocalDate.of(2016, 06, 30);
	}

	public BillRunMaster generateBill() throws Exception {
		initBillRun();

		List<BillDetails> bd = billDetailsRepository.findAllInitiated();

		if (bd.size() == 0)
			throw new Exception("No new meter readings available to run bills.");

		processBillsWithMeter(bd);

		processBillsWithoutMeter();

		if (failedRecords > 0)
			br.setStatus("Completed with Errors");
		else
			br.setStatus("Completed Successfully");

		billRunMasterRepository.save(br);

		return br;
	}

	public BillRunMaster generateSingleBill(String can) throws Exception {

		initBillRun();

		process_bill(can);

		if (failedRecords > 0)
			br.setStatus("Completed with Errors");
		else
			br.setStatus("Completed Successfully");

		billRunMasterRepository.save(br);

		return br;
	}

	public void process_error(String message, String can)
	{
		log.debug(message + ":" + can);

		brd.setToDt(ZonedDateTime.now());
		brd.setStatus(0);
		brd.setRemarks(
				"Failed with error:" + message +  ":" + can);
		billRunDetailsRepository.save(brd);

		br.setFailed(++failedRecords);
		billRunMasterRepository.save(br);	
	}
	
	public void process_bill(BillDetails bill_details) {
		if (bill_details == null)
			return;

		try {
			if (bill_details.getBillDate().isBefore(LocalDate.now().minusYears(1))) {
				process_error("Bill Date Older than 1 year for CAN", bill_details.getCan());
				return;
			}
			
			initBill(bill_details.getCan());

			brd.setBillDetails(bill_details);
			log.debug("#############################################################################");
			log.debug("Process customer with CAN:" + bill_details.getCan());
			CustDetails customer = custDetailsRepository.findByCan(bill_details.getCan());

			if (customer == null) {
				process_error("Customer not found in CUST_DETAILS for CAN", bill_details.getCan());
				return;
			}
			else if(customer.getStatus() != CustStatus.ACTIVE)
			{
				process_error("Customer not found in CUST_DETAILS for CAN", bill_details.getCan());
				return;
			}

			if (bill_details.getCurrentBillType().equals("M") || bill_details.getCurrentBillType().equals("S")) {
				if (meterChange != null) {
					getUnitsKLMeterChange(bill_details, customer);
				} else if (bill_details.getIsRounding()) {
					unitsKL = bill_details.getPresentReading() + 9999 - bill_details.getInitialReading();
				} else {
					unitsKL = bill_details.getPresentReading() - bill_details.getInitialReading();

					if (unitsKL < 0)
						throw new Exception("Invalid Meter Reading");
				}

				billedUnitsKL = unitsKL; // All "M" cases - Will be calculated
											// here.
			}

			if (customer.getPrevBillMonth() == null) {
				process_bill_new_meter(bill_details, customer);
				return;
			} else {
				process_bill_normal(bill_details, customer);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();			
			process_error(CPSUtils.getStackLimited("", e, 150),brd.getCan());			
			return;
		}
	}

	public String cancelBillRun(long billRunId) {
		try {
			BillRunMaster brm = billRunMasterRepository.findOne(billRunId);

			if (brm.getStatus().equalsIgnoreCase("COMMITTED"))
				return "Bill run already COMMITTED";

			if (brm.getStatus().equalsIgnoreCase("CANCELLED"))
				return "Bill run already CANCELLED";

			if (brm.getStatus().equalsIgnoreCase("IN PROCESS"))
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

			cd = configurationDetailsRepository.findOneByName("MIN_AVG_KL");

			if(cd != null)
				minAvgKL = Float.parseFloat(cd.getValue());
			else
				minAvgKL = 2.5f;
			
			if (brm.getStatus().equalsIgnoreCase("COMMITTED"))
				return "Bill run already COMMITTED";

			if (brm.getStatus().equalsIgnoreCase("CANCELLED"))
				return "Bill run already CANCELLED";

			if (brm.getStatus().equalsIgnoreCase("IN PROCESS"))
				return "Cannot COMMIT IN PROCESS Bill run";

			billRunDetailsRepository.findByBillRunId(billRunId).forEach(bill_run_detail -> commit(bill_run_detail));

			brm.setStatus("COMMITTED");

			return "COMMIT Success!";
		} catch (Exception e) {
			e.printStackTrace();
			return "COMMIT Failed:" + e.getMessage();
		}
	}

	public void commit(BillRunDetails brd) {
		try {
			BillFullDetails bfd = brd.getBillFullDetails();
			BillDetails bd = brd.getBillDetails();
			
			prevAvgKL = minAvgKL;

			bd.setStatus(BillingStatus.COMMITTED);
			billDetailsRepository.save(bd);

			int commit_status = (brd.getStatus() == BrdStatus.SUCCESS.getValue() ? BrdStatus.COMMITTED.getValue()
					: BrdStatus.FAILED_COMMIT.getValue());
			brd.setStatus(commit_status);
			billRunDetailsRepository.save(brd);

			if (commit_status == BrdStatus.FAILED_COMMIT.getValue()) {
				log.debug("Failed Bill Run: Cannot Commit CAN:" + bd.getCan());
				return;
			}

			List<BillRunDetails> brdList = billRunDetailsRepository.findTop3ByCanAndStatusOrderByIdDesc(brd.getCan(),
					BrdStatus.COMMITTED.getValue());

			float kl = 0.0f;

			int i = 0;
			LocalDate fromDt = null, toDt = null;

			DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyyMMdd");

			for (BillRunDetails brd1 : brdList) {
				if (!brd1.getBillFullDetails().getCurrentBillType().equals("M"))
					continue;

				if (i == 0) {
					toDt = LocalDate.parse(brd1.getBillFullDetails().getToMonth() + "01", date_format);
					toDt = toDt.withDayOfMonth(toDt.lengthOfMonth());
				}

				fromDt = LocalDate.parse(brd1.getBillFullDetails().getFromMonth() + "01", date_format);

				kl = kl + brd1.getBillFullDetails().getUnits();
				i++;
			}

			if (fromDt != null && toDt != null) {
				long months = ChronoUnit.MONTHS.between(fromDt, toDt.plusDays(1));

				if (months > 0)
					prevAvgKL = kl / months;
			} 

			CustDetails customer = custDetailsRepository.findByCanForUpdate(brd.getCan());
			customer.setPrevBillType(bfd.getCurrentBillType());
			customer.setPrevAvgKl(prevAvgKL);
			customer.setPrevBillMonth(LocalDate.parse(bfd.getToMonth() + "01", date_format));
			customer.setArrears(CPSUtils.round(bfd.getNetPayableAmount().floatValue(), 2));
			customer.setOtherCharges(0.0f);
			customer.setPrevReading(bfd.getPresentReading());
			customer.setMetReadingDt(bfd.getMetReadingDt());
			customer.setMetReadingMo(bfd.getMetReadingDt().withDayOfMonth(1));
			if (bfd.getCurrentBillType().equals("L"))
				customer.setLockCharges(bfd.getLockCharges());
			else
				customer.setLockCharges(0.0f);

			List<Adjustments> adjustments = adjustmentsRepository
					.findByCanAndStatusAndBillFullDetails(customer.getCan(), TxnStatus.PROCESSING, bfd);

			for (Adjustments adj : adjustments) {
				adj.setStatus(TxnStatus.BILLED);
			}

			adjustmentsRepository.save(adjustments);

			custDetailsRepository.saveAndFlush(customer);

			MeterChange meterChange = meterChangeRepository.findByCanAndStatus(customer.getCan(),
					MeterChangeStatus.APPROVED.getValue());

			if (meterChange != null) {
				meterChange.setBillFullDetails(bfd);
				meterChange.setStatus(3);

				meterChangeRepository.save(meterChange);
			}
			
			log.debug("Finished committing CAN:" + bd.getCan());

		} catch (Exception e) {
			e.printStackTrace();
			brd.setRemarks(CPSUtils.getStackLimited("Failed with error:", e, 250));
			brd.setStatus(BrdStatus.FAILED_COMMIT.getValue());
			billRunDetailsRepository.save(brd);
		}
	}

	public void initBillRun() {

		successRecords = 0;
		failedRecords = 0;

		cd = configurationDetailsRepository.findOneByName("MIN_AVG_KL");

		if(cd != null)
			minAvgKL = Float.parseFloat(cd.getValue());
		else
			minAvgKL = 2.5f;

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
		billedUnitsKL = 0.0f;
		hasSewer = false;
		ewura = 0.0f;
		monthsDiff = 0;
		dFrom = null;
		dTo = null;
		newMeterFlag = 0;
		unMeteredFlag = 0;
		multipleTariffs = false;

		partialUnitsKL = 0.0f;
		remUnitsKL = 0.0f;

		total_amount = 0.0f;
		net_payable_amount = 0.0f;
		surcharge = 0.0f;
		total_cess = 0.0f;
		kl = 0.0f;

		meterChange = meterChangeRepository.findByCanAndStatus(can, MeterChangeStatus.APPROVED.getValue());

		brd = new BillRunDetails();
		brd.setCan(can);
		brd.setFromDt(ZonedDateTime.now());
		brd.setStatus(9);
		brd.setRemarks("In Process");
		brd.setBillRunMaster(br);
	}

	public void processBillsWithMeter(List<BillDetails> bd) {
		bd.forEach(bill_details -> process_bill(bill_details));
	}

	public void processBillsWithoutMeter() {
		List<CustDetails> customers = custDetailsRepository.findByPrevBillType("U");
		customers.forEach(customer -> saveBillDetails(customer));
	}

	public void saveBillDetails(CustDetails customer) {
		// Insert bill_details record for each run
		try {
			
			if(customer.getStatus() != CustStatus.ACTIVE)
			{
				process_error("Customer not found in CUST_DETAILS for CAN",customer.getCan());
				return;
			}
			
			initBill(customer.getCan()); // Is inited again in process_bill, but
											// right now there is no better
											// solution

			BillDetails bill_details = new BillDetails();
			bill_details.setCan(customer.getCan());
			LocalDate now = LocalDate.now();
			bill_details.setBillDate(now);
			bill_details.setCurrentBillType("U");

			DateTimeFormatter date_format = DateTimeFormatter.ofPattern("yyyyMM");
			if (customer.getPrevBillMonth() != null) {
				LocalDate from = customer.getPrevBillMonth().plusMonths(1);
				String fromMonth = from.format(date_format);
				bill_details.setFromMonth(fromMonth);
			} else if (customer.getMeterFixDate() != null) {
				LocalDate from = customer.getMeterFixDate();
				String fromMonth = from.format(date_format);
				bill_details.setFromMonth(fromMonth);
			} else
				throw new Exception("Unmetered Customer does not have Prev Bill Month or Meter Fix Date");

			bill_details.setToMonth(now.format(date_format));
			bill_details.setMeterFixDate(customer.getMeterFixDate());
			bill_details.setInitialReading(null);
			bill_details.setPresentReading(null);
			bill_details.setMetReadingDt(now);
			bill_details.setInsertDt(ZonedDateTime.now());
			bill_details.setStatus(BillingStatus.INITIATED);
			bill_details.setMeterReaderId("1");

			billDetailsRepository.saveAndFlush(bill_details);

			process_bill(bill_details);
		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150),brd.getCan());	
			return;
		}

	}

	public void process_bill(String can) throws Exception {
		BillDetails bill_details = billDetailsRepository.findValidBillForCan(can);

		if (bill_details == null)
			throw new Exception("No new meter readings available to run bill.");

		process_bill(bill_details);
	}

	public void process_bill_new_meter(BillDetails bill_details, CustDetails customer) {

		newMeterFlag = 1;

		if (!validateCust(customer, bill_details))
			return;

		try {
			dFrom = customer.getMeterFixDate();
			dTo = bill_details.getBillDate().withDayOfMonth(bill_details.getBillDate().lengthOfMonth());

			long days = ChronoUnit.DAYS.between(dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));
			long totDays = ChronoUnit.DAYS.between(dFrom, dTo);

			if (days <= 0) {
				throw new Exception("Invalid From:" + dFrom.format(DateTimeFormatter.ofPattern("yyyyMM")) + " and To:"
						+ dTo.format(DateTimeFormatter.ofPattern("yyyyMM")));
			}

			log.debug("################################################################################");
			log.debug("          NEW METER BILL CASE (" + days + " days this month, total days:" + totDays + " )");
			log.debug("################################################################################");
			log.debug("Customer Info:" + customer.toString());
			log.debug("From:" + dFrom.toString() + ", To:" + dTo.toString());

			unMeteredFlag = (bill_details.getCurrentBillType().equals("U") ? 1 : 0);

			calc_units(customer, bill_details, dFrom, dTo, unitsKL, true);

			billedUnitsKL = unitsKL; // Will be calculated for Stuck and Burnt
										// cases.

			// This query just to determine if multiple tariffs exist during the
			// period
			List<java.util.Map<String, Object>> charges = tariffMasterCustomRepository.getTariffs(bill_details.getCan(),
					dFrom, dTo, avgKL, unMeteredFlag, newMeterFlag);

			if (charges.isEmpty())
				throw new Exception("No tariffs configured.");
			
			BillFullDetails bfd = BillMapper.INSTANCE.bdToBfd(bill_details, customer);
			bfd.setId(null);

			bfd.setWaterCess(0.00f);
			bfd.setSewerageCess(0.00f);
			bfd.setServiceCharge(0.00f);
			bfd.setMeterServiceCharge(0.00f);
			bfd.setLockCharges(0.00f);
			bfd.setNoMeterAmt(0.00f);

			if (charges.size() > 3) {
				multipleTariffs = true; // Multiple tariffs for first bill.
										// Pro-rata required
				partialUnitsKL = (float) days / (float) totDays * unitsKL;

				charges = tariffMasterCustomRepository.getTariffs(bill_details.getCan(), dFrom,
						dFrom.withDayOfMonth(dFrom.lengthOfMonth()), partialUnitsKL, unMeteredFlag, newMeterFlag);

				calc_charges_first(charges, bfd, partialUnitsKL, dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));

				log.debug("Charges for partial units:" + partialUnitsKL + "=> " + charges);

				remUnitsKL = unitsKL - partialUnitsKL;
				calc_units(customer, bill_details, dFrom.plusMonths(1).withDayOfMonth(1), dTo, remUnitsKL, false);

				log.debug("Charges for remaining units:" + remUnitsKL + ", avgKL:" + avgKL + "=>" + charges);

				charges = tariffMasterCustomRepository.findTariffs(bill_details.getCan(),
						dFrom.plusMonths(1).withDayOfMonth(1), dTo, avgKL, unMeteredFlag, newMeterFlag);

				if (charges.isEmpty())
					throw new Exception("No tariffs configured. ");

				calc_charges_normal(charges, bill_details, customer, bfd, remUnitsKL);
			} else { // Single Tariff for first bill
				calc_charges_first(charges, bfd, unitsKL, dFrom, dTo);
			}

			process_bill_common(customer, bill_details, bfd, dFrom, dTo);

		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150),brd.getCan());	
			return;
		}

	}

	public void calc_units(CustDetails customer, BillDetails bill_details, LocalDate dFrom, LocalDate dTo,
			float unitsKL, boolean partialMonth) {

		try {
			long monthsDiff = ChronoUnit.MONTHS.between(dFrom, dTo.plusDays(1));

			long days = ChronoUnit.DAYS.between(dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));

			float avgUnitsKL = 0.0f;

			if (monthsDiff == 0)
				monthsDiff = 1;

			log.debug("Months:" + monthsDiff);

			if (bill_details.getCurrentBillType().equals("M") && (customer.getPrevBillType() == null
					|| customer.getPrevBillType().equals("M") || customer.getPrevBillType().equals("L"))) {

				if (!customer.getPrevReading().equals("0")) {
					units = unitsKL * 1000.0f;

					avgKL = unitsKL / monthsDiff;

					if (prevAvgKL != 0) {
						factor = avgKL / prevAvgKL;

						log.debug("units:" + units + ", unitsKL=" + unitsKL + ", avgKL=" + avgKL + ", prevAvgKL="
								+ prevAvgKL + ", factor=" + factor);

						if (factor > 4.0f || factor < 0.25f) {
							// Unable to process customer
							log.debug("Meter reading for:" + customer.getId() + ": is ::"
									+ bill_details.getPresentReading() + ". This is too high or too low.");
							return;
						}
					}
				}

				kl = (float) (unitsKL);
			} else if (bill_details.getCurrentBillType().equals("M") || bill_details.getCurrentBillType().equals("S")) // Moved
																														// from
																														// Stuck/Burnt
																														// to
																														// Metered
																														// in
																														// the
																														// middle
																														// of
																														// the
																														// month
			{
				log.debug("########################################");
				log.debug("    STUCK OR PARTIAL METERED BILL CASE");
				log.debug("########################################");

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = (customer.getPrevAvgKl() == null || customer.getPrevAvgKl() < 0.1f ? minAvgKL
						: customer.getPrevAvgKl());

				if (partialMonth) {
					long monthsDiff1 = ChronoUnit.MONTHS.between(dFrom, dTo.plusDays(1));

					float partialMo = (float) days / (float) dFrom.lengthOfMonth();

					avgUnitsKL = avgKL * (partialMo + monthsDiff1);
				} else
					avgUnitsKL = avgKL * monthsDiff;

				this.unitsKL = (avgUnitsKL > unitsKL ? avgUnitsKL : unitsKL);

				units = this.unitsKL * 1000.0f;

				avgKL = this.unitsKL / monthsDiff;

				log.debug("Units:" + units + " based on avgKL:" + avgKL + " for " + monthsDiff + " months.");
			} else if (bill_details.getCurrentBillType().equals("L") || bill_details.getCurrentBillType().equals("B")
					|| bill_details.getCurrentBillType().equals("R")) {

				log.debug("########################################");
				log.debug("          LOCK/BURNT BILL CASE");
				log.debug("########################################");

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = (customer.getPrevAvgKl() == null || customer.getPrevAvgKl() < 0.1f ? minAvgKL
						: customer.getPrevAvgKl());

				if (partialMonth) {
					long monthsDiff1 = ChronoUnit.MONTHS.between(dFrom, dTo.plusDays(1));

					float partialMo = (float) days / (float) dFrom.lengthOfMonth();

					avgUnitsKL = avgKL * (partialMo + monthsDiff1);
				} else
					avgUnitsKL = avgKL * monthsDiff;

				this.unitsKL = (avgUnitsKL > unitsKL ? avgUnitsKL : unitsKL);

				// this.unitsKL = avgKL * monthsDiff;
				units = this.unitsKL * 1000.0f;
				log.debug("Units:" + units + " based on avgKL:" + avgKL + " for " + monthsDiff + " months.");
			} else if (bill_details.getCurrentBillType().equals("U")) {

				log.debug("########################################");
				log.debug("          UNMETERED BILL CASE");
				log.debug("########################################");

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = (customer.getPrevAvgKl() == null || customer.getPrevAvgKl() < 0.1f ? minAvgKL
						: customer.getPrevAvgKl());

				this.unitsKL = avgKL * monthsDiff;
				units = this.unitsKL * 1000.0f;
				log.debug("Units:" + units + " based on avgKL:" + avgKL + " for " + monthsDiff + " months.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150),brd.getCan());	
			return;
		}
	}

	public void calc_charges_first(List<java.util.Map<String, Object>> charges, BillFullDetails bfd, float units,
			LocalDate dFrom, LocalDate dTo) {
		long days = ChronoUnit.DAYS.between(dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));
		long months = ChronoUnit.MONTHS.between(dFrom, dTo);
		months += (days >= 15 ? 1 : 0);

		for (Map<String, Object> charge : charges) {
			if (((Long) charge.get("tariff_type_master_id")) == 1) {
				bfd.setNoMeterAmt(bfd.getNoMeterAmt() + (Float) charge.get("rate"));
				bfd.setWaterCess(bfd.getWaterCess() + ((Float) charge.get("rate")).floatValue() * units);
				log.debug("Usage Charge:" + ((Float) charge.get("rate")).floatValue() * units);
			} else if (((Long) charge.get("tariff_type_master_id")) == 2) {
				log.debug("Meter Rent:" + ((Float) charge.get("rate")).floatValue() * months);
				bfd.setMeterServiceCharge(
						bfd.getMeterServiceCharge() + ((Float) charge.get("rate")).floatValue() * months);
			} else if (((Long) charge.get("tariff_type_master_id")) == 3) {
				log.debug("Service Charge:" + ((Float) charge.get("rate")).floatValue() * months);
				bfd.setServiceCharge(bfd.getServiceCharge() + ((Float) charge.get("rate")).floatValue() * months);
			}
		}
	}

	public void calc_charges_normal(List<java.util.Map<String, Object>> charges, BillDetails bill_details,
			CustDetails customer, BillFullDetails bfd, float units) {

		// Subtract Avg Water charges in case of Lock Bill scenario
		for (Map<String, Object> charge : charges) {
			if (((Long) charge.get("tariff_type_master_id")) == 1) {

				bfd.setNoMeterAmt(((Double) charge.get("rate")).floatValue());

				log.debug("Usage Charge:" + (Double) charge.get("amount"));
				bfd.setWaterCess(bfd.getWaterCess() + ((Double) charge.get("amount")).floatValue());

				if ( (bill_details.getCurrentBillType().equals("M") || bill_details.getCurrentBillType().equals("S") )
						&& (customer.getPrevBillType() == null || customer.getPrevBillType().equals("L"))) {
					if (customer.getLockCharges() == null)
						bfd.setLockCharges(0.0f);
					else {
						bfd.setLockCharges(-1 * customer.getLockCharges());
						bfd.setWaterCess(bfd.getWaterCess() + bfd.getLockCharges());
					}
				} else if (bill_details.getCurrentBillType().equals("L")) {
					if (customer.getLockCharges() != null)
						bfd.setLockCharges(bfd.getWaterCess() + customer.getLockCharges());
					else
						bfd.setLockCharges(bfd.getWaterCess());
				}

			} else if (((Long) charge.get("tariff_type_master_id")) == 2) {
				log.debug("Meter Rent:" + (Double) charge.get("amount"));
				bfd.setMeterServiceCharge(bfd.getMeterServiceCharge() + ((Double) charge.get("amount")).floatValue());
			} else if (((Long) charge.get("tariff_type_master_id")) == 3) {
				log.debug("Service Charge:" + (Double) charge.get("amount"));
				bfd.setServiceCharge(bfd.getServiceCharge() + ((Double) charge.get("amount")).floatValue());
			}
		}
	}

	public void process_bill_common(CustDetails customer, BillDetails bill_details, BillFullDetails bfd,
			LocalDate dFrom, LocalDate dTo) {

		try {

			hasSewer = (customer.getSewerage().equals("T") ? true : false);

			cd = configurationDetailsRepository.findOneByName("SEWERAGE");

			log.debug("This is the SEWERAGE Charge Configuration:" + cd.toString());

			if (hasSewer)
				bfd.setSewerageCess(Float.parseFloat(cd.getValue()) * bfd.getWaterCess() / 100.0f);

			cd = configurationDetailsRepository.findOneByName("EWURA");

			log.debug("This is the EWURA Configuration:" + cd.toString());
			float ewura = ((bfd.getWaterCess() + bfd.getSewerageCess()) * Float.parseFloat(cd.getValue())) / 100.0f;

			bfd.setSurcharge(CPSUtils.round(ewura, 2));

			List<Adjustments> adjustments = adjustmentsRepository
					.findByCanAndStatusAndBillFullDetails(bill_details.getCan(), TxnStatus.INITIATED, null);

			BigDecimal adjAmount = new BigDecimal("0.00");
			adjAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
			for (Adjustments adj : adjustments) {
				BigDecimal adj1 = adj.getAmount();
				adj1.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				adj.setBillFullDetails(bfd);
				adj.setCustDetails(customer);
				if (adj.getTransactionTypeMaster().getTypeOfTxn().equalsIgnoreCase("Credit"))
					adjAmount = adjAmount.add(adj1);
				else
					adjAmount = adjAmount.subtract(adj1);

				adj.setStatus(TxnStatus.PROCESSING);
			}

			BigDecimal total = new BigDecimal(bfd.getWaterCess() + bfd.getMeterServiceCharge() + bfd.getServiceCharge()
					+ bfd.getSewerageCess() + bfd.getSurcharge() + bfd.getOtherCharges()).add(adjAmount);

			log.debug("Total=Water Cess (" + bfd.getWaterCess() + ") " + "+ Meter Svc Charge("
					+ bfd.getMeterServiceCharge() + ") " + "+ Service Charge (" + bfd.getServiceCharge() + ") "
					+ "+ SewerageCess (" + bfd.getSewerageCess() + ") " + "+ Surcharge (" + bfd.getSurcharge() + ") "
					+ "+ OtherCharges (" + bfd.getOtherCharges() + ") + Adjustments (" + adjAmount + ") " + "= Total ("
					+ total + ") ");

			bfd.setTotalAmount(CPSUtils.round(total.floatValue(), 2));

			BigDecimal netPayable = new BigDecimal(bfd.getTotalAmount() + bfd.getIntOnArrears() + bfd.getArrears());
			bfd.setNetPayableAmount(CPSUtils.round(netPayable.floatValue(), 2));

			log.debug(
					"Net Payable = Total (" + bfd.getTotalAmount() + ") " + "+ Int on Arrears (" + bfd.getIntOnArrears()
							+ ") " + "+ Arrears (" + bfd.getArrears() + ") " + "= Net Payable  (" + netPayable + ") ");

			LocalDateTime date = LocalDateTime.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmmss");

			bfd.setBillDate(date.toLocalDate());
			bfd.setBillTime(date.format(formatter));

			if (bill_details.getCurrentBillType().equals("M")) {
				bfd.setMetReadingMo(bill_details.getMetReadingDt().withDayOfMonth(1));
				bfd.setMetAvgKl(avgKL);
			}

			bfd.setMeterStatus(bill_details.getCurrentBillType());

			bfd.setUnits(billedUnitsKL);
			bfd.setFromMonth(dFrom.format(DateTimeFormatter.ofPattern("yyyyMM")));
			bfd.setToMonth(dTo.format(DateTimeFormatter.ofPattern("yyyyMM")));

			log.debug("This is the BillFullDetails:" + bfd);

			bfdRepository.save(bfd);

			adjustmentsRepository.save(adjustments);

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
			process_error(CPSUtils.getStackLimited("", e, 150),brd.getCan());	
			return;
		}
	}

	public void process_bill_normal(BillDetails bill_details, CustDetails customer) {

		if (!validateCust(customer, bill_details))
			return;

		if (billRunDetailsRepository.findByCanAndToMonth(bill_details.getCan(), bill_details.getToMonth()) != null) {
			process_error("Failed with error:" + CustValidation.ALREADY_BILLED.name(),bill_details.getCan());	
			return;
		}

		try {
			if (!bill_details.getCurrentBillType().equals("M"))
				bill_details.setPresentReading(customer.getPrevReading());

			dFrom = customer.getPrevBillMonth().plusMonths(1);

			dTo = bill_details.getBillDate().withDayOfMonth(bill_details.getBillDate().lengthOfMonth());

			// Previously Metered or Locked and currently Metered
			if ((customer.getPrevBillType().equals("L") || customer.getPrevBillType().equals("M"))
					&& bill_details.getCurrentBillType().equals("M")) {

				long billDays = ChronoUnit.DAYS.between(dFrom, dTo);

				if (billDays <= 0) {
					throw new Exception("Invalid From:" + dFrom.format(DateTimeFormatter.ofPattern("yyyyMM"))
							+ " and To:" + dTo.format(DateTimeFormatter.ofPattern("yyyyMM")));
				}

				log.debug("########################################");
				log.debug("          METER BILL CASE (" + billDays + " days)");
				log.debug("########################################");

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom.toString() + ", To:" + dTo.toString());
				log.debug("Units:" + unitsKL);
			}

			calc_units(customer, bill_details, dFrom, dTo, unitsKL, false);

			billedUnitsKL = unitsKL; // Will be calculated for Stuck and Burnt
										// cases.

			unMeteredFlag = (bill_details.getCurrentBillType().equals("U") ? 1 : 0);

			List<java.util.Map<String, Object>> charges = tariffMasterCustomRepository
					.findTariffs(bill_details.getCan(), dFrom, dTo, avgKL, unMeteredFlag, newMeterFlag);

			if (charges.isEmpty())
				throw new Exception("No tariffs configured.");

			BillFullDetails bfd = BillMapper.INSTANCE.bdToBfd(bill_details, customer);
			bfd.setId(null);

			bfd.setWaterCess(0.00f);
			bfd.setSewerageCess(0.00f);
			bfd.setServiceCharge(0.00f);
			bfd.setMeterServiceCharge(0.00f);
			bfd.setLockCharges(0.00f);
			bfd.setNoMeterAmt(0.00f);

			calc_charges_normal(charges, bill_details, customer, bfd, unitsKL);

			process_bill_common(customer, bill_details, bfd, dFrom, dTo);
		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150),brd.getCan());	
			return;
		}
	}

	public void getUnitsKLMeterChange(BillDetails bill_details, CustDetails customer) {
		float oldUnits = 0.0f;
		float newUnits = 0.0f;

		oldUnits = meterChange.getPrevMeterReading() - customer.getPrevReading();
		newUnits = bill_details.getPresentReading() - meterChange.getNewMeterReading();

		if (oldUnits < 0.0f)
			oldUnits = 0.0f;

		if (newUnits < 0.0f)
			newUnits = 0.0f;
		
		unitsKL = oldUnits + newUnits;
		
		log.debug("Meter Change: Old Units:" + oldUnits +", newUnits:" + newUnits + ", total:" + unitsKL);
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

	public boolean validateCust(CustDetails customer, BillDetails bill_details) {

		CustValidation retVal = getCustInfo(customer, bill_details);

		if (retVal != CustValidation.SUCCESS) {
			// Unable to process customer
			process_error("Failed with error:" + retVal.name(),brd.getCan());	
			return false;
		}

		return true;
	}

	public CustValidation getCustInfo(CustDetails customer, BillDetails bill_details) {

		if (customer.getPipeSize() < 0.5f)
			return CustValidation.INVALID_PIPESIZE;

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		if (newMeterFlag == 0) {
			if (customer.getPrevBillMonth() == null || customer.getPrevBillMonth().equals(""))
				return CustValidation.INVALID_PREV_BILL_MONTH;

			if (customer.getPrevBillMonth().isBefore(LocalDate.of(2005, 01, 01)))
				return CustValidation.NOT_IMPLEMENTED;

			if ((customer.getPrevBillType().equals("M") || customer.getPrevBillType().equals("L"))
					&& bill_details.getCurrentBillType().equals("U"))
				return CustValidation.INVALID_BILL_TYPE;

			if (customer.getPrevBillType().equals("R") && !bill_details.getCurrentBillType().equals("R"))
				return CustValidation.INVALID_BILL_TYPE;

			if (customer.getPrevBillType().equals("U") && !bill_details.getCurrentBillType().equals("U"))
				return CustValidation.INVALID_BILL_TYPE;
		}

		return CustValidation.SUCCESS;
	}

}

package com.callippus.water.erp.service;

import com.callippus.water.erp.common.CPSConstants;
import com.callippus.water.erp.common.CPSUtils;
import com.callippus.water.erp.domain.Adjustments;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.domain.ConnectionTerminate;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.domain.Customer;
import com.callippus.water.erp.domain.MeterChange;
import com.callippus.water.erp.domain.enumeration.BillingStatus;
import com.callippus.water.erp.domain.enumeration.ChangeCaseStatus;
import com.callippus.water.erp.domain.enumeration.CustStatus;
import com.callippus.water.erp.domain.enumeration.MeterChangeStatus;
import com.callippus.water.erp.domain.enumeration.TxnStatus;
import com.callippus.water.erp.mappings.BillMapper;
import com.callippus.water.erp.repository.AdjustmentsRepository;
import com.callippus.water.erp.repository.BillDetailsRepository;
import com.callippus.water.erp.repository.BillFullDetailsRepository;
import com.callippus.water.erp.repository.BillRunDetailsRepository;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;
import com.callippus.water.erp.repository.ConnectionTerminateRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.CustomerRepository;
import com.callippus.water.erp.repository.MeterChangeRepository;
import com.callippus.water.erp.repository.TariffMasterCustomRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

	@Inject
	private ConnectionTerminateRepository connectionTerminateRepository;

	@Inject
	private CustomerRepository customerRepository;

	enum Status {
		SUCCESS, FAILURE
	};

	enum CustValidation {
		ALREADY_BILLED, INVALID_BILL_TYPE, INVALID_METER_READING, INVALID_METER_READING_MONTH, INVALID_PIPESIZE, INVALID_CATEGORY, NOT_IMPLEMENTED, INVALID_PREV_BILL_MONTH, CUSTOMER_DOES_NOT_EXIST, SUCCESS
	};

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

	BigDecimal avgKL = CPSConstants.ZERO;
	BigDecimal factor = CPSConstants.ZERO;
	BigDecimal prevAvgKL = CPSConstants.ZERO;
	BigDecimal units = CPSConstants.ZERO;
	BigDecimal unitsKL = CPSConstants.ZERO;
	BigDecimal billedUnitsKL = CPSConstants.ZERO;
	BigDecimal partialUnitsKL = CPSConstants.ZERO;
	BigDecimal remUnitsKL = CPSConstants.ZERO;
	BigDecimal minAvgKL = CPSConstants.ZERO;
	String monthUpto;
	boolean hasSewer;
	BigDecimal ewura = CPSConstants.ZERO;
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

	BigDecimal total_amount = CPSConstants.ZERO, net_payable_amount = CPSConstants.ZERO, surcharge = CPSConstants.ZERO,
			total_cess = CPSConstants.ZERO, kl = CPSConstants.ZERO;

	public static void main(String[] args) throws Exception {
		LocalDate dFrom = null;
		LocalDate dTo = null;

		dFrom = LocalDate.of(2016, 05, 24);
		dTo = LocalDate.of(2016, 06, 30);

		System.out.println("THis is the dFrom:" + dFrom + ", to:" + dTo);
	}

	public BillRunMaster generateBill(boolean runAll) throws Exception {
		initBillRun();

		List<BillDetails> bd = billDetailsRepository.findAllInitiated();

		if (bd.size() == 0)
			throw new Exception("No new meter readings available to run bills.");

		processBillsWithMeter(bd);

		if (runAll) {
			processTerminatedMeters();
			processDisconnectedMeters();
			processBillsWithoutMeter();
		}

		if (failedRecords > 0)
			br.setStatus("Completed with Errors");
		else
			br.setStatus("Completed Successfully");

		billRunMasterRepository.save(br);

		return br;
	}

	public void processDisconnectedMeters() {
		List<CustDetails> customers = custDetailsRepository.findByStatus(CustStatus.DISCONNECTED.toString());
		customers.forEach(customer -> processDisconnection(customer));
	}

	public BillRunMaster generateSingleBill(String can) throws Exception {

		initBillRun();

		CustDetails custDetails = custDetailsRepository.findByCan(can);

		if (custDetails.getStatus() == CustStatus.DEACTIVE) {
			throw new Exception("Invalid CustStatus for CAN:" + custDetails.getCan() + ", Status:"
					+ custDetails.getStatus().toString());
		}

		
		if (custDetails.getStatus() == CustStatus.TERMINATED)
			saveAndRunTerminations(custDetails);
		else if(custDetails.getStatus() == CustStatus.DISCONNECTED)
			processDisconnection(custDetails);
		else if (custDetails.getPrevBillType() != null && custDetails.getPrevBillType().equals("U"))
			saveAndRunUnmetered(custDetails);
		else
			process_bill(can);

		if (failedRecords > 0)
			br.setStatus("Completed with Errors");
		else
			br.setStatus("Completed Successfully");

		billRunMasterRepository.save(br);

		return br;
	}
	
	public void processDisconnection(CustDetails custDetails) 
	{
		try
		{
			
			List<java.util.Map<String, Object>> charges = new ArrayList<java.util.Map<String, Object>>();
			for(int i=0;i<3;i++)
			{
				Map<String, Object> charge = new HashMap<String,Object>();
				charge.put("tariff_type_master_id", new Long(i));
				
				if(i != 2)
					charge.put("rate", CPSConstants.ZERO);
				else
					charge.put("rate", new BigDecimal("520.00"));
				
				charges.add(charge);			
			}

			BillDetails bill_details = billDetailsRepository.findValidBillForCan(custDetails.getCan());
			
			BillFullDetails bfd = BillMapper.INSTANCE.bdToBfd(bill_details, custDetails);
			bfd.setId(null);

			bfd.setWaterCess(CPSConstants.ZERO);
			bfd.setSewerageCess(CPSConstants.ZERO);
			bfd.setServiceCharge(CPSConstants.ZERO);
			bfd.setMeterServiceCharge(CPSConstants.ZERO);
			bfd.setLockCharges(CPSConstants.ZERO);
			bfd.setNoMeterAmt(CPSConstants.ZERO);

			calc_charges_normal(charges, bill_details, custDetails, bfd, unitsKL);

			process_bill_common(custDetails, bill_details, bfd, dFrom, dTo);			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void process_error(String message, String can) {
		log.debug(message + ":" + can);

		brd.setToDt(ZonedDateTime.now());
		brd.setStatus(0);
		brd.setRemarks("Failed with error:" + message + ":" + can);
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
			
			if (customer.getStatus() == CustStatus.DEACTIVE) {
				process_error("Invalid Status:"
						+ customer.getStatus().toString(), customer.getCan());
				return;
			}

			if (bill_details.getCurrentBillType().equals("M") || bill_details.getCurrentBillType().equals("S")) {
				if (meterChange != null) {
					getUnitsKLMeterChange(bill_details, customer);
				} else if (bill_details.getIsRounding()) {
					unitsKL = bill_details.getPresentReading().add(new BigDecimal("9999"))
							.subtract(bill_details.getInitialReading());
				} else {
					unitsKL = bill_details.getPresentReading().subtract(bill_details.getInitialReading());

					if (unitsKL.compareTo(CPSConstants.ZERO) < 0)
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
			process_error(CPSUtils.getStackLimited("", e, 150), brd.getCan());
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

			if (cd != null)
				minAvgKL = new BigDecimal(cd.getValue());
			else
				throw new Exception("Min Avg KL (MIN_AVG_KL) not configured in CONFIGURATION_DETAILS");

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

			BigDecimal kl = CPSConstants.ZERO;

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

				kl = kl.add(brd1.getBillFullDetails().getUnits());
				i++;
			}

			if (fromDt != null && toDt != null) {
				long months = ChronoUnit.MONTHS.between(fromDt, toDt.plusDays(1));

				if (months > 0)
					prevAvgKL = kl.divide(new BigDecimal(months));
			}

			CustDetails customer = custDetailsRepository.findByCanForUpdate(brd.getCan());
			customer.setPrevBillType(bfd.getCurrentBillType());
			customer.setPrevAvgKl(prevAvgKL);
			customer.setPrevBillMonth(LocalDate.parse(bfd.getToMonth() + "01", date_format));
			customer.setArrears(bfd.getNetPayableAmount());
			customer.setOtherCharges(CPSConstants.ZERO);
			customer.setPrevReading(bfd.getPresentReading());
			customer.setMetReadingDt(bfd.getMetReadingDt());
			customer.setMetReadingMo(bfd.getMetReadingDt().withDayOfMonth(1));

			if (customer.getStatus() == CustStatus.TERMINATED)
				customer.setStatus(CustStatus.DISCONNECTED);

			if (bfd.getCurrentBillType().equals("L"))
				customer.setLockCharges(bfd.getLockCharges());
			else
				customer.setLockCharges(CPSConstants.ZERO);

			List<Adjustments> adjustments = adjustmentsRepository
					.findByCanAndStatusAndBillFullDetails(customer.getCan(), TxnStatus.PROCESSING, bfd);

			for (Adjustments adj : adjustments) {
				adj.setStatus(TxnStatus.BILLED);
			}

			adjustmentsRepository.save(adjustments);

			MeterChange meterChange = meterChangeRepository.findByCanAndStatus(customer.getCan(),
					MeterChangeStatus.APPROVED);

			if (meterChange != null) {
				meterChange.setBillFullDetails(bfd);
				meterChange.setStatus(MeterChangeStatus.BILLED);

				meterChangeRepository.save(meterChange);
			}

			List<Customer> customerChanges = customerRepository.findByCanAndStatus(customer.getCan(),
					ChangeCaseStatus.APPROVED);

			for (Customer customerChange : customerChanges) {
				if (customerChange.getChangeType().equals("CONNECTIONCATEGORY")) {
					customer.setTariffCategoryMaster(customerChange.getNewTariffCategory());
					customerChange.setStatus(ChangeCaseStatus.BILLED);

					customerRepository.saveAndFlush(customerChange);
				} else if (customerChange.getChangeType().equals("PIPESIZE")) {
					customer.setPipeSize(new BigDecimal(customerChange.getRequestedPipeSizeMaster().getPipeSize()));
					customer.setPipeSizeMaster(customerChange.getRequestedPipeSizeMaster());
					customerChange.setStatus(ChangeCaseStatus.BILLED);

					customerRepository.saveAndFlush(customerChange);
				}
			}

			custDetailsRepository.saveAndFlush(customer);

			log.debug("Finished committing CAN:" + bd.getCan());

		} catch (Exception e) {
			e.printStackTrace();
			brd.setRemarks(CPSUtils.getStackLimited("Failed with error:", e, 250));
			brd.setStatus(BrdStatus.FAILED_COMMIT.getValue());
			billRunDetailsRepository.save(brd);
		}
	}

	public void initBillRun() throws Exception {

		successRecords = 0;
		failedRecords = 0;

		cd = configurationDetailsRepository.findOneByName("MIN_AVG_KL");

		if (cd != null)
			minAvgKL = new BigDecimal(cd.getValue());
		else
			throw new Exception("Min Avg KL (MIN_AVG_KL) not configured in CONFIGURATION_DETAILS");

		br = new BillRunMaster();
		br.setArea("0");
		br.setDate(ZonedDateTime.now());
		br.setSuccess(0);
		br.setFailed(0);
		br.setStatus("In Process");

		billRunMasterRepository.save(br);
	}

	public void initBill(String can) {
		avgKL = CPSConstants.ZERO;
		factor = CPSConstants.ZERO;
		prevAvgKL = CPSConstants.ZERO;
		units = CPSConstants.ZERO;
		unitsKL = CPSConstants.ZERO;
		billedUnitsKL = CPSConstants.ZERO;
		hasSewer = false;
		ewura = CPSConstants.ZERO;
		monthsDiff = 0;
		dFrom = null;
		dTo = null;
		newMeterFlag = 0;
		unMeteredFlag = 0;
		multipleTariffs = false;

		partialUnitsKL = CPSConstants.ZERO;
		remUnitsKL = CPSConstants.ZERO;

		total_amount = CPSConstants.ZERO;
		net_payable_amount = CPSConstants.ZERO;
		surcharge = CPSConstants.ZERO;
		total_cess = CPSConstants.ZERO;
		kl = CPSConstants.ZERO;

		meterChange = meterChangeRepository.findByCanAndStatus(can, MeterChangeStatus.APPROVED);

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

	public void processTerminatedMeters() {
		List<CustDetails> customers = custDetailsRepository.findByStatus(CustStatus.TERMINATED.toString());
		customers.forEach(customer -> saveAndRunTerminations(customer));
	}

	public void processBillsWithoutMeter() {
		List<CustDetails> customers = custDetailsRepository.findByPrevBillType("U");
		customers.forEach(customer -> saveAndRunUnmetered(customer));
	}

	public void saveAndRunTerminations(CustDetails customer) {
		// Insert bill_details record for each new termination
		ConnectionTerminate ct = connectionTerminateRepository.findByCan(customer.getCan());
		//BigDecimal currentReading = new BigDecimal(ct.getLastMeterReading()); //commented by mohib
		BigDecimal currentReading = ct.getLastMeterReading(); // added by mohib
		BigDecimal previousReading = customer.getPrevReading();
		LocalDate meterReadingDt = ct.getMeterRecoveredDate();
		String currentBillType = "";

		currentBillType = "M";

		saveAndRunBill(customer, currentBillType, meterReadingDt, previousReading, currentReading);
	}

	public void saveAndRunUnmetered(CustDetails customer) {
		// Insert bill_details record for each run
		if (customer.getStatus() != CustStatus.ACTIVE) {
			process_error("Customer not found in CUST_DETAILS for CAN", customer.getCan());
			return;
		}

		saveAndRunBill(customer, "U", LocalDate.now(), null, null);
	}

	public void saveAndRunBill(CustDetails customer, String currentBillType, LocalDate metReadingDt,
			BigDecimal prevReading, BigDecimal curReading) {
		initBill(customer.getCan()); // Is inited again in process_bill, but
		// right now there is no better
		// solution
		try {
			BillDetails bill_details = new BillDetails();
			bill_details.setCan(customer.getCan());
			LocalDate now = LocalDate.now();
			bill_details.setBillDate(now);
			bill_details.setCurrentBillType(currentBillType);
			bill_details.setIsRounding(false);

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
				throw new Exception("Customer does not have Prev Bill Month or Meter Fix Date");

			bill_details.setToMonth(now.format(date_format));
			bill_details.setMeterFixDate(customer.getMeterFixDate());
			bill_details.setInitialReading(prevReading);
			bill_details.setPresentReading(curReading);
			bill_details.setMetReadingDt(metReadingDt);
			bill_details.setInsertDt(ZonedDateTime.now());
			bill_details.setStatus(BillingStatus.INITIATED);
			bill_details.setMeterReaderId("1");

			billDetailsRepository.saveAndFlush(bill_details);

			process_bill(bill_details);

		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150), brd.getCan());
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

			bfd.setWaterCess(new BigDecimal("0.00"));
			bfd.setSewerageCess(new BigDecimal("0.00"));
			bfd.setServiceCharge(new BigDecimal("0.00"));
			bfd.setMeterServiceCharge(new BigDecimal("0.00"));
			bfd.setLockCharges(new BigDecimal("0.00"));
			bfd.setNoMeterAmt(new BigDecimal("0.00"));

			if (charges.size() > 3) {
				multipleTariffs = true; // Multiple tariffs for first bill.
										// Pro-rata required
				partialUnitsKL = new BigDecimal(days).divide(new BigDecimal(totDays).multiply(unitsKL));

				charges = tariffMasterCustomRepository.getTariffs(bill_details.getCan(), dFrom,
						dFrom.withDayOfMonth(dFrom.lengthOfMonth()), partialUnitsKL, unMeteredFlag, newMeterFlag);

				calc_charges_first(charges, bfd, partialUnitsKL, dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));

				log.debug("Charges for partial units:" + partialUnitsKL + "=> " + charges);

				remUnitsKL = unitsKL.subtract(partialUnitsKL);
				calc_units(customer, bill_details, dFrom.plusMonths(1).withDayOfMonth(1), dTo, remUnitsKL, false);

				log.debug("Charges for remaining units:" + remUnitsKL + ", avgKL:" + avgKL + "=>" + charges);

				cd = configurationDetailsRepository.findOneByName("IS_TELESCOPIC");

				boolean isTelescopic = (cd != null && cd.getValue().equalsIgnoreCase("true"))?true:false;
				
				charges = tariffMasterCustomRepository.findTariffs(bill_details.getCan(),
						dFrom.plusMonths(1).withDayOfMonth(1), dTo, avgKL, unMeteredFlag, newMeterFlag, isTelescopic);

				if (charges.isEmpty())
					throw new Exception("No tariffs configured. ");

				calc_charges_normal(charges, bill_details, customer, bfd, remUnitsKL);
			} else { // Single Tariff for first bill
				calc_charges_first(charges, bfd, unitsKL, dFrom, dTo);
			}

			process_bill_common(customer, bill_details, bfd, dFrom, dTo);

		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150), brd.getCan());
			return;
		}

	}

	public void calc_units(CustDetails customer, BillDetails bill_details, LocalDate dFrom, LocalDate dTo,
			BigDecimal unitsKL, boolean partialMonth) {

		try {
			long monthsDiff = ChronoUnit.MONTHS.between(dFrom, dTo.plusDays(1));

			long days = ChronoUnit.DAYS.between(dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));

			BigDecimal avgUnitsKL = CPSConstants.ZERO;

			if (monthsDiff == 0)
				monthsDiff = 1;

			log.debug("Months:" + monthsDiff);

			if (bill_details.getCurrentBillType().equals("M") && (customer.getPrevBillType() == null
					|| customer.getPrevBillType().equals("M") || customer.getPrevBillType().equals("L"))) {

				if (!customer.getPrevReading().equals("0")) {
					units = unitsKL.multiply(new BigDecimal("100.0"));

					avgKL = unitsKL.divide(new BigDecimal(monthsDiff));

					if (prevAvgKL.compareTo(CPSConstants.ZERO) != 0) {
						factor = avgKL.divide(prevAvgKL);

						log.debug("units:" + units + ", unitsKL=" + unitsKL + ", avgKL=" + avgKL + ", prevAvgKL="
								+ prevAvgKL + ", factor=" + factor);

						if (factor.compareTo(new BigDecimal("4.0")) > 0
								|| factor.compareTo(new BigDecimal("2.5")) < 0) {
							// Unable to process customer
							log.debug("Meter reading for:" + customer.getId() + ": is ::"
									+ bill_details.getPresentReading() + ". This is too high or too low.");
							return;
						}
					}
				}

				kl = unitsKL;
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

				avgKL = (customer.getPrevAvgKl() == null || customer.getPrevAvgKl().compareTo(new BigDecimal("0.1")) < 0
						? minAvgKL : customer.getPrevAvgKl());

				if (partialMonth) {
					long monthsDiff1 = ChronoUnit.MONTHS.between(dFrom, dTo.plusDays(1));

					BigDecimal partialMo = new BigDecimal(days).divide(new BigDecimal(dFrom.lengthOfMonth()));

					avgUnitsKL = avgKL.multiply((partialMo.add(new BigDecimal(monthsDiff1))));
				} else
					avgUnitsKL = avgKL.multiply(new BigDecimal(monthsDiff));

				this.unitsKL = (avgUnitsKL.compareTo(unitsKL) > 0 ? avgUnitsKL : unitsKL);

				units = this.unitsKL.multiply(new BigDecimal("100.0"));

				avgKL = this.unitsKL.divide(new BigDecimal(monthsDiff));

				log.debug("Units:" + units + " based on avgKL:" + avgKL + " for " + monthsDiff + " months.");
			} else if (bill_details.getCurrentBillType().equals("L") || bill_details.getCurrentBillType().equals("B")
					|| bill_details.getCurrentBillType().equals("R")) {

				log.debug("########################################");
				log.debug("          LOCK/BURNT BILL CASE");
				log.debug("########################################");

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = (customer.getPrevAvgKl() == null || customer.getPrevAvgKl().compareTo(new BigDecimal("0.1")) < 0
						? minAvgKL : customer.getPrevAvgKl());

				if (partialMonth) {
					long monthsDiff1 = ChronoUnit.MONTHS.between(dFrom, dTo.plusDays(1));

					BigDecimal partialMo = (new BigDecimal(days)).divide(new BigDecimal(dFrom.lengthOfMonth()));

					avgUnitsKL = avgKL.multiply(partialMo.add(new BigDecimal(monthsDiff1)));
				} else
					avgUnitsKL = avgKL.multiply(new BigDecimal(monthsDiff));

				this.unitsKL = (avgUnitsKL.compareTo(unitsKL) > 0 ? avgUnitsKL : unitsKL);

				units = this.unitsKL.multiply(new BigDecimal("100.0"));

				log.debug("Units:" + units + " based on avgKL:" + avgKL + " for " + monthsDiff + " months.");
			} else if (bill_details.getCurrentBillType().equals("U")) {

				log.debug("########################################");
				log.debug("          UNMETERED BILL CASE");
				log.debug("########################################");

				log.debug("Customer Info:" + customer.toString());
				log.debug("From:" + dFrom + ", To:" + dTo);

				avgKL = (customer.getPrevAvgKl() == null || customer.getPrevAvgKl().compareTo(new BigDecimal("0.1")) < 0
						? minAvgKL : customer.getPrevAvgKl());

				this.unitsKL = avgKL.multiply(new BigDecimal(monthsDiff));
				units = this.unitsKL.multiply(new BigDecimal("100.0"));
				log.debug("Units:" + units + " based on avgKL:" + avgKL + " for " + monthsDiff + " months.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150), brd.getCan());
			return;
		}
	}

	public void calc_charges_first(List<java.util.Map<String, Object>> charges, BillFullDetails bfd, BigDecimal units,
			LocalDate dFrom, LocalDate dTo) {
		long days = ChronoUnit.DAYS.between(dFrom, dFrom.withDayOfMonth(dFrom.lengthOfMonth()));
		long months = ChronoUnit.MONTHS.between(dFrom, dTo);
		months += (days >= 15 ? 1 : 0);

		for (Map<String, Object> charge : charges) {
			if (((Long) charge.get("tariff_type_master_id")) == 1) {
				bfd.setNoMeterAmt(bfd.getNoMeterAmt().add((BigDecimal) charge.get("rate")));
				bfd.setWaterCess(bfd.getWaterCess().add((BigDecimal) charge.get("rate")).multiply(units));
				log.debug("Usage Charge:" + ((BigDecimal) charge.get("rate")).multiply(units));
			} else if (((Long) charge.get("tariff_type_master_id")) == 2) {
				log.debug("Meter Rent:" + ((BigDecimal) charge.get("rate")).multiply(new BigDecimal(months)));
				bfd.setMeterServiceCharge(bfd.getMeterServiceCharge().add((BigDecimal) charge.get("rate"))
						.multiply(new BigDecimal(months)));
			} else if (((Long) charge.get("tariff_type_master_id")) == 3) {
				log.debug("Service Charge:" + ((BigDecimal) charge.get("rate")).multiply(new BigDecimal(months)));
				bfd.setServiceCharge(
						bfd.getServiceCharge().add((BigDecimal) charge.get("rate")).multiply(new BigDecimal(months)));
			}
		}
	}

	public void calc_charges_normal(List<java.util.Map<String, Object>> charges, BillDetails bill_details,
			CustDetails customer, BillFullDetails bfd, BigDecimal units) {

		// Subtract Avg Water charges in case of Lock Bill scenario
		for (Map<String, Object> charge : charges) {
			if (((Long) charge.get("tariff_type_master_id")) == 1) {

				bfd.setNoMeterAmt(((BigDecimal) charge.get("rate")));

				log.debug("Usage Charge:" + (BigDecimal) charge.get("amount"));
				bfd.setWaterCess(bfd.getWaterCess().add((BigDecimal) charge.get("amount")));

				if ((bill_details.getCurrentBillType().equals("M") || bill_details.getCurrentBillType().equals("S"))
						&& (customer.getPrevBillType() == null || customer.getPrevBillType().equals("L"))) {
					if (customer.getLockCharges() == null)
						bfd.setLockCharges(CPSConstants.ZERO);
					else {
						bfd.setLockCharges(new BigDecimal("-1").multiply(customer.getLockCharges()));
						bfd.setWaterCess(bfd.getWaterCess().add(bfd.getLockCharges()));
					}
				} else if (bill_details.getCurrentBillType().equals("L")) {
					if (customer.getLockCharges() != null)
						bfd.setLockCharges(bfd.getWaterCess().add(customer.getLockCharges()));
					else
						bfd.setLockCharges(bfd.getWaterCess());
				}

			} else if (((Long) charge.get("tariff_type_master_id")) == 2) {
				log.debug("Meter Rent:" + (BigDecimal) charge.get("amount"));
				bfd.setMeterServiceCharge(bfd.getMeterServiceCharge().add((BigDecimal) charge.get("amount")));
			} else if (((Long) charge.get("tariff_type_master_id")) == 3) {
				log.debug("Service Charge:" + (BigDecimal) charge.get("amount"));
				bfd.setServiceCharge(bfd.getServiceCharge().add((BigDecimal) charge.get("amount")));
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
				bfd.setSewerageCess(
						new BigDecimal(cd.getValue()).multiply(bfd.getWaterCess().divide(new BigDecimal("100.0"))));

			cd = configurationDetailsRepository.findOneByName("EWURA");

			log.debug("This is the EWURA Configuration:" + cd.toString());
			BigDecimal ewura = ((bfd.getWaterCess().add(bfd.getSewerageCess()).add(bfd.getMeterServiceCharge()).add(bfd.getServiceCharge())).multiply(new BigDecimal(cd.getValue())))
					.divide(new BigDecimal("100.0"));
			ewura = ewura.setScale(3, RoundingMode.CEILING);

			bfd.setSurcharge(ewura);

			List<Adjustments> adjustments = adjustmentsRepository
					.findByCanAndStatusAndBillFullDetails(bill_details.getCan(), TxnStatus.INITIATED, null);

			BigDecimal adjAmount = CPSConstants.ZERO;
			adjAmount = adjAmount.setScale(3, RoundingMode.CEILING);
			
			for (Adjustments adj : adjustments) {
				BigDecimal adj1 = adj.getAmount();
				adj1.setScale(3, RoundingMode.CEILING);
				adj.setBillFullDetails(bfd);
				adj.setCustDetails(customer);
				if (adj.getTransactionTypeMaster().getTypeOfTxn().equalsIgnoreCase("Credit"))
					adjAmount = adjAmount.add(adj1);
				else
					adjAmount = adjAmount.subtract(adj1);
			}

			BigDecimal total = bfd.getWaterCess().add(bfd.getMeterServiceCharge()).add(bfd.getServiceCharge())
					.add(bfd.getSewerageCess()).add(bfd.getSurcharge()).add(bfd.getOtherCharges()).add(adjAmount);
			total = total.setScale(3, RoundingMode.CEILING);

			log.debug("Total=Water Cess (" + bfd.getWaterCess() + ") " + "+ Meter Svc Charge("
					+ bfd.getMeterServiceCharge() + ") " + "+ Service Charge (" + bfd.getServiceCharge() + ") "
					+ "+ SewerageCess (" + bfd.getSewerageCess() + ") " + "+ Surcharge (" + bfd.getSurcharge() + ") "
					+ "+ OtherCharges (" + bfd.getOtherCharges() + ") + Adjustments (" + adjAmount + ") " + "= Total ("
					+ total + ") ");

			bfd.setTotalAmount(total);

			BigDecimal netPayable = bfd.getTotalAmount().add(bfd.getIntOnArrears()).add(bfd.getArrears());
			netPayable = netPayable.setScale(3, RoundingMode.CEILING);
			bfd.setNetPayableAmount(netPayable);
			bfd.setDueAmount(total);

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
			process_error(CPSUtils.getStackLimited("", e, 150), brd.getCan());
			return;
		}
	}

	public void process_bill_normal(BillDetails bill_details, CustDetails customer) {

		if (!validateCust(customer, bill_details))
			return;

		if (billRunDetailsRepository.findByCanAndToMonth(bill_details.getCan(), bill_details.getToMonth()) != null) {
			process_error("Failed with error:" + CustValidation.ALREADY_BILLED.name(), bill_details.getCan());
			return;
		}

		try {
			if (!(bill_details.getCurrentBillType().equals("M")) || bill_details.getCurrentBillType().equals("S"))
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

			cd = configurationDetailsRepository.findOneByName("IS_TELESCOPIC");

			boolean isTelescopic = (cd != null && cd.getValue().equalsIgnoreCase("true"))?true:false;
			
			List<java.util.Map<String, Object>> charges = tariffMasterCustomRepository
					.findTariffs(bill_details.getCan(), dFrom, dTo, avgKL, unMeteredFlag, newMeterFlag, isTelescopic);

			if (charges.isEmpty())
				throw new Exception("No tariffs configured.");

			BillFullDetails bfd = BillMapper.INSTANCE.bdToBfd(bill_details, customer);
			bfd.setId(null);

			bfd.setWaterCess(CPSConstants.ZERO);
			bfd.setSewerageCess(CPSConstants.ZERO);
			bfd.setServiceCharge(CPSConstants.ZERO);
			bfd.setMeterServiceCharge(CPSConstants.ZERO);
			bfd.setLockCharges(CPSConstants.ZERO);
			bfd.setNoMeterAmt(CPSConstants.ZERO);

			calc_charges_normal(charges, bill_details, customer, bfd, unitsKL);

			process_bill_common(customer, bill_details, bfd, dFrom, dTo);
		} catch (Exception e) {
			e.printStackTrace();
			process_error(CPSUtils.getStackLimited("", e, 150), brd.getCan());
			return;
		}
	}

	public void getUnitsKLMeterChange(BillDetails bill_details, CustDetails customer) {
		BigDecimal oldUnits = CPSConstants.ZERO;
		BigDecimal newUnits = CPSConstants.ZERO;

		//oldUnits = (new BigDecimal(meterChange.getPrevMeterReading()).subtract(customer.getPrevReading())); // commented by mohib
		//newUnits = bill_details.getPresentReading().subtract(new BigDecimal(meterChange.getNewMeterReading())); commented by mohib
		oldUnits = (meterChange.getPrevMeterReading().subtract(customer.getPrevReading())); // added by mohib
		newUnits = bill_details.getPresentReading().subtract(meterChange.getNewMeterReading()); // added by mohib

		if (oldUnits.compareTo(CPSConstants.ZERO) < 0)
			oldUnits = CPSConstants.ZERO;

		if (newUnits.compareTo(CPSConstants.ZERO) < 0)
			newUnits = CPSConstants.ZERO;

		unitsKL = oldUnits.add(newUnits);

		log.debug("Meter Change: Old Units:" + oldUnits + ", newUnits:" + newUnits + ", total:" + unitsKL);
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
			process_error("Failed with error:" + retVal.name(), brd.getCan());
			return false;
		}

		return true;
	}

	public CustValidation getCustInfo(CustDetails customer, BillDetails bill_details) {

		if (customer.getPipeSize().compareTo(new BigDecimal("0.25")) < 0)
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

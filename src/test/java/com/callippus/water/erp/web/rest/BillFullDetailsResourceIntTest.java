package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BillFullDetails;
import com.callippus.water.erp.repository.BillFullDetailsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BillFullDetailsResource REST controller.
 *
 * @see BillFullDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BillFullDetailsResourceIntTest {

    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";
    private static final String DEFAULT_DIVCODE = "AAAAA";
    private static final String UPDATED_DIVCODE = "BBBBB";
    private static final String DEFAULT_SEC_CODE = "AAAAA";
    private static final String UPDATED_SEC_CODE = "BBBBB";
    private static final String DEFAULT_SEC_NAME = "AAAAA";
    private static final String UPDATED_SEC_NAME = "BBBBB";
    private static final String DEFAULT_MET_READER_CODE = "AAAAA";
    private static final String UPDATED_MET_READER_CODE = "BBBBB";

    private static final LocalDate DEFAULT_CONN_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONN_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_CONS_NAME = "AAAAA";
    private static final String UPDATED_CONS_NAME = "BBBBB";
    private static final String DEFAULT_HOUSE_NO = "AAAAA";
    private static final String UPDATED_HOUSE_NO = "BBBBB";
    private static final String DEFAULT_ADDRESS = "AAAAA";
    private static final String UPDATED_ADDRESS = "BBBBB";
    private static final String DEFAULT_CITY = "AAAAA";
    private static final String UPDATED_CITY = "BBBBB";
    private static final String DEFAULT_PIN_CODE = "AAAAA";
    private static final String UPDATED_PIN_CODE = "BBBBB";
    private static final String DEFAULT_CATEGORY = "AAAAA";
    private static final String UPDATED_CATEGORY = "BBBBB";

    private static final Float DEFAULT_PIPE_SIZE = 1F;
    private static final Float UPDATED_PIPE_SIZE = 2F;
    private static final String DEFAULT_BOARD_METER = "AAAAA";
    private static final String UPDATED_BOARD_METER = "BBBBB";
    private static final String DEFAULT_SEWERAGE = "AAAAA";
    private static final String UPDATED_SEWERAGE = "BBBBB";
    private static final String DEFAULT_METER_NO = "AAAAA";
    private static final String UPDATED_METER_NO = "BBBBB";
    private static final String DEFAULT_PREV_BILL_TYPE = "AAAAA";
    private static final String UPDATED_PREV_BILL_TYPE = "BBBBB";

    private static final LocalDate DEFAULT_PREV_BILL_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREV_BILL_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_PREV_AVG_KL = 1F;
    private static final Float UPDATED_PREV_AVG_KL = 2F;

    private static final LocalDate DEFAULT_MET_READING_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MET_READING_DT = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_PREV_READING = 1F;
    private static final Float UPDATED_PREV_READING = 2F;

    private static final LocalDate DEFAULT_MET_READING_MO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MET_READING_MO = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_MET_AVG_KL = 1F;
    private static final Float UPDATED_MET_AVG_KL = 2F;

    private static final Float DEFAULT_ARREARS = 1F;
    private static final Float UPDATED_ARREARS = 2F;

    private static final Float DEFAULT_REVERSAL_AMT = 1F;
    private static final Float UPDATED_REVERSAL_AMT = 2F;

    private static final Float DEFAULT_INSTALLMENT = 1F;
    private static final Float UPDATED_INSTALLMENT = 2F;

    private static final Float DEFAULT_OTHER_CHARGES = 1F;
    private static final Float UPDATED_OTHER_CHARGES = 2F;

    private static final Float DEFAULT_SURCHARGE = 1F;
    private static final Float UPDATED_SURCHARGE = 2F;

    private static final Float DEFAULT_HRS_SURCHARGE = 1F;
    private static final Float UPDATED_HRS_SURCHARGE = 2F;

    private static final Long DEFAULT_RES_UNITS = 1L;
    private static final Long UPDATED_RES_UNITS = 2L;

    private static final Float DEFAULT_MET_COST_INSTALLMENT = 1F;
    private static final Float UPDATED_MET_COST_INSTALLMENT = 2F;

    private static final Float DEFAULT_INT_ON_ARREARS = 1F;
    private static final Float UPDATED_INT_ON_ARREARS = 2F;

    private static final LocalDate DEFAULT_LAST_PYMT_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_PYMT_DT = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_LAST_PYMT_AMT = 1F;
    private static final Float UPDATED_LAST_PYMT_AMT = 2F;
    private static final String DEFAULT_BILL_NUMBER = "AAAAA";
    private static final String UPDATED_BILL_NUMBER = "BBBBB";

    private static final LocalDate DEFAULT_BILL_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BILL_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_BILL_TIME = "AAAAA";
    private static final String UPDATED_BILL_TIME = "BBBBB";
    private static final String DEFAULT_METER_MAKE = "AAAAA";
    private static final String UPDATED_METER_MAKE = "BBBBB";
    private static final String DEFAULT_CURRENT_BILL_TYPE = "AAAAA";
    private static final String UPDATED_CURRENT_BILL_TYPE = "BBBBB";
    private static final String DEFAULT_FROM_MONTH = "AAAAA";
    private static final String UPDATED_FROM_MONTH = "BBBBB";
    private static final String DEFAULT_TO_MONTH = "AAAAA";
    private static final String UPDATED_TO_MONTH = "BBBBB";
    private static final String DEFAULT_METER_FIX_DATE = "AAAAA";
    private static final String UPDATED_METER_FIX_DATE = "BBBBB";

    private static final Float DEFAULT_INITIAL_READING = 1F;
    private static final Float UPDATED_INITIAL_READING = 2F;

    private static final Float DEFAULT_PRESENT_READING = 1F;
    private static final Float UPDATED_PRESENT_READING = 2F;

    private static final Float DEFAULT_UNITS = 1F;
    private static final Float UPDATED_UNITS = 2F;

    private static final Float DEFAULT_WATER_CESS = 1F;
    private static final Float UPDATED_WATER_CESS = 2F;

    private static final Float DEFAULT_SEWERAGE_CESS = 1F;
    private static final Float UPDATED_SEWERAGE_CESS = 2F;

    private static final Float DEFAULT_SERVICE_CHARGE = 1F;
    private static final Float UPDATED_SERVICE_CHARGE = 2F;

    private static final Float DEFAULT_METER_SERVICE_CHARGE = 1F;
    private static final Float UPDATED_METER_SERVICE_CHARGE = 2F;

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;

    private static final Float DEFAULT_NET_PAYABLE_AMOUNT = 1F;
    private static final Float UPDATED_NET_PAYABLE_AMOUNT = 2F;
    private static final String DEFAULT_TELEPHONE_NO = "AAAAA";
    private static final String UPDATED_TELEPHONE_NO = "BBBBB";
    private static final String DEFAULT_METER_STATUS = "AAAAA";
    private static final String UPDATED_METER_STATUS = "BBBBB";
    private static final String DEFAULT_BILL_FLAG = "AAAAA";
    private static final String UPDATED_BILL_FLAG = "BBBBB";
    private static final String DEFAULT_SVR_STATUS = "AAAAA";
    private static final String UPDATED_SVR_STATUS = "BBBBB";
    private static final String DEFAULT_TERMINAL_ID = "AAAAA";
    private static final String UPDATED_TERMINAL_ID = "BBBBB";
    private static final String DEFAULT_METER_READER_ID = "AAAAA";
    private static final String UPDATED_METER_READER_ID = "BBBBB";
    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";
    private static final String DEFAULT_MOBILE_NO = "AAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBB";
    private static final String DEFAULT_NOTICE_NO = "AAAAA";
    private static final String UPDATED_NOTICE_NO = "BBBBB";
    private static final String DEFAULT_LAT = "AAAAA";
    private static final String UPDATED_LAT = "BBBBB";
    private static final String DEFAULT_LONGI = "AAAAA";
    private static final String UPDATED_LONGI = "BBBBB";

    private static final Float DEFAULT_NOMETER_AMT = 1F;
    private static final Float UPDATED_NOMETER_AMT = 2F;

    @Inject
    private BillFullDetailsRepository billFullDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBillFullDetailsMockMvc;

    private BillFullDetails billFullDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BillFullDetailsResource billFullDetailsResource = new BillFullDetailsResource();
        ReflectionTestUtils.setField(billFullDetailsResource, "billFullDetailsRepository", billFullDetailsRepository);
        this.restBillFullDetailsMockMvc = MockMvcBuilders.standaloneSetup(billFullDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        billFullDetails = new BillFullDetails();
        billFullDetails.setCan(DEFAULT_CAN);
        billFullDetails.setDivcode(DEFAULT_DIVCODE);
        billFullDetails.setSecCode(DEFAULT_SEC_CODE);
        billFullDetails.setSecName(DEFAULT_SEC_NAME);
        billFullDetails.setMetReaderCode(DEFAULT_MET_READER_CODE);
        billFullDetails.setConnDate(DEFAULT_CONN_DATE);
        billFullDetails.setConsName(DEFAULT_CONS_NAME);
        billFullDetails.setHouseNo(DEFAULT_HOUSE_NO);
        billFullDetails.setAddress(DEFAULT_ADDRESS);
        billFullDetails.setCity(DEFAULT_CITY);
        billFullDetails.setPinCode(DEFAULT_PIN_CODE);
        billFullDetails.setCategory(DEFAULT_CATEGORY);
        billFullDetails.setPipeSize(DEFAULT_PIPE_SIZE);
        billFullDetails.setBoardMeter(DEFAULT_BOARD_METER);
        billFullDetails.setSewerage(DEFAULT_SEWERAGE);
        billFullDetails.setMeterNo(DEFAULT_METER_NO);
        billFullDetails.setPrevBillType(DEFAULT_PREV_BILL_TYPE);
        billFullDetails.setPrevBillMonth(DEFAULT_PREV_BILL_MONTH);
        billFullDetails.setPrevAvgKl(DEFAULT_PREV_AVG_KL);
        billFullDetails.setMetReadingDt(DEFAULT_MET_READING_DT);
        billFullDetails.setPrevReading(DEFAULT_PREV_READING);
        billFullDetails.setMetReadingMo(DEFAULT_MET_READING_MO);
        billFullDetails.setMetAvgKl(DEFAULT_MET_AVG_KL);
        billFullDetails.setArrears(DEFAULT_ARREARS);
        billFullDetails.setReversalAmt(DEFAULT_REVERSAL_AMT);
        billFullDetails.setInstallment(DEFAULT_INSTALLMENT);
        billFullDetails.setOtherCharges(DEFAULT_OTHER_CHARGES);
        billFullDetails.setSurcharge(DEFAULT_SURCHARGE);
        billFullDetails.setHrsSurcharge(DEFAULT_HRS_SURCHARGE);
        billFullDetails.setResUnits(DEFAULT_RES_UNITS);
        billFullDetails.setMetCostInstallment(DEFAULT_MET_COST_INSTALLMENT);
        billFullDetails.setIntOnArrears(DEFAULT_INT_ON_ARREARS);
        billFullDetails.setLastPymtDt(DEFAULT_LAST_PYMT_DT);
        billFullDetails.setLastPymtAmt(DEFAULT_LAST_PYMT_AMT);
        billFullDetails.setBillNumber(DEFAULT_BILL_NUMBER);
        billFullDetails.setBillDate(DEFAULT_BILL_DATE);
        billFullDetails.setBillTime(DEFAULT_BILL_TIME);
        billFullDetails.setMeterMake(DEFAULT_METER_MAKE);
        billFullDetails.setCurrentBillType(DEFAULT_CURRENT_BILL_TYPE);
        billFullDetails.setFromMonth(DEFAULT_FROM_MONTH);
        billFullDetails.setToMonth(DEFAULT_TO_MONTH);
        billFullDetails.setMeterFixDate(DEFAULT_METER_FIX_DATE);
        billFullDetails.setInitialReading(DEFAULT_INITIAL_READING);
        billFullDetails.setPresentReading(DEFAULT_PRESENT_READING);
        billFullDetails.setUnits(DEFAULT_UNITS);
        billFullDetails.setWaterCess(DEFAULT_WATER_CESS);
        billFullDetails.setSewerageCess(DEFAULT_SEWERAGE_CESS);
        billFullDetails.setServiceCharge(DEFAULT_SERVICE_CHARGE);
        billFullDetails.setMeterServiceCharge(DEFAULT_METER_SERVICE_CHARGE);
        billFullDetails.setTotalAmount(DEFAULT_TOTAL_AMOUNT);
        billFullDetails.setNetPayableAmount(DEFAULT_NET_PAYABLE_AMOUNT);
        billFullDetails.setTelephoneNo(DEFAULT_TELEPHONE_NO);
        billFullDetails.setMeterStatus(DEFAULT_METER_STATUS);
        billFullDetails.setBillFlag(DEFAULT_BILL_FLAG);
        billFullDetails.setSvrStatus(DEFAULT_SVR_STATUS);
        billFullDetails.setTerminalId(DEFAULT_TERMINAL_ID);
        billFullDetails.setMeterReaderId(DEFAULT_METER_READER_ID);
        billFullDetails.setUserId(DEFAULT_USER_ID);
        billFullDetails.setMobileNo(DEFAULT_MOBILE_NO);
        billFullDetails.setNoticeNo(DEFAULT_NOTICE_NO);
        billFullDetails.setLat(DEFAULT_LAT);
        billFullDetails.setLongi(DEFAULT_LONGI);
        billFullDetails.setNometerAmt(DEFAULT_NOMETER_AMT);
    }

    @Test
    @Transactional
    public void createBillFullDetails() throws Exception {
        int databaseSizeBeforeCreate = billFullDetailsRepository.findAll().size();

        // Create the BillFullDetails

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isCreated());

        // Validate the BillFullDetails in the database
        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeCreate + 1);
        BillFullDetails testBillFullDetails = billFullDetailss.get(billFullDetailss.size() - 1);
        assertThat(testBillFullDetails.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testBillFullDetails.getDivcode()).isEqualTo(DEFAULT_DIVCODE);
        assertThat(testBillFullDetails.getSecCode()).isEqualTo(DEFAULT_SEC_CODE);
        assertThat(testBillFullDetails.getSecName()).isEqualTo(DEFAULT_SEC_NAME);
        assertThat(testBillFullDetails.getMetReaderCode()).isEqualTo(DEFAULT_MET_READER_CODE);
        assertThat(testBillFullDetails.getConnDate()).isEqualTo(DEFAULT_CONN_DATE);
        assertThat(testBillFullDetails.getConsName()).isEqualTo(DEFAULT_CONS_NAME);
        assertThat(testBillFullDetails.getHouseNo()).isEqualTo(DEFAULT_HOUSE_NO);
        assertThat(testBillFullDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testBillFullDetails.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testBillFullDetails.getPinCode()).isEqualTo(DEFAULT_PIN_CODE);
        assertThat(testBillFullDetails.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testBillFullDetails.getPipeSize()).isEqualTo(DEFAULT_PIPE_SIZE);
        assertThat(testBillFullDetails.getBoardMeter()).isEqualTo(DEFAULT_BOARD_METER);
        assertThat(testBillFullDetails.getSewerage()).isEqualTo(DEFAULT_SEWERAGE);
        assertThat(testBillFullDetails.getMeterNo()).isEqualTo(DEFAULT_METER_NO);
        assertThat(testBillFullDetails.getPrevBillType()).isEqualTo(DEFAULT_PREV_BILL_TYPE);
        assertThat(testBillFullDetails.getPrevBillMonth()).isEqualTo(DEFAULT_PREV_BILL_MONTH);
        assertThat(testBillFullDetails.getPrevAvgKl()).isEqualTo(DEFAULT_PREV_AVG_KL);
        assertThat(testBillFullDetails.getMetReadingDt()).isEqualTo(DEFAULT_MET_READING_DT);
        assertThat(testBillFullDetails.getPrevReading()).isEqualTo(DEFAULT_PREV_READING);
        assertThat(testBillFullDetails.getMetReadingMo()).isEqualTo(DEFAULT_MET_READING_MO);
        assertThat(testBillFullDetails.getMetAvgKl()).isEqualTo(DEFAULT_MET_AVG_KL);
        assertThat(testBillFullDetails.getArrears()).isEqualTo(DEFAULT_ARREARS);
        assertThat(testBillFullDetails.getReversalAmt()).isEqualTo(DEFAULT_REVERSAL_AMT);
        assertThat(testBillFullDetails.getInstallment()).isEqualTo(DEFAULT_INSTALLMENT);
        assertThat(testBillFullDetails.getOtherCharges()).isEqualTo(DEFAULT_OTHER_CHARGES);
        assertThat(testBillFullDetails.getSurcharge()).isEqualTo(DEFAULT_SURCHARGE);
        assertThat(testBillFullDetails.getHrsSurcharge()).isEqualTo(DEFAULT_HRS_SURCHARGE);
        assertThat(testBillFullDetails.getResUnits()).isEqualTo(DEFAULT_RES_UNITS);
        assertThat(testBillFullDetails.getMetCostInstallment()).isEqualTo(DEFAULT_MET_COST_INSTALLMENT);
        assertThat(testBillFullDetails.getIntOnArrears()).isEqualTo(DEFAULT_INT_ON_ARREARS);
        assertThat(testBillFullDetails.getLastPymtDt()).isEqualTo(DEFAULT_LAST_PYMT_DT);
        assertThat(testBillFullDetails.getLastPymtAmt()).isEqualTo(DEFAULT_LAST_PYMT_AMT);
        assertThat(testBillFullDetails.getBillNumber()).isEqualTo(DEFAULT_BILL_NUMBER);
        assertThat(testBillFullDetails.getBillDate()).isEqualTo(DEFAULT_BILL_DATE);
        assertThat(testBillFullDetails.getBillTime()).isEqualTo(DEFAULT_BILL_TIME);
        assertThat(testBillFullDetails.getMeterMake()).isEqualTo(DEFAULT_METER_MAKE);
        assertThat(testBillFullDetails.getCurrentBillType()).isEqualTo(DEFAULT_CURRENT_BILL_TYPE);
        assertThat(testBillFullDetails.getFromMonth()).isEqualTo(DEFAULT_FROM_MONTH);
        assertThat(testBillFullDetails.getToMonth()).isEqualTo(DEFAULT_TO_MONTH);
        assertThat(testBillFullDetails.getMeterFixDate()).isEqualTo(DEFAULT_METER_FIX_DATE);
        assertThat(testBillFullDetails.getInitialReading()).isEqualTo(DEFAULT_INITIAL_READING);
        assertThat(testBillFullDetails.getPresentReading()).isEqualTo(DEFAULT_PRESENT_READING);
        assertThat(testBillFullDetails.getUnits()).isEqualTo(DEFAULT_UNITS);
        assertThat(testBillFullDetails.getWaterCess()).isEqualTo(DEFAULT_WATER_CESS);
        assertThat(testBillFullDetails.getSewerageCess()).isEqualTo(DEFAULT_SEWERAGE_CESS);
        assertThat(testBillFullDetails.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testBillFullDetails.getMeterServiceCharge()).isEqualTo(DEFAULT_METER_SERVICE_CHARGE);
        assertThat(testBillFullDetails.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testBillFullDetails.getNetPayableAmount()).isEqualTo(DEFAULT_NET_PAYABLE_AMOUNT);
        assertThat(testBillFullDetails.getTelephoneNo()).isEqualTo(DEFAULT_TELEPHONE_NO);
        assertThat(testBillFullDetails.getMeterStatus()).isEqualTo(DEFAULT_METER_STATUS);
        assertThat(testBillFullDetails.getBillFlag()).isEqualTo(DEFAULT_BILL_FLAG);
        assertThat(testBillFullDetails.getSvrStatus()).isEqualTo(DEFAULT_SVR_STATUS);
        assertThat(testBillFullDetails.getTerminalId()).isEqualTo(DEFAULT_TERMINAL_ID);
        assertThat(testBillFullDetails.getMeterReaderId()).isEqualTo(DEFAULT_METER_READER_ID);
        assertThat(testBillFullDetails.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBillFullDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testBillFullDetails.getNoticeNo()).isEqualTo(DEFAULT_NOTICE_NO);
        assertThat(testBillFullDetails.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testBillFullDetails.getLongi()).isEqualTo(DEFAULT_LONGI);
        assertThat(testBillFullDetails.getNometerAmt()).isEqualTo(DEFAULT_NOMETER_AMT);
    }

    @Test
    @Transactional
    public void checkCanIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setCan(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDivcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setDivcode(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConnDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setConnDate(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setConsName(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHouseNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setHouseNo(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setCity(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMeterNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setMeterNo(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMetReadingDtIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setMetReadingDt(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastPymtDtIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setLastPymtDt(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBillDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = billFullDetailsRepository.findAll().size();
        // set the field null
        billFullDetails.setBillDate(null);

        // Create the BillFullDetails, which fails.

        restBillFullDetailsMockMvc.perform(post("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isBadRequest());

        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBillFullDetailss() throws Exception {
        // Initialize the database
        billFullDetailsRepository.saveAndFlush(billFullDetails);

        // Get all the billFullDetailss
        restBillFullDetailsMockMvc.perform(get("/api/billFullDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(billFullDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].divcode").value(hasItem(DEFAULT_DIVCODE.toString())))
                .andExpect(jsonPath("$.[*].secCode").value(hasItem(DEFAULT_SEC_CODE.toString())))
                .andExpect(jsonPath("$.[*].secName").value(hasItem(DEFAULT_SEC_NAME.toString())))
                .andExpect(jsonPath("$.[*].metReaderCode").value(hasItem(DEFAULT_MET_READER_CODE.toString())))
                .andExpect(jsonPath("$.[*].connDate").value(hasItem(DEFAULT_CONN_DATE.toString())))
                .andExpect(jsonPath("$.[*].consName").value(hasItem(DEFAULT_CONS_NAME.toString())))
                .andExpect(jsonPath("$.[*].houseNo").value(hasItem(DEFAULT_HOUSE_NO.toString())))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
                .andExpect(jsonPath("$.[*].pinCode").value(hasItem(DEFAULT_PIN_CODE.toString())))
                .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
                .andExpect(jsonPath("$.[*].pipeSize").value(hasItem(DEFAULT_PIPE_SIZE.doubleValue())))
                .andExpect(jsonPath("$.[*].boardMeter").value(hasItem(DEFAULT_BOARD_METER.toString())))
                .andExpect(jsonPath("$.[*].sewerage").value(hasItem(DEFAULT_SEWERAGE.toString())))
                .andExpect(jsonPath("$.[*].meterNo").value(hasItem(DEFAULT_METER_NO.toString())))
                .andExpect(jsonPath("$.[*].prevBillType").value(hasItem(DEFAULT_PREV_BILL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].prevBillMonth").value(hasItem(DEFAULT_PREV_BILL_MONTH.toString())))
                .andExpect(jsonPath("$.[*].prevAvgKl").value(hasItem(DEFAULT_PREV_AVG_KL.doubleValue())))
                .andExpect(jsonPath("$.[*].metReadingDt").value(hasItem(DEFAULT_MET_READING_DT.toString())))
                .andExpect(jsonPath("$.[*].prevReading").value(hasItem(DEFAULT_PREV_READING.doubleValue())))
                .andExpect(jsonPath("$.[*].metReadingMo").value(hasItem(DEFAULT_MET_READING_MO.toString())))
                .andExpect(jsonPath("$.[*].metAvgKl").value(hasItem(DEFAULT_MET_AVG_KL.doubleValue())))
                .andExpect(jsonPath("$.[*].arrears").value(hasItem(DEFAULT_ARREARS.doubleValue())))
                .andExpect(jsonPath("$.[*].reversalAmt").value(hasItem(DEFAULT_REVERSAL_AMT.doubleValue())))
                .andExpect(jsonPath("$.[*].installment").value(hasItem(DEFAULT_INSTALLMENT.doubleValue())))
                .andExpect(jsonPath("$.[*].otherCharges").value(hasItem(DEFAULT_OTHER_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].surcharge").value(hasItem(DEFAULT_SURCHARGE.doubleValue())))
                .andExpect(jsonPath("$.[*].hrsSurcharge").value(hasItem(DEFAULT_HRS_SURCHARGE.doubleValue())))
                .andExpect(jsonPath("$.[*].resUnits").value(hasItem(DEFAULT_RES_UNITS.intValue())))
                .andExpect(jsonPath("$.[*].metCostInstallment").value(hasItem(DEFAULT_MET_COST_INSTALLMENT.doubleValue())))
                .andExpect(jsonPath("$.[*].intOnArrears").value(hasItem(DEFAULT_INT_ON_ARREARS.doubleValue())))
                .andExpect(jsonPath("$.[*].lastPymtDt").value(hasItem(DEFAULT_LAST_PYMT_DT.toString())))
                .andExpect(jsonPath("$.[*].lastPymtAmt").value(hasItem(DEFAULT_LAST_PYMT_AMT.doubleValue())))
                .andExpect(jsonPath("$.[*].billNumber").value(hasItem(DEFAULT_BILL_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].billDate").value(hasItem(DEFAULT_BILL_DATE.toString())))
                .andExpect(jsonPath("$.[*].billTime").value(hasItem(DEFAULT_BILL_TIME.toString())))
                .andExpect(jsonPath("$.[*].meterMake").value(hasItem(DEFAULT_METER_MAKE.toString())))
                .andExpect(jsonPath("$.[*].currentBillType").value(hasItem(DEFAULT_CURRENT_BILL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].fromMonth").value(hasItem(DEFAULT_FROM_MONTH.toString())))
                .andExpect(jsonPath("$.[*].toMonth").value(hasItem(DEFAULT_TO_MONTH.toString())))
                .andExpect(jsonPath("$.[*].meterFixDate").value(hasItem(DEFAULT_METER_FIX_DATE.toString())))
                .andExpect(jsonPath("$.[*].initialReading").value(hasItem(DEFAULT_INITIAL_READING.doubleValue())))
                .andExpect(jsonPath("$.[*].presentReading").value(hasItem(DEFAULT_PRESENT_READING.doubleValue())))
                .andExpect(jsonPath("$.[*].units").value(hasItem(DEFAULT_UNITS.doubleValue())))
                .andExpect(jsonPath("$.[*].waterCess").value(hasItem(DEFAULT_WATER_CESS.doubleValue())))
                .andExpect(jsonPath("$.[*].sewerageCess").value(hasItem(DEFAULT_SEWERAGE_CESS.doubleValue())))
                .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.doubleValue())))
                .andExpect(jsonPath("$.[*].meterServiceCharge").value(hasItem(DEFAULT_METER_SERVICE_CHARGE.doubleValue())))
                .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].netPayableAmount").value(hasItem(DEFAULT_NET_PAYABLE_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].telephoneNo").value(hasItem(DEFAULT_TELEPHONE_NO.toString())))
                .andExpect(jsonPath("$.[*].meterStatus").value(hasItem(DEFAULT_METER_STATUS.toString())))
                .andExpect(jsonPath("$.[*].billFlag").value(hasItem(DEFAULT_BILL_FLAG.toString())))
                .andExpect(jsonPath("$.[*].svrStatus").value(hasItem(DEFAULT_SVR_STATUS.toString())))
                .andExpect(jsonPath("$.[*].terminalId").value(hasItem(DEFAULT_TERMINAL_ID.toString())))
                .andExpect(jsonPath("$.[*].meterReaderId").value(hasItem(DEFAULT_METER_READER_ID.toString())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].noticeNo").value(hasItem(DEFAULT_NOTICE_NO.toString())))
                .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
                .andExpect(jsonPath("$.[*].longi").value(hasItem(DEFAULT_LONGI.toString())))
                .andExpect(jsonPath("$.[*].nometerAmt").value(hasItem(DEFAULT_NOMETER_AMT.doubleValue())));
    }

    @Test
    @Transactional
    public void getBillFullDetails() throws Exception {
        // Initialize the database
        billFullDetailsRepository.saveAndFlush(billFullDetails);

        // Get the billFullDetails
        restBillFullDetailsMockMvc.perform(get("/api/billFullDetailss/{id}", billFullDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(billFullDetails.getId().intValue()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.divcode").value(DEFAULT_DIVCODE.toString()))
            .andExpect(jsonPath("$.secCode").value(DEFAULT_SEC_CODE.toString()))
            .andExpect(jsonPath("$.secName").value(DEFAULT_SEC_NAME.toString()))
            .andExpect(jsonPath("$.metReaderCode").value(DEFAULT_MET_READER_CODE.toString()))
            .andExpect(jsonPath("$.connDate").value(DEFAULT_CONN_DATE.toString()))
            .andExpect(jsonPath("$.consName").value(DEFAULT_CONS_NAME.toString()))
            .andExpect(jsonPath("$.houseNo").value(DEFAULT_HOUSE_NO.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.pinCode").value(DEFAULT_PIN_CODE.toString()))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.pipeSize").value(DEFAULT_PIPE_SIZE.doubleValue()))
            .andExpect(jsonPath("$.boardMeter").value(DEFAULT_BOARD_METER.toString()))
            .andExpect(jsonPath("$.sewerage").value(DEFAULT_SEWERAGE.toString()))
            .andExpect(jsonPath("$.meterNo").value(DEFAULT_METER_NO.toString()))
            .andExpect(jsonPath("$.prevBillType").value(DEFAULT_PREV_BILL_TYPE.toString()))
            .andExpect(jsonPath("$.prevBillMonth").value(DEFAULT_PREV_BILL_MONTH.toString()))
            .andExpect(jsonPath("$.prevAvgKl").value(DEFAULT_PREV_AVG_KL.doubleValue()))
            .andExpect(jsonPath("$.metReadingDt").value(DEFAULT_MET_READING_DT.toString()))
            .andExpect(jsonPath("$.prevReading").value(DEFAULT_PREV_READING.doubleValue()))
            .andExpect(jsonPath("$.metReadingMo").value(DEFAULT_MET_READING_MO.toString()))
            .andExpect(jsonPath("$.metAvgKl").value(DEFAULT_MET_AVG_KL.doubleValue()))
            .andExpect(jsonPath("$.arrears").value(DEFAULT_ARREARS.doubleValue()))
            .andExpect(jsonPath("$.reversalAmt").value(DEFAULT_REVERSAL_AMT.doubleValue()))
            .andExpect(jsonPath("$.installment").value(DEFAULT_INSTALLMENT.doubleValue()))
            .andExpect(jsonPath("$.otherCharges").value(DEFAULT_OTHER_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.surcharge").value(DEFAULT_SURCHARGE.doubleValue()))
            .andExpect(jsonPath("$.hrsSurcharge").value(DEFAULT_HRS_SURCHARGE.doubleValue()))
            .andExpect(jsonPath("$.resUnits").value(DEFAULT_RES_UNITS.intValue()))
            .andExpect(jsonPath("$.metCostInstallment").value(DEFAULT_MET_COST_INSTALLMENT.doubleValue()))
            .andExpect(jsonPath("$.intOnArrears").value(DEFAULT_INT_ON_ARREARS.doubleValue()))
            .andExpect(jsonPath("$.lastPymtDt").value(DEFAULT_LAST_PYMT_DT.toString()))
            .andExpect(jsonPath("$.lastPymtAmt").value(DEFAULT_LAST_PYMT_AMT.doubleValue()))
            .andExpect(jsonPath("$.billNumber").value(DEFAULT_BILL_NUMBER.toString()))
            .andExpect(jsonPath("$.billDate").value(DEFAULT_BILL_DATE.toString()))
            .andExpect(jsonPath("$.billTime").value(DEFAULT_BILL_TIME.toString()))
            .andExpect(jsonPath("$.meterMake").value(DEFAULT_METER_MAKE.toString()))
            .andExpect(jsonPath("$.currentBillType").value(DEFAULT_CURRENT_BILL_TYPE.toString()))
            .andExpect(jsonPath("$.fromMonth").value(DEFAULT_FROM_MONTH.toString()))
            .andExpect(jsonPath("$.toMonth").value(DEFAULT_TO_MONTH.toString()))
            .andExpect(jsonPath("$.meterFixDate").value(DEFAULT_METER_FIX_DATE.toString()))
            .andExpect(jsonPath("$.initialReading").value(DEFAULT_INITIAL_READING.doubleValue()))
            .andExpect(jsonPath("$.presentReading").value(DEFAULT_PRESENT_READING.doubleValue()))
            .andExpect(jsonPath("$.units").value(DEFAULT_UNITS.doubleValue()))
            .andExpect(jsonPath("$.waterCess").value(DEFAULT_WATER_CESS.doubleValue()))
            .andExpect(jsonPath("$.sewerageCess").value(DEFAULT_SEWERAGE_CESS.doubleValue()))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.meterServiceCharge").value(DEFAULT_METER_SERVICE_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.netPayableAmount").value(DEFAULT_NET_PAYABLE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.telephoneNo").value(DEFAULT_TELEPHONE_NO.toString()))
            .andExpect(jsonPath("$.meterStatus").value(DEFAULT_METER_STATUS.toString()))
            .andExpect(jsonPath("$.billFlag").value(DEFAULT_BILL_FLAG.toString()))
            .andExpect(jsonPath("$.svrStatus").value(DEFAULT_SVR_STATUS.toString()))
            .andExpect(jsonPath("$.terminalId").value(DEFAULT_TERMINAL_ID.toString()))
            .andExpect(jsonPath("$.meterReaderId").value(DEFAULT_METER_READER_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.noticeNo").value(DEFAULT_NOTICE_NO.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.longi").value(DEFAULT_LONGI.toString()))
            .andExpect(jsonPath("$.nometerAmt").value(DEFAULT_NOMETER_AMT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBillFullDetails() throws Exception {
        // Get the billFullDetails
        restBillFullDetailsMockMvc.perform(get("/api/billFullDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillFullDetails() throws Exception {
        // Initialize the database
        billFullDetailsRepository.saveAndFlush(billFullDetails);

		int databaseSizeBeforeUpdate = billFullDetailsRepository.findAll().size();

        // Update the billFullDetails
        billFullDetails.setCan(UPDATED_CAN);
        billFullDetails.setDivcode(UPDATED_DIVCODE);
        billFullDetails.setSecCode(UPDATED_SEC_CODE);
        billFullDetails.setSecName(UPDATED_SEC_NAME);
        billFullDetails.setMetReaderCode(UPDATED_MET_READER_CODE);
        billFullDetails.setConnDate(UPDATED_CONN_DATE);
        billFullDetails.setConsName(UPDATED_CONS_NAME);
        billFullDetails.setHouseNo(UPDATED_HOUSE_NO);
        billFullDetails.setAddress(UPDATED_ADDRESS);
        billFullDetails.setCity(UPDATED_CITY);
        billFullDetails.setPinCode(UPDATED_PIN_CODE);
        billFullDetails.setCategory(UPDATED_CATEGORY);
        billFullDetails.setPipeSize(UPDATED_PIPE_SIZE);
        billFullDetails.setBoardMeter(UPDATED_BOARD_METER);
        billFullDetails.setSewerage(UPDATED_SEWERAGE);
        billFullDetails.setMeterNo(UPDATED_METER_NO);
        billFullDetails.setPrevBillType(UPDATED_PREV_BILL_TYPE);
        billFullDetails.setPrevBillMonth(UPDATED_PREV_BILL_MONTH);
        billFullDetails.setPrevAvgKl(UPDATED_PREV_AVG_KL);
        billFullDetails.setMetReadingDt(UPDATED_MET_READING_DT);
        billFullDetails.setPrevReading(UPDATED_PREV_READING);
        billFullDetails.setMetReadingMo(UPDATED_MET_READING_MO);
        billFullDetails.setMetAvgKl(UPDATED_MET_AVG_KL);
        billFullDetails.setArrears(UPDATED_ARREARS);
        billFullDetails.setReversalAmt(UPDATED_REVERSAL_AMT);
        billFullDetails.setInstallment(UPDATED_INSTALLMENT);
        billFullDetails.setOtherCharges(UPDATED_OTHER_CHARGES);
        billFullDetails.setSurcharge(UPDATED_SURCHARGE);
        billFullDetails.setHrsSurcharge(UPDATED_HRS_SURCHARGE);
        billFullDetails.setResUnits(UPDATED_RES_UNITS);
        billFullDetails.setMetCostInstallment(UPDATED_MET_COST_INSTALLMENT);
        billFullDetails.setIntOnArrears(UPDATED_INT_ON_ARREARS);
        billFullDetails.setLastPymtDt(UPDATED_LAST_PYMT_DT);
        billFullDetails.setLastPymtAmt(UPDATED_LAST_PYMT_AMT);
        billFullDetails.setBillNumber(UPDATED_BILL_NUMBER);
        billFullDetails.setBillDate(UPDATED_BILL_DATE);
        billFullDetails.setBillTime(UPDATED_BILL_TIME);
        billFullDetails.setMeterMake(UPDATED_METER_MAKE);
        billFullDetails.setCurrentBillType(UPDATED_CURRENT_BILL_TYPE);
        billFullDetails.setFromMonth(UPDATED_FROM_MONTH);
        billFullDetails.setToMonth(UPDATED_TO_MONTH);
        billFullDetails.setMeterFixDate(UPDATED_METER_FIX_DATE);
        billFullDetails.setInitialReading(UPDATED_INITIAL_READING);
        billFullDetails.setPresentReading(UPDATED_PRESENT_READING);
        billFullDetails.setUnits(UPDATED_UNITS);
        billFullDetails.setWaterCess(UPDATED_WATER_CESS);
        billFullDetails.setSewerageCess(UPDATED_SEWERAGE_CESS);
        billFullDetails.setServiceCharge(UPDATED_SERVICE_CHARGE);
        billFullDetails.setMeterServiceCharge(UPDATED_METER_SERVICE_CHARGE);
        billFullDetails.setTotalAmount(UPDATED_TOTAL_AMOUNT);
        billFullDetails.setNetPayableAmount(UPDATED_NET_PAYABLE_AMOUNT);
        billFullDetails.setTelephoneNo(UPDATED_TELEPHONE_NO);
        billFullDetails.setMeterStatus(UPDATED_METER_STATUS);
        billFullDetails.setBillFlag(UPDATED_BILL_FLAG);
        billFullDetails.setSvrStatus(UPDATED_SVR_STATUS);
        billFullDetails.setTerminalId(UPDATED_TERMINAL_ID);
        billFullDetails.setMeterReaderId(UPDATED_METER_READER_ID);
        billFullDetails.setUserId(UPDATED_USER_ID);
        billFullDetails.setMobileNo(UPDATED_MOBILE_NO);
        billFullDetails.setNoticeNo(UPDATED_NOTICE_NO);
        billFullDetails.setLat(UPDATED_LAT);
        billFullDetails.setLongi(UPDATED_LONGI);
        billFullDetails.setNometerAmt(UPDATED_NOMETER_AMT);

        restBillFullDetailsMockMvc.perform(put("/api/billFullDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billFullDetails)))
                .andExpect(status().isOk());

        // Validate the BillFullDetails in the database
        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeUpdate);
        BillFullDetails testBillFullDetails = billFullDetailss.get(billFullDetailss.size() - 1);
        assertThat(testBillFullDetails.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBillFullDetails.getDivcode()).isEqualTo(UPDATED_DIVCODE);
        assertThat(testBillFullDetails.getSecCode()).isEqualTo(UPDATED_SEC_CODE);
        assertThat(testBillFullDetails.getSecName()).isEqualTo(UPDATED_SEC_NAME);
        assertThat(testBillFullDetails.getMetReaderCode()).isEqualTo(UPDATED_MET_READER_CODE);
        assertThat(testBillFullDetails.getConnDate()).isEqualTo(UPDATED_CONN_DATE);
        assertThat(testBillFullDetails.getConsName()).isEqualTo(UPDATED_CONS_NAME);
        assertThat(testBillFullDetails.getHouseNo()).isEqualTo(UPDATED_HOUSE_NO);
        assertThat(testBillFullDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testBillFullDetails.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testBillFullDetails.getPinCode()).isEqualTo(UPDATED_PIN_CODE);
        assertThat(testBillFullDetails.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testBillFullDetails.getPipeSize()).isEqualTo(UPDATED_PIPE_SIZE);
        assertThat(testBillFullDetails.getBoardMeter()).isEqualTo(UPDATED_BOARD_METER);
        assertThat(testBillFullDetails.getSewerage()).isEqualTo(UPDATED_SEWERAGE);
        assertThat(testBillFullDetails.getMeterNo()).isEqualTo(UPDATED_METER_NO);
        assertThat(testBillFullDetails.getPrevBillType()).isEqualTo(UPDATED_PREV_BILL_TYPE);
        assertThat(testBillFullDetails.getPrevBillMonth()).isEqualTo(UPDATED_PREV_BILL_MONTH);
        assertThat(testBillFullDetails.getPrevAvgKl()).isEqualTo(UPDATED_PREV_AVG_KL);
        assertThat(testBillFullDetails.getMetReadingDt()).isEqualTo(UPDATED_MET_READING_DT);
        assertThat(testBillFullDetails.getPrevReading()).isEqualTo(UPDATED_PREV_READING);
        assertThat(testBillFullDetails.getMetReadingMo()).isEqualTo(UPDATED_MET_READING_MO);
        assertThat(testBillFullDetails.getMetAvgKl()).isEqualTo(UPDATED_MET_AVG_KL);
        assertThat(testBillFullDetails.getArrears()).isEqualTo(UPDATED_ARREARS);
        assertThat(testBillFullDetails.getReversalAmt()).isEqualTo(UPDATED_REVERSAL_AMT);
        assertThat(testBillFullDetails.getInstallment()).isEqualTo(UPDATED_INSTALLMENT);
        assertThat(testBillFullDetails.getOtherCharges()).isEqualTo(UPDATED_OTHER_CHARGES);
        assertThat(testBillFullDetails.getSurcharge()).isEqualTo(UPDATED_SURCHARGE);
        assertThat(testBillFullDetails.getHrsSurcharge()).isEqualTo(UPDATED_HRS_SURCHARGE);
        assertThat(testBillFullDetails.getResUnits()).isEqualTo(UPDATED_RES_UNITS);
        assertThat(testBillFullDetails.getMetCostInstallment()).isEqualTo(UPDATED_MET_COST_INSTALLMENT);
        assertThat(testBillFullDetails.getIntOnArrears()).isEqualTo(UPDATED_INT_ON_ARREARS);
        assertThat(testBillFullDetails.getLastPymtDt()).isEqualTo(UPDATED_LAST_PYMT_DT);
        assertThat(testBillFullDetails.getLastPymtAmt()).isEqualTo(UPDATED_LAST_PYMT_AMT);
        assertThat(testBillFullDetails.getBillNumber()).isEqualTo(UPDATED_BILL_NUMBER);
        assertThat(testBillFullDetails.getBillDate()).isEqualTo(UPDATED_BILL_DATE);
        assertThat(testBillFullDetails.getBillTime()).isEqualTo(UPDATED_BILL_TIME);
        assertThat(testBillFullDetails.getMeterMake()).isEqualTo(UPDATED_METER_MAKE);
        assertThat(testBillFullDetails.getCurrentBillType()).isEqualTo(UPDATED_CURRENT_BILL_TYPE);
        assertThat(testBillFullDetails.getFromMonth()).isEqualTo(UPDATED_FROM_MONTH);
        assertThat(testBillFullDetails.getToMonth()).isEqualTo(UPDATED_TO_MONTH);
        assertThat(testBillFullDetails.getMeterFixDate()).isEqualTo(UPDATED_METER_FIX_DATE);
        assertThat(testBillFullDetails.getInitialReading()).isEqualTo(UPDATED_INITIAL_READING);
        assertThat(testBillFullDetails.getPresentReading()).isEqualTo(UPDATED_PRESENT_READING);
        assertThat(testBillFullDetails.getUnits()).isEqualTo(UPDATED_UNITS);
        assertThat(testBillFullDetails.getWaterCess()).isEqualTo(UPDATED_WATER_CESS);
        assertThat(testBillFullDetails.getSewerageCess()).isEqualTo(UPDATED_SEWERAGE_CESS);
        assertThat(testBillFullDetails.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testBillFullDetails.getMeterServiceCharge()).isEqualTo(UPDATED_METER_SERVICE_CHARGE);
        assertThat(testBillFullDetails.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testBillFullDetails.getNetPayableAmount()).isEqualTo(UPDATED_NET_PAYABLE_AMOUNT);
        assertThat(testBillFullDetails.getTelephoneNo()).isEqualTo(UPDATED_TELEPHONE_NO);
        assertThat(testBillFullDetails.getMeterStatus()).isEqualTo(UPDATED_METER_STATUS);
        assertThat(testBillFullDetails.getBillFlag()).isEqualTo(UPDATED_BILL_FLAG);
        assertThat(testBillFullDetails.getSvrStatus()).isEqualTo(UPDATED_SVR_STATUS);
        assertThat(testBillFullDetails.getTerminalId()).isEqualTo(UPDATED_TERMINAL_ID);
        assertThat(testBillFullDetails.getMeterReaderId()).isEqualTo(UPDATED_METER_READER_ID);
        assertThat(testBillFullDetails.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBillFullDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testBillFullDetails.getNoticeNo()).isEqualTo(UPDATED_NOTICE_NO);
        assertThat(testBillFullDetails.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testBillFullDetails.getLongi()).isEqualTo(UPDATED_LONGI);
        assertThat(testBillFullDetails.getNometerAmt()).isEqualTo(UPDATED_NOMETER_AMT);
    }

    @Test
    @Transactional
    public void deleteBillFullDetails() throws Exception {
        // Initialize the database
        billFullDetailsRepository.saveAndFlush(billFullDetails);

		int databaseSizeBeforeDelete = billFullDetailsRepository.findAll().size();

        // Get the billFullDetails
        restBillFullDetailsMockMvc.perform(delete("/api/billFullDetailss/{id}", billFullDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BillFullDetails> billFullDetailss = billFullDetailsRepository.findAll();
        assertThat(billFullDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CustDetails;
import com.callippus.water.erp.repository.CustDetailsRepository;

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
 * Test class for the CustDetailsResource REST controller.
 *
 * @see CustDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CustDetailsResourceIntTest {

    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";
    private static final String DEFAULT_DIV_CODE = "AAAAA";
    private static final String UPDATED_DIV_CODE = "BBBBB";
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
    private static final String DEFAULT_PIPE_SIZE = "AAAAA";
    private static final String UPDATED_PIPE_SIZE = "BBBBB";
    private static final String DEFAULT_BOARD_METER = "AAAAA";
    private static final String UPDATED_BOARD_METER = "BBBBB";
    private static final String DEFAULT_SEWERAGE = "AAAAA";
    private static final String UPDATED_SEWERAGE = "BBBBB";
    private static final String DEFAULT_METER_NO = "AAAAA";
    private static final String UPDATED_METER_NO = "BBBBB";
    private static final String DEFAULT_PREV_BILL_TYPE = "AAAAA";
    private static final String UPDATED_PREV_BILL_TYPE = "BBBBB";
    private static final String DEFAULT_PREV_BILL_MONTH = "AAAAA";
    private static final String UPDATED_PREV_BILL_MONTH = "BBBBB";
    private static final String DEFAULT_PREV_AVG_KL = "AAAAA";
    private static final String UPDATED_PREV_AVG_KL = "BBBBB";

    private static final LocalDate DEFAULT_MET_READING_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MET_READING_DT = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_PREV_READING = "AAAAA";
    private static final String UPDATED_PREV_READING = "BBBBB";
    private static final String DEFAULT_MET_READING_MO = "AAAAA";
    private static final String UPDATED_MET_READING_MO = "BBBBB";
    private static final String DEFAULT_MET_AVG_KL = "AAAAA";
    private static final String UPDATED_MET_AVG_KL = "BBBBB";
    private static final String DEFAULT_ARREARS = "AAAAA";
    private static final String UPDATED_ARREARS = "BBBBB";
    private static final String DEFAULT_REVERSAL_AMT = "AAAAA";
    private static final String UPDATED_REVERSAL_AMT = "BBBBB";
    private static final String DEFAULT_INSTALLMENT = "AAAAA";
    private static final String UPDATED_INSTALLMENT = "BBBBB";
    private static final String DEFAULT_OTHER_CHARGES = "AAAAA";
    private static final String UPDATED_OTHER_CHARGES = "BBBBB";
    private static final String DEFAULT_SUR_CHARGE = "AAAAA";
    private static final String UPDATED_SUR_CHARGE = "BBBBB";
    private static final String DEFAULT_HRS_SUR_CHARGE = "AAAAA";
    private static final String UPDATED_HRS_SUR_CHARGE = "BBBBB";
    private static final String DEFAULT_RES_UNITS = "AAAAA";
    private static final String UPDATED_RES_UNITS = "BBBBB";
    private static final String DEFAULT_MET_COST_INSTALLMENT = "AAAAA";
    private static final String UPDATED_MET_COST_INSTALLMENT = "BBBBB";
    private static final String DEFAULT_INT_ON_ARREARS = "AAAAA";
    private static final String UPDATED_INT_ON_ARREARS = "BBBBB";
    private static final String DEFAULT_LAST_PYMT_DT = "AAAAA";
    private static final String UPDATED_LAST_PYMT_DT = "BBBBB";

    private static final Float DEFAULT_LAST_PYMT_AMT = 1F;
    private static final Float UPDATED_LAST_PYMT_AMT = 2F;
    private static final String DEFAULT_MOBILE_NO = "AAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBB";
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
    private static final String DEFAULT_INITIAL_READING = "AAAAA";
    private static final String UPDATED_INITIAL_READING = "BBBBB";
    private static final String DEFAULT_PRESENT_READING = "AAAAA";
    private static final String UPDATED_PRESENT_READING = "BBBBB";
    private static final String DEFAULT_UNITS = "AAAAA";
    private static final String UPDATED_UNITS = "BBBBB";
    private static final String DEFAULT_WATER_CESS = "AAAAA";
    private static final String UPDATED_WATER_CESS = "BBBBB";
    private static final String DEFAULT_SEWERAGE_CESS = "AAAAA";
    private static final String UPDATED_SEWERAGE_CESS = "BBBBB";
    private static final String DEFAULT_SERVICE_CHARGE = "AAAAA";
    private static final String UPDATED_SERVICE_CHARGE = "BBBBB";
    private static final String DEFAULT_METER_SERVICE_CHARGE = "AAAAA";
    private static final String UPDATED_METER_SERVICE_CHARGE = "BBBBB";

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;
    private static final String DEFAULT_NET_PAYABLE_AMOUNT = "AAAAA";
    private static final String UPDATED_NET_PAYABLE_AMOUNT = "BBBBB";
    private static final String DEFAULT_TELEPHONE_NO = "AAAAA";
    private static final String UPDATED_TELEPHONE_NO = "BBBBB";
    private static final String DEFAULT_METER_STATUS = "AAAAA";
    private static final String UPDATED_METER_STATUS = "BBBBB";
    private static final String DEFAULT_MC_MET_READER_CODE = "AAAAA";
    private static final String UPDATED_MC_MET_READER_CODE = "BBBBB";
    private static final String DEFAULT_BILL_FLAG = "AAAAA";
    private static final String UPDATED_BILL_FLAG = "BBBBB";
    private static final String DEFAULT_DOCKET = "AAAAA";
    private static final String UPDATED_DOCKET = "BBBBB";
    private static final String DEFAULT_OC_FLAG = "AAAAA";
    private static final String UPDATED_OC_FLAG = "BBBBB";

    private static final LocalDate DEFAULT_OC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OC_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_LAT = "AAAAA";
    private static final String UPDATED_LAT = "BBBBB";
    private static final String DEFAULT_LONG_I = "AAAAA";
    private static final String UPDATED_LONG_I = "BBBBB";
    private static final String DEFAULT_NO_METER_FLAG = "AAAAA";
    private static final String UPDATED_NO_METER_FLAG = "BBBBB";
    private static final String DEFAULT_NO_METER_ACK_DT = "AAAAA";
    private static final String UPDATED_NO_METER_ACK_DT = "BBBBB";

    private static final Float DEFAULT_NO_METER_AMT = 1F;
    private static final Float UPDATED_NO_METER_AMT = 2F;

    private static final Float DEFAULT_METER_TAMP_AMT = 1F;
    private static final Float UPDATED_METER_TAMP_AMT = 2F;

    @Inject
    private CustDetailsRepository custDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCustDetailsMockMvc;

    private CustDetails custDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustDetailsResource custDetailsResource = new CustDetailsResource();
        ReflectionTestUtils.setField(custDetailsResource, "custDetailsRepository", custDetailsRepository);
        this.restCustDetailsMockMvc = MockMvcBuilders.standaloneSetup(custDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        custDetails = new CustDetails();
        custDetails.setCan(DEFAULT_CAN);
        custDetails.setDivCode(DEFAULT_DIV_CODE);
        custDetails.setSecCode(DEFAULT_SEC_CODE);
        custDetails.setSecName(DEFAULT_SEC_NAME);
        custDetails.setMetReaderCode(DEFAULT_MET_READER_CODE);
        custDetails.setConnDate(DEFAULT_CONN_DATE);
        custDetails.setConsName(DEFAULT_CONS_NAME);
        custDetails.setHouseNo(DEFAULT_HOUSE_NO);
        custDetails.setAddress(DEFAULT_ADDRESS);
        custDetails.setCity(DEFAULT_CITY);
        custDetails.setPinCode(DEFAULT_PIN_CODE);
        custDetails.setCategory(DEFAULT_CATEGORY);
        custDetails.setPipeSize(DEFAULT_PIPE_SIZE);
        custDetails.setBoardMeter(DEFAULT_BOARD_METER);
        custDetails.setSewerage(DEFAULT_SEWERAGE);
        custDetails.setMeterNo(DEFAULT_METER_NO);
        custDetails.setPrevBillType(DEFAULT_PREV_BILL_TYPE);
        custDetails.setPrevBillMonth(DEFAULT_PREV_BILL_MONTH);
        custDetails.setPrevAvgKl(DEFAULT_PREV_AVG_KL);
        custDetails.setMetReadingDt(DEFAULT_MET_READING_DT);
        custDetails.setPrevReading(DEFAULT_PREV_READING);
        custDetails.setMetReadingMo(DEFAULT_MET_READING_MO);
        custDetails.setMetAvgKl(DEFAULT_MET_AVG_KL);
        custDetails.setArrears(DEFAULT_ARREARS);
        custDetails.setReversalAmt(DEFAULT_REVERSAL_AMT);
        custDetails.setInstallment(DEFAULT_INSTALLMENT);
        custDetails.setOtherCharges(DEFAULT_OTHER_CHARGES);
        custDetails.setSurCharge(DEFAULT_SUR_CHARGE);
        custDetails.setHrsSurCharge(DEFAULT_HRS_SUR_CHARGE);
        custDetails.setResUnits(DEFAULT_RES_UNITS);
        custDetails.setMetCostInstallment(DEFAULT_MET_COST_INSTALLMENT);
        custDetails.setIntOnArrears(DEFAULT_INT_ON_ARREARS);
        custDetails.setLastPymtDt(DEFAULT_LAST_PYMT_DT);
        custDetails.setLastPymtAmt(DEFAULT_LAST_PYMT_AMT);
        custDetails.setMobileNo(DEFAULT_MOBILE_NO);
        custDetails.setBillNumber(DEFAULT_BILL_NUMBER);
        custDetails.setBillDate(DEFAULT_BILL_DATE);
        custDetails.setBillTime(DEFAULT_BILL_TIME);
        custDetails.setMeterMake(DEFAULT_METER_MAKE);
        custDetails.setCurrentBillType(DEFAULT_CURRENT_BILL_TYPE);
        custDetails.setFromMonth(DEFAULT_FROM_MONTH);
        custDetails.setToMonth(DEFAULT_TO_MONTH);
        custDetails.setMeterFixDate(DEFAULT_METER_FIX_DATE);
        custDetails.setInitialReading(DEFAULT_INITIAL_READING);
        custDetails.setPresentReading(DEFAULT_PRESENT_READING);
        custDetails.setUnits(DEFAULT_UNITS);
        custDetails.setWaterCess(DEFAULT_WATER_CESS);
        custDetails.setSewerageCess(DEFAULT_SEWERAGE_CESS);
        custDetails.setServiceCharge(DEFAULT_SERVICE_CHARGE);
        custDetails.setMeterServiceCharge(DEFAULT_METER_SERVICE_CHARGE);
        custDetails.setTotalAmount(DEFAULT_TOTAL_AMOUNT);
        custDetails.setNetPayableAmount(DEFAULT_NET_PAYABLE_AMOUNT);
        custDetails.setTelephoneNo(DEFAULT_TELEPHONE_NO);
        custDetails.setMeterStatus(DEFAULT_METER_STATUS);
        custDetails.setMcMetReaderCode(DEFAULT_MC_MET_READER_CODE);
        custDetails.setBillFlag(DEFAULT_BILL_FLAG);
        custDetails.setDocket(DEFAULT_DOCKET);
        custDetails.setOcFlag(DEFAULT_OC_FLAG);
        custDetails.setOcDate(DEFAULT_OC_DATE);
        custDetails.setLat(DEFAULT_LAT);
        custDetails.setLongI(DEFAULT_LONG_I);
        custDetails.setNoMeterFlag(DEFAULT_NO_METER_FLAG);
        custDetails.setNoMeterAckDt(DEFAULT_NO_METER_ACK_DT);
        custDetails.setNoMeterAmt(DEFAULT_NO_METER_AMT);
        custDetails.setMeterTampAmt(DEFAULT_METER_TAMP_AMT);
    }

    @Test
    @Transactional
    public void createCustDetails() throws Exception {
        int databaseSizeBeforeCreate = custDetailsRepository.findAll().size();

        // Create the CustDetails

        restCustDetailsMockMvc.perform(post("/api/custDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custDetails)))
                .andExpect(status().isCreated());

        // Validate the CustDetails in the database
        List<CustDetails> custDetailss = custDetailsRepository.findAll();
        assertThat(custDetailss).hasSize(databaseSizeBeforeCreate + 1);
        CustDetails testCustDetails = custDetailss.get(custDetailss.size() - 1);
        assertThat(testCustDetails.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testCustDetails.getDivCode()).isEqualTo(DEFAULT_DIV_CODE);
        assertThat(testCustDetails.getSecCode()).isEqualTo(DEFAULT_SEC_CODE);
        assertThat(testCustDetails.getSecName()).isEqualTo(DEFAULT_SEC_NAME);
        assertThat(testCustDetails.getMetReaderCode()).isEqualTo(DEFAULT_MET_READER_CODE);
        assertThat(testCustDetails.getConnDate()).isEqualTo(DEFAULT_CONN_DATE);
        assertThat(testCustDetails.getConsName()).isEqualTo(DEFAULT_CONS_NAME);
        assertThat(testCustDetails.getHouseNo()).isEqualTo(DEFAULT_HOUSE_NO);
        assertThat(testCustDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testCustDetails.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testCustDetails.getPinCode()).isEqualTo(DEFAULT_PIN_CODE);
        assertThat(testCustDetails.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testCustDetails.getPipeSize()).isEqualTo(DEFAULT_PIPE_SIZE);
        assertThat(testCustDetails.getBoardMeter()).isEqualTo(DEFAULT_BOARD_METER);
        assertThat(testCustDetails.getSewerage()).isEqualTo(DEFAULT_SEWERAGE);
        assertThat(testCustDetails.getMeterNo()).isEqualTo(DEFAULT_METER_NO);
        assertThat(testCustDetails.getPrevBillType()).isEqualTo(DEFAULT_PREV_BILL_TYPE);
        assertThat(testCustDetails.getPrevBillMonth()).isEqualTo(DEFAULT_PREV_BILL_MONTH);
        assertThat(testCustDetails.getPrevAvgKl()).isEqualTo(DEFAULT_PREV_AVG_KL);
        assertThat(testCustDetails.getMetReadingDt()).isEqualTo(DEFAULT_MET_READING_DT);
        assertThat(testCustDetails.getPrevReading()).isEqualTo(DEFAULT_PREV_READING);
        assertThat(testCustDetails.getMetReadingMo()).isEqualTo(DEFAULT_MET_READING_MO);
        assertThat(testCustDetails.getMetAvgKl()).isEqualTo(DEFAULT_MET_AVG_KL);
        assertThat(testCustDetails.getArrears()).isEqualTo(DEFAULT_ARREARS);
        assertThat(testCustDetails.getReversalAmt()).isEqualTo(DEFAULT_REVERSAL_AMT);
        assertThat(testCustDetails.getInstallment()).isEqualTo(DEFAULT_INSTALLMENT);
        assertThat(testCustDetails.getOtherCharges()).isEqualTo(DEFAULT_OTHER_CHARGES);
        assertThat(testCustDetails.getSurCharge()).isEqualTo(DEFAULT_SUR_CHARGE);
        assertThat(testCustDetails.getHrsSurCharge()).isEqualTo(DEFAULT_HRS_SUR_CHARGE);
        assertThat(testCustDetails.getResUnits()).isEqualTo(DEFAULT_RES_UNITS);
        assertThat(testCustDetails.getMetCostInstallment()).isEqualTo(DEFAULT_MET_COST_INSTALLMENT);
        assertThat(testCustDetails.getIntOnArrears()).isEqualTo(DEFAULT_INT_ON_ARREARS);
        assertThat(testCustDetails.getLastPymtDt()).isEqualTo(DEFAULT_LAST_PYMT_DT);
        assertThat(testCustDetails.getLastPymtAmt()).isEqualTo(DEFAULT_LAST_PYMT_AMT);
        assertThat(testCustDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testCustDetails.getBillNumber()).isEqualTo(DEFAULT_BILL_NUMBER);
        assertThat(testCustDetails.getBillDate()).isEqualTo(DEFAULT_BILL_DATE);
        assertThat(testCustDetails.getBillTime()).isEqualTo(DEFAULT_BILL_TIME);
        assertThat(testCustDetails.getMeterMake()).isEqualTo(DEFAULT_METER_MAKE);
        assertThat(testCustDetails.getCurrentBillType()).isEqualTo(DEFAULT_CURRENT_BILL_TYPE);
        assertThat(testCustDetails.getFromMonth()).isEqualTo(DEFAULT_FROM_MONTH);
        assertThat(testCustDetails.getToMonth()).isEqualTo(DEFAULT_TO_MONTH);
        assertThat(testCustDetails.getMeterFixDate()).isEqualTo(DEFAULT_METER_FIX_DATE);
        assertThat(testCustDetails.getInitialReading()).isEqualTo(DEFAULT_INITIAL_READING);
        assertThat(testCustDetails.getPresentReading()).isEqualTo(DEFAULT_PRESENT_READING);
        assertThat(testCustDetails.getUnits()).isEqualTo(DEFAULT_UNITS);
        assertThat(testCustDetails.getWaterCess()).isEqualTo(DEFAULT_WATER_CESS);
        assertThat(testCustDetails.getSewerageCess()).isEqualTo(DEFAULT_SEWERAGE_CESS);
        assertThat(testCustDetails.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testCustDetails.getMeterServiceCharge()).isEqualTo(DEFAULT_METER_SERVICE_CHARGE);
        assertThat(testCustDetails.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testCustDetails.getNetPayableAmount()).isEqualTo(DEFAULT_NET_PAYABLE_AMOUNT);
        assertThat(testCustDetails.getTelephoneNo()).isEqualTo(DEFAULT_TELEPHONE_NO);
        assertThat(testCustDetails.getMeterStatus()).isEqualTo(DEFAULT_METER_STATUS);
        assertThat(testCustDetails.getMcMetReaderCode()).isEqualTo(DEFAULT_MC_MET_READER_CODE);
        assertThat(testCustDetails.getBillFlag()).isEqualTo(DEFAULT_BILL_FLAG);
        assertThat(testCustDetails.getDocket()).isEqualTo(DEFAULT_DOCKET);
        assertThat(testCustDetails.getOcFlag()).isEqualTo(DEFAULT_OC_FLAG);
        assertThat(testCustDetails.getOcDate()).isEqualTo(DEFAULT_OC_DATE);
        assertThat(testCustDetails.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testCustDetails.getLongI()).isEqualTo(DEFAULT_LONG_I);
        assertThat(testCustDetails.getNoMeterFlag()).isEqualTo(DEFAULT_NO_METER_FLAG);
        assertThat(testCustDetails.getNoMeterAckDt()).isEqualTo(DEFAULT_NO_METER_ACK_DT);
        assertThat(testCustDetails.getNoMeterAmt()).isEqualTo(DEFAULT_NO_METER_AMT);
        assertThat(testCustDetails.getMeterTampAmt()).isEqualTo(DEFAULT_METER_TAMP_AMT);
    }

    @Test
    @Transactional
    public void checkCanIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDetailsRepository.findAll().size();
        // set the field null
        custDetails.setCan(null);

        // Create the CustDetails, which fails.

        restCustDetailsMockMvc.perform(post("/api/custDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custDetails)))
                .andExpect(status().isBadRequest());

        List<CustDetails> custDetailss = custDetailsRepository.findAll();
        assertThat(custDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDetailsRepository.findAll().size();
        // set the field null
        custDetails.setConsName(null);

        // Create the CustDetails, which fails.

        restCustDetailsMockMvc.perform(post("/api/custDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custDetails)))
                .andExpect(status().isBadRequest());

        List<CustDetails> custDetailss = custDetailsRepository.findAll();
        assertThat(custDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustDetailss() throws Exception {
        // Initialize the database
        custDetailsRepository.saveAndFlush(custDetails);

        // Get all the custDetailss
        restCustDetailsMockMvc.perform(get("/api/custDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(custDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].divCode").value(hasItem(DEFAULT_DIV_CODE.toString())))
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
                .andExpect(jsonPath("$.[*].pipeSize").value(hasItem(DEFAULT_PIPE_SIZE.toString())))
                .andExpect(jsonPath("$.[*].boardMeter").value(hasItem(DEFAULT_BOARD_METER.toString())))
                .andExpect(jsonPath("$.[*].sewerage").value(hasItem(DEFAULT_SEWERAGE.toString())))
                .andExpect(jsonPath("$.[*].meterNo").value(hasItem(DEFAULT_METER_NO.toString())))
                .andExpect(jsonPath("$.[*].prevBillType").value(hasItem(DEFAULT_PREV_BILL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].prevBillMonth").value(hasItem(DEFAULT_PREV_BILL_MONTH.toString())))
                .andExpect(jsonPath("$.[*].prevAvgKl").value(hasItem(DEFAULT_PREV_AVG_KL.toString())))
                .andExpect(jsonPath("$.[*].metReadingDt").value(hasItem(DEFAULT_MET_READING_DT.toString())))
                .andExpect(jsonPath("$.[*].prevReading").value(hasItem(DEFAULT_PREV_READING.toString())))
                .andExpect(jsonPath("$.[*].metReadingMo").value(hasItem(DEFAULT_MET_READING_MO.toString())))
                .andExpect(jsonPath("$.[*].metAvgKl").value(hasItem(DEFAULT_MET_AVG_KL.toString())))
                .andExpect(jsonPath("$.[*].arrears").value(hasItem(DEFAULT_ARREARS.toString())))
                .andExpect(jsonPath("$.[*].reversalAmt").value(hasItem(DEFAULT_REVERSAL_AMT.toString())))
                .andExpect(jsonPath("$.[*].installment").value(hasItem(DEFAULT_INSTALLMENT.toString())))
                .andExpect(jsonPath("$.[*].otherCharges").value(hasItem(DEFAULT_OTHER_CHARGES.toString())))
                .andExpect(jsonPath("$.[*].surCharge").value(hasItem(DEFAULT_SUR_CHARGE.toString())))
                .andExpect(jsonPath("$.[*].hrsSurCharge").value(hasItem(DEFAULT_HRS_SUR_CHARGE.toString())))
                .andExpect(jsonPath("$.[*].resUnits").value(hasItem(DEFAULT_RES_UNITS.toString())))
                .andExpect(jsonPath("$.[*].metCostInstallment").value(hasItem(DEFAULT_MET_COST_INSTALLMENT.toString())))
                .andExpect(jsonPath("$.[*].intOnArrears").value(hasItem(DEFAULT_INT_ON_ARREARS.toString())))
                .andExpect(jsonPath("$.[*].lastPymtDt").value(hasItem(DEFAULT_LAST_PYMT_DT.toString())))
                .andExpect(jsonPath("$.[*].lastPymtAmt").value(hasItem(DEFAULT_LAST_PYMT_AMT.doubleValue())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].billNumber").value(hasItem(DEFAULT_BILL_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].billDate").value(hasItem(DEFAULT_BILL_DATE.toString())))
                .andExpect(jsonPath("$.[*].billTime").value(hasItem(DEFAULT_BILL_TIME.toString())))
                .andExpect(jsonPath("$.[*].meterMake").value(hasItem(DEFAULT_METER_MAKE.toString())))
                .andExpect(jsonPath("$.[*].currentBillType").value(hasItem(DEFAULT_CURRENT_BILL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].fromMonth").value(hasItem(DEFAULT_FROM_MONTH.toString())))
                .andExpect(jsonPath("$.[*].toMonth").value(hasItem(DEFAULT_TO_MONTH.toString())))
                .andExpect(jsonPath("$.[*].meterFixDate").value(hasItem(DEFAULT_METER_FIX_DATE.toString())))
                .andExpect(jsonPath("$.[*].initialReading").value(hasItem(DEFAULT_INITIAL_READING.toString())))
                .andExpect(jsonPath("$.[*].presentReading").value(hasItem(DEFAULT_PRESENT_READING.toString())))
                .andExpect(jsonPath("$.[*].units").value(hasItem(DEFAULT_UNITS.toString())))
                .andExpect(jsonPath("$.[*].waterCess").value(hasItem(DEFAULT_WATER_CESS.toString())))
                .andExpect(jsonPath("$.[*].sewerageCess").value(hasItem(DEFAULT_SEWERAGE_CESS.toString())))
                .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE.toString())))
                .andExpect(jsonPath("$.[*].meterServiceCharge").value(hasItem(DEFAULT_METER_SERVICE_CHARGE.toString())))
                .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].netPayableAmount").value(hasItem(DEFAULT_NET_PAYABLE_AMOUNT.toString())))
                .andExpect(jsonPath("$.[*].telephoneNo").value(hasItem(DEFAULT_TELEPHONE_NO.toString())))
                .andExpect(jsonPath("$.[*].meterStatus").value(hasItem(DEFAULT_METER_STATUS.toString())))
                .andExpect(jsonPath("$.[*].mcMetReaderCode").value(hasItem(DEFAULT_MC_MET_READER_CODE.toString())))
                .andExpect(jsonPath("$.[*].billFlag").value(hasItem(DEFAULT_BILL_FLAG.toString())))
                .andExpect(jsonPath("$.[*].docket").value(hasItem(DEFAULT_DOCKET.toString())))
                .andExpect(jsonPath("$.[*].ocFlag").value(hasItem(DEFAULT_OC_FLAG.toString())))
                .andExpect(jsonPath("$.[*].ocDate").value(hasItem(DEFAULT_OC_DATE.toString())))
                .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
                .andExpect(jsonPath("$.[*].longI").value(hasItem(DEFAULT_LONG_I.toString())))
                .andExpect(jsonPath("$.[*].noMeterFlag").value(hasItem(DEFAULT_NO_METER_FLAG.toString())))
                .andExpect(jsonPath("$.[*].noMeterAckDt").value(hasItem(DEFAULT_NO_METER_ACK_DT.toString())))
                .andExpect(jsonPath("$.[*].noMeterAmt").value(hasItem(DEFAULT_NO_METER_AMT.doubleValue())))
                .andExpect(jsonPath("$.[*].meterTampAmt").value(hasItem(DEFAULT_METER_TAMP_AMT.doubleValue())));
    }

    @Test
    @Transactional
    public void getCustDetails() throws Exception {
        // Initialize the database
        custDetailsRepository.saveAndFlush(custDetails);

        // Get the custDetails
        restCustDetailsMockMvc.perform(get("/api/custDetailss/{id}", custDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(custDetails.getId().intValue()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.divCode").value(DEFAULT_DIV_CODE.toString()))
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
            .andExpect(jsonPath("$.pipeSize").value(DEFAULT_PIPE_SIZE.toString()))
            .andExpect(jsonPath("$.boardMeter").value(DEFAULT_BOARD_METER.toString()))
            .andExpect(jsonPath("$.sewerage").value(DEFAULT_SEWERAGE.toString()))
            .andExpect(jsonPath("$.meterNo").value(DEFAULT_METER_NO.toString()))
            .andExpect(jsonPath("$.prevBillType").value(DEFAULT_PREV_BILL_TYPE.toString()))
            .andExpect(jsonPath("$.prevBillMonth").value(DEFAULT_PREV_BILL_MONTH.toString()))
            .andExpect(jsonPath("$.prevAvgKl").value(DEFAULT_PREV_AVG_KL.toString()))
            .andExpect(jsonPath("$.metReadingDt").value(DEFAULT_MET_READING_DT.toString()))
            .andExpect(jsonPath("$.prevReading").value(DEFAULT_PREV_READING.toString()))
            .andExpect(jsonPath("$.metReadingMo").value(DEFAULT_MET_READING_MO.toString()))
            .andExpect(jsonPath("$.metAvgKl").value(DEFAULT_MET_AVG_KL.toString()))
            .andExpect(jsonPath("$.arrears").value(DEFAULT_ARREARS.toString()))
            .andExpect(jsonPath("$.reversalAmt").value(DEFAULT_REVERSAL_AMT.toString()))
            .andExpect(jsonPath("$.installment").value(DEFAULT_INSTALLMENT.toString()))
            .andExpect(jsonPath("$.otherCharges").value(DEFAULT_OTHER_CHARGES.toString()))
            .andExpect(jsonPath("$.surCharge").value(DEFAULT_SUR_CHARGE.toString()))
            .andExpect(jsonPath("$.hrsSurCharge").value(DEFAULT_HRS_SUR_CHARGE.toString()))
            .andExpect(jsonPath("$.resUnits").value(DEFAULT_RES_UNITS.toString()))
            .andExpect(jsonPath("$.metCostInstallment").value(DEFAULT_MET_COST_INSTALLMENT.toString()))
            .andExpect(jsonPath("$.intOnArrears").value(DEFAULT_INT_ON_ARREARS.toString()))
            .andExpect(jsonPath("$.lastPymtDt").value(DEFAULT_LAST_PYMT_DT.toString()))
            .andExpect(jsonPath("$.lastPymtAmt").value(DEFAULT_LAST_PYMT_AMT.doubleValue()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.billNumber").value(DEFAULT_BILL_NUMBER.toString()))
            .andExpect(jsonPath("$.billDate").value(DEFAULT_BILL_DATE.toString()))
            .andExpect(jsonPath("$.billTime").value(DEFAULT_BILL_TIME.toString()))
            .andExpect(jsonPath("$.meterMake").value(DEFAULT_METER_MAKE.toString()))
            .andExpect(jsonPath("$.currentBillType").value(DEFAULT_CURRENT_BILL_TYPE.toString()))
            .andExpect(jsonPath("$.fromMonth").value(DEFAULT_FROM_MONTH.toString()))
            .andExpect(jsonPath("$.toMonth").value(DEFAULT_TO_MONTH.toString()))
            .andExpect(jsonPath("$.meterFixDate").value(DEFAULT_METER_FIX_DATE.toString()))
            .andExpect(jsonPath("$.initialReading").value(DEFAULT_INITIAL_READING.toString()))
            .andExpect(jsonPath("$.presentReading").value(DEFAULT_PRESENT_READING.toString()))
            .andExpect(jsonPath("$.units").value(DEFAULT_UNITS.toString()))
            .andExpect(jsonPath("$.waterCess").value(DEFAULT_WATER_CESS.toString()))
            .andExpect(jsonPath("$.sewerageCess").value(DEFAULT_SEWERAGE_CESS.toString()))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE.toString()))
            .andExpect(jsonPath("$.meterServiceCharge").value(DEFAULT_METER_SERVICE_CHARGE.toString()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.netPayableAmount").value(DEFAULT_NET_PAYABLE_AMOUNT.toString()))
            .andExpect(jsonPath("$.telephoneNo").value(DEFAULT_TELEPHONE_NO.toString()))
            .andExpect(jsonPath("$.meterStatus").value(DEFAULT_METER_STATUS.toString()))
            .andExpect(jsonPath("$.mcMetReaderCode").value(DEFAULT_MC_MET_READER_CODE.toString()))
            .andExpect(jsonPath("$.billFlag").value(DEFAULT_BILL_FLAG.toString()))
            .andExpect(jsonPath("$.docket").value(DEFAULT_DOCKET.toString()))
            .andExpect(jsonPath("$.ocFlag").value(DEFAULT_OC_FLAG.toString()))
            .andExpect(jsonPath("$.ocDate").value(DEFAULT_OC_DATE.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.longI").value(DEFAULT_LONG_I.toString()))
            .andExpect(jsonPath("$.noMeterFlag").value(DEFAULT_NO_METER_FLAG.toString()))
            .andExpect(jsonPath("$.noMeterAckDt").value(DEFAULT_NO_METER_ACK_DT.toString()))
            .andExpect(jsonPath("$.noMeterAmt").value(DEFAULT_NO_METER_AMT.doubleValue()))
            .andExpect(jsonPath("$.meterTampAmt").value(DEFAULT_METER_TAMP_AMT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingCustDetails() throws Exception {
        // Get the custDetails
        restCustDetailsMockMvc.perform(get("/api/custDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustDetails() throws Exception {
        // Initialize the database
        custDetailsRepository.saveAndFlush(custDetails);

		int databaseSizeBeforeUpdate = custDetailsRepository.findAll().size();

        // Update the custDetails
        custDetails.setCan(UPDATED_CAN);
        custDetails.setDivCode(UPDATED_DIV_CODE);
        custDetails.setSecCode(UPDATED_SEC_CODE);
        custDetails.setSecName(UPDATED_SEC_NAME);
        custDetails.setMetReaderCode(UPDATED_MET_READER_CODE);
        custDetails.setConnDate(UPDATED_CONN_DATE);
        custDetails.setConsName(UPDATED_CONS_NAME);
        custDetails.setHouseNo(UPDATED_HOUSE_NO);
        custDetails.setAddress(UPDATED_ADDRESS);
        custDetails.setCity(UPDATED_CITY);
        custDetails.setPinCode(UPDATED_PIN_CODE);
        custDetails.setCategory(UPDATED_CATEGORY);
        custDetails.setPipeSize(UPDATED_PIPE_SIZE);
        custDetails.setBoardMeter(UPDATED_BOARD_METER);
        custDetails.setSewerage(UPDATED_SEWERAGE);
        custDetails.setMeterNo(UPDATED_METER_NO);
        custDetails.setPrevBillType(UPDATED_PREV_BILL_TYPE);
        custDetails.setPrevBillMonth(UPDATED_PREV_BILL_MONTH);
        custDetails.setPrevAvgKl(UPDATED_PREV_AVG_KL);
        custDetails.setMetReadingDt(UPDATED_MET_READING_DT);
        custDetails.setPrevReading(UPDATED_PREV_READING);
        custDetails.setMetReadingMo(UPDATED_MET_READING_MO);
        custDetails.setMetAvgKl(UPDATED_MET_AVG_KL);
        custDetails.setArrears(UPDATED_ARREARS);
        custDetails.setReversalAmt(UPDATED_REVERSAL_AMT);
        custDetails.setInstallment(UPDATED_INSTALLMENT);
        custDetails.setOtherCharges(UPDATED_OTHER_CHARGES);
        custDetails.setSurCharge(UPDATED_SUR_CHARGE);
        custDetails.setHrsSurCharge(UPDATED_HRS_SUR_CHARGE);
        custDetails.setResUnits(UPDATED_RES_UNITS);
        custDetails.setMetCostInstallment(UPDATED_MET_COST_INSTALLMENT);
        custDetails.setIntOnArrears(UPDATED_INT_ON_ARREARS);
        custDetails.setLastPymtDt(UPDATED_LAST_PYMT_DT);
        custDetails.setLastPymtAmt(UPDATED_LAST_PYMT_AMT);
        custDetails.setMobileNo(UPDATED_MOBILE_NO);
        custDetails.setBillNumber(UPDATED_BILL_NUMBER);
        custDetails.setBillDate(UPDATED_BILL_DATE);
        custDetails.setBillTime(UPDATED_BILL_TIME);
        custDetails.setMeterMake(UPDATED_METER_MAKE);
        custDetails.setCurrentBillType(UPDATED_CURRENT_BILL_TYPE);
        custDetails.setFromMonth(UPDATED_FROM_MONTH);
        custDetails.setToMonth(UPDATED_TO_MONTH);
        custDetails.setMeterFixDate(UPDATED_METER_FIX_DATE);
        custDetails.setInitialReading(UPDATED_INITIAL_READING);
        custDetails.setPresentReading(UPDATED_PRESENT_READING);
        custDetails.setUnits(UPDATED_UNITS);
        custDetails.setWaterCess(UPDATED_WATER_CESS);
        custDetails.setSewerageCess(UPDATED_SEWERAGE_CESS);
        custDetails.setServiceCharge(UPDATED_SERVICE_CHARGE);
        custDetails.setMeterServiceCharge(UPDATED_METER_SERVICE_CHARGE);
        custDetails.setTotalAmount(UPDATED_TOTAL_AMOUNT);
        custDetails.setNetPayableAmount(UPDATED_NET_PAYABLE_AMOUNT);
        custDetails.setTelephoneNo(UPDATED_TELEPHONE_NO);
        custDetails.setMeterStatus(UPDATED_METER_STATUS);
        custDetails.setMcMetReaderCode(UPDATED_MC_MET_READER_CODE);
        custDetails.setBillFlag(UPDATED_BILL_FLAG);
        custDetails.setDocket(UPDATED_DOCKET);
        custDetails.setOcFlag(UPDATED_OC_FLAG);
        custDetails.setOcDate(UPDATED_OC_DATE);
        custDetails.setLat(UPDATED_LAT);
        custDetails.setLongI(UPDATED_LONG_I);
        custDetails.setNoMeterFlag(UPDATED_NO_METER_FLAG);
        custDetails.setNoMeterAckDt(UPDATED_NO_METER_ACK_DT);
        custDetails.setNoMeterAmt(UPDATED_NO_METER_AMT);
        custDetails.setMeterTampAmt(UPDATED_METER_TAMP_AMT);

        restCustDetailsMockMvc.perform(put("/api/custDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custDetails)))
                .andExpect(status().isOk());

        // Validate the CustDetails in the database
        List<CustDetails> custDetailss = custDetailsRepository.findAll();
        assertThat(custDetailss).hasSize(databaseSizeBeforeUpdate);
        CustDetails testCustDetails = custDetailss.get(custDetailss.size() - 1);
        assertThat(testCustDetails.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testCustDetails.getDivCode()).isEqualTo(UPDATED_DIV_CODE);
        assertThat(testCustDetails.getSecCode()).isEqualTo(UPDATED_SEC_CODE);
        assertThat(testCustDetails.getSecName()).isEqualTo(UPDATED_SEC_NAME);
        assertThat(testCustDetails.getMetReaderCode()).isEqualTo(UPDATED_MET_READER_CODE);
        assertThat(testCustDetails.getConnDate()).isEqualTo(UPDATED_CONN_DATE);
        assertThat(testCustDetails.getConsName()).isEqualTo(UPDATED_CONS_NAME);
        assertThat(testCustDetails.getHouseNo()).isEqualTo(UPDATED_HOUSE_NO);
        assertThat(testCustDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testCustDetails.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testCustDetails.getPinCode()).isEqualTo(UPDATED_PIN_CODE);
        assertThat(testCustDetails.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testCustDetails.getPipeSize()).isEqualTo(UPDATED_PIPE_SIZE);
        assertThat(testCustDetails.getBoardMeter()).isEqualTo(UPDATED_BOARD_METER);
        assertThat(testCustDetails.getSewerage()).isEqualTo(UPDATED_SEWERAGE);
        assertThat(testCustDetails.getMeterNo()).isEqualTo(UPDATED_METER_NO);
        assertThat(testCustDetails.getPrevBillType()).isEqualTo(UPDATED_PREV_BILL_TYPE);
        assertThat(testCustDetails.getPrevBillMonth()).isEqualTo(UPDATED_PREV_BILL_MONTH);
        assertThat(testCustDetails.getPrevAvgKl()).isEqualTo(UPDATED_PREV_AVG_KL);
        assertThat(testCustDetails.getMetReadingDt()).isEqualTo(UPDATED_MET_READING_DT);
        assertThat(testCustDetails.getPrevReading()).isEqualTo(UPDATED_PREV_READING);
        assertThat(testCustDetails.getMetReadingMo()).isEqualTo(UPDATED_MET_READING_MO);
        assertThat(testCustDetails.getMetAvgKl()).isEqualTo(UPDATED_MET_AVG_KL);
        assertThat(testCustDetails.getArrears()).isEqualTo(UPDATED_ARREARS);
        assertThat(testCustDetails.getReversalAmt()).isEqualTo(UPDATED_REVERSAL_AMT);
        assertThat(testCustDetails.getInstallment()).isEqualTo(UPDATED_INSTALLMENT);
        assertThat(testCustDetails.getOtherCharges()).isEqualTo(UPDATED_OTHER_CHARGES);
        assertThat(testCustDetails.getSurCharge()).isEqualTo(UPDATED_SUR_CHARGE);
        assertThat(testCustDetails.getHrsSurCharge()).isEqualTo(UPDATED_HRS_SUR_CHARGE);
        assertThat(testCustDetails.getResUnits()).isEqualTo(UPDATED_RES_UNITS);
        assertThat(testCustDetails.getMetCostInstallment()).isEqualTo(UPDATED_MET_COST_INSTALLMENT);
        assertThat(testCustDetails.getIntOnArrears()).isEqualTo(UPDATED_INT_ON_ARREARS);
        assertThat(testCustDetails.getLastPymtDt()).isEqualTo(UPDATED_LAST_PYMT_DT);
        assertThat(testCustDetails.getLastPymtAmt()).isEqualTo(UPDATED_LAST_PYMT_AMT);
        assertThat(testCustDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testCustDetails.getBillNumber()).isEqualTo(UPDATED_BILL_NUMBER);
        assertThat(testCustDetails.getBillDate()).isEqualTo(UPDATED_BILL_DATE);
        assertThat(testCustDetails.getBillTime()).isEqualTo(UPDATED_BILL_TIME);
        assertThat(testCustDetails.getMeterMake()).isEqualTo(UPDATED_METER_MAKE);
        assertThat(testCustDetails.getCurrentBillType()).isEqualTo(UPDATED_CURRENT_BILL_TYPE);
        assertThat(testCustDetails.getFromMonth()).isEqualTo(UPDATED_FROM_MONTH);
        assertThat(testCustDetails.getToMonth()).isEqualTo(UPDATED_TO_MONTH);
        assertThat(testCustDetails.getMeterFixDate()).isEqualTo(UPDATED_METER_FIX_DATE);
        assertThat(testCustDetails.getInitialReading()).isEqualTo(UPDATED_INITIAL_READING);
        assertThat(testCustDetails.getPresentReading()).isEqualTo(UPDATED_PRESENT_READING);
        assertThat(testCustDetails.getUnits()).isEqualTo(UPDATED_UNITS);
        assertThat(testCustDetails.getWaterCess()).isEqualTo(UPDATED_WATER_CESS);
        assertThat(testCustDetails.getSewerageCess()).isEqualTo(UPDATED_SEWERAGE_CESS);
        assertThat(testCustDetails.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testCustDetails.getMeterServiceCharge()).isEqualTo(UPDATED_METER_SERVICE_CHARGE);
        assertThat(testCustDetails.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testCustDetails.getNetPayableAmount()).isEqualTo(UPDATED_NET_PAYABLE_AMOUNT);
        assertThat(testCustDetails.getTelephoneNo()).isEqualTo(UPDATED_TELEPHONE_NO);
        assertThat(testCustDetails.getMeterStatus()).isEqualTo(UPDATED_METER_STATUS);
        assertThat(testCustDetails.getMcMetReaderCode()).isEqualTo(UPDATED_MC_MET_READER_CODE);
        assertThat(testCustDetails.getBillFlag()).isEqualTo(UPDATED_BILL_FLAG);
        assertThat(testCustDetails.getDocket()).isEqualTo(UPDATED_DOCKET);
        assertThat(testCustDetails.getOcFlag()).isEqualTo(UPDATED_OC_FLAG);
        assertThat(testCustDetails.getOcDate()).isEqualTo(UPDATED_OC_DATE);
        assertThat(testCustDetails.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testCustDetails.getLongI()).isEqualTo(UPDATED_LONG_I);
        assertThat(testCustDetails.getNoMeterFlag()).isEqualTo(UPDATED_NO_METER_FLAG);
        assertThat(testCustDetails.getNoMeterAckDt()).isEqualTo(UPDATED_NO_METER_ACK_DT);
        assertThat(testCustDetails.getNoMeterAmt()).isEqualTo(UPDATED_NO_METER_AMT);
        assertThat(testCustDetails.getMeterTampAmt()).isEqualTo(UPDATED_METER_TAMP_AMT);
    }

    @Test
    @Transactional
    public void deleteCustDetails() throws Exception {
        // Initialize the database
        custDetailsRepository.saveAndFlush(custDetails);

		int databaseSizeBeforeDelete = custDetailsRepository.findAll().size();

        // Get the custDetails
        restCustDetailsMockMvc.perform(delete("/api/custDetailss/{id}", custDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CustDetails> custDetailss = custDetailsRepository.findAll();
        assertThat(custDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

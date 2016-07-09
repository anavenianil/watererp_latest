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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.callippus.water.erp.domain.enumeration.CustStatus;

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
    private static final String DEFAULT_CATEGORY_UNUSED = "AAAAA";
    private static final String UPDATED_CATEGORY_UNUSED = "BBBBB";

    private static final BigDecimal DEFAULT_PIPE_SIZE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PIPE_SIZE = new BigDecimal(2);
    private static final String DEFAULT_BOARD_METER = "AAAAA";
    private static final String UPDATED_BOARD_METER = "BBBBB";
    private static final String DEFAULT_SEWERAGE = "AAAAA";
    private static final String UPDATED_SEWERAGE = "BBBBB";
    private static final String DEFAULT_PREV_BILL_TYPE = "AAAAA";
    private static final String UPDATED_PREV_BILL_TYPE = "BBBBB";

    private static final LocalDate DEFAULT_PREV_BILL_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PREV_BILL_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PREV_AVG_KL = new BigDecimal(1);
    private static final BigDecimal UPDATED_PREV_AVG_KL = new BigDecimal(2);

    private static final LocalDate DEFAULT_MET_READING_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MET_READING_DT = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PREV_READING = new BigDecimal(1);
    private static final BigDecimal UPDATED_PREV_READING = new BigDecimal(2);

    private static final LocalDate DEFAULT_MET_READING_MO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MET_READING_MO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_MET_AVG_KL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MET_AVG_KL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ARREARS = new BigDecimal(1);
    private static final BigDecimal UPDATED_ARREARS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_REVERSAL_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_REVERSAL_AMT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INSTALLMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INSTALLMENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_OTHER_CHARGES = new BigDecimal(1);
    private static final BigDecimal UPDATED_OTHER_CHARGES = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SURCHARGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SURCHARGE = new BigDecimal(2);
    private static final String DEFAULT_HRS_SURCHARGE = "AAAAA";
    private static final String UPDATED_HRS_SURCHARGE = "BBBBB";

    private static final Long DEFAULT_RES_UNITS = 1L;
    private static final Long UPDATED_RES_UNITS = 2L;

    private static final BigDecimal DEFAULT_MET_COST_INSTALLMENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_MET_COST_INSTALLMENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INT_ON_ARREARS = new BigDecimal(1);
    private static final BigDecimal UPDATED_INT_ON_ARREARS = new BigDecimal(2);

    private static final LocalDate DEFAULT_LAST_PYMT_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_PYMT_DT = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_LAST_PYMT_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_LAST_PYMT_AMT = new BigDecimal(2);
    private static final String DEFAULT_MOBILE_NO = "AAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBB";
    private static final String DEFAULT_CC_FLAG = "AAAAA";
    private static final String UPDATED_CC_FLAG = "BBBBB";
    private static final String DEFAULT_CP_FLAG = "AAAAA";
    private static final String UPDATED_CP_FLAG = "BBBBB";
    private static final String DEFAULT_NOTICE_FLAG = "AAAAA";
    private static final String UPDATED_NOTICE_FLAG = "BBBBB";
    private static final String DEFAULT_DR_FLAG = "AAAAA";
    private static final String UPDATED_DR_FLAG = "BBBBB";
    private static final String DEFAULT_LAT = "AAAAA";
    private static final String UPDATED_LAT = "BBBBB";
    private static final String DEFAULT_LONGI = "AAAAA";
    private static final String UPDATED_LONGI = "BBBBB";

    private static final LocalDate DEFAULT_METER_FIX_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_METER_FIX_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_LOCK_CHARGES = new BigDecimal(1);
    private static final BigDecimal UPDATED_LOCK_CHARGES = new BigDecimal(2);
    private static final String DEFAULT_ID_NUMBER = "AAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBB";
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";


    private static final CustStatus DEFAULT_STATUS = CustStatus.ACTIVE;
    private static final CustStatus UPDATED_STATUS = CustStatus.TERMINATED;
    private static final String DEFAULT_ORGANISATION_NAME = "AAAAA";
    private static final String UPDATED_ORGANISATION_NAME = "BBBBB";
    private static final String DEFAULT_DESIGNATION = "AAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBB";
    private static final String DEFAULT_PHOTO = "AAAAA";
    private static final String UPDATED_PHOTO = "BBBBB";
    private static final String DEFAULT_DEED_DOC = "AAAAA";
    private static final String UPDATED_DEED_DOC = "BBBBB";
    private static final String DEFAULT_AGREEMENT_DOC = "AAAAA";
    private static final String UPDATED_AGREEMENT_DOC = "BBBBB";

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
        custDetails.setCategoryUnused(DEFAULT_CATEGORY_UNUSED);
        custDetails.setPipeSize(DEFAULT_PIPE_SIZE);
        custDetails.setBoardMeter(DEFAULT_BOARD_METER);
        custDetails.setSewerage(DEFAULT_SEWERAGE);
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
        custDetails.setSurcharge(DEFAULT_SURCHARGE);
        custDetails.setHrsSurcharge(DEFAULT_HRS_SURCHARGE);
        custDetails.setResUnits(DEFAULT_RES_UNITS);
        custDetails.setMetCostInstallment(DEFAULT_MET_COST_INSTALLMENT);
        custDetails.setIntOnArrears(DEFAULT_INT_ON_ARREARS);
        custDetails.setLastPymtDt(DEFAULT_LAST_PYMT_DT);
        custDetails.setLastPymtAmt(DEFAULT_LAST_PYMT_AMT);
        custDetails.setMobileNo(DEFAULT_MOBILE_NO);
        custDetails.setCcFlag(DEFAULT_CC_FLAG);
        custDetails.setCpFlag(DEFAULT_CP_FLAG);
        custDetails.setNoticeFlag(DEFAULT_NOTICE_FLAG);
        custDetails.setDrFlag(DEFAULT_DR_FLAG);
        custDetails.setLat(DEFAULT_LAT);
        custDetails.setLongi(DEFAULT_LONGI);
        custDetails.setMeterFixDate(DEFAULT_METER_FIX_DATE);
        custDetails.setLockCharges(DEFAULT_LOCK_CHARGES);
        custDetails.setIdNumber(DEFAULT_ID_NUMBER);
        custDetails.setEmail(DEFAULT_EMAIL);
        custDetails.setStatus(DEFAULT_STATUS);
        custDetails.setOrganisationName(DEFAULT_ORGANISATION_NAME);
        custDetails.setDesignation(DEFAULT_DESIGNATION);
        custDetails.setPhoto(DEFAULT_PHOTO);
        custDetails.setDeedDoc(DEFAULT_DEED_DOC);
        custDetails.setAgreementDoc(DEFAULT_AGREEMENT_DOC);
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
        assertThat(testCustDetails.getCategoryUnused()).isEqualTo(DEFAULT_CATEGORY_UNUSED);
        assertThat(testCustDetails.getPipeSize()).isEqualTo(DEFAULT_PIPE_SIZE);
        assertThat(testCustDetails.getBoardMeter()).isEqualTo(DEFAULT_BOARD_METER);
        assertThat(testCustDetails.getSewerage()).isEqualTo(DEFAULT_SEWERAGE);
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
        assertThat(testCustDetails.getSurcharge()).isEqualTo(DEFAULT_SURCHARGE);
        assertThat(testCustDetails.getHrsSurcharge()).isEqualTo(DEFAULT_HRS_SURCHARGE);
        assertThat(testCustDetails.getResUnits()).isEqualTo(DEFAULT_RES_UNITS);
        assertThat(testCustDetails.getMetCostInstallment()).isEqualTo(DEFAULT_MET_COST_INSTALLMENT);
        assertThat(testCustDetails.getIntOnArrears()).isEqualTo(DEFAULT_INT_ON_ARREARS);
        assertThat(testCustDetails.getLastPymtDt()).isEqualTo(DEFAULT_LAST_PYMT_DT);
        assertThat(testCustDetails.getLastPymtAmt()).isEqualTo(DEFAULT_LAST_PYMT_AMT);
        assertThat(testCustDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testCustDetails.getCcFlag()).isEqualTo(DEFAULT_CC_FLAG);
        assertThat(testCustDetails.getCpFlag()).isEqualTo(DEFAULT_CP_FLAG);
        assertThat(testCustDetails.getNoticeFlag()).isEqualTo(DEFAULT_NOTICE_FLAG);
        assertThat(testCustDetails.getDrFlag()).isEqualTo(DEFAULT_DR_FLAG);
        assertThat(testCustDetails.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testCustDetails.getLongi()).isEqualTo(DEFAULT_LONGI);
        assertThat(testCustDetails.getMeterFixDate()).isEqualTo(DEFAULT_METER_FIX_DATE);
        assertThat(testCustDetails.getLockCharges()).isEqualTo(DEFAULT_LOCK_CHARGES);
        assertThat(testCustDetails.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testCustDetails.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustDetails.getOrganisationName()).isEqualTo(DEFAULT_ORGANISATION_NAME);
        assertThat(testCustDetails.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testCustDetails.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testCustDetails.getDeedDoc()).isEqualTo(DEFAULT_DEED_DOC);
        assertThat(testCustDetails.getAgreementDoc()).isEqualTo(DEFAULT_AGREEMENT_DOC);
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
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = custDetailsRepository.findAll().size();
        // set the field null
        custDetails.setStatus(null);

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
                .andExpect(jsonPath("$.[*].categoryUnused").value(hasItem(DEFAULT_CATEGORY_UNUSED.toString())))
                .andExpect(jsonPath("$.[*].pipeSize").value(hasItem(DEFAULT_PIPE_SIZE.intValue())))
                .andExpect(jsonPath("$.[*].boardMeter").value(hasItem(DEFAULT_BOARD_METER.toString())))
                .andExpect(jsonPath("$.[*].sewerage").value(hasItem(DEFAULT_SEWERAGE.toString())))
                .andExpect(jsonPath("$.[*].prevBillType").value(hasItem(DEFAULT_PREV_BILL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].prevBillMonth").value(hasItem(DEFAULT_PREV_BILL_MONTH.toString())))
                .andExpect(jsonPath("$.[*].prevAvgKl").value(hasItem(DEFAULT_PREV_AVG_KL.intValue())))
                .andExpect(jsonPath("$.[*].metReadingDt").value(hasItem(DEFAULT_MET_READING_DT.toString())))
                .andExpect(jsonPath("$.[*].prevReading").value(hasItem(DEFAULT_PREV_READING.intValue())))
                .andExpect(jsonPath("$.[*].metReadingMo").value(hasItem(DEFAULT_MET_READING_MO.toString())))
                .andExpect(jsonPath("$.[*].metAvgKl").value(hasItem(DEFAULT_MET_AVG_KL.intValue())))
                .andExpect(jsonPath("$.[*].arrears").value(hasItem(DEFAULT_ARREARS.intValue())))
                .andExpect(jsonPath("$.[*].reversalAmt").value(hasItem(DEFAULT_REVERSAL_AMT.intValue())))
                .andExpect(jsonPath("$.[*].installment").value(hasItem(DEFAULT_INSTALLMENT.intValue())))
                .andExpect(jsonPath("$.[*].otherCharges").value(hasItem(DEFAULT_OTHER_CHARGES.intValue())))
                .andExpect(jsonPath("$.[*].surcharge").value(hasItem(DEFAULT_SURCHARGE.intValue())))
                .andExpect(jsonPath("$.[*].hrsSurcharge").value(hasItem(DEFAULT_HRS_SURCHARGE.toString())))
                .andExpect(jsonPath("$.[*].resUnits").value(hasItem(DEFAULT_RES_UNITS.intValue())))
                .andExpect(jsonPath("$.[*].metCostInstallment").value(hasItem(DEFAULT_MET_COST_INSTALLMENT.intValue())))
                .andExpect(jsonPath("$.[*].intOnArrears").value(hasItem(DEFAULT_INT_ON_ARREARS.intValue())))
                .andExpect(jsonPath("$.[*].lastPymtDt").value(hasItem(DEFAULT_LAST_PYMT_DT.toString())))
                .andExpect(jsonPath("$.[*].lastPymtAmt").value(hasItem(DEFAULT_LAST_PYMT_AMT.intValue())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].ccFlag").value(hasItem(DEFAULT_CC_FLAG.toString())))
                .andExpect(jsonPath("$.[*].cpFlag").value(hasItem(DEFAULT_CP_FLAG.toString())))
                .andExpect(jsonPath("$.[*].noticeFlag").value(hasItem(DEFAULT_NOTICE_FLAG.toString())))
                .andExpect(jsonPath("$.[*].drFlag").value(hasItem(DEFAULT_DR_FLAG.toString())))
                .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
                .andExpect(jsonPath("$.[*].longi").value(hasItem(DEFAULT_LONGI.toString())))
                .andExpect(jsonPath("$.[*].meterFixDate").value(hasItem(DEFAULT_METER_FIX_DATE.toString())))
                .andExpect(jsonPath("$.[*].lockCharges").value(hasItem(DEFAULT_LOCK_CHARGES.intValue())))
                .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].organisationName").value(hasItem(DEFAULT_ORGANISATION_NAME.toString())))
                .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
                .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
                .andExpect(jsonPath("$.[*].deedDoc").value(hasItem(DEFAULT_DEED_DOC.toString())))
                .andExpect(jsonPath("$.[*].agreementDoc").value(hasItem(DEFAULT_AGREEMENT_DOC.toString())));
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
            .andExpect(jsonPath("$.categoryUnused").value(DEFAULT_CATEGORY_UNUSED.toString()))
            .andExpect(jsonPath("$.pipeSize").value(DEFAULT_PIPE_SIZE.intValue()))
            .andExpect(jsonPath("$.boardMeter").value(DEFAULT_BOARD_METER.toString()))
            .andExpect(jsonPath("$.sewerage").value(DEFAULT_SEWERAGE.toString()))
            .andExpect(jsonPath("$.prevBillType").value(DEFAULT_PREV_BILL_TYPE.toString()))
            .andExpect(jsonPath("$.prevBillMonth").value(DEFAULT_PREV_BILL_MONTH.toString()))
            .andExpect(jsonPath("$.prevAvgKl").value(DEFAULT_PREV_AVG_KL.intValue()))
            .andExpect(jsonPath("$.metReadingDt").value(DEFAULT_MET_READING_DT.toString()))
            .andExpect(jsonPath("$.prevReading").value(DEFAULT_PREV_READING.intValue()))
            .andExpect(jsonPath("$.metReadingMo").value(DEFAULT_MET_READING_MO.toString()))
            .andExpect(jsonPath("$.metAvgKl").value(DEFAULT_MET_AVG_KL.intValue()))
            .andExpect(jsonPath("$.arrears").value(DEFAULT_ARREARS.intValue()))
            .andExpect(jsonPath("$.reversalAmt").value(DEFAULT_REVERSAL_AMT.intValue()))
            .andExpect(jsonPath("$.installment").value(DEFAULT_INSTALLMENT.intValue()))
            .andExpect(jsonPath("$.otherCharges").value(DEFAULT_OTHER_CHARGES.intValue()))
            .andExpect(jsonPath("$.surcharge").value(DEFAULT_SURCHARGE.intValue()))
            .andExpect(jsonPath("$.hrsSurcharge").value(DEFAULT_HRS_SURCHARGE.toString()))
            .andExpect(jsonPath("$.resUnits").value(DEFAULT_RES_UNITS.intValue()))
            .andExpect(jsonPath("$.metCostInstallment").value(DEFAULT_MET_COST_INSTALLMENT.intValue()))
            .andExpect(jsonPath("$.intOnArrears").value(DEFAULT_INT_ON_ARREARS.intValue()))
            .andExpect(jsonPath("$.lastPymtDt").value(DEFAULT_LAST_PYMT_DT.toString()))
            .andExpect(jsonPath("$.lastPymtAmt").value(DEFAULT_LAST_PYMT_AMT.intValue()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.ccFlag").value(DEFAULT_CC_FLAG.toString()))
            .andExpect(jsonPath("$.cpFlag").value(DEFAULT_CP_FLAG.toString()))
            .andExpect(jsonPath("$.noticeFlag").value(DEFAULT_NOTICE_FLAG.toString()))
            .andExpect(jsonPath("$.drFlag").value(DEFAULT_DR_FLAG.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.longi").value(DEFAULT_LONGI.toString()))
            .andExpect(jsonPath("$.meterFixDate").value(DEFAULT_METER_FIX_DATE.toString()))
            .andExpect(jsonPath("$.lockCharges").value(DEFAULT_LOCK_CHARGES.intValue()))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.organisationName").value(DEFAULT_ORGANISATION_NAME.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.deedDoc").value(DEFAULT_DEED_DOC.toString()))
            .andExpect(jsonPath("$.agreementDoc").value(DEFAULT_AGREEMENT_DOC.toString()));
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
        custDetails.setCategoryUnused(UPDATED_CATEGORY_UNUSED);
        custDetails.setPipeSize(UPDATED_PIPE_SIZE);
        custDetails.setBoardMeter(UPDATED_BOARD_METER);
        custDetails.setSewerage(UPDATED_SEWERAGE);
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
        custDetails.setSurcharge(UPDATED_SURCHARGE);
        custDetails.setHrsSurcharge(UPDATED_HRS_SURCHARGE);
        custDetails.setResUnits(UPDATED_RES_UNITS);
        custDetails.setMetCostInstallment(UPDATED_MET_COST_INSTALLMENT);
        custDetails.setIntOnArrears(UPDATED_INT_ON_ARREARS);
        custDetails.setLastPymtDt(UPDATED_LAST_PYMT_DT);
        custDetails.setLastPymtAmt(UPDATED_LAST_PYMT_AMT);
        custDetails.setMobileNo(UPDATED_MOBILE_NO);
        custDetails.setCcFlag(UPDATED_CC_FLAG);
        custDetails.setCpFlag(UPDATED_CP_FLAG);
        custDetails.setNoticeFlag(UPDATED_NOTICE_FLAG);
        custDetails.setDrFlag(UPDATED_DR_FLAG);
        custDetails.setLat(UPDATED_LAT);
        custDetails.setLongi(UPDATED_LONGI);
        custDetails.setMeterFixDate(UPDATED_METER_FIX_DATE);
        custDetails.setLockCharges(UPDATED_LOCK_CHARGES);
        custDetails.setIdNumber(UPDATED_ID_NUMBER);
        custDetails.setEmail(UPDATED_EMAIL);
        custDetails.setStatus(UPDATED_STATUS);
        custDetails.setOrganisationName(UPDATED_ORGANISATION_NAME);
        custDetails.setDesignation(UPDATED_DESIGNATION);
        custDetails.setPhoto(UPDATED_PHOTO);
        custDetails.setDeedDoc(UPDATED_DEED_DOC);
        custDetails.setAgreementDoc(UPDATED_AGREEMENT_DOC);

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
        assertThat(testCustDetails.getCategoryUnused()).isEqualTo(UPDATED_CATEGORY_UNUSED);
        assertThat(testCustDetails.getPipeSize()).isEqualTo(UPDATED_PIPE_SIZE);
        assertThat(testCustDetails.getBoardMeter()).isEqualTo(UPDATED_BOARD_METER);
        assertThat(testCustDetails.getSewerage()).isEqualTo(UPDATED_SEWERAGE);
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
        assertThat(testCustDetails.getSurcharge()).isEqualTo(UPDATED_SURCHARGE);
        assertThat(testCustDetails.getHrsSurcharge()).isEqualTo(UPDATED_HRS_SURCHARGE);
        assertThat(testCustDetails.getResUnits()).isEqualTo(UPDATED_RES_UNITS);
        assertThat(testCustDetails.getMetCostInstallment()).isEqualTo(UPDATED_MET_COST_INSTALLMENT);
        assertThat(testCustDetails.getIntOnArrears()).isEqualTo(UPDATED_INT_ON_ARREARS);
        assertThat(testCustDetails.getLastPymtDt()).isEqualTo(UPDATED_LAST_PYMT_DT);
        assertThat(testCustDetails.getLastPymtAmt()).isEqualTo(UPDATED_LAST_PYMT_AMT);
        assertThat(testCustDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testCustDetails.getCcFlag()).isEqualTo(UPDATED_CC_FLAG);
        assertThat(testCustDetails.getCpFlag()).isEqualTo(UPDATED_CP_FLAG);
        assertThat(testCustDetails.getNoticeFlag()).isEqualTo(UPDATED_NOTICE_FLAG);
        assertThat(testCustDetails.getDrFlag()).isEqualTo(UPDATED_DR_FLAG);
        assertThat(testCustDetails.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testCustDetails.getLongi()).isEqualTo(UPDATED_LONGI);
        assertThat(testCustDetails.getMeterFixDate()).isEqualTo(UPDATED_METER_FIX_DATE);
        assertThat(testCustDetails.getLockCharges()).isEqualTo(UPDATED_LOCK_CHARGES);
        assertThat(testCustDetails.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testCustDetails.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustDetails.getOrganisationName()).isEqualTo(UPDATED_ORGANISATION_NAME);
        assertThat(testCustDetails.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testCustDetails.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testCustDetails.getDeedDoc()).isEqualTo(UPDATED_DEED_DOC);
        assertThat(testCustDetails.getAgreementDoc()).isEqualTo(UPDATED_AGREEMENT_DOC);
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

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BillDetails;
import com.callippus.water.erp.repository.BillDetailsRepository;

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
 * Test class for the BillDetailsResource REST controller.
 *
 * @see BillDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BillDetailsResourceIntTest {

    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";
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

    private static final LocalDate DEFAULT_METER_FIX_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_METER_FIX_DATE = LocalDate.now(ZoneId.systemDefault());

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
    private static final String DEFAULT_MET_READER_CODE = "AAAAA";
    private static final String UPDATED_MET_READER_CODE = "BBBBB";
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

    private static final Float DEFAULT_NO_METER_AMT = 1F;
    private static final Float UPDATED_NO_METER_AMT = 2F;

    private static final LocalDate DEFAULT_MET_READING_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MET_READING_DT = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private BillDetailsRepository billDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBillDetailsMockMvc;

    private BillDetails billDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BillDetailsResource billDetailsResource = new BillDetailsResource();
        ReflectionTestUtils.setField(billDetailsResource, "billDetailsRepository", billDetailsRepository);
        this.restBillDetailsMockMvc = MockMvcBuilders.standaloneSetup(billDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        billDetails = new BillDetails();
        billDetails.setCan(DEFAULT_CAN);
        billDetails.setBillNumber(DEFAULT_BILL_NUMBER);
        billDetails.setBillDate(DEFAULT_BILL_DATE);
        billDetails.setBillTime(DEFAULT_BILL_TIME);
        billDetails.setMeterMake(DEFAULT_METER_MAKE);
        billDetails.setCurrentBillType(DEFAULT_CURRENT_BILL_TYPE);
        billDetails.setFromMonth(DEFAULT_FROM_MONTH);
        billDetails.setToMonth(DEFAULT_TO_MONTH);
        billDetails.setMeterFixDate(DEFAULT_METER_FIX_DATE);
        billDetails.setInitialReading(DEFAULT_INITIAL_READING);
        billDetails.setPresentReading(DEFAULT_PRESENT_READING);
        billDetails.setUnits(DEFAULT_UNITS);
        billDetails.setWaterCess(DEFAULT_WATER_CESS);
        billDetails.setSewerageCess(DEFAULT_SEWERAGE_CESS);
        billDetails.setServiceCharge(DEFAULT_SERVICE_CHARGE);
        billDetails.setMeterServiceCharge(DEFAULT_METER_SERVICE_CHARGE);
        billDetails.setTotalAmount(DEFAULT_TOTAL_AMOUNT);
        billDetails.setNetPayableAmount(DEFAULT_NET_PAYABLE_AMOUNT);
        billDetails.setTelephoneNo(DEFAULT_TELEPHONE_NO);
        billDetails.setMeterStatus(DEFAULT_METER_STATUS);
        billDetails.setMetReaderCode(DEFAULT_MET_READER_CODE);
        billDetails.setBillFlag(DEFAULT_BILL_FLAG);
        billDetails.setSvrStatus(DEFAULT_SVR_STATUS);
        billDetails.setTerminalId(DEFAULT_TERMINAL_ID);
        billDetails.setMeterReaderId(DEFAULT_METER_READER_ID);
        billDetails.setUserId(DEFAULT_USER_ID);
        billDetails.setMobileNo(DEFAULT_MOBILE_NO);
        billDetails.setNoticeNo(DEFAULT_NOTICE_NO);
        billDetails.setLat(DEFAULT_LAT);
        billDetails.setLongi(DEFAULT_LONGI);
        billDetails.setNoMeterAmt(DEFAULT_NO_METER_AMT);
        billDetails.setMetReadingDt(DEFAULT_MET_READING_DT);
    }

    @Test
    @Transactional
    public void createBillDetails() throws Exception {
        int databaseSizeBeforeCreate = billDetailsRepository.findAll().size();

        // Create the BillDetails

        restBillDetailsMockMvc.perform(post("/api/billDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billDetails)))
                .andExpect(status().isCreated());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailss = billDetailsRepository.findAll();
        assertThat(billDetailss).hasSize(databaseSizeBeforeCreate + 1);
        BillDetails testBillDetails = billDetailss.get(billDetailss.size() - 1);
        assertThat(testBillDetails.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testBillDetails.getBillNumber()).isEqualTo(DEFAULT_BILL_NUMBER);
        assertThat(testBillDetails.getBillDate()).isEqualTo(DEFAULT_BILL_DATE);
        assertThat(testBillDetails.getBillTime()).isEqualTo(DEFAULT_BILL_TIME);
        assertThat(testBillDetails.getMeterMake()).isEqualTo(DEFAULT_METER_MAKE);
        assertThat(testBillDetails.getCurrentBillType()).isEqualTo(DEFAULT_CURRENT_BILL_TYPE);
        assertThat(testBillDetails.getFromMonth()).isEqualTo(DEFAULT_FROM_MONTH);
        assertThat(testBillDetails.getToMonth()).isEqualTo(DEFAULT_TO_MONTH);
        assertThat(testBillDetails.getMeterFixDate()).isEqualTo(DEFAULT_METER_FIX_DATE);
        assertThat(testBillDetails.getInitialReading()).isEqualTo(DEFAULT_INITIAL_READING);
        assertThat(testBillDetails.getPresentReading()).isEqualTo(DEFAULT_PRESENT_READING);
        assertThat(testBillDetails.getUnits()).isEqualTo(DEFAULT_UNITS);
        assertThat(testBillDetails.getWaterCess()).isEqualTo(DEFAULT_WATER_CESS);
        assertThat(testBillDetails.getSewerageCess()).isEqualTo(DEFAULT_SEWERAGE_CESS);
        assertThat(testBillDetails.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testBillDetails.getMeterServiceCharge()).isEqualTo(DEFAULT_METER_SERVICE_CHARGE);
        assertThat(testBillDetails.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testBillDetails.getNetPayableAmount()).isEqualTo(DEFAULT_NET_PAYABLE_AMOUNT);
        assertThat(testBillDetails.getTelephoneNo()).isEqualTo(DEFAULT_TELEPHONE_NO);
        assertThat(testBillDetails.getMeterStatus()).isEqualTo(DEFAULT_METER_STATUS);
        assertThat(testBillDetails.getMetReaderCode()).isEqualTo(DEFAULT_MET_READER_CODE);
        assertThat(testBillDetails.getBillFlag()).isEqualTo(DEFAULT_BILL_FLAG);
        assertThat(testBillDetails.getSvrStatus()).isEqualTo(DEFAULT_SVR_STATUS);
        assertThat(testBillDetails.getTerminalId()).isEqualTo(DEFAULT_TERMINAL_ID);
        assertThat(testBillDetails.getMeterReaderId()).isEqualTo(DEFAULT_METER_READER_ID);
        assertThat(testBillDetails.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBillDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testBillDetails.getNoticeNo()).isEqualTo(DEFAULT_NOTICE_NO);
        assertThat(testBillDetails.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testBillDetails.getLongi()).isEqualTo(DEFAULT_LONGI);
        assertThat(testBillDetails.getNoMeterAmt()).isEqualTo(DEFAULT_NO_METER_AMT);
        assertThat(testBillDetails.getMetReadingDt()).isEqualTo(DEFAULT_MET_READING_DT);
    }

    @Test
    @Transactional
    public void checkBillDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = billDetailsRepository.findAll().size();
        // set the field null
        billDetails.setBillDate(null);

        // Create the BillDetails, which fails.

        restBillDetailsMockMvc.perform(post("/api/billDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billDetails)))
                .andExpect(status().isBadRequest());

        List<BillDetails> billDetailss = billDetailsRepository.findAll();
        assertThat(billDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBillDetailss() throws Exception {
        // Initialize the database
        billDetailsRepository.saveAndFlush(billDetails);

        // Get all the billDetailss
        restBillDetailsMockMvc.perform(get("/api/billDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(billDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
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
                .andExpect(jsonPath("$.[*].metReaderCode").value(hasItem(DEFAULT_MET_READER_CODE.toString())))
                .andExpect(jsonPath("$.[*].billFlag").value(hasItem(DEFAULT_BILL_FLAG.toString())))
                .andExpect(jsonPath("$.[*].svrStatus").value(hasItem(DEFAULT_SVR_STATUS.toString())))
                .andExpect(jsonPath("$.[*].terminalId").value(hasItem(DEFAULT_TERMINAL_ID.toString())))
                .andExpect(jsonPath("$.[*].meterReaderId").value(hasItem(DEFAULT_METER_READER_ID.toString())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].noticeNo").value(hasItem(DEFAULT_NOTICE_NO.toString())))
                .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
                .andExpect(jsonPath("$.[*].longi").value(hasItem(DEFAULT_LONGI.toString())))
                .andExpect(jsonPath("$.[*].noMeterAmt").value(hasItem(DEFAULT_NO_METER_AMT.doubleValue())))
                .andExpect(jsonPath("$.[*].metReadingDt").value(hasItem(DEFAULT_MET_READING_DT.toString())));
    }

    @Test
    @Transactional
    public void getBillDetails() throws Exception {
        // Initialize the database
        billDetailsRepository.saveAndFlush(billDetails);

        // Get the billDetails
        restBillDetailsMockMvc.perform(get("/api/billDetailss/{id}", billDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(billDetails.getId().intValue()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
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
            .andExpect(jsonPath("$.metReaderCode").value(DEFAULT_MET_READER_CODE.toString()))
            .andExpect(jsonPath("$.billFlag").value(DEFAULT_BILL_FLAG.toString()))
            .andExpect(jsonPath("$.svrStatus").value(DEFAULT_SVR_STATUS.toString()))
            .andExpect(jsonPath("$.terminalId").value(DEFAULT_TERMINAL_ID.toString()))
            .andExpect(jsonPath("$.meterReaderId").value(DEFAULT_METER_READER_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.noticeNo").value(DEFAULT_NOTICE_NO.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.longi").value(DEFAULT_LONGI.toString()))
            .andExpect(jsonPath("$.noMeterAmt").value(DEFAULT_NO_METER_AMT.doubleValue()))
            .andExpect(jsonPath("$.metReadingDt").value(DEFAULT_MET_READING_DT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBillDetails() throws Exception {
        // Get the billDetails
        restBillDetailsMockMvc.perform(get("/api/billDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillDetails() throws Exception {
        // Initialize the database
        billDetailsRepository.saveAndFlush(billDetails);

		int databaseSizeBeforeUpdate = billDetailsRepository.findAll().size();

        // Update the billDetails
        billDetails.setCan(UPDATED_CAN);
        billDetails.setBillNumber(UPDATED_BILL_NUMBER);
        billDetails.setBillDate(UPDATED_BILL_DATE);
        billDetails.setBillTime(UPDATED_BILL_TIME);
        billDetails.setMeterMake(UPDATED_METER_MAKE);
        billDetails.setCurrentBillType(UPDATED_CURRENT_BILL_TYPE);
        billDetails.setFromMonth(UPDATED_FROM_MONTH);
        billDetails.setToMonth(UPDATED_TO_MONTH);
        billDetails.setMeterFixDate(UPDATED_METER_FIX_DATE);
        billDetails.setInitialReading(UPDATED_INITIAL_READING);
        billDetails.setPresentReading(UPDATED_PRESENT_READING);
        billDetails.setUnits(UPDATED_UNITS);
        billDetails.setWaterCess(UPDATED_WATER_CESS);
        billDetails.setSewerageCess(UPDATED_SEWERAGE_CESS);
        billDetails.setServiceCharge(UPDATED_SERVICE_CHARGE);
        billDetails.setMeterServiceCharge(UPDATED_METER_SERVICE_CHARGE);
        billDetails.setTotalAmount(UPDATED_TOTAL_AMOUNT);
        billDetails.setNetPayableAmount(UPDATED_NET_PAYABLE_AMOUNT);
        billDetails.setTelephoneNo(UPDATED_TELEPHONE_NO);
        billDetails.setMeterStatus(UPDATED_METER_STATUS);
        billDetails.setMetReaderCode(UPDATED_MET_READER_CODE);
        billDetails.setBillFlag(UPDATED_BILL_FLAG);
        billDetails.setSvrStatus(UPDATED_SVR_STATUS);
        billDetails.setTerminalId(UPDATED_TERMINAL_ID);
        billDetails.setMeterReaderId(UPDATED_METER_READER_ID);
        billDetails.setUserId(UPDATED_USER_ID);
        billDetails.setMobileNo(UPDATED_MOBILE_NO);
        billDetails.setNoticeNo(UPDATED_NOTICE_NO);
        billDetails.setLat(UPDATED_LAT);
        billDetails.setLongi(UPDATED_LONGI);
        billDetails.setNoMeterAmt(UPDATED_NO_METER_AMT);
        billDetails.setMetReadingDt(UPDATED_MET_READING_DT);

        restBillDetailsMockMvc.perform(put("/api/billDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billDetails)))
                .andExpect(status().isOk());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailss = billDetailsRepository.findAll();
        assertThat(billDetailss).hasSize(databaseSizeBeforeUpdate);
        BillDetails testBillDetails = billDetailss.get(billDetailss.size() - 1);
        assertThat(testBillDetails.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBillDetails.getBillNumber()).isEqualTo(UPDATED_BILL_NUMBER);
        assertThat(testBillDetails.getBillDate()).isEqualTo(UPDATED_BILL_DATE);
        assertThat(testBillDetails.getBillTime()).isEqualTo(UPDATED_BILL_TIME);
        assertThat(testBillDetails.getMeterMake()).isEqualTo(UPDATED_METER_MAKE);
        assertThat(testBillDetails.getCurrentBillType()).isEqualTo(UPDATED_CURRENT_BILL_TYPE);
        assertThat(testBillDetails.getFromMonth()).isEqualTo(UPDATED_FROM_MONTH);
        assertThat(testBillDetails.getToMonth()).isEqualTo(UPDATED_TO_MONTH);
        assertThat(testBillDetails.getMeterFixDate()).isEqualTo(UPDATED_METER_FIX_DATE);
        assertThat(testBillDetails.getInitialReading()).isEqualTo(UPDATED_INITIAL_READING);
        assertThat(testBillDetails.getPresentReading()).isEqualTo(UPDATED_PRESENT_READING);
        assertThat(testBillDetails.getUnits()).isEqualTo(UPDATED_UNITS);
        assertThat(testBillDetails.getWaterCess()).isEqualTo(UPDATED_WATER_CESS);
        assertThat(testBillDetails.getSewerageCess()).isEqualTo(UPDATED_SEWERAGE_CESS);
        assertThat(testBillDetails.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testBillDetails.getMeterServiceCharge()).isEqualTo(UPDATED_METER_SERVICE_CHARGE);
        assertThat(testBillDetails.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testBillDetails.getNetPayableAmount()).isEqualTo(UPDATED_NET_PAYABLE_AMOUNT);
        assertThat(testBillDetails.getTelephoneNo()).isEqualTo(UPDATED_TELEPHONE_NO);
        assertThat(testBillDetails.getMeterStatus()).isEqualTo(UPDATED_METER_STATUS);
        assertThat(testBillDetails.getMetReaderCode()).isEqualTo(UPDATED_MET_READER_CODE);
        assertThat(testBillDetails.getBillFlag()).isEqualTo(UPDATED_BILL_FLAG);
        assertThat(testBillDetails.getSvrStatus()).isEqualTo(UPDATED_SVR_STATUS);
        assertThat(testBillDetails.getTerminalId()).isEqualTo(UPDATED_TERMINAL_ID);
        assertThat(testBillDetails.getMeterReaderId()).isEqualTo(UPDATED_METER_READER_ID);
        assertThat(testBillDetails.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBillDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testBillDetails.getNoticeNo()).isEqualTo(UPDATED_NOTICE_NO);
        assertThat(testBillDetails.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testBillDetails.getLongi()).isEqualTo(UPDATED_LONGI);
        assertThat(testBillDetails.getNoMeterAmt()).isEqualTo(UPDATED_NO_METER_AMT);
        assertThat(testBillDetails.getMetReadingDt()).isEqualTo(UPDATED_MET_READING_DT);
    }

    @Test
    @Transactional
    public void deleteBillDetails() throws Exception {
        // Initialize the database
        billDetailsRepository.saveAndFlush(billDetails);

		int databaseSizeBeforeDelete = billDetailsRepository.findAll().size();

        // Get the billDetails
        restBillDetailsMockMvc.perform(delete("/api/billDetailss/{id}", billDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BillDetails> billDetailss = billDetailsRepository.findAll();
        assertThat(billDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

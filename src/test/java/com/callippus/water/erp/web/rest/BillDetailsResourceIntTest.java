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

    private static final Float DEFAULT_NET_PAYABLE_AMOUNT = 1F;
    private static final Float UPDATED_NET_PAYABLE_AMOUNT = 2F;
    private static final String DEFAULT_TELEPHONE_NO = "AAAAA";
    private static final String UPDATED_TELEPHONE_NO = "BBBBB";
    private static final String DEFAULT_METER_STATUS = "AAAAA";
    private static final String UPDATED_METER_STATUS = "BBBBB";
    private static final String DEFAULT_MC_MET_READER_CODE = "AAAAA";
    private static final String UPDATED_MC_MET_READER_CODE = "BBBBB";
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
    private static final String DEFAULT_LONG_I = "AAAAA";
    private static final String UPDATED_LONG_I = "BBBBB";
    private static final String DEFAULT_NOMETER_AMT = "AAAAA";
    private static final String UPDATED_NOMETER_AMT = "BBBBB";

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
        billDetails.setBill_number(DEFAULT_BILL_NUMBER);
        billDetails.setBill_date(DEFAULT_BILL_DATE);
        billDetails.setBill_time(DEFAULT_BILL_TIME);
        billDetails.setMeter_make(DEFAULT_METER_MAKE);
        billDetails.setCurrent_bill_type(DEFAULT_CURRENT_BILL_TYPE);
        billDetails.setFrom_month(DEFAULT_FROM_MONTH);
        billDetails.setTo_month(DEFAULT_TO_MONTH);
        billDetails.setMeter_fix_date(DEFAULT_METER_FIX_DATE);
        billDetails.setInitial_reading(DEFAULT_INITIAL_READING);
        billDetails.setPresent_reading(DEFAULT_PRESENT_READING);
        billDetails.setUnits(DEFAULT_UNITS);
        billDetails.setWater_cess(DEFAULT_WATER_CESS);
        billDetails.setSewerage_cess(DEFAULT_SEWERAGE_CESS);
        billDetails.setService_charge(DEFAULT_SERVICE_CHARGE);
        billDetails.setMeter_service_charge(DEFAULT_METER_SERVICE_CHARGE);
        billDetails.setTotal_amount(DEFAULT_TOTAL_AMOUNT);
        billDetails.setNet_payable_amount(DEFAULT_NET_PAYABLE_AMOUNT);
        billDetails.setTelephone_no(DEFAULT_TELEPHONE_NO);
        billDetails.setMeter_status(DEFAULT_METER_STATUS);
        billDetails.setMc_met_reader_code(DEFAULT_MC_MET_READER_CODE);
        billDetails.setBill_flag(DEFAULT_BILL_FLAG);
        billDetails.setSvr_status(DEFAULT_SVR_STATUS);
        billDetails.setTerminal_id(DEFAULT_TERMINAL_ID);
        billDetails.setMeter_reader_id(DEFAULT_METER_READER_ID);
        billDetails.setUser_id(DEFAULT_USER_ID);
        billDetails.setMobile_no(DEFAULT_MOBILE_NO);
        billDetails.setNotice_no(DEFAULT_NOTICE_NO);
        billDetails.setLat(DEFAULT_LAT);
        billDetails.setLongI(DEFAULT_LONG_I);
        billDetails.setNometer_amt(DEFAULT_NOMETER_AMT);
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
        assertThat(testBillDetails.getBill_number()).isEqualTo(DEFAULT_BILL_NUMBER);
        assertThat(testBillDetails.getBill_date()).isEqualTo(DEFAULT_BILL_DATE);
        assertThat(testBillDetails.getBill_time()).isEqualTo(DEFAULT_BILL_TIME);
        assertThat(testBillDetails.getMeter_make()).isEqualTo(DEFAULT_METER_MAKE);
        assertThat(testBillDetails.getCurrent_bill_type()).isEqualTo(DEFAULT_CURRENT_BILL_TYPE);
        assertThat(testBillDetails.getFrom_month()).isEqualTo(DEFAULT_FROM_MONTH);
        assertThat(testBillDetails.getTo_month()).isEqualTo(DEFAULT_TO_MONTH);
        assertThat(testBillDetails.getMeter_fix_date()).isEqualTo(DEFAULT_METER_FIX_DATE);
        assertThat(testBillDetails.getInitial_reading()).isEqualTo(DEFAULT_INITIAL_READING);
        assertThat(testBillDetails.getPresent_reading()).isEqualTo(DEFAULT_PRESENT_READING);
        assertThat(testBillDetails.getUnits()).isEqualTo(DEFAULT_UNITS);
        assertThat(testBillDetails.getWater_cess()).isEqualTo(DEFAULT_WATER_CESS);
        assertThat(testBillDetails.getSewerage_cess()).isEqualTo(DEFAULT_SEWERAGE_CESS);
        assertThat(testBillDetails.getService_charge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testBillDetails.getMeter_service_charge()).isEqualTo(DEFAULT_METER_SERVICE_CHARGE);
        assertThat(testBillDetails.getTotal_amount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testBillDetails.getNet_payable_amount()).isEqualTo(DEFAULT_NET_PAYABLE_AMOUNT);
        assertThat(testBillDetails.getTelephone_no()).isEqualTo(DEFAULT_TELEPHONE_NO);
        assertThat(testBillDetails.getMeter_status()).isEqualTo(DEFAULT_METER_STATUS);
        assertThat(testBillDetails.getMc_met_reader_code()).isEqualTo(DEFAULT_MC_MET_READER_CODE);
        assertThat(testBillDetails.getBill_flag()).isEqualTo(DEFAULT_BILL_FLAG);
        assertThat(testBillDetails.getSvr_status()).isEqualTo(DEFAULT_SVR_STATUS);
        assertThat(testBillDetails.getTerminal_id()).isEqualTo(DEFAULT_TERMINAL_ID);
        assertThat(testBillDetails.getMeter_reader_id()).isEqualTo(DEFAULT_METER_READER_ID);
        assertThat(testBillDetails.getUser_id()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testBillDetails.getMobile_no()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testBillDetails.getNotice_no()).isEqualTo(DEFAULT_NOTICE_NO);
        assertThat(testBillDetails.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testBillDetails.getLongI()).isEqualTo(DEFAULT_LONG_I);
        assertThat(testBillDetails.getNometer_amt()).isEqualTo(DEFAULT_NOMETER_AMT);
    }

    @Test
    @Transactional
    public void checkBill_dateIsRequired() throws Exception {
        int databaseSizeBeforeTest = billDetailsRepository.findAll().size();
        // set the field null
        billDetails.setBill_date(null);

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
                .andExpect(jsonPath("$.[*].bill_number").value(hasItem(DEFAULT_BILL_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].bill_date").value(hasItem(DEFAULT_BILL_DATE.toString())))
                .andExpect(jsonPath("$.[*].bill_time").value(hasItem(DEFAULT_BILL_TIME.toString())))
                .andExpect(jsonPath("$.[*].meter_make").value(hasItem(DEFAULT_METER_MAKE.toString())))
                .andExpect(jsonPath("$.[*].current_bill_type").value(hasItem(DEFAULT_CURRENT_BILL_TYPE.toString())))
                .andExpect(jsonPath("$.[*].from_month").value(hasItem(DEFAULT_FROM_MONTH.toString())))
                .andExpect(jsonPath("$.[*].to_month").value(hasItem(DEFAULT_TO_MONTH.toString())))
                .andExpect(jsonPath("$.[*].meter_fix_date").value(hasItem(DEFAULT_METER_FIX_DATE.toString())))
                .andExpect(jsonPath("$.[*].initial_reading").value(hasItem(DEFAULT_INITIAL_READING.toString())))
                .andExpect(jsonPath("$.[*].present_reading").value(hasItem(DEFAULT_PRESENT_READING.toString())))
                .andExpect(jsonPath("$.[*].units").value(hasItem(DEFAULT_UNITS.toString())))
                .andExpect(jsonPath("$.[*].water_cess").value(hasItem(DEFAULT_WATER_CESS.toString())))
                .andExpect(jsonPath("$.[*].sewerage_cess").value(hasItem(DEFAULT_SEWERAGE_CESS.toString())))
                .andExpect(jsonPath("$.[*].service_charge").value(hasItem(DEFAULT_SERVICE_CHARGE.toString())))
                .andExpect(jsonPath("$.[*].meter_service_charge").value(hasItem(DEFAULT_METER_SERVICE_CHARGE.toString())))
                .andExpect(jsonPath("$.[*].total_amount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].net_payable_amount").value(hasItem(DEFAULT_NET_PAYABLE_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].telephone_no").value(hasItem(DEFAULT_TELEPHONE_NO.toString())))
                .andExpect(jsonPath("$.[*].meter_status").value(hasItem(DEFAULT_METER_STATUS.toString())))
                .andExpect(jsonPath("$.[*].mc_met_reader_code").value(hasItem(DEFAULT_MC_MET_READER_CODE.toString())))
                .andExpect(jsonPath("$.[*].bill_flag").value(hasItem(DEFAULT_BILL_FLAG.toString())))
                .andExpect(jsonPath("$.[*].svr_status").value(hasItem(DEFAULT_SVR_STATUS.toString())))
                .andExpect(jsonPath("$.[*].terminal_id").value(hasItem(DEFAULT_TERMINAL_ID.toString())))
                .andExpect(jsonPath("$.[*].meter_reader_id").value(hasItem(DEFAULT_METER_READER_ID.toString())))
                .andExpect(jsonPath("$.[*].user_id").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].mobile_no").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].notice_no").value(hasItem(DEFAULT_NOTICE_NO.toString())))
                .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
                .andExpect(jsonPath("$.[*].longI").value(hasItem(DEFAULT_LONG_I.toString())))
                .andExpect(jsonPath("$.[*].nometer_amt").value(hasItem(DEFAULT_NOMETER_AMT.toString())));
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
            .andExpect(jsonPath("$.bill_number").value(DEFAULT_BILL_NUMBER.toString()))
            .andExpect(jsonPath("$.bill_date").value(DEFAULT_BILL_DATE.toString()))
            .andExpect(jsonPath("$.bill_time").value(DEFAULT_BILL_TIME.toString()))
            .andExpect(jsonPath("$.meter_make").value(DEFAULT_METER_MAKE.toString()))
            .andExpect(jsonPath("$.current_bill_type").value(DEFAULT_CURRENT_BILL_TYPE.toString()))
            .andExpect(jsonPath("$.from_month").value(DEFAULT_FROM_MONTH.toString()))
            .andExpect(jsonPath("$.to_month").value(DEFAULT_TO_MONTH.toString()))
            .andExpect(jsonPath("$.meter_fix_date").value(DEFAULT_METER_FIX_DATE.toString()))
            .andExpect(jsonPath("$.initial_reading").value(DEFAULT_INITIAL_READING.toString()))
            .andExpect(jsonPath("$.present_reading").value(DEFAULT_PRESENT_READING.toString()))
            .andExpect(jsonPath("$.units").value(DEFAULT_UNITS.toString()))
            .andExpect(jsonPath("$.water_cess").value(DEFAULT_WATER_CESS.toString()))
            .andExpect(jsonPath("$.sewerage_cess").value(DEFAULT_SEWERAGE_CESS.toString()))
            .andExpect(jsonPath("$.service_charge").value(DEFAULT_SERVICE_CHARGE.toString()))
            .andExpect(jsonPath("$.meter_service_charge").value(DEFAULT_METER_SERVICE_CHARGE.toString()))
            .andExpect(jsonPath("$.total_amount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.net_payable_amount").value(DEFAULT_NET_PAYABLE_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.telephone_no").value(DEFAULT_TELEPHONE_NO.toString()))
            .andExpect(jsonPath("$.meter_status").value(DEFAULT_METER_STATUS.toString()))
            .andExpect(jsonPath("$.mc_met_reader_code").value(DEFAULT_MC_MET_READER_CODE.toString()))
            .andExpect(jsonPath("$.bill_flag").value(DEFAULT_BILL_FLAG.toString()))
            .andExpect(jsonPath("$.svr_status").value(DEFAULT_SVR_STATUS.toString()))
            .andExpect(jsonPath("$.terminal_id").value(DEFAULT_TERMINAL_ID.toString()))
            .andExpect(jsonPath("$.meter_reader_id").value(DEFAULT_METER_READER_ID.toString()))
            .andExpect(jsonPath("$.user_id").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.mobile_no").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.notice_no").value(DEFAULT_NOTICE_NO.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.longI").value(DEFAULT_LONG_I.toString()))
            .andExpect(jsonPath("$.nometer_amt").value(DEFAULT_NOMETER_AMT.toString()));
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
        billDetails.setBill_number(UPDATED_BILL_NUMBER);
        billDetails.setBill_date(UPDATED_BILL_DATE);
        billDetails.setBill_time(UPDATED_BILL_TIME);
        billDetails.setMeter_make(UPDATED_METER_MAKE);
        billDetails.setCurrent_bill_type(UPDATED_CURRENT_BILL_TYPE);
        billDetails.setFrom_month(UPDATED_FROM_MONTH);
        billDetails.setTo_month(UPDATED_TO_MONTH);
        billDetails.setMeter_fix_date(UPDATED_METER_FIX_DATE);
        billDetails.setInitial_reading(UPDATED_INITIAL_READING);
        billDetails.setPresent_reading(UPDATED_PRESENT_READING);
        billDetails.setUnits(UPDATED_UNITS);
        billDetails.setWater_cess(UPDATED_WATER_CESS);
        billDetails.setSewerage_cess(UPDATED_SEWERAGE_CESS);
        billDetails.setService_charge(UPDATED_SERVICE_CHARGE);
        billDetails.setMeter_service_charge(UPDATED_METER_SERVICE_CHARGE);
        billDetails.setTotal_amount(UPDATED_TOTAL_AMOUNT);
        billDetails.setNet_payable_amount(UPDATED_NET_PAYABLE_AMOUNT);
        billDetails.setTelephone_no(UPDATED_TELEPHONE_NO);
        billDetails.setMeter_status(UPDATED_METER_STATUS);
        billDetails.setMc_met_reader_code(UPDATED_MC_MET_READER_CODE);
        billDetails.setBill_flag(UPDATED_BILL_FLAG);
        billDetails.setSvr_status(UPDATED_SVR_STATUS);
        billDetails.setTerminal_id(UPDATED_TERMINAL_ID);
        billDetails.setMeter_reader_id(UPDATED_METER_READER_ID);
        billDetails.setUser_id(UPDATED_USER_ID);
        billDetails.setMobile_no(UPDATED_MOBILE_NO);
        billDetails.setNotice_no(UPDATED_NOTICE_NO);
        billDetails.setLat(UPDATED_LAT);
        billDetails.setLongI(UPDATED_LONG_I);
        billDetails.setNometer_amt(UPDATED_NOMETER_AMT);

        restBillDetailsMockMvc.perform(put("/api/billDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billDetails)))
                .andExpect(status().isOk());

        // Validate the BillDetails in the database
        List<BillDetails> billDetailss = billDetailsRepository.findAll();
        assertThat(billDetailss).hasSize(databaseSizeBeforeUpdate);
        BillDetails testBillDetails = billDetailss.get(billDetailss.size() - 1);
        assertThat(testBillDetails.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBillDetails.getBill_number()).isEqualTo(UPDATED_BILL_NUMBER);
        assertThat(testBillDetails.getBill_date()).isEqualTo(UPDATED_BILL_DATE);
        assertThat(testBillDetails.getBill_time()).isEqualTo(UPDATED_BILL_TIME);
        assertThat(testBillDetails.getMeter_make()).isEqualTo(UPDATED_METER_MAKE);
        assertThat(testBillDetails.getCurrent_bill_type()).isEqualTo(UPDATED_CURRENT_BILL_TYPE);
        assertThat(testBillDetails.getFrom_month()).isEqualTo(UPDATED_FROM_MONTH);
        assertThat(testBillDetails.getTo_month()).isEqualTo(UPDATED_TO_MONTH);
        assertThat(testBillDetails.getMeter_fix_date()).isEqualTo(UPDATED_METER_FIX_DATE);
        assertThat(testBillDetails.getInitial_reading()).isEqualTo(UPDATED_INITIAL_READING);
        assertThat(testBillDetails.getPresent_reading()).isEqualTo(UPDATED_PRESENT_READING);
        assertThat(testBillDetails.getUnits()).isEqualTo(UPDATED_UNITS);
        assertThat(testBillDetails.getWater_cess()).isEqualTo(UPDATED_WATER_CESS);
        assertThat(testBillDetails.getSewerage_cess()).isEqualTo(UPDATED_SEWERAGE_CESS);
        assertThat(testBillDetails.getService_charge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testBillDetails.getMeter_service_charge()).isEqualTo(UPDATED_METER_SERVICE_CHARGE);
        assertThat(testBillDetails.getTotal_amount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testBillDetails.getNet_payable_amount()).isEqualTo(UPDATED_NET_PAYABLE_AMOUNT);
        assertThat(testBillDetails.getTelephone_no()).isEqualTo(UPDATED_TELEPHONE_NO);
        assertThat(testBillDetails.getMeter_status()).isEqualTo(UPDATED_METER_STATUS);
        assertThat(testBillDetails.getMc_met_reader_code()).isEqualTo(UPDATED_MC_MET_READER_CODE);
        assertThat(testBillDetails.getBill_flag()).isEqualTo(UPDATED_BILL_FLAG);
        assertThat(testBillDetails.getSvr_status()).isEqualTo(UPDATED_SVR_STATUS);
        assertThat(testBillDetails.getTerminal_id()).isEqualTo(UPDATED_TERMINAL_ID);
        assertThat(testBillDetails.getMeter_reader_id()).isEqualTo(UPDATED_METER_READER_ID);
        assertThat(testBillDetails.getUser_id()).isEqualTo(UPDATED_USER_ID);
        assertThat(testBillDetails.getMobile_no()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testBillDetails.getNotice_no()).isEqualTo(UPDATED_NOTICE_NO);
        assertThat(testBillDetails.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testBillDetails.getLongI()).isEqualTo(UPDATED_LONG_I);
        assertThat(testBillDetails.getNometer_amt()).isEqualTo(UPDATED_NOMETER_AMT);
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

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CollDetails;
import com.callippus.water.erp.domain.CollectionTypeMaster;
import com.callippus.water.erp.domain.PaymentTypes;
import com.callippus.water.erp.repository.CollDetailsRepository;
import com.callippus.water.erp.repository.CollectionTypeMasterRepository;
import com.callippus.water.erp.repository.CustDetailsRepository;
import com.callippus.water.erp.repository.PaymentTypesRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CollDetailsResource REST controller.
 *
 * @see CollDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CollDetailsResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_REVERSAL_REF = "AAAAA";
    private static final String UPDATED_REVERSAL_REF = "BBBBB";
    private static final String DEFAULT_RECEIPT_NO = "AAAAA";
    private static final String UPDATED_RECEIPT_NO = "BBBBB";

    private static final Float DEFAULT_RECEIPT_AMT = 1F;
    private static final Float UPDATED_RECEIPT_AMT = 2F;

    private static final ZonedDateTime DEFAULT_RECEIPT_DT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_RECEIPT_DT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_RECEIPT_DT_STR = dateTimeFormatter.format(DEFAULT_RECEIPT_DT);
    private static final String DEFAULT_RECEIPT_MODE = "AAAAA";
    private static final String UPDATED_RECEIPT_MODE = "BBBBB";
    private static final String DEFAULT_INSTR_NO = "AAAAA";
    private static final String UPDATED_INSTR_NO = "BBBBB";

    private static final LocalDate DEFAULT_INSTR_DT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSTR_DT = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_INSTR_ISSUER = "AAAAA";
    private static final String UPDATED_INSTR_ISSUER = "BBBBB";
    private static final String DEFAULT_SVR_STATUS = "AAAAA";
    private static final String UPDATED_SVR_STATUS = "BBBBB";
    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";
    private static final String DEFAULT_CONS_NAME = "AAAAA";
    private static final String UPDATED_CONS_NAME = "BBBBB";
    private static final String DEFAULT_TERMINAL_ID = "AAAAA";
    private static final String UPDATED_TERMINAL_ID = "BBBBB";

    private static final ZonedDateTime DEFAULT_COLL_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_COLL_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_COLL_TIME_STR = dateTimeFormatter.format(DEFAULT_COLL_TIME);
    private static final String DEFAULT_TXN_STATUS = "AAAAA";
    private static final String UPDATED_TXN_STATUS = "BBBBB";
    private static final String DEFAULT_METER_READER_ID = "AAAAA";
    private static final String UPDATED_METER_READER_ID = "BBBBB";
    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";
    private static final String DEFAULT_SETTLEMENT_ID = "AAAAA";
    private static final String UPDATED_SETTLEMENT_ID = "BBBBB";
    private static final String DEFAULT_EXT_SETTLEMENT_ID = "AAAAA";
    private static final String UPDATED_EXT_SETTLEMENT_ID = "BBBBB";
    private static final String DEFAULT_LAT = "AAAAA";
    private static final String UPDATED_LAT = "BBBBB";
    private static final String DEFAULT_LONG_I = "AAAAA";
    private static final String UPDATED_LONG_I = "BBBBB";

    @Inject
    private CollDetailsRepository collDetailsRepository;

    @Inject
    private CollectionTypeMasterRepository collectionTypeMasterRepository;
    
    @Inject
    private CustDetailsRepository custDetailsRepository;
    
    @Inject
    private PaymentTypesRepository paymentTypesRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCollDetailsMockMvc;

    private CollDetails collDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CollDetailsResource collDetailsResource = new CollDetailsResource();
        ReflectionTestUtils.setField(collDetailsResource, "collDetailsRepository", collDetailsRepository);
        ReflectionTestUtils.setField(collDetailsResource, "custDetailsRepository", custDetailsRepository);
        this.restCollDetailsMockMvc = MockMvcBuilders.standaloneSetup(collDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        collDetails = new CollDetails();
        collDetails.setReversalRef(DEFAULT_REVERSAL_REF);
        collDetails.setReceiptNo(DEFAULT_RECEIPT_NO);
        collDetails.setReceiptAmt(DEFAULT_RECEIPT_AMT);
        collDetails.setReceiptDt(DEFAULT_RECEIPT_DT);
        collDetails.setReceiptMode(DEFAULT_RECEIPT_MODE);
        collDetails.setInstrNo(DEFAULT_INSTR_NO);
        collDetails.setInstrDt(DEFAULT_INSTR_DT);
        collDetails.setInstrIssuer(DEFAULT_INSTR_ISSUER);
        collDetails.setSvrStatus(DEFAULT_SVR_STATUS);
        collDetails.setCan(DEFAULT_CAN);
        collDetails.setConsName(DEFAULT_CONS_NAME);
        collDetails.setTerminalId(DEFAULT_TERMINAL_ID);
        collDetails.setCollTime(DEFAULT_COLL_TIME);
        collDetails.setTxnStatus(DEFAULT_TXN_STATUS);
        collDetails.setMeterReaderId(DEFAULT_METER_READER_ID);
        collDetails.setUserId(DEFAULT_USER_ID);
        collDetails.setRemarks(DEFAULT_REMARKS);
        collDetails.setSettlementId(DEFAULT_SETTLEMENT_ID);
        collDetails.setExtSettlementId(DEFAULT_EXT_SETTLEMENT_ID);
        collDetails.setLat(DEFAULT_LAT);
        collDetails.setLongI(DEFAULT_LONG_I);
    }

    
    public void createPayment(MockMvc restCollDetailsMockMvc, String can, Float amount, ZonedDateTime date) throws Exception {
    	collDetails = new CollDetails();
    	collDetails.setCan(can);
    	collDetails.setReceiptAmt(amount);
    	collDetails.setReceiptDt(date);
    	collDetails.setCollTime(ZonedDateTime.now());
    	CollectionTypeMaster ct = collectionTypeMasterRepository.findOne(1L);
    	PaymentTypes pt = paymentTypesRepository.findOne(1L);
    	collDetails.setCollectionTypeMaster(ct);
    	collDetails.setPaymentTypes(pt);

        restCollDetailsMockMvc.perform(post("/api/collDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(collDetails)))
                .andExpect(status().isCreated());
    }
    
    
    @Test
    @Transactional
    public void createCollDetails() throws Exception {
        int databaseSizeBeforeCreate = collDetailsRepository.findAll().size();
        // Create the CollDetails

        restCollDetailsMockMvc.perform(post("/api/collDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(collDetails)))
                .andExpect(status().isCreated());

        // Validate the CollDetails in the database
        List<CollDetails> collDetailss = collDetailsRepository.findAll();
        assertThat(collDetailss).hasSize(databaseSizeBeforeCreate + 1);
        CollDetails testCollDetails = collDetailss.get(collDetailss.size() - 1);
        assertThat(testCollDetails.getReversalRef()).isEqualTo(DEFAULT_REVERSAL_REF);
        assertThat(testCollDetails.getReceiptNo()).isEqualTo(DEFAULT_RECEIPT_NO);
        assertThat(testCollDetails.getReceiptAmt()).isEqualTo(DEFAULT_RECEIPT_AMT);
        assertThat(testCollDetails.getReceiptDt()).isEqualTo(DEFAULT_RECEIPT_DT);
        assertThat(testCollDetails.getReceiptMode()).isEqualTo(DEFAULT_RECEIPT_MODE);
        assertThat(testCollDetails.getInstrNo()).isEqualTo(DEFAULT_INSTR_NO);
        assertThat(testCollDetails.getInstrDt()).isEqualTo(DEFAULT_INSTR_DT);
        assertThat(testCollDetails.getInstrIssuer()).isEqualTo(DEFAULT_INSTR_ISSUER);
        assertThat(testCollDetails.getSvrStatus()).isEqualTo(DEFAULT_SVR_STATUS);
        assertThat(testCollDetails.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testCollDetails.getConsName()).isEqualTo(DEFAULT_CONS_NAME);
        assertThat(testCollDetails.getTerminalId()).isEqualTo(DEFAULT_TERMINAL_ID);
        assertThat(testCollDetails.getCollTime()).isEqualTo(DEFAULT_COLL_TIME);
        assertThat(testCollDetails.getTxnStatus()).isEqualTo(DEFAULT_TXN_STATUS);
        assertThat(testCollDetails.getMeterReaderId()).isEqualTo(DEFAULT_METER_READER_ID);
        assertThat(testCollDetails.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCollDetails.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testCollDetails.getSettlementId()).isEqualTo(DEFAULT_SETTLEMENT_ID);
        assertThat(testCollDetails.getExtSettlementId()).isEqualTo(DEFAULT_EXT_SETTLEMENT_ID);
        assertThat(testCollDetails.getLat()).isEqualTo(DEFAULT_LAT);
        assertThat(testCollDetails.getLongI()).isEqualTo(DEFAULT_LONG_I);
    }

    @Test
    @Transactional
    public void getAllCollDetailss() throws Exception {
        // Initialize the database
        collDetailsRepository.saveAndFlush(collDetails);

        // Get all the collDetailss
        restCollDetailsMockMvc.perform(get("/api/collDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(collDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].reversalRef").value(hasItem(DEFAULT_REVERSAL_REF.toString())))
                .andExpect(jsonPath("$.[*].receiptNo").value(hasItem(DEFAULT_RECEIPT_NO.toString())))
                .andExpect(jsonPath("$.[*].receiptAmt").value(hasItem(DEFAULT_RECEIPT_AMT.doubleValue())))
                .andExpect(jsonPath("$.[*].receiptDt").value(hasItem(DEFAULT_RECEIPT_DT_STR)))
                .andExpect(jsonPath("$.[*].receiptMode").value(hasItem(DEFAULT_RECEIPT_MODE.toString())))
                .andExpect(jsonPath("$.[*].instrNo").value(hasItem(DEFAULT_INSTR_NO.toString())))
                .andExpect(jsonPath("$.[*].instrDt").value(hasItem(DEFAULT_INSTR_DT.toString())))
                .andExpect(jsonPath("$.[*].instrIssuer").value(hasItem(DEFAULT_INSTR_ISSUER.toString())))
                .andExpect(jsonPath("$.[*].svrStatus").value(hasItem(DEFAULT_SVR_STATUS.toString())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].consName").value(hasItem(DEFAULT_CONS_NAME.toString())))
                .andExpect(jsonPath("$.[*].terminalId").value(hasItem(DEFAULT_TERMINAL_ID.toString())))
                .andExpect(jsonPath("$.[*].collTime").value(hasItem(DEFAULT_COLL_TIME_STR)))
                .andExpect(jsonPath("$.[*].txnStatus").value(hasItem(DEFAULT_TXN_STATUS.toString())))
                .andExpect(jsonPath("$.[*].meterReaderId").value(hasItem(DEFAULT_METER_READER_ID.toString())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].settlementId").value(hasItem(DEFAULT_SETTLEMENT_ID.toString())))
                .andExpect(jsonPath("$.[*].extSettlementId").value(hasItem(DEFAULT_EXT_SETTLEMENT_ID.toString())))
                .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT.toString())))
                .andExpect(jsonPath("$.[*].longI").value(hasItem(DEFAULT_LONG_I.toString())));
    }

    @Test
    @Transactional
    public void getCollDetails() throws Exception {
        // Initialize the database
        collDetailsRepository.saveAndFlush(collDetails);

        // Get the collDetails
        restCollDetailsMockMvc.perform(get("/api/collDetailss/{id}", collDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(collDetails.getId().intValue()))
            .andExpect(jsonPath("$.reversalRef").value(DEFAULT_REVERSAL_REF.toString()))
            .andExpect(jsonPath("$.receiptNo").value(DEFAULT_RECEIPT_NO.toString()))
            .andExpect(jsonPath("$.receiptAmt").value(DEFAULT_RECEIPT_AMT.doubleValue()))
            .andExpect(jsonPath("$.receiptDt").value(DEFAULT_RECEIPT_DT_STR))
            .andExpect(jsonPath("$.receiptMode").value(DEFAULT_RECEIPT_MODE.toString()))
            .andExpect(jsonPath("$.instrNo").value(DEFAULT_INSTR_NO.toString()))
            .andExpect(jsonPath("$.instrDt").value(DEFAULT_INSTR_DT.toString()))
            .andExpect(jsonPath("$.instrIssuer").value(DEFAULT_INSTR_ISSUER.toString()))
            .andExpect(jsonPath("$.svrStatus").value(DEFAULT_SVR_STATUS.toString()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.consName").value(DEFAULT_CONS_NAME.toString()))
            .andExpect(jsonPath("$.terminalId").value(DEFAULT_TERMINAL_ID.toString()))
            .andExpect(jsonPath("$.collTime").value(DEFAULT_COLL_TIME_STR))
            .andExpect(jsonPath("$.txnStatus").value(DEFAULT_TXN_STATUS.toString()))
            .andExpect(jsonPath("$.meterReaderId").value(DEFAULT_METER_READER_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.settlementId").value(DEFAULT_SETTLEMENT_ID.toString()))
            .andExpect(jsonPath("$.extSettlementId").value(DEFAULT_EXT_SETTLEMENT_ID.toString()))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT.toString()))
            .andExpect(jsonPath("$.longI").value(DEFAULT_LONG_I.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCollDetails() throws Exception {
        // Get the collDetails
        restCollDetailsMockMvc.perform(get("/api/collDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollDetails() throws Exception {
        // Initialize the database
        collDetailsRepository.saveAndFlush(collDetails);

		int databaseSizeBeforeUpdate = collDetailsRepository.findAll().size();

        // Update the collDetails
        collDetails.setReversalRef(UPDATED_REVERSAL_REF);
        collDetails.setReceiptNo(UPDATED_RECEIPT_NO);
        collDetails.setReceiptAmt(UPDATED_RECEIPT_AMT);
        collDetails.setReceiptDt(UPDATED_RECEIPT_DT);
        collDetails.setReceiptMode(UPDATED_RECEIPT_MODE);
        collDetails.setInstrNo(UPDATED_INSTR_NO);
        collDetails.setInstrDt(UPDATED_INSTR_DT);
        collDetails.setInstrIssuer(UPDATED_INSTR_ISSUER);
        collDetails.setSvrStatus(UPDATED_SVR_STATUS);
        collDetails.setCan(UPDATED_CAN);
        collDetails.setConsName(UPDATED_CONS_NAME);
        collDetails.setTerminalId(UPDATED_TERMINAL_ID);
        collDetails.setCollTime(UPDATED_COLL_TIME);
        collDetails.setTxnStatus(UPDATED_TXN_STATUS);
        collDetails.setMeterReaderId(UPDATED_METER_READER_ID);
        collDetails.setUserId(UPDATED_USER_ID);
        collDetails.setRemarks(UPDATED_REMARKS);
        collDetails.setSettlementId(UPDATED_SETTLEMENT_ID);
        collDetails.setExtSettlementId(UPDATED_EXT_SETTLEMENT_ID);
        collDetails.setLat(UPDATED_LAT);
        collDetails.setLongI(UPDATED_LONG_I);

        restCollDetailsMockMvc.perform(put("/api/collDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(collDetails)))
                .andExpect(status().isOk());

        // Validate the CollDetails in the database
        List<CollDetails> collDetailss = collDetailsRepository.findAll();
        assertThat(collDetailss).hasSize(databaseSizeBeforeUpdate);
        CollDetails testCollDetails = collDetailss.get(collDetailss.size() - 1);
        assertThat(testCollDetails.getReversalRef()).isEqualTo(UPDATED_REVERSAL_REF);
        assertThat(testCollDetails.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testCollDetails.getReceiptAmt()).isEqualTo(UPDATED_RECEIPT_AMT);
        assertThat(testCollDetails.getReceiptDt()).isEqualTo(UPDATED_RECEIPT_DT);
        assertThat(testCollDetails.getReceiptMode()).isEqualTo(UPDATED_RECEIPT_MODE);
        assertThat(testCollDetails.getInstrNo()).isEqualTo(UPDATED_INSTR_NO);
        assertThat(testCollDetails.getInstrDt()).isEqualTo(UPDATED_INSTR_DT);
        assertThat(testCollDetails.getInstrIssuer()).isEqualTo(UPDATED_INSTR_ISSUER);
        assertThat(testCollDetails.getSvrStatus()).isEqualTo(UPDATED_SVR_STATUS);
        assertThat(testCollDetails.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testCollDetails.getConsName()).isEqualTo(UPDATED_CONS_NAME);
        assertThat(testCollDetails.getTerminalId()).isEqualTo(UPDATED_TERMINAL_ID);
        assertThat(testCollDetails.getCollTime()).isEqualTo(UPDATED_COLL_TIME);
        assertThat(testCollDetails.getTxnStatus()).isEqualTo(UPDATED_TXN_STATUS);
        assertThat(testCollDetails.getMeterReaderId()).isEqualTo(UPDATED_METER_READER_ID);
        assertThat(testCollDetails.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCollDetails.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testCollDetails.getSettlementId()).isEqualTo(UPDATED_SETTLEMENT_ID);
        assertThat(testCollDetails.getExtSettlementId()).isEqualTo(UPDATED_EXT_SETTLEMENT_ID);
        assertThat(testCollDetails.getLat()).isEqualTo(UPDATED_LAT);
        assertThat(testCollDetails.getLongI()).isEqualTo(UPDATED_LONG_I);
    }

    @Test
    @Transactional
    public void deleteCollDetails() throws Exception {
        // Initialize the database
        collDetailsRepository.saveAndFlush(collDetails);

		int databaseSizeBeforeDelete = collDetailsRepository.findAll().size();

        // Get the collDetails
        restCollDetailsMockMvc.perform(delete("/api/collDetailss/{id}", collDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CollDetails> collDetailss = collDetailsRepository.findAll();
        assertThat(collDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

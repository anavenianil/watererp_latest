package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Adjustments;
import com.callippus.water.erp.repository.AdjustmentsRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.callippus.water.erp.domain.enumeration.TxnStatus;

/**
 * Test class for the AdjustmentsResource REST controller.
 *
 * @see AdjustmentsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class AdjustmentsResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";

    private static final ZonedDateTime DEFAULT_TXN_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_TXN_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_TXN_TIME_STR = dateTimeFormatter.format(DEFAULT_TXN_TIME);


    private static final TxnStatus DEFAULT_STATUS = TxnStatus.INITIATED;
    private static final TxnStatus UPDATED_STATUS = TxnStatus.PROCESSING;

    @Inject
    private AdjustmentsRepository adjustmentsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restAdjustmentsMockMvc;

    private Adjustments adjustments;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AdjustmentsResource adjustmentsResource = new AdjustmentsResource();
        ReflectionTestUtils.setField(adjustmentsResource, "adjustmentsRepository", adjustmentsRepository);
        this.restAdjustmentsMockMvc = MockMvcBuilders.standaloneSetup(adjustmentsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        adjustments = new Adjustments();
        adjustments.setCan(DEFAULT_CAN);
        adjustments.setAmount(DEFAULT_AMOUNT);
        adjustments.setRemarks(DEFAULT_REMARKS);
        adjustments.setTxnTime(DEFAULT_TXN_TIME);
        adjustments.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createAdjustments() throws Exception {
        int databaseSizeBeforeCreate = adjustmentsRepository.findAll().size();

        // Create the Adjustments

        restAdjustmentsMockMvc.perform(post("/api/adjustmentss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adjustments)))
                .andExpect(status().isCreated());

        // Validate the Adjustments in the database
        List<Adjustments> adjustmentss = adjustmentsRepository.findAll();
        assertThat(adjustmentss).hasSize(databaseSizeBeforeCreate + 1);
        Adjustments testAdjustments = adjustmentss.get(adjustmentss.size() - 1);
        assertThat(testAdjustments.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testAdjustments.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testAdjustments.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testAdjustments.getTxnTime()).isEqualTo(DEFAULT_TXN_TIME);
        assertThat(testAdjustments.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = adjustmentsRepository.findAll().size();
        // set the field null
        adjustments.setStatus(null);

        // Create the Adjustments, which fails.

        restAdjustmentsMockMvc.perform(post("/api/adjustmentss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adjustments)))
                .andExpect(status().isBadRequest());

        List<Adjustments> adjustmentss = adjustmentsRepository.findAll();
        assertThat(adjustmentss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdjustmentss() throws Exception {
        // Initialize the database
        adjustmentsRepository.saveAndFlush(adjustments);

        // Get all the adjustmentss
        restAdjustmentsMockMvc.perform(get("/api/adjustmentss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(adjustments.getId().intValue())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].txnTime").value(hasItem(DEFAULT_TXN_TIME_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getAdjustments() throws Exception {
        // Initialize the database
        adjustmentsRepository.saveAndFlush(adjustments);

        // Get the adjustments
        restAdjustmentsMockMvc.perform(get("/api/adjustmentss/{id}", adjustments.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(adjustments.getId().intValue()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.txnTime").value(DEFAULT_TXN_TIME_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdjustments() throws Exception {
        // Get the adjustments
        restAdjustmentsMockMvc.perform(get("/api/adjustmentss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdjustments() throws Exception {
        // Initialize the database
        adjustmentsRepository.saveAndFlush(adjustments);

		int databaseSizeBeforeUpdate = adjustmentsRepository.findAll().size();

        // Update the adjustments
        adjustments.setCan(UPDATED_CAN);
        adjustments.setAmount(UPDATED_AMOUNT);
        adjustments.setRemarks(UPDATED_REMARKS);
        adjustments.setTxnTime(UPDATED_TXN_TIME);
        adjustments.setStatus(UPDATED_STATUS);

        restAdjustmentsMockMvc.perform(put("/api/adjustmentss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(adjustments)))
                .andExpect(status().isOk());

        // Validate the Adjustments in the database
        List<Adjustments> adjustmentss = adjustmentsRepository.findAll();
        assertThat(adjustmentss).hasSize(databaseSizeBeforeUpdate);
        Adjustments testAdjustments = adjustmentss.get(adjustmentss.size() - 1);
        assertThat(testAdjustments.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testAdjustments.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testAdjustments.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testAdjustments.getTxnTime()).isEqualTo(UPDATED_TXN_TIME);
        assertThat(testAdjustments.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteAdjustments() throws Exception {
        // Initialize the database
        adjustmentsRepository.saveAndFlush(adjustments);

		int databaseSizeBeforeDelete = adjustmentsRepository.findAll().size();

        // Get the adjustments
        restAdjustmentsMockMvc.perform(delete("/api/adjustmentss/{id}", adjustments.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Adjustments> adjustmentss = adjustmentsRepository.findAll();
        assertThat(adjustmentss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

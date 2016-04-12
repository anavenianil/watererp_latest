package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BillRunDetails;
import com.callippus.water.erp.repository.BillRunDetailsRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the BillRunDetailsResource REST controller.
 *
 * @see BillRunDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BillRunDetailsResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";

    private static final ZonedDateTime DEFAULT_FROM_DT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_FROM_DT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_FROM_DT_STR = dateTimeFormatter.format(DEFAULT_FROM_DT);

    private static final ZonedDateTime DEFAULT_TO_DT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_TO_DT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_TO_DT_STR = dateTimeFormatter.format(DEFAULT_TO_DT);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";

    @Inject
    private BillRunDetailsRepository billRunDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBillRunDetailsMockMvc;

    private BillRunDetails billRunDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BillRunDetailsResource billRunDetailsResource = new BillRunDetailsResource();
        ReflectionTestUtils.setField(billRunDetailsResource, "billRunDetailsRepository", billRunDetailsRepository);
        this.restBillRunDetailsMockMvc = MockMvcBuilders.standaloneSetup(billRunDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        billRunDetails = new BillRunDetails();
        billRunDetails.setCan(DEFAULT_CAN);
        billRunDetails.setFromDt(DEFAULT_FROM_DT);
        billRunDetails.setToDt(DEFAULT_TO_DT);
        billRunDetails.setStatus(DEFAULT_STATUS);
        billRunDetails.setRemarks(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createBillRunDetails() throws Exception {
        int databaseSizeBeforeCreate = billRunDetailsRepository.findAll().size();

        // Create the BillRunDetails

        restBillRunDetailsMockMvc.perform(post("/api/billRunDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billRunDetails)))
                .andExpect(status().isCreated());

        // Validate the BillRunDetails in the database
        List<BillRunDetails> billRunDetailss = billRunDetailsRepository.findAll();
        assertThat(billRunDetailss).hasSize(databaseSizeBeforeCreate + 1);
        BillRunDetails testBillRunDetails = billRunDetailss.get(billRunDetailss.size() - 1);
        assertThat(testBillRunDetails.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testBillRunDetails.getFromDt()).isEqualTo(DEFAULT_FROM_DT);
        assertThat(testBillRunDetails.getToDt()).isEqualTo(DEFAULT_TO_DT);
        assertThat(testBillRunDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBillRunDetails.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void getAllBillRunDetailss() throws Exception {
        // Initialize the database
        billRunDetailsRepository.saveAndFlush(billRunDetails);

        // Get all the billRunDetailss
        restBillRunDetailsMockMvc.perform(get("/api/billRunDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(billRunDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].fromDt").value(hasItem(DEFAULT_FROM_DT_STR)))
                .andExpect(jsonPath("$.[*].toDt").value(hasItem(DEFAULT_TO_DT_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }

    @Test
    @Transactional
    public void getBillRunDetails() throws Exception {
        // Initialize the database
        billRunDetailsRepository.saveAndFlush(billRunDetails);

        // Get the billRunDetails
        restBillRunDetailsMockMvc.perform(get("/api/billRunDetailss/{id}", billRunDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(billRunDetails.getId().intValue()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.fromDt").value(DEFAULT_FROM_DT_STR))
            .andExpect(jsonPath("$.toDt").value(DEFAULT_TO_DT_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBillRunDetails() throws Exception {
        // Get the billRunDetails
        restBillRunDetailsMockMvc.perform(get("/api/billRunDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillRunDetails() throws Exception {
        // Initialize the database
        billRunDetailsRepository.saveAndFlush(billRunDetails);

		int databaseSizeBeforeUpdate = billRunDetailsRepository.findAll().size();

        // Update the billRunDetails
        billRunDetails.setCan(UPDATED_CAN);
        billRunDetails.setFromDt(UPDATED_FROM_DT);
        billRunDetails.setToDt(UPDATED_TO_DT);
        billRunDetails.setStatus(UPDATED_STATUS);
        billRunDetails.setRemarks(UPDATED_REMARKS);

        restBillRunDetailsMockMvc.perform(put("/api/billRunDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billRunDetails)))
                .andExpect(status().isOk());

        // Validate the BillRunDetails in the database
        List<BillRunDetails> billRunDetailss = billRunDetailsRepository.findAll();
        assertThat(billRunDetailss).hasSize(databaseSizeBeforeUpdate);
        BillRunDetails testBillRunDetails = billRunDetailss.get(billRunDetailss.size() - 1);
        assertThat(testBillRunDetails.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testBillRunDetails.getFromDt()).isEqualTo(UPDATED_FROM_DT);
        assertThat(testBillRunDetails.getToDt()).isEqualTo(UPDATED_TO_DT);
        assertThat(testBillRunDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBillRunDetails.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void deleteBillRunDetails() throws Exception {
        // Initialize the database
        billRunDetailsRepository.saveAndFlush(billRunDetails);

		int databaseSizeBeforeDelete = billRunDetailsRepository.findAll().size();

        // Get the billRunDetails
        restBillRunDetailsMockMvc.perform(delete("/api/billRunDetailss/{id}", billRunDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BillRunDetails> billRunDetailss = billRunDetailsRepository.findAll();
        assertThat(billRunDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

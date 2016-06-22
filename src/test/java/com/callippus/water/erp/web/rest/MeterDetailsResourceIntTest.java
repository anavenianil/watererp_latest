package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MeterDetails;
import com.callippus.water.erp.repository.MeterDetailsRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the MeterDetailsResource REST controller.
 *
 * @see MeterDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MeterDetailsResourceIntTest {

    private static final String DEFAULT_METER_ID = "AAAAA";
    private static final String UPDATED_METER_ID = "BBBBB";
    private static final String DEFAULT_METER_NO = "AAAAA";
    private static final String UPDATED_METER_NO = "BBBBB";
    private static final String DEFAULT_METER_TYPE = "AAAAA";
    private static final String UPDATED_METER_TYPE = "BBBBB";
    private static final String DEFAULT_METER_MAKE = "AAAAA";
    private static final String UPDATED_METER_MAKE = "BBBBB";

    private static final Float DEFAULT_MIN = 1F;
    private static final Float UPDATED_MIN = 2F;

    private static final Float DEFAULT_MAX = 1F;
    private static final Float UPDATED_MAX = 2F;

    @Inject
    private MeterDetailsRepository meterDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMeterDetailsMockMvc;

    private MeterDetails meterDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MeterDetailsResource meterDetailsResource = new MeterDetailsResource();
        ReflectionTestUtils.setField(meterDetailsResource, "meterDetailsRepository", meterDetailsRepository);
        this.restMeterDetailsMockMvc = MockMvcBuilders.standaloneSetup(meterDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        meterDetails = new MeterDetails();
        meterDetails.setMeterId(DEFAULT_METER_ID);
        meterDetails.setMeterNo(DEFAULT_METER_NO);
        meterDetails.setMeterType(DEFAULT_METER_TYPE);
        meterDetails.setMeterMake(DEFAULT_METER_MAKE);
        meterDetails.setMin(DEFAULT_MIN);
        meterDetails.setMax(DEFAULT_MAX);
    }

    @Test
    @Transactional
    public void createMeterDetails() throws Exception {
        int databaseSizeBeforeCreate = meterDetailsRepository.findAll().size();

        // Create the MeterDetails

        restMeterDetailsMockMvc.perform(post("/api/meterDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(meterDetails)))
                .andExpect(status().isCreated());

        // Validate the MeterDetails in the database
        List<MeterDetails> meterDetailss = meterDetailsRepository.findAll();
        assertThat(meterDetailss).hasSize(databaseSizeBeforeCreate + 1);
        MeterDetails testMeterDetails = meterDetailss.get(meterDetailss.size() - 1);
        assertThat(testMeterDetails.getMeterId()).isEqualTo(DEFAULT_METER_ID);
        assertThat(testMeterDetails.getMeterNo()).isEqualTo(DEFAULT_METER_NO);
        assertThat(testMeterDetails.getMeterType()).isEqualTo(DEFAULT_METER_TYPE);
        assertThat(testMeterDetails.getMeterMake()).isEqualTo(DEFAULT_METER_MAKE);
        assertThat(testMeterDetails.getMin()).isEqualTo(DEFAULT_MIN);
        assertThat(testMeterDetails.getMax()).isEqualTo(DEFAULT_MAX);
    }

    @Test
    @Transactional
    public void checkMeterIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = meterDetailsRepository.findAll().size();
        // set the field null
        meterDetails.setMeterId(null);

        // Create the MeterDetails, which fails.

        restMeterDetailsMockMvc.perform(post("/api/meterDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(meterDetails)))
                .andExpect(status().isBadRequest());

        List<MeterDetails> meterDetailss = meterDetailsRepository.findAll();
        assertThat(meterDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMeterNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = meterDetailsRepository.findAll().size();
        // set the field null
        meterDetails.setMeterNo(null);

        // Create the MeterDetails, which fails.

        restMeterDetailsMockMvc.perform(post("/api/meterDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(meterDetails)))
                .andExpect(status().isBadRequest());

        List<MeterDetails> meterDetailss = meterDetailsRepository.findAll();
        assertThat(meterDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMeterDetailss() throws Exception {
        // Initialize the database
        meterDetailsRepository.saveAndFlush(meterDetails);

        // Get all the meterDetailss
        restMeterDetailsMockMvc.perform(get("/api/meterDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(meterDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].meterId").value(hasItem(DEFAULT_METER_ID.toString())))
                .andExpect(jsonPath("$.[*].meterNo").value(hasItem(DEFAULT_METER_NO.toString())))
                .andExpect(jsonPath("$.[*].meterType").value(hasItem(DEFAULT_METER_TYPE.toString())))
                .andExpect(jsonPath("$.[*].meterMake").value(hasItem(DEFAULT_METER_MAKE.toString())))
                .andExpect(jsonPath("$.[*].min").value(hasItem(DEFAULT_MIN.doubleValue())))
                .andExpect(jsonPath("$.[*].max").value(hasItem(DEFAULT_MAX.doubleValue())));
    }

    @Test
    @Transactional
    public void getMeterDetails() throws Exception {
        // Initialize the database
        meterDetailsRepository.saveAndFlush(meterDetails);

        // Get the meterDetails
        restMeterDetailsMockMvc.perform(get("/api/meterDetailss/{id}", meterDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(meterDetails.getId().intValue()))
            .andExpect(jsonPath("$.meterId").value(DEFAULT_METER_ID.toString()))
            .andExpect(jsonPath("$.meterNo").value(DEFAULT_METER_NO.toString()))
            .andExpect(jsonPath("$.meterType").value(DEFAULT_METER_TYPE.toString()))
            .andExpect(jsonPath("$.meterMake").value(DEFAULT_METER_MAKE.toString()))
            .andExpect(jsonPath("$.min").value(DEFAULT_MIN.doubleValue()))
            .andExpect(jsonPath("$.max").value(DEFAULT_MAX.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMeterDetails() throws Exception {
        // Get the meterDetails
        restMeterDetailsMockMvc.perform(get("/api/meterDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeterDetails() throws Exception {
        // Initialize the database
        meterDetailsRepository.saveAndFlush(meterDetails);

		int databaseSizeBeforeUpdate = meterDetailsRepository.findAll().size();

        // Update the meterDetails
        meterDetails.setMeterId(UPDATED_METER_ID);
        meterDetails.setMeterNo(UPDATED_METER_NO);
        meterDetails.setMeterType(UPDATED_METER_TYPE);
        meterDetails.setMeterMake(UPDATED_METER_MAKE);
        meterDetails.setMin(UPDATED_MIN);
        meterDetails.setMax(UPDATED_MAX);

        restMeterDetailsMockMvc.perform(put("/api/meterDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(meterDetails)))
                .andExpect(status().isOk());

        // Validate the MeterDetails in the database
        List<MeterDetails> meterDetailss = meterDetailsRepository.findAll();
        assertThat(meterDetailss).hasSize(databaseSizeBeforeUpdate);
        MeterDetails testMeterDetails = meterDetailss.get(meterDetailss.size() - 1);
        assertThat(testMeterDetails.getMeterId()).isEqualTo(UPDATED_METER_ID);
        assertThat(testMeterDetails.getMeterNo()).isEqualTo(UPDATED_METER_NO);
        assertThat(testMeterDetails.getMeterType()).isEqualTo(UPDATED_METER_TYPE);
        assertThat(testMeterDetails.getMeterMake()).isEqualTo(UPDATED_METER_MAKE);
        assertThat(testMeterDetails.getMin()).isEqualTo(UPDATED_MIN);
        assertThat(testMeterDetails.getMax()).isEqualTo(UPDATED_MAX);
    }

    @Test
    @Transactional
    public void deleteMeterDetails() throws Exception {
        // Initialize the database
        meterDetailsRepository.saveAndFlush(meterDetails);

		int databaseSizeBeforeDelete = meterDetailsRepository.findAll().size();

        // Get the meterDetails
        restMeterDetailsMockMvc.perform(delete("/api/meterDetailss/{id}", meterDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MeterDetails> meterDetailss = meterDetailsRepository.findAll();
        assertThat(meterDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

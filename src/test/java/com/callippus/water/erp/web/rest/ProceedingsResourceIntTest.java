package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Proceedings;
import com.callippus.water.erp.repository.ProceedingsRepository;

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
 * Test class for the ProceedingsResource REST controller.
 *
 * @see ProceedingsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProceedingsResourceIntTest {


    private static final Double DEFAULT_SUB_TOTAL_A = 1D;
    private static final Double UPDATED_SUB_TOTAL_A = 2D;

    private static final Double DEFAULT_SUPERVISION_CHARGE = 1D;
    private static final Double UPDATED_SUPERVISION_CHARGE = 2D;

    private static final Double DEFAULT_LABOUR_CHARGE = 1D;
    private static final Double UPDATED_LABOUR_CHARGE = 2D;

    private static final Double DEFAULT_SITE_SURVEY = 1D;
    private static final Double UPDATED_SITE_SURVEY = 2D;

    private static final Double DEFAULT_SUB_TOTAL_B = 1D;
    private static final Double UPDATED_SUB_TOTAL_B = 2D;

    private static final Double DEFAULT_CONNECTION_FEE = 1D;
    private static final Double UPDATED_CONNECTION_FEE = 2D;

    private static final Double DEFAULT_WATER_METER_SHS = 1D;
    private static final Double UPDATED_WATER_METER_SHS = 2D;

    private static final Double DEFAULT_APPLICATION_FORM_FEE = 1D;
    private static final Double UPDATED_APPLICATION_FORM_FEE = 2D;

    private static final Double DEFAULT_GRAND_TOTAL = 1D;
    private static final Double UPDATED_GRAND_TOTAL = 2D;

    @Inject
    private ProceedingsRepository proceedingsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProceedingsMockMvc;

    private Proceedings proceedings;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProceedingsResource proceedingsResource = new ProceedingsResource();
        ReflectionTestUtils.setField(proceedingsResource, "proceedingsRepository", proceedingsRepository);
        this.restProceedingsMockMvc = MockMvcBuilders.standaloneSetup(proceedingsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        proceedings = new Proceedings();
        proceedings.setSubTotalA(DEFAULT_SUB_TOTAL_A);
        proceedings.setSupervisionCharge(DEFAULT_SUPERVISION_CHARGE);
        proceedings.setLabourCharge(DEFAULT_LABOUR_CHARGE);
        proceedings.setSiteSurvey(DEFAULT_SITE_SURVEY);
        proceedings.setSubTotalB(DEFAULT_SUB_TOTAL_B);
        proceedings.setConnectionFee(DEFAULT_CONNECTION_FEE);
        proceedings.setWaterMeterShs(DEFAULT_WATER_METER_SHS);
        proceedings.setApplicationFormFee(DEFAULT_APPLICATION_FORM_FEE);
        proceedings.setGrandTotal(DEFAULT_GRAND_TOTAL);
    }

    @Test
    @Transactional
    public void createProceedings() throws Exception {
        int databaseSizeBeforeCreate = proceedingsRepository.findAll().size();

        // Create the Proceedings

        restProceedingsMockMvc.perform(post("/api/proceedingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(proceedings)))
                .andExpect(status().isCreated());

        // Validate the Proceedings in the database
        List<Proceedings> proceedingss = proceedingsRepository.findAll();
        assertThat(proceedingss).hasSize(databaseSizeBeforeCreate + 1);
        Proceedings testProceedings = proceedingss.get(proceedingss.size() - 1);
        assertThat(testProceedings.getSubTotalA()).isEqualTo(DEFAULT_SUB_TOTAL_A);
        assertThat(testProceedings.getSupervisionCharge()).isEqualTo(DEFAULT_SUPERVISION_CHARGE);
        assertThat(testProceedings.getLabourCharge()).isEqualTo(DEFAULT_LABOUR_CHARGE);
        assertThat(testProceedings.getSiteSurvey()).isEqualTo(DEFAULT_SITE_SURVEY);
        assertThat(testProceedings.getSubTotalB()).isEqualTo(DEFAULT_SUB_TOTAL_B);
        assertThat(testProceedings.getConnectionFee()).isEqualTo(DEFAULT_CONNECTION_FEE);
        assertThat(testProceedings.getWaterMeterShs()).isEqualTo(DEFAULT_WATER_METER_SHS);
        assertThat(testProceedings.getApplicationFormFee()).isEqualTo(DEFAULT_APPLICATION_FORM_FEE);
        assertThat(testProceedings.getGrandTotal()).isEqualTo(DEFAULT_GRAND_TOTAL);
    }

    @Test
    @Transactional
    public void getAllProceedingss() throws Exception {
        // Initialize the database
        proceedingsRepository.saveAndFlush(proceedings);

        // Get all the proceedingss
        restProceedingsMockMvc.perform(get("/api/proceedingss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(proceedings.getId().intValue())))
                .andExpect(jsonPath("$.[*].subTotalA").value(hasItem(DEFAULT_SUB_TOTAL_A.doubleValue())))
                .andExpect(jsonPath("$.[*].supervisionCharge").value(hasItem(DEFAULT_SUPERVISION_CHARGE.doubleValue())))
                .andExpect(jsonPath("$.[*].labourCharge").value(hasItem(DEFAULT_LABOUR_CHARGE.doubleValue())))
                .andExpect(jsonPath("$.[*].siteSurvey").value(hasItem(DEFAULT_SITE_SURVEY.doubleValue())))
                .andExpect(jsonPath("$.[*].subTotalB").value(hasItem(DEFAULT_SUB_TOTAL_B.doubleValue())))
                .andExpect(jsonPath("$.[*].connectionFee").value(hasItem(DEFAULT_CONNECTION_FEE.doubleValue())))
                .andExpect(jsonPath("$.[*].waterMeterShs").value(hasItem(DEFAULT_WATER_METER_SHS.doubleValue())))
                .andExpect(jsonPath("$.[*].applicationFormFee").value(hasItem(DEFAULT_APPLICATION_FORM_FEE.doubleValue())))
                .andExpect(jsonPath("$.[*].grandTotal").value(hasItem(DEFAULT_GRAND_TOTAL.doubleValue())));
    }

    @Test
    @Transactional
    public void getProceedings() throws Exception {
        // Initialize the database
        proceedingsRepository.saveAndFlush(proceedings);

        // Get the proceedings
        restProceedingsMockMvc.perform(get("/api/proceedingss/{id}", proceedings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(proceedings.getId().intValue()))
            .andExpect(jsonPath("$.subTotalA").value(DEFAULT_SUB_TOTAL_A.doubleValue()))
            .andExpect(jsonPath("$.supervisionCharge").value(DEFAULT_SUPERVISION_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.labourCharge").value(DEFAULT_LABOUR_CHARGE.doubleValue()))
            .andExpect(jsonPath("$.siteSurvey").value(DEFAULT_SITE_SURVEY.doubleValue()))
            .andExpect(jsonPath("$.subTotalB").value(DEFAULT_SUB_TOTAL_B.doubleValue()))
            .andExpect(jsonPath("$.connectionFee").value(DEFAULT_CONNECTION_FEE.doubleValue()))
            .andExpect(jsonPath("$.waterMeterShs").value(DEFAULT_WATER_METER_SHS.doubleValue()))
            .andExpect(jsonPath("$.applicationFormFee").value(DEFAULT_APPLICATION_FORM_FEE.doubleValue()))
            .andExpect(jsonPath("$.grandTotal").value(DEFAULT_GRAND_TOTAL.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProceedings() throws Exception {
        // Get the proceedings
        restProceedingsMockMvc.perform(get("/api/proceedingss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProceedings() throws Exception {
        // Initialize the database
        proceedingsRepository.saveAndFlush(proceedings);

		int databaseSizeBeforeUpdate = proceedingsRepository.findAll().size();

        // Update the proceedings
        proceedings.setSubTotalA(UPDATED_SUB_TOTAL_A);
        proceedings.setSupervisionCharge(UPDATED_SUPERVISION_CHARGE);
        proceedings.setLabourCharge(UPDATED_LABOUR_CHARGE);
        proceedings.setSiteSurvey(UPDATED_SITE_SURVEY);
        proceedings.setSubTotalB(UPDATED_SUB_TOTAL_B);
        proceedings.setConnectionFee(UPDATED_CONNECTION_FEE);
        proceedings.setWaterMeterShs(UPDATED_WATER_METER_SHS);
        proceedings.setApplicationFormFee(UPDATED_APPLICATION_FORM_FEE);
        proceedings.setGrandTotal(UPDATED_GRAND_TOTAL);

        restProceedingsMockMvc.perform(put("/api/proceedingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(proceedings)))
                .andExpect(status().isOk());

        // Validate the Proceedings in the database
        List<Proceedings> proceedingss = proceedingsRepository.findAll();
        assertThat(proceedingss).hasSize(databaseSizeBeforeUpdate);
        Proceedings testProceedings = proceedingss.get(proceedingss.size() - 1);
        assertThat(testProceedings.getSubTotalA()).isEqualTo(UPDATED_SUB_TOTAL_A);
        assertThat(testProceedings.getSupervisionCharge()).isEqualTo(UPDATED_SUPERVISION_CHARGE);
        assertThat(testProceedings.getLabourCharge()).isEqualTo(UPDATED_LABOUR_CHARGE);
        assertThat(testProceedings.getSiteSurvey()).isEqualTo(UPDATED_SITE_SURVEY);
        assertThat(testProceedings.getSubTotalB()).isEqualTo(UPDATED_SUB_TOTAL_B);
        assertThat(testProceedings.getConnectionFee()).isEqualTo(UPDATED_CONNECTION_FEE);
        assertThat(testProceedings.getWaterMeterShs()).isEqualTo(UPDATED_WATER_METER_SHS);
        assertThat(testProceedings.getApplicationFormFee()).isEqualTo(UPDATED_APPLICATION_FORM_FEE);
        assertThat(testProceedings.getGrandTotal()).isEqualTo(UPDATED_GRAND_TOTAL);
    }

    @Test
    @Transactional
    public void deleteProceedings() throws Exception {
        // Initialize the database
        proceedingsRepository.saveAndFlush(proceedings);

		int databaseSizeBeforeDelete = proceedingsRepository.findAll().size();

        // Get the proceedings
        restProceedingsMockMvc.perform(delete("/api/proceedingss/{id}", proceedings.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Proceedings> proceedingss = proceedingsRepository.findAll();
        assertThat(proceedingss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

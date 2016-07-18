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
import java.math.BigDecimal;
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


    private static final BigDecimal DEFAULT_SUB_TOTAL_A = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUB_TOTAL_A = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SUPERVISION_CHARGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUPERVISION_CHARGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LABOUR_CHARGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_LABOUR_CHARGE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SITE_SURVEY = new BigDecimal(1);
    private static final BigDecimal UPDATED_SITE_SURVEY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SUB_TOTAL_B = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUB_TOTAL_B = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONNECTION_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONNECTION_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_WATER_METER_SHS = new BigDecimal(1);
    private static final BigDecimal UPDATED_WATER_METER_SHS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_APPLICATION_FORM_FEE = new BigDecimal(1);
    private static final BigDecimal UPDATED_APPLICATION_FORM_FEE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_GRAND_TOTAL = new BigDecimal(1);
    private static final BigDecimal UPDATED_GRAND_TOTAL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SUPERVISION_PERCENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SUPERVISION_PERCENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LABOUR_CHARGE_PERCENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_LABOUR_CHARGE_PERCENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_SITE_SURVEY_PERCENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_SITE_SURVEY_PERCENT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONNECTION_FEE_PERCENT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONNECTION_FEE_PERCENT = new BigDecimal(2);

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
        proceedings.setSupervisionPercent(DEFAULT_SUPERVISION_PERCENT);
        proceedings.setLabourChargePercent(DEFAULT_LABOUR_CHARGE_PERCENT);
        proceedings.setSiteSurveyPercent(DEFAULT_SITE_SURVEY_PERCENT);
        proceedings.setConnectionFeePercent(DEFAULT_CONNECTION_FEE_PERCENT);
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
        assertThat(testProceedings.getSupervisionPercent()).isEqualTo(DEFAULT_SUPERVISION_PERCENT);
        assertThat(testProceedings.getLabourChargePercent()).isEqualTo(DEFAULT_LABOUR_CHARGE_PERCENT);
        assertThat(testProceedings.getSiteSurveyPercent()).isEqualTo(DEFAULT_SITE_SURVEY_PERCENT);
        assertThat(testProceedings.getConnectionFeePercent()).isEqualTo(DEFAULT_CONNECTION_FEE_PERCENT);
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
                .andExpect(jsonPath("$.[*].subTotalA").value(hasItem(DEFAULT_SUB_TOTAL_A.intValue())))
                .andExpect(jsonPath("$.[*].supervisionCharge").value(hasItem(DEFAULT_SUPERVISION_CHARGE.intValue())))
                .andExpect(jsonPath("$.[*].labourCharge").value(hasItem(DEFAULT_LABOUR_CHARGE.intValue())))
                .andExpect(jsonPath("$.[*].siteSurvey").value(hasItem(DEFAULT_SITE_SURVEY.intValue())))
                .andExpect(jsonPath("$.[*].subTotalB").value(hasItem(DEFAULT_SUB_TOTAL_B.intValue())))
                .andExpect(jsonPath("$.[*].connectionFee").value(hasItem(DEFAULT_CONNECTION_FEE.intValue())))
                .andExpect(jsonPath("$.[*].waterMeterShs").value(hasItem(DEFAULT_WATER_METER_SHS.intValue())))
                .andExpect(jsonPath("$.[*].applicationFormFee").value(hasItem(DEFAULT_APPLICATION_FORM_FEE.intValue())))
                .andExpect(jsonPath("$.[*].grandTotal").value(hasItem(DEFAULT_GRAND_TOTAL.intValue())))
                .andExpect(jsonPath("$.[*].supervisionPercent").value(hasItem(DEFAULT_SUPERVISION_PERCENT.intValue())))
                .andExpect(jsonPath("$.[*].labourChargePercent").value(hasItem(DEFAULT_LABOUR_CHARGE_PERCENT.intValue())))
                .andExpect(jsonPath("$.[*].siteSurveyPercent").value(hasItem(DEFAULT_SITE_SURVEY_PERCENT.intValue())))
                .andExpect(jsonPath("$.[*].ConnectionFeePercent").value(hasItem(DEFAULT_CONNECTION_FEE_PERCENT.intValue())));
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
            .andExpect(jsonPath("$.subTotalA").value(DEFAULT_SUB_TOTAL_A.intValue()))
            .andExpect(jsonPath("$.supervisionCharge").value(DEFAULT_SUPERVISION_CHARGE.intValue()))
            .andExpect(jsonPath("$.labourCharge").value(DEFAULT_LABOUR_CHARGE.intValue()))
            .andExpect(jsonPath("$.siteSurvey").value(DEFAULT_SITE_SURVEY.intValue()))
            .andExpect(jsonPath("$.subTotalB").value(DEFAULT_SUB_TOTAL_B.intValue()))
            .andExpect(jsonPath("$.connectionFee").value(DEFAULT_CONNECTION_FEE.intValue()))
            .andExpect(jsonPath("$.waterMeterShs").value(DEFAULT_WATER_METER_SHS.intValue()))
            .andExpect(jsonPath("$.applicationFormFee").value(DEFAULT_APPLICATION_FORM_FEE.intValue()))
            .andExpect(jsonPath("$.grandTotal").value(DEFAULT_GRAND_TOTAL.intValue()))
            .andExpect(jsonPath("$.supervisionPercent").value(DEFAULT_SUPERVISION_PERCENT.intValue()))
            .andExpect(jsonPath("$.labourChargePercent").value(DEFAULT_LABOUR_CHARGE_PERCENT.intValue()))
            .andExpect(jsonPath("$.siteSurveyPercent").value(DEFAULT_SITE_SURVEY_PERCENT.intValue()))
            .andExpect(jsonPath("$.ConnectionFeePercent").value(DEFAULT_CONNECTION_FEE_PERCENT.intValue()));
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
        proceedings.setSupervisionPercent(UPDATED_SUPERVISION_PERCENT);
        proceedings.setLabourChargePercent(UPDATED_LABOUR_CHARGE_PERCENT);
        proceedings.setSiteSurveyPercent(UPDATED_SITE_SURVEY_PERCENT);
        proceedings.setConnectionFeePercent(UPDATED_CONNECTION_FEE_PERCENT);

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
        assertThat(testProceedings.getSupervisionPercent()).isEqualTo(UPDATED_SUPERVISION_PERCENT);
        assertThat(testProceedings.getLabourChargePercent()).isEqualTo(UPDATED_LABOUR_CHARGE_PERCENT);
        assertThat(testProceedings.getSiteSurveyPercent()).isEqualTo(UPDATED_SITE_SURVEY_PERCENT);
        assertThat(testProceedings.getConnectionFeePercent()).isEqualTo(UPDATED_CONNECTION_FEE_PERCENT);
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

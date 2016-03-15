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


    private static final Float DEFAULT_WATER_SUPPLY_CONNECTION_CHARGES = 1F;
    private static final Float UPDATED_WATER_SUPPLY_CONNECTION_CHARGES = 2F;

    private static final Float DEFAULT_WATER_OTHER_CHARGES = 1F;
    private static final Float UPDATED_WATER_OTHER_CHARGES = 2F;

    private static final Float DEFAULT_SIXTY_DAYS_CONSUMPTION_CHARGES = 1F;
    private static final Float UPDATED_SIXTY_DAYS_CONSUMPTION_CHARGES = 2F;

    private static final Float DEFAULT_WATER_SUPPLY_IMPROVEMENT_CHARGES = 1F;
    private static final Float UPDATED_WATER_SUPPLY_IMPROVEMENT_CHARGES = 2F;

    private static final Float DEFAULT_METER_COST = 1F;
    private static final Float UPDATED_METER_COST = 2F;

    private static final Float DEFAULT_GREEN_BRIGADE_CHARGES = 1F;
    private static final Float UPDATED_GREEN_BRIGADE_CHARGES = 2F;

    private static final Float DEFAULT_RWHS_CHARGES = 1F;
    private static final Float UPDATED_RWHS_CHARGES = 2F;

    private static final Float DEFAULT_TOTAL_WATER_CHARGES = 1F;
    private static final Float UPDATED_TOTAL_WATER_CHARGES = 2F;

    private static final Float DEFAULT_SEWERAGE_CONNECTION_CHARGES = 1F;
    private static final Float UPDATED_SEWERAGE_CONNECTION_CHARGES = 2F;

    private static final Float DEFAULT_SEWERAGE_OTHER_CHARGES = 1F;
    private static final Float UPDATED_SEWERAGE_OTHER_CHARGES = 2F;

    private static final Float DEFAULT_SEWERGE_IMPROVEMENT_CHARGES = 1F;
    private static final Float UPDATED_SEWERGE_IMPROVEMENT_CHARGES = 2F;

    private static final Float DEFAULT_TOTAL_SEWERAGE_CHARGES = 1F;
    private static final Float UPDATED_TOTAL_SEWERAGE_CHARGES = 2F;

    private static final Float DEFAULT_TOTAL_AMOUNT = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT = 2F;

    private static final Float DEFAULT_TOTAL_DEDUCTION = 1F;
    private static final Float UPDATED_TOTAL_DEDUCTION = 2F;

    private static final Float DEFAULT_BALANCE = 1F;
    private static final Float UPDATED_BALANCE = 2F;

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
        proceedings.setWaterSupplyConnectionCharges(DEFAULT_WATER_SUPPLY_CONNECTION_CHARGES);
        proceedings.setWaterOtherCharges(DEFAULT_WATER_OTHER_CHARGES);
        proceedings.setSixtyDaysConsumptionCharges(DEFAULT_SIXTY_DAYS_CONSUMPTION_CHARGES);
        proceedings.setWaterSupplyImprovementCharges(DEFAULT_WATER_SUPPLY_IMPROVEMENT_CHARGES);
        proceedings.setMeterCost(DEFAULT_METER_COST);
        proceedings.setGreenBrigadeCharges(DEFAULT_GREEN_BRIGADE_CHARGES);
        proceedings.setRwhsCharges(DEFAULT_RWHS_CHARGES);
        proceedings.setTotalWaterCharges(DEFAULT_TOTAL_WATER_CHARGES);
        proceedings.setSewerageConnectionCharges(DEFAULT_SEWERAGE_CONNECTION_CHARGES);
        proceedings.setSewerageOtherCharges(DEFAULT_SEWERAGE_OTHER_CHARGES);
        proceedings.setSewergeImprovementCharges(DEFAULT_SEWERGE_IMPROVEMENT_CHARGES);
        proceedings.setTotalSewerageCharges(DEFAULT_TOTAL_SEWERAGE_CHARGES);
        proceedings.setTotalAmount(DEFAULT_TOTAL_AMOUNT);
        proceedings.setTotalDeduction(DEFAULT_TOTAL_DEDUCTION);
        proceedings.setBalance(DEFAULT_BALANCE);
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
        assertThat(testProceedings.getWaterSupplyConnectionCharges()).isEqualTo(DEFAULT_WATER_SUPPLY_CONNECTION_CHARGES);
        assertThat(testProceedings.getWaterOtherCharges()).isEqualTo(DEFAULT_WATER_OTHER_CHARGES);
        assertThat(testProceedings.getSixtyDaysConsumptionCharges()).isEqualTo(DEFAULT_SIXTY_DAYS_CONSUMPTION_CHARGES);
        assertThat(testProceedings.getWaterSupplyImprovementCharges()).isEqualTo(DEFAULT_WATER_SUPPLY_IMPROVEMENT_CHARGES);
        assertThat(testProceedings.getMeterCost()).isEqualTo(DEFAULT_METER_COST);
        assertThat(testProceedings.getGreenBrigadeCharges()).isEqualTo(DEFAULT_GREEN_BRIGADE_CHARGES);
        assertThat(testProceedings.getRwhsCharges()).isEqualTo(DEFAULT_RWHS_CHARGES);
        assertThat(testProceedings.getTotalWaterCharges()).isEqualTo(DEFAULT_TOTAL_WATER_CHARGES);
        assertThat(testProceedings.getSewerageConnectionCharges()).isEqualTo(DEFAULT_SEWERAGE_CONNECTION_CHARGES);
        assertThat(testProceedings.getSewerageOtherCharges()).isEqualTo(DEFAULT_SEWERAGE_OTHER_CHARGES);
        assertThat(testProceedings.getSewergeImprovementCharges()).isEqualTo(DEFAULT_SEWERGE_IMPROVEMENT_CHARGES);
        assertThat(testProceedings.getTotalSewerageCharges()).isEqualTo(DEFAULT_TOTAL_SEWERAGE_CHARGES);
        assertThat(testProceedings.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testProceedings.getTotalDeduction()).isEqualTo(DEFAULT_TOTAL_DEDUCTION);
        assertThat(testProceedings.getBalance()).isEqualTo(DEFAULT_BALANCE);
    }

    @Test
    @Transactional
    public void checkWaterSupplyConnectionChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = proceedingsRepository.findAll().size();
        // set the field null
        proceedings.setWaterSupplyConnectionCharges(null);

        // Create the Proceedings, which fails.

        restProceedingsMockMvc.perform(post("/api/proceedingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(proceedings)))
                .andExpect(status().isBadRequest());

        List<Proceedings> proceedingss = proceedingsRepository.findAll();
        assertThat(proceedingss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSixtyDaysConsumptionChargesIsRequired() throws Exception {
        int databaseSizeBeforeTest = proceedingsRepository.findAll().size();
        // set the field null
        proceedings.setSixtyDaysConsumptionCharges(null);

        // Create the Proceedings, which fails.

        restProceedingsMockMvc.perform(post("/api/proceedingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(proceedings)))
                .andExpect(status().isBadRequest());

        List<Proceedings> proceedingss = proceedingsRepository.findAll();
        assertThat(proceedingss).hasSize(databaseSizeBeforeTest);
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
                .andExpect(jsonPath("$.[*].waterSupplyConnectionCharges").value(hasItem(DEFAULT_WATER_SUPPLY_CONNECTION_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].waterOtherCharges").value(hasItem(DEFAULT_WATER_OTHER_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].sixtyDaysConsumptionCharges").value(hasItem(DEFAULT_SIXTY_DAYS_CONSUMPTION_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].waterSupplyImprovementCharges").value(hasItem(DEFAULT_WATER_SUPPLY_IMPROVEMENT_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].meterCost").value(hasItem(DEFAULT_METER_COST.doubleValue())))
                .andExpect(jsonPath("$.[*].greenBrigadeCharges").value(hasItem(DEFAULT_GREEN_BRIGADE_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].rwhsCharges").value(hasItem(DEFAULT_RWHS_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].totalWaterCharges").value(hasItem(DEFAULT_TOTAL_WATER_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].sewerageConnectionCharges").value(hasItem(DEFAULT_SEWERAGE_CONNECTION_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].sewerageOtherCharges").value(hasItem(DEFAULT_SEWERAGE_OTHER_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].sewergeImprovementCharges").value(hasItem(DEFAULT_SEWERGE_IMPROVEMENT_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].totalSewerageCharges").value(hasItem(DEFAULT_TOTAL_SEWERAGE_CHARGES.doubleValue())))
                .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].totalDeduction").value(hasItem(DEFAULT_TOTAL_DEDUCTION.doubleValue())))
                .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.doubleValue())));
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
            .andExpect(jsonPath("$.waterSupplyConnectionCharges").value(DEFAULT_WATER_SUPPLY_CONNECTION_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.waterOtherCharges").value(DEFAULT_WATER_OTHER_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.sixtyDaysConsumptionCharges").value(DEFAULT_SIXTY_DAYS_CONSUMPTION_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.waterSupplyImprovementCharges").value(DEFAULT_WATER_SUPPLY_IMPROVEMENT_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.meterCost").value(DEFAULT_METER_COST.doubleValue()))
            .andExpect(jsonPath("$.greenBrigadeCharges").value(DEFAULT_GREEN_BRIGADE_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.rwhsCharges").value(DEFAULT_RWHS_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.totalWaterCharges").value(DEFAULT_TOTAL_WATER_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.sewerageConnectionCharges").value(DEFAULT_SEWERAGE_CONNECTION_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.sewerageOtherCharges").value(DEFAULT_SEWERAGE_OTHER_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.sewergeImprovementCharges").value(DEFAULT_SEWERGE_IMPROVEMENT_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.totalSewerageCharges").value(DEFAULT_TOTAL_SEWERAGE_CHARGES.doubleValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalDeduction").value(DEFAULT_TOTAL_DEDUCTION.doubleValue()))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.doubleValue()));
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
        proceedings.setWaterSupplyConnectionCharges(UPDATED_WATER_SUPPLY_CONNECTION_CHARGES);
        proceedings.setWaterOtherCharges(UPDATED_WATER_OTHER_CHARGES);
        proceedings.setSixtyDaysConsumptionCharges(UPDATED_SIXTY_DAYS_CONSUMPTION_CHARGES);
        proceedings.setWaterSupplyImprovementCharges(UPDATED_WATER_SUPPLY_IMPROVEMENT_CHARGES);
        proceedings.setMeterCost(UPDATED_METER_COST);
        proceedings.setGreenBrigadeCharges(UPDATED_GREEN_BRIGADE_CHARGES);
        proceedings.setRwhsCharges(UPDATED_RWHS_CHARGES);
        proceedings.setTotalWaterCharges(UPDATED_TOTAL_WATER_CHARGES);
        proceedings.setSewerageConnectionCharges(UPDATED_SEWERAGE_CONNECTION_CHARGES);
        proceedings.setSewerageOtherCharges(UPDATED_SEWERAGE_OTHER_CHARGES);
        proceedings.setSewergeImprovementCharges(UPDATED_SEWERGE_IMPROVEMENT_CHARGES);
        proceedings.setTotalSewerageCharges(UPDATED_TOTAL_SEWERAGE_CHARGES);
        proceedings.setTotalAmount(UPDATED_TOTAL_AMOUNT);
        proceedings.setTotalDeduction(UPDATED_TOTAL_DEDUCTION);
        proceedings.setBalance(UPDATED_BALANCE);

        restProceedingsMockMvc.perform(put("/api/proceedingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(proceedings)))
                .andExpect(status().isOk());

        // Validate the Proceedings in the database
        List<Proceedings> proceedingss = proceedingsRepository.findAll();
        assertThat(proceedingss).hasSize(databaseSizeBeforeUpdate);
        Proceedings testProceedings = proceedingss.get(proceedingss.size() - 1);
        assertThat(testProceedings.getWaterSupplyConnectionCharges()).isEqualTo(UPDATED_WATER_SUPPLY_CONNECTION_CHARGES);
        assertThat(testProceedings.getWaterOtherCharges()).isEqualTo(UPDATED_WATER_OTHER_CHARGES);
        assertThat(testProceedings.getSixtyDaysConsumptionCharges()).isEqualTo(UPDATED_SIXTY_DAYS_CONSUMPTION_CHARGES);
        assertThat(testProceedings.getWaterSupplyImprovementCharges()).isEqualTo(UPDATED_WATER_SUPPLY_IMPROVEMENT_CHARGES);
        assertThat(testProceedings.getMeterCost()).isEqualTo(UPDATED_METER_COST);
        assertThat(testProceedings.getGreenBrigadeCharges()).isEqualTo(UPDATED_GREEN_BRIGADE_CHARGES);
        assertThat(testProceedings.getRwhsCharges()).isEqualTo(UPDATED_RWHS_CHARGES);
        assertThat(testProceedings.getTotalWaterCharges()).isEqualTo(UPDATED_TOTAL_WATER_CHARGES);
        assertThat(testProceedings.getSewerageConnectionCharges()).isEqualTo(UPDATED_SEWERAGE_CONNECTION_CHARGES);
        assertThat(testProceedings.getSewerageOtherCharges()).isEqualTo(UPDATED_SEWERAGE_OTHER_CHARGES);
        assertThat(testProceedings.getSewergeImprovementCharges()).isEqualTo(UPDATED_SEWERGE_IMPROVEMENT_CHARGES);
        assertThat(testProceedings.getTotalSewerageCharges()).isEqualTo(UPDATED_TOTAL_SEWERAGE_CHARGES);
        assertThat(testProceedings.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testProceedings.getTotalDeduction()).isEqualTo(UPDATED_TOTAL_DEDUCTION);
        assertThat(testProceedings.getBalance()).isEqualTo(UPDATED_BALANCE);
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

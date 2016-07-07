package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.TariffCharges;
import com.callippus.water.erp.repository.TariffChargesRepository;

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
 * Test class for the TariffChargesResource REST controller.
 *
 * @see TariffChargesResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TariffChargesResourceIntTest {

    private static final String DEFAULT_TARIFF_DESC = "AAAAA";
    private static final String UPDATED_TARIFF_DESC = "BBBBB";

    private static final Integer DEFAULT_SLAB_MIN = 1;
    private static final Integer UPDATED_SLAB_MIN = 2;

    private static final Integer DEFAULT_SLAB_MAX = 1;
    private static final Integer UPDATED_SLAB_MAX = 2;

    private static final BigDecimal DEFAULT_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_RATE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_KL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_KL = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MIN_UNMETERED_KL = new BigDecimal(1);
    private static final BigDecimal UPDATED_MIN_UNMETERED_KL = new BigDecimal(2);

    @Inject
    private TariffChargesRepository tariffChargesRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTariffChargesMockMvc;

    private TariffCharges tariffCharges;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TariffChargesResource tariffChargesResource = new TariffChargesResource();
        ReflectionTestUtils.setField(tariffChargesResource, "tariffChargesRepository", tariffChargesRepository);
        this.restTariffChargesMockMvc = MockMvcBuilders.standaloneSetup(tariffChargesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        tariffCharges = new TariffCharges();
        tariffCharges.setTariffDesc(DEFAULT_TARIFF_DESC);
        tariffCharges.setSlabMin(DEFAULT_SLAB_MIN);
        tariffCharges.setSlabMax(DEFAULT_SLAB_MAX);
        tariffCharges.setRate(DEFAULT_RATE);
        tariffCharges.setMinKL(DEFAULT_MIN_KL);
        tariffCharges.setMinUnmeteredKL(DEFAULT_MIN_UNMETERED_KL);
    }

    @Test
    @Transactional
    public void createTariffCharges() throws Exception {
        int databaseSizeBeforeCreate = tariffChargesRepository.findAll().size();

        // Create the TariffCharges

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isCreated());

        // Validate the TariffCharges in the database
        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeCreate + 1);
        TariffCharges testTariffCharges = tariffChargess.get(tariffChargess.size() - 1);
        assertThat(testTariffCharges.getTariffDesc()).isEqualTo(DEFAULT_TARIFF_DESC);
        assertThat(testTariffCharges.getSlabMin()).isEqualTo(DEFAULT_SLAB_MIN);
        assertThat(testTariffCharges.getSlabMax()).isEqualTo(DEFAULT_SLAB_MAX);
        assertThat(testTariffCharges.getRate()).isEqualTo(DEFAULT_RATE);
        assertThat(testTariffCharges.getMinKL()).isEqualTo(DEFAULT_MIN_KL);
        assertThat(testTariffCharges.getMinUnmeteredKL()).isEqualTo(DEFAULT_MIN_UNMETERED_KL);
    }

    @Test
    @Transactional
    public void checkTariffDescIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffChargesRepository.findAll().size();
        // set the field null
        tariffCharges.setTariffDesc(null);

        // Create the TariffCharges, which fails.

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isBadRequest());

        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSlabMinIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffChargesRepository.findAll().size();
        // set the field null
        tariffCharges.setSlabMin(null);

        // Create the TariffCharges, which fails.

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isBadRequest());

        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSlabMaxIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffChargesRepository.findAll().size();
        // set the field null
        tariffCharges.setSlabMax(null);

        // Create the TariffCharges, which fails.

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isBadRequest());

        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffChargesRepository.findAll().size();
        // set the field null
        tariffCharges.setRate(null);

        // Create the TariffCharges, which fails.

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isBadRequest());

        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinKLIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffChargesRepository.findAll().size();
        // set the field null
        tariffCharges.setMinKL(null);

        // Create the TariffCharges, which fails.

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isBadRequest());

        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinUnmeteredKLIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffChargesRepository.findAll().size();
        // set the field null
        tariffCharges.setMinUnmeteredKL(null);

        // Create the TariffCharges, which fails.

        restTariffChargesMockMvc.perform(post("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isBadRequest());

        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTariffChargess() throws Exception {
        // Initialize the database
        tariffChargesRepository.saveAndFlush(tariffCharges);

        // Get all the tariffChargess
        restTariffChargesMockMvc.perform(get("/api/tariffChargess?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(tariffCharges.getId().intValue())))
                .andExpect(jsonPath("$.[*].tariffDesc").value(hasItem(DEFAULT_TARIFF_DESC.toString())))
                .andExpect(jsonPath("$.[*].slabMin").value(hasItem(DEFAULT_SLAB_MIN)))
                .andExpect(jsonPath("$.[*].slabMax").value(hasItem(DEFAULT_SLAB_MAX)))
                .andExpect(jsonPath("$.[*].rate").value(hasItem(DEFAULT_RATE.intValue())))
                .andExpect(jsonPath("$.[*].minKL").value(hasItem(DEFAULT_MIN_KL.intValue())))
                .andExpect(jsonPath("$.[*].minUnmeteredKL").value(hasItem(DEFAULT_MIN_UNMETERED_KL.intValue())));
    }

    @Test
    @Transactional
    public void getTariffCharges() throws Exception {
        // Initialize the database
        tariffChargesRepository.saveAndFlush(tariffCharges);

        // Get the tariffCharges
        restTariffChargesMockMvc.perform(get("/api/tariffChargess/{id}", tariffCharges.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(tariffCharges.getId().intValue()))
            .andExpect(jsonPath("$.tariffDesc").value(DEFAULT_TARIFF_DESC.toString()))
            .andExpect(jsonPath("$.slabMin").value(DEFAULT_SLAB_MIN))
            .andExpect(jsonPath("$.slabMax").value(DEFAULT_SLAB_MAX))
            .andExpect(jsonPath("$.rate").value(DEFAULT_RATE.intValue()))
            .andExpect(jsonPath("$.minKL").value(DEFAULT_MIN_KL.intValue()))
            .andExpect(jsonPath("$.minUnmeteredKL").value(DEFAULT_MIN_UNMETERED_KL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTariffCharges() throws Exception {
        // Get the tariffCharges
        restTariffChargesMockMvc.perform(get("/api/tariffChargess/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTariffCharges() throws Exception {
        // Initialize the database
        tariffChargesRepository.saveAndFlush(tariffCharges);

		int databaseSizeBeforeUpdate = tariffChargesRepository.findAll().size();

        // Update the tariffCharges
        tariffCharges.setTariffDesc(UPDATED_TARIFF_DESC);
        tariffCharges.setSlabMin(UPDATED_SLAB_MIN);
        tariffCharges.setSlabMax(UPDATED_SLAB_MAX);
        tariffCharges.setRate(UPDATED_RATE);
        tariffCharges.setMinKL(UPDATED_MIN_KL);
        tariffCharges.setMinUnmeteredKL(UPDATED_MIN_UNMETERED_KL);

        restTariffChargesMockMvc.perform(put("/api/tariffChargess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffCharges)))
                .andExpect(status().isOk());

        // Validate the TariffCharges in the database
        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeUpdate);
        TariffCharges testTariffCharges = tariffChargess.get(tariffChargess.size() - 1);
        assertThat(testTariffCharges.getTariffDesc()).isEqualTo(UPDATED_TARIFF_DESC);
        assertThat(testTariffCharges.getSlabMin()).isEqualTo(UPDATED_SLAB_MIN);
        assertThat(testTariffCharges.getSlabMax()).isEqualTo(UPDATED_SLAB_MAX);
        assertThat(testTariffCharges.getRate()).isEqualTo(UPDATED_RATE);
        assertThat(testTariffCharges.getMinKL()).isEqualTo(UPDATED_MIN_KL);
        assertThat(testTariffCharges.getMinUnmeteredKL()).isEqualTo(UPDATED_MIN_UNMETERED_KL);
    }

    @Test
    @Transactional
    public void deleteTariffCharges() throws Exception {
        // Initialize the database
        tariffChargesRepository.saveAndFlush(tariffCharges);

		int databaseSizeBeforeDelete = tariffChargesRepository.findAll().size();

        // Get the tariffCharges
        restTariffChargesMockMvc.perform(delete("/api/tariffChargess/{id}", tariffCharges.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TariffCharges> tariffChargess = tariffChargesRepository.findAll();
        assertThat(tariffChargess).hasSize(databaseSizeBeforeDelete - 1);
    }
}

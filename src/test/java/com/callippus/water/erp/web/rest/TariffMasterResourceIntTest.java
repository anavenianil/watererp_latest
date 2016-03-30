package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.TariffMaster;
import com.callippus.water.erp.repository.TariffMasterRepository;

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
 * Test class for the TariffMasterResource REST controller.
 *
 * @see TariffMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TariffMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_TARIFF_NAME = "AAAAA";
    private static final String UPDATED_TARIFF_NAME = "BBBBB";

    private static final ZonedDateTime DEFAULT_VALID_FROM = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_VALID_FROM = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_VALID_FROM_STR = dateTimeFormatter.format(DEFAULT_VALID_FROM);

    private static final ZonedDateTime DEFAULT_VALID_TO = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_VALID_TO = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_VALID_TO_STR = dateTimeFormatter.format(DEFAULT_VALID_TO);
    private static final String DEFAULT_ACTIVE = "AAAAA";
    private static final String UPDATED_ACTIVE = "BBBBB";

    @Inject
    private TariffMasterRepository tariffMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTariffMasterMockMvc;

    private TariffMaster tariffMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TariffMasterResource tariffMasterResource = new TariffMasterResource();
        ReflectionTestUtils.setField(tariffMasterResource, "tariffMasterRepository", tariffMasterRepository);
        this.restTariffMasterMockMvc = MockMvcBuilders.standaloneSetup(tariffMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        tariffMaster = new TariffMaster();
        tariffMaster.setTariffName(DEFAULT_TARIFF_NAME);
        tariffMaster.setValidFrom(DEFAULT_VALID_FROM);
        tariffMaster.setValidTo(DEFAULT_VALID_TO);
        tariffMaster.setActive(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void createTariffMaster() throws Exception {
        int databaseSizeBeforeCreate = tariffMasterRepository.findAll().size();

        // Create the TariffMaster

        restTariffMasterMockMvc.perform(post("/api/tariffMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffMaster)))
                .andExpect(status().isCreated());

        // Validate the TariffMaster in the database
        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeCreate + 1);
        TariffMaster testTariffMaster = tariffMasters.get(tariffMasters.size() - 1);
        assertThat(testTariffMaster.getTariffName()).isEqualTo(DEFAULT_TARIFF_NAME);
        assertThat(testTariffMaster.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
        assertThat(testTariffMaster.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
        assertThat(testTariffMaster.getActive()).isEqualTo(DEFAULT_ACTIVE);
    }

    @Test
    @Transactional
    public void checkTariffNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffMasterRepository.findAll().size();
        // set the field null
        tariffMaster.setTariffName(null);

        // Create the TariffMaster, which fails.

        restTariffMasterMockMvc.perform(post("/api/tariffMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffMaster)))
                .andExpect(status().isBadRequest());

        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValidFromIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffMasterRepository.findAll().size();
        // set the field null
        tariffMaster.setValidFrom(null);

        // Create the TariffMaster, which fails.

        restTariffMasterMockMvc.perform(post("/api/tariffMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffMaster)))
                .andExpect(status().isBadRequest());

        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValidToIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffMasterRepository.findAll().size();
        // set the field null
        tariffMaster.setValidTo(null);

        // Create the TariffMaster, which fails.

        restTariffMasterMockMvc.perform(post("/api/tariffMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffMaster)))
                .andExpect(status().isBadRequest());

        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffMasterRepository.findAll().size();
        // set the field null
        tariffMaster.setActive(null);

        // Create the TariffMaster, which fails.

        restTariffMasterMockMvc.perform(post("/api/tariffMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffMaster)))
                .andExpect(status().isBadRequest());

        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTariffMasters() throws Exception {
        // Initialize the database
        tariffMasterRepository.saveAndFlush(tariffMaster);

        // Get all the tariffMasters
        restTariffMasterMockMvc.perform(get("/api/tariffMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(tariffMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].tariffName").value(hasItem(DEFAULT_TARIFF_NAME.toString())))
                .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM_STR)))
                .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO_STR)))
                .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.toString())));
    }

    @Test
    @Transactional
    public void getTariffMaster() throws Exception {
        // Initialize the database
        tariffMasterRepository.saveAndFlush(tariffMaster);

        // Get the tariffMaster
        restTariffMasterMockMvc.perform(get("/api/tariffMasters/{id}", tariffMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(tariffMaster.getId().intValue()))
            .andExpect(jsonPath("$.tariffName").value(DEFAULT_TARIFF_NAME.toString()))
            .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM_STR))
            .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO_STR))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTariffMaster() throws Exception {
        // Get the tariffMaster
        restTariffMasterMockMvc.perform(get("/api/tariffMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTariffMaster() throws Exception {
        // Initialize the database
        tariffMasterRepository.saveAndFlush(tariffMaster);

		int databaseSizeBeforeUpdate = tariffMasterRepository.findAll().size();

        // Update the tariffMaster
        tariffMaster.setTariffName(UPDATED_TARIFF_NAME);
        tariffMaster.setValidFrom(UPDATED_VALID_FROM);
        tariffMaster.setValidTo(UPDATED_VALID_TO);
        tariffMaster.setActive(UPDATED_ACTIVE);

        restTariffMasterMockMvc.perform(put("/api/tariffMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffMaster)))
                .andExpect(status().isOk());

        // Validate the TariffMaster in the database
        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeUpdate);
        TariffMaster testTariffMaster = tariffMasters.get(tariffMasters.size() - 1);
        assertThat(testTariffMaster.getTariffName()).isEqualTo(UPDATED_TARIFF_NAME);
        assertThat(testTariffMaster.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
        assertThat(testTariffMaster.getValidTo()).isEqualTo(UPDATED_VALID_TO);
        assertThat(testTariffMaster.getActive()).isEqualTo(UPDATED_ACTIVE);
    }

    @Test
    @Transactional
    public void deleteTariffMaster() throws Exception {
        // Initialize the database
        tariffMasterRepository.saveAndFlush(tariffMaster);

		int databaseSizeBeforeDelete = tariffMasterRepository.findAll().size();

        // Get the tariffMaster
        restTariffMasterMockMvc.perform(delete("/api/tariffMasters/{id}", tariffMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TariffMaster> tariffMasters = tariffMasterRepository.findAll();
        assertThat(tariffMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

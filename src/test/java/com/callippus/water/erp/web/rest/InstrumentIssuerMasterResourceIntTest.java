package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.InstrumentIssuerMaster;
import com.callippus.water.erp.repository.InstrumentIssuerMasterRepository;

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
 * Test class for the InstrumentIssuerMasterResource REST controller.
 *
 * @see InstrumentIssuerMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class InstrumentIssuerMasterResourceIntTest {

    private static final String DEFAULT_INSTRUMENT_ISSUER = "AAAAA";
    private static final String UPDATED_INSTRUMENT_ISSUER = "BBBBB";

    @Inject
    private InstrumentIssuerMasterRepository instrumentIssuerMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInstrumentIssuerMasterMockMvc;

    private InstrumentIssuerMaster instrumentIssuerMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InstrumentIssuerMasterResource instrumentIssuerMasterResource = new InstrumentIssuerMasterResource();
        ReflectionTestUtils.setField(instrumentIssuerMasterResource, "instrumentIssuerMasterRepository", instrumentIssuerMasterRepository);
        this.restInstrumentIssuerMasterMockMvc = MockMvcBuilders.standaloneSetup(instrumentIssuerMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        instrumentIssuerMaster = new InstrumentIssuerMaster();
        instrumentIssuerMaster.setInstrumentIssuer(DEFAULT_INSTRUMENT_ISSUER);
    }

    @Test
    @Transactional
    public void createInstrumentIssuerMaster() throws Exception {
        int databaseSizeBeforeCreate = instrumentIssuerMasterRepository.findAll().size();

        // Create the InstrumentIssuerMaster

        restInstrumentIssuerMasterMockMvc.perform(post("/api/instrumentIssuerMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(instrumentIssuerMaster)))
                .andExpect(status().isCreated());

        // Validate the InstrumentIssuerMaster in the database
        List<InstrumentIssuerMaster> instrumentIssuerMasters = instrumentIssuerMasterRepository.findAll();
        assertThat(instrumentIssuerMasters).hasSize(databaseSizeBeforeCreate + 1);
        InstrumentIssuerMaster testInstrumentIssuerMaster = instrumentIssuerMasters.get(instrumentIssuerMasters.size() - 1);
        assertThat(testInstrumentIssuerMaster.getInstrumentIssuer()).isEqualTo(DEFAULT_INSTRUMENT_ISSUER);
    }

    @Test
    @Transactional
    public void checkInstrumentIssuerIsRequired() throws Exception {
        int databaseSizeBeforeTest = instrumentIssuerMasterRepository.findAll().size();
        // set the field null
        instrumentIssuerMaster.setInstrumentIssuer(null);

        // Create the InstrumentIssuerMaster, which fails.

        restInstrumentIssuerMasterMockMvc.perform(post("/api/instrumentIssuerMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(instrumentIssuerMaster)))
                .andExpect(status().isBadRequest());

        List<InstrumentIssuerMaster> instrumentIssuerMasters = instrumentIssuerMasterRepository.findAll();
        assertThat(instrumentIssuerMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllInstrumentIssuerMasters() throws Exception {
        // Initialize the database
        instrumentIssuerMasterRepository.saveAndFlush(instrumentIssuerMaster);

        // Get all the instrumentIssuerMasters
        restInstrumentIssuerMasterMockMvc.perform(get("/api/instrumentIssuerMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(instrumentIssuerMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].instrumentIssuer").value(hasItem(DEFAULT_INSTRUMENT_ISSUER.toString())));
    }

    @Test
    @Transactional
    public void getInstrumentIssuerMaster() throws Exception {
        // Initialize the database
        instrumentIssuerMasterRepository.saveAndFlush(instrumentIssuerMaster);

        // Get the instrumentIssuerMaster
        restInstrumentIssuerMasterMockMvc.perform(get("/api/instrumentIssuerMasters/{id}", instrumentIssuerMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(instrumentIssuerMaster.getId().intValue()))
            .andExpect(jsonPath("$.instrumentIssuer").value(DEFAULT_INSTRUMENT_ISSUER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInstrumentIssuerMaster() throws Exception {
        // Get the instrumentIssuerMaster
        restInstrumentIssuerMasterMockMvc.perform(get("/api/instrumentIssuerMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInstrumentIssuerMaster() throws Exception {
        // Initialize the database
        instrumentIssuerMasterRepository.saveAndFlush(instrumentIssuerMaster);

		int databaseSizeBeforeUpdate = instrumentIssuerMasterRepository.findAll().size();

        // Update the instrumentIssuerMaster
        instrumentIssuerMaster.setInstrumentIssuer(UPDATED_INSTRUMENT_ISSUER);

        restInstrumentIssuerMasterMockMvc.perform(put("/api/instrumentIssuerMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(instrumentIssuerMaster)))
                .andExpect(status().isOk());

        // Validate the InstrumentIssuerMaster in the database
        List<InstrumentIssuerMaster> instrumentIssuerMasters = instrumentIssuerMasterRepository.findAll();
        assertThat(instrumentIssuerMasters).hasSize(databaseSizeBeforeUpdate);
        InstrumentIssuerMaster testInstrumentIssuerMaster = instrumentIssuerMasters.get(instrumentIssuerMasters.size() - 1);
        assertThat(testInstrumentIssuerMaster.getInstrumentIssuer()).isEqualTo(UPDATED_INSTRUMENT_ISSUER);
    }

    @Test
    @Transactional
    public void deleteInstrumentIssuerMaster() throws Exception {
        // Initialize the database
        instrumentIssuerMasterRepository.saveAndFlush(instrumentIssuerMaster);

		int databaseSizeBeforeDelete = instrumentIssuerMasterRepository.findAll().size();

        // Get the instrumentIssuerMaster
        restInstrumentIssuerMasterMockMvc.perform(delete("/api/instrumentIssuerMasters/{id}", instrumentIssuerMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<InstrumentIssuerMaster> instrumentIssuerMasters = instrumentIssuerMasterRepository.findAll();
        assertThat(instrumentIssuerMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

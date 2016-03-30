package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.TariffTypeMaster;
import com.callippus.water.erp.repository.TariffTypeMasterRepository;

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
 * Test class for the TariffTypeMasterResource REST controller.
 *
 * @see TariffTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TariffTypeMasterResourceIntTest {

    private static final String DEFAULT_TARIFF_TYPE = "AAAAA";
    private static final String UPDATED_TARIFF_TYPE = "BBBBB";

    @Inject
    private TariffTypeMasterRepository tariffTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTariffTypeMasterMockMvc;

    private TariffTypeMaster tariffTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TariffTypeMasterResource tariffTypeMasterResource = new TariffTypeMasterResource();
        ReflectionTestUtils.setField(tariffTypeMasterResource, "tariffTypeMasterRepository", tariffTypeMasterRepository);
        this.restTariffTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(tariffTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        tariffTypeMaster = new TariffTypeMaster();
        tariffTypeMaster.setTariffType(DEFAULT_TARIFF_TYPE);
    }

    @Test
    @Transactional
    public void createTariffTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = tariffTypeMasterRepository.findAll().size();

        // Create the TariffTypeMaster

        restTariffTypeMasterMockMvc.perform(post("/api/tariffTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the TariffTypeMaster in the database
        List<TariffTypeMaster> tariffTypeMasters = tariffTypeMasterRepository.findAll();
        assertThat(tariffTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        TariffTypeMaster testTariffTypeMaster = tariffTypeMasters.get(tariffTypeMasters.size() - 1);
        assertThat(testTariffTypeMaster.getTariffType()).isEqualTo(DEFAULT_TARIFF_TYPE);
    }

    @Test
    @Transactional
    public void checkTariffTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = tariffTypeMasterRepository.findAll().size();
        // set the field null
        tariffTypeMaster.setTariffType(null);

        // Create the TariffTypeMaster, which fails.

        restTariffTypeMasterMockMvc.perform(post("/api/tariffTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffTypeMaster)))
                .andExpect(status().isBadRequest());

        List<TariffTypeMaster> tariffTypeMasters = tariffTypeMasterRepository.findAll();
        assertThat(tariffTypeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTariffTypeMasters() throws Exception {
        // Initialize the database
        tariffTypeMasterRepository.saveAndFlush(tariffTypeMaster);

        // Get all the tariffTypeMasters
        restTariffTypeMasterMockMvc.perform(get("/api/tariffTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(tariffTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].tariffType").value(hasItem(DEFAULT_TARIFF_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTariffTypeMaster() throws Exception {
        // Initialize the database
        tariffTypeMasterRepository.saveAndFlush(tariffTypeMaster);

        // Get the tariffTypeMaster
        restTariffTypeMasterMockMvc.perform(get("/api/tariffTypeMasters/{id}", tariffTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(tariffTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.tariffType").value(DEFAULT_TARIFF_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTariffTypeMaster() throws Exception {
        // Get the tariffTypeMaster
        restTariffTypeMasterMockMvc.perform(get("/api/tariffTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTariffTypeMaster() throws Exception {
        // Initialize the database
        tariffTypeMasterRepository.saveAndFlush(tariffTypeMaster);

		int databaseSizeBeforeUpdate = tariffTypeMasterRepository.findAll().size();

        // Update the tariffTypeMaster
        tariffTypeMaster.setTariffType(UPDATED_TARIFF_TYPE);

        restTariffTypeMasterMockMvc.perform(put("/api/tariffTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(tariffTypeMaster)))
                .andExpect(status().isOk());

        // Validate the TariffTypeMaster in the database
        List<TariffTypeMaster> tariffTypeMasters = tariffTypeMasterRepository.findAll();
        assertThat(tariffTypeMasters).hasSize(databaseSizeBeforeUpdate);
        TariffTypeMaster testTariffTypeMaster = tariffTypeMasters.get(tariffTypeMasters.size() - 1);
        assertThat(testTariffTypeMaster.getTariffType()).isEqualTo(UPDATED_TARIFF_TYPE);
    }

    @Test
    @Transactional
    public void deleteTariffTypeMaster() throws Exception {
        // Initialize the database
        tariffTypeMasterRepository.saveAndFlush(tariffTypeMaster);

		int databaseSizeBeforeDelete = tariffTypeMasterRepository.findAll().size();

        // Get the tariffTypeMaster
        restTariffTypeMasterMockMvc.perform(delete("/api/tariffTypeMasters/{id}", tariffTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TariffTypeMaster> tariffTypeMasters = tariffTypeMasterRepository.findAll();
        assertThat(tariffTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

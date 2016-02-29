package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ConfigurationDetails;
import com.callippus.water.erp.repository.ConfigurationDetailsRepository;

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
 * Test class for the ConfigurationDetailsResource REST controller.
 *
 * @see ConfigurationDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ConfigurationDetailsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_VALUE = "AAAAA";
    private static final String UPDATED_VALUE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private ConfigurationDetailsRepository configurationDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restConfigurationDetailsMockMvc;

    private ConfigurationDetails configurationDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConfigurationDetailsResource configurationDetailsResource = new ConfigurationDetailsResource();
        ReflectionTestUtils.setField(configurationDetailsResource, "configurationDetailsRepository", configurationDetailsRepository);
        this.restConfigurationDetailsMockMvc = MockMvcBuilders.standaloneSetup(configurationDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        configurationDetails = new ConfigurationDetails();
        configurationDetails.setName(DEFAULT_NAME);
        configurationDetails.setValue(DEFAULT_VALUE);
        configurationDetails.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createConfigurationDetails() throws Exception {
        int databaseSizeBeforeCreate = configurationDetailsRepository.findAll().size();

        // Create the ConfigurationDetails

        restConfigurationDetailsMockMvc.perform(post("/api/configurationDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(configurationDetails)))
                .andExpect(status().isCreated());

        // Validate the ConfigurationDetails in the database
        List<ConfigurationDetails> configurationDetailss = configurationDetailsRepository.findAll();
        assertThat(configurationDetailss).hasSize(databaseSizeBeforeCreate + 1);
        ConfigurationDetails testConfigurationDetails = configurationDetailss.get(configurationDetailss.size() - 1);
        assertThat(testConfigurationDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testConfigurationDetails.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testConfigurationDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = configurationDetailsRepository.findAll().size();
        // set the field null
        configurationDetails.setName(null);

        // Create the ConfigurationDetails, which fails.

        restConfigurationDetailsMockMvc.perform(post("/api/configurationDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(configurationDetails)))
                .andExpect(status().isBadRequest());

        List<ConfigurationDetails> configurationDetailss = configurationDetailsRepository.findAll();
        assertThat(configurationDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConfigurationDetailss() throws Exception {
        // Initialize the database
        configurationDetailsRepository.saveAndFlush(configurationDetails);

        // Get all the configurationDetailss
        restConfigurationDetailsMockMvc.perform(get("/api/configurationDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(configurationDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getConfigurationDetails() throws Exception {
        // Initialize the database
        configurationDetailsRepository.saveAndFlush(configurationDetails);

        // Get the configurationDetails
        restConfigurationDetailsMockMvc.perform(get("/api/configurationDetailss/{id}", configurationDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(configurationDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConfigurationDetails() throws Exception {
        // Get the configurationDetails
        restConfigurationDetailsMockMvc.perform(get("/api/configurationDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConfigurationDetails() throws Exception {
        // Initialize the database
        configurationDetailsRepository.saveAndFlush(configurationDetails);

		int databaseSizeBeforeUpdate = configurationDetailsRepository.findAll().size();

        // Update the configurationDetails
        configurationDetails.setName(UPDATED_NAME);
        configurationDetails.setValue(UPDATED_VALUE);
        configurationDetails.setDescription(UPDATED_DESCRIPTION);

        restConfigurationDetailsMockMvc.perform(put("/api/configurationDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(configurationDetails)))
                .andExpect(status().isOk());

        // Validate the ConfigurationDetails in the database
        List<ConfigurationDetails> configurationDetailss = configurationDetailsRepository.findAll();
        assertThat(configurationDetailss).hasSize(databaseSizeBeforeUpdate);
        ConfigurationDetails testConfigurationDetails = configurationDetailss.get(configurationDetailss.size() - 1);
        assertThat(testConfigurationDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testConfigurationDetails.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testConfigurationDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteConfigurationDetails() throws Exception {
        // Initialize the database
        configurationDetailsRepository.saveAndFlush(configurationDetails);

		int databaseSizeBeforeDelete = configurationDetailsRepository.findAll().size();

        // Get the configurationDetails
        restConfigurationDetailsMockMvc.perform(delete("/api/configurationDetailss/{id}", configurationDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ConfigurationDetails> configurationDetailss = configurationDetailsRepository.findAll();
        assertThat(configurationDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

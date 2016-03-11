package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MainWaterSize;
import com.callippus.water.erp.repository.MainWaterSizeRepository;

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
 * Test class for the MainWaterSizeResource REST controller.
 *
 * @see MainWaterSizeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MainWaterSizeResourceIntTest {


    private static final Float DEFAULT_SIZE = 1F;
    private static final Float UPDATED_SIZE = 2F;

    @Inject
    private MainWaterSizeRepository mainWaterSizeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMainWaterSizeMockMvc;

    private MainWaterSize mainWaterSize;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MainWaterSizeResource mainWaterSizeResource = new MainWaterSizeResource();
        ReflectionTestUtils.setField(mainWaterSizeResource, "mainWaterSizeRepository", mainWaterSizeRepository);
        this.restMainWaterSizeMockMvc = MockMvcBuilders.standaloneSetup(mainWaterSizeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        mainWaterSize = new MainWaterSize();
        mainWaterSize.setSize(DEFAULT_SIZE);
    }

    @Test
    @Transactional
    public void createMainWaterSize() throws Exception {
        int databaseSizeBeforeCreate = mainWaterSizeRepository.findAll().size();

        // Create the MainWaterSize

        restMainWaterSizeMockMvc.perform(post("/api/mainWaterSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mainWaterSize)))
                .andExpect(status().isCreated());

        // Validate the MainWaterSize in the database
        List<MainWaterSize> mainWaterSizes = mainWaterSizeRepository.findAll();
        assertThat(mainWaterSizes).hasSize(databaseSizeBeforeCreate + 1);
        MainWaterSize testMainWaterSize = mainWaterSizes.get(mainWaterSizes.size() - 1);
        assertThat(testMainWaterSize.getSize()).isEqualTo(DEFAULT_SIZE);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mainWaterSizeRepository.findAll().size();
        // set the field null
        mainWaterSize.setSize(null);

        // Create the MainWaterSize, which fails.

        restMainWaterSizeMockMvc.perform(post("/api/mainWaterSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mainWaterSize)))
                .andExpect(status().isBadRequest());

        List<MainWaterSize> mainWaterSizes = mainWaterSizeRepository.findAll();
        assertThat(mainWaterSizes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMainWaterSizes() throws Exception {
        // Initialize the database
        mainWaterSizeRepository.saveAndFlush(mainWaterSize);

        // Get all the mainWaterSizes
        restMainWaterSizeMockMvc.perform(get("/api/mainWaterSizes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(mainWaterSize.getId().intValue())))
                .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())));
    }

    @Test
    @Transactional
    public void getMainWaterSize() throws Exception {
        // Initialize the database
        mainWaterSizeRepository.saveAndFlush(mainWaterSize);

        // Get the mainWaterSize
        restMainWaterSizeMockMvc.perform(get("/api/mainWaterSizes/{id}", mainWaterSize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(mainWaterSize.getId().intValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMainWaterSize() throws Exception {
        // Get the mainWaterSize
        restMainWaterSizeMockMvc.perform(get("/api/mainWaterSizes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMainWaterSize() throws Exception {
        // Initialize the database
        mainWaterSizeRepository.saveAndFlush(mainWaterSize);

		int databaseSizeBeforeUpdate = mainWaterSizeRepository.findAll().size();

        // Update the mainWaterSize
        mainWaterSize.setSize(UPDATED_SIZE);

        restMainWaterSizeMockMvc.perform(put("/api/mainWaterSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mainWaterSize)))
                .andExpect(status().isOk());

        // Validate the MainWaterSize in the database
        List<MainWaterSize> mainWaterSizes = mainWaterSizeRepository.findAll();
        assertThat(mainWaterSizes).hasSize(databaseSizeBeforeUpdate);
        MainWaterSize testMainWaterSize = mainWaterSizes.get(mainWaterSizes.size() - 1);
        assertThat(testMainWaterSize.getSize()).isEqualTo(UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void deleteMainWaterSize() throws Exception {
        // Initialize the database
        mainWaterSizeRepository.saveAndFlush(mainWaterSize);

		int databaseSizeBeforeDelete = mainWaterSizeRepository.findAll().size();

        // Get the mainWaterSize
        restMainWaterSizeMockMvc.perform(delete("/api/mainWaterSizes/{id}", mainWaterSize.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MainWaterSize> mainWaterSizes = mainWaterSizeRepository.findAll();
        assertThat(mainWaterSizes).hasSize(databaseSizeBeforeDelete - 1);
    }
}

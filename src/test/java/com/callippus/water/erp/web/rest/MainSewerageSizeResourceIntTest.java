package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MainSewerageSize;
import com.callippus.water.erp.repository.MainSewerageSizeRepository;

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
 * Test class for the MainSewerageSizeResource REST controller.
 *
 * @see MainSewerageSizeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MainSewerageSizeResourceIntTest {


    private static final Float DEFAULT_SIZE = 1F;
    private static final Float UPDATED_SIZE = 2F;

    @Inject
    private MainSewerageSizeRepository mainSewerageSizeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMainSewerageSizeMockMvc;

    private MainSewerageSize mainSewerageSize;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MainSewerageSizeResource mainSewerageSizeResource = new MainSewerageSizeResource();
        ReflectionTestUtils.setField(mainSewerageSizeResource, "mainSewerageSizeRepository", mainSewerageSizeRepository);
        this.restMainSewerageSizeMockMvc = MockMvcBuilders.standaloneSetup(mainSewerageSizeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        mainSewerageSize = new MainSewerageSize();
        mainSewerageSize.setSize(DEFAULT_SIZE);
    }

    @Test
    @Transactional
    public void createMainSewerageSize() throws Exception {
        int databaseSizeBeforeCreate = mainSewerageSizeRepository.findAll().size();

        // Create the MainSewerageSize

        restMainSewerageSizeMockMvc.perform(post("/api/mainSewerageSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mainSewerageSize)))
                .andExpect(status().isCreated());

        // Validate the MainSewerageSize in the database
        List<MainSewerageSize> mainSewerageSizes = mainSewerageSizeRepository.findAll();
        assertThat(mainSewerageSizes).hasSize(databaseSizeBeforeCreate + 1);
        MainSewerageSize testMainSewerageSize = mainSewerageSizes.get(mainSewerageSizes.size() - 1);
        assertThat(testMainSewerageSize.getSize()).isEqualTo(DEFAULT_SIZE);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = mainSewerageSizeRepository.findAll().size();
        // set the field null
        mainSewerageSize.setSize(null);

        // Create the MainSewerageSize, which fails.

        restMainSewerageSizeMockMvc.perform(post("/api/mainSewerageSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mainSewerageSize)))
                .andExpect(status().isBadRequest());

        List<MainSewerageSize> mainSewerageSizes = mainSewerageSizeRepository.findAll();
        assertThat(mainSewerageSizes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMainSewerageSizes() throws Exception {
        // Initialize the database
        mainSewerageSizeRepository.saveAndFlush(mainSewerageSize);

        // Get all the mainSewerageSizes
        restMainSewerageSizeMockMvc.perform(get("/api/mainSewerageSizes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(mainSewerageSize.getId().intValue())))
                .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())));
    }

    @Test
    @Transactional
    public void getMainSewerageSize() throws Exception {
        // Initialize the database
        mainSewerageSizeRepository.saveAndFlush(mainSewerageSize);

        // Get the mainSewerageSize
        restMainSewerageSizeMockMvc.perform(get("/api/mainSewerageSizes/{id}", mainSewerageSize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(mainSewerageSize.getId().intValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMainSewerageSize() throws Exception {
        // Get the mainSewerageSize
        restMainSewerageSizeMockMvc.perform(get("/api/mainSewerageSizes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMainSewerageSize() throws Exception {
        // Initialize the database
        mainSewerageSizeRepository.saveAndFlush(mainSewerageSize);

		int databaseSizeBeforeUpdate = mainSewerageSizeRepository.findAll().size();

        // Update the mainSewerageSize
        mainSewerageSize.setSize(UPDATED_SIZE);

        restMainSewerageSizeMockMvc.perform(put("/api/mainSewerageSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(mainSewerageSize)))
                .andExpect(status().isOk());

        // Validate the MainSewerageSize in the database
        List<MainSewerageSize> mainSewerageSizes = mainSewerageSizeRepository.findAll();
        assertThat(mainSewerageSizes).hasSize(databaseSizeBeforeUpdate);
        MainSewerageSize testMainSewerageSize = mainSewerageSizes.get(mainSewerageSizes.size() - 1);
        assertThat(testMainSewerageSize.getSize()).isEqualTo(UPDATED_SIZE);
    }

    @Test
    @Transactional
    public void deleteMainSewerageSize() throws Exception {
        // Initialize the database
        mainSewerageSizeRepository.saveAndFlush(mainSewerageSize);

		int databaseSizeBeforeDelete = mainSewerageSizeRepository.findAll().size();

        // Get the mainSewerageSize
        restMainSewerageSizeMockMvc.perform(delete("/api/mainSewerageSizes/{id}", mainSewerageSize.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MainSewerageSize> mainSewerageSizes = mainSewerageSizeRepository.findAll();
        assertThat(mainSewerageSizes).hasSize(databaseSizeBeforeDelete - 1);
    }
}

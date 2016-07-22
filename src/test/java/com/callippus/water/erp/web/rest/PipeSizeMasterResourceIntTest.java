package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.PipeSizeMaster;
import com.callippus.water.erp.repository.PipeSizeMasterRepository;

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
 * Test class for the PipeSizeMasterResource REST controller.
 *
 * @see PipeSizeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PipeSizeMasterResourceIntTest {


    private static final Float DEFAULT_PIPE_SIZE = 1F;
    private static final Float UPDATED_PIPE_SIZE = 2F;

    @Inject
    private PipeSizeMasterRepository pipeSizeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPipeSizeMasterMockMvc;

    private PipeSizeMaster pipeSizeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PipeSizeMasterResource pipeSizeMasterResource = new PipeSizeMasterResource();
        ReflectionTestUtils.setField(pipeSizeMasterResource, "pipeSizeMasterRepository", pipeSizeMasterRepository);
        this.restPipeSizeMasterMockMvc = MockMvcBuilders.standaloneSetup(pipeSizeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        pipeSizeMaster = new PipeSizeMaster();
        pipeSizeMaster.setPipeSize(DEFAULT_PIPE_SIZE);
    }

    @Test
    @Transactional
    public void createPipeSizeMaster() throws Exception {
        int databaseSizeBeforeCreate = pipeSizeMasterRepository.findAll().size();

        // Create the PipeSizeMaster

        restPipeSizeMasterMockMvc.perform(post("/api/pipeSizeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pipeSizeMaster)))
                .andExpect(status().isCreated());

        // Validate the PipeSizeMaster in the database
        List<PipeSizeMaster> pipeSizeMasters = pipeSizeMasterRepository.findAll();
        assertThat(pipeSizeMasters).hasSize(databaseSizeBeforeCreate + 1);
        PipeSizeMaster testPipeSizeMaster = pipeSizeMasters.get(pipeSizeMasters.size() - 1);
        assertThat(testPipeSizeMaster.getPipeSize()).isEqualTo(DEFAULT_PIPE_SIZE);
    }

    @Test
    @Transactional
    public void checkPipeSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = pipeSizeMasterRepository.findAll().size();
        // set the field null
        pipeSizeMaster.setPipeSize(null);

        // Create the PipeSizeMaster, which fails.

        restPipeSizeMasterMockMvc.perform(post("/api/pipeSizeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pipeSizeMaster)))
                .andExpect(status().isBadRequest());

        List<PipeSizeMaster> pipeSizeMasters = pipeSizeMasterRepository.findAll();
        assertThat(pipeSizeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPipeSizeMasters() throws Exception {
        // Initialize the database
        pipeSizeMasterRepository.saveAndFlush(pipeSizeMaster);

        // Get all the pipeSizeMasters
        restPipeSizeMasterMockMvc.perform(get("/api/pipeSizeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(pipeSizeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].pipeSize").value(hasItem(DEFAULT_PIPE_SIZE.doubleValue())));
    }

    @Test
    @Transactional
    public void getPipeSizeMaster() throws Exception {
        // Initialize the database
        pipeSizeMasterRepository.saveAndFlush(pipeSizeMaster);

        // Get the pipeSizeMaster
        restPipeSizeMasterMockMvc.perform(get("/api/pipeSizeMasters/{id}", pipeSizeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(pipeSizeMaster.getId().intValue()))
            .andExpect(jsonPath("$.pipeSize").value(DEFAULT_PIPE_SIZE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPipeSizeMaster() throws Exception {
        // Get the pipeSizeMaster
        restPipeSizeMasterMockMvc.perform(get("/api/pipeSizeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePipeSizeMaster() throws Exception {
        // Initialize the database
        pipeSizeMasterRepository.saveAndFlush(pipeSizeMaster);

		int databaseSizeBeforeUpdate = pipeSizeMasterRepository.findAll().size();

        // Update the pipeSizeMaster
        pipeSizeMaster.setPipeSize(UPDATED_PIPE_SIZE);

        restPipeSizeMasterMockMvc.perform(put("/api/pipeSizeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(pipeSizeMaster)))
                .andExpect(status().isOk());

        // Validate the PipeSizeMaster in the database
        List<PipeSizeMaster> pipeSizeMasters = pipeSizeMasterRepository.findAll();
        assertThat(pipeSizeMasters).hasSize(databaseSizeBeforeUpdate);
        PipeSizeMaster testPipeSizeMaster = pipeSizeMasters.get(pipeSizeMasters.size() - 1);
        assertThat(testPipeSizeMaster.getPipeSize()).isEqualTo(UPDATED_PIPE_SIZE);
    }

    @Test
    @Transactional
    public void deletePipeSizeMaster() throws Exception {
        // Initialize the database
        pipeSizeMasterRepository.saveAndFlush(pipeSizeMaster);

		int databaseSizeBeforeDelete = pipeSizeMasterRepository.findAll().size();

        // Get the pipeSizeMaster
        restPipeSizeMasterMockMvc.perform(delete("/api/pipeSizeMasters/{id}", pipeSizeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PipeSizeMaster> pipeSizeMasters = pipeSizeMasterRepository.findAll();
        assertThat(pipeSizeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.SewerSize;
import com.callippus.water.erp.repository.SewerSizeRepository;

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
 * Test class for the SewerSizeResource REST controller.
 *
 * @see SewerSizeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SewerSizeResourceIntTest {

    private static final String DEFAULT_SEWER_SIZE = "AAAAA";
    private static final String UPDATED_SEWER_SIZE = "BBBBB";

    @Inject
    private SewerSizeRepository sewerSizeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSewerSizeMockMvc;

    private SewerSize sewerSize;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SewerSizeResource sewerSizeResource = new SewerSizeResource();
        ReflectionTestUtils.setField(sewerSizeResource, "sewerSizeRepository", sewerSizeRepository);
        this.restSewerSizeMockMvc = MockMvcBuilders.standaloneSetup(sewerSizeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        sewerSize = new SewerSize();
        sewerSize.setSewerSize(DEFAULT_SEWER_SIZE);
    }

    @Test
    @Transactional
    public void createSewerSize() throws Exception {
        int databaseSizeBeforeCreate = sewerSizeRepository.findAll().size();

        // Create the SewerSize

        restSewerSizeMockMvc.perform(post("/api/sewerSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerSize)))
                .andExpect(status().isCreated());

        // Validate the SewerSize in the database
        List<SewerSize> sewerSizes = sewerSizeRepository.findAll();
        assertThat(sewerSizes).hasSize(databaseSizeBeforeCreate + 1);
        SewerSize testSewerSize = sewerSizes.get(sewerSizes.size() - 1);
        assertThat(testSewerSize.getSewerSize()).isEqualTo(DEFAULT_SEWER_SIZE);
    }

    @Test
    @Transactional
    public void checkSewerSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sewerSizeRepository.findAll().size();
        // set the field null
        sewerSize.setSewerSize(null);

        // Create the SewerSize, which fails.

        restSewerSizeMockMvc.perform(post("/api/sewerSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerSize)))
                .andExpect(status().isBadRequest());

        List<SewerSize> sewerSizes = sewerSizeRepository.findAll();
        assertThat(sewerSizes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSewerSizes() throws Exception {
        // Initialize the database
        sewerSizeRepository.saveAndFlush(sewerSize);

        // Get all the sewerSizes
        restSewerSizeMockMvc.perform(get("/api/sewerSizes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(sewerSize.getId().intValue())))
                .andExpect(jsonPath("$.[*].sewerSize").value(hasItem(DEFAULT_SEWER_SIZE.toString())));
    }

    @Test
    @Transactional
    public void getSewerSize() throws Exception {
        // Initialize the database
        sewerSizeRepository.saveAndFlush(sewerSize);

        // Get the sewerSize
        restSewerSizeMockMvc.perform(get("/api/sewerSizes/{id}", sewerSize.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(sewerSize.getId().intValue()))
            .andExpect(jsonPath("$.sewerSize").value(DEFAULT_SEWER_SIZE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSewerSize() throws Exception {
        // Get the sewerSize
        restSewerSizeMockMvc.perform(get("/api/sewerSizes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSewerSize() throws Exception {
        // Initialize the database
        sewerSizeRepository.saveAndFlush(sewerSize);

		int databaseSizeBeforeUpdate = sewerSizeRepository.findAll().size();

        // Update the sewerSize
        sewerSize.setSewerSize(UPDATED_SEWER_SIZE);

        restSewerSizeMockMvc.perform(put("/api/sewerSizes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerSize)))
                .andExpect(status().isOk());

        // Validate the SewerSize in the database
        List<SewerSize> sewerSizes = sewerSizeRepository.findAll();
        assertThat(sewerSizes).hasSize(databaseSizeBeforeUpdate);
        SewerSize testSewerSize = sewerSizes.get(sewerSizes.size() - 1);
        assertThat(testSewerSize.getSewerSize()).isEqualTo(UPDATED_SEWER_SIZE);
    }

    @Test
    @Transactional
    public void deleteSewerSize() throws Exception {
        // Initialize the database
        sewerSizeRepository.saveAndFlush(sewerSize);

		int databaseSizeBeforeDelete = sewerSizeRepository.findAll().size();

        // Get the sewerSize
        restSewerSizeMockMvc.perform(delete("/api/sewerSizes/{id}", sewerSize.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SewerSize> sewerSizes = sewerSizeRepository.findAll();
        assertThat(sewerSizes).hasSize(databaseSizeBeforeDelete - 1);
    }
}

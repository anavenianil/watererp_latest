package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.RevenueTypeMaster;
import com.callippus.water.erp.repository.RevenueTypeMasterRepository;

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
 * Test class for the RevenueTypeMasterResource REST controller.
 *
 * @see RevenueTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RevenueTypeMasterResourceIntTest {

    private static final String DEFAULT_REVENUE_TYPE = "AAAAA";
    private static final String UPDATED_REVENUE_TYPE = "BBBBB";

    @Inject
    private RevenueTypeMasterRepository revenueTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRevenueTypeMasterMockMvc;

    private RevenueTypeMaster revenueTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RevenueTypeMasterResource revenueTypeMasterResource = new RevenueTypeMasterResource();
        ReflectionTestUtils.setField(revenueTypeMasterResource, "revenueTypeMasterRepository", revenueTypeMasterRepository);
        this.restRevenueTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(revenueTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        revenueTypeMaster = new RevenueTypeMaster();
        revenueTypeMaster.setRevenueType(DEFAULT_REVENUE_TYPE);
    }

    @Test
    @Transactional
    public void createRevenueTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = revenueTypeMasterRepository.findAll().size();

        // Create the RevenueTypeMaster

        restRevenueTypeMasterMockMvc.perform(post("/api/revenueTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(revenueTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the RevenueTypeMaster in the database
        List<RevenueTypeMaster> revenueTypeMasters = revenueTypeMasterRepository.findAll();
        assertThat(revenueTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        RevenueTypeMaster testRevenueTypeMaster = revenueTypeMasters.get(revenueTypeMasters.size() - 1);
        assertThat(testRevenueTypeMaster.getRevenueType()).isEqualTo(DEFAULT_REVENUE_TYPE);
    }

    @Test
    @Transactional
    public void checkRevenueTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = revenueTypeMasterRepository.findAll().size();
        // set the field null
        revenueTypeMaster.setRevenueType(null);

        // Create the RevenueTypeMaster, which fails.

        restRevenueTypeMasterMockMvc.perform(post("/api/revenueTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(revenueTypeMaster)))
                .andExpect(status().isBadRequest());

        List<RevenueTypeMaster> revenueTypeMasters = revenueTypeMasterRepository.findAll();
        assertThat(revenueTypeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRevenueTypeMasters() throws Exception {
        // Initialize the database
        revenueTypeMasterRepository.saveAndFlush(revenueTypeMaster);

        // Get all the revenueTypeMasters
        restRevenueTypeMasterMockMvc.perform(get("/api/revenueTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(revenueTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].revenueType").value(hasItem(DEFAULT_REVENUE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getRevenueTypeMaster() throws Exception {
        // Initialize the database
        revenueTypeMasterRepository.saveAndFlush(revenueTypeMaster);

        // Get the revenueTypeMaster
        restRevenueTypeMasterMockMvc.perform(get("/api/revenueTypeMasters/{id}", revenueTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(revenueTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.revenueType").value(DEFAULT_REVENUE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRevenueTypeMaster() throws Exception {
        // Get the revenueTypeMaster
        restRevenueTypeMasterMockMvc.perform(get("/api/revenueTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRevenueTypeMaster() throws Exception {
        // Initialize the database
        revenueTypeMasterRepository.saveAndFlush(revenueTypeMaster);

		int databaseSizeBeforeUpdate = revenueTypeMasterRepository.findAll().size();

        // Update the revenueTypeMaster
        revenueTypeMaster.setRevenueType(UPDATED_REVENUE_TYPE);

        restRevenueTypeMasterMockMvc.perform(put("/api/revenueTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(revenueTypeMaster)))
                .andExpect(status().isOk());

        // Validate the RevenueTypeMaster in the database
        List<RevenueTypeMaster> revenueTypeMasters = revenueTypeMasterRepository.findAll();
        assertThat(revenueTypeMasters).hasSize(databaseSizeBeforeUpdate);
        RevenueTypeMaster testRevenueTypeMaster = revenueTypeMasters.get(revenueTypeMasters.size() - 1);
        assertThat(testRevenueTypeMaster.getRevenueType()).isEqualTo(UPDATED_REVENUE_TYPE);
    }

    @Test
    @Transactional
    public void deleteRevenueTypeMaster() throws Exception {
        // Initialize the database
        revenueTypeMasterRepository.saveAndFlush(revenueTypeMaster);

		int databaseSizeBeforeDelete = revenueTypeMasterRepository.findAll().size();

        // Get the revenueTypeMaster
        restRevenueTypeMasterMockMvc.perform(delete("/api/revenueTypeMasters/{id}", revenueTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RevenueTypeMaster> revenueTypeMasters = revenueTypeMasterRepository.findAll();
        assertThat(revenueTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

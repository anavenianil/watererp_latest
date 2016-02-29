package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.FeasibilityStatus;
import com.callippus.water.erp.repository.FeasibilityStatusRepository;

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
 * Test class for the FeasibilityStatusResource REST controller.
 *
 * @see FeasibilityStatusResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FeasibilityStatusResourceIntTest {

    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private FeasibilityStatusRepository feasibilityStatusRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFeasibilityStatusMockMvc;

    private FeasibilityStatus feasibilityStatus;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FeasibilityStatusResource feasibilityStatusResource = new FeasibilityStatusResource();
        ReflectionTestUtils.setField(feasibilityStatusResource, "feasibilityStatusRepository", feasibilityStatusRepository);
        this.restFeasibilityStatusMockMvc = MockMvcBuilders.standaloneSetup(feasibilityStatusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        feasibilityStatus = new FeasibilityStatus();
        feasibilityStatus.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFeasibilityStatus() throws Exception {
        int databaseSizeBeforeCreate = feasibilityStatusRepository.findAll().size();

        // Create the FeasibilityStatus

        restFeasibilityStatusMockMvc.perform(post("/api/feasibilityStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(feasibilityStatus)))
                .andExpect(status().isCreated());

        // Validate the FeasibilityStatus in the database
        List<FeasibilityStatus> feasibilityStatuss = feasibilityStatusRepository.findAll();
        assertThat(feasibilityStatuss).hasSize(databaseSizeBeforeCreate + 1);
        FeasibilityStatus testFeasibilityStatus = feasibilityStatuss.get(feasibilityStatuss.size() - 1);
        assertThat(testFeasibilityStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllFeasibilityStatuss() throws Exception {
        // Initialize the database
        feasibilityStatusRepository.saveAndFlush(feasibilityStatus);

        // Get all the feasibilityStatuss
        restFeasibilityStatusMockMvc.perform(get("/api/feasibilityStatuss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(feasibilityStatus.getId().intValue())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getFeasibilityStatus() throws Exception {
        // Initialize the database
        feasibilityStatusRepository.saveAndFlush(feasibilityStatus);

        // Get the feasibilityStatus
        restFeasibilityStatusMockMvc.perform(get("/api/feasibilityStatuss/{id}", feasibilityStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(feasibilityStatus.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFeasibilityStatus() throws Exception {
        // Get the feasibilityStatus
        restFeasibilityStatusMockMvc.perform(get("/api/feasibilityStatuss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeasibilityStatus() throws Exception {
        // Initialize the database
        feasibilityStatusRepository.saveAndFlush(feasibilityStatus);

		int databaseSizeBeforeUpdate = feasibilityStatusRepository.findAll().size();

        // Update the feasibilityStatus
        feasibilityStatus.setStatus(UPDATED_STATUS);

        restFeasibilityStatusMockMvc.perform(put("/api/feasibilityStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(feasibilityStatus)))
                .andExpect(status().isOk());

        // Validate the FeasibilityStatus in the database
        List<FeasibilityStatus> feasibilityStatuss = feasibilityStatusRepository.findAll();
        assertThat(feasibilityStatuss).hasSize(databaseSizeBeforeUpdate);
        FeasibilityStatus testFeasibilityStatus = feasibilityStatuss.get(feasibilityStatuss.size() - 1);
        assertThat(testFeasibilityStatus.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteFeasibilityStatus() throws Exception {
        // Initialize the database
        feasibilityStatusRepository.saveAndFlush(feasibilityStatus);

		int databaseSizeBeforeDelete = feasibilityStatusRepository.findAll().size();

        // Get the feasibilityStatus
        restFeasibilityStatusMockMvc.perform(delete("/api/feasibilityStatuss/{id}", feasibilityStatus.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<FeasibilityStatus> feasibilityStatuss = feasibilityStatusRepository.findAll();
        assertThat(feasibilityStatuss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

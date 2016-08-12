package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.HydrantComplaint;
import com.callippus.water.erp.repository.HydrantComplaintRepository;

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
 * Test class for the HydrantComplaintResource REST controller.
 *
 * @see HydrantComplaintResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class HydrantComplaintResourceIntTest {

    private static final String DEFAULT_FROM_LEFT = "AAAAA";
    private static final String UPDATED_FROM_LEFT = "BBBBB";
    private static final String DEFAULT_FROM_SB = "AAAAA";
    private static final String UPDATED_FROM_SB = "BBBBB";
    private static final String DEFAULT_PROBLEM_AT = "AAAAA";
    private static final String UPDATED_PROBLEM_AT = "BBBBB";
    private static final String DEFAULT_ACTIVITY_TYPE = "AAAAA";
    private static final String UPDATED_ACTIVITY_TYPE = "BBBBB";
    private static final String DEFAULT_PRESSURE = "AAAAA";
    private static final String UPDATED_PRESSURE = "BBBBB";

    private static final Integer DEFAULT_PRESSURE_TIME = 1;
    private static final Integer UPDATED_PRESSURE_TIME = 2;
    private static final String DEFAULT_FLOW = "AAAAA";
    private static final String UPDATED_FLOW = "BBBBB";

    private static final Integer DEFAULT_FLOW_TIME = 1;
    private static final Integer UPDATED_FLOW_TIME = 2;

    @Inject
    private HydrantComplaintRepository hydrantComplaintRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restHydrantComplaintMockMvc;

    private HydrantComplaint hydrantComplaint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HydrantComplaintResource hydrantComplaintResource = new HydrantComplaintResource();
        ReflectionTestUtils.setField(hydrantComplaintResource, "hydrantComplaintRepository", hydrantComplaintRepository);
        this.restHydrantComplaintMockMvc = MockMvcBuilders.standaloneSetup(hydrantComplaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        hydrantComplaint = new HydrantComplaint();
        hydrantComplaint.setFromLeft(DEFAULT_FROM_LEFT);
        hydrantComplaint.setFromSb(DEFAULT_FROM_SB);
        hydrantComplaint.setProblemAt(DEFAULT_PROBLEM_AT);
        hydrantComplaint.setActivityType(DEFAULT_ACTIVITY_TYPE);
        hydrantComplaint.setPressure(DEFAULT_PRESSURE);
        hydrantComplaint.setPressureTime(DEFAULT_PRESSURE_TIME);
        hydrantComplaint.setFlow(DEFAULT_FLOW);
        hydrantComplaint.setFlowTime(DEFAULT_FLOW_TIME);
    }

    @Test
    @Transactional
    public void createHydrantComplaint() throws Exception {
        int databaseSizeBeforeCreate = hydrantComplaintRepository.findAll().size();

        // Create the HydrantComplaint

        restHydrantComplaintMockMvc.perform(post("/api/hydrantComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hydrantComplaint)))
                .andExpect(status().isCreated());

        // Validate the HydrantComplaint in the database
        List<HydrantComplaint> hydrantComplaints = hydrantComplaintRepository.findAll();
        assertThat(hydrantComplaints).hasSize(databaseSizeBeforeCreate + 1);
        HydrantComplaint testHydrantComplaint = hydrantComplaints.get(hydrantComplaints.size() - 1);
        assertThat(testHydrantComplaint.getFromLeft()).isEqualTo(DEFAULT_FROM_LEFT);
        assertThat(testHydrantComplaint.getFromSb()).isEqualTo(DEFAULT_FROM_SB);
        assertThat(testHydrantComplaint.getProblemAt()).isEqualTo(DEFAULT_PROBLEM_AT);
        assertThat(testHydrantComplaint.getActivityType()).isEqualTo(DEFAULT_ACTIVITY_TYPE);
        assertThat(testHydrantComplaint.getPressure()).isEqualTo(DEFAULT_PRESSURE);
        assertThat(testHydrantComplaint.getPressureTime()).isEqualTo(DEFAULT_PRESSURE_TIME);
        assertThat(testHydrantComplaint.getFlow()).isEqualTo(DEFAULT_FLOW);
        assertThat(testHydrantComplaint.getFlowTime()).isEqualTo(DEFAULT_FLOW_TIME);
    }

    @Test
    @Transactional
    public void getAllHydrantComplaints() throws Exception {
        // Initialize the database
        hydrantComplaintRepository.saveAndFlush(hydrantComplaint);

        // Get all the hydrantComplaints
        restHydrantComplaintMockMvc.perform(get("/api/hydrantComplaints?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(hydrantComplaint.getId().intValue())))
                .andExpect(jsonPath("$.[*].fromLeft").value(hasItem(DEFAULT_FROM_LEFT.toString())))
                .andExpect(jsonPath("$.[*].fromSb").value(hasItem(DEFAULT_FROM_SB.toString())))
                .andExpect(jsonPath("$.[*].problemAt").value(hasItem(DEFAULT_PROBLEM_AT.toString())))
                .andExpect(jsonPath("$.[*].activityType").value(hasItem(DEFAULT_ACTIVITY_TYPE.toString())))
                .andExpect(jsonPath("$.[*].pressure").value(hasItem(DEFAULT_PRESSURE.toString())))
                .andExpect(jsonPath("$.[*].pressureTime").value(hasItem(DEFAULT_PRESSURE_TIME)))
                .andExpect(jsonPath("$.[*].flow").value(hasItem(DEFAULT_FLOW.toString())))
                .andExpect(jsonPath("$.[*].flowTime").value(hasItem(DEFAULT_FLOW_TIME)));
    }

    @Test
    @Transactional
    public void getHydrantComplaint() throws Exception {
        // Initialize the database
        hydrantComplaintRepository.saveAndFlush(hydrantComplaint);

        // Get the hydrantComplaint
        restHydrantComplaintMockMvc.perform(get("/api/hydrantComplaints/{id}", hydrantComplaint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(hydrantComplaint.getId().intValue()))
            .andExpect(jsonPath("$.fromLeft").value(DEFAULT_FROM_LEFT.toString()))
            .andExpect(jsonPath("$.fromSb").value(DEFAULT_FROM_SB.toString()))
            .andExpect(jsonPath("$.problemAt").value(DEFAULT_PROBLEM_AT.toString()))
            .andExpect(jsonPath("$.activityType").value(DEFAULT_ACTIVITY_TYPE.toString()))
            .andExpect(jsonPath("$.pressure").value(DEFAULT_PRESSURE.toString()))
            .andExpect(jsonPath("$.pressureTime").value(DEFAULT_PRESSURE_TIME))
            .andExpect(jsonPath("$.flow").value(DEFAULT_FLOW.toString()))
            .andExpect(jsonPath("$.flowTime").value(DEFAULT_FLOW_TIME));
    }

    @Test
    @Transactional
    public void getNonExistingHydrantComplaint() throws Exception {
        // Get the hydrantComplaint
        restHydrantComplaintMockMvc.perform(get("/api/hydrantComplaints/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHydrantComplaint() throws Exception {
        // Initialize the database
        hydrantComplaintRepository.saveAndFlush(hydrantComplaint);

		int databaseSizeBeforeUpdate = hydrantComplaintRepository.findAll().size();

        // Update the hydrantComplaint
        hydrantComplaint.setFromLeft(UPDATED_FROM_LEFT);
        hydrantComplaint.setFromSb(UPDATED_FROM_SB);
        hydrantComplaint.setProblemAt(UPDATED_PROBLEM_AT);
        hydrantComplaint.setActivityType(UPDATED_ACTIVITY_TYPE);
        hydrantComplaint.setPressure(UPDATED_PRESSURE);
        hydrantComplaint.setPressureTime(UPDATED_PRESSURE_TIME);
        hydrantComplaint.setFlow(UPDATED_FLOW);
        hydrantComplaint.setFlowTime(UPDATED_FLOW_TIME);

        restHydrantComplaintMockMvc.perform(put("/api/hydrantComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hydrantComplaint)))
                .andExpect(status().isOk());

        // Validate the HydrantComplaint in the database
        List<HydrantComplaint> hydrantComplaints = hydrantComplaintRepository.findAll();
        assertThat(hydrantComplaints).hasSize(databaseSizeBeforeUpdate);
        HydrantComplaint testHydrantComplaint = hydrantComplaints.get(hydrantComplaints.size() - 1);
        assertThat(testHydrantComplaint.getFromLeft()).isEqualTo(UPDATED_FROM_LEFT);
        assertThat(testHydrantComplaint.getFromSb()).isEqualTo(UPDATED_FROM_SB);
        assertThat(testHydrantComplaint.getProblemAt()).isEqualTo(UPDATED_PROBLEM_AT);
        assertThat(testHydrantComplaint.getActivityType()).isEqualTo(UPDATED_ACTIVITY_TYPE);
        assertThat(testHydrantComplaint.getPressure()).isEqualTo(UPDATED_PRESSURE);
        assertThat(testHydrantComplaint.getPressureTime()).isEqualTo(UPDATED_PRESSURE_TIME);
        assertThat(testHydrantComplaint.getFlow()).isEqualTo(UPDATED_FLOW);
        assertThat(testHydrantComplaint.getFlowTime()).isEqualTo(UPDATED_FLOW_TIME);
    }

    @Test
    @Transactional
    public void deleteHydrantComplaint() throws Exception {
        // Initialize the database
        hydrantComplaintRepository.saveAndFlush(hydrantComplaint);

		int databaseSizeBeforeDelete = hydrantComplaintRepository.findAll().size();

        // Get the hydrantComplaint
        restHydrantComplaintMockMvc.perform(delete("/api/hydrantComplaints/{id}", hydrantComplaint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<HydrantComplaint> hydrantComplaints = hydrantComplaintRepository.findAll();
        assertThat(hydrantComplaints).hasSize(databaseSizeBeforeDelete - 1);
    }
}

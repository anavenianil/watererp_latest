package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BurstComplaint;
import com.callippus.water.erp.repository.BurstComplaintRepository;

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
 * Test class for the BurstComplaintResource REST controller.
 *
 * @see BurstComplaintResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BurstComplaintResourceIntTest {

    private static final String DEFAULT_METER_LOCATION = "AAAAA";
    private static final String UPDATED_METER_LOCATION = "BBBBB";
    private static final String DEFAULT_BURST_PIPE = "AAAAA";
    private static final String UPDATED_BURST_PIPE = "BBBBB";
    private static final String DEFAULT_FROM_LEFT = "AAAAA";
    private static final String UPDATED_FROM_LEFT = "BBBBB";
    private static final String DEFAULT_FROM_SB = "AAAAA";
    private static final String UPDATED_FROM_SB = "BBBBB";
    private static final String DEFAULT_BURST_AREA = "AAAAA";
    private static final String UPDATED_BURST_AREA = "BBBBB";
    private static final String DEFAULT_PIPE_TYPE = "AAAAA";
    private static final String UPDATED_PIPE_TYPE = "BBBBB";

    private static final Integer DEFAULT_PIPE_SIZE = 1;
    private static final Integer UPDATED_PIPE_SIZE = 2;

    private static final Integer DEFAULT_HOLE_SIZE = 1;
    private static final Integer UPDATED_HOLE_SIZE = 2;
    private static final String DEFAULT_REPAIR_CODE = "AAAAA";
    private static final String UPDATED_REPAIR_CODE = "BBBBB";

    @Inject
    private BurstComplaintRepository burstComplaintRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBurstComplaintMockMvc;

    private BurstComplaint burstComplaint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BurstComplaintResource burstComplaintResource = new BurstComplaintResource();
        ReflectionTestUtils.setField(burstComplaintResource, "burstComplaintRepository", burstComplaintRepository);
        this.restBurstComplaintMockMvc = MockMvcBuilders.standaloneSetup(burstComplaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        burstComplaint = new BurstComplaint();
        burstComplaint.setMeterLocation(DEFAULT_METER_LOCATION);
        burstComplaint.setBurstPipe(DEFAULT_BURST_PIPE);
        burstComplaint.setFromLeft(DEFAULT_FROM_LEFT);
        burstComplaint.setFromSb(DEFAULT_FROM_SB);
        burstComplaint.setBurstArea(DEFAULT_BURST_AREA);
        burstComplaint.setPipeType(DEFAULT_PIPE_TYPE);
        burstComplaint.setPipeSize(DEFAULT_PIPE_SIZE);
        burstComplaint.setHoleSize(DEFAULT_HOLE_SIZE);
        burstComplaint.setRepairCode(DEFAULT_REPAIR_CODE);
    }

    @Test
    @Transactional
    public void createBurstComplaint() throws Exception {
        int databaseSizeBeforeCreate = burstComplaintRepository.findAll().size();

        // Create the BurstComplaint

        restBurstComplaintMockMvc.perform(post("/api/burstComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(burstComplaint)))
                .andExpect(status().isCreated());

        // Validate the BurstComplaint in the database
        List<BurstComplaint> burstComplaints = burstComplaintRepository.findAll();
        assertThat(burstComplaints).hasSize(databaseSizeBeforeCreate + 1);
        BurstComplaint testBurstComplaint = burstComplaints.get(burstComplaints.size() - 1);
        assertThat(testBurstComplaint.getMeterLocation()).isEqualTo(DEFAULT_METER_LOCATION);
        assertThat(testBurstComplaint.getBurstPipe()).isEqualTo(DEFAULT_BURST_PIPE);
        assertThat(testBurstComplaint.getFromLeft()).isEqualTo(DEFAULT_FROM_LEFT);
        assertThat(testBurstComplaint.getFromSb()).isEqualTo(DEFAULT_FROM_SB);
        assertThat(testBurstComplaint.getBurstArea()).isEqualTo(DEFAULT_BURST_AREA);
        assertThat(testBurstComplaint.getPipeType()).isEqualTo(DEFAULT_PIPE_TYPE);
        assertThat(testBurstComplaint.getPipeSize()).isEqualTo(DEFAULT_PIPE_SIZE);
        assertThat(testBurstComplaint.getHoleSize()).isEqualTo(DEFAULT_HOLE_SIZE);
        assertThat(testBurstComplaint.getRepairCode()).isEqualTo(DEFAULT_REPAIR_CODE);
    }

    @Test
    @Transactional
    public void getAllBurstComplaints() throws Exception {
        // Initialize the database
        burstComplaintRepository.saveAndFlush(burstComplaint);

        // Get all the burstComplaints
        restBurstComplaintMockMvc.perform(get("/api/burstComplaints?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(burstComplaint.getId().intValue())))
                .andExpect(jsonPath("$.[*].meterLocation").value(hasItem(DEFAULT_METER_LOCATION.toString())))
                .andExpect(jsonPath("$.[*].burstPipe").value(hasItem(DEFAULT_BURST_PIPE.toString())))
                .andExpect(jsonPath("$.[*].fromLeft").value(hasItem(DEFAULT_FROM_LEFT.toString())))
                .andExpect(jsonPath("$.[*].fromSb").value(hasItem(DEFAULT_FROM_SB.toString())))
                .andExpect(jsonPath("$.[*].burstArea").value(hasItem(DEFAULT_BURST_AREA.toString())))
                .andExpect(jsonPath("$.[*].pipeType").value(hasItem(DEFAULT_PIPE_TYPE.toString())))
                .andExpect(jsonPath("$.[*].pipeSize").value(hasItem(DEFAULT_PIPE_SIZE)))
                .andExpect(jsonPath("$.[*].holeSize").value(hasItem(DEFAULT_HOLE_SIZE)))
                .andExpect(jsonPath("$.[*].repairCode").value(hasItem(DEFAULT_REPAIR_CODE.toString())));
    }

    @Test
    @Transactional
    public void getBurstComplaint() throws Exception {
        // Initialize the database
        burstComplaintRepository.saveAndFlush(burstComplaint);

        // Get the burstComplaint
        restBurstComplaintMockMvc.perform(get("/api/burstComplaints/{id}", burstComplaint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(burstComplaint.getId().intValue()))
            .andExpect(jsonPath("$.meterLocation").value(DEFAULT_METER_LOCATION.toString()))
            .andExpect(jsonPath("$.burstPipe").value(DEFAULT_BURST_PIPE.toString()))
            .andExpect(jsonPath("$.fromLeft").value(DEFAULT_FROM_LEFT.toString()))
            .andExpect(jsonPath("$.fromSb").value(DEFAULT_FROM_SB.toString()))
            .andExpect(jsonPath("$.burstArea").value(DEFAULT_BURST_AREA.toString()))
            .andExpect(jsonPath("$.pipeType").value(DEFAULT_PIPE_TYPE.toString()))
            .andExpect(jsonPath("$.pipeSize").value(DEFAULT_PIPE_SIZE))
            .andExpect(jsonPath("$.holeSize").value(DEFAULT_HOLE_SIZE))
            .andExpect(jsonPath("$.repairCode").value(DEFAULT_REPAIR_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBurstComplaint() throws Exception {
        // Get the burstComplaint
        restBurstComplaintMockMvc.perform(get("/api/burstComplaints/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBurstComplaint() throws Exception {
        // Initialize the database
        burstComplaintRepository.saveAndFlush(burstComplaint);

		int databaseSizeBeforeUpdate = burstComplaintRepository.findAll().size();

        // Update the burstComplaint
        burstComplaint.setMeterLocation(UPDATED_METER_LOCATION);
        burstComplaint.setBurstPipe(UPDATED_BURST_PIPE);
        burstComplaint.setFromLeft(UPDATED_FROM_LEFT);
        burstComplaint.setFromSb(UPDATED_FROM_SB);
        burstComplaint.setBurstArea(UPDATED_BURST_AREA);
        burstComplaint.setPipeType(UPDATED_PIPE_TYPE);
        burstComplaint.setPipeSize(UPDATED_PIPE_SIZE);
        burstComplaint.setHoleSize(UPDATED_HOLE_SIZE);
        burstComplaint.setRepairCode(UPDATED_REPAIR_CODE);

        restBurstComplaintMockMvc.perform(put("/api/burstComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(burstComplaint)))
                .andExpect(status().isOk());

        // Validate the BurstComplaint in the database
        List<BurstComplaint> burstComplaints = burstComplaintRepository.findAll();
        assertThat(burstComplaints).hasSize(databaseSizeBeforeUpdate);
        BurstComplaint testBurstComplaint = burstComplaints.get(burstComplaints.size() - 1);
        assertThat(testBurstComplaint.getMeterLocation()).isEqualTo(UPDATED_METER_LOCATION);
        assertThat(testBurstComplaint.getBurstPipe()).isEqualTo(UPDATED_BURST_PIPE);
        assertThat(testBurstComplaint.getFromLeft()).isEqualTo(UPDATED_FROM_LEFT);
        assertThat(testBurstComplaint.getFromSb()).isEqualTo(UPDATED_FROM_SB);
        assertThat(testBurstComplaint.getBurstArea()).isEqualTo(UPDATED_BURST_AREA);
        assertThat(testBurstComplaint.getPipeType()).isEqualTo(UPDATED_PIPE_TYPE);
        assertThat(testBurstComplaint.getPipeSize()).isEqualTo(UPDATED_PIPE_SIZE);
        assertThat(testBurstComplaint.getHoleSize()).isEqualTo(UPDATED_HOLE_SIZE);
        assertThat(testBurstComplaint.getRepairCode()).isEqualTo(UPDATED_REPAIR_CODE);
    }

    @Test
    @Transactional
    public void deleteBurstComplaint() throws Exception {
        // Initialize the database
        burstComplaintRepository.saveAndFlush(burstComplaint);

		int databaseSizeBeforeDelete = burstComplaintRepository.findAll().size();

        // Get the burstComplaint
        restBurstComplaintMockMvc.perform(delete("/api/burstComplaints/{id}", burstComplaint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BurstComplaint> burstComplaints = burstComplaintRepository.findAll();
        assertThat(burstComplaints).hasSize(databaseSizeBeforeDelete - 1);
    }
}

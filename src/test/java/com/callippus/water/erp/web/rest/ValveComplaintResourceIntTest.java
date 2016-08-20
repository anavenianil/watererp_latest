package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ValveComplaint;
import com.callippus.water.erp.repository.ValveComplaintRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ValveComplaintResource REST controller.
 *
 * @see ValveComplaintResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ValveComplaintResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_CLOSED_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CLOSED_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CLOSED_TIME_STR = dateTimeFormatter.format(DEFAULT_CLOSED_TIME);

    private static final ZonedDateTime DEFAULT_OPEN_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_OPEN_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_OPEN_TIME_STR = dateTimeFormatter.format(DEFAULT_OPEN_TIME);
    private static final String DEFAULT_COLOUR_CODE = "AAAAA";
    private static final String UPDATED_COLOUR_CODE = "BBBBB";
    private static final String DEFAULT_SIDE = "AAAAA";
    private static final String UPDATED_SIDE = "BBBBB";

    private static final Integer DEFAULT_NO_OF_TURNS = 1;
    private static final Integer UPDATED_NO_OF_TURNS = 2;

    private static final Float DEFAULT_VALVE_SIZE = 1F;
    private static final Float UPDATED_VALVE_SIZE = 2F;

    private static final Integer DEFAULT_VALVE_NO = 1;
    private static final Integer UPDATED_VALVE_NO = 2;
    private static final String DEFAULT_REPAIR_CODE = "AAAAA";
    private static final String UPDATED_REPAIR_CODE = "BBBBB";
    private static final String DEFAULT_DISTANCE_LEFT = "AAAAA";
    private static final String UPDATED_DISTANCE_LEFT = "BBBBB";
    private static final String DEFAULT_DISTANCE_SB = "AAAAA";
    private static final String UPDATED_DISTANCE_SB = "BBBBB";
    private static final String DEFAULT_DISTANCE_Z = "AAAAA";
    private static final String UPDATED_DISTANCE_Z = "BBBBB";

    @Inject
    private ValveComplaintRepository valveComplaintRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restValveComplaintMockMvc;

    private ValveComplaint valveComplaint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ValveComplaintResource valveComplaintResource = new ValveComplaintResource();
        ReflectionTestUtils.setField(valveComplaintResource, "valveComplaintRepository", valveComplaintRepository);
        this.restValveComplaintMockMvc = MockMvcBuilders.standaloneSetup(valveComplaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        valveComplaint = new ValveComplaint();
        valveComplaint.setClosedTime(DEFAULT_CLOSED_TIME);
        valveComplaint.setOpenTime(DEFAULT_OPEN_TIME);
        valveComplaint.setColourCode(DEFAULT_COLOUR_CODE);
        valveComplaint.setSide(DEFAULT_SIDE);
        valveComplaint.setNoOfTurns(DEFAULT_NO_OF_TURNS);
        valveComplaint.setValveSize(DEFAULT_VALVE_SIZE);
        valveComplaint.setValveNo(DEFAULT_VALVE_NO);
        valveComplaint.setRepairCode(DEFAULT_REPAIR_CODE);
        valveComplaint.setDistanceLeft(DEFAULT_DISTANCE_LEFT);
        valveComplaint.setDistanceSb(DEFAULT_DISTANCE_SB);
        valveComplaint.setDistanceZ(DEFAULT_DISTANCE_Z);
    }

    @Test
    @Transactional
    public void createValveComplaint() throws Exception {
        int databaseSizeBeforeCreate = valveComplaintRepository.findAll().size();

        // Create the ValveComplaint

        restValveComplaintMockMvc.perform(post("/api/valveComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(valveComplaint)))
                .andExpect(status().isCreated());

        // Validate the ValveComplaint in the database
        List<ValveComplaint> valveComplaints = valveComplaintRepository.findAll();
        assertThat(valveComplaints).hasSize(databaseSizeBeforeCreate + 1);
        ValveComplaint testValveComplaint = valveComplaints.get(valveComplaints.size() - 1);
        assertThat(testValveComplaint.getClosedTime()).isEqualTo(DEFAULT_CLOSED_TIME);
        assertThat(testValveComplaint.getOpenTime()).isEqualTo(DEFAULT_OPEN_TIME);
        assertThat(testValveComplaint.getColourCode()).isEqualTo(DEFAULT_COLOUR_CODE);
        assertThat(testValveComplaint.getSide()).isEqualTo(DEFAULT_SIDE);
        assertThat(testValveComplaint.getNoOfTurns()).isEqualTo(DEFAULT_NO_OF_TURNS);
        assertThat(testValveComplaint.getValveSize()).isEqualTo(DEFAULT_VALVE_SIZE);
        assertThat(testValveComplaint.getValveNo()).isEqualTo(DEFAULT_VALVE_NO);
        assertThat(testValveComplaint.getRepairCode()).isEqualTo(DEFAULT_REPAIR_CODE);
        assertThat(testValveComplaint.getDistanceLeft()).isEqualTo(DEFAULT_DISTANCE_LEFT);
        assertThat(testValveComplaint.getDistanceSb()).isEqualTo(DEFAULT_DISTANCE_SB);
        assertThat(testValveComplaint.getDistanceZ()).isEqualTo(DEFAULT_DISTANCE_Z);
    }

    @Test
    @Transactional
    public void getAllValveComplaints() throws Exception {
        // Initialize the database
        valveComplaintRepository.saveAndFlush(valveComplaint);

        // Get all the valveComplaints
        restValveComplaintMockMvc.perform(get("/api/valveComplaints?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(valveComplaint.getId().intValue())))
                .andExpect(jsonPath("$.[*].closedTime").value(hasItem(DEFAULT_CLOSED_TIME_STR)))
                .andExpect(jsonPath("$.[*].openTime").value(hasItem(DEFAULT_OPEN_TIME_STR)))
                .andExpect(jsonPath("$.[*].colourCode").value(hasItem(DEFAULT_COLOUR_CODE.toString())))
                .andExpect(jsonPath("$.[*].side").value(hasItem(DEFAULT_SIDE.toString())))
                .andExpect(jsonPath("$.[*].noOfTurns").value(hasItem(DEFAULT_NO_OF_TURNS)))
                .andExpect(jsonPath("$.[*].valveSize").value(hasItem(DEFAULT_VALVE_SIZE.doubleValue())))
                .andExpect(jsonPath("$.[*].valveNo").value(hasItem(DEFAULT_VALVE_NO)))
                .andExpect(jsonPath("$.[*].repairCode").value(hasItem(DEFAULT_REPAIR_CODE.toString())))
                .andExpect(jsonPath("$.[*].distanceLeft").value(hasItem(DEFAULT_DISTANCE_LEFT.toString())))
                .andExpect(jsonPath("$.[*].distanceSb").value(hasItem(DEFAULT_DISTANCE_SB.toString())))
                .andExpect(jsonPath("$.[*].distanceZ").value(hasItem(DEFAULT_DISTANCE_Z.toString())));
    }

    @Test
    @Transactional
    public void getValveComplaint() throws Exception {
        // Initialize the database
        valveComplaintRepository.saveAndFlush(valveComplaint);

        // Get the valveComplaint
        restValveComplaintMockMvc.perform(get("/api/valveComplaints/{id}", valveComplaint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(valveComplaint.getId().intValue()))
            .andExpect(jsonPath("$.closedTime").value(DEFAULT_CLOSED_TIME_STR))
            .andExpect(jsonPath("$.openTime").value(DEFAULT_OPEN_TIME_STR))
            .andExpect(jsonPath("$.colourCode").value(DEFAULT_COLOUR_CODE.toString()))
            .andExpect(jsonPath("$.side").value(DEFAULT_SIDE.toString()))
            .andExpect(jsonPath("$.noOfTurns").value(DEFAULT_NO_OF_TURNS))
            .andExpect(jsonPath("$.valveSize").value(DEFAULT_VALVE_SIZE.doubleValue()))
            .andExpect(jsonPath("$.valveNo").value(DEFAULT_VALVE_NO))
            .andExpect(jsonPath("$.repairCode").value(DEFAULT_REPAIR_CODE.toString()))
            .andExpect(jsonPath("$.distanceLeft").value(DEFAULT_DISTANCE_LEFT.toString()))
            .andExpect(jsonPath("$.distanceSb").value(DEFAULT_DISTANCE_SB.toString()))
            .andExpect(jsonPath("$.distanceZ").value(DEFAULT_DISTANCE_Z.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingValveComplaint() throws Exception {
        // Get the valveComplaint
        restValveComplaintMockMvc.perform(get("/api/valveComplaints/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateValveComplaint() throws Exception {
        // Initialize the database
        valveComplaintRepository.saveAndFlush(valveComplaint);

		int databaseSizeBeforeUpdate = valveComplaintRepository.findAll().size();

        // Update the valveComplaint
        valveComplaint.setClosedTime(UPDATED_CLOSED_TIME);
        valveComplaint.setOpenTime(UPDATED_OPEN_TIME);
        valveComplaint.setColourCode(UPDATED_COLOUR_CODE);
        valveComplaint.setSide(UPDATED_SIDE);
        valveComplaint.setNoOfTurns(UPDATED_NO_OF_TURNS);
        valveComplaint.setValveSize(UPDATED_VALVE_SIZE);
        valveComplaint.setValveNo(UPDATED_VALVE_NO);
        valveComplaint.setRepairCode(UPDATED_REPAIR_CODE);
        valveComplaint.setDistanceLeft(UPDATED_DISTANCE_LEFT);
        valveComplaint.setDistanceSb(UPDATED_DISTANCE_SB);
        valveComplaint.setDistanceZ(UPDATED_DISTANCE_Z);

        restValveComplaintMockMvc.perform(put("/api/valveComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(valveComplaint)))
                .andExpect(status().isOk());

        // Validate the ValveComplaint in the database
        List<ValveComplaint> valveComplaints = valveComplaintRepository.findAll();
        assertThat(valveComplaints).hasSize(databaseSizeBeforeUpdate);
        ValveComplaint testValveComplaint = valveComplaints.get(valveComplaints.size() - 1);
        assertThat(testValveComplaint.getClosedTime()).isEqualTo(UPDATED_CLOSED_TIME);
        assertThat(testValveComplaint.getOpenTime()).isEqualTo(UPDATED_OPEN_TIME);
        assertThat(testValveComplaint.getColourCode()).isEqualTo(UPDATED_COLOUR_CODE);
        assertThat(testValveComplaint.getSide()).isEqualTo(UPDATED_SIDE);
        assertThat(testValveComplaint.getNoOfTurns()).isEqualTo(UPDATED_NO_OF_TURNS);
        assertThat(testValveComplaint.getValveSize()).isEqualTo(UPDATED_VALVE_SIZE);
        assertThat(testValveComplaint.getValveNo()).isEqualTo(UPDATED_VALVE_NO);
        assertThat(testValveComplaint.getRepairCode()).isEqualTo(UPDATED_REPAIR_CODE);
        assertThat(testValveComplaint.getDistanceLeft()).isEqualTo(UPDATED_DISTANCE_LEFT);
        assertThat(testValveComplaint.getDistanceSb()).isEqualTo(UPDATED_DISTANCE_SB);
        assertThat(testValveComplaint.getDistanceZ()).isEqualTo(UPDATED_DISTANCE_Z);
    }

    @Test
    @Transactional
    public void deleteValveComplaint() throws Exception {
        // Initialize the database
        valveComplaintRepository.saveAndFlush(valveComplaint);

		int databaseSizeBeforeDelete = valveComplaintRepository.findAll().size();

        // Get the valveComplaint
        restValveComplaintMockMvc.perform(delete("/api/valveComplaints/{id}", valveComplaint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ValveComplaint> valveComplaints = valveComplaintRepository.findAll();
        assertThat(valveComplaints).hasSize(databaseSizeBeforeDelete - 1);
    }
}

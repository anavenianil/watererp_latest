package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WaterLeakageComplaint;
import com.callippus.water.erp.repository.WaterLeakageComplaintRepository;

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
 * Test class for the WaterLeakageComplaintResource REST controller.
 *
 * @see WaterLeakageComplaintResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WaterLeakageComplaintResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_LEAKAGE_TYPE = "AAAAA";
    private static final String UPDATED_LEAKAGE_TYPE = "BBBBB";
    private static final String DEFAULT_COORDINATE_X = "AAAAA";
    private static final String UPDATED_COORDINATE_X = "BBBBB";
    private static final String DEFAULT_COORDINATE_Y = "AAAAA";
    private static final String UPDATED_COORDINATE_Y = "BBBBB";
    private static final String DEFAULT_LEAK_MAGNITUDE = "AAAAA";
    private static final String UPDATED_LEAK_MAGNITUDE = "BBBBB";

    private static final ZonedDateTime DEFAULT_COMPLAINT_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_COMPLAINT_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_COMPLAINT_DATE_TIME_STR = dateTimeFormatter.format(DEFAULT_COMPLAINT_DATE_TIME);

    private static final Integer DEFAULT_DAYS_REQUIRED = 1;
    private static final Integer UPDATED_DAYS_REQUIRED = 2;

    private static final Integer DEFAULT_STAFF_REQUIRED = 1;
    private static final Integer UPDATED_STAFF_REQUIRED = 2;
    private static final String DEFAULT_COMPLAINT_BY = "AAAAA";
    private static final String UPDATED_COMPLAINT_BY = "BBBBB";
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private WaterLeakageComplaintRepository waterLeakageComplaintRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWaterLeakageComplaintMockMvc;

    private WaterLeakageComplaint waterLeakageComplaint;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WaterLeakageComplaintResource waterLeakageComplaintResource = new WaterLeakageComplaintResource();
        ReflectionTestUtils.setField(waterLeakageComplaintResource, "waterLeakageComplaintRepository", waterLeakageComplaintRepository);
        this.restWaterLeakageComplaintMockMvc = MockMvcBuilders.standaloneSetup(waterLeakageComplaintResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        waterLeakageComplaint = new WaterLeakageComplaint();
        waterLeakageComplaint.setLeakageType(DEFAULT_LEAKAGE_TYPE);
        waterLeakageComplaint.setCoordinateX(DEFAULT_COORDINATE_X);
        waterLeakageComplaint.setCoordinateY(DEFAULT_COORDINATE_Y);
        waterLeakageComplaint.setLeakMagnitude(DEFAULT_LEAK_MAGNITUDE);
        waterLeakageComplaint.setComplaintDateTime(DEFAULT_COMPLAINT_DATE_TIME);
        waterLeakageComplaint.setDaysRequired(DEFAULT_DAYS_REQUIRED);
        waterLeakageComplaint.setStaffRequired(DEFAULT_STAFF_REQUIRED);
        waterLeakageComplaint.setComplaintBy(DEFAULT_COMPLAINT_BY);
        waterLeakageComplaint.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createWaterLeakageComplaint() throws Exception {
        int databaseSizeBeforeCreate = waterLeakageComplaintRepository.findAll().size();

        // Create the WaterLeakageComplaint

        restWaterLeakageComplaintMockMvc.perform(post("/api/waterLeakageComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(waterLeakageComplaint)))
                .andExpect(status().isCreated());

        // Validate the WaterLeakageComplaint in the database
        List<WaterLeakageComplaint> waterLeakageComplaints = waterLeakageComplaintRepository.findAll();
        assertThat(waterLeakageComplaints).hasSize(databaseSizeBeforeCreate + 1);
        WaterLeakageComplaint testWaterLeakageComplaint = waterLeakageComplaints.get(waterLeakageComplaints.size() - 1);
        assertThat(testWaterLeakageComplaint.getLeakageType()).isEqualTo(DEFAULT_LEAKAGE_TYPE);
        assertThat(testWaterLeakageComplaint.getCoordinateX()).isEqualTo(DEFAULT_COORDINATE_X);
        assertThat(testWaterLeakageComplaint.getCoordinateY()).isEqualTo(DEFAULT_COORDINATE_Y);
        assertThat(testWaterLeakageComplaint.getLeakMagnitude()).isEqualTo(DEFAULT_LEAK_MAGNITUDE);
        assertThat(testWaterLeakageComplaint.getComplaintDateTime()).isEqualTo(DEFAULT_COMPLAINT_DATE_TIME);
        assertThat(testWaterLeakageComplaint.getDaysRequired()).isEqualTo(DEFAULT_DAYS_REQUIRED);
        assertThat(testWaterLeakageComplaint.getStaffRequired()).isEqualTo(DEFAULT_STAFF_REQUIRED);
        assertThat(testWaterLeakageComplaint.getComplaintBy()).isEqualTo(DEFAULT_COMPLAINT_BY);
        assertThat(testWaterLeakageComplaint.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllWaterLeakageComplaints() throws Exception {
        // Initialize the database
        waterLeakageComplaintRepository.saveAndFlush(waterLeakageComplaint);

        // Get all the waterLeakageComplaints
        restWaterLeakageComplaintMockMvc.perform(get("/api/waterLeakageComplaints?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(waterLeakageComplaint.getId().intValue())))
                .andExpect(jsonPath("$.[*].leakageType").value(hasItem(DEFAULT_LEAKAGE_TYPE.toString())))
                .andExpect(jsonPath("$.[*].coordinateX").value(hasItem(DEFAULT_COORDINATE_X.toString())))
                .andExpect(jsonPath("$.[*].coordinateY").value(hasItem(DEFAULT_COORDINATE_Y.toString())))
                .andExpect(jsonPath("$.[*].leakMagnitude").value(hasItem(DEFAULT_LEAK_MAGNITUDE.toString())))
                .andExpect(jsonPath("$.[*].complaintDateTime").value(hasItem(DEFAULT_COMPLAINT_DATE_TIME_STR)))
                .andExpect(jsonPath("$.[*].daysRequired").value(hasItem(DEFAULT_DAYS_REQUIRED)))
                .andExpect(jsonPath("$.[*].staffRequired").value(hasItem(DEFAULT_STAFF_REQUIRED)))
                .andExpect(jsonPath("$.[*].complaintBy").value(hasItem(DEFAULT_COMPLAINT_BY.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getWaterLeakageComplaint() throws Exception {
        // Initialize the database
        waterLeakageComplaintRepository.saveAndFlush(waterLeakageComplaint);

        // Get the waterLeakageComplaint
        restWaterLeakageComplaintMockMvc.perform(get("/api/waterLeakageComplaints/{id}", waterLeakageComplaint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(waterLeakageComplaint.getId().intValue()))
            .andExpect(jsonPath("$.leakageType").value(DEFAULT_LEAKAGE_TYPE.toString()))
            .andExpect(jsonPath("$.coordinateX").value(DEFAULT_COORDINATE_X.toString()))
            .andExpect(jsonPath("$.coordinateY").value(DEFAULT_COORDINATE_Y.toString()))
            .andExpect(jsonPath("$.leakMagnitude").value(DEFAULT_LEAK_MAGNITUDE.toString()))
            .andExpect(jsonPath("$.complaintDateTime").value(DEFAULT_COMPLAINT_DATE_TIME_STR))
            .andExpect(jsonPath("$.daysRequired").value(DEFAULT_DAYS_REQUIRED))
            .andExpect(jsonPath("$.staffRequired").value(DEFAULT_STAFF_REQUIRED))
            .andExpect(jsonPath("$.complaintBy").value(DEFAULT_COMPLAINT_BY.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWaterLeakageComplaint() throws Exception {
        // Get the waterLeakageComplaint
        restWaterLeakageComplaintMockMvc.perform(get("/api/waterLeakageComplaints/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaterLeakageComplaint() throws Exception {
        // Initialize the database
        waterLeakageComplaintRepository.saveAndFlush(waterLeakageComplaint);

		int databaseSizeBeforeUpdate = waterLeakageComplaintRepository.findAll().size();

        // Update the waterLeakageComplaint
        waterLeakageComplaint.setLeakageType(UPDATED_LEAKAGE_TYPE);
        waterLeakageComplaint.setCoordinateX(UPDATED_COORDINATE_X);
        waterLeakageComplaint.setCoordinateY(UPDATED_COORDINATE_Y);
        waterLeakageComplaint.setLeakMagnitude(UPDATED_LEAK_MAGNITUDE);
        waterLeakageComplaint.setComplaintDateTime(UPDATED_COMPLAINT_DATE_TIME);
        waterLeakageComplaint.setDaysRequired(UPDATED_DAYS_REQUIRED);
        waterLeakageComplaint.setStaffRequired(UPDATED_STAFF_REQUIRED);
        waterLeakageComplaint.setComplaintBy(UPDATED_COMPLAINT_BY);
        waterLeakageComplaint.setStatus(UPDATED_STATUS);

        restWaterLeakageComplaintMockMvc.perform(put("/api/waterLeakageComplaints")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(waterLeakageComplaint)))
                .andExpect(status().isOk());

        // Validate the WaterLeakageComplaint in the database
        List<WaterLeakageComplaint> waterLeakageComplaints = waterLeakageComplaintRepository.findAll();
        assertThat(waterLeakageComplaints).hasSize(databaseSizeBeforeUpdate);
        WaterLeakageComplaint testWaterLeakageComplaint = waterLeakageComplaints.get(waterLeakageComplaints.size() - 1);
        assertThat(testWaterLeakageComplaint.getLeakageType()).isEqualTo(UPDATED_LEAKAGE_TYPE);
        assertThat(testWaterLeakageComplaint.getCoordinateX()).isEqualTo(UPDATED_COORDINATE_X);
        assertThat(testWaterLeakageComplaint.getCoordinateY()).isEqualTo(UPDATED_COORDINATE_Y);
        assertThat(testWaterLeakageComplaint.getLeakMagnitude()).isEqualTo(UPDATED_LEAK_MAGNITUDE);
        assertThat(testWaterLeakageComplaint.getComplaintDateTime()).isEqualTo(UPDATED_COMPLAINT_DATE_TIME);
        assertThat(testWaterLeakageComplaint.getDaysRequired()).isEqualTo(UPDATED_DAYS_REQUIRED);
        assertThat(testWaterLeakageComplaint.getStaffRequired()).isEqualTo(UPDATED_STAFF_REQUIRED);
        assertThat(testWaterLeakageComplaint.getComplaintBy()).isEqualTo(UPDATED_COMPLAINT_BY);
        assertThat(testWaterLeakageComplaint.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteWaterLeakageComplaint() throws Exception {
        // Initialize the database
        waterLeakageComplaintRepository.saveAndFlush(waterLeakageComplaint);

		int databaseSizeBeforeDelete = waterLeakageComplaintRepository.findAll().size();

        // Get the waterLeakageComplaint
        restWaterLeakageComplaintMockMvc.perform(delete("/api/waterLeakageComplaints/{id}", waterLeakageComplaint.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WaterLeakageComplaint> waterLeakageComplaints = waterLeakageComplaintRepository.findAll();
        assertThat(waterLeakageComplaints).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ConnectionTerminate;
import com.callippus.water.erp.repository.ConnectionTerminateRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.callippus.water.erp.domain.enumeration.TerminationType;
import com.callippus.water.erp.domain.enumeration.MeterCondition;
import com.callippus.water.erp.domain.enumeration.TerminationStatus;

/**
 * Test class for the ConnectionTerminateResource REST controller.
 *
 * @see ConnectionTerminateResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ConnectionTerminateResourceIntTest {

    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";

    private static final LocalDate DEFAULT_REQUEST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUEST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_METER_RECOVERED = false;
    private static final Boolean UPDATED_METER_RECOVERED = true;

    private static final BigDecimal DEFAULT_LAST_METER_READING = new BigDecimal(1);
    private static final BigDecimal UPDATED_LAST_METER_READING = new BigDecimal(2);

    private static final LocalDate DEFAULT_METER_RECOVERED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_METER_RECOVERED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Boolean DEFAULT_METERED_CONNECTION = false;
    private static final Boolean UPDATED_METERED_CONNECTION = true;
    private static final String DEFAULT_REASON = "AAAAA";
    private static final String UPDATED_REASON = "BBBBB";
    
    private static final TerminationType DEFAULT_TERMINATION_TYPE = TerminationType.TEMPORARY;
    private static final TerminationType UPDATED_TERMINATION_TYPE = TerminationType.PERMANENT;
    
    private static final MeterCondition DEFAULT_METER_CONDITION = MeterCondition.WORKING;
    private static final MeterCondition UPDATED_METER_CONDITION = MeterCondition.FAULT;
    
    private static final TerminationStatus DEFAULT_STATUS = TerminationStatus.PENDING;
    private static final TerminationStatus UPDATED_STATUS = TerminationStatus.INITIATED;

    @Inject
    private ConnectionTerminateRepository connectionTerminateRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restConnectionTerminateMockMvc;

    private ConnectionTerminate connectionTerminate;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ConnectionTerminateResource connectionTerminateResource = new ConnectionTerminateResource();
        ReflectionTestUtils.setField(connectionTerminateResource, "connectionTerminateRepository", connectionTerminateRepository);
        this.restConnectionTerminateMockMvc = MockMvcBuilders.standaloneSetup(connectionTerminateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        connectionTerminate = new ConnectionTerminate();
        connectionTerminate.setCan(DEFAULT_CAN);
        connectionTerminate.setRequestDate(DEFAULT_REQUEST_DATE);
        connectionTerminate.setMeterRecovered(DEFAULT_METER_RECOVERED);
        connectionTerminate.setLastMeterReading(DEFAULT_LAST_METER_READING);
        connectionTerminate.setMeterRecoveredDate(DEFAULT_METER_RECOVERED_DATE);
        connectionTerminate.setMeteredConnection(DEFAULT_METERED_CONNECTION);
        connectionTerminate.setReason(DEFAULT_REASON);
        connectionTerminate.setTerminationType(DEFAULT_TERMINATION_TYPE);
        connectionTerminate.setMeterCondition(DEFAULT_METER_CONDITION);
        connectionTerminate.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createConnectionTerminate() throws Exception {
        int databaseSizeBeforeCreate = connectionTerminateRepository.findAll().size();

        // Create the ConnectionTerminate

        restConnectionTerminateMockMvc.perform(post("/api/connectionTerminates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(connectionTerminate)))
                .andExpect(status().isCreated());

        // Validate the ConnectionTerminate in the database
        List<ConnectionTerminate> connectionTerminates = connectionTerminateRepository.findAll();
        assertThat(connectionTerminates).hasSize(databaseSizeBeforeCreate + 1);
        ConnectionTerminate testConnectionTerminate = connectionTerminates.get(connectionTerminates.size() - 1);
        assertThat(testConnectionTerminate.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testConnectionTerminate.getRequestDate()).isEqualTo(DEFAULT_REQUEST_DATE);
        assertThat(testConnectionTerminate.getMeterRecovered()).isEqualTo(DEFAULT_METER_RECOVERED);
        assertThat(testConnectionTerminate.getLastMeterReading()).isEqualTo(DEFAULT_LAST_METER_READING);
        assertThat(testConnectionTerminate.getMeterRecoveredDate()).isEqualTo(DEFAULT_METER_RECOVERED_DATE);
        assertThat(testConnectionTerminate.getMeteredConnection()).isEqualTo(DEFAULT_METERED_CONNECTION);
        assertThat(testConnectionTerminate.getReason()).isEqualTo(DEFAULT_REASON);
        assertThat(testConnectionTerminate.getTerminationType()).isEqualTo(DEFAULT_TERMINATION_TYPE);
        assertThat(testConnectionTerminate.getMeterCondition()).isEqualTo(DEFAULT_METER_CONDITION);
        assertThat(testConnectionTerminate.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllConnectionTerminates() throws Exception {
        // Initialize the database
        connectionTerminateRepository.saveAndFlush(connectionTerminate);

        // Get all the connectionTerminates
        restConnectionTerminateMockMvc.perform(get("/api/connectionTerminates?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(connectionTerminate.getId().intValue())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].requestDate").value(hasItem(DEFAULT_REQUEST_DATE.toString())))
                .andExpect(jsonPath("$.[*].meterRecovered").value(hasItem(DEFAULT_METER_RECOVERED.booleanValue())))
                .andExpect(jsonPath("$.[*].lastMeterReading").value(hasItem(DEFAULT_LAST_METER_READING.intValue())))
                .andExpect(jsonPath("$.[*].meterRecoveredDate").value(hasItem(DEFAULT_METER_RECOVERED_DATE.toString())))
                .andExpect(jsonPath("$.[*].meteredConnection").value(hasItem(DEFAULT_METERED_CONNECTION.booleanValue())))
                .andExpect(jsonPath("$.[*].reason").value(hasItem(DEFAULT_REASON.toString())))
                .andExpect(jsonPath("$.[*].terminationType").value(hasItem(DEFAULT_TERMINATION_TYPE.toString())))
                .andExpect(jsonPath("$.[*].meterCondition").value(hasItem(DEFAULT_METER_CONDITION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getConnectionTerminate() throws Exception {
        // Initialize the database
        connectionTerminateRepository.saveAndFlush(connectionTerminate);

        // Get the connectionTerminate
        restConnectionTerminateMockMvc.perform(get("/api/connectionTerminates/{id}", connectionTerminate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(connectionTerminate.getId().intValue()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.requestDate").value(DEFAULT_REQUEST_DATE.toString()))
            .andExpect(jsonPath("$.meterRecovered").value(DEFAULT_METER_RECOVERED.booleanValue()))
            .andExpect(jsonPath("$.lastMeterReading").value(DEFAULT_LAST_METER_READING.intValue()))
            .andExpect(jsonPath("$.meterRecoveredDate").value(DEFAULT_METER_RECOVERED_DATE.toString()))
            .andExpect(jsonPath("$.meteredConnection").value(DEFAULT_METERED_CONNECTION.booleanValue()))
            .andExpect(jsonPath("$.reason").value(DEFAULT_REASON.toString()))
            .andExpect(jsonPath("$.terminationType").value(DEFAULT_TERMINATION_TYPE.toString()))
            .andExpect(jsonPath("$.meterCondition").value(DEFAULT_METER_CONDITION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConnectionTerminate() throws Exception {
        // Get the connectionTerminate
        restConnectionTerminateMockMvc.perform(get("/api/connectionTerminates/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConnectionTerminate() throws Exception {
        // Initialize the database
        connectionTerminateRepository.saveAndFlush(connectionTerminate);

		int databaseSizeBeforeUpdate = connectionTerminateRepository.findAll().size();

        // Update the connectionTerminate
        connectionTerminate.setCan(UPDATED_CAN);
        connectionTerminate.setRequestDate(UPDATED_REQUEST_DATE);
        connectionTerminate.setMeterRecovered(UPDATED_METER_RECOVERED);
        connectionTerminate.setLastMeterReading(UPDATED_LAST_METER_READING);
        connectionTerminate.setMeterRecoveredDate(UPDATED_METER_RECOVERED_DATE);
        connectionTerminate.setMeteredConnection(UPDATED_METERED_CONNECTION);
        connectionTerminate.setReason(UPDATED_REASON);
        connectionTerminate.setTerminationType(UPDATED_TERMINATION_TYPE);
        connectionTerminate.setMeterCondition(UPDATED_METER_CONDITION);
        connectionTerminate.setStatus(UPDATED_STATUS);

        restConnectionTerminateMockMvc.perform(put("/api/connectionTerminates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(connectionTerminate)))
                .andExpect(status().isOk());

        // Validate the ConnectionTerminate in the database
        List<ConnectionTerminate> connectionTerminates = connectionTerminateRepository.findAll();
        assertThat(connectionTerminates).hasSize(databaseSizeBeforeUpdate);
        ConnectionTerminate testConnectionTerminate = connectionTerminates.get(connectionTerminates.size() - 1);
        assertThat(testConnectionTerminate.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testConnectionTerminate.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testConnectionTerminate.getMeterRecovered()).isEqualTo(UPDATED_METER_RECOVERED);
        assertThat(testConnectionTerminate.getLastMeterReading()).isEqualTo(UPDATED_LAST_METER_READING);
        assertThat(testConnectionTerminate.getMeterRecoveredDate()).isEqualTo(UPDATED_METER_RECOVERED_DATE);
        assertThat(testConnectionTerminate.getMeteredConnection()).isEqualTo(UPDATED_METERED_CONNECTION);
        assertThat(testConnectionTerminate.getReason()).isEqualTo(UPDATED_REASON);
        assertThat(testConnectionTerminate.getTerminationType()).isEqualTo(UPDATED_TERMINATION_TYPE);
        assertThat(testConnectionTerminate.getMeterCondition()).isEqualTo(UPDATED_METER_CONDITION);
        assertThat(testConnectionTerminate.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteConnectionTerminate() throws Exception {
        // Initialize the database
        connectionTerminateRepository.saveAndFlush(connectionTerminate);

		int databaseSizeBeforeDelete = connectionTerminateRepository.findAll().size();

        // Get the connectionTerminate
        restConnectionTerminateMockMvc.perform(delete("/api/connectionTerminates/{id}", connectionTerminate.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ConnectionTerminate> connectionTerminates = connectionTerminateRepository.findAll();
        assertThat(connectionTerminates).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CurrentUsers;
import com.callippus.water.erp.repository.CurrentUsersRepository;

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
 * Test class for the CurrentUsersResource REST controller.
 *
 * @see CurrentUsersResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CurrentUsersResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_TERMINAL_ID = "AAAAA";
    private static final String UPDATED_TERMINAL_ID = "BBBBB";
    private static final String DEFAULT_METER_READER_ID = "AAAAA";
    private static final String UPDATED_METER_READER_ID = "BBBBB";
    private static final String DEFAULT_USER_ID = "AAAAA";
    private static final String UPDATED_USER_ID = "BBBBB";
    private static final String DEFAULT_REQUEST_TYPE = "AAAAA";
    private static final String UPDATED_REQUEST_TYPE = "BBBBB";

    private static final ZonedDateTime DEFAULT_LOGIN_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LOGIN_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LOGIN_TIME_STR = dateTimeFormatter.format(DEFAULT_LOGIN_TIME);
    private static final String DEFAULT_IP = "AAAAA";
    private static final String UPDATED_IP = "BBBBB";

    @Inject
    private CurrentUsersRepository currentUsersRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCurrentUsersMockMvc;

    private CurrentUsers currentUsers;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CurrentUsersResource currentUsersResource = new CurrentUsersResource();
        ReflectionTestUtils.setField(currentUsersResource, "currentUsersRepository", currentUsersRepository);
        this.restCurrentUsersMockMvc = MockMvcBuilders.standaloneSetup(currentUsersResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        currentUsers = new CurrentUsers();
        currentUsers.setTerminalId(DEFAULT_TERMINAL_ID);
        currentUsers.setMeterReaderId(DEFAULT_METER_READER_ID);
        currentUsers.setUserId(DEFAULT_USER_ID);
        currentUsers.setRequestType(DEFAULT_REQUEST_TYPE);
        currentUsers.setLoginTime(DEFAULT_LOGIN_TIME);
        currentUsers.setIp(DEFAULT_IP);
    }

    @Test
    @Transactional
    public void createCurrentUsers() throws Exception {
        int databaseSizeBeforeCreate = currentUsersRepository.findAll().size();

        // Create the CurrentUsers

        restCurrentUsersMockMvc.perform(post("/api/currentUserss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(currentUsers)))
                .andExpect(status().isCreated());

        // Validate the CurrentUsers in the database
        List<CurrentUsers> currentUserss = currentUsersRepository.findAll();
        assertThat(currentUserss).hasSize(databaseSizeBeforeCreate + 1);
        CurrentUsers testCurrentUsers = currentUserss.get(currentUserss.size() - 1);
        assertThat(testCurrentUsers.getTerminalId()).isEqualTo(DEFAULT_TERMINAL_ID);
        assertThat(testCurrentUsers.getMeterReaderId()).isEqualTo(DEFAULT_METER_READER_ID);
        assertThat(testCurrentUsers.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testCurrentUsers.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testCurrentUsers.getLoginTime()).isEqualTo(DEFAULT_LOGIN_TIME);
        assertThat(testCurrentUsers.getIp()).isEqualTo(DEFAULT_IP);
    }

    @Test
    @Transactional
    public void getAllCurrentUserss() throws Exception {
        // Initialize the database
        currentUsersRepository.saveAndFlush(currentUsers);

        // Get all the currentUserss
        restCurrentUsersMockMvc.perform(get("/api/currentUserss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(currentUsers.getId().intValue())))
                .andExpect(jsonPath("$.[*].terminalId").value(hasItem(DEFAULT_TERMINAL_ID.toString())))
                .andExpect(jsonPath("$.[*].meterReaderId").value(hasItem(DEFAULT_METER_READER_ID.toString())))
                .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
                .andExpect(jsonPath("$.[*].requestType").value(hasItem(DEFAULT_REQUEST_TYPE.toString())))
                .andExpect(jsonPath("$.[*].loginTime").value(hasItem(DEFAULT_LOGIN_TIME_STR)))
                .andExpect(jsonPath("$.[*].ip").value(hasItem(DEFAULT_IP.toString())));
    }

    @Test
    @Transactional
    public void getCurrentUsers() throws Exception {
        // Initialize the database
        currentUsersRepository.saveAndFlush(currentUsers);

        // Get the currentUsers
        restCurrentUsersMockMvc.perform(get("/api/currentUserss/{id}", currentUsers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(currentUsers.getId().intValue()))
            .andExpect(jsonPath("$.terminalId").value(DEFAULT_TERMINAL_ID.toString()))
            .andExpect(jsonPath("$.meterReaderId").value(DEFAULT_METER_READER_ID.toString()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.requestType").value(DEFAULT_REQUEST_TYPE.toString()))
            .andExpect(jsonPath("$.loginTime").value(DEFAULT_LOGIN_TIME_STR))
            .andExpect(jsonPath("$.ip").value(DEFAULT_IP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCurrentUsers() throws Exception {
        // Get the currentUsers
        restCurrentUsersMockMvc.perform(get("/api/currentUserss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCurrentUsers() throws Exception {
        // Initialize the database
        currentUsersRepository.saveAndFlush(currentUsers);

		int databaseSizeBeforeUpdate = currentUsersRepository.findAll().size();

        // Update the currentUsers
        currentUsers.setTerminalId(UPDATED_TERMINAL_ID);
        currentUsers.setMeterReaderId(UPDATED_METER_READER_ID);
        currentUsers.setUserId(UPDATED_USER_ID);
        currentUsers.setRequestType(UPDATED_REQUEST_TYPE);
        currentUsers.setLoginTime(UPDATED_LOGIN_TIME);
        currentUsers.setIp(UPDATED_IP);

        restCurrentUsersMockMvc.perform(put("/api/currentUserss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(currentUsers)))
                .andExpect(status().isOk());

        // Validate the CurrentUsers in the database
        List<CurrentUsers> currentUserss = currentUsersRepository.findAll();
        assertThat(currentUserss).hasSize(databaseSizeBeforeUpdate);
        CurrentUsers testCurrentUsers = currentUserss.get(currentUserss.size() - 1);
        assertThat(testCurrentUsers.getTerminalId()).isEqualTo(UPDATED_TERMINAL_ID);
        assertThat(testCurrentUsers.getMeterReaderId()).isEqualTo(UPDATED_METER_READER_ID);
        assertThat(testCurrentUsers.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testCurrentUsers.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testCurrentUsers.getLoginTime()).isEqualTo(UPDATED_LOGIN_TIME);
        assertThat(testCurrentUsers.getIp()).isEqualTo(UPDATED_IP);
    }

    @Test
    @Transactional
    public void deleteCurrentUsers() throws Exception {
        // Initialize the database
        currentUsersRepository.saveAndFlush(currentUsers);

		int databaseSizeBeforeDelete = currentUsersRepository.findAll().size();

        // Get the currentUsers
        restCurrentUsersMockMvc.perform(delete("/api/currentUserss/{id}", currentUsers.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CurrentUsers> currentUserss = currentUsersRepository.findAll();
        assertThat(currentUserss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

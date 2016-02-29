package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.RequestWorkflowHistory;
import com.callippus.water.erp.repository.RequestWorkflowHistoryRepository;

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
 * Test class for the RequestWorkflowHistoryResource REST controller.
 *
 * @see RequestWorkflowHistoryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RequestWorkflowHistoryResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final Integer DEFAULT_REQUEST_STAGE = 1;
    private static final Integer UPDATED_REQUEST_STAGE = 2;

    private static final ZonedDateTime DEFAULT_ASSIGNED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_ASSIGNED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_ASSIGNED_DATE_STR = dateTimeFormatter.format(DEFAULT_ASSIGNED_DATE);

    private static final ZonedDateTime DEFAULT_ACTIONED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_ACTIONED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_ACTIONED_DATE_STR = dateTimeFormatter.format(DEFAULT_ACTIONED_DATE);
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";
    private static final String DEFAULT_IP_ADDRESS = "AAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBB";

    private static final Integer DEFAULT_ASSIGNED_ROLE = 1;
    private static final Integer UPDATED_ASSIGNED_ROLE = 2;

    private static final Long DEFAULT_DOMAIN_OBJECT = 1L;
    private static final Long UPDATED_DOMAIN_OBJECT = 2L;

    @Inject
    private RequestWorkflowHistoryRepository requestWorkflowHistoryRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRequestWorkflowHistoryMockMvc;

    private RequestWorkflowHistory requestWorkflowHistory;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RequestWorkflowHistoryResource requestWorkflowHistoryResource = new RequestWorkflowHistoryResource();
        ReflectionTestUtils.setField(requestWorkflowHistoryResource, "requestWorkflowHistoryRepository", requestWorkflowHistoryRepository);
        this.restRequestWorkflowHistoryMockMvc = MockMvcBuilders.standaloneSetup(requestWorkflowHistoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        requestWorkflowHistory = new RequestWorkflowHistory();
        requestWorkflowHistory.setRequestStage(DEFAULT_REQUEST_STAGE);
        requestWorkflowHistory.setAssignedDate(DEFAULT_ASSIGNED_DATE);
        requestWorkflowHistory.setActionedDate(DEFAULT_ACTIONED_DATE);
        requestWorkflowHistory.setRemarks(DEFAULT_REMARKS);
        requestWorkflowHistory.setIpAddress(DEFAULT_IP_ADDRESS);
        requestWorkflowHistory.setAssignedRole(DEFAULT_ASSIGNED_ROLE);
        requestWorkflowHistory.setDomainObject(DEFAULT_DOMAIN_OBJECT);
    }

    @Test
    @Transactional
    public void createRequestWorkflowHistory() throws Exception {
        int databaseSizeBeforeCreate = requestWorkflowHistoryRepository.findAll().size();

        // Create the RequestWorkflowHistory

        restRequestWorkflowHistoryMockMvc.perform(post("/api/requestWorkflowHistorys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(requestWorkflowHistory)))
                .andExpect(status().isCreated());

        // Validate the RequestWorkflowHistory in the database
        List<RequestWorkflowHistory> requestWorkflowHistorys = requestWorkflowHistoryRepository.findAll();
        assertThat(requestWorkflowHistorys).hasSize(databaseSizeBeforeCreate + 1);
        RequestWorkflowHistory testRequestWorkflowHistory = requestWorkflowHistorys.get(requestWorkflowHistorys.size() - 1);
        assertThat(testRequestWorkflowHistory.getRequestStage()).isEqualTo(DEFAULT_REQUEST_STAGE);
        assertThat(testRequestWorkflowHistory.getAssignedDate()).isEqualTo(DEFAULT_ASSIGNED_DATE);
        assertThat(testRequestWorkflowHistory.getActionedDate()).isEqualTo(DEFAULT_ACTIONED_DATE);
        assertThat(testRequestWorkflowHistory.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testRequestWorkflowHistory.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testRequestWorkflowHistory.getAssignedRole()).isEqualTo(DEFAULT_ASSIGNED_ROLE);
        assertThat(testRequestWorkflowHistory.getDomainObject()).isEqualTo(DEFAULT_DOMAIN_OBJECT);
    }

    @Test
    @Transactional
    public void getAllRequestWorkflowHistorys() throws Exception {
        // Initialize the database
        requestWorkflowHistoryRepository.saveAndFlush(requestWorkflowHistory);

        // Get all the requestWorkflowHistorys
        restRequestWorkflowHistoryMockMvc.perform(get("/api/requestWorkflowHistorys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(requestWorkflowHistory.getId().intValue())))
                .andExpect(jsonPath("$.[*].requestStage").value(hasItem(DEFAULT_REQUEST_STAGE)))
                .andExpect(jsonPath("$.[*].assignedDate").value(hasItem(DEFAULT_ASSIGNED_DATE_STR)))
                .andExpect(jsonPath("$.[*].actionedDate").value(hasItem(DEFAULT_ACTIONED_DATE_STR)))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].assignedRole").value(hasItem(DEFAULT_ASSIGNED_ROLE)))
                .andExpect(jsonPath("$.[*].domainObject").value(hasItem(DEFAULT_DOMAIN_OBJECT.intValue())));
    }

    @Test
    @Transactional
    public void getRequestWorkflowHistory() throws Exception {
        // Initialize the database
        requestWorkflowHistoryRepository.saveAndFlush(requestWorkflowHistory);

        // Get the requestWorkflowHistory
        restRequestWorkflowHistoryMockMvc.perform(get("/api/requestWorkflowHistorys/{id}", requestWorkflowHistory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(requestWorkflowHistory.getId().intValue()))
            .andExpect(jsonPath("$.requestStage").value(DEFAULT_REQUEST_STAGE))
            .andExpect(jsonPath("$.assignedDate").value(DEFAULT_ASSIGNED_DATE_STR))
            .andExpect(jsonPath("$.actionedDate").value(DEFAULT_ACTIONED_DATE_STR))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.toString()))
            .andExpect(jsonPath("$.assignedRole").value(DEFAULT_ASSIGNED_ROLE))
            .andExpect(jsonPath("$.domainObject").value(DEFAULT_DOMAIN_OBJECT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingRequestWorkflowHistory() throws Exception {
        // Get the requestWorkflowHistory
        restRequestWorkflowHistoryMockMvc.perform(get("/api/requestWorkflowHistorys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestWorkflowHistory() throws Exception {
        // Initialize the database
        requestWorkflowHistoryRepository.saveAndFlush(requestWorkflowHistory);

		int databaseSizeBeforeUpdate = requestWorkflowHistoryRepository.findAll().size();

        // Update the requestWorkflowHistory
        requestWorkflowHistory.setRequestStage(UPDATED_REQUEST_STAGE);
        requestWorkflowHistory.setAssignedDate(UPDATED_ASSIGNED_DATE);
        requestWorkflowHistory.setActionedDate(UPDATED_ACTIONED_DATE);
        requestWorkflowHistory.setRemarks(UPDATED_REMARKS);
        requestWorkflowHistory.setIpAddress(UPDATED_IP_ADDRESS);
        requestWorkflowHistory.setAssignedRole(UPDATED_ASSIGNED_ROLE);
        requestWorkflowHistory.setDomainObject(UPDATED_DOMAIN_OBJECT);

        restRequestWorkflowHistoryMockMvc.perform(put("/api/requestWorkflowHistorys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(requestWorkflowHistory)))
                .andExpect(status().isOk());

        // Validate the RequestWorkflowHistory in the database
        List<RequestWorkflowHistory> requestWorkflowHistorys = requestWorkflowHistoryRepository.findAll();
        assertThat(requestWorkflowHistorys).hasSize(databaseSizeBeforeUpdate);
        RequestWorkflowHistory testRequestWorkflowHistory = requestWorkflowHistorys.get(requestWorkflowHistorys.size() - 1);
        assertThat(testRequestWorkflowHistory.getRequestStage()).isEqualTo(UPDATED_REQUEST_STAGE);
        assertThat(testRequestWorkflowHistory.getAssignedDate()).isEqualTo(UPDATED_ASSIGNED_DATE);
        assertThat(testRequestWorkflowHistory.getActionedDate()).isEqualTo(UPDATED_ACTIONED_DATE);
        assertThat(testRequestWorkflowHistory.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testRequestWorkflowHistory.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testRequestWorkflowHistory.getAssignedRole()).isEqualTo(UPDATED_ASSIGNED_ROLE);
        assertThat(testRequestWorkflowHistory.getDomainObject()).isEqualTo(UPDATED_DOMAIN_OBJECT);
    }

    @Test
    @Transactional
    public void deleteRequestWorkflowHistory() throws Exception {
        // Initialize the database
        requestWorkflowHistoryRepository.saveAndFlush(requestWorkflowHistory);

		int databaseSizeBeforeDelete = requestWorkflowHistoryRepository.findAll().size();

        // Get the requestWorkflowHistory
        restRequestWorkflowHistoryMockMvc.perform(delete("/api/requestWorkflowHistorys/{id}", requestWorkflowHistory.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RequestWorkflowHistory> requestWorkflowHistorys = requestWorkflowHistoryRepository.findAll();
        assertThat(requestWorkflowHistorys).hasSize(databaseSizeBeforeDelete - 1);
    }
}

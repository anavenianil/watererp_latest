package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WorkflowTxnDetails;
import com.callippus.water.erp.repository.WorkflowTxnDetailsRepository;

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
 * Test class for the WorkflowTxnDetailsResource REST controller.
 *
 * @see WorkflowTxnDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowTxnDetailsResourceIntTest {


    private static final Integer DEFAULT_REQUEST_ID = 1;
    private static final Integer UPDATED_REQUEST_ID = 2;
    private static final String DEFAULT_REFERENCE_NUMBER = "AAAAA";
    private static final String UPDATED_REFERENCE_NUMBER = "BBBBB";

    private static final Integer DEFAULT_ROW_NUMBER = 1;
    private static final Integer UPDATED_ROW_NUMBER = 2;
    private static final String DEFAULT_COLUMN_NAME = "AAAAA";
    private static final String UPDATED_COLUMN_NAME = "BBBBB";
    private static final String DEFAULT_PREVIOUS_VALUE = "AAAAA";
    private static final String UPDATED_PREVIOUS_VALUE = "BBBBB";
    private static final String DEFAULT_NEW_VALUE = "AAAAA";
    private static final String UPDATED_NEW_VALUE = "BBBBB";
    private static final String DEFAULT_IP_ADDRESS = "AAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private WorkflowTxnDetailsRepository workflowTxnDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowTxnDetailsMockMvc;

    private WorkflowTxnDetails workflowTxnDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowTxnDetailsResource workflowTxnDetailsResource = new WorkflowTxnDetailsResource();
        ReflectionTestUtils.setField(workflowTxnDetailsResource, "workflowTxnDetailsRepository", workflowTxnDetailsRepository);
        this.restWorkflowTxnDetailsMockMvc = MockMvcBuilders.standaloneSetup(workflowTxnDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflowTxnDetails = new WorkflowTxnDetails();
        workflowTxnDetails.setRequestId(DEFAULT_REQUEST_ID);
        workflowTxnDetails.setReferenceNumber(DEFAULT_REFERENCE_NUMBER);
        workflowTxnDetails.setRowNumber(DEFAULT_ROW_NUMBER);
        workflowTxnDetails.setColumnName(DEFAULT_COLUMN_NAME);
        workflowTxnDetails.setPreviousValue(DEFAULT_PREVIOUS_VALUE);
        workflowTxnDetails.setNewValue(DEFAULT_NEW_VALUE);
        workflowTxnDetails.setIpAddress(DEFAULT_IP_ADDRESS);
        workflowTxnDetails.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createWorkflowTxnDetails() throws Exception {
        int databaseSizeBeforeCreate = workflowTxnDetailsRepository.findAll().size();

        // Create the WorkflowTxnDetails

        restWorkflowTxnDetailsMockMvc.perform(post("/api/workflowTxnDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowTxnDetails)))
                .andExpect(status().isCreated());

        // Validate the WorkflowTxnDetails in the database
        List<WorkflowTxnDetails> workflowTxnDetailss = workflowTxnDetailsRepository.findAll();
        assertThat(workflowTxnDetailss).hasSize(databaseSizeBeforeCreate + 1);
        WorkflowTxnDetails testWorkflowTxnDetails = workflowTxnDetailss.get(workflowTxnDetailss.size() - 1);
        assertThat(testWorkflowTxnDetails.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testWorkflowTxnDetails.getReferenceNumber()).isEqualTo(DEFAULT_REFERENCE_NUMBER);
        assertThat(testWorkflowTxnDetails.getRowNumber()).isEqualTo(DEFAULT_ROW_NUMBER);
        assertThat(testWorkflowTxnDetails.getColumnName()).isEqualTo(DEFAULT_COLUMN_NAME);
        assertThat(testWorkflowTxnDetails.getPreviousValue()).isEqualTo(DEFAULT_PREVIOUS_VALUE);
        assertThat(testWorkflowTxnDetails.getNewValue()).isEqualTo(DEFAULT_NEW_VALUE);
        assertThat(testWorkflowTxnDetails.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testWorkflowTxnDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllWorkflowTxnDetailss() throws Exception {
        // Initialize the database
        workflowTxnDetailsRepository.saveAndFlush(workflowTxnDetails);

        // Get all the workflowTxnDetailss
        restWorkflowTxnDetailsMockMvc.perform(get("/api/workflowTxnDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflowTxnDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].requestId").value(hasItem(DEFAULT_REQUEST_ID)))
                .andExpect(jsonPath("$.[*].referenceNumber").value(hasItem(DEFAULT_REFERENCE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].rowNumber").value(hasItem(DEFAULT_ROW_NUMBER)))
                .andExpect(jsonPath("$.[*].columnName").value(hasItem(DEFAULT_COLUMN_NAME.toString())))
                .andExpect(jsonPath("$.[*].previousValue").value(hasItem(DEFAULT_PREVIOUS_VALUE.toString())))
                .andExpect(jsonPath("$.[*].newValue").value(hasItem(DEFAULT_NEW_VALUE.toString())))
                .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getWorkflowTxnDetails() throws Exception {
        // Initialize the database
        workflowTxnDetailsRepository.saveAndFlush(workflowTxnDetails);

        // Get the workflowTxnDetails
        restWorkflowTxnDetailsMockMvc.perform(get("/api/workflowTxnDetailss/{id}", workflowTxnDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflowTxnDetails.getId().intValue()))
            .andExpect(jsonPath("$.requestId").value(DEFAULT_REQUEST_ID))
            .andExpect(jsonPath("$.referenceNumber").value(DEFAULT_REFERENCE_NUMBER.toString()))
            .andExpect(jsonPath("$.rowNumber").value(DEFAULT_ROW_NUMBER))
            .andExpect(jsonPath("$.columnName").value(DEFAULT_COLUMN_NAME.toString()))
            .andExpect(jsonPath("$.previousValue").value(DEFAULT_PREVIOUS_VALUE.toString()))
            .andExpect(jsonPath("$.newValue").value(DEFAULT_NEW_VALUE.toString()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflowTxnDetails() throws Exception {
        // Get the workflowTxnDetails
        restWorkflowTxnDetailsMockMvc.perform(get("/api/workflowTxnDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflowTxnDetails() throws Exception {
        // Initialize the database
        workflowTxnDetailsRepository.saveAndFlush(workflowTxnDetails);

		int databaseSizeBeforeUpdate = workflowTxnDetailsRepository.findAll().size();

        // Update the workflowTxnDetails
        workflowTxnDetails.setRequestId(UPDATED_REQUEST_ID);
        workflowTxnDetails.setReferenceNumber(UPDATED_REFERENCE_NUMBER);
        workflowTxnDetails.setRowNumber(UPDATED_ROW_NUMBER);
        workflowTxnDetails.setColumnName(UPDATED_COLUMN_NAME);
        workflowTxnDetails.setPreviousValue(UPDATED_PREVIOUS_VALUE);
        workflowTxnDetails.setNewValue(UPDATED_NEW_VALUE);
        workflowTxnDetails.setIpAddress(UPDATED_IP_ADDRESS);
        workflowTxnDetails.setDescription(UPDATED_DESCRIPTION);

        restWorkflowTxnDetailsMockMvc.perform(put("/api/workflowTxnDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowTxnDetails)))
                .andExpect(status().isOk());

        // Validate the WorkflowTxnDetails in the database
        List<WorkflowTxnDetails> workflowTxnDetailss = workflowTxnDetailsRepository.findAll();
        assertThat(workflowTxnDetailss).hasSize(databaseSizeBeforeUpdate);
        WorkflowTxnDetails testWorkflowTxnDetails = workflowTxnDetailss.get(workflowTxnDetailss.size() - 1);
        assertThat(testWorkflowTxnDetails.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testWorkflowTxnDetails.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
        assertThat(testWorkflowTxnDetails.getRowNumber()).isEqualTo(UPDATED_ROW_NUMBER);
        assertThat(testWorkflowTxnDetails.getColumnName()).isEqualTo(UPDATED_COLUMN_NAME);
        assertThat(testWorkflowTxnDetails.getPreviousValue()).isEqualTo(UPDATED_PREVIOUS_VALUE);
        assertThat(testWorkflowTxnDetails.getNewValue()).isEqualTo(UPDATED_NEW_VALUE);
        assertThat(testWorkflowTxnDetails.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testWorkflowTxnDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteWorkflowTxnDetails() throws Exception {
        // Initialize the database
        workflowTxnDetailsRepository.saveAndFlush(workflowTxnDetails);

		int databaseSizeBeforeDelete = workflowTxnDetailsRepository.findAll().size();

        // Get the workflowTxnDetails
        restWorkflowTxnDetailsMockMvc.perform(delete("/api/workflowTxnDetailss/{id}", workflowTxnDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkflowTxnDetails> workflowTxnDetailss = workflowTxnDetailsRepository.findAll();
        assertThat(workflowTxnDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

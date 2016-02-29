package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WorkflowMaster;
import com.callippus.water.erp.repository.WorkflowMasterRepository;

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
 * Test class for the WorkflowMasterResource REST controller.
 *
 * @see WorkflowMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_WORKFLOW_NAME = "AAAAA";
    private static final String UPDATED_WORKFLOW_NAME = "BBBBB";

    private static final Integer DEFAULT_TO_WORKFLOW = 1;
    private static final Integer UPDATED_TO_WORKFLOW = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private WorkflowMasterRepository workflowMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowMasterMockMvc;

    private WorkflowMaster workflowMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowMasterResource workflowMasterResource = new WorkflowMasterResource();
        ReflectionTestUtils.setField(workflowMasterResource, "workflowMasterRepository", workflowMasterRepository);
        this.restWorkflowMasterMockMvc = MockMvcBuilders.standaloneSetup(workflowMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflowMaster = new WorkflowMaster();
        workflowMaster.setWorkflowName(DEFAULT_WORKFLOW_NAME);
        workflowMaster.setToWorkflow(DEFAULT_TO_WORKFLOW);
        workflowMaster.setCreationDate(DEFAULT_CREATION_DATE);
        workflowMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createWorkflowMaster() throws Exception {
        int databaseSizeBeforeCreate = workflowMasterRepository.findAll().size();

        // Create the WorkflowMaster

        restWorkflowMasterMockMvc.perform(post("/api/workflowMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowMaster)))
                .andExpect(status().isCreated());

        // Validate the WorkflowMaster in the database
        List<WorkflowMaster> workflowMasters = workflowMasterRepository.findAll();
        assertThat(workflowMasters).hasSize(databaseSizeBeforeCreate + 1);
        WorkflowMaster testWorkflowMaster = workflowMasters.get(workflowMasters.size() - 1);
        assertThat(testWorkflowMaster.getWorkflowName()).isEqualTo(DEFAULT_WORKFLOW_NAME);
        assertThat(testWorkflowMaster.getToWorkflow()).isEqualTo(DEFAULT_TO_WORKFLOW);
        assertThat(testWorkflowMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testWorkflowMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void checkWorkflowNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = workflowMasterRepository.findAll().size();
        // set the field null
        workflowMaster.setWorkflowName(null);

        // Create the WorkflowMaster, which fails.

        restWorkflowMasterMockMvc.perform(post("/api/workflowMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowMaster)))
                .andExpect(status().isBadRequest());

        List<WorkflowMaster> workflowMasters = workflowMasterRepository.findAll();
        assertThat(workflowMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWorkflowMasters() throws Exception {
        // Initialize the database
        workflowMasterRepository.saveAndFlush(workflowMaster);

        // Get all the workflowMasters
        restWorkflowMasterMockMvc.perform(get("/api/workflowMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflowMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].workflowName").value(hasItem(DEFAULT_WORKFLOW_NAME.toString())))
                .andExpect(jsonPath("$.[*].toWorkflow").value(hasItem(DEFAULT_TO_WORKFLOW)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getWorkflowMaster() throws Exception {
        // Initialize the database
        workflowMasterRepository.saveAndFlush(workflowMaster);

        // Get the workflowMaster
        restWorkflowMasterMockMvc.perform(get("/api/workflowMasters/{id}", workflowMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflowMaster.getId().intValue()))
            .andExpect(jsonPath("$.workflowName").value(DEFAULT_WORKFLOW_NAME.toString()))
            .andExpect(jsonPath("$.toWorkflow").value(DEFAULT_TO_WORKFLOW))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflowMaster() throws Exception {
        // Get the workflowMaster
        restWorkflowMasterMockMvc.perform(get("/api/workflowMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflowMaster() throws Exception {
        // Initialize the database
        workflowMasterRepository.saveAndFlush(workflowMaster);

		int databaseSizeBeforeUpdate = workflowMasterRepository.findAll().size();

        // Update the workflowMaster
        workflowMaster.setWorkflowName(UPDATED_WORKFLOW_NAME);
        workflowMaster.setToWorkflow(UPDATED_TO_WORKFLOW);
        workflowMaster.setCreationDate(UPDATED_CREATION_DATE);
        workflowMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restWorkflowMasterMockMvc.perform(put("/api/workflowMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowMaster)))
                .andExpect(status().isOk());

        // Validate the WorkflowMaster in the database
        List<WorkflowMaster> workflowMasters = workflowMasterRepository.findAll();
        assertThat(workflowMasters).hasSize(databaseSizeBeforeUpdate);
        WorkflowMaster testWorkflowMaster = workflowMasters.get(workflowMasters.size() - 1);
        assertThat(testWorkflowMaster.getWorkflowName()).isEqualTo(UPDATED_WORKFLOW_NAME);
        assertThat(testWorkflowMaster.getToWorkflow()).isEqualTo(UPDATED_TO_WORKFLOW);
        assertThat(testWorkflowMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testWorkflowMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteWorkflowMaster() throws Exception {
        // Initialize the database
        workflowMasterRepository.saveAndFlush(workflowMaster);

		int databaseSizeBeforeDelete = workflowMasterRepository.findAll().size();

        // Get the workflowMaster
        restWorkflowMasterMockMvc.perform(delete("/api/workflowMasters/{id}", workflowMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkflowMaster> workflowMasters = workflowMasterRepository.findAll();
        assertThat(workflowMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

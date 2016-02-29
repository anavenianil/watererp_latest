package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Workflow;
import com.callippus.water.erp.repository.WorkflowRepository;

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
 * Test class for the WorkflowResource REST controller.
 *
 * @see WorkflowResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowResourceIntTest {


    private static final Integer DEFAULT_STAGE_ID = 1;
    private static final Integer UPDATED_STAGE_ID = 2;

    @Inject
    private WorkflowRepository workflowRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowMockMvc;

    private Workflow workflow;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowResource workflowResource = new WorkflowResource();
        ReflectionTestUtils.setField(workflowResource, "workflowRepository", workflowRepository);
        this.restWorkflowMockMvc = MockMvcBuilders.standaloneSetup(workflowResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflow = new Workflow();
        workflow.setStageId(DEFAULT_STAGE_ID);
    }

    @Test
    @Transactional
    public void createWorkflow() throws Exception {
        int databaseSizeBeforeCreate = workflowRepository.findAll().size();

        // Create the Workflow

        restWorkflowMockMvc.perform(post("/api/workflows")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflow)))
                .andExpect(status().isCreated());

        // Validate the Workflow in the database
        List<Workflow> workflows = workflowRepository.findAll();
        assertThat(workflows).hasSize(databaseSizeBeforeCreate + 1);
        Workflow testWorkflow = workflows.get(workflows.size() - 1);
        assertThat(testWorkflow.getStageId()).isEqualTo(DEFAULT_STAGE_ID);
    }

    @Test
    @Transactional
    public void getAllWorkflows() throws Exception {
        // Initialize the database
        workflowRepository.saveAndFlush(workflow);

        // Get all the workflows
        restWorkflowMockMvc.perform(get("/api/workflows?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflow.getId().intValue())))
                .andExpect(jsonPath("$.[*].stageId").value(hasItem(DEFAULT_STAGE_ID)));
    }

    @Test
    @Transactional
    public void getWorkflow() throws Exception {
        // Initialize the database
        workflowRepository.saveAndFlush(workflow);

        // Get the workflow
        restWorkflowMockMvc.perform(get("/api/workflows/{id}", workflow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflow.getId().intValue()))
            .andExpect(jsonPath("$.stageId").value(DEFAULT_STAGE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflow() throws Exception {
        // Get the workflow
        restWorkflowMockMvc.perform(get("/api/workflows/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflow() throws Exception {
        // Initialize the database
        workflowRepository.saveAndFlush(workflow);

		int databaseSizeBeforeUpdate = workflowRepository.findAll().size();

        // Update the workflow
        workflow.setStageId(UPDATED_STAGE_ID);

        restWorkflowMockMvc.perform(put("/api/workflows")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflow)))
                .andExpect(status().isOk());

        // Validate the Workflow in the database
        List<Workflow> workflows = workflowRepository.findAll();
        assertThat(workflows).hasSize(databaseSizeBeforeUpdate);
        Workflow testWorkflow = workflows.get(workflows.size() - 1);
        assertThat(testWorkflow.getStageId()).isEqualTo(UPDATED_STAGE_ID);
    }

    @Test
    @Transactional
    public void deleteWorkflow() throws Exception {
        // Initialize the database
        workflowRepository.saveAndFlush(workflow);

		int databaseSizeBeforeDelete = workflowRepository.findAll().size();

        // Get the workflow
        restWorkflowMockMvc.perform(delete("/api/workflows/{id}", workflow.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Workflow> workflows = workflowRepository.findAll();
        assertThat(workflows).hasSize(databaseSizeBeforeDelete - 1);
    }
}

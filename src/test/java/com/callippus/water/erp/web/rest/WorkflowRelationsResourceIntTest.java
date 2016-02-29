package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WorkflowRelations;
import com.callippus.water.erp.repository.WorkflowRelationsRepository;

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
 * Test class for the WorkflowRelationsResource REST controller.
 *
 * @see WorkflowRelationsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowRelationsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private WorkflowRelationsRepository workflowRelationsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowRelationsMockMvc;

    private WorkflowRelations workflowRelations;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowRelationsResource workflowRelationsResource = new WorkflowRelationsResource();
        ReflectionTestUtils.setField(workflowRelationsResource, "workflowRelationsRepository", workflowRelationsRepository);
        this.restWorkflowRelationsMockMvc = MockMvcBuilders.standaloneSetup(workflowRelationsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflowRelations = new WorkflowRelations();
        workflowRelations.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWorkflowRelations() throws Exception {
        int databaseSizeBeforeCreate = workflowRelationsRepository.findAll().size();

        // Create the WorkflowRelations

        restWorkflowRelationsMockMvc.perform(post("/api/workflowRelationss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowRelations)))
                .andExpect(status().isCreated());

        // Validate the WorkflowRelations in the database
        List<WorkflowRelations> workflowRelationss = workflowRelationsRepository.findAll();
        assertThat(workflowRelationss).hasSize(databaseSizeBeforeCreate + 1);
        WorkflowRelations testWorkflowRelations = workflowRelationss.get(workflowRelationss.size() - 1);
        assertThat(testWorkflowRelations.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllWorkflowRelationss() throws Exception {
        // Initialize the database
        workflowRelationsRepository.saveAndFlush(workflowRelations);

        // Get all the workflowRelationss
        restWorkflowRelationsMockMvc.perform(get("/api/workflowRelationss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflowRelations.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getWorkflowRelations() throws Exception {
        // Initialize the database
        workflowRelationsRepository.saveAndFlush(workflowRelations);

        // Get the workflowRelations
        restWorkflowRelationsMockMvc.perform(get("/api/workflowRelationss/{id}", workflowRelations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflowRelations.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflowRelations() throws Exception {
        // Get the workflowRelations
        restWorkflowRelationsMockMvc.perform(get("/api/workflowRelationss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflowRelations() throws Exception {
        // Initialize the database
        workflowRelationsRepository.saveAndFlush(workflowRelations);

		int databaseSizeBeforeUpdate = workflowRelationsRepository.findAll().size();

        // Update the workflowRelations
        workflowRelations.setName(UPDATED_NAME);

        restWorkflowRelationsMockMvc.perform(put("/api/workflowRelationss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowRelations)))
                .andExpect(status().isOk());

        // Validate the WorkflowRelations in the database
        List<WorkflowRelations> workflowRelationss = workflowRelationsRepository.findAll();
        assertThat(workflowRelationss).hasSize(databaseSizeBeforeUpdate);
        WorkflowRelations testWorkflowRelations = workflowRelationss.get(workflowRelationss.size() - 1);
        assertThat(testWorkflowRelations.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteWorkflowRelations() throws Exception {
        // Initialize the database
        workflowRelationsRepository.saveAndFlush(workflowRelations);

		int databaseSizeBeforeDelete = workflowRelationsRepository.findAll().size();

        // Get the workflowRelations
        restWorkflowRelationsMockMvc.perform(delete("/api/workflowRelationss/{id}", workflowRelations.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkflowRelations> workflowRelationss = workflowRelationsRepository.findAll();
        assertThat(workflowRelationss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

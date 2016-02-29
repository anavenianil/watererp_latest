package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WorkflowRelationships;
import com.callippus.water.erp.repository.WorkflowRelationshipsRepository;

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
 * Test class for the WorkflowRelationshipsResource REST controller.
 *
 * @see WorkflowRelationshipsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowRelationshipsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private WorkflowRelationshipsRepository workflowRelationshipsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowRelationshipsMockMvc;

    private WorkflowRelationships workflowRelationships;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowRelationshipsResource workflowRelationshipsResource = new WorkflowRelationshipsResource();
        ReflectionTestUtils.setField(workflowRelationshipsResource, "workflowRelationshipsRepository", workflowRelationshipsRepository);
        this.restWorkflowRelationshipsMockMvc = MockMvcBuilders.standaloneSetup(workflowRelationshipsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflowRelationships = new WorkflowRelationships();
        workflowRelationships.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createWorkflowRelationships() throws Exception {
        int databaseSizeBeforeCreate = workflowRelationshipsRepository.findAll().size();

        // Create the WorkflowRelationships

        restWorkflowRelationshipsMockMvc.perform(post("/api/workflowRelationshipss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowRelationships)))
                .andExpect(status().isCreated());

        // Validate the WorkflowRelationships in the database
        List<WorkflowRelationships> workflowRelationshipss = workflowRelationshipsRepository.findAll();
        assertThat(workflowRelationshipss).hasSize(databaseSizeBeforeCreate + 1);
        WorkflowRelationships testWorkflowRelationships = workflowRelationshipss.get(workflowRelationshipss.size() - 1);
        assertThat(testWorkflowRelationships.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllWorkflowRelationshipss() throws Exception {
        // Initialize the database
        workflowRelationshipsRepository.saveAndFlush(workflowRelationships);

        // Get all the workflowRelationshipss
        restWorkflowRelationshipsMockMvc.perform(get("/api/workflowRelationshipss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflowRelationships.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getWorkflowRelationships() throws Exception {
        // Initialize the database
        workflowRelationshipsRepository.saveAndFlush(workflowRelationships);

        // Get the workflowRelationships
        restWorkflowRelationshipsMockMvc.perform(get("/api/workflowRelationshipss/{id}", workflowRelationships.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflowRelationships.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflowRelationships() throws Exception {
        // Get the workflowRelationships
        restWorkflowRelationshipsMockMvc.perform(get("/api/workflowRelationshipss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflowRelationships() throws Exception {
        // Initialize the database
        workflowRelationshipsRepository.saveAndFlush(workflowRelationships);

		int databaseSizeBeforeUpdate = workflowRelationshipsRepository.findAll().size();

        // Update the workflowRelationships
        workflowRelationships.setName(UPDATED_NAME);

        restWorkflowRelationshipsMockMvc.perform(put("/api/workflowRelationshipss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowRelationships)))
                .andExpect(status().isOk());

        // Validate the WorkflowRelationships in the database
        List<WorkflowRelationships> workflowRelationshipss = workflowRelationshipsRepository.findAll();
        assertThat(workflowRelationshipss).hasSize(databaseSizeBeforeUpdate);
        WorkflowRelationships testWorkflowRelationships = workflowRelationshipss.get(workflowRelationshipss.size() - 1);
        assertThat(testWorkflowRelationships.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteWorkflowRelationships() throws Exception {
        // Initialize the database
        workflowRelationshipsRepository.saveAndFlush(workflowRelationships);

		int databaseSizeBeforeDelete = workflowRelationshipsRepository.findAll().size();

        // Get the workflowRelationships
        restWorkflowRelationshipsMockMvc.perform(delete("/api/workflowRelationshipss/{id}", workflowRelationships.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkflowRelationships> workflowRelationshipss = workflowRelationshipsRepository.findAll();
        assertThat(workflowRelationshipss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

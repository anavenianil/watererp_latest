package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WorkflowStageMaster;
import com.callippus.water.erp.repository.WorkflowStageMasterRepository;

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
 * Test class for the WorkflowStageMasterResource REST controller.
 *
 * @see WorkflowStageMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowStageMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private WorkflowStageMasterRepository workflowStageMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowStageMasterMockMvc;

    private WorkflowStageMaster workflowStageMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowStageMasterResource workflowStageMasterResource = new WorkflowStageMasterResource();
        ReflectionTestUtils.setField(workflowStageMasterResource, "workflowStageMasterRepository", workflowStageMasterRepository);
        this.restWorkflowStageMasterMockMvc = MockMvcBuilders.standaloneSetup(workflowStageMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflowStageMaster = new WorkflowStageMaster();
        workflowStageMaster.setName(DEFAULT_NAME);
        workflowStageMaster.setCreationDate(DEFAULT_CREATION_DATE);
        workflowStageMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        workflowStageMaster.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createWorkflowStageMaster() throws Exception {
        int databaseSizeBeforeCreate = workflowStageMasterRepository.findAll().size();

        // Create the WorkflowStageMaster

        restWorkflowStageMasterMockMvc.perform(post("/api/workflowStageMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowStageMaster)))
                .andExpect(status().isCreated());

        // Validate the WorkflowStageMaster in the database
        List<WorkflowStageMaster> workflowStageMasters = workflowStageMasterRepository.findAll();
        assertThat(workflowStageMasters).hasSize(databaseSizeBeforeCreate + 1);
        WorkflowStageMaster testWorkflowStageMaster = workflowStageMasters.get(workflowStageMasters.size() - 1);
        assertThat(testWorkflowStageMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWorkflowStageMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testWorkflowStageMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testWorkflowStageMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllWorkflowStageMasters() throws Exception {
        // Initialize the database
        workflowStageMasterRepository.saveAndFlush(workflowStageMaster);

        // Get all the workflowStageMasters
        restWorkflowStageMasterMockMvc.perform(get("/api/workflowStageMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflowStageMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getWorkflowStageMaster() throws Exception {
        // Initialize the database
        workflowStageMasterRepository.saveAndFlush(workflowStageMaster);

        // Get the workflowStageMaster
        restWorkflowStageMasterMockMvc.perform(get("/api/workflowStageMasters/{id}", workflowStageMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflowStageMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflowStageMaster() throws Exception {
        // Get the workflowStageMaster
        restWorkflowStageMasterMockMvc.perform(get("/api/workflowStageMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflowStageMaster() throws Exception {
        // Initialize the database
        workflowStageMasterRepository.saveAndFlush(workflowStageMaster);

		int databaseSizeBeforeUpdate = workflowStageMasterRepository.findAll().size();

        // Update the workflowStageMaster
        workflowStageMaster.setName(UPDATED_NAME);
        workflowStageMaster.setCreationDate(UPDATED_CREATION_DATE);
        workflowStageMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        workflowStageMaster.setDescription(UPDATED_DESCRIPTION);

        restWorkflowStageMasterMockMvc.perform(put("/api/workflowStageMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowStageMaster)))
                .andExpect(status().isOk());

        // Validate the WorkflowStageMaster in the database
        List<WorkflowStageMaster> workflowStageMasters = workflowStageMasterRepository.findAll();
        assertThat(workflowStageMasters).hasSize(databaseSizeBeforeUpdate);
        WorkflowStageMaster testWorkflowStageMaster = workflowStageMasters.get(workflowStageMasters.size() - 1);
        assertThat(testWorkflowStageMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWorkflowStageMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testWorkflowStageMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testWorkflowStageMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteWorkflowStageMaster() throws Exception {
        // Initialize the database
        workflowStageMasterRepository.saveAndFlush(workflowStageMaster);

		int databaseSizeBeforeDelete = workflowStageMasterRepository.findAll().size();

        // Get the workflowStageMaster
        restWorkflowStageMasterMockMvc.perform(delete("/api/workflowStageMasters/{id}", workflowStageMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkflowStageMaster> workflowStageMasters = workflowStageMasterRepository.findAll();
        assertThat(workflowStageMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

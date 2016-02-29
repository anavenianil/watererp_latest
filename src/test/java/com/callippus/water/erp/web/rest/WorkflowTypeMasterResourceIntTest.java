package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.WorkflowTypeMaster;
import com.callippus.water.erp.repository.WorkflowTypeMasterRepository;

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
 * Test class for the WorkflowTypeMasterResource REST controller.
 *
 * @see WorkflowTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class WorkflowTypeMasterResourceIntTest {

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
    private WorkflowTypeMasterRepository workflowTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restWorkflowTypeMasterMockMvc;

    private WorkflowTypeMaster workflowTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        WorkflowTypeMasterResource workflowTypeMasterResource = new WorkflowTypeMasterResource();
        ReflectionTestUtils.setField(workflowTypeMasterResource, "workflowTypeMasterRepository", workflowTypeMasterRepository);
        this.restWorkflowTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(workflowTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        workflowTypeMaster = new WorkflowTypeMaster();
        workflowTypeMaster.setName(DEFAULT_NAME);
        workflowTypeMaster.setCreationDate(DEFAULT_CREATION_DATE);
        workflowTypeMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        workflowTypeMaster.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createWorkflowTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = workflowTypeMasterRepository.findAll().size();

        // Create the WorkflowTypeMaster

        restWorkflowTypeMasterMockMvc.perform(post("/api/workflowTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the WorkflowTypeMaster in the database
        List<WorkflowTypeMaster> workflowTypeMasters = workflowTypeMasterRepository.findAll();
        assertThat(workflowTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        WorkflowTypeMaster testWorkflowTypeMaster = workflowTypeMasters.get(workflowTypeMasters.size() - 1);
        assertThat(testWorkflowTypeMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWorkflowTypeMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testWorkflowTypeMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testWorkflowTypeMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllWorkflowTypeMasters() throws Exception {
        // Initialize the database
        workflowTypeMasterRepository.saveAndFlush(workflowTypeMaster);

        // Get all the workflowTypeMasters
        restWorkflowTypeMasterMockMvc.perform(get("/api/workflowTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(workflowTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getWorkflowTypeMaster() throws Exception {
        // Initialize the database
        workflowTypeMasterRepository.saveAndFlush(workflowTypeMaster);

        // Get the workflowTypeMaster
        restWorkflowTypeMasterMockMvc.perform(get("/api/workflowTypeMasters/{id}", workflowTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(workflowTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWorkflowTypeMaster() throws Exception {
        // Get the workflowTypeMaster
        restWorkflowTypeMasterMockMvc.perform(get("/api/workflowTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWorkflowTypeMaster() throws Exception {
        // Initialize the database
        workflowTypeMasterRepository.saveAndFlush(workflowTypeMaster);

		int databaseSizeBeforeUpdate = workflowTypeMasterRepository.findAll().size();

        // Update the workflowTypeMaster
        workflowTypeMaster.setName(UPDATED_NAME);
        workflowTypeMaster.setCreationDate(UPDATED_CREATION_DATE);
        workflowTypeMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        workflowTypeMaster.setDescription(UPDATED_DESCRIPTION);

        restWorkflowTypeMasterMockMvc.perform(put("/api/workflowTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(workflowTypeMaster)))
                .andExpect(status().isOk());

        // Validate the WorkflowTypeMaster in the database
        List<WorkflowTypeMaster> workflowTypeMasters = workflowTypeMasterRepository.findAll();
        assertThat(workflowTypeMasters).hasSize(databaseSizeBeforeUpdate);
        WorkflowTypeMaster testWorkflowTypeMaster = workflowTypeMasters.get(workflowTypeMasters.size() - 1);
        assertThat(testWorkflowTypeMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWorkflowTypeMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testWorkflowTypeMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testWorkflowTypeMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteWorkflowTypeMaster() throws Exception {
        // Initialize the database
        workflowTypeMasterRepository.saveAndFlush(workflowTypeMaster);

		int databaseSizeBeforeDelete = workflowTypeMasterRepository.findAll().size();

        // Get the workflowTypeMaster
        restWorkflowTypeMasterMockMvc.perform(delete("/api/workflowTypeMasters/{id}", workflowTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<WorkflowTypeMaster> workflowTypeMasters = workflowTypeMasterRepository.findAll();
        assertThat(workflowTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

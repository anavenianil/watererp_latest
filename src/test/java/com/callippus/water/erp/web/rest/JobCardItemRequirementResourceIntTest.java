package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.JobCardItemRequirement;
import com.callippus.water.erp.repository.JobCardItemRequirementRepository;

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
 * Test class for the JobCardItemRequirementResource REST controller.
 *
 * @see JobCardItemRequirementResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class JobCardItemRequirementResourceIntTest {


    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;
    private static final String DEFAULT_REPLACE_LENGTH = "AAAAA";
    private static final String UPDATED_REPLACE_LENGTH = "BBBBB";
    private static final String DEFAULT_CASCADE_CLAMP = "AAAAA";
    private static final String UPDATED_CASCADE_CLAMP = "BBBBB";

    private static final Integer DEFAULT_NO_OF_SECTION = 1;
    private static final Integer UPDATED_NO_OF_SECTION = 2;

    private static final Integer DEFAULT_NO_OF_CLAMPS = 1;
    private static final Integer UPDATED_NO_OF_CLAMPS = 2;
    private static final String DEFAULT_TYPE = "AAAAA";
    private static final String UPDATED_TYPE = "BBBBB";

    private static final Long DEFAULT_DOMAIN_OBJECT = 1L;
    private static final Long UPDATED_DOMAIN_OBJECT = 2L;

    @Inject
    private JobCardItemRequirementRepository jobCardItemRequirementRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restJobCardItemRequirementMockMvc;

    private JobCardItemRequirement jobCardItemRequirement;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JobCardItemRequirementResource jobCardItemRequirementResource = new JobCardItemRequirementResource();
        ReflectionTestUtils.setField(jobCardItemRequirementResource, "jobCardItemRequirementRepository", jobCardItemRequirementRepository);
        this.restJobCardItemRequirementMockMvc = MockMvcBuilders.standaloneSetup(jobCardItemRequirementResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        jobCardItemRequirement = new JobCardItemRequirement();
        jobCardItemRequirement.setQuantity(DEFAULT_QUANTITY);
        jobCardItemRequirement.setReplaceLength(DEFAULT_REPLACE_LENGTH);
        jobCardItemRequirement.setCascadeClamp(DEFAULT_CASCADE_CLAMP);
        jobCardItemRequirement.setNoOfSection(DEFAULT_NO_OF_SECTION);
        jobCardItemRequirement.setNoOfClamps(DEFAULT_NO_OF_CLAMPS);
        jobCardItemRequirement.setType(DEFAULT_TYPE);
        jobCardItemRequirement.setDomainObject(DEFAULT_DOMAIN_OBJECT);
    }

    @Test
    @Transactional
    public void createJobCardItemRequirement() throws Exception {
        int databaseSizeBeforeCreate = jobCardItemRequirementRepository.findAll().size();

        // Create the JobCardItemRequirement

        restJobCardItemRequirementMockMvc.perform(post("/api/jobCardItemRequirements")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jobCardItemRequirement)))
                .andExpect(status().isCreated());

        // Validate the JobCardItemRequirement in the database
        List<JobCardItemRequirement> jobCardItemRequirements = jobCardItemRequirementRepository.findAll();
        assertThat(jobCardItemRequirements).hasSize(databaseSizeBeforeCreate + 1);
        JobCardItemRequirement testJobCardItemRequirement = jobCardItemRequirements.get(jobCardItemRequirements.size() - 1);
        assertThat(testJobCardItemRequirement.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testJobCardItemRequirement.getReplaceLength()).isEqualTo(DEFAULT_REPLACE_LENGTH);
        assertThat(testJobCardItemRequirement.getCascadeClamp()).isEqualTo(DEFAULT_CASCADE_CLAMP);
        assertThat(testJobCardItemRequirement.getNoOfSection()).isEqualTo(DEFAULT_NO_OF_SECTION);
        assertThat(testJobCardItemRequirement.getNoOfClamps()).isEqualTo(DEFAULT_NO_OF_CLAMPS);
        assertThat(testJobCardItemRequirement.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testJobCardItemRequirement.getDomainObject()).isEqualTo(DEFAULT_DOMAIN_OBJECT);
    }

    @Test
    @Transactional
    public void getAllJobCardItemRequirements() throws Exception {
        // Initialize the database
        jobCardItemRequirementRepository.saveAndFlush(jobCardItemRequirement);

        // Get all the jobCardItemRequirements
        restJobCardItemRequirementMockMvc.perform(get("/api/jobCardItemRequirements?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(jobCardItemRequirement.getId().intValue())))
                .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
                .andExpect(jsonPath("$.[*].replaceLength").value(hasItem(DEFAULT_REPLACE_LENGTH.toString())))
                .andExpect(jsonPath("$.[*].cascadeClamp").value(hasItem(DEFAULT_CASCADE_CLAMP.toString())))
                .andExpect(jsonPath("$.[*].noOfSection").value(hasItem(DEFAULT_NO_OF_SECTION)))
                .andExpect(jsonPath("$.[*].noOfClamps").value(hasItem(DEFAULT_NO_OF_CLAMPS)))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].domainObject").value(hasItem(DEFAULT_DOMAIN_OBJECT.intValue())));
    }

    @Test
    @Transactional
    public void getJobCardItemRequirement() throws Exception {
        // Initialize the database
        jobCardItemRequirementRepository.saveAndFlush(jobCardItemRequirement);

        // Get the jobCardItemRequirement
        restJobCardItemRequirementMockMvc.perform(get("/api/jobCardItemRequirements/{id}", jobCardItemRequirement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(jobCardItemRequirement.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.replaceLength").value(DEFAULT_REPLACE_LENGTH.toString()))
            .andExpect(jsonPath("$.cascadeClamp").value(DEFAULT_CASCADE_CLAMP.toString()))
            .andExpect(jsonPath("$.noOfSection").value(DEFAULT_NO_OF_SECTION))
            .andExpect(jsonPath("$.noOfClamps").value(DEFAULT_NO_OF_CLAMPS))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.domainObject").value(DEFAULT_DOMAIN_OBJECT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingJobCardItemRequirement() throws Exception {
        // Get the jobCardItemRequirement
        restJobCardItemRequirementMockMvc.perform(get("/api/jobCardItemRequirements/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobCardItemRequirement() throws Exception {
        // Initialize the database
        jobCardItemRequirementRepository.saveAndFlush(jobCardItemRequirement);

		int databaseSizeBeforeUpdate = jobCardItemRequirementRepository.findAll().size();

        // Update the jobCardItemRequirement
        jobCardItemRequirement.setQuantity(UPDATED_QUANTITY);
        jobCardItemRequirement.setReplaceLength(UPDATED_REPLACE_LENGTH);
        jobCardItemRequirement.setCascadeClamp(UPDATED_CASCADE_CLAMP);
        jobCardItemRequirement.setNoOfSection(UPDATED_NO_OF_SECTION);
        jobCardItemRequirement.setNoOfClamps(UPDATED_NO_OF_CLAMPS);
        jobCardItemRequirement.setType(UPDATED_TYPE);
        jobCardItemRequirement.setDomainObject(UPDATED_DOMAIN_OBJECT);

        restJobCardItemRequirementMockMvc.perform(put("/api/jobCardItemRequirements")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jobCardItemRequirement)))
                .andExpect(status().isOk());

        // Validate the JobCardItemRequirement in the database
        List<JobCardItemRequirement> jobCardItemRequirements = jobCardItemRequirementRepository.findAll();
        assertThat(jobCardItemRequirements).hasSize(databaseSizeBeforeUpdate);
        JobCardItemRequirement testJobCardItemRequirement = jobCardItemRequirements.get(jobCardItemRequirements.size() - 1);
        assertThat(testJobCardItemRequirement.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testJobCardItemRequirement.getReplaceLength()).isEqualTo(UPDATED_REPLACE_LENGTH);
        assertThat(testJobCardItemRequirement.getCascadeClamp()).isEqualTo(UPDATED_CASCADE_CLAMP);
        assertThat(testJobCardItemRequirement.getNoOfSection()).isEqualTo(UPDATED_NO_OF_SECTION);
        assertThat(testJobCardItemRequirement.getNoOfClamps()).isEqualTo(UPDATED_NO_OF_CLAMPS);
        assertThat(testJobCardItemRequirement.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testJobCardItemRequirement.getDomainObject()).isEqualTo(UPDATED_DOMAIN_OBJECT);
    }

    @Test
    @Transactional
    public void deleteJobCardItemRequirement() throws Exception {
        // Initialize the database
        jobCardItemRequirementRepository.saveAndFlush(jobCardItemRequirement);

		int databaseSizeBeforeDelete = jobCardItemRequirementRepository.findAll().size();

        // Get the jobCardItemRequirement
        restJobCardItemRequirementMockMvc.perform(delete("/api/jobCardItemRequirements/{id}", jobCardItemRequirement.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<JobCardItemRequirement> jobCardItemRequirements = jobCardItemRequirementRepository.findAll();
        assertThat(jobCardItemRequirements).hasSize(databaseSizeBeforeDelete - 1);
    }
}

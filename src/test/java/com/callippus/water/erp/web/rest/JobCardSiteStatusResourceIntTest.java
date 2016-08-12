package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.JobCardSiteStatus;
import com.callippus.water.erp.repository.JobCardSiteStatusRepository;

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
 * Test class for the JobCardSiteStatusResource REST controller.
 *
 * @see JobCardSiteStatusResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class JobCardSiteStatusResourceIntTest {

    private static final String DEFAULT_TAR_PATCHING = "AAAAA";
    private static final String UPDATED_TAR_PATCHING = "BBBBB";
    private static final String DEFAULT_TAR_PATCHING_LENGTH = "AAAAA";
    private static final String UPDATED_TAR_PATCHING_LENGTH = "BBBBB";
    private static final String DEFAULT_TAR_PATCHING_BREADTH = "AAAAA";
    private static final String UPDATED_TAR_PATCHING_BREADTH = "BBBBB";
    private static final String DEFAULT_CLEAN_SITE = "AAAAA";
    private static final String UPDATED_CLEAN_SITE = "BBBBB";
    private static final String DEFAULT_BACK_FILL = "AAAAA";
    private static final String UPDATED_BACK_FILL = "BBBBB";
    private static final String DEFAULT_BACK_FILL_LENGTH = "AAAAA";
    private static final String UPDATED_BACK_FILL_LENGTH = "BBBBB";
    private static final String DEFAULT_BACK_FILL_BREADTH = "AAAAA";
    private static final String UPDATED_BACK_FILL_BREADTH = "BBBBB";
    private static final String DEFAULT_BRICK_LAYER = "AAAAA";
    private static final String UPDATED_BRICK_LAYER = "BBBBB";
    private static final String DEFAULT_PAVING = "AAAAA";
    private static final String UPDATED_PAVING = "BBBBB";
    private static final String DEFAULT_PAVING_LENGTH = "AAAAA";
    private static final String UPDATED_PAVING_LENGTH = "BBBBB";
    private static final String DEFAULT_PAVING_BREADTH = "AAAAA";
    private static final String UPDATED_PAVING_BREADTH = "BBBBB";
    private static final String DEFAULT_UNABLE_TO_LOCATE = "AAAAA";
    private static final String UPDATED_UNABLE_TO_LOCATE = "BBBBB";

    @Inject
    private JobCardSiteStatusRepository jobCardSiteStatusRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restJobCardSiteStatusMockMvc;

    private JobCardSiteStatus jobCardSiteStatus;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        JobCardSiteStatusResource jobCardSiteStatusResource = new JobCardSiteStatusResource();
        ReflectionTestUtils.setField(jobCardSiteStatusResource, "jobCardSiteStatusRepository", jobCardSiteStatusRepository);
        this.restJobCardSiteStatusMockMvc = MockMvcBuilders.standaloneSetup(jobCardSiteStatusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        jobCardSiteStatus = new JobCardSiteStatus();
        jobCardSiteStatus.setTarPatching(DEFAULT_TAR_PATCHING);
        jobCardSiteStatus.setTarPatchingLength(DEFAULT_TAR_PATCHING_LENGTH);
        jobCardSiteStatus.setTarPatchingBreadth(DEFAULT_TAR_PATCHING_BREADTH);
        jobCardSiteStatus.setCleanSite(DEFAULT_CLEAN_SITE);
        jobCardSiteStatus.setBackFill(DEFAULT_BACK_FILL);
        jobCardSiteStatus.setBackFillLength(DEFAULT_BACK_FILL_LENGTH);
        jobCardSiteStatus.setBackFillBreadth(DEFAULT_BACK_FILL_BREADTH);
        jobCardSiteStatus.setBrickLayer(DEFAULT_BRICK_LAYER);
        jobCardSiteStatus.setPaving(DEFAULT_PAVING);
        jobCardSiteStatus.setPavingLength(DEFAULT_PAVING_LENGTH);
        jobCardSiteStatus.setPavingBreadth(DEFAULT_PAVING_BREADTH);
        jobCardSiteStatus.setUnableToLocate(DEFAULT_UNABLE_TO_LOCATE);
    }

    @Test
    @Transactional
    public void createJobCardSiteStatus() throws Exception {
        int databaseSizeBeforeCreate = jobCardSiteStatusRepository.findAll().size();

        // Create the JobCardSiteStatus

        restJobCardSiteStatusMockMvc.perform(post("/api/jobCardSiteStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jobCardSiteStatus)))
                .andExpect(status().isCreated());

        // Validate the JobCardSiteStatus in the database
        List<JobCardSiteStatus> jobCardSiteStatuss = jobCardSiteStatusRepository.findAll();
        assertThat(jobCardSiteStatuss).hasSize(databaseSizeBeforeCreate + 1);
        JobCardSiteStatus testJobCardSiteStatus = jobCardSiteStatuss.get(jobCardSiteStatuss.size() - 1);
        assertThat(testJobCardSiteStatus.getTarPatching()).isEqualTo(DEFAULT_TAR_PATCHING);
        assertThat(testJobCardSiteStatus.getTarPatchingLength()).isEqualTo(DEFAULT_TAR_PATCHING_LENGTH);
        assertThat(testJobCardSiteStatus.getTarPatchingBreadth()).isEqualTo(DEFAULT_TAR_PATCHING_BREADTH);
        assertThat(testJobCardSiteStatus.getCleanSite()).isEqualTo(DEFAULT_CLEAN_SITE);
        assertThat(testJobCardSiteStatus.getBackFill()).isEqualTo(DEFAULT_BACK_FILL);
        assertThat(testJobCardSiteStatus.getBackFillLength()).isEqualTo(DEFAULT_BACK_FILL_LENGTH);
        assertThat(testJobCardSiteStatus.getBackFillBreadth()).isEqualTo(DEFAULT_BACK_FILL_BREADTH);
        assertThat(testJobCardSiteStatus.getBrickLayer()).isEqualTo(DEFAULT_BRICK_LAYER);
        assertThat(testJobCardSiteStatus.getPaving()).isEqualTo(DEFAULT_PAVING);
        assertThat(testJobCardSiteStatus.getPavingLength()).isEqualTo(DEFAULT_PAVING_LENGTH);
        assertThat(testJobCardSiteStatus.getPavingBreadth()).isEqualTo(DEFAULT_PAVING_BREADTH);
        assertThat(testJobCardSiteStatus.getUnableToLocate()).isEqualTo(DEFAULT_UNABLE_TO_LOCATE);
    }

    @Test
    @Transactional
    public void checkPavingBreadthIsRequired() throws Exception {
        int databaseSizeBeforeTest = jobCardSiteStatusRepository.findAll().size();
        // set the field null
        jobCardSiteStatus.setPavingBreadth(null);

        // Create the JobCardSiteStatus, which fails.

        restJobCardSiteStatusMockMvc.perform(post("/api/jobCardSiteStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jobCardSiteStatus)))
                .andExpect(status().isBadRequest());

        List<JobCardSiteStatus> jobCardSiteStatuss = jobCardSiteStatusRepository.findAll();
        assertThat(jobCardSiteStatuss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllJobCardSiteStatuss() throws Exception {
        // Initialize the database
        jobCardSiteStatusRepository.saveAndFlush(jobCardSiteStatus);

        // Get all the jobCardSiteStatuss
        restJobCardSiteStatusMockMvc.perform(get("/api/jobCardSiteStatuss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(jobCardSiteStatus.getId().intValue())))
                .andExpect(jsonPath("$.[*].tarPatching").value(hasItem(DEFAULT_TAR_PATCHING.toString())))
                .andExpect(jsonPath("$.[*].tarPatchingLength").value(hasItem(DEFAULT_TAR_PATCHING_LENGTH.toString())))
                .andExpect(jsonPath("$.[*].tarPatchingBreadth").value(hasItem(DEFAULT_TAR_PATCHING_BREADTH.toString())))
                .andExpect(jsonPath("$.[*].cleanSite").value(hasItem(DEFAULT_CLEAN_SITE.toString())))
                .andExpect(jsonPath("$.[*].backFill").value(hasItem(DEFAULT_BACK_FILL.toString())))
                .andExpect(jsonPath("$.[*].backFillLength").value(hasItem(DEFAULT_BACK_FILL_LENGTH.toString())))
                .andExpect(jsonPath("$.[*].backFillBreadth").value(hasItem(DEFAULT_BACK_FILL_BREADTH.toString())))
                .andExpect(jsonPath("$.[*].brickLayer").value(hasItem(DEFAULT_BRICK_LAYER.toString())))
                .andExpect(jsonPath("$.[*].paving").value(hasItem(DEFAULT_PAVING.toString())))
                .andExpect(jsonPath("$.[*].pavingLength").value(hasItem(DEFAULT_PAVING_LENGTH.toString())))
                .andExpect(jsonPath("$.[*].pavingBreadth").value(hasItem(DEFAULT_PAVING_BREADTH.toString())))
                .andExpect(jsonPath("$.[*].unableToLocate").value(hasItem(DEFAULT_UNABLE_TO_LOCATE.toString())));
    }

    @Test
    @Transactional
    public void getJobCardSiteStatus() throws Exception {
        // Initialize the database
        jobCardSiteStatusRepository.saveAndFlush(jobCardSiteStatus);

        // Get the jobCardSiteStatus
        restJobCardSiteStatusMockMvc.perform(get("/api/jobCardSiteStatuss/{id}", jobCardSiteStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(jobCardSiteStatus.getId().intValue()))
            .andExpect(jsonPath("$.tarPatching").value(DEFAULT_TAR_PATCHING.toString()))
            .andExpect(jsonPath("$.tarPatchingLength").value(DEFAULT_TAR_PATCHING_LENGTH.toString()))
            .andExpect(jsonPath("$.tarPatchingBreadth").value(DEFAULT_TAR_PATCHING_BREADTH.toString()))
            .andExpect(jsonPath("$.cleanSite").value(DEFAULT_CLEAN_SITE.toString()))
            .andExpect(jsonPath("$.backFill").value(DEFAULT_BACK_FILL.toString()))
            .andExpect(jsonPath("$.backFillLength").value(DEFAULT_BACK_FILL_LENGTH.toString()))
            .andExpect(jsonPath("$.backFillBreadth").value(DEFAULT_BACK_FILL_BREADTH.toString()))
            .andExpect(jsonPath("$.brickLayer").value(DEFAULT_BRICK_LAYER.toString()))
            .andExpect(jsonPath("$.paving").value(DEFAULT_PAVING.toString()))
            .andExpect(jsonPath("$.pavingLength").value(DEFAULT_PAVING_LENGTH.toString()))
            .andExpect(jsonPath("$.pavingBreadth").value(DEFAULT_PAVING_BREADTH.toString()))
            .andExpect(jsonPath("$.unableToLocate").value(DEFAULT_UNABLE_TO_LOCATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingJobCardSiteStatus() throws Exception {
        // Get the jobCardSiteStatus
        restJobCardSiteStatusMockMvc.perform(get("/api/jobCardSiteStatuss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateJobCardSiteStatus() throws Exception {
        // Initialize the database
        jobCardSiteStatusRepository.saveAndFlush(jobCardSiteStatus);

		int databaseSizeBeforeUpdate = jobCardSiteStatusRepository.findAll().size();

        // Update the jobCardSiteStatus
        jobCardSiteStatus.setTarPatching(UPDATED_TAR_PATCHING);
        jobCardSiteStatus.setTarPatchingLength(UPDATED_TAR_PATCHING_LENGTH);
        jobCardSiteStatus.setTarPatchingBreadth(UPDATED_TAR_PATCHING_BREADTH);
        jobCardSiteStatus.setCleanSite(UPDATED_CLEAN_SITE);
        jobCardSiteStatus.setBackFill(UPDATED_BACK_FILL);
        jobCardSiteStatus.setBackFillLength(UPDATED_BACK_FILL_LENGTH);
        jobCardSiteStatus.setBackFillBreadth(UPDATED_BACK_FILL_BREADTH);
        jobCardSiteStatus.setBrickLayer(UPDATED_BRICK_LAYER);
        jobCardSiteStatus.setPaving(UPDATED_PAVING);
        jobCardSiteStatus.setPavingLength(UPDATED_PAVING_LENGTH);
        jobCardSiteStatus.setPavingBreadth(UPDATED_PAVING_BREADTH);
        jobCardSiteStatus.setUnableToLocate(UPDATED_UNABLE_TO_LOCATE);

        restJobCardSiteStatusMockMvc.perform(put("/api/jobCardSiteStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(jobCardSiteStatus)))
                .andExpect(status().isOk());

        // Validate the JobCardSiteStatus in the database
        List<JobCardSiteStatus> jobCardSiteStatuss = jobCardSiteStatusRepository.findAll();
        assertThat(jobCardSiteStatuss).hasSize(databaseSizeBeforeUpdate);
        JobCardSiteStatus testJobCardSiteStatus = jobCardSiteStatuss.get(jobCardSiteStatuss.size() - 1);
        assertThat(testJobCardSiteStatus.getTarPatching()).isEqualTo(UPDATED_TAR_PATCHING);
        assertThat(testJobCardSiteStatus.getTarPatchingLength()).isEqualTo(UPDATED_TAR_PATCHING_LENGTH);
        assertThat(testJobCardSiteStatus.getTarPatchingBreadth()).isEqualTo(UPDATED_TAR_PATCHING_BREADTH);
        assertThat(testJobCardSiteStatus.getCleanSite()).isEqualTo(UPDATED_CLEAN_SITE);
        assertThat(testJobCardSiteStatus.getBackFill()).isEqualTo(UPDATED_BACK_FILL);
        assertThat(testJobCardSiteStatus.getBackFillLength()).isEqualTo(UPDATED_BACK_FILL_LENGTH);
        assertThat(testJobCardSiteStatus.getBackFillBreadth()).isEqualTo(UPDATED_BACK_FILL_BREADTH);
        assertThat(testJobCardSiteStatus.getBrickLayer()).isEqualTo(UPDATED_BRICK_LAYER);
        assertThat(testJobCardSiteStatus.getPaving()).isEqualTo(UPDATED_PAVING);
        assertThat(testJobCardSiteStatus.getPavingLength()).isEqualTo(UPDATED_PAVING_LENGTH);
        assertThat(testJobCardSiteStatus.getPavingBreadth()).isEqualTo(UPDATED_PAVING_BREADTH);
        assertThat(testJobCardSiteStatus.getUnableToLocate()).isEqualTo(UPDATED_UNABLE_TO_LOCATE);
    }

    @Test
    @Transactional
    public void deleteJobCardSiteStatus() throws Exception {
        // Initialize the database
        jobCardSiteStatusRepository.saveAndFlush(jobCardSiteStatus);

		int databaseSizeBeforeDelete = jobCardSiteStatusRepository.findAll().size();

        // Get the jobCardSiteStatus
        restJobCardSiteStatusMockMvc.perform(delete("/api/jobCardSiteStatuss/{id}", jobCardSiteStatus.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<JobCardSiteStatus> jobCardSiteStatuss = jobCardSiteStatusRepository.findAll();
        assertThat(jobCardSiteStatuss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

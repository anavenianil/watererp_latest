package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.FeasibilityStudy;
import com.callippus.water.erp.repository.FeasibilityStudyRepository;

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
 * Test class for the FeasibilityStudyResource REST controller.
 *
 * @see FeasibilityStudyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FeasibilityStudyResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATED_DATE);

    private static final ZonedDateTime DEFAULT_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_MODIFIED_DATE);
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private FeasibilityStudyRepository feasibilityStudyRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFeasibilityStudyMockMvc;

    private FeasibilityStudy feasibilityStudy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FeasibilityStudyResource feasibilityStudyResource = new FeasibilityStudyResource();
        ReflectionTestUtils.setField(feasibilityStudyResource, "feasibilityStudyRepository", feasibilityStudyRepository);
        this.restFeasibilityStudyMockMvc = MockMvcBuilders.standaloneSetup(feasibilityStudyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        feasibilityStudy = new FeasibilityStudy();
        feasibilityStudy.setCreatedDate(DEFAULT_CREATED_DATE);
        feasibilityStudy.setModifiedDate(DEFAULT_MODIFIED_DATE);
        feasibilityStudy.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createFeasibilityStudy() throws Exception {
        int databaseSizeBeforeCreate = feasibilityStudyRepository.findAll().size();

        // Create the FeasibilityStudy

        restFeasibilityStudyMockMvc.perform(post("/api/feasibilityStudys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(feasibilityStudy)))
                .andExpect(status().isCreated());

        // Validate the FeasibilityStudy in the database
        List<FeasibilityStudy> feasibilityStudys = feasibilityStudyRepository.findAll();
        assertThat(feasibilityStudys).hasSize(databaseSizeBeforeCreate + 1);
        FeasibilityStudy testFeasibilityStudy = feasibilityStudys.get(feasibilityStudys.size() - 1);
        assertThat(testFeasibilityStudy.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testFeasibilityStudy.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testFeasibilityStudy.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllFeasibilityStudys() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

        // Get all the feasibilityStudys
        restFeasibilityStudyMockMvc.perform(get("/api/feasibilityStudys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(feasibilityStudy.getId().intValue())))
                .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getFeasibilityStudy() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

        // Get the feasibilityStudy
        restFeasibilityStudyMockMvc.perform(get("/api/feasibilityStudys/{id}", feasibilityStudy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(feasibilityStudy.getId().intValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingFeasibilityStudy() throws Exception {
        // Get the feasibilityStudy
        restFeasibilityStudyMockMvc.perform(get("/api/feasibilityStudys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeasibilityStudy() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

		int databaseSizeBeforeUpdate = feasibilityStudyRepository.findAll().size();

        // Update the feasibilityStudy
        feasibilityStudy.setCreatedDate(UPDATED_CREATED_DATE);
        feasibilityStudy.setModifiedDate(UPDATED_MODIFIED_DATE);
        feasibilityStudy.setStatus(UPDATED_STATUS);

        restFeasibilityStudyMockMvc.perform(put("/api/feasibilityStudys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(feasibilityStudy)))
                .andExpect(status().isOk());

        // Validate the FeasibilityStudy in the database
        List<FeasibilityStudy> feasibilityStudys = feasibilityStudyRepository.findAll();
        assertThat(feasibilityStudys).hasSize(databaseSizeBeforeUpdate);
        FeasibilityStudy testFeasibilityStudy = feasibilityStudys.get(feasibilityStudys.size() - 1);
        assertThat(testFeasibilityStudy.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testFeasibilityStudy.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testFeasibilityStudy.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteFeasibilityStudy() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

		int databaseSizeBeforeDelete = feasibilityStudyRepository.findAll().size();

        // Get the feasibilityStudy
        restFeasibilityStudyMockMvc.perform(delete("/api/feasibilityStudys/{id}", feasibilityStudy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<FeasibilityStudy> feasibilityStudys = feasibilityStudyRepository.findAll();
        assertThat(feasibilityStudys).hasSize(databaseSizeBeforeDelete - 1);
    }
}

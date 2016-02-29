package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ApplicationTypeMaster;
import com.callippus.water.erp.repository.ApplicationTypeMasterRepository;

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
 * Test class for the ApplicationTypeMasterResource REST controller.
 *
 * @see ApplicationTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationTypeMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_APPLICATION_TYPE = "AAAAA";
    private static final String UPDATED_APPLICATION_TYPE = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATED_DATE);

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_UPDATED_DATE_STR = dateTimeFormatter.format(DEFAULT_UPDATED_DATE);
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";
    private static final String DEFAULT_CREATED_BY = "AAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBB";
    private static final String DEFAULT_UPDATED_BY = "AAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBB";

    @Inject
    private ApplicationTypeMasterRepository applicationTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restApplicationTypeMasterMockMvc;

    private ApplicationTypeMaster applicationTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApplicationTypeMasterResource applicationTypeMasterResource = new ApplicationTypeMasterResource();
        ReflectionTestUtils.setField(applicationTypeMasterResource, "applicationTypeMasterRepository", applicationTypeMasterRepository);
        this.restApplicationTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(applicationTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        applicationTypeMaster = new ApplicationTypeMaster();
        applicationTypeMaster.setApplicationType(DEFAULT_APPLICATION_TYPE);
        applicationTypeMaster.setCreatedDate(DEFAULT_CREATED_DATE);
        applicationTypeMaster.setUpdatedDate(DEFAULT_UPDATED_DATE);
        applicationTypeMaster.setStatus(DEFAULT_STATUS);
        applicationTypeMaster.setCreatedBy(DEFAULT_CREATED_BY);
        applicationTypeMaster.setUpdatedBy(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    public void createApplicationTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = applicationTypeMasterRepository.findAll().size();

        // Create the ApplicationTypeMaster

        restApplicationTypeMasterMockMvc.perform(post("/api/applicationTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(applicationTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the ApplicationTypeMaster in the database
        List<ApplicationTypeMaster> applicationTypeMasters = applicationTypeMasterRepository.findAll();
        assertThat(applicationTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        ApplicationTypeMaster testApplicationTypeMaster = applicationTypeMasters.get(applicationTypeMasters.size() - 1);
        assertThat(testApplicationTypeMaster.getApplicationType()).isEqualTo(DEFAULT_APPLICATION_TYPE);
        assertThat(testApplicationTypeMaster.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testApplicationTypeMaster.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testApplicationTypeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testApplicationTypeMaster.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testApplicationTypeMaster.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    public void getAllApplicationTypeMasters() throws Exception {
        // Initialize the database
        applicationTypeMasterRepository.saveAndFlush(applicationTypeMaster);

        // Get all the applicationTypeMasters
        restApplicationTypeMasterMockMvc.perform(get("/api/applicationTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(applicationTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].applicationType").value(hasItem(DEFAULT_APPLICATION_TYPE.toString())))
                .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
                .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY.toString())));
    }

    @Test
    @Transactional
    public void getApplicationTypeMaster() throws Exception {
        // Initialize the database
        applicationTypeMasterRepository.saveAndFlush(applicationTypeMaster);

        // Get the applicationTypeMaster
        restApplicationTypeMasterMockMvc.perform(get("/api/applicationTypeMasters/{id}", applicationTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(applicationTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.applicationType").value(DEFAULT_APPLICATION_TYPE.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApplicationTypeMaster() throws Exception {
        // Get the applicationTypeMaster
        restApplicationTypeMasterMockMvc.perform(get("/api/applicationTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApplicationTypeMaster() throws Exception {
        // Initialize the database
        applicationTypeMasterRepository.saveAndFlush(applicationTypeMaster);

		int databaseSizeBeforeUpdate = applicationTypeMasterRepository.findAll().size();

        // Update the applicationTypeMaster
        applicationTypeMaster.setApplicationType(UPDATED_APPLICATION_TYPE);
        applicationTypeMaster.setCreatedDate(UPDATED_CREATED_DATE);
        applicationTypeMaster.setUpdatedDate(UPDATED_UPDATED_DATE);
        applicationTypeMaster.setStatus(UPDATED_STATUS);
        applicationTypeMaster.setCreatedBy(UPDATED_CREATED_BY);
        applicationTypeMaster.setUpdatedBy(UPDATED_UPDATED_BY);

        restApplicationTypeMasterMockMvc.perform(put("/api/applicationTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(applicationTypeMaster)))
                .andExpect(status().isOk());

        // Validate the ApplicationTypeMaster in the database
        List<ApplicationTypeMaster> applicationTypeMasters = applicationTypeMasterRepository.findAll();
        assertThat(applicationTypeMasters).hasSize(databaseSizeBeforeUpdate);
        ApplicationTypeMaster testApplicationTypeMaster = applicationTypeMasters.get(applicationTypeMasters.size() - 1);
        assertThat(testApplicationTypeMaster.getApplicationType()).isEqualTo(UPDATED_APPLICATION_TYPE);
        assertThat(testApplicationTypeMaster.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testApplicationTypeMaster.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testApplicationTypeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testApplicationTypeMaster.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testApplicationTypeMaster.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    public void deleteApplicationTypeMaster() throws Exception {
        // Initialize the database
        applicationTypeMasterRepository.saveAndFlush(applicationTypeMaster);

		int databaseSizeBeforeDelete = applicationTypeMasterRepository.findAll().size();

        // Get the applicationTypeMaster
        restApplicationTypeMasterMockMvc.perform(delete("/api/applicationTypeMasters/{id}", applicationTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ApplicationTypeMaster> applicationTypeMasters = applicationTypeMasterRepository.findAll();
        assertThat(applicationTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ReqOrgWorkflowMapping;
import com.callippus.water.erp.repository.ReqOrgWorkflowMappingRepository;

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
 * Test class for the ReqOrgWorkflowMappingResource REST controller.
 *
 * @see ReqOrgWorkflowMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReqOrgWorkflowMappingResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private ReqOrgWorkflowMappingRepository reqOrgWorkflowMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReqOrgWorkflowMappingMockMvc;

    private ReqOrgWorkflowMapping reqOrgWorkflowMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReqOrgWorkflowMappingResource reqOrgWorkflowMappingResource = new ReqOrgWorkflowMappingResource();
        ReflectionTestUtils.setField(reqOrgWorkflowMappingResource, "reqOrgWorkflowMappingRepository", reqOrgWorkflowMappingRepository);
        this.restReqOrgWorkflowMappingMockMvc = MockMvcBuilders.standaloneSetup(reqOrgWorkflowMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        reqOrgWorkflowMapping = new ReqOrgWorkflowMapping();
        reqOrgWorkflowMapping.setCreationDate(DEFAULT_CREATION_DATE);
        reqOrgWorkflowMapping.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createReqOrgWorkflowMapping() throws Exception {
        int databaseSizeBeforeCreate = reqOrgWorkflowMappingRepository.findAll().size();

        // Create the ReqOrgWorkflowMapping

        restReqOrgWorkflowMappingMockMvc.perform(post("/api/reqOrgWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reqOrgWorkflowMapping)))
                .andExpect(status().isCreated());

        // Validate the ReqOrgWorkflowMapping in the database
        List<ReqOrgWorkflowMapping> reqOrgWorkflowMappings = reqOrgWorkflowMappingRepository.findAll();
        assertThat(reqOrgWorkflowMappings).hasSize(databaseSizeBeforeCreate + 1);
        ReqOrgWorkflowMapping testReqOrgWorkflowMapping = reqOrgWorkflowMappings.get(reqOrgWorkflowMappings.size() - 1);
        assertThat(testReqOrgWorkflowMapping.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testReqOrgWorkflowMapping.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllReqOrgWorkflowMappings() throws Exception {
        // Initialize the database
        reqOrgWorkflowMappingRepository.saveAndFlush(reqOrgWorkflowMapping);

        // Get all the reqOrgWorkflowMappings
        restReqOrgWorkflowMappingMockMvc.perform(get("/api/reqOrgWorkflowMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(reqOrgWorkflowMapping.getId().intValue())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getReqOrgWorkflowMapping() throws Exception {
        // Initialize the database
        reqOrgWorkflowMappingRepository.saveAndFlush(reqOrgWorkflowMapping);

        // Get the reqOrgWorkflowMapping
        restReqOrgWorkflowMappingMockMvc.perform(get("/api/reqOrgWorkflowMappings/{id}", reqOrgWorkflowMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(reqOrgWorkflowMapping.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingReqOrgWorkflowMapping() throws Exception {
        // Get the reqOrgWorkflowMapping
        restReqOrgWorkflowMappingMockMvc.perform(get("/api/reqOrgWorkflowMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReqOrgWorkflowMapping() throws Exception {
        // Initialize the database
        reqOrgWorkflowMappingRepository.saveAndFlush(reqOrgWorkflowMapping);

		int databaseSizeBeforeUpdate = reqOrgWorkflowMappingRepository.findAll().size();

        // Update the reqOrgWorkflowMapping
        reqOrgWorkflowMapping.setCreationDate(UPDATED_CREATION_DATE);
        reqOrgWorkflowMapping.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restReqOrgWorkflowMappingMockMvc.perform(put("/api/reqOrgWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reqOrgWorkflowMapping)))
                .andExpect(status().isOk());

        // Validate the ReqOrgWorkflowMapping in the database
        List<ReqOrgWorkflowMapping> reqOrgWorkflowMappings = reqOrgWorkflowMappingRepository.findAll();
        assertThat(reqOrgWorkflowMappings).hasSize(databaseSizeBeforeUpdate);
        ReqOrgWorkflowMapping testReqOrgWorkflowMapping = reqOrgWorkflowMappings.get(reqOrgWorkflowMappings.size() - 1);
        assertThat(testReqOrgWorkflowMapping.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testReqOrgWorkflowMapping.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteReqOrgWorkflowMapping() throws Exception {
        // Initialize the database
        reqOrgWorkflowMappingRepository.saveAndFlush(reqOrgWorkflowMapping);

		int databaseSizeBeforeDelete = reqOrgWorkflowMappingRepository.findAll().size();

        // Get the reqOrgWorkflowMapping
        restReqOrgWorkflowMappingMockMvc.perform(delete("/api/reqOrgWorkflowMappings/{id}", reqOrgWorkflowMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ReqOrgWorkflowMapping> reqOrgWorkflowMappings = reqOrgWorkflowMappingRepository.findAll();
        assertThat(reqOrgWorkflowMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}

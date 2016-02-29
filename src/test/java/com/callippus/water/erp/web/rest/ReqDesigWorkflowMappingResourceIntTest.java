package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ReqDesigWorkflowMapping;
import com.callippus.water.erp.repository.ReqDesigWorkflowMappingRepository;

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
 * Test class for the ReqDesigWorkflowMappingResource REST controller.
 *
 * @see ReqDesigWorkflowMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReqDesigWorkflowMappingResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private ReqDesigWorkflowMappingRepository reqDesigWorkflowMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReqDesigWorkflowMappingMockMvc;

    private ReqDesigWorkflowMapping reqDesigWorkflowMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReqDesigWorkflowMappingResource reqDesigWorkflowMappingResource = new ReqDesigWorkflowMappingResource();
        ReflectionTestUtils.setField(reqDesigWorkflowMappingResource, "reqDesigWorkflowMappingRepository", reqDesigWorkflowMappingRepository);
        this.restReqDesigWorkflowMappingMockMvc = MockMvcBuilders.standaloneSetup(reqDesigWorkflowMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        reqDesigWorkflowMapping = new ReqDesigWorkflowMapping();
        reqDesigWorkflowMapping.setCreationDate(DEFAULT_CREATION_DATE);
        reqDesigWorkflowMapping.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createReqDesigWorkflowMapping() throws Exception {
        int databaseSizeBeforeCreate = reqDesigWorkflowMappingRepository.findAll().size();

        // Create the ReqDesigWorkflowMapping

        restReqDesigWorkflowMappingMockMvc.perform(post("/api/reqDesigWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reqDesigWorkflowMapping)))
                .andExpect(status().isCreated());

        // Validate the ReqDesigWorkflowMapping in the database
        List<ReqDesigWorkflowMapping> reqDesigWorkflowMappings = reqDesigWorkflowMappingRepository.findAll();
        assertThat(reqDesigWorkflowMappings).hasSize(databaseSizeBeforeCreate + 1);
        ReqDesigWorkflowMapping testReqDesigWorkflowMapping = reqDesigWorkflowMappings.get(reqDesigWorkflowMappings.size() - 1);
        assertThat(testReqDesigWorkflowMapping.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testReqDesigWorkflowMapping.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllReqDesigWorkflowMappings() throws Exception {
        // Initialize the database
        reqDesigWorkflowMappingRepository.saveAndFlush(reqDesigWorkflowMapping);

        // Get all the reqDesigWorkflowMappings
        restReqDesigWorkflowMappingMockMvc.perform(get("/api/reqDesigWorkflowMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(reqDesigWorkflowMapping.getId().intValue())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getReqDesigWorkflowMapping() throws Exception {
        // Initialize the database
        reqDesigWorkflowMappingRepository.saveAndFlush(reqDesigWorkflowMapping);

        // Get the reqDesigWorkflowMapping
        restReqDesigWorkflowMappingMockMvc.perform(get("/api/reqDesigWorkflowMappings/{id}", reqDesigWorkflowMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(reqDesigWorkflowMapping.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingReqDesigWorkflowMapping() throws Exception {
        // Get the reqDesigWorkflowMapping
        restReqDesigWorkflowMappingMockMvc.perform(get("/api/reqDesigWorkflowMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReqDesigWorkflowMapping() throws Exception {
        // Initialize the database
        reqDesigWorkflowMappingRepository.saveAndFlush(reqDesigWorkflowMapping);

		int databaseSizeBeforeUpdate = reqDesigWorkflowMappingRepository.findAll().size();

        // Update the reqDesigWorkflowMapping
        reqDesigWorkflowMapping.setCreationDate(UPDATED_CREATION_DATE);
        reqDesigWorkflowMapping.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restReqDesigWorkflowMappingMockMvc.perform(put("/api/reqDesigWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reqDesigWorkflowMapping)))
                .andExpect(status().isOk());

        // Validate the ReqDesigWorkflowMapping in the database
        List<ReqDesigWorkflowMapping> reqDesigWorkflowMappings = reqDesigWorkflowMappingRepository.findAll();
        assertThat(reqDesigWorkflowMappings).hasSize(databaseSizeBeforeUpdate);
        ReqDesigWorkflowMapping testReqDesigWorkflowMapping = reqDesigWorkflowMappings.get(reqDesigWorkflowMappings.size() - 1);
        assertThat(testReqDesigWorkflowMapping.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testReqDesigWorkflowMapping.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteReqDesigWorkflowMapping() throws Exception {
        // Initialize the database
        reqDesigWorkflowMappingRepository.saveAndFlush(reqDesigWorkflowMapping);

		int databaseSizeBeforeDelete = reqDesigWorkflowMappingRepository.findAll().size();

        // Get the reqDesigWorkflowMapping
        restReqDesigWorkflowMappingMockMvc.perform(delete("/api/reqDesigWorkflowMappings/{id}", reqDesigWorkflowMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ReqDesigWorkflowMapping> reqDesigWorkflowMappings = reqDesigWorkflowMappingRepository.findAll();
        assertThat(reqDesigWorkflowMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}

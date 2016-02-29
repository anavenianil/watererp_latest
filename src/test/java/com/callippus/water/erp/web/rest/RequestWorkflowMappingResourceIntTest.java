package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.RequestWorkflowMapping;
import com.callippus.water.erp.repository.RequestWorkflowMappingRepository;

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
 * Test class for the RequestWorkflowMappingResource REST controller.
 *
 * @see RequestWorkflowMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RequestWorkflowMappingResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private RequestWorkflowMappingRepository requestWorkflowMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRequestWorkflowMappingMockMvc;

    private RequestWorkflowMapping requestWorkflowMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RequestWorkflowMappingResource requestWorkflowMappingResource = new RequestWorkflowMappingResource();
        ReflectionTestUtils.setField(requestWorkflowMappingResource, "requestWorkflowMappingRepository", requestWorkflowMappingRepository);
        this.restRequestWorkflowMappingMockMvc = MockMvcBuilders.standaloneSetup(requestWorkflowMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        requestWorkflowMapping = new RequestWorkflowMapping();
        requestWorkflowMapping.setCreationDate(DEFAULT_CREATION_DATE);
        requestWorkflowMapping.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createRequestWorkflowMapping() throws Exception {
        int databaseSizeBeforeCreate = requestWorkflowMappingRepository.findAll().size();

        // Create the RequestWorkflowMapping

        restRequestWorkflowMappingMockMvc.perform(post("/api/requestWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(requestWorkflowMapping)))
                .andExpect(status().isCreated());

        // Validate the RequestWorkflowMapping in the database
        List<RequestWorkflowMapping> requestWorkflowMappings = requestWorkflowMappingRepository.findAll();
        assertThat(requestWorkflowMappings).hasSize(databaseSizeBeforeCreate + 1);
        RequestWorkflowMapping testRequestWorkflowMapping = requestWorkflowMappings.get(requestWorkflowMappings.size() - 1);
        assertThat(testRequestWorkflowMapping.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testRequestWorkflowMapping.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllRequestWorkflowMappings() throws Exception {
        // Initialize the database
        requestWorkflowMappingRepository.saveAndFlush(requestWorkflowMapping);

        // Get all the requestWorkflowMappings
        restRequestWorkflowMappingMockMvc.perform(get("/api/requestWorkflowMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(requestWorkflowMapping.getId().intValue())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getRequestWorkflowMapping() throws Exception {
        // Initialize the database
        requestWorkflowMappingRepository.saveAndFlush(requestWorkflowMapping);

        // Get the requestWorkflowMapping
        restRequestWorkflowMappingMockMvc.perform(get("/api/requestWorkflowMappings/{id}", requestWorkflowMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(requestWorkflowMapping.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingRequestWorkflowMapping() throws Exception {
        // Get the requestWorkflowMapping
        restRequestWorkflowMappingMockMvc.perform(get("/api/requestWorkflowMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestWorkflowMapping() throws Exception {
        // Initialize the database
        requestWorkflowMappingRepository.saveAndFlush(requestWorkflowMapping);

		int databaseSizeBeforeUpdate = requestWorkflowMappingRepository.findAll().size();

        // Update the requestWorkflowMapping
        requestWorkflowMapping.setCreationDate(UPDATED_CREATION_DATE);
        requestWorkflowMapping.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restRequestWorkflowMappingMockMvc.perform(put("/api/requestWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(requestWorkflowMapping)))
                .andExpect(status().isOk());

        // Validate the RequestWorkflowMapping in the database
        List<RequestWorkflowMapping> requestWorkflowMappings = requestWorkflowMappingRepository.findAll();
        assertThat(requestWorkflowMappings).hasSize(databaseSizeBeforeUpdate);
        RequestWorkflowMapping testRequestWorkflowMapping = requestWorkflowMappings.get(requestWorkflowMappings.size() - 1);
        assertThat(testRequestWorkflowMapping.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testRequestWorkflowMapping.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteRequestWorkflowMapping() throws Exception {
        // Initialize the database
        requestWorkflowMappingRepository.saveAndFlush(requestWorkflowMapping);

		int databaseSizeBeforeDelete = requestWorkflowMappingRepository.findAll().size();

        // Get the requestWorkflowMapping
        restRequestWorkflowMappingMockMvc.perform(delete("/api/requestWorkflowMappings/{id}", requestWorkflowMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RequestWorkflowMapping> requestWorkflowMappings = requestWorkflowMappingRepository.findAll();
        assertThat(requestWorkflowMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}

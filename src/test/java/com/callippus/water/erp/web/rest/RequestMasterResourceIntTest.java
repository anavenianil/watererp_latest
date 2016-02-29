package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.RequestMaster;
import com.callippus.water.erp.repository.RequestMasterRepository;

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
 * Test class for the RequestMasterResource REST controller.
 *
 * @see RequestMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RequestMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_REQUEST_TYPE = "AAAAA";
    private static final String UPDATED_REQUEST_TYPE = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    private static final Integer DEFAULT_INTERNAL_FLAG = 1;
    private static final Integer UPDATED_INTERNAL_FLAG = 2;

    @Inject
    private RequestMasterRepository requestMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRequestMasterMockMvc;

    private RequestMaster requestMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RequestMasterResource requestMasterResource = new RequestMasterResource();
        ReflectionTestUtils.setField(requestMasterResource, "requestMasterRepository", requestMasterRepository);
        this.restRequestMasterMockMvc = MockMvcBuilders.standaloneSetup(requestMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        requestMaster = new RequestMaster();
        requestMaster.setRequestType(DEFAULT_REQUEST_TYPE);
        requestMaster.setCreationDate(DEFAULT_CREATION_DATE);
        requestMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        requestMaster.setDescription(DEFAULT_DESCRIPTION);
        requestMaster.setInternalFlag(DEFAULT_INTERNAL_FLAG);
    }

    @Test
    @Transactional
    public void createRequestMaster() throws Exception {
        int databaseSizeBeforeCreate = requestMasterRepository.findAll().size();

        // Create the RequestMaster

        restRequestMasterMockMvc.perform(post("/api/requestMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(requestMaster)))
                .andExpect(status().isCreated());

        // Validate the RequestMaster in the database
        List<RequestMaster> requestMasters = requestMasterRepository.findAll();
        assertThat(requestMasters).hasSize(databaseSizeBeforeCreate + 1);
        RequestMaster testRequestMaster = requestMasters.get(requestMasters.size() - 1);
        assertThat(testRequestMaster.getRequestType()).isEqualTo(DEFAULT_REQUEST_TYPE);
        assertThat(testRequestMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testRequestMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testRequestMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testRequestMaster.getInternalFlag()).isEqualTo(DEFAULT_INTERNAL_FLAG);
    }

    @Test
    @Transactional
    public void getAllRequestMasters() throws Exception {
        // Initialize the database
        requestMasterRepository.saveAndFlush(requestMaster);

        // Get all the requestMasters
        restRequestMasterMockMvc.perform(get("/api/requestMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(requestMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].requestType").value(hasItem(DEFAULT_REQUEST_TYPE.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].internalFlag").value(hasItem(DEFAULT_INTERNAL_FLAG)));
    }

    @Test
    @Transactional
    public void getRequestMaster() throws Exception {
        // Initialize the database
        requestMasterRepository.saveAndFlush(requestMaster);

        // Get the requestMaster
        restRequestMasterMockMvc.perform(get("/api/requestMasters/{id}", requestMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(requestMaster.getId().intValue()))
            .andExpect(jsonPath("$.requestType").value(DEFAULT_REQUEST_TYPE.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.internalFlag").value(DEFAULT_INTERNAL_FLAG));
    }

    @Test
    @Transactional
    public void getNonExistingRequestMaster() throws Exception {
        // Get the requestMaster
        restRequestMasterMockMvc.perform(get("/api/requestMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRequestMaster() throws Exception {
        // Initialize the database
        requestMasterRepository.saveAndFlush(requestMaster);

		int databaseSizeBeforeUpdate = requestMasterRepository.findAll().size();

        // Update the requestMaster
        requestMaster.setRequestType(UPDATED_REQUEST_TYPE);
        requestMaster.setCreationDate(UPDATED_CREATION_DATE);
        requestMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        requestMaster.setDescription(UPDATED_DESCRIPTION);
        requestMaster.setInternalFlag(UPDATED_INTERNAL_FLAG);

        restRequestMasterMockMvc.perform(put("/api/requestMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(requestMaster)))
                .andExpect(status().isOk());

        // Validate the RequestMaster in the database
        List<RequestMaster> requestMasters = requestMasterRepository.findAll();
        assertThat(requestMasters).hasSize(databaseSizeBeforeUpdate);
        RequestMaster testRequestMaster = requestMasters.get(requestMasters.size() - 1);
        assertThat(testRequestMaster.getRequestType()).isEqualTo(UPDATED_REQUEST_TYPE);
        assertThat(testRequestMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testRequestMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testRequestMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testRequestMaster.getInternalFlag()).isEqualTo(UPDATED_INTERNAL_FLAG);
    }

    @Test
    @Transactional
    public void deleteRequestMaster() throws Exception {
        // Initialize the database
        requestMasterRepository.saveAndFlush(requestMaster);

		int databaseSizeBeforeDelete = requestMasterRepository.findAll().size();

        // Get the requestMaster
        restRequestMasterMockMvc.perform(delete("/api/requestMasters/{id}", requestMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RequestMaster> requestMasters = requestMasterRepository.findAll();
        assertThat(requestMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

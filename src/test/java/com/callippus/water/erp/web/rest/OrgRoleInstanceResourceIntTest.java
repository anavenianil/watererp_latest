package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OrgRoleInstance;
import com.callippus.water.erp.repository.OrgRoleInstanceRepository;

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
 * Test class for the OrgRoleInstanceResource REST controller.
 *
 * @see OrgRoleInstanceResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OrgRoleInstanceResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_ORG_ROLE_NAME = "AAAAA";
    private static final String UPDATED_ORG_ROLE_NAME = "BBBBB";

    private static final Integer DEFAULT_PARENT_ORG_ROLE_ID = 1;
    private static final Integer UPDATED_PARENT_ORG_ROLE_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    private static final ZonedDateTime DEFAULT_IS_HEAD = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_IS_HEAD = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_IS_HEAD_STR = dateTimeFormatter.format(DEFAULT_IS_HEAD);

    @Inject
    private OrgRoleInstanceRepository orgRoleInstanceRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrgRoleInstanceMockMvc;

    private OrgRoleInstance orgRoleInstance;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrgRoleInstanceResource orgRoleInstanceResource = new OrgRoleInstanceResource();
        ReflectionTestUtils.setField(orgRoleInstanceResource, "orgRoleInstanceRepository", orgRoleInstanceRepository);
        this.restOrgRoleInstanceMockMvc = MockMvcBuilders.standaloneSetup(orgRoleInstanceResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        orgRoleInstance = new OrgRoleInstance();
        orgRoleInstance.setOrgRoleName(DEFAULT_ORG_ROLE_NAME);
        orgRoleInstance.setParentOrgRoleId(DEFAULT_PARENT_ORG_ROLE_ID);
        orgRoleInstance.setCreationDate(DEFAULT_CREATION_DATE);
        orgRoleInstance.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        orgRoleInstance.setIsHead(DEFAULT_IS_HEAD);
    }

    @Test
    @Transactional
    public void createOrgRoleInstance() throws Exception {
        int databaseSizeBeforeCreate = orgRoleInstanceRepository.findAll().size();

        // Create the OrgRoleInstance

        restOrgRoleInstanceMockMvc.perform(post("/api/orgRoleInstances")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgRoleInstance)))
                .andExpect(status().isCreated());

        // Validate the OrgRoleInstance in the database
        List<OrgRoleInstance> orgRoleInstances = orgRoleInstanceRepository.findAll();
        assertThat(orgRoleInstances).hasSize(databaseSizeBeforeCreate + 1);
        OrgRoleInstance testOrgRoleInstance = orgRoleInstances.get(orgRoleInstances.size() - 1);
        assertThat(testOrgRoleInstance.getOrgRoleName()).isEqualTo(DEFAULT_ORG_ROLE_NAME);
        assertThat(testOrgRoleInstance.getParentOrgRoleId()).isEqualTo(DEFAULT_PARENT_ORG_ROLE_ID);
        assertThat(testOrgRoleInstance.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testOrgRoleInstance.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testOrgRoleInstance.getIsHead()).isEqualTo(DEFAULT_IS_HEAD);
    }

    @Test
    @Transactional
    public void getAllOrgRoleInstances() throws Exception {
        // Initialize the database
        orgRoleInstanceRepository.saveAndFlush(orgRoleInstance);

        // Get all the orgRoleInstances
        restOrgRoleInstanceMockMvc.perform(get("/api/orgRoleInstances?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(orgRoleInstance.getId().intValue())))
                .andExpect(jsonPath("$.[*].orgRoleName").value(hasItem(DEFAULT_ORG_ROLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].parentOrgRoleId").value(hasItem(DEFAULT_PARENT_ORG_ROLE_ID)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].isHead").value(hasItem(DEFAULT_IS_HEAD_STR)));
    }

    @Test
    @Transactional
    public void getOrgRoleInstance() throws Exception {
        // Initialize the database
        orgRoleInstanceRepository.saveAndFlush(orgRoleInstance);

        // Get the orgRoleInstance
        restOrgRoleInstanceMockMvc.perform(get("/api/orgRoleInstances/{id}", orgRoleInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(orgRoleInstance.getId().intValue()))
            .andExpect(jsonPath("$.orgRoleName").value(DEFAULT_ORG_ROLE_NAME.toString()))
            .andExpect(jsonPath("$.parentOrgRoleId").value(DEFAULT_PARENT_ORG_ROLE_ID))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.isHead").value(DEFAULT_IS_HEAD_STR));
    }

    @Test
    @Transactional
    public void getNonExistingOrgRoleInstance() throws Exception {
        // Get the orgRoleInstance
        restOrgRoleInstanceMockMvc.perform(get("/api/orgRoleInstances/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgRoleInstance() throws Exception {
        // Initialize the database
        orgRoleInstanceRepository.saveAndFlush(orgRoleInstance);

		int databaseSizeBeforeUpdate = orgRoleInstanceRepository.findAll().size();

        // Update the orgRoleInstance
        orgRoleInstance.setOrgRoleName(UPDATED_ORG_ROLE_NAME);
        orgRoleInstance.setParentOrgRoleId(UPDATED_PARENT_ORG_ROLE_ID);
        orgRoleInstance.setCreationDate(UPDATED_CREATION_DATE);
        orgRoleInstance.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        orgRoleInstance.setIsHead(UPDATED_IS_HEAD);

        restOrgRoleInstanceMockMvc.perform(put("/api/orgRoleInstances")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgRoleInstance)))
                .andExpect(status().isOk());

        // Validate the OrgRoleInstance in the database
        List<OrgRoleInstance> orgRoleInstances = orgRoleInstanceRepository.findAll();
        assertThat(orgRoleInstances).hasSize(databaseSizeBeforeUpdate);
        OrgRoleInstance testOrgRoleInstance = orgRoleInstances.get(orgRoleInstances.size() - 1);
        assertThat(testOrgRoleInstance.getOrgRoleName()).isEqualTo(UPDATED_ORG_ROLE_NAME);
        assertThat(testOrgRoleInstance.getParentOrgRoleId()).isEqualTo(UPDATED_PARENT_ORG_ROLE_ID);
        assertThat(testOrgRoleInstance.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testOrgRoleInstance.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testOrgRoleInstance.getIsHead()).isEqualTo(UPDATED_IS_HEAD);
    }

    @Test
    @Transactional
    public void deleteOrgRoleInstance() throws Exception {
        // Initialize the database
        orgRoleInstanceRepository.saveAndFlush(orgRoleInstance);

		int databaseSizeBeforeDelete = orgRoleInstanceRepository.findAll().size();

        // Get the orgRoleInstance
        restOrgRoleInstanceMockMvc.perform(delete("/api/orgRoleInstances/{id}", orgRoleInstance.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OrgRoleInstance> orgRoleInstances = orgRoleInstanceRepository.findAll();
        assertThat(orgRoleInstances).hasSize(databaseSizeBeforeDelete - 1);
    }
}

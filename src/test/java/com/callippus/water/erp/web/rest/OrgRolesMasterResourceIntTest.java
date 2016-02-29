package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OrgRolesMaster;
import com.callippus.water.erp.repository.OrgRolesMasterRepository;

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
 * Test class for the OrgRolesMasterResource REST controller.
 *
 * @see OrgRolesMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OrgRolesMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_ORG_ROLE_NAME = "AAAAA";
    private static final String UPDATED_ORG_ROLE_NAME = "BBBBB";

    private static final Integer DEFAULT_HIERARCHY_ID = 1;
    private static final Integer UPDATED_HIERARCHY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private OrgRolesMasterRepository orgRolesMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrgRolesMasterMockMvc;

    private OrgRolesMaster orgRolesMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrgRolesMasterResource orgRolesMasterResource = new OrgRolesMasterResource();
        ReflectionTestUtils.setField(orgRolesMasterResource, "orgRolesMasterRepository", orgRolesMasterRepository);
        this.restOrgRolesMasterMockMvc = MockMvcBuilders.standaloneSetup(orgRolesMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        orgRolesMaster = new OrgRolesMaster();
        orgRolesMaster.setOrgRoleName(DEFAULT_ORG_ROLE_NAME);
        orgRolesMaster.setHierarchyId(DEFAULT_HIERARCHY_ID);
        orgRolesMaster.setCreationDate(DEFAULT_CREATION_DATE);
        orgRolesMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrgRolesMaster() throws Exception {
        int databaseSizeBeforeCreate = orgRolesMasterRepository.findAll().size();

        // Create the OrgRolesMaster

        restOrgRolesMasterMockMvc.perform(post("/api/orgRolesMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgRolesMaster)))
                .andExpect(status().isCreated());

        // Validate the OrgRolesMaster in the database
        List<OrgRolesMaster> orgRolesMasters = orgRolesMasterRepository.findAll();
        assertThat(orgRolesMasters).hasSize(databaseSizeBeforeCreate + 1);
        OrgRolesMaster testOrgRolesMaster = orgRolesMasters.get(orgRolesMasters.size() - 1);
        assertThat(testOrgRolesMaster.getOrgRoleName()).isEqualTo(DEFAULT_ORG_ROLE_NAME);
        assertThat(testOrgRolesMaster.getHierarchyId()).isEqualTo(DEFAULT_HIERARCHY_ID);
        assertThat(testOrgRolesMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testOrgRolesMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllOrgRolesMasters() throws Exception {
        // Initialize the database
        orgRolesMasterRepository.saveAndFlush(orgRolesMaster);

        // Get all the orgRolesMasters
        restOrgRolesMasterMockMvc.perform(get("/api/orgRolesMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(orgRolesMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].orgRoleName").value(hasItem(DEFAULT_ORG_ROLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].hierarchyId").value(hasItem(DEFAULT_HIERARCHY_ID)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getOrgRolesMaster() throws Exception {
        // Initialize the database
        orgRolesMasterRepository.saveAndFlush(orgRolesMaster);

        // Get the orgRolesMaster
        restOrgRolesMasterMockMvc.perform(get("/api/orgRolesMasters/{id}", orgRolesMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(orgRolesMaster.getId().intValue()))
            .andExpect(jsonPath("$.orgRoleName").value(DEFAULT_ORG_ROLE_NAME.toString()))
            .andExpect(jsonPath("$.hierarchyId").value(DEFAULT_HIERARCHY_ID))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingOrgRolesMaster() throws Exception {
        // Get the orgRolesMaster
        restOrgRolesMasterMockMvc.perform(get("/api/orgRolesMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgRolesMaster() throws Exception {
        // Initialize the database
        orgRolesMasterRepository.saveAndFlush(orgRolesMaster);

		int databaseSizeBeforeUpdate = orgRolesMasterRepository.findAll().size();

        // Update the orgRolesMaster
        orgRolesMaster.setOrgRoleName(UPDATED_ORG_ROLE_NAME);
        orgRolesMaster.setHierarchyId(UPDATED_HIERARCHY_ID);
        orgRolesMaster.setCreationDate(UPDATED_CREATION_DATE);
        orgRolesMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restOrgRolesMasterMockMvc.perform(put("/api/orgRolesMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgRolesMaster)))
                .andExpect(status().isOk());

        // Validate the OrgRolesMaster in the database
        List<OrgRolesMaster> orgRolesMasters = orgRolesMasterRepository.findAll();
        assertThat(orgRolesMasters).hasSize(databaseSizeBeforeUpdate);
        OrgRolesMaster testOrgRolesMaster = orgRolesMasters.get(orgRolesMasters.size() - 1);
        assertThat(testOrgRolesMaster.getOrgRoleName()).isEqualTo(UPDATED_ORG_ROLE_NAME);
        assertThat(testOrgRolesMaster.getHierarchyId()).isEqualTo(UPDATED_HIERARCHY_ID);
        assertThat(testOrgRolesMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testOrgRolesMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteOrgRolesMaster() throws Exception {
        // Initialize the database
        orgRolesMasterRepository.saveAndFlush(orgRolesMaster);

		int databaseSizeBeforeDelete = orgRolesMasterRepository.findAll().size();

        // Get the orgRolesMaster
        restOrgRolesMasterMockMvc.perform(delete("/api/orgRolesMasters/{id}", orgRolesMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OrgRolesMaster> orgRolesMasters = orgRolesMasterRepository.findAll();
        assertThat(orgRolesMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

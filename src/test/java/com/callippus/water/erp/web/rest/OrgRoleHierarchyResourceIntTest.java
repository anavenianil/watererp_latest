package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OrgRoleHierarchy;
import com.callippus.water.erp.repository.OrgRoleHierarchyRepository;

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
 * Test class for the OrgRoleHierarchyResource REST controller.
 *
 * @see OrgRoleHierarchyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OrgRoleHierarchyResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_ROLE_HIERARCHY_NAME = "AAAAA";
    private static final String UPDATED_ROLE_HIERARCHY_NAME = "BBBBB";

    private static final Integer DEFAULT_PARENT_ROLE_HIERARCHY_ID = 1;
    private static final Integer UPDATED_PARENT_ROLE_HIERARCHY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private OrgRoleHierarchyRepository orgRoleHierarchyRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrgRoleHierarchyMockMvc;

    private OrgRoleHierarchy orgRoleHierarchy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrgRoleHierarchyResource orgRoleHierarchyResource = new OrgRoleHierarchyResource();
        ReflectionTestUtils.setField(orgRoleHierarchyResource, "orgRoleHierarchyRepository", orgRoleHierarchyRepository);
        this.restOrgRoleHierarchyMockMvc = MockMvcBuilders.standaloneSetup(orgRoleHierarchyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        orgRoleHierarchy = new OrgRoleHierarchy();
        orgRoleHierarchy.setRoleHierarchyName(DEFAULT_ROLE_HIERARCHY_NAME);
        orgRoleHierarchy.setParentRoleHierarchyId(DEFAULT_PARENT_ROLE_HIERARCHY_ID);
        orgRoleHierarchy.setCreationDate(DEFAULT_CREATION_DATE);
        orgRoleHierarchy.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrgRoleHierarchy() throws Exception {
        int databaseSizeBeforeCreate = orgRoleHierarchyRepository.findAll().size();

        // Create the OrgRoleHierarchy

        restOrgRoleHierarchyMockMvc.perform(post("/api/orgRoleHierarchys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgRoleHierarchy)))
                .andExpect(status().isCreated());

        // Validate the OrgRoleHierarchy in the database
        List<OrgRoleHierarchy> orgRoleHierarchys = orgRoleHierarchyRepository.findAll();
        assertThat(orgRoleHierarchys).hasSize(databaseSizeBeforeCreate + 1);
        OrgRoleHierarchy testOrgRoleHierarchy = orgRoleHierarchys.get(orgRoleHierarchys.size() - 1);
        assertThat(testOrgRoleHierarchy.getRoleHierarchyName()).isEqualTo(DEFAULT_ROLE_HIERARCHY_NAME);
        assertThat(testOrgRoleHierarchy.getParentRoleHierarchyId()).isEqualTo(DEFAULT_PARENT_ROLE_HIERARCHY_ID);
        assertThat(testOrgRoleHierarchy.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testOrgRoleHierarchy.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllOrgRoleHierarchys() throws Exception {
        // Initialize the database
        orgRoleHierarchyRepository.saveAndFlush(orgRoleHierarchy);

        // Get all the orgRoleHierarchys
        restOrgRoleHierarchyMockMvc.perform(get("/api/orgRoleHierarchys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(orgRoleHierarchy.getId().intValue())))
                .andExpect(jsonPath("$.[*].roleHierarchyName").value(hasItem(DEFAULT_ROLE_HIERARCHY_NAME.toString())))
                .andExpect(jsonPath("$.[*].parentRoleHierarchyId").value(hasItem(DEFAULT_PARENT_ROLE_HIERARCHY_ID)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getOrgRoleHierarchy() throws Exception {
        // Initialize the database
        orgRoleHierarchyRepository.saveAndFlush(orgRoleHierarchy);

        // Get the orgRoleHierarchy
        restOrgRoleHierarchyMockMvc.perform(get("/api/orgRoleHierarchys/{id}", orgRoleHierarchy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(orgRoleHierarchy.getId().intValue()))
            .andExpect(jsonPath("$.roleHierarchyName").value(DEFAULT_ROLE_HIERARCHY_NAME.toString()))
            .andExpect(jsonPath("$.parentRoleHierarchyId").value(DEFAULT_PARENT_ROLE_HIERARCHY_ID))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingOrgRoleHierarchy() throws Exception {
        // Get the orgRoleHierarchy
        restOrgRoleHierarchyMockMvc.perform(get("/api/orgRoleHierarchys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgRoleHierarchy() throws Exception {
        // Initialize the database
        orgRoleHierarchyRepository.saveAndFlush(orgRoleHierarchy);

		int databaseSizeBeforeUpdate = orgRoleHierarchyRepository.findAll().size();

        // Update the orgRoleHierarchy
        orgRoleHierarchy.setRoleHierarchyName(UPDATED_ROLE_HIERARCHY_NAME);
        orgRoleHierarchy.setParentRoleHierarchyId(UPDATED_PARENT_ROLE_HIERARCHY_ID);
        orgRoleHierarchy.setCreationDate(UPDATED_CREATION_DATE);
        orgRoleHierarchy.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restOrgRoleHierarchyMockMvc.perform(put("/api/orgRoleHierarchys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgRoleHierarchy)))
                .andExpect(status().isOk());

        // Validate the OrgRoleHierarchy in the database
        List<OrgRoleHierarchy> orgRoleHierarchys = orgRoleHierarchyRepository.findAll();
        assertThat(orgRoleHierarchys).hasSize(databaseSizeBeforeUpdate);
        OrgRoleHierarchy testOrgRoleHierarchy = orgRoleHierarchys.get(orgRoleHierarchys.size() - 1);
        assertThat(testOrgRoleHierarchy.getRoleHierarchyName()).isEqualTo(UPDATED_ROLE_HIERARCHY_NAME);
        assertThat(testOrgRoleHierarchy.getParentRoleHierarchyId()).isEqualTo(UPDATED_PARENT_ROLE_HIERARCHY_ID);
        assertThat(testOrgRoleHierarchy.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testOrgRoleHierarchy.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteOrgRoleHierarchy() throws Exception {
        // Initialize the database
        orgRoleHierarchyRepository.saveAndFlush(orgRoleHierarchy);

		int databaseSizeBeforeDelete = orgRoleHierarchyRepository.findAll().size();

        // Get the orgRoleHierarchy
        restOrgRoleHierarchyMockMvc.perform(delete("/api/orgRoleHierarchys/{id}", orgRoleHierarchy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OrgRoleHierarchy> orgRoleHierarchys = orgRoleHierarchyRepository.findAll();
        assertThat(orgRoleHierarchys).hasSize(databaseSizeBeforeDelete - 1);
    }
}

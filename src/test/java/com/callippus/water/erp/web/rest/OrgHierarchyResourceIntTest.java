package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OrgHierarchy;
import com.callippus.water.erp.repository.OrgHierarchyRepository;

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
 * Test class for the OrgHierarchyResource REST controller.
 *
 * @see OrgHierarchyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OrgHierarchyResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_HIERARCHY_NAME = "AAAAA";
    private static final String UPDATED_HIERARCHY_NAME = "BBBBB";

    private static final Integer DEFAULT_PARENT_HIERARCHY_ID = 1;
    private static final Integer UPDATED_PARENT_HIERARCHY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private OrgHierarchyRepository orgHierarchyRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOrgHierarchyMockMvc;

    private OrgHierarchy orgHierarchy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OrgHierarchyResource orgHierarchyResource = new OrgHierarchyResource();
        ReflectionTestUtils.setField(orgHierarchyResource, "orgHierarchyRepository", orgHierarchyRepository);
        this.restOrgHierarchyMockMvc = MockMvcBuilders.standaloneSetup(orgHierarchyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        orgHierarchy = new OrgHierarchy();
        orgHierarchy.setHierarchyName(DEFAULT_HIERARCHY_NAME);
        orgHierarchy.setParentHierarchyId(DEFAULT_PARENT_HIERARCHY_ID);
        orgHierarchy.setCreationDate(DEFAULT_CREATION_DATE);
        orgHierarchy.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createOrgHierarchy() throws Exception {
        int databaseSizeBeforeCreate = orgHierarchyRepository.findAll().size();

        // Create the OrgHierarchy

        restOrgHierarchyMockMvc.perform(post("/api/orgHierarchys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgHierarchy)))
                .andExpect(status().isCreated());

        // Validate the OrgHierarchy in the database
        List<OrgHierarchy> orgHierarchys = orgHierarchyRepository.findAll();
        assertThat(orgHierarchys).hasSize(databaseSizeBeforeCreate + 1);
        OrgHierarchy testOrgHierarchy = orgHierarchys.get(orgHierarchys.size() - 1);
        assertThat(testOrgHierarchy.getHierarchyName()).isEqualTo(DEFAULT_HIERARCHY_NAME);
        assertThat(testOrgHierarchy.getParentHierarchyId()).isEqualTo(DEFAULT_PARENT_HIERARCHY_ID);
        assertThat(testOrgHierarchy.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testOrgHierarchy.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllOrgHierarchys() throws Exception {
        // Initialize the database
        orgHierarchyRepository.saveAndFlush(orgHierarchy);

        // Get all the orgHierarchys
        restOrgHierarchyMockMvc.perform(get("/api/orgHierarchys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(orgHierarchy.getId().intValue())))
                .andExpect(jsonPath("$.[*].hierarchyName").value(hasItem(DEFAULT_HIERARCHY_NAME.toString())))
                .andExpect(jsonPath("$.[*].parentHierarchyId").value(hasItem(DEFAULT_PARENT_HIERARCHY_ID)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getOrgHierarchy() throws Exception {
        // Initialize the database
        orgHierarchyRepository.saveAndFlush(orgHierarchy);

        // Get the orgHierarchy
        restOrgHierarchyMockMvc.perform(get("/api/orgHierarchys/{id}", orgHierarchy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(orgHierarchy.getId().intValue()))
            .andExpect(jsonPath("$.hierarchyName").value(DEFAULT_HIERARCHY_NAME.toString()))
            .andExpect(jsonPath("$.parentHierarchyId").value(DEFAULT_PARENT_HIERARCHY_ID))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingOrgHierarchy() throws Exception {
        // Get the orgHierarchy
        restOrgHierarchyMockMvc.perform(get("/api/orgHierarchys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOrgHierarchy() throws Exception {
        // Initialize the database
        orgHierarchyRepository.saveAndFlush(orgHierarchy);

		int databaseSizeBeforeUpdate = orgHierarchyRepository.findAll().size();

        // Update the orgHierarchy
        orgHierarchy.setHierarchyName(UPDATED_HIERARCHY_NAME);
        orgHierarchy.setParentHierarchyId(UPDATED_PARENT_HIERARCHY_ID);
        orgHierarchy.setCreationDate(UPDATED_CREATION_DATE);
        orgHierarchy.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restOrgHierarchyMockMvc.perform(put("/api/orgHierarchys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(orgHierarchy)))
                .andExpect(status().isOk());

        // Validate the OrgHierarchy in the database
        List<OrgHierarchy> orgHierarchys = orgHierarchyRepository.findAll();
        assertThat(orgHierarchys).hasSize(databaseSizeBeforeUpdate);
        OrgHierarchy testOrgHierarchy = orgHierarchys.get(orgHierarchys.size() - 1);
        assertThat(testOrgHierarchy.getHierarchyName()).isEqualTo(UPDATED_HIERARCHY_NAME);
        assertThat(testOrgHierarchy.getParentHierarchyId()).isEqualTo(UPDATED_PARENT_HIERARCHY_ID);
        assertThat(testOrgHierarchy.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testOrgHierarchy.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteOrgHierarchy() throws Exception {
        // Initialize the database
        orgHierarchyRepository.saveAndFlush(orgHierarchy);

		int databaseSizeBeforeDelete = orgHierarchyRepository.findAll().size();

        // Get the orgHierarchy
        restOrgHierarchyMockMvc.perform(delete("/api/orgHierarchys/{id}", orgHierarchy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OrgHierarchy> orgHierarchys = orgHierarchyRepository.findAll();
        assertThat(orgHierarchys).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.RoleWorkflowMapping;
import com.callippus.water.erp.repository.RoleWorkflowMappingRepository;

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
 * Test class for the RoleWorkflowMappingResource REST controller.
 *
 * @see RoleWorkflowMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class RoleWorkflowMappingResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private RoleWorkflowMappingRepository roleWorkflowMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restRoleWorkflowMappingMockMvc;

    private RoleWorkflowMapping roleWorkflowMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RoleWorkflowMappingResource roleWorkflowMappingResource = new RoleWorkflowMappingResource();
        ReflectionTestUtils.setField(roleWorkflowMappingResource, "roleWorkflowMappingRepository", roleWorkflowMappingRepository);
        this.restRoleWorkflowMappingMockMvc = MockMvcBuilders.standaloneSetup(roleWorkflowMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        roleWorkflowMapping = new RoleWorkflowMapping();
        roleWorkflowMapping.setCreationDate(DEFAULT_CREATION_DATE);
        roleWorkflowMapping.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createRoleWorkflowMapping() throws Exception {
        int databaseSizeBeforeCreate = roleWorkflowMappingRepository.findAll().size();

        // Create the RoleWorkflowMapping

        restRoleWorkflowMappingMockMvc.perform(post("/api/roleWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(roleWorkflowMapping)))
                .andExpect(status().isCreated());

        // Validate the RoleWorkflowMapping in the database
        List<RoleWorkflowMapping> roleWorkflowMappings = roleWorkflowMappingRepository.findAll();
        assertThat(roleWorkflowMappings).hasSize(databaseSizeBeforeCreate + 1);
        RoleWorkflowMapping testRoleWorkflowMapping = roleWorkflowMappings.get(roleWorkflowMappings.size() - 1);
        assertThat(testRoleWorkflowMapping.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testRoleWorkflowMapping.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllRoleWorkflowMappings() throws Exception {
        // Initialize the database
        roleWorkflowMappingRepository.saveAndFlush(roleWorkflowMapping);

        // Get all the roleWorkflowMappings
        restRoleWorkflowMappingMockMvc.perform(get("/api/roleWorkflowMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(roleWorkflowMapping.getId().intValue())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getRoleWorkflowMapping() throws Exception {
        // Initialize the database
        roleWorkflowMappingRepository.saveAndFlush(roleWorkflowMapping);

        // Get the roleWorkflowMapping
        restRoleWorkflowMappingMockMvc.perform(get("/api/roleWorkflowMappings/{id}", roleWorkflowMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(roleWorkflowMapping.getId().intValue()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingRoleWorkflowMapping() throws Exception {
        // Get the roleWorkflowMapping
        restRoleWorkflowMappingMockMvc.perform(get("/api/roleWorkflowMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoleWorkflowMapping() throws Exception {
        // Initialize the database
        roleWorkflowMappingRepository.saveAndFlush(roleWorkflowMapping);

		int databaseSizeBeforeUpdate = roleWorkflowMappingRepository.findAll().size();

        // Update the roleWorkflowMapping
        roleWorkflowMapping.setCreationDate(UPDATED_CREATION_DATE);
        roleWorkflowMapping.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restRoleWorkflowMappingMockMvc.perform(put("/api/roleWorkflowMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(roleWorkflowMapping)))
                .andExpect(status().isOk());

        // Validate the RoleWorkflowMapping in the database
        List<RoleWorkflowMapping> roleWorkflowMappings = roleWorkflowMappingRepository.findAll();
        assertThat(roleWorkflowMappings).hasSize(databaseSizeBeforeUpdate);
        RoleWorkflowMapping testRoleWorkflowMapping = roleWorkflowMappings.get(roleWorkflowMappings.size() - 1);
        assertThat(testRoleWorkflowMapping.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testRoleWorkflowMapping.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteRoleWorkflowMapping() throws Exception {
        // Initialize the database
        roleWorkflowMappingRepository.saveAndFlush(roleWorkflowMapping);

		int databaseSizeBeforeDelete = roleWorkflowMappingRepository.findAll().size();

        // Get the roleWorkflowMapping
        restRoleWorkflowMappingMockMvc.perform(delete("/api/roleWorkflowMappings/{id}", roleWorkflowMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<RoleWorkflowMapping> roleWorkflowMappings = roleWorkflowMappingRepository.findAll();
        assertThat(roleWorkflowMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.EmpRoleMapping;
import com.callippus.water.erp.repository.EmpRoleMappingRepository;

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
 * Test class for the EmpRoleMappingResource REST controller.
 *
 * @see EmpRoleMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EmpRoleMappingResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_INTERNAL_DIVISION = "AAAAA";
    private static final String UPDATED_INTERNAL_DIVISION = "BBBBB";
    private static final String DEFAULT_INTERNAL_ROLE = "AAAAA";
    private static final String UPDATED_INTERNAL_ROLE = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    private static final Integer DEFAULT_PARENT_ROLE_ID = 1;
    private static final Integer UPDATED_PARENT_ROLE_ID = 2;

    @Inject
    private EmpRoleMappingRepository empRoleMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restEmpRoleMappingMockMvc;

    private EmpRoleMapping empRoleMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmpRoleMappingResource empRoleMappingResource = new EmpRoleMappingResource();
        ReflectionTestUtils.setField(empRoleMappingResource, "empRoleMappingRepository", empRoleMappingRepository);
        this.restEmpRoleMappingMockMvc = MockMvcBuilders.standaloneSetup(empRoleMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        empRoleMapping = new EmpRoleMapping();
        empRoleMapping.setInternalDivision(DEFAULT_INTERNAL_DIVISION);
        empRoleMapping.setInternalRole(DEFAULT_INTERNAL_ROLE);
        empRoleMapping.setCreationDate(DEFAULT_CREATION_DATE);
        empRoleMapping.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        empRoleMapping.setParentRoleId(DEFAULT_PARENT_ROLE_ID);
    }

    @Test
    @Transactional
    public void createEmpRoleMapping() throws Exception {
        int databaseSizeBeforeCreate = empRoleMappingRepository.findAll().size();

        // Create the EmpRoleMapping

        restEmpRoleMappingMockMvc.perform(post("/api/empRoleMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(empRoleMapping)))
                .andExpect(status().isCreated());

        // Validate the EmpRoleMapping in the database
        List<EmpRoleMapping> empRoleMappings = empRoleMappingRepository.findAll();
        assertThat(empRoleMappings).hasSize(databaseSizeBeforeCreate + 1);
        EmpRoleMapping testEmpRoleMapping = empRoleMappings.get(empRoleMappings.size() - 1);
        assertThat(testEmpRoleMapping.getInternalDivision()).isEqualTo(DEFAULT_INTERNAL_DIVISION);
        assertThat(testEmpRoleMapping.getInternalRole()).isEqualTo(DEFAULT_INTERNAL_ROLE);
        assertThat(testEmpRoleMapping.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testEmpRoleMapping.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testEmpRoleMapping.getParentRoleId()).isEqualTo(DEFAULT_PARENT_ROLE_ID);
    }

    @Test
    @Transactional
    public void getAllEmpRoleMappings() throws Exception {
        // Initialize the database
        empRoleMappingRepository.saveAndFlush(empRoleMapping);

        // Get all the empRoleMappings
        restEmpRoleMappingMockMvc.perform(get("/api/empRoleMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(empRoleMapping.getId().intValue())))
                .andExpect(jsonPath("$.[*].internalDivision").value(hasItem(DEFAULT_INTERNAL_DIVISION.toString())))
                .andExpect(jsonPath("$.[*].internalRole").value(hasItem(DEFAULT_INTERNAL_ROLE.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].parentRoleId").value(hasItem(DEFAULT_PARENT_ROLE_ID)));
    }

    @Test
    @Transactional
    public void getEmpRoleMapping() throws Exception {
        // Initialize the database
        empRoleMappingRepository.saveAndFlush(empRoleMapping);

        // Get the empRoleMapping
        restEmpRoleMappingMockMvc.perform(get("/api/empRoleMappings/{id}", empRoleMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(empRoleMapping.getId().intValue()))
            .andExpect(jsonPath("$.internalDivision").value(DEFAULT_INTERNAL_DIVISION.toString()))
            .andExpect(jsonPath("$.internalRole").value(DEFAULT_INTERNAL_ROLE.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.parentRoleId").value(DEFAULT_PARENT_ROLE_ID));
    }

    @Test
    @Transactional
    public void getNonExistingEmpRoleMapping() throws Exception {
        // Get the empRoleMapping
        restEmpRoleMappingMockMvc.perform(get("/api/empRoleMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpRoleMapping() throws Exception {
        // Initialize the database
        empRoleMappingRepository.saveAndFlush(empRoleMapping);

		int databaseSizeBeforeUpdate = empRoleMappingRepository.findAll().size();

        // Update the empRoleMapping
        empRoleMapping.setInternalDivision(UPDATED_INTERNAL_DIVISION);
        empRoleMapping.setInternalRole(UPDATED_INTERNAL_ROLE);
        empRoleMapping.setCreationDate(UPDATED_CREATION_DATE);
        empRoleMapping.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        empRoleMapping.setParentRoleId(UPDATED_PARENT_ROLE_ID);

        restEmpRoleMappingMockMvc.perform(put("/api/empRoleMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(empRoleMapping)))
                .andExpect(status().isOk());

        // Validate the EmpRoleMapping in the database
        List<EmpRoleMapping> empRoleMappings = empRoleMappingRepository.findAll();
        assertThat(empRoleMappings).hasSize(databaseSizeBeforeUpdate);
        EmpRoleMapping testEmpRoleMapping = empRoleMappings.get(empRoleMappings.size() - 1);
        assertThat(testEmpRoleMapping.getInternalDivision()).isEqualTo(UPDATED_INTERNAL_DIVISION);
        assertThat(testEmpRoleMapping.getInternalRole()).isEqualTo(UPDATED_INTERNAL_ROLE);
        assertThat(testEmpRoleMapping.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testEmpRoleMapping.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testEmpRoleMapping.getParentRoleId()).isEqualTo(UPDATED_PARENT_ROLE_ID);
    }

    @Test
    @Transactional
    public void deleteEmpRoleMapping() throws Exception {
        // Initialize the database
        empRoleMappingRepository.saveAndFlush(empRoleMapping);

		int databaseSizeBeforeDelete = empRoleMappingRepository.findAll().size();

        // Get the empRoleMapping
        restEmpRoleMappingMockMvc.perform(delete("/api/empRoleMappings/{id}", empRoleMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<EmpRoleMapping> empRoleMappings = empRoleMappingRepository.findAll();
        assertThat(empRoleMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}

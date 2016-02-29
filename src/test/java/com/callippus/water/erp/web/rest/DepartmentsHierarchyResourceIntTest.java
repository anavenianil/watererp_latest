package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DepartmentsHierarchy;
import com.callippus.water.erp.repository.DepartmentsHierarchyRepository;

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
 * Test class for the DepartmentsHierarchyResource REST controller.
 *
 * @see DepartmentsHierarchyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DepartmentsHierarchyResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_DEPT_HIERARCHY_NAME = "AAAAA";
    private static final String UPDATED_DEPT_HIERARCHY_NAME = "BBBBB";

    private static final Integer DEFAULT_PARENT_DEPT_HIERARCHY_ID = 1;
    private static final Integer UPDATED_PARENT_DEPT_HIERARCHY_ID = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private DepartmentsHierarchyRepository departmentsHierarchyRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDepartmentsHierarchyMockMvc;

    private DepartmentsHierarchy departmentsHierarchy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentsHierarchyResource departmentsHierarchyResource = new DepartmentsHierarchyResource();
        ReflectionTestUtils.setField(departmentsHierarchyResource, "departmentsHierarchyRepository", departmentsHierarchyRepository);
        this.restDepartmentsHierarchyMockMvc = MockMvcBuilders.standaloneSetup(departmentsHierarchyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        departmentsHierarchy = new DepartmentsHierarchy();
        departmentsHierarchy.setDeptHierarchyName(DEFAULT_DEPT_HIERARCHY_NAME);
        departmentsHierarchy.setParentDeptHierarchyId(DEFAULT_PARENT_DEPT_HIERARCHY_ID);
        departmentsHierarchy.setCreationDate(DEFAULT_CREATION_DATE);
        departmentsHierarchy.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createDepartmentsHierarchy() throws Exception {
        int databaseSizeBeforeCreate = departmentsHierarchyRepository.findAll().size();

        // Create the DepartmentsHierarchy

        restDepartmentsHierarchyMockMvc.perform(post("/api/departmentsHierarchys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(departmentsHierarchy)))
                .andExpect(status().isCreated());

        // Validate the DepartmentsHierarchy in the database
        List<DepartmentsHierarchy> departmentsHierarchys = departmentsHierarchyRepository.findAll();
        assertThat(departmentsHierarchys).hasSize(databaseSizeBeforeCreate + 1);
        DepartmentsHierarchy testDepartmentsHierarchy = departmentsHierarchys.get(departmentsHierarchys.size() - 1);
        assertThat(testDepartmentsHierarchy.getDeptHierarchyName()).isEqualTo(DEFAULT_DEPT_HIERARCHY_NAME);
        assertThat(testDepartmentsHierarchy.getParentDeptHierarchyId()).isEqualTo(DEFAULT_PARENT_DEPT_HIERARCHY_ID);
        assertThat(testDepartmentsHierarchy.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testDepartmentsHierarchy.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllDepartmentsHierarchys() throws Exception {
        // Initialize the database
        departmentsHierarchyRepository.saveAndFlush(departmentsHierarchy);

        // Get all the departmentsHierarchys
        restDepartmentsHierarchyMockMvc.perform(get("/api/departmentsHierarchys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(departmentsHierarchy.getId().intValue())))
                .andExpect(jsonPath("$.[*].deptHierarchyName").value(hasItem(DEFAULT_DEPT_HIERARCHY_NAME.toString())))
                .andExpect(jsonPath("$.[*].parentDeptHierarchyId").value(hasItem(DEFAULT_PARENT_DEPT_HIERARCHY_ID)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getDepartmentsHierarchy() throws Exception {
        // Initialize the database
        departmentsHierarchyRepository.saveAndFlush(departmentsHierarchy);

        // Get the departmentsHierarchy
        restDepartmentsHierarchyMockMvc.perform(get("/api/departmentsHierarchys/{id}", departmentsHierarchy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(departmentsHierarchy.getId().intValue()))
            .andExpect(jsonPath("$.deptHierarchyName").value(DEFAULT_DEPT_HIERARCHY_NAME.toString()))
            .andExpect(jsonPath("$.parentDeptHierarchyId").value(DEFAULT_PARENT_DEPT_HIERARCHY_ID))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingDepartmentsHierarchy() throws Exception {
        // Get the departmentsHierarchy
        restDepartmentsHierarchyMockMvc.perform(get("/api/departmentsHierarchys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartmentsHierarchy() throws Exception {
        // Initialize the database
        departmentsHierarchyRepository.saveAndFlush(departmentsHierarchy);

		int databaseSizeBeforeUpdate = departmentsHierarchyRepository.findAll().size();

        // Update the departmentsHierarchy
        departmentsHierarchy.setDeptHierarchyName(UPDATED_DEPT_HIERARCHY_NAME);
        departmentsHierarchy.setParentDeptHierarchyId(UPDATED_PARENT_DEPT_HIERARCHY_ID);
        departmentsHierarchy.setCreationDate(UPDATED_CREATION_DATE);
        departmentsHierarchy.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restDepartmentsHierarchyMockMvc.perform(put("/api/departmentsHierarchys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(departmentsHierarchy)))
                .andExpect(status().isOk());

        // Validate the DepartmentsHierarchy in the database
        List<DepartmentsHierarchy> departmentsHierarchys = departmentsHierarchyRepository.findAll();
        assertThat(departmentsHierarchys).hasSize(databaseSizeBeforeUpdate);
        DepartmentsHierarchy testDepartmentsHierarchy = departmentsHierarchys.get(departmentsHierarchys.size() - 1);
        assertThat(testDepartmentsHierarchy.getDeptHierarchyName()).isEqualTo(UPDATED_DEPT_HIERARCHY_NAME);
        assertThat(testDepartmentsHierarchy.getParentDeptHierarchyId()).isEqualTo(UPDATED_PARENT_DEPT_HIERARCHY_ID);
        assertThat(testDepartmentsHierarchy.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testDepartmentsHierarchy.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteDepartmentsHierarchy() throws Exception {
        // Initialize the database
        departmentsHierarchyRepository.saveAndFlush(departmentsHierarchy);

		int databaseSizeBeforeDelete = departmentsHierarchyRepository.findAll().size();

        // Get the departmentsHierarchy
        restDepartmentsHierarchyMockMvc.perform(delete("/api/departmentsHierarchys/{id}", departmentsHierarchy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DepartmentsHierarchy> departmentsHierarchys = departmentsHierarchyRepository.findAll();
        assertThat(departmentsHierarchys).hasSize(databaseSizeBeforeDelete - 1);
    }
}

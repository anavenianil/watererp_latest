package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DepartmentsMaster;
import com.callippus.water.erp.repository.DepartmentsMasterRepository;

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
 * Test class for the DepartmentsMasterResource REST controller.
 *
 * @see DepartmentsMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DepartmentsMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_DEPARTMENT_NAME = "AAAAA";
    private static final String UPDATED_DEPARTMENT_NAME = "BBBBB";

    private static final Integer DEFAULT_PARENT_DEPARMENT = 1;
    private static final Integer UPDATED_PARENT_DEPARMENT = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    @Inject
    private DepartmentsMasterRepository departmentsMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDepartmentsMasterMockMvc;

    private DepartmentsMaster departmentsMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentsMasterResource departmentsMasterResource = new DepartmentsMasterResource();
        ReflectionTestUtils.setField(departmentsMasterResource, "departmentsMasterRepository", departmentsMasterRepository);
        this.restDepartmentsMasterMockMvc = MockMvcBuilders.standaloneSetup(departmentsMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        departmentsMaster = new DepartmentsMaster();
        departmentsMaster.setDepartmentName(DEFAULT_DEPARTMENT_NAME);
        departmentsMaster.setParentDeparment(DEFAULT_PARENT_DEPARMENT);
        departmentsMaster.setCreationDate(DEFAULT_CREATION_DATE);
        departmentsMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createDepartmentsMaster() throws Exception {
        int databaseSizeBeforeCreate = departmentsMasterRepository.findAll().size();

        // Create the DepartmentsMaster

        restDepartmentsMasterMockMvc.perform(post("/api/departmentsMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(departmentsMaster)))
                .andExpect(status().isCreated());

        // Validate the DepartmentsMaster in the database
        List<DepartmentsMaster> departmentsMasters = departmentsMasterRepository.findAll();
        assertThat(departmentsMasters).hasSize(databaseSizeBeforeCreate + 1);
        DepartmentsMaster testDepartmentsMaster = departmentsMasters.get(departmentsMasters.size() - 1);
        assertThat(testDepartmentsMaster.getDepartmentName()).isEqualTo(DEFAULT_DEPARTMENT_NAME);
        assertThat(testDepartmentsMaster.getParentDeparment()).isEqualTo(DEFAULT_PARENT_DEPARMENT);
        assertThat(testDepartmentsMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testDepartmentsMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllDepartmentsMasters() throws Exception {
        // Initialize the database
        departmentsMasterRepository.saveAndFlush(departmentsMaster);

        // Get all the departmentsMasters
        restDepartmentsMasterMockMvc.perform(get("/api/departmentsMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(departmentsMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].departmentName").value(hasItem(DEFAULT_DEPARTMENT_NAME.toString())))
                .andExpect(jsonPath("$.[*].parentDeparment").value(hasItem(DEFAULT_PARENT_DEPARMENT)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getDepartmentsMaster() throws Exception {
        // Initialize the database
        departmentsMasterRepository.saveAndFlush(departmentsMaster);

        // Get the departmentsMaster
        restDepartmentsMasterMockMvc.perform(get("/api/departmentsMasters/{id}", departmentsMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(departmentsMaster.getId().intValue()))
            .andExpect(jsonPath("$.departmentName").value(DEFAULT_DEPARTMENT_NAME.toString()))
            .andExpect(jsonPath("$.parentDeparment").value(DEFAULT_PARENT_DEPARMENT))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingDepartmentsMaster() throws Exception {
        // Get the departmentsMaster
        restDepartmentsMasterMockMvc.perform(get("/api/departmentsMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartmentsMaster() throws Exception {
        // Initialize the database
        departmentsMasterRepository.saveAndFlush(departmentsMaster);

		int databaseSizeBeforeUpdate = departmentsMasterRepository.findAll().size();

        // Update the departmentsMaster
        departmentsMaster.setDepartmentName(UPDATED_DEPARTMENT_NAME);
        departmentsMaster.setParentDeparment(UPDATED_PARENT_DEPARMENT);
        departmentsMaster.setCreationDate(UPDATED_CREATION_DATE);
        departmentsMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restDepartmentsMasterMockMvc.perform(put("/api/departmentsMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(departmentsMaster)))
                .andExpect(status().isOk());

        // Validate the DepartmentsMaster in the database
        List<DepartmentsMaster> departmentsMasters = departmentsMasterRepository.findAll();
        assertThat(departmentsMasters).hasSize(databaseSizeBeforeUpdate);
        DepartmentsMaster testDepartmentsMaster = departmentsMasters.get(departmentsMasters.size() - 1);
        assertThat(testDepartmentsMaster.getDepartmentName()).isEqualTo(UPDATED_DEPARTMENT_NAME);
        assertThat(testDepartmentsMaster.getParentDeparment()).isEqualTo(UPDATED_PARENT_DEPARMENT);
        assertThat(testDepartmentsMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testDepartmentsMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteDepartmentsMaster() throws Exception {
        // Initialize the database
        departmentsMasterRepository.saveAndFlush(departmentsMaster);

		int databaseSizeBeforeDelete = departmentsMasterRepository.findAll().size();

        // Get the departmentsMaster
        restDepartmentsMasterMockMvc.perform(delete("/api/departmentsMasters/{id}", departmentsMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DepartmentsMaster> departmentsMasters = departmentsMasterRepository.findAll();
        assertThat(departmentsMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

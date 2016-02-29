package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DepartmentTypeMaster;
import com.callippus.water.erp.repository.DepartmentTypeMasterRepository;

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
 * Test class for the DepartmentTypeMasterResource REST controller.
 *
 * @see DepartmentTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DepartmentTypeMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private DepartmentTypeMasterRepository departmentTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDepartmentTypeMasterMockMvc;

    private DepartmentTypeMaster departmentTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DepartmentTypeMasterResource departmentTypeMasterResource = new DepartmentTypeMasterResource();
        ReflectionTestUtils.setField(departmentTypeMasterResource, "departmentTypeMasterRepository", departmentTypeMasterRepository);
        this.restDepartmentTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(departmentTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        departmentTypeMaster = new DepartmentTypeMaster();
        departmentTypeMaster.setName(DEFAULT_NAME);
        departmentTypeMaster.setCreationDate(DEFAULT_CREATION_DATE);
        departmentTypeMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        departmentTypeMaster.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDepartmentTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = departmentTypeMasterRepository.findAll().size();

        // Create the DepartmentTypeMaster

        restDepartmentTypeMasterMockMvc.perform(post("/api/departmentTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(departmentTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the DepartmentTypeMaster in the database
        List<DepartmentTypeMaster> departmentTypeMasters = departmentTypeMasterRepository.findAll();
        assertThat(departmentTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        DepartmentTypeMaster testDepartmentTypeMaster = departmentTypeMasters.get(departmentTypeMasters.size() - 1);
        assertThat(testDepartmentTypeMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDepartmentTypeMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testDepartmentTypeMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testDepartmentTypeMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDepartmentTypeMasters() throws Exception {
        // Initialize the database
        departmentTypeMasterRepository.saveAndFlush(departmentTypeMaster);

        // Get all the departmentTypeMasters
        restDepartmentTypeMasterMockMvc.perform(get("/api/departmentTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(departmentTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getDepartmentTypeMaster() throws Exception {
        // Initialize the database
        departmentTypeMasterRepository.saveAndFlush(departmentTypeMaster);

        // Get the departmentTypeMaster
        restDepartmentTypeMasterMockMvc.perform(get("/api/departmentTypeMasters/{id}", departmentTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(departmentTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDepartmentTypeMaster() throws Exception {
        // Get the departmentTypeMaster
        restDepartmentTypeMasterMockMvc.perform(get("/api/departmentTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepartmentTypeMaster() throws Exception {
        // Initialize the database
        departmentTypeMasterRepository.saveAndFlush(departmentTypeMaster);

		int databaseSizeBeforeUpdate = departmentTypeMasterRepository.findAll().size();

        // Update the departmentTypeMaster
        departmentTypeMaster.setName(UPDATED_NAME);
        departmentTypeMaster.setCreationDate(UPDATED_CREATION_DATE);
        departmentTypeMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        departmentTypeMaster.setDescription(UPDATED_DESCRIPTION);

        restDepartmentTypeMasterMockMvc.perform(put("/api/departmentTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(departmentTypeMaster)))
                .andExpect(status().isOk());

        // Validate the DepartmentTypeMaster in the database
        List<DepartmentTypeMaster> departmentTypeMasters = departmentTypeMasterRepository.findAll();
        assertThat(departmentTypeMasters).hasSize(databaseSizeBeforeUpdate);
        DepartmentTypeMaster testDepartmentTypeMaster = departmentTypeMasters.get(departmentTypeMasters.size() - 1);
        assertThat(testDepartmentTypeMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDepartmentTypeMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testDepartmentTypeMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDepartmentTypeMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteDepartmentTypeMaster() throws Exception {
        // Initialize the database
        departmentTypeMasterRepository.saveAndFlush(departmentTypeMaster);

		int databaseSizeBeforeDelete = departmentTypeMasterRepository.findAll().size();

        // Get the departmentTypeMaster
        restDepartmentTypeMasterMockMvc.perform(delete("/api/departmentTypeMasters/{id}", departmentTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DepartmentTypeMaster> departmentTypeMasters = departmentTypeMasterRepository.findAll();
        assertThat(departmentTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

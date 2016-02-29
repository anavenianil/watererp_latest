package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.SubDesigCategoryMaster;
import com.callippus.water.erp.repository.SubDesigCategoryMasterRepository;

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
 * Test class for the SubDesigCategoryMasterResource REST controller.
 *
 * @see SubDesigCategoryMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SubDesigCategoryMasterResourceIntTest {

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
    private static final String DEFAULT_ALIAS = "AAAAA";
    private static final String UPDATED_ALIAS = "BBBBB";

    private static final Integer DEFAULT_ORDER_BY = 1;
    private static final Integer UPDATED_ORDER_BY = 2;

    @Inject
    private SubDesigCategoryMasterRepository subDesigCategoryMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSubDesigCategoryMasterMockMvc;

    private SubDesigCategoryMaster subDesigCategoryMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SubDesigCategoryMasterResource subDesigCategoryMasterResource = new SubDesigCategoryMasterResource();
        ReflectionTestUtils.setField(subDesigCategoryMasterResource, "subDesigCategoryMasterRepository", subDesigCategoryMasterRepository);
        this.restSubDesigCategoryMasterMockMvc = MockMvcBuilders.standaloneSetup(subDesigCategoryMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        subDesigCategoryMaster = new SubDesigCategoryMaster();
        subDesigCategoryMaster.setName(DEFAULT_NAME);
        subDesigCategoryMaster.setCreationDate(DEFAULT_CREATION_DATE);
        subDesigCategoryMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        subDesigCategoryMaster.setDescription(DEFAULT_DESCRIPTION);
        subDesigCategoryMaster.setAlias(DEFAULT_ALIAS);
        subDesigCategoryMaster.setOrderBy(DEFAULT_ORDER_BY);
    }

    @Test
    @Transactional
    public void createSubDesigCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = subDesigCategoryMasterRepository.findAll().size();

        // Create the SubDesigCategoryMaster

        restSubDesigCategoryMasterMockMvc.perform(post("/api/subDesigCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(subDesigCategoryMaster)))
                .andExpect(status().isCreated());

        // Validate the SubDesigCategoryMaster in the database
        List<SubDesigCategoryMaster> subDesigCategoryMasters = subDesigCategoryMasterRepository.findAll();
        assertThat(subDesigCategoryMasters).hasSize(databaseSizeBeforeCreate + 1);
        SubDesigCategoryMaster testSubDesigCategoryMaster = subDesigCategoryMasters.get(subDesigCategoryMasters.size() - 1);
        assertThat(testSubDesigCategoryMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSubDesigCategoryMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testSubDesigCategoryMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testSubDesigCategoryMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testSubDesigCategoryMaster.getAlias()).isEqualTo(DEFAULT_ALIAS);
        assertThat(testSubDesigCategoryMaster.getOrderBy()).isEqualTo(DEFAULT_ORDER_BY);
    }

    @Test
    @Transactional
    public void getAllSubDesigCategoryMasters() throws Exception {
        // Initialize the database
        subDesigCategoryMasterRepository.saveAndFlush(subDesigCategoryMaster);

        // Get all the subDesigCategoryMasters
        restSubDesigCategoryMasterMockMvc.perform(get("/api/subDesigCategoryMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(subDesigCategoryMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].alias").value(hasItem(DEFAULT_ALIAS.toString())))
                .andExpect(jsonPath("$.[*].orderBy").value(hasItem(DEFAULT_ORDER_BY)));
    }

    @Test
    @Transactional
    public void getSubDesigCategoryMaster() throws Exception {
        // Initialize the database
        subDesigCategoryMasterRepository.saveAndFlush(subDesigCategoryMaster);

        // Get the subDesigCategoryMaster
        restSubDesigCategoryMasterMockMvc.perform(get("/api/subDesigCategoryMasters/{id}", subDesigCategoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(subDesigCategoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.alias").value(DEFAULT_ALIAS.toString()))
            .andExpect(jsonPath("$.orderBy").value(DEFAULT_ORDER_BY));
    }

    @Test
    @Transactional
    public void getNonExistingSubDesigCategoryMaster() throws Exception {
        // Get the subDesigCategoryMaster
        restSubDesigCategoryMasterMockMvc.perform(get("/api/subDesigCategoryMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubDesigCategoryMaster() throws Exception {
        // Initialize the database
        subDesigCategoryMasterRepository.saveAndFlush(subDesigCategoryMaster);

		int databaseSizeBeforeUpdate = subDesigCategoryMasterRepository.findAll().size();

        // Update the subDesigCategoryMaster
        subDesigCategoryMaster.setName(UPDATED_NAME);
        subDesigCategoryMaster.setCreationDate(UPDATED_CREATION_DATE);
        subDesigCategoryMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        subDesigCategoryMaster.setDescription(UPDATED_DESCRIPTION);
        subDesigCategoryMaster.setAlias(UPDATED_ALIAS);
        subDesigCategoryMaster.setOrderBy(UPDATED_ORDER_BY);

        restSubDesigCategoryMasterMockMvc.perform(put("/api/subDesigCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(subDesigCategoryMaster)))
                .andExpect(status().isOk());

        // Validate the SubDesigCategoryMaster in the database
        List<SubDesigCategoryMaster> subDesigCategoryMasters = subDesigCategoryMasterRepository.findAll();
        assertThat(subDesigCategoryMasters).hasSize(databaseSizeBeforeUpdate);
        SubDesigCategoryMaster testSubDesigCategoryMaster = subDesigCategoryMasters.get(subDesigCategoryMasters.size() - 1);
        assertThat(testSubDesigCategoryMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSubDesigCategoryMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testSubDesigCategoryMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testSubDesigCategoryMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testSubDesigCategoryMaster.getAlias()).isEqualTo(UPDATED_ALIAS);
        assertThat(testSubDesigCategoryMaster.getOrderBy()).isEqualTo(UPDATED_ORDER_BY);
    }

    @Test
    @Transactional
    public void deleteSubDesigCategoryMaster() throws Exception {
        // Initialize the database
        subDesigCategoryMasterRepository.saveAndFlush(subDesigCategoryMaster);

		int databaseSizeBeforeDelete = subDesigCategoryMasterRepository.findAll().size();

        // Get the subDesigCategoryMaster
        restSubDesigCategoryMasterMockMvc.perform(delete("/api/subDesigCategoryMasters/{id}", subDesigCategoryMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SubDesigCategoryMaster> subDesigCategoryMasters = subDesigCategoryMasterRepository.findAll();
        assertThat(subDesigCategoryMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DesigCategoryMaster;
import com.callippus.water.erp.repository.DesigCategoryMasterRepository;

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
 * Test class for the DesigCategoryMasterResource REST controller.
 *
 * @see DesigCategoryMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DesigCategoryMasterResourceIntTest {

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
    private DesigCategoryMasterRepository desigCategoryMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDesigCategoryMasterMockMvc;

    private DesigCategoryMaster desigCategoryMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DesigCategoryMasterResource desigCategoryMasterResource = new DesigCategoryMasterResource();
        ReflectionTestUtils.setField(desigCategoryMasterResource, "desigCategoryMasterRepository", desigCategoryMasterRepository);
        this.restDesigCategoryMasterMockMvc = MockMvcBuilders.standaloneSetup(desigCategoryMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        desigCategoryMaster = new DesigCategoryMaster();
        desigCategoryMaster.setName(DEFAULT_NAME);
        desigCategoryMaster.setCreationDate(DEFAULT_CREATION_DATE);
        desigCategoryMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        desigCategoryMaster.setDescription(DEFAULT_DESCRIPTION);
        desigCategoryMaster.setAlias(DEFAULT_ALIAS);
        desigCategoryMaster.setOrderBy(DEFAULT_ORDER_BY);
    }

    @Test
    @Transactional
    public void createDesigCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = desigCategoryMasterRepository.findAll().size();

        // Create the DesigCategoryMaster

        restDesigCategoryMasterMockMvc.perform(post("/api/desigCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(desigCategoryMaster)))
                .andExpect(status().isCreated());

        // Validate the DesigCategoryMaster in the database
        List<DesigCategoryMaster> desigCategoryMasters = desigCategoryMasterRepository.findAll();
        assertThat(desigCategoryMasters).hasSize(databaseSizeBeforeCreate + 1);
        DesigCategoryMaster testDesigCategoryMaster = desigCategoryMasters.get(desigCategoryMasters.size() - 1);
        assertThat(testDesigCategoryMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDesigCategoryMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testDesigCategoryMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testDesigCategoryMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDesigCategoryMaster.getAlias()).isEqualTo(DEFAULT_ALIAS);
        assertThat(testDesigCategoryMaster.getOrderBy()).isEqualTo(DEFAULT_ORDER_BY);
    }

    @Test
    @Transactional
    public void getAllDesigCategoryMasters() throws Exception {
        // Initialize the database
        desigCategoryMasterRepository.saveAndFlush(desigCategoryMaster);

        // Get all the desigCategoryMasters
        restDesigCategoryMasterMockMvc.perform(get("/api/desigCategoryMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(desigCategoryMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].alias").value(hasItem(DEFAULT_ALIAS.toString())))
                .andExpect(jsonPath("$.[*].orderBy").value(hasItem(DEFAULT_ORDER_BY)));
    }

    @Test
    @Transactional
    public void getDesigCategoryMaster() throws Exception {
        // Initialize the database
        desigCategoryMasterRepository.saveAndFlush(desigCategoryMaster);

        // Get the desigCategoryMaster
        restDesigCategoryMasterMockMvc.perform(get("/api/desigCategoryMasters/{id}", desigCategoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(desigCategoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.alias").value(DEFAULT_ALIAS.toString()))
            .andExpect(jsonPath("$.orderBy").value(DEFAULT_ORDER_BY));
    }

    @Test
    @Transactional
    public void getNonExistingDesigCategoryMaster() throws Exception {
        // Get the desigCategoryMaster
        restDesigCategoryMasterMockMvc.perform(get("/api/desigCategoryMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDesigCategoryMaster() throws Exception {
        // Initialize the database
        desigCategoryMasterRepository.saveAndFlush(desigCategoryMaster);

		int databaseSizeBeforeUpdate = desigCategoryMasterRepository.findAll().size();

        // Update the desigCategoryMaster
        desigCategoryMaster.setName(UPDATED_NAME);
        desigCategoryMaster.setCreationDate(UPDATED_CREATION_DATE);
        desigCategoryMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        desigCategoryMaster.setDescription(UPDATED_DESCRIPTION);
        desigCategoryMaster.setAlias(UPDATED_ALIAS);
        desigCategoryMaster.setOrderBy(UPDATED_ORDER_BY);

        restDesigCategoryMasterMockMvc.perform(put("/api/desigCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(desigCategoryMaster)))
                .andExpect(status().isOk());

        // Validate the DesigCategoryMaster in the database
        List<DesigCategoryMaster> desigCategoryMasters = desigCategoryMasterRepository.findAll();
        assertThat(desigCategoryMasters).hasSize(databaseSizeBeforeUpdate);
        DesigCategoryMaster testDesigCategoryMaster = desigCategoryMasters.get(desigCategoryMasters.size() - 1);
        assertThat(testDesigCategoryMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDesigCategoryMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testDesigCategoryMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDesigCategoryMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDesigCategoryMaster.getAlias()).isEqualTo(UPDATED_ALIAS);
        assertThat(testDesigCategoryMaster.getOrderBy()).isEqualTo(UPDATED_ORDER_BY);
    }

    @Test
    @Transactional
    public void deleteDesigCategoryMaster() throws Exception {
        // Initialize the database
        desigCategoryMasterRepository.saveAndFlush(desigCategoryMaster);

		int databaseSizeBeforeDelete = desigCategoryMasterRepository.findAll().size();

        // Get the desigCategoryMaster
        restDesigCategoryMasterMockMvc.perform(delete("/api/desigCategoryMasters/{id}", desigCategoryMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DesigCategoryMaster> desigCategoryMasters = desigCategoryMasterRepository.findAll();
        assertThat(desigCategoryMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.SchemeMaster;
import com.callippus.water.erp.repository.SchemeMasterRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the SchemeMasterResource REST controller.
 *
 * @see SchemeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SchemeMasterResourceIntTest {

    private static final String DEFAULT_SCHEME_NAME = "AAAAA";
    private static final String UPDATED_SCHEME_NAME = "BBBBB";

    @Inject
    private SchemeMasterRepository schemeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSchemeMasterMockMvc;

    private SchemeMaster schemeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SchemeMasterResource schemeMasterResource = new SchemeMasterResource();
        ReflectionTestUtils.setField(schemeMasterResource, "schemeMasterRepository", schemeMasterRepository);
        this.restSchemeMasterMockMvc = MockMvcBuilders.standaloneSetup(schemeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        schemeMaster = new SchemeMaster();
        schemeMaster.setSchemeName(DEFAULT_SCHEME_NAME);
    }

    @Test
    @Transactional
    public void createSchemeMaster() throws Exception {
        int databaseSizeBeforeCreate = schemeMasterRepository.findAll().size();

        // Create the SchemeMaster

        restSchemeMasterMockMvc.perform(post("/api/schemeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(schemeMaster)))
                .andExpect(status().isCreated());

        // Validate the SchemeMaster in the database
        List<SchemeMaster> schemeMasters = schemeMasterRepository.findAll();
        assertThat(schemeMasters).hasSize(databaseSizeBeforeCreate + 1);
        SchemeMaster testSchemeMaster = schemeMasters.get(schemeMasters.size() - 1);
        assertThat(testSchemeMaster.getSchemeName()).isEqualTo(DEFAULT_SCHEME_NAME);
    }

    @Test
    @Transactional
    public void checkSchemeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = schemeMasterRepository.findAll().size();
        // set the field null
        schemeMaster.setSchemeName(null);

        // Create the SchemeMaster, which fails.

        restSchemeMasterMockMvc.perform(post("/api/schemeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(schemeMaster)))
                .andExpect(status().isBadRequest());

        List<SchemeMaster> schemeMasters = schemeMasterRepository.findAll();
        assertThat(schemeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSchemeMasters() throws Exception {
        // Initialize the database
        schemeMasterRepository.saveAndFlush(schemeMaster);

        // Get all the schemeMasters
        restSchemeMasterMockMvc.perform(get("/api/schemeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(schemeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].schemeName").value(hasItem(DEFAULT_SCHEME_NAME.toString())));
    }

    @Test
    @Transactional
    public void getSchemeMaster() throws Exception {
        // Initialize the database
        schemeMasterRepository.saveAndFlush(schemeMaster);

        // Get the schemeMaster
        restSchemeMasterMockMvc.perform(get("/api/schemeMasters/{id}", schemeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(schemeMaster.getId().intValue()))
            .andExpect(jsonPath("$.schemeName").value(DEFAULT_SCHEME_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSchemeMaster() throws Exception {
        // Get the schemeMaster
        restSchemeMasterMockMvc.perform(get("/api/schemeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSchemeMaster() throws Exception {
        // Initialize the database
        schemeMasterRepository.saveAndFlush(schemeMaster);

		int databaseSizeBeforeUpdate = schemeMasterRepository.findAll().size();

        // Update the schemeMaster
        schemeMaster.setSchemeName(UPDATED_SCHEME_NAME);

        restSchemeMasterMockMvc.perform(put("/api/schemeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(schemeMaster)))
                .andExpect(status().isOk());

        // Validate the SchemeMaster in the database
        List<SchemeMaster> schemeMasters = schemeMasterRepository.findAll();
        assertThat(schemeMasters).hasSize(databaseSizeBeforeUpdate);
        SchemeMaster testSchemeMaster = schemeMasters.get(schemeMasters.size() - 1);
        assertThat(testSchemeMaster.getSchemeName()).isEqualTo(UPDATED_SCHEME_NAME);
    }

    @Test
    @Transactional
    public void deleteSchemeMaster() throws Exception {
        // Initialize the database
        schemeMasterRepository.saveAndFlush(schemeMaster);

		int databaseSizeBeforeDelete = schemeMasterRepository.findAll().size();

        // Get the schemeMaster
        restSchemeMasterMockMvc.perform(delete("/api/schemeMasters/{id}", schemeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SchemeMaster> schemeMasters = schemeMasterRepository.findAll();
        assertThat(schemeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

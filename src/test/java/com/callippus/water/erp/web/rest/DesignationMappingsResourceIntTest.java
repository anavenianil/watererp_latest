package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DesignationMappings;
import com.callippus.water.erp.repository.DesignationMappingsRepository;

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
 * Test class for the DesignationMappingsResource REST controller.
 *
 * @see DesignationMappingsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DesignationMappingsResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAA";
    private static final String UPDATED_TYPE = "BBBBB";

    @Inject
    private DesignationMappingsRepository designationMappingsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDesignationMappingsMockMvc;

    private DesignationMappings designationMappings;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DesignationMappingsResource designationMappingsResource = new DesignationMappingsResource();
        ReflectionTestUtils.setField(designationMappingsResource, "designationMappingsRepository", designationMappingsRepository);
        this.restDesignationMappingsMockMvc = MockMvcBuilders.standaloneSetup(designationMappingsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        designationMappings = new DesignationMappings();
        designationMappings.setType(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createDesignationMappings() throws Exception {
        int databaseSizeBeforeCreate = designationMappingsRepository.findAll().size();

        // Create the DesignationMappings

        restDesignationMappingsMockMvc.perform(post("/api/designationMappingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(designationMappings)))
                .andExpect(status().isCreated());

        // Validate the DesignationMappings in the database
        List<DesignationMappings> designationMappingss = designationMappingsRepository.findAll();
        assertThat(designationMappingss).hasSize(databaseSizeBeforeCreate + 1);
        DesignationMappings testDesignationMappings = designationMappingss.get(designationMappingss.size() - 1);
        assertThat(testDesignationMappings.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void getAllDesignationMappingss() throws Exception {
        // Initialize the database
        designationMappingsRepository.saveAndFlush(designationMappings);

        // Get all the designationMappingss
        restDesignationMappingsMockMvc.perform(get("/api/designationMappingss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(designationMappings.getId().intValue())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getDesignationMappings() throws Exception {
        // Initialize the database
        designationMappingsRepository.saveAndFlush(designationMappings);

        // Get the designationMappings
        restDesignationMappingsMockMvc.perform(get("/api/designationMappingss/{id}", designationMappings.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(designationMappings.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDesignationMappings() throws Exception {
        // Get the designationMappings
        restDesignationMappingsMockMvc.perform(get("/api/designationMappingss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDesignationMappings() throws Exception {
        // Initialize the database
        designationMappingsRepository.saveAndFlush(designationMappings);

		int databaseSizeBeforeUpdate = designationMappingsRepository.findAll().size();

        // Update the designationMappings
        designationMappings.setType(UPDATED_TYPE);

        restDesignationMappingsMockMvc.perform(put("/api/designationMappingss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(designationMappings)))
                .andExpect(status().isOk());

        // Validate the DesignationMappings in the database
        List<DesignationMappings> designationMappingss = designationMappingsRepository.findAll();
        assertThat(designationMappingss).hasSize(databaseSizeBeforeUpdate);
        DesignationMappings testDesignationMappings = designationMappingss.get(designationMappingss.size() - 1);
        assertThat(testDesignationMappings.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void deleteDesignationMappings() throws Exception {
        // Initialize the database
        designationMappingsRepository.saveAndFlush(designationMappings);

		int databaseSizeBeforeDelete = designationMappingsRepository.findAll().size();

        // Get the designationMappings
        restDesignationMappingsMockMvc.perform(delete("/api/designationMappingss/{id}", designationMappings.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DesignationMappings> designationMappingss = designationMappingsRepository.findAll();
        assertThat(designationMappingss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

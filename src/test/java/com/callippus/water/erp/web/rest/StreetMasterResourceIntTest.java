package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.StreetMaster;
import com.callippus.water.erp.repository.StreetMasterRepository;

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
 * Test class for the StreetMasterResource REST controller.
 *
 * @see StreetMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StreetMasterResourceIntTest {

    private static final String DEFAULT_STREET_NAME = "AAAAA";
    private static final String UPDATED_STREET_NAME = "BBBBB";
    private static final String DEFAULT_STREET_NO = "AAAAA";
    private static final String UPDATED_STREET_NO = "BBBBB";

    @Inject
    private StreetMasterRepository streetMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restStreetMasterMockMvc;

    private StreetMaster streetMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StreetMasterResource streetMasterResource = new StreetMasterResource();
        ReflectionTestUtils.setField(streetMasterResource, "streetMasterRepository", streetMasterRepository);
        this.restStreetMasterMockMvc = MockMvcBuilders.standaloneSetup(streetMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        streetMaster = new StreetMaster();
        streetMaster.setStreetName(DEFAULT_STREET_NAME);
        streetMaster.setStreetNo(DEFAULT_STREET_NO);
    }

    @Test
    @Transactional
    public void createStreetMaster() throws Exception {
        int databaseSizeBeforeCreate = streetMasterRepository.findAll().size();

        // Create the StreetMaster

        restStreetMasterMockMvc.perform(post("/api/streetMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(streetMaster)))
                .andExpect(status().isCreated());

        // Validate the StreetMaster in the database
        List<StreetMaster> streetMasters = streetMasterRepository.findAll();
        assertThat(streetMasters).hasSize(databaseSizeBeforeCreate + 1);
        StreetMaster testStreetMaster = streetMasters.get(streetMasters.size() - 1);
        assertThat(testStreetMaster.getStreetName()).isEqualTo(DEFAULT_STREET_NAME);
        assertThat(testStreetMaster.getStreetNo()).isEqualTo(DEFAULT_STREET_NO);
    }

    @Test
    @Transactional
    public void getAllStreetMasters() throws Exception {
        // Initialize the database
        streetMasterRepository.saveAndFlush(streetMaster);

        // Get all the streetMasters
        restStreetMasterMockMvc.perform(get("/api/streetMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(streetMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].streetName").value(hasItem(DEFAULT_STREET_NAME.toString())))
                .andExpect(jsonPath("$.[*].streetNo").value(hasItem(DEFAULT_STREET_NO.toString())));
    }

    @Test
    @Transactional
    public void getStreetMaster() throws Exception {
        // Initialize the database
        streetMasterRepository.saveAndFlush(streetMaster);

        // Get the streetMaster
        restStreetMasterMockMvc.perform(get("/api/streetMasters/{id}", streetMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(streetMaster.getId().intValue()))
            .andExpect(jsonPath("$.streetName").value(DEFAULT_STREET_NAME.toString()))
            .andExpect(jsonPath("$.streetNo").value(DEFAULT_STREET_NO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStreetMaster() throws Exception {
        // Get the streetMaster
        restStreetMasterMockMvc.perform(get("/api/streetMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStreetMaster() throws Exception {
        // Initialize the database
        streetMasterRepository.saveAndFlush(streetMaster);

		int databaseSizeBeforeUpdate = streetMasterRepository.findAll().size();

        // Update the streetMaster
        streetMaster.setStreetName(UPDATED_STREET_NAME);
        streetMaster.setStreetNo(UPDATED_STREET_NO);

        restStreetMasterMockMvc.perform(put("/api/streetMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(streetMaster)))
                .andExpect(status().isOk());

        // Validate the StreetMaster in the database
        List<StreetMaster> streetMasters = streetMasterRepository.findAll();
        assertThat(streetMasters).hasSize(databaseSizeBeforeUpdate);
        StreetMaster testStreetMaster = streetMasters.get(streetMasters.size() - 1);
        assertThat(testStreetMaster.getStreetName()).isEqualTo(UPDATED_STREET_NAME);
        assertThat(testStreetMaster.getStreetNo()).isEqualTo(UPDATED_STREET_NO);
    }

    @Test
    @Transactional
    public void deleteStreetMaster() throws Exception {
        // Initialize the database
        streetMasterRepository.saveAndFlush(streetMaster);

		int databaseSizeBeforeDelete = streetMasterRepository.findAll().size();

        // Get the streetMaster
        restStreetMasterMockMvc.perform(delete("/api/streetMasters/{id}", streetMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<StreetMaster> streetMasters = streetMasterRepository.findAll();
        assertThat(streetMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DivisionMaster;
import com.callippus.water.erp.repository.DivisionMasterRepository;

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
 * Test class for the DivisionMasterResource REST controller.
 *
 * @see DivisionMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DivisionMasterResourceIntTest {

    private static final String DEFAULT_DIVISION_NAME = "AAAAA";
    private static final String UPDATED_DIVISION_NAME = "BBBBB";
    private static final String DEFAULT_DIVISION_CODE = "AAAAA";
    private static final String UPDATED_DIVISION_CODE = "BBBBB";

    @Inject
    private DivisionMasterRepository divisionMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDivisionMasterMockMvc;

    private DivisionMaster divisionMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DivisionMasterResource divisionMasterResource = new DivisionMasterResource();
        ReflectionTestUtils.setField(divisionMasterResource, "divisionMasterRepository", divisionMasterRepository);
        this.restDivisionMasterMockMvc = MockMvcBuilders.standaloneSetup(divisionMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        divisionMaster = new DivisionMaster();
        divisionMaster.setDivisionName(DEFAULT_DIVISION_NAME);
        divisionMaster.setDivisionCode(DEFAULT_DIVISION_CODE);
    }

    @Test
    @Transactional
    public void createDivisionMaster() throws Exception {
        int databaseSizeBeforeCreate = divisionMasterRepository.findAll().size();

        // Create the DivisionMaster

        restDivisionMasterMockMvc.perform(post("/api/divisionMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(divisionMaster)))
                .andExpect(status().isCreated());

        // Validate the DivisionMaster in the database
        List<DivisionMaster> divisionMasters = divisionMasterRepository.findAll();
        assertThat(divisionMasters).hasSize(databaseSizeBeforeCreate + 1);
        DivisionMaster testDivisionMaster = divisionMasters.get(divisionMasters.size() - 1);
        assertThat(testDivisionMaster.getDivisionName()).isEqualTo(DEFAULT_DIVISION_NAME);
        assertThat(testDivisionMaster.getDivisionCode()).isEqualTo(DEFAULT_DIVISION_CODE);
    }

    @Test
    @Transactional
    public void getAllDivisionMasters() throws Exception {
        // Initialize the database
        divisionMasterRepository.saveAndFlush(divisionMaster);

        // Get all the divisionMasters
        restDivisionMasterMockMvc.perform(get("/api/divisionMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(divisionMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].divisionName").value(hasItem(DEFAULT_DIVISION_NAME.toString())))
                .andExpect(jsonPath("$.[*].divisionCode").value(hasItem(DEFAULT_DIVISION_CODE.toString())));
    }

    @Test
    @Transactional
    public void getDivisionMaster() throws Exception {
        // Initialize the database
        divisionMasterRepository.saveAndFlush(divisionMaster);

        // Get the divisionMaster
        restDivisionMasterMockMvc.perform(get("/api/divisionMasters/{id}", divisionMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(divisionMaster.getId().intValue()))
            .andExpect(jsonPath("$.divisionName").value(DEFAULT_DIVISION_NAME.toString()))
            .andExpect(jsonPath("$.divisionCode").value(DEFAULT_DIVISION_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDivisionMaster() throws Exception {
        // Get the divisionMaster
        restDivisionMasterMockMvc.perform(get("/api/divisionMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDivisionMaster() throws Exception {
        // Initialize the database
        divisionMasterRepository.saveAndFlush(divisionMaster);

		int databaseSizeBeforeUpdate = divisionMasterRepository.findAll().size();

        // Update the divisionMaster
        divisionMaster.setDivisionName(UPDATED_DIVISION_NAME);
        divisionMaster.setDivisionCode(UPDATED_DIVISION_CODE);

        restDivisionMasterMockMvc.perform(put("/api/divisionMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(divisionMaster)))
                .andExpect(status().isOk());

        // Validate the DivisionMaster in the database
        List<DivisionMaster> divisionMasters = divisionMasterRepository.findAll();
        assertThat(divisionMasters).hasSize(databaseSizeBeforeUpdate);
        DivisionMaster testDivisionMaster = divisionMasters.get(divisionMasters.size() - 1);
        assertThat(testDivisionMaster.getDivisionName()).isEqualTo(UPDATED_DIVISION_NAME);
        assertThat(testDivisionMaster.getDivisionCode()).isEqualTo(UPDATED_DIVISION_CODE);
    }

    @Test
    @Transactional
    public void deleteDivisionMaster() throws Exception {
        // Initialize the database
        divisionMasterRepository.saveAndFlush(divisionMaster);

		int databaseSizeBeforeDelete = divisionMasterRepository.findAll().size();

        // Get the divisionMaster
        restDivisionMasterMockMvc.perform(delete("/api/divisionMasters/{id}", divisionMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DivisionMaster> divisionMasters = divisionMasterRepository.findAll();
        assertThat(divisionMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

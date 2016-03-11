package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.FeasibilityStudy;
import com.callippus.water.erp.repository.FeasibilityStudyRepository;

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
 * Test class for the FeasibilityStudyResource REST controller.
 *
 * @see FeasibilityStudyResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class FeasibilityStudyResourceIntTest {


    private static final Float DEFAULT_PLOT_AREA_IN_SQ_MTRS = 1F;
    private static final Float UPDATED_PLOT_AREA_IN_SQ_MTRS = 2F;

    private static final Float DEFAULT_PLOT_AREA_IN_YARDS = 1F;
    private static final Float UPDATED_PLOT_AREA_IN_YARDS = 2F;

    private static final Integer DEFAULT_NO_OF_FLATS_OR_NO_OF_UNITS = 1;
    private static final Integer UPDATED_NO_OF_FLATS_OR_NO_OF_UNITS = 2;

    private static final Integer DEFAULT_NO_OF_FLOORS = 1;
    private static final Integer UPDATED_NO_OF_FLOORS = 2;

    private static final Float DEFAULT_TOTAL_PLINTH_AREA = 1F;
    private static final Float UPDATED_TOTAL_PLINTH_AREA = 2F;

    private static final Float DEFAULT_WATER_REQUIREMENT = 1F;
    private static final Float UPDATED_WATER_REQUIREMENT = 2F;

    @Inject
    private FeasibilityStudyRepository feasibilityStudyRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restFeasibilityStudyMockMvc;

    private FeasibilityStudy feasibilityStudy;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        FeasibilityStudyResource feasibilityStudyResource = new FeasibilityStudyResource();
        ReflectionTestUtils.setField(feasibilityStudyResource, "feasibilityStudyRepository", feasibilityStudyRepository);
        this.restFeasibilityStudyMockMvc = MockMvcBuilders.standaloneSetup(feasibilityStudyResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        feasibilityStudy = new FeasibilityStudy();
        feasibilityStudy.setPlotAreaInSqMtrs(DEFAULT_PLOT_AREA_IN_SQ_MTRS);
        feasibilityStudy.setPlotAreaInYards(DEFAULT_PLOT_AREA_IN_YARDS);
        feasibilityStudy.setNoOfFlatsOrNoOfUnits(DEFAULT_NO_OF_FLATS_OR_NO_OF_UNITS);
        feasibilityStudy.setNoOfFloors(DEFAULT_NO_OF_FLOORS);
        feasibilityStudy.setTotalPlinthArea(DEFAULT_TOTAL_PLINTH_AREA);
        feasibilityStudy.setWaterRequirement(DEFAULT_WATER_REQUIREMENT);
    }

    @Test
    @Transactional
    public void createFeasibilityStudy() throws Exception {
        int databaseSizeBeforeCreate = feasibilityStudyRepository.findAll().size();

        // Create the FeasibilityStudy

        restFeasibilityStudyMockMvc.perform(post("/api/feasibilityStudys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(feasibilityStudy)))
                .andExpect(status().isCreated());

        // Validate the FeasibilityStudy in the database
        List<FeasibilityStudy> feasibilityStudys = feasibilityStudyRepository.findAll();
        assertThat(feasibilityStudys).hasSize(databaseSizeBeforeCreate + 1);
        FeasibilityStudy testFeasibilityStudy = feasibilityStudys.get(feasibilityStudys.size() - 1);
        assertThat(testFeasibilityStudy.getPlotAreaInSqMtrs()).isEqualTo(DEFAULT_PLOT_AREA_IN_SQ_MTRS);
        assertThat(testFeasibilityStudy.getPlotAreaInYards()).isEqualTo(DEFAULT_PLOT_AREA_IN_YARDS);
        assertThat(testFeasibilityStudy.getNoOfFlatsOrNoOfUnits()).isEqualTo(DEFAULT_NO_OF_FLATS_OR_NO_OF_UNITS);
        assertThat(testFeasibilityStudy.getNoOfFloors()).isEqualTo(DEFAULT_NO_OF_FLOORS);
        assertThat(testFeasibilityStudy.getTotalPlinthArea()).isEqualTo(DEFAULT_TOTAL_PLINTH_AREA);
        assertThat(testFeasibilityStudy.getWaterRequirement()).isEqualTo(DEFAULT_WATER_REQUIREMENT);
    }

    @Test
    @Transactional
    public void getAllFeasibilityStudys() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

        // Get all the feasibilityStudys
        restFeasibilityStudyMockMvc.perform(get("/api/feasibilityStudys?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(feasibilityStudy.getId().intValue())))
                .andExpect(jsonPath("$.[*].plotAreaInSqMtrs").value(hasItem(DEFAULT_PLOT_AREA_IN_SQ_MTRS.doubleValue())))
                .andExpect(jsonPath("$.[*].plotAreaInYards").value(hasItem(DEFAULT_PLOT_AREA_IN_YARDS.doubleValue())))
                .andExpect(jsonPath("$.[*].noOfFlatsOrNoOfUnits").value(hasItem(DEFAULT_NO_OF_FLATS_OR_NO_OF_UNITS)))
                .andExpect(jsonPath("$.[*].noOfFloors").value(hasItem(DEFAULT_NO_OF_FLOORS)))
                .andExpect(jsonPath("$.[*].totalPlinthArea").value(hasItem(DEFAULT_TOTAL_PLINTH_AREA.doubleValue())))
                .andExpect(jsonPath("$.[*].waterRequirement").value(hasItem(DEFAULT_WATER_REQUIREMENT.doubleValue())));
    }

    @Test
    @Transactional
    public void getFeasibilityStudy() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

        // Get the feasibilityStudy
        restFeasibilityStudyMockMvc.perform(get("/api/feasibilityStudys/{id}", feasibilityStudy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(feasibilityStudy.getId().intValue()))
            .andExpect(jsonPath("$.plotAreaInSqMtrs").value(DEFAULT_PLOT_AREA_IN_SQ_MTRS.doubleValue()))
            .andExpect(jsonPath("$.plotAreaInYards").value(DEFAULT_PLOT_AREA_IN_YARDS.doubleValue()))
            .andExpect(jsonPath("$.noOfFlatsOrNoOfUnits").value(DEFAULT_NO_OF_FLATS_OR_NO_OF_UNITS))
            .andExpect(jsonPath("$.noOfFloors").value(DEFAULT_NO_OF_FLOORS))
            .andExpect(jsonPath("$.totalPlinthArea").value(DEFAULT_TOTAL_PLINTH_AREA.doubleValue()))
            .andExpect(jsonPath("$.waterRequirement").value(DEFAULT_WATER_REQUIREMENT.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingFeasibilityStudy() throws Exception {
        // Get the feasibilityStudy
        restFeasibilityStudyMockMvc.perform(get("/api/feasibilityStudys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFeasibilityStudy() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

		int databaseSizeBeforeUpdate = feasibilityStudyRepository.findAll().size();

        // Update the feasibilityStudy
        feasibilityStudy.setPlotAreaInSqMtrs(UPDATED_PLOT_AREA_IN_SQ_MTRS);
        feasibilityStudy.setPlotAreaInYards(UPDATED_PLOT_AREA_IN_YARDS);
        feasibilityStudy.setNoOfFlatsOrNoOfUnits(UPDATED_NO_OF_FLATS_OR_NO_OF_UNITS);
        feasibilityStudy.setNoOfFloors(UPDATED_NO_OF_FLOORS);
        feasibilityStudy.setTotalPlinthArea(UPDATED_TOTAL_PLINTH_AREA);
        feasibilityStudy.setWaterRequirement(UPDATED_WATER_REQUIREMENT);

        restFeasibilityStudyMockMvc.perform(put("/api/feasibilityStudys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(feasibilityStudy)))
                .andExpect(status().isOk());

        // Validate the FeasibilityStudy in the database
        List<FeasibilityStudy> feasibilityStudys = feasibilityStudyRepository.findAll();
        assertThat(feasibilityStudys).hasSize(databaseSizeBeforeUpdate);
        FeasibilityStudy testFeasibilityStudy = feasibilityStudys.get(feasibilityStudys.size() - 1);
        assertThat(testFeasibilityStudy.getPlotAreaInSqMtrs()).isEqualTo(UPDATED_PLOT_AREA_IN_SQ_MTRS);
        assertThat(testFeasibilityStudy.getPlotAreaInYards()).isEqualTo(UPDATED_PLOT_AREA_IN_YARDS);
        assertThat(testFeasibilityStudy.getNoOfFlatsOrNoOfUnits()).isEqualTo(UPDATED_NO_OF_FLATS_OR_NO_OF_UNITS);
        assertThat(testFeasibilityStudy.getNoOfFloors()).isEqualTo(UPDATED_NO_OF_FLOORS);
        assertThat(testFeasibilityStudy.getTotalPlinthArea()).isEqualTo(UPDATED_TOTAL_PLINTH_AREA);
        assertThat(testFeasibilityStudy.getWaterRequirement()).isEqualTo(UPDATED_WATER_REQUIREMENT);
    }

    @Test
    @Transactional
    public void deleteFeasibilityStudy() throws Exception {
        // Initialize the database
        feasibilityStudyRepository.saveAndFlush(feasibilityStudy);

		int databaseSizeBeforeDelete = feasibilityStudyRepository.findAll().size();

        // Get the feasibilityStudy
        restFeasibilityStudyMockMvc.perform(delete("/api/feasibilityStudys/{id}", feasibilityStudy.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<FeasibilityStudy> feasibilityStudys = feasibilityStudyRepository.findAll();
        assertThat(feasibilityStudys).hasSize(databaseSizeBeforeDelete - 1);
    }
}

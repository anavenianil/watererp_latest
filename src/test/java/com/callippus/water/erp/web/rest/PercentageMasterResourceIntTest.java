package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.PercentageMaster;
import com.callippus.water.erp.repository.PercentageMasterRepository;

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
 * Test class for the PercentageMasterResource REST controller.
 *
 * @see PercentageMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PercentageMasterResourceIntTest {

    private static final String DEFAULT_PERCENT_TYPE = "AAAAA";
    private static final String UPDATED_PERCENT_TYPE = "BBBBB";

    private static final Double DEFAULT_PERCENT_VALUE = 1D;
    private static final Double UPDATED_PERCENT_VALUE = 2D;

    @Inject
    private PercentageMasterRepository percentageMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPercentageMasterMockMvc;

    private PercentageMaster percentageMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PercentageMasterResource percentageMasterResource = new PercentageMasterResource();
        ReflectionTestUtils.setField(percentageMasterResource, "percentageMasterRepository", percentageMasterRepository);
        this.restPercentageMasterMockMvc = MockMvcBuilders.standaloneSetup(percentageMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        percentageMaster = new PercentageMaster();
        percentageMaster.setPercentType(DEFAULT_PERCENT_TYPE);
        percentageMaster.setPercentValue(DEFAULT_PERCENT_VALUE);
    }

    @Test
    @Transactional
    public void createPercentageMaster() throws Exception {
        int databaseSizeBeforeCreate = percentageMasterRepository.findAll().size();

        // Create the PercentageMaster

        restPercentageMasterMockMvc.perform(post("/api/percentageMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(percentageMaster)))
                .andExpect(status().isCreated());

        // Validate the PercentageMaster in the database
        List<PercentageMaster> percentageMasters = percentageMasterRepository.findAll();
        assertThat(percentageMasters).hasSize(databaseSizeBeforeCreate + 1);
        PercentageMaster testPercentageMaster = percentageMasters.get(percentageMasters.size() - 1);
        assertThat(testPercentageMaster.getPercentType()).isEqualTo(DEFAULT_PERCENT_TYPE);
        assertThat(testPercentageMaster.getPercentValue()).isEqualTo(DEFAULT_PERCENT_VALUE);
    }

    @Test
    @Transactional
    public void getAllPercentageMasters() throws Exception {
        // Initialize the database
        percentageMasterRepository.saveAndFlush(percentageMaster);

        // Get all the percentageMasters
        restPercentageMasterMockMvc.perform(get("/api/percentageMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(percentageMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].percentType").value(hasItem(DEFAULT_PERCENT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].percentValue").value(hasItem(DEFAULT_PERCENT_VALUE.doubleValue())));
    }

    @Test
    @Transactional
    public void getPercentageMaster() throws Exception {
        // Initialize the database
        percentageMasterRepository.saveAndFlush(percentageMaster);

        // Get the percentageMaster
        restPercentageMasterMockMvc.perform(get("/api/percentageMasters/{id}", percentageMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(percentageMaster.getId().intValue()))
            .andExpect(jsonPath("$.percentType").value(DEFAULT_PERCENT_TYPE.toString()))
            .andExpect(jsonPath("$.percentValue").value(DEFAULT_PERCENT_VALUE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPercentageMaster() throws Exception {
        // Get the percentageMaster
        restPercentageMasterMockMvc.perform(get("/api/percentageMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePercentageMaster() throws Exception {
        // Initialize the database
        percentageMasterRepository.saveAndFlush(percentageMaster);

		int databaseSizeBeforeUpdate = percentageMasterRepository.findAll().size();

        // Update the percentageMaster
        percentageMaster.setPercentType(UPDATED_PERCENT_TYPE);
        percentageMaster.setPercentValue(UPDATED_PERCENT_VALUE);

        restPercentageMasterMockMvc.perform(put("/api/percentageMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(percentageMaster)))
                .andExpect(status().isOk());

        // Validate the PercentageMaster in the database
        List<PercentageMaster> percentageMasters = percentageMasterRepository.findAll();
        assertThat(percentageMasters).hasSize(databaseSizeBeforeUpdate);
        PercentageMaster testPercentageMaster = percentageMasters.get(percentageMasters.size() - 1);
        assertThat(testPercentageMaster.getPercentType()).isEqualTo(UPDATED_PERCENT_TYPE);
        assertThat(testPercentageMaster.getPercentValue()).isEqualTo(UPDATED_PERCENT_VALUE);
    }

    @Test
    @Transactional
    public void deletePercentageMaster() throws Exception {
        // Initialize the database
        percentageMasterRepository.saveAndFlush(percentageMaster);

		int databaseSizeBeforeDelete = percentageMasterRepository.findAll().size();

        // Get the percentageMaster
        restPercentageMasterMockMvc.perform(delete("/api/percentageMasters/{id}", percentageMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PercentageMaster> percentageMasters = percentageMasterRepository.findAll();
        assertThat(percentageMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CustMeterMapping;
import com.callippus.water.erp.repository.CustMeterMappingRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CustMeterMappingResource REST controller.
 *
 * @see CustMeterMappingResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CustMeterMappingResourceIntTest {


    private static final LocalDate DEFAULT_FROM_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FROM_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TO_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TO_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private CustMeterMappingRepository custMeterMappingRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCustMeterMappingMockMvc;

    private CustMeterMapping custMeterMapping;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustMeterMappingResource custMeterMappingResource = new CustMeterMappingResource();
        ReflectionTestUtils.setField(custMeterMappingResource, "custMeterMappingRepository", custMeterMappingRepository);
        this.restCustMeterMappingMockMvc = MockMvcBuilders.standaloneSetup(custMeterMappingResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        custMeterMapping = new CustMeterMapping();
        custMeterMapping.setFromDate(DEFAULT_FROM_DATE);
        custMeterMapping.setToDate(DEFAULT_TO_DATE);
    }

    @Test
    @Transactional
    public void createCustMeterMapping() throws Exception {
        int databaseSizeBeforeCreate = custMeterMappingRepository.findAll().size();

        // Create the CustMeterMapping

        restCustMeterMappingMockMvc.perform(post("/api/custMeterMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custMeterMapping)))
                .andExpect(status().isCreated());

        // Validate the CustMeterMapping in the database
        List<CustMeterMapping> custMeterMappings = custMeterMappingRepository.findAll();
        assertThat(custMeterMappings).hasSize(databaseSizeBeforeCreate + 1);
        CustMeterMapping testCustMeterMapping = custMeterMappings.get(custMeterMappings.size() - 1);
        assertThat(testCustMeterMapping.getFromDate()).isEqualTo(DEFAULT_FROM_DATE);
        assertThat(testCustMeterMapping.getToDate()).isEqualTo(DEFAULT_TO_DATE);
    }

    @Test
    @Transactional
    public void checkFromDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = custMeterMappingRepository.findAll().size();
        // set the field null
        custMeterMapping.setFromDate(null);

        // Create the CustMeterMapping, which fails.

        restCustMeterMappingMockMvc.perform(post("/api/custMeterMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custMeterMapping)))
                .andExpect(status().isBadRequest());

        List<CustMeterMapping> custMeterMappings = custMeterMappingRepository.findAll();
        assertThat(custMeterMappings).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustMeterMappings() throws Exception {
        // Initialize the database
        custMeterMappingRepository.saveAndFlush(custMeterMapping);

        // Get all the custMeterMappings
        restCustMeterMappingMockMvc.perform(get("/api/custMeterMappings?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(custMeterMapping.getId().intValue())))
                .andExpect(jsonPath("$.[*].fromDate").value(hasItem(DEFAULT_FROM_DATE.toString())))
                .andExpect(jsonPath("$.[*].toDate").value(hasItem(DEFAULT_TO_DATE.toString())));
    }

    @Test
    @Transactional
    public void getCustMeterMapping() throws Exception {
        // Initialize the database
        custMeterMappingRepository.saveAndFlush(custMeterMapping);

        // Get the custMeterMapping
        restCustMeterMappingMockMvc.perform(get("/api/custMeterMappings/{id}", custMeterMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(custMeterMapping.getId().intValue()))
            .andExpect(jsonPath("$.fromDate").value(DEFAULT_FROM_DATE.toString()))
            .andExpect(jsonPath("$.toDate").value(DEFAULT_TO_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustMeterMapping() throws Exception {
        // Get the custMeterMapping
        restCustMeterMappingMockMvc.perform(get("/api/custMeterMappings/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustMeterMapping() throws Exception {
        // Initialize the database
        custMeterMappingRepository.saveAndFlush(custMeterMapping);

		int databaseSizeBeforeUpdate = custMeterMappingRepository.findAll().size();

        // Update the custMeterMapping
        custMeterMapping.setFromDate(UPDATED_FROM_DATE);
        custMeterMapping.setToDate(UPDATED_TO_DATE);

        restCustMeterMappingMockMvc.perform(put("/api/custMeterMappings")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(custMeterMapping)))
                .andExpect(status().isOk());

        // Validate the CustMeterMapping in the database
        List<CustMeterMapping> custMeterMappings = custMeterMappingRepository.findAll();
        assertThat(custMeterMappings).hasSize(databaseSizeBeforeUpdate);
        CustMeterMapping testCustMeterMapping = custMeterMappings.get(custMeterMappings.size() - 1);
        assertThat(testCustMeterMapping.getFromDate()).isEqualTo(UPDATED_FROM_DATE);
        assertThat(testCustMeterMapping.getToDate()).isEqualTo(UPDATED_TO_DATE);
    }

    @Test
    @Transactional
    public void deleteCustMeterMapping() throws Exception {
        // Initialize the database
        custMeterMappingRepository.saveAndFlush(custMeterMapping);

		int databaseSizeBeforeDelete = custMeterMappingRepository.findAll().size();

        // Get the custMeterMapping
        restCustMeterMappingMockMvc.perform(delete("/api/custMeterMappings/{id}", custMeterMapping.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CustMeterMapping> custMeterMappings = custMeterMappingRepository.findAll();
        assertThat(custMeterMappings).hasSize(databaseSizeBeforeDelete - 1);
    }
}

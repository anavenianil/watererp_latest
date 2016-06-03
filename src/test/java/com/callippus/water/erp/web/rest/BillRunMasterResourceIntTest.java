package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BillRunMaster;
import com.callippus.water.erp.repository.BillRunMasterRepository;
import com.callippus.water.erp.repository.CustDetailsCustomRepository;
import com.callippus.water.erp.service.BillingService;

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
 * Test class for the BillRunMasterResource REST controller.
 *
 * @see BillRunMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BillRunMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_DATE = ZonedDateTime.now();
    private static final ZonedDateTime UPDATED_DATE = ZonedDateTime.now();
    private static final String DEFAULT_DATE_STR = dateTimeFormatter.format(DEFAULT_DATE);
    private static final String DEFAULT_AREA = "AAAAA";
    private static final String UPDATED_AREA = "BBBBB";

    private static final Integer DEFAULT_SUCCESS = 1;
    private static final Integer UPDATED_SUCCESS = 2;

    private static final Integer DEFAULT_FAILED = 1;
    private static final Integer UPDATED_FAILED = 2;
    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private BillRunMasterRepository billRunMasterRepository;

	@Inject
	private BillingService billingService;
	
    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBillRunMasterMockMvc;

    private BillRunMaster billRunMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BillRunMasterResource billRunMasterResource = new BillRunMasterResource();
        ReflectionTestUtils.setField(billRunMasterResource, "billRunMasterRepository", billRunMasterRepository);
        ReflectionTestUtils.setField(billRunMasterResource, "billingService", billingService);
        this.restBillRunMasterMockMvc = MockMvcBuilders.standaloneSetup(billRunMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        billRunMaster = new BillRunMaster();
        billRunMaster.setDate(DEFAULT_DATE);
        billRunMaster.setArea(DEFAULT_AREA);
        billRunMaster.setSuccess(DEFAULT_SUCCESS);
        billRunMaster.setFailed(DEFAULT_FAILED);
        billRunMaster.setStatus(DEFAULT_STATUS);
    }

    /*
    @Test
    @Transactional
    public void createBillRun() throws Exception {
        int databaseSizeBeforeCreate = billRunMasterRepository.findAll().size();

        // Create the BillRunMaster

        billRunMaster.setArea(null);
        
        restBillRunMasterMockMvc.perform(post("/api/billRunMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billRunMaster)))
                .andExpect(status().isCreated());

    }
    */
    
    @Test
    @Transactional
    public void createBillRunMaster() throws Exception {
        int databaseSizeBeforeCreate = billRunMasterRepository.findAll().size();

        // Create the BillRunMaster

        billRunMaster.setArea(null);
/*
        try
        {
        	custDetailsCustomRepository.loadTestData("/initData.sql");
        	custDetailsCustomRepository.loadTestData("/run1.sql");
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
*/        
        restBillRunMasterMockMvc.perform(post("/api/billRunMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billRunMaster)))
                .andExpect(status().isCreated());

        // Validate the BillRunMaster in the database
        List<BillRunMaster> billRunMasters = billRunMasterRepository.findAll();
        assertThat(billRunMasters).hasSize(databaseSizeBeforeCreate + 1);
        BillRunMaster testBillRunMaster = billRunMasters.get(billRunMasters.size() - 1);
        assertThat(testBillRunMaster.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testBillRunMaster.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testBillRunMaster.getSuccess()).isEqualTo(DEFAULT_SUCCESS);
        assertThat(testBillRunMaster.getFailed()).isEqualTo(DEFAULT_FAILED);
        assertThat(testBillRunMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllBillRunMasters() throws Exception {
        // Initialize the database
        billRunMasterRepository.saveAndFlush(billRunMaster);

        // Get all the billRunMasters
        restBillRunMasterMockMvc.perform(get("/api/billRunMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(billRunMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE_STR)))
                .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
                .andExpect(jsonPath("$.[*].success").value(hasItem(DEFAULT_SUCCESS)))
                .andExpect(jsonPath("$.[*].failed").value(hasItem(DEFAULT_FAILED)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getBillRunMaster() throws Exception {
        // Initialize the database
        billRunMasterRepository.saveAndFlush(billRunMaster);

        // Get the billRunMaster
        restBillRunMasterMockMvc.perform(get("/api/billRunMasters/{id}", billRunMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(billRunMaster.getId().intValue()))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE_STR))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.success").value(DEFAULT_SUCCESS))
            .andExpect(jsonPath("$.failed").value(DEFAULT_FAILED))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBillRunMaster() throws Exception {
        // Get the billRunMaster
        restBillRunMasterMockMvc.perform(get("/api/billRunMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBillRunMaster() throws Exception {
        // Initialize the database
        billRunMasterRepository.saveAndFlush(billRunMaster);

		int databaseSizeBeforeUpdate = billRunMasterRepository.findAll().size();

        // Update the billRunMaster
        billRunMaster.setDate(UPDATED_DATE);
        billRunMaster.setArea(UPDATED_AREA);
        billRunMaster.setSuccess(UPDATED_SUCCESS);
        billRunMaster.setFailed(UPDATED_FAILED);
        billRunMaster.setStatus(UPDATED_STATUS);

        restBillRunMasterMockMvc.perform(put("/api/billRunMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(billRunMaster)))
                .andExpect(status().isOk());

        // Validate the BillRunMaster in the database
        List<BillRunMaster> billRunMasters = billRunMasterRepository.findAll();
        assertThat(billRunMasters).hasSize(databaseSizeBeforeUpdate);
        BillRunMaster testBillRunMaster = billRunMasters.get(billRunMasters.size() - 1);
        assertThat(testBillRunMaster.getDate()).isEqualTo(UPDATED_DATE);
        assertThat(testBillRunMaster.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testBillRunMaster.getSuccess()).isEqualTo(UPDATED_SUCCESS);
        assertThat(testBillRunMaster.getFailed()).isEqualTo(UPDATED_FAILED);
        assertThat(testBillRunMaster.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteBillRunMaster() throws Exception {
        // Initialize the database
        billRunMasterRepository.saveAndFlush(billRunMaster);

		int databaseSizeBeforeDelete = billRunMasterRepository.findAll().size();

        // Get the billRunMaster
        restBillRunMasterMockMvc.perform(delete("/api/billRunMasters/{id}", billRunMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BillRunMaster> billRunMasters = billRunMasterRepository.findAll();
        assertThat(billRunMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

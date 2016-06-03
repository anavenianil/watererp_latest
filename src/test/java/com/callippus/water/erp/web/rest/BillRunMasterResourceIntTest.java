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
import org.springframework.test.annotation.Rollback;
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

    
    @Test
    @Transactional
    @Rollback(false) 
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
                .andExpect(status().isOk());
        
        List<BillRunMaster> billRunMasters = billRunMasterRepository.findAll();
        assertThat(billRunMasters).hasSize(databaseSizeBeforeCreate + 1);
        BillRunMaster testBillRunMaster = billRunMasters.get(billRunMasters.size() - 1);
        
        
        
/*
        // Validate the BillRunMaster in the database
        List<BillRunMaster> billRunMasters = billRunMasterRepository.findAll();
        assertThat(billRunMasters).hasSize(databaseSizeBeforeCreate + 1);
        BillRunMaster testBillRunMaster = billRunMasters.get(billRunMasters.size() - 1);
        assertThat(testBillRunMaster.getDate()).isEqualTo(DEFAULT_DATE);
        assertThat(testBillRunMaster.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testBillRunMaster.getSuccess()).isEqualTo(DEFAULT_SUCCESS);
        assertThat(testBillRunMaster.getFailed()).isEqualTo(DEFAULT_FAILED);
        assertThat(testBillRunMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
*/        
    }

}

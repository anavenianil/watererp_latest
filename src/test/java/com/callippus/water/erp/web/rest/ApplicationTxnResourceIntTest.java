package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ApplicationTxn;
import com.callippus.water.erp.repository.ApplicationTxnRepository;

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
 * Test class for the ApplicationTxnResource REST controller.
 *
 * @see ApplicationTxnResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApplicationTxnResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_S_HOUSE_NO = "AAAAA";
    private static final String UPDATED_S_HOUSE_NO = "BBBBB";
    private static final String DEFAULT_GOVT_OFFICIAL_NO = "AAAAA";
    private static final String UPDATED_GOVT_OFFICIAL_NO = "BBBBB";
    private static final String DEFAULT_WARD = "AAAAA";
    private static final String UPDATED_WARD = "BBBBB";
    private static final String DEFAULT_STREET = "AAAAA";
    private static final String UPDATED_STREET = "BBBBB";
    private static final String DEFAULT_PINCODE = "AAAAA";
    private static final String UPDATED_PINCODE = "BBBBB";
    private static final String DEFAULT_BLOCK = "AAAAA";
    private static final String UPDATED_BLOCK = "BBBBB";
    private static final String DEFAULT_AREA = "AAAAA";
    private static final String UPDATED_AREA = "BBBBB";
    private static final String DEFAULT_SECTION = "AAAAA";
    private static final String UPDATED_SECTION = "BBBBB";
    private static final String DEFAULT_CONSTITUENCY = "AAAAA";
    private static final String UPDATED_CONSTITUENCY = "BBBBB";
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";
    private static final String DEFAULT_TELEPHONE_NUMBER = "AAAAA";
    private static final String UPDATED_TELEPHONE_NUMBER = "BBBBB";
    private static final String DEFAULT_MOBILE = "AAAAA";
    private static final String UPDATED_MOBILE = "BBBBB";

    private static final Float DEFAULT_SCAN_PLAN = 1F;
    private static final Float UPDATED_SCAN_PLAN = 2F;

    private static final Float DEFAULT_SCAN_PLAN1 = 1F;
    private static final Float UPDATED_SCAN_PLAN1 = 2F;

    private static final Float DEFAULT_SALE_DEED = 1F;
    private static final Float UPDATED_SALE_DEED = 2F;

    private static final Float DEFAULT_SALE_DEED1 = 1F;
    private static final Float UPDATED_SALE_DEED1 = 2F;

    private static final Float DEFAULT_TOTAL_PLINTH_AREA = 1F;
    private static final Float UPDATED_TOTAL_PLINTH_AREA = 2F;

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATED_DATE);

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_UPDATED_DATE_STR = dateTimeFormatter.format(DEFAULT_UPDATED_DATE);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final String DEFAULT_FILE_NUMBER = "AAAAA";
    private static final String UPDATED_FILE_NUMBER = "BBBBB";

    @Inject
    private ApplicationTxnRepository applicationTxnRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restApplicationTxnMockMvc;

    private ApplicationTxn applicationTxn;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApplicationTxnResource applicationTxnResource = new ApplicationTxnResource();
        ReflectionTestUtils.setField(applicationTxnResource, "applicationTxnRepository", applicationTxnRepository);
        this.restApplicationTxnMockMvc = MockMvcBuilders.standaloneSetup(applicationTxnResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        applicationTxn = new ApplicationTxn();
        applicationTxn.setsHouseNo(DEFAULT_S_HOUSE_NO);
        applicationTxn.setGovtOfficialNo(DEFAULT_GOVT_OFFICIAL_NO);
        applicationTxn.setWard(DEFAULT_WARD);
        applicationTxn.setStreet(DEFAULT_STREET);
        applicationTxn.setPincode(DEFAULT_PINCODE);
        applicationTxn.setBlock(DEFAULT_BLOCK);
        applicationTxn.setArea(DEFAULT_AREA);
        applicationTxn.setSection(DEFAULT_SECTION);
        applicationTxn.setConstituency(DEFAULT_CONSTITUENCY);
        applicationTxn.setEmail(DEFAULT_EMAIL);
        applicationTxn.setTelephoneNumber(DEFAULT_TELEPHONE_NUMBER);
        applicationTxn.setMobile(DEFAULT_MOBILE);
        applicationTxn.setScanPlan(DEFAULT_SCAN_PLAN);
        applicationTxn.setScanPlan1(DEFAULT_SCAN_PLAN1);
        applicationTxn.setSaleDeed(DEFAULT_SALE_DEED);
        applicationTxn.setSaleDeed1(DEFAULT_SALE_DEED1);
        applicationTxn.setTotalPlinthArea(DEFAULT_TOTAL_PLINTH_AREA);
        applicationTxn.setCreatedDate(DEFAULT_CREATED_DATE);
        applicationTxn.setUpdatedDate(DEFAULT_UPDATED_DATE);
        applicationTxn.setStatus(DEFAULT_STATUS);
        applicationTxn.setFileNumber(DEFAULT_FILE_NUMBER);
    }

    @Test
    @Transactional
    public void createApplicationTxn() throws Exception {
        int databaseSizeBeforeCreate = applicationTxnRepository.findAll().size();

        // Create the ApplicationTxn

        restApplicationTxnMockMvc.perform(post("/api/applicationTxns")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(applicationTxn)))
                .andExpect(status().isCreated());

        // Validate the ApplicationTxn in the database
        List<ApplicationTxn> applicationTxns = applicationTxnRepository.findAll();
        assertThat(applicationTxns).hasSize(databaseSizeBeforeCreate + 1);
        ApplicationTxn testApplicationTxn = applicationTxns.get(applicationTxns.size() - 1);
        assertThat(testApplicationTxn.getsHouseNo()).isEqualTo(DEFAULT_S_HOUSE_NO);
        assertThat(testApplicationTxn.getGovtOfficialNo()).isEqualTo(DEFAULT_GOVT_OFFICIAL_NO);
        assertThat(testApplicationTxn.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testApplicationTxn.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testApplicationTxn.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testApplicationTxn.getBlock()).isEqualTo(DEFAULT_BLOCK);
        assertThat(testApplicationTxn.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testApplicationTxn.getSection()).isEqualTo(DEFAULT_SECTION);
        assertThat(testApplicationTxn.getConstituency()).isEqualTo(DEFAULT_CONSTITUENCY);
        assertThat(testApplicationTxn.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testApplicationTxn.getTelephoneNumber()).isEqualTo(DEFAULT_TELEPHONE_NUMBER);
        assertThat(testApplicationTxn.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testApplicationTxn.getScanPlan()).isEqualTo(DEFAULT_SCAN_PLAN);
        assertThat(testApplicationTxn.getScanPlan1()).isEqualTo(DEFAULT_SCAN_PLAN1);
        assertThat(testApplicationTxn.getSaleDeed()).isEqualTo(DEFAULT_SALE_DEED);
        assertThat(testApplicationTxn.getSaleDeed1()).isEqualTo(DEFAULT_SALE_DEED1);
        assertThat(testApplicationTxn.getTotalPlinthArea()).isEqualTo(DEFAULT_TOTAL_PLINTH_AREA);
        assertThat(testApplicationTxn.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testApplicationTxn.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testApplicationTxn.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testApplicationTxn.getFileNumber()).isEqualTo(DEFAULT_FILE_NUMBER);
    }

    @Test
    @Transactional
    public void getAllApplicationTxns() throws Exception {
        // Initialize the database
        applicationTxnRepository.saveAndFlush(applicationTxn);

        // Get all the applicationTxns
        restApplicationTxnMockMvc.perform(get("/api/applicationTxns?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(applicationTxn.getId().intValue())))
                .andExpect(jsonPath("$.[*].sHouseNo").value(hasItem(DEFAULT_S_HOUSE_NO.toString())))
                .andExpect(jsonPath("$.[*].govtOfficialNo").value(hasItem(DEFAULT_GOVT_OFFICIAL_NO.toString())))
                .andExpect(jsonPath("$.[*].ward").value(hasItem(DEFAULT_WARD.toString())))
                .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
                .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.toString())))
                .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK.toString())))
                .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
                .andExpect(jsonPath("$.[*].section").value(hasItem(DEFAULT_SECTION.toString())))
                .andExpect(jsonPath("$.[*].constituency").value(hasItem(DEFAULT_CONSTITUENCY.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].telephoneNumber").value(hasItem(DEFAULT_TELEPHONE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
                .andExpect(jsonPath("$.[*].scanPlan").value(hasItem(DEFAULT_SCAN_PLAN.doubleValue())))
                .andExpect(jsonPath("$.[*].scanPlan1").value(hasItem(DEFAULT_SCAN_PLAN1.doubleValue())))
                .andExpect(jsonPath("$.[*].saleDeed").value(hasItem(DEFAULT_SALE_DEED.doubleValue())))
                .andExpect(jsonPath("$.[*].saleDeed1").value(hasItem(DEFAULT_SALE_DEED1.doubleValue())))
                .andExpect(jsonPath("$.[*].totalPlinthArea").value(hasItem(DEFAULT_TOTAL_PLINTH_AREA.doubleValue())))
                .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].fileNumber").value(hasItem(DEFAULT_FILE_NUMBER.toString())));
    }

    @Test
    @Transactional
    public void getApplicationTxn() throws Exception {
        // Initialize the database
        applicationTxnRepository.saveAndFlush(applicationTxn);

        // Get the applicationTxn
        restApplicationTxnMockMvc.perform(get("/api/applicationTxns/{id}", applicationTxn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(applicationTxn.getId().intValue()))
            .andExpect(jsonPath("$.sHouseNo").value(DEFAULT_S_HOUSE_NO.toString()))
            .andExpect(jsonPath("$.govtOfficialNo").value(DEFAULT_GOVT_OFFICIAL_NO.toString()))
            .andExpect(jsonPath("$.ward").value(DEFAULT_WARD.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.toString()))
            .andExpect(jsonPath("$.block").value(DEFAULT_BLOCK.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.section").value(DEFAULT_SECTION.toString()))
            .andExpect(jsonPath("$.constituency").value(DEFAULT_CONSTITUENCY.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.telephoneNumber").value(DEFAULT_TELEPHONE_NUMBER.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.scanPlan").value(DEFAULT_SCAN_PLAN.doubleValue()))
            .andExpect(jsonPath("$.scanPlan1").value(DEFAULT_SCAN_PLAN1.doubleValue()))
            .andExpect(jsonPath("$.saleDeed").value(DEFAULT_SALE_DEED.doubleValue()))
            .andExpect(jsonPath("$.saleDeed1").value(DEFAULT_SALE_DEED1.doubleValue()))
            .andExpect(jsonPath("$.totalPlinthArea").value(DEFAULT_TOTAL_PLINTH_AREA.doubleValue()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.fileNumber").value(DEFAULT_FILE_NUMBER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApplicationTxn() throws Exception {
        // Get the applicationTxn
        restApplicationTxnMockMvc.perform(get("/api/applicationTxns/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApplicationTxn() throws Exception {
        // Initialize the database
        applicationTxnRepository.saveAndFlush(applicationTxn);

		int databaseSizeBeforeUpdate = applicationTxnRepository.findAll().size();

        // Update the applicationTxn
        applicationTxn.setsHouseNo(UPDATED_S_HOUSE_NO);
        applicationTxn.setGovtOfficialNo(UPDATED_GOVT_OFFICIAL_NO);
        applicationTxn.setWard(UPDATED_WARD);
        applicationTxn.setStreet(UPDATED_STREET);
        applicationTxn.setPincode(UPDATED_PINCODE);
        applicationTxn.setBlock(UPDATED_BLOCK);
        applicationTxn.setArea(UPDATED_AREA);
        applicationTxn.setSection(UPDATED_SECTION);
        applicationTxn.setConstituency(UPDATED_CONSTITUENCY);
        applicationTxn.setEmail(UPDATED_EMAIL);
        applicationTxn.setTelephoneNumber(UPDATED_TELEPHONE_NUMBER);
        applicationTxn.setMobile(UPDATED_MOBILE);
        applicationTxn.setScanPlan(UPDATED_SCAN_PLAN);
        applicationTxn.setScanPlan1(UPDATED_SCAN_PLAN1);
        applicationTxn.setSaleDeed(UPDATED_SALE_DEED);
        applicationTxn.setSaleDeed1(UPDATED_SALE_DEED1);
        applicationTxn.setTotalPlinthArea(UPDATED_TOTAL_PLINTH_AREA);
        applicationTxn.setCreatedDate(UPDATED_CREATED_DATE);
        applicationTxn.setUpdatedDate(UPDATED_UPDATED_DATE);
        applicationTxn.setStatus(UPDATED_STATUS);
        applicationTxn.setFileNumber(UPDATED_FILE_NUMBER);

        restApplicationTxnMockMvc.perform(put("/api/applicationTxns")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(applicationTxn)))
                .andExpect(status().isOk());

        // Validate the ApplicationTxn in the database
        List<ApplicationTxn> applicationTxns = applicationTxnRepository.findAll();
        assertThat(applicationTxns).hasSize(databaseSizeBeforeUpdate);
        ApplicationTxn testApplicationTxn = applicationTxns.get(applicationTxns.size() - 1);
        assertThat(testApplicationTxn.getsHouseNo()).isEqualTo(UPDATED_S_HOUSE_NO);
        assertThat(testApplicationTxn.getGovtOfficialNo()).isEqualTo(UPDATED_GOVT_OFFICIAL_NO);
        assertThat(testApplicationTxn.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testApplicationTxn.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testApplicationTxn.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testApplicationTxn.getBlock()).isEqualTo(UPDATED_BLOCK);
        assertThat(testApplicationTxn.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testApplicationTxn.getSection()).isEqualTo(UPDATED_SECTION);
        assertThat(testApplicationTxn.getConstituency()).isEqualTo(UPDATED_CONSTITUENCY);
        assertThat(testApplicationTxn.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testApplicationTxn.getTelephoneNumber()).isEqualTo(UPDATED_TELEPHONE_NUMBER);
        assertThat(testApplicationTxn.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testApplicationTxn.getScanPlan()).isEqualTo(UPDATED_SCAN_PLAN);
        assertThat(testApplicationTxn.getScanPlan1()).isEqualTo(UPDATED_SCAN_PLAN1);
        assertThat(testApplicationTxn.getSaleDeed()).isEqualTo(UPDATED_SALE_DEED);
        assertThat(testApplicationTxn.getSaleDeed1()).isEqualTo(UPDATED_SALE_DEED1);
        assertThat(testApplicationTxn.getTotalPlinthArea()).isEqualTo(UPDATED_TOTAL_PLINTH_AREA);
        assertThat(testApplicationTxn.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testApplicationTxn.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testApplicationTxn.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testApplicationTxn.getFileNumber()).isEqualTo(UPDATED_FILE_NUMBER);
    }

    @Test
    @Transactional
    public void deleteApplicationTxn() throws Exception {
        // Initialize the database
        applicationTxnRepository.saveAndFlush(applicationTxn);

		int databaseSizeBeforeDelete = applicationTxnRepository.findAll().size();

        // Get the applicationTxn
        restApplicationTxnMockMvc.perform(delete("/api/applicationTxns/{id}", applicationTxn.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ApplicationTxn> applicationTxns = applicationTxnRepository.findAll();
        assertThat(applicationTxns).hasSize(databaseSizeBeforeDelete - 1);
    }
}

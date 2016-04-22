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
import java.time.LocalDate;
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

    private static final String DEFAULT_FIRST_NAME = "AAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBB";
    private static final String DEFAULT_MIDDLE_NAME = "AAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBB";
    private static final String DEFAULT_LAST_NAME = "AAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBB";

    private static final Boolean DEFAULT_ORGANIZATION = false;
    private static final Boolean UPDATED_ORGANIZATION = true;
    private static final String DEFAULT_ORGANIZATION_NAME = "AAAAA";
    private static final String UPDATED_ORGANIZATION_NAME = "BBBBB";
    private static final String DEFAULT_DESIGNATION = "AAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBB";

    private static final Long DEFAULT_MOBILE_NO = 1L;
    private static final Long UPDATED_MOBILE_NO = 2L;

    private static final Long DEFAULT_OFFICE_NO = 1L;
    private static final Long UPDATED_OFFICE_NO = 2L;
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";
    private static final String DEFAULT_STREET = "AAAAA";
    private static final String UPDATED_STREET = "BBBBB";
    private static final String DEFAULT_PLOT_NO = "AAAAA";
    private static final String UPDATED_PLOT_NO = "BBBBB";
    private static final String DEFAULT_BLOCK_NO = "AAAAA";
    private static final String UPDATED_BLOCK_NO = "BBBBB";
    private static final String DEFAULT_TANESCO_METER = "AAAAA";
    private static final String UPDATED_TANESCO_METER = "BBBBB";
    private static final String DEFAULT_WATER_CONNECTION_USE = "AAAAA";
    private static final String UPDATED_WATER_CONNECTION_USE = "BBBBB";
    private static final String DEFAULT_B_STREET = "AAAAA";
    private static final String UPDATED_B_STREET = "BBBBB";
    private static final String DEFAULT_WARD = "AAAAA";
    private static final String UPDATED_WARD = "BBBBB";
    private static final String DEFAULT_DMA = "AAAAA";
    private static final String UPDATED_DMA = "BBBBB";
    private static final String DEFAULT_B_PLOT_NO = "AAAAA";
    private static final String UPDATED_B_PLOT_NO = "BBBBB";

    private static final Long DEFAULT_REGISTERED_MOBILE = 1L;
    private static final Long UPDATED_REGISTERED_MOBILE = 2L;
    private static final String DEFAULT_ATTACHED_DOC_TYPE = "AAAAA";
    private static final String UPDATED_ATTACHED_DOC_TYPE = "BBBBB";
    private static final String DEFAULT_ID_NUMBER = "AAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBB";
    private static final String DEFAULT_PROPERTY_DOC = "AAAAA";
    private static final String UPDATED_PROPERTY_DOC = "BBBBB";
    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";
    private static final String DEFAULT_PHOTO = "AAAAA";
    private static final String UPDATED_PHOTO = "BBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Float DEFAULT_METER_READING = 1F;
    private static final Float UPDATED_METER_READING = 2F;

    private static final LocalDate DEFAULT_REQUESTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUESTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CONNECTION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CONNECTION_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";
    private static final String DEFAULT_METER_NO = "AAAAA";
    private static final String UPDATED_METER_NO = "BBBBB";

    private static final LocalDate DEFAULT_APPROVED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPROVED_DATE = LocalDate.now(ZoneId.systemDefault());

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
        applicationTxn.setFirstName(DEFAULT_FIRST_NAME);
        applicationTxn.setMiddleName(DEFAULT_MIDDLE_NAME);
        applicationTxn.setLastName(DEFAULT_LAST_NAME);
        applicationTxn.setOrganization(DEFAULT_ORGANIZATION);
        applicationTxn.setOrganizationName(DEFAULT_ORGANIZATION_NAME);
        applicationTxn.setDesignation(DEFAULT_DESIGNATION);
        applicationTxn.setMobileNo(DEFAULT_MOBILE_NO);
        applicationTxn.setOfficeNo(DEFAULT_OFFICE_NO);
        applicationTxn.setEmail(DEFAULT_EMAIL);
        applicationTxn.setStreet(DEFAULT_STREET);
        applicationTxn.setPlotNo(DEFAULT_PLOT_NO);
        applicationTxn.setBlockNo(DEFAULT_BLOCK_NO);
        applicationTxn.setTanescoMeter(DEFAULT_TANESCO_METER);
        applicationTxn.setWaterConnectionUse(DEFAULT_WATER_CONNECTION_USE);
        applicationTxn.setbStreet(DEFAULT_B_STREET);
        applicationTxn.setWard(DEFAULT_WARD);
        applicationTxn.setDma(DEFAULT_DMA);
        applicationTxn.setbPlotNo(DEFAULT_B_PLOT_NO);
        applicationTxn.setRegisteredMobile(DEFAULT_REGISTERED_MOBILE);
        applicationTxn.setAttachedDocType(DEFAULT_ATTACHED_DOC_TYPE);
        applicationTxn.setIdNumber(DEFAULT_ID_NUMBER);
        applicationTxn.setPropertyDoc(DEFAULT_PROPERTY_DOC);
        applicationTxn.setCan(DEFAULT_CAN);
        applicationTxn.setPhoto(DEFAULT_PHOTO);
        applicationTxn.setStatus(DEFAULT_STATUS);
        applicationTxn.setMeterReading(DEFAULT_METER_READING);
        applicationTxn.setRequestedDate(DEFAULT_REQUESTED_DATE);
        applicationTxn.setConnectionDate(DEFAULT_CONNECTION_DATE);
        applicationTxn.setRemarks(DEFAULT_REMARKS);
        applicationTxn.setMeterNo(DEFAULT_METER_NO);
        applicationTxn.setApprovedDate(DEFAULT_APPROVED_DATE);
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
        assertThat(testApplicationTxn.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testApplicationTxn.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testApplicationTxn.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testApplicationTxn.getOrganization()).isEqualTo(DEFAULT_ORGANIZATION);
        assertThat(testApplicationTxn.getOrganizationName()).isEqualTo(DEFAULT_ORGANIZATION_NAME);
        assertThat(testApplicationTxn.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testApplicationTxn.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testApplicationTxn.getOfficeNo()).isEqualTo(DEFAULT_OFFICE_NO);
        assertThat(testApplicationTxn.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testApplicationTxn.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testApplicationTxn.getPlotNo()).isEqualTo(DEFAULT_PLOT_NO);
        assertThat(testApplicationTxn.getBlockNo()).isEqualTo(DEFAULT_BLOCK_NO);
        assertThat(testApplicationTxn.getTanescoMeter()).isEqualTo(DEFAULT_TANESCO_METER);
        assertThat(testApplicationTxn.getWaterConnectionUse()).isEqualTo(DEFAULT_WATER_CONNECTION_USE);
        assertThat(testApplicationTxn.getbStreet()).isEqualTo(DEFAULT_B_STREET);
        assertThat(testApplicationTxn.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testApplicationTxn.getDma()).isEqualTo(DEFAULT_DMA);
        assertThat(testApplicationTxn.getbPlotNo()).isEqualTo(DEFAULT_B_PLOT_NO);
        assertThat(testApplicationTxn.getRegisteredMobile()).isEqualTo(DEFAULT_REGISTERED_MOBILE);
        assertThat(testApplicationTxn.getAttachedDocType()).isEqualTo(DEFAULT_ATTACHED_DOC_TYPE);
        assertThat(testApplicationTxn.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testApplicationTxn.getPropertyDoc()).isEqualTo(DEFAULT_PROPERTY_DOC);
        assertThat(testApplicationTxn.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testApplicationTxn.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testApplicationTxn.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testApplicationTxn.getMeterReading()).isEqualTo(DEFAULT_METER_READING);
        assertThat(testApplicationTxn.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
        assertThat(testApplicationTxn.getConnectionDate()).isEqualTo(DEFAULT_CONNECTION_DATE);
        assertThat(testApplicationTxn.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testApplicationTxn.getMeterNo()).isEqualTo(DEFAULT_METER_NO);
        assertThat(testApplicationTxn.getApprovedDate()).isEqualTo(DEFAULT_APPROVED_DATE);
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
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].organization").value(hasItem(DEFAULT_ORGANIZATION.booleanValue())))
                .andExpect(jsonPath("$.[*].organizationName").value(hasItem(DEFAULT_ORGANIZATION_NAME.toString())))
                .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.intValue())))
                .andExpect(jsonPath("$.[*].officeNo").value(hasItem(DEFAULT_OFFICE_NO.intValue())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
                .andExpect(jsonPath("$.[*].plotNo").value(hasItem(DEFAULT_PLOT_NO.toString())))
                .andExpect(jsonPath("$.[*].blockNo").value(hasItem(DEFAULT_BLOCK_NO.toString())))
                .andExpect(jsonPath("$.[*].tanescoMeter").value(hasItem(DEFAULT_TANESCO_METER.toString())))
                .andExpect(jsonPath("$.[*].waterConnectionUse").value(hasItem(DEFAULT_WATER_CONNECTION_USE.toString())))
                .andExpect(jsonPath("$.[*].bStreet").value(hasItem(DEFAULT_B_STREET.toString())))
                .andExpect(jsonPath("$.[*].ward").value(hasItem(DEFAULT_WARD.toString())))
                .andExpect(jsonPath("$.[*].dma").value(hasItem(DEFAULT_DMA.toString())))
                .andExpect(jsonPath("$.[*].bPlotNo").value(hasItem(DEFAULT_B_PLOT_NO.toString())))
                .andExpect(jsonPath("$.[*].registeredMobile").value(hasItem(DEFAULT_REGISTERED_MOBILE.intValue())))
                .andExpect(jsonPath("$.[*].attachedDocType").value(hasItem(DEFAULT_ATTACHED_DOC_TYPE.toString())))
                .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].propertyDoc").value(hasItem(DEFAULT_PROPERTY_DOC.toString())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].meterReading").value(hasItem(DEFAULT_METER_READING.doubleValue())))
                .andExpect(jsonPath("$.[*].requestedDate").value(hasItem(DEFAULT_REQUESTED_DATE.toString())))
                .andExpect(jsonPath("$.[*].connectionDate").value(hasItem(DEFAULT_CONNECTION_DATE.toString())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].meterNo").value(hasItem(DEFAULT_METER_NO.toString())))
                .andExpect(jsonPath("$.[*].approvedDate").value(hasItem(DEFAULT_APPROVED_DATE.toString())));
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
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.organization").value(DEFAULT_ORGANIZATION.booleanValue()))
            .andExpect(jsonPath("$.organizationName").value(DEFAULT_ORGANIZATION_NAME.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.intValue()))
            .andExpect(jsonPath("$.officeNo").value(DEFAULT_OFFICE_NO.intValue()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.plotNo").value(DEFAULT_PLOT_NO.toString()))
            .andExpect(jsonPath("$.blockNo").value(DEFAULT_BLOCK_NO.toString()))
            .andExpect(jsonPath("$.tanescoMeter").value(DEFAULT_TANESCO_METER.toString()))
            .andExpect(jsonPath("$.waterConnectionUse").value(DEFAULT_WATER_CONNECTION_USE.toString()))
            .andExpect(jsonPath("$.bStreet").value(DEFAULT_B_STREET.toString()))
            .andExpect(jsonPath("$.ward").value(DEFAULT_WARD.toString()))
            .andExpect(jsonPath("$.dma").value(DEFAULT_DMA.toString()))
            .andExpect(jsonPath("$.bPlotNo").value(DEFAULT_B_PLOT_NO.toString()))
            .andExpect(jsonPath("$.registeredMobile").value(DEFAULT_REGISTERED_MOBILE.intValue()))
            .andExpect(jsonPath("$.attachedDocType").value(DEFAULT_ATTACHED_DOC_TYPE.toString()))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.propertyDoc").value(DEFAULT_PROPERTY_DOC.toString()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.meterReading").value(DEFAULT_METER_READING.doubleValue()))
            .andExpect(jsonPath("$.requestedDate").value(DEFAULT_REQUESTED_DATE.toString()))
            .andExpect(jsonPath("$.connectionDate").value(DEFAULT_CONNECTION_DATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.meterNo").value(DEFAULT_METER_NO.toString()))
            .andExpect(jsonPath("$.approvedDate").value(DEFAULT_APPROVED_DATE.toString()));
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
        applicationTxn.setFirstName(UPDATED_FIRST_NAME);
        applicationTxn.setMiddleName(UPDATED_MIDDLE_NAME);
        applicationTxn.setLastName(UPDATED_LAST_NAME);
        applicationTxn.setOrganization(UPDATED_ORGANIZATION);
        applicationTxn.setOrganizationName(UPDATED_ORGANIZATION_NAME);
        applicationTxn.setDesignation(UPDATED_DESIGNATION);
        applicationTxn.setMobileNo(UPDATED_MOBILE_NO);
        applicationTxn.setOfficeNo(UPDATED_OFFICE_NO);
        applicationTxn.setEmail(UPDATED_EMAIL);
        applicationTxn.setStreet(UPDATED_STREET);
        applicationTxn.setPlotNo(UPDATED_PLOT_NO);
        applicationTxn.setBlockNo(UPDATED_BLOCK_NO);
        applicationTxn.setTanescoMeter(UPDATED_TANESCO_METER);
        applicationTxn.setWaterConnectionUse(UPDATED_WATER_CONNECTION_USE);
        applicationTxn.setbStreet(UPDATED_B_STREET);
        applicationTxn.setWard(UPDATED_WARD);
        applicationTxn.setDma(UPDATED_DMA);
        applicationTxn.setbPlotNo(UPDATED_B_PLOT_NO);
        applicationTxn.setRegisteredMobile(UPDATED_REGISTERED_MOBILE);
        applicationTxn.setAttachedDocType(UPDATED_ATTACHED_DOC_TYPE);
        applicationTxn.setIdNumber(UPDATED_ID_NUMBER);
        applicationTxn.setPropertyDoc(UPDATED_PROPERTY_DOC);
        applicationTxn.setCan(UPDATED_CAN);
        applicationTxn.setPhoto(UPDATED_PHOTO);
        applicationTxn.setStatus(UPDATED_STATUS);
        applicationTxn.setMeterReading(UPDATED_METER_READING);
        applicationTxn.setRequestedDate(UPDATED_REQUESTED_DATE);
        applicationTxn.setConnectionDate(UPDATED_CONNECTION_DATE);
        applicationTxn.setRemarks(UPDATED_REMARKS);
        applicationTxn.setMeterNo(UPDATED_METER_NO);
        applicationTxn.setApprovedDate(UPDATED_APPROVED_DATE);

        restApplicationTxnMockMvc.perform(put("/api/applicationTxns")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(applicationTxn)))
                .andExpect(status().isOk());

        // Validate the ApplicationTxn in the database
        List<ApplicationTxn> applicationTxns = applicationTxnRepository.findAll();
        assertThat(applicationTxns).hasSize(databaseSizeBeforeUpdate);
        ApplicationTxn testApplicationTxn = applicationTxns.get(applicationTxns.size() - 1);
        assertThat(testApplicationTxn.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testApplicationTxn.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testApplicationTxn.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testApplicationTxn.getOrganization()).isEqualTo(UPDATED_ORGANIZATION);
        assertThat(testApplicationTxn.getOrganizationName()).isEqualTo(UPDATED_ORGANIZATION_NAME);
        assertThat(testApplicationTxn.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testApplicationTxn.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testApplicationTxn.getOfficeNo()).isEqualTo(UPDATED_OFFICE_NO);
        assertThat(testApplicationTxn.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testApplicationTxn.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testApplicationTxn.getPlotNo()).isEqualTo(UPDATED_PLOT_NO);
        assertThat(testApplicationTxn.getBlockNo()).isEqualTo(UPDATED_BLOCK_NO);
        assertThat(testApplicationTxn.getTanescoMeter()).isEqualTo(UPDATED_TANESCO_METER);
        assertThat(testApplicationTxn.getWaterConnectionUse()).isEqualTo(UPDATED_WATER_CONNECTION_USE);
        assertThat(testApplicationTxn.getbStreet()).isEqualTo(UPDATED_B_STREET);
        assertThat(testApplicationTxn.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testApplicationTxn.getDma()).isEqualTo(UPDATED_DMA);
        assertThat(testApplicationTxn.getbPlotNo()).isEqualTo(UPDATED_B_PLOT_NO);
        assertThat(testApplicationTxn.getRegisteredMobile()).isEqualTo(UPDATED_REGISTERED_MOBILE);
        assertThat(testApplicationTxn.getAttachedDocType()).isEqualTo(UPDATED_ATTACHED_DOC_TYPE);
        assertThat(testApplicationTxn.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testApplicationTxn.getPropertyDoc()).isEqualTo(UPDATED_PROPERTY_DOC);
        assertThat(testApplicationTxn.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testApplicationTxn.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testApplicationTxn.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testApplicationTxn.getMeterReading()).isEqualTo(UPDATED_METER_READING);
        assertThat(testApplicationTxn.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
        assertThat(testApplicationTxn.getConnectionDate()).isEqualTo(UPDATED_CONNECTION_DATE);
        assertThat(testApplicationTxn.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testApplicationTxn.getMeterNo()).isEqualTo(UPDATED_METER_NO);
        assertThat(testApplicationTxn.getApprovedDate()).isEqualTo(UPDATED_APPROVED_DATE);
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

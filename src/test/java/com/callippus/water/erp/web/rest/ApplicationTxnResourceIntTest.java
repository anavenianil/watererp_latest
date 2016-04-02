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

    private static final String DEFAULT_FULL_NAME = "AAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBB";

    private static final Long DEFAULT_HOME_OR_OFFICE_NUMBER = 1L;
    private static final Long UPDATED_HOME_OR_OFFICE_NUMBER = 2L;

    private static final Long DEFAULT_REGIONAL_NUMBER = 1L;
    private static final Long UPDATED_REGIONAL_NUMBER = 2L;

    private static final Long DEFAULT_FAX_NUMBER = 1L;
    private static final Long UPDATED_FAX_NUMBER = 2L;
    private static final String DEFAULT_PLOT_NUMBER = "AAAAA";
    private static final String UPDATED_PLOT_NUMBER = "BBBBB";
    private static final String DEFAULT_AREA = "AAAAA";
    private static final String UPDATED_AREA = "BBBBB";
    private static final String DEFAULT_STREET = "AAAAA";
    private static final String UPDATED_STREET = "BBBBB";
    private static final String DEFAULT_VILLAGE_EXECUTIVE_OFFICE = "AAAAA";
    private static final String UPDATED_VILLAGE_EXECUTIVE_OFFICE = "BBBBB";
    private static final String DEFAULT_VILLAGE_EXECUTIVE_OFFICE_NUMBER = "AAAAA";
    private static final String UPDATED_VILLAGE_EXECUTIVE_OFFICE_NUMBER = "BBBBB";
    private static final String DEFAULT_PO_BOX = "AAAAA";
    private static final String UPDATED_PO_BOX = "BBBBB";

    private static final ZonedDateTime DEFAULT_REQUESTED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_REQUESTED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_REQUESTED_DATE_STR = dateTimeFormatter.format(DEFAULT_REQUESTED_DATE);
    private static final String DEFAULT_PHOTO = "AAAAA";
    private static final String UPDATED_PHOTO = "BBBBB";
    private static final String DEFAULT_FILE_NUMBER = "AAAAA";
    private static final String UPDATED_FILE_NUMBER = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATED_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATED_DATE);

    private static final ZonedDateTime DEFAULT_UPDATED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_UPDATED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_UPDATED_DATE_STR = dateTimeFormatter.format(DEFAULT_UPDATED_DATE);

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;
    private static final String DEFAULT_DETAIL_ADDRESS = "AAAAA";
    private static final String UPDATED_DETAIL_ADDRESS = "BBBBB";

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
        applicationTxn.setFullName(DEFAULT_FULL_NAME);
        applicationTxn.setHomeOrOfficeNumber(DEFAULT_HOME_OR_OFFICE_NUMBER);
        applicationTxn.setRegionalNumber(DEFAULT_REGIONAL_NUMBER);
        applicationTxn.setFaxNumber(DEFAULT_FAX_NUMBER);
        applicationTxn.setPlotNumber(DEFAULT_PLOT_NUMBER);
        applicationTxn.setArea(DEFAULT_AREA);
        applicationTxn.setStreet(DEFAULT_STREET);
        applicationTxn.setVillageExecutiveOffice(DEFAULT_VILLAGE_EXECUTIVE_OFFICE);
        applicationTxn.setVillageExecutiveOfficeNumber(DEFAULT_VILLAGE_EXECUTIVE_OFFICE_NUMBER);
        applicationTxn.setPoBox(DEFAULT_PO_BOX);
        applicationTxn.setRequestedDate(DEFAULT_REQUESTED_DATE);
        applicationTxn.setPhoto(DEFAULT_PHOTO);
        applicationTxn.setFileNumber(DEFAULT_FILE_NUMBER);
        applicationTxn.setCreatedDate(DEFAULT_CREATED_DATE);
        applicationTxn.setUpdatedDate(DEFAULT_UPDATED_DATE);
        applicationTxn.setStatus(DEFAULT_STATUS);
        applicationTxn.setDetailAddress(DEFAULT_DETAIL_ADDRESS);
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
        assertThat(testApplicationTxn.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testApplicationTxn.getHomeOrOfficeNumber()).isEqualTo(DEFAULT_HOME_OR_OFFICE_NUMBER);
        assertThat(testApplicationTxn.getRegionalNumber()).isEqualTo(DEFAULT_REGIONAL_NUMBER);
        assertThat(testApplicationTxn.getFaxNumber()).isEqualTo(DEFAULT_FAX_NUMBER);
        assertThat(testApplicationTxn.getPlotNumber()).isEqualTo(DEFAULT_PLOT_NUMBER);
        assertThat(testApplicationTxn.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testApplicationTxn.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testApplicationTxn.getVillageExecutiveOffice()).isEqualTo(DEFAULT_VILLAGE_EXECUTIVE_OFFICE);
        assertThat(testApplicationTxn.getVillageExecutiveOfficeNumber()).isEqualTo(DEFAULT_VILLAGE_EXECUTIVE_OFFICE_NUMBER);
        assertThat(testApplicationTxn.getPoBox()).isEqualTo(DEFAULT_PO_BOX);
        assertThat(testApplicationTxn.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
        assertThat(testApplicationTxn.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testApplicationTxn.getFileNumber()).isEqualTo(DEFAULT_FILE_NUMBER);
        assertThat(testApplicationTxn.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testApplicationTxn.getUpdatedDate()).isEqualTo(DEFAULT_UPDATED_DATE);
        assertThat(testApplicationTxn.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testApplicationTxn.getDetailAddress()).isEqualTo(DEFAULT_DETAIL_ADDRESS);
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
                .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME.toString())))
                .andExpect(jsonPath("$.[*].homeOrOfficeNumber").value(hasItem(DEFAULT_HOME_OR_OFFICE_NUMBER.intValue())))
                .andExpect(jsonPath("$.[*].regionalNumber").value(hasItem(DEFAULT_REGIONAL_NUMBER.intValue())))
                .andExpect(jsonPath("$.[*].faxNumber").value(hasItem(DEFAULT_FAX_NUMBER.intValue())))
                .andExpect(jsonPath("$.[*].plotNumber").value(hasItem(DEFAULT_PLOT_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
                .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
                .andExpect(jsonPath("$.[*].villageExecutiveOffice").value(hasItem(DEFAULT_VILLAGE_EXECUTIVE_OFFICE.toString())))
                .andExpect(jsonPath("$.[*].villageExecutiveOfficeNumber").value(hasItem(DEFAULT_VILLAGE_EXECUTIVE_OFFICE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].poBox").value(hasItem(DEFAULT_PO_BOX.toString())))
                .andExpect(jsonPath("$.[*].requestedDate").value(hasItem(DEFAULT_REQUESTED_DATE_STR)))
                .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
                .andExpect(jsonPath("$.[*].fileNumber").value(hasItem(DEFAULT_FILE_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].updatedDate").value(hasItem(DEFAULT_UPDATED_DATE_STR)))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].detailAddress").value(hasItem(DEFAULT_DETAIL_ADDRESS.toString())));
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
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME.toString()))
            .andExpect(jsonPath("$.homeOrOfficeNumber").value(DEFAULT_HOME_OR_OFFICE_NUMBER.intValue()))
            .andExpect(jsonPath("$.regionalNumber").value(DEFAULT_REGIONAL_NUMBER.intValue()))
            .andExpect(jsonPath("$.faxNumber").value(DEFAULT_FAX_NUMBER.intValue()))
            .andExpect(jsonPath("$.plotNumber").value(DEFAULT_PLOT_NUMBER.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.villageExecutiveOffice").value(DEFAULT_VILLAGE_EXECUTIVE_OFFICE.toString()))
            .andExpect(jsonPath("$.villageExecutiveOfficeNumber").value(DEFAULT_VILLAGE_EXECUTIVE_OFFICE_NUMBER.toString()))
            .andExpect(jsonPath("$.poBox").value(DEFAULT_PO_BOX.toString()))
            .andExpect(jsonPath("$.requestedDate").value(DEFAULT_REQUESTED_DATE_STR))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.fileNumber").value(DEFAULT_FILE_NUMBER.toString()))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE_STR))
            .andExpect(jsonPath("$.updatedDate").value(DEFAULT_UPDATED_DATE_STR))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.detailAddress").value(DEFAULT_DETAIL_ADDRESS.toString()));
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
        applicationTxn.setFullName(UPDATED_FULL_NAME);
        applicationTxn.setHomeOrOfficeNumber(UPDATED_HOME_OR_OFFICE_NUMBER);
        applicationTxn.setRegionalNumber(UPDATED_REGIONAL_NUMBER);
        applicationTxn.setFaxNumber(UPDATED_FAX_NUMBER);
        applicationTxn.setPlotNumber(UPDATED_PLOT_NUMBER);
        applicationTxn.setArea(UPDATED_AREA);
        applicationTxn.setStreet(UPDATED_STREET);
        applicationTxn.setVillageExecutiveOffice(UPDATED_VILLAGE_EXECUTIVE_OFFICE);
        applicationTxn.setVillageExecutiveOfficeNumber(UPDATED_VILLAGE_EXECUTIVE_OFFICE_NUMBER);
        applicationTxn.setPoBox(UPDATED_PO_BOX);
        applicationTxn.setRequestedDate(UPDATED_REQUESTED_DATE);
        applicationTxn.setPhoto(UPDATED_PHOTO);
        applicationTxn.setFileNumber(UPDATED_FILE_NUMBER);
        applicationTxn.setCreatedDate(UPDATED_CREATED_DATE);
        applicationTxn.setUpdatedDate(UPDATED_UPDATED_DATE);
        applicationTxn.setStatus(UPDATED_STATUS);
        applicationTxn.setDetailAddress(UPDATED_DETAIL_ADDRESS);

        restApplicationTxnMockMvc.perform(put("/api/applicationTxns")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(applicationTxn)))
                .andExpect(status().isOk());

        // Validate the ApplicationTxn in the database
        List<ApplicationTxn> applicationTxns = applicationTxnRepository.findAll();
        assertThat(applicationTxns).hasSize(databaseSizeBeforeUpdate);
        ApplicationTxn testApplicationTxn = applicationTxns.get(applicationTxns.size() - 1);
        assertThat(testApplicationTxn.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testApplicationTxn.getHomeOrOfficeNumber()).isEqualTo(UPDATED_HOME_OR_OFFICE_NUMBER);
        assertThat(testApplicationTxn.getRegionalNumber()).isEqualTo(UPDATED_REGIONAL_NUMBER);
        assertThat(testApplicationTxn.getFaxNumber()).isEqualTo(UPDATED_FAX_NUMBER);
        assertThat(testApplicationTxn.getPlotNumber()).isEqualTo(UPDATED_PLOT_NUMBER);
        assertThat(testApplicationTxn.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testApplicationTxn.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testApplicationTxn.getVillageExecutiveOffice()).isEqualTo(UPDATED_VILLAGE_EXECUTIVE_OFFICE);
        assertThat(testApplicationTxn.getVillageExecutiveOfficeNumber()).isEqualTo(UPDATED_VILLAGE_EXECUTIVE_OFFICE_NUMBER);
        assertThat(testApplicationTxn.getPoBox()).isEqualTo(UPDATED_PO_BOX);
        assertThat(testApplicationTxn.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
        assertThat(testApplicationTxn.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testApplicationTxn.getFileNumber()).isEqualTo(UPDATED_FILE_NUMBER);
        assertThat(testApplicationTxn.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testApplicationTxn.getUpdatedDate()).isEqualTo(UPDATED_UPDATED_DATE);
        assertThat(testApplicationTxn.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testApplicationTxn.getDetailAddress()).isEqualTo(UPDATED_DETAIL_ADDRESS);
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

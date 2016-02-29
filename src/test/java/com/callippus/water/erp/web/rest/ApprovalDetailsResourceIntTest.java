package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ApprovalDetails;
import com.callippus.water.erp.repository.ApprovalDetailsRepository;

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
 * Test class for the ApprovalDetailsResource REST controller.
 *
 * @see ApprovalDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ApprovalDetailsResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";

    private static final ZonedDateTime DEFAULT_APPROVED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_APPROVED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_APPROVED_DATE_STR = dateTimeFormatter.format(DEFAULT_APPROVED_DATE);
    private static final String DEFAULT_APPROVED_EMP_NO = "AAAAA";
    private static final String UPDATED_APPROVED_EMP_NO = "BBBBB";
    private static final String DEFAULT_APPROVED_EMP_NAME = "AAAAA";
    private static final String UPDATED_APPROVED_EMP_NAME = "BBBBB";
    private static final String DEFAULT_APPROVED_EMP_DESIG = "AAAAA";
    private static final String UPDATED_APPROVED_EMP_DESIG = "BBBBB";

    @Inject
    private ApprovalDetailsRepository approvalDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restApprovalDetailsMockMvc;

    private ApprovalDetails approvalDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ApprovalDetailsResource approvalDetailsResource = new ApprovalDetailsResource();
        ReflectionTestUtils.setField(approvalDetailsResource, "approvalDetailsRepository", approvalDetailsRepository);
        this.restApprovalDetailsMockMvc = MockMvcBuilders.standaloneSetup(approvalDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        approvalDetails = new ApprovalDetails();
        approvalDetails.setRemarks(DEFAULT_REMARKS);
        approvalDetails.setApprovedDate(DEFAULT_APPROVED_DATE);
        approvalDetails.setApprovedEmpNo(DEFAULT_APPROVED_EMP_NO);
        approvalDetails.setApprovedEmpName(DEFAULT_APPROVED_EMP_NAME);
        approvalDetails.setApprovedEmpDesig(DEFAULT_APPROVED_EMP_DESIG);
    }

    @Test
    @Transactional
    public void createApprovalDetails() throws Exception {
        int databaseSizeBeforeCreate = approvalDetailsRepository.findAll().size();

        // Create the ApprovalDetails

        restApprovalDetailsMockMvc.perform(post("/api/approvalDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(approvalDetails)))
                .andExpect(status().isCreated());

        // Validate the ApprovalDetails in the database
        List<ApprovalDetails> approvalDetailss = approvalDetailsRepository.findAll();
        assertThat(approvalDetailss).hasSize(databaseSizeBeforeCreate + 1);
        ApprovalDetails testApprovalDetails = approvalDetailss.get(approvalDetailss.size() - 1);
        assertThat(testApprovalDetails.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testApprovalDetails.getApprovedDate()).isEqualTo(DEFAULT_APPROVED_DATE);
        assertThat(testApprovalDetails.getApprovedEmpNo()).isEqualTo(DEFAULT_APPROVED_EMP_NO);
        assertThat(testApprovalDetails.getApprovedEmpName()).isEqualTo(DEFAULT_APPROVED_EMP_NAME);
        assertThat(testApprovalDetails.getApprovedEmpDesig()).isEqualTo(DEFAULT_APPROVED_EMP_DESIG);
    }

    @Test
    @Transactional
    public void getAllApprovalDetailss() throws Exception {
        // Initialize the database
        approvalDetailsRepository.saveAndFlush(approvalDetails);

        // Get all the approvalDetailss
        restApprovalDetailsMockMvc.perform(get("/api/approvalDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(approvalDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].approvedDate").value(hasItem(DEFAULT_APPROVED_DATE_STR)))
                .andExpect(jsonPath("$.[*].approvedEmpNo").value(hasItem(DEFAULT_APPROVED_EMP_NO.toString())))
                .andExpect(jsonPath("$.[*].approvedEmpName").value(hasItem(DEFAULT_APPROVED_EMP_NAME.toString())))
                .andExpect(jsonPath("$.[*].approvedEmpDesig").value(hasItem(DEFAULT_APPROVED_EMP_DESIG.toString())));
    }

    @Test
    @Transactional
    public void getApprovalDetails() throws Exception {
        // Initialize the database
        approvalDetailsRepository.saveAndFlush(approvalDetails);

        // Get the approvalDetails
        restApprovalDetailsMockMvc.perform(get("/api/approvalDetailss/{id}", approvalDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(approvalDetails.getId().intValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.approvedDate").value(DEFAULT_APPROVED_DATE_STR))
            .andExpect(jsonPath("$.approvedEmpNo").value(DEFAULT_APPROVED_EMP_NO.toString()))
            .andExpect(jsonPath("$.approvedEmpName").value(DEFAULT_APPROVED_EMP_NAME.toString()))
            .andExpect(jsonPath("$.approvedEmpDesig").value(DEFAULT_APPROVED_EMP_DESIG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingApprovalDetails() throws Exception {
        // Get the approvalDetails
        restApprovalDetailsMockMvc.perform(get("/api/approvalDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateApprovalDetails() throws Exception {
        // Initialize the database
        approvalDetailsRepository.saveAndFlush(approvalDetails);

		int databaseSizeBeforeUpdate = approvalDetailsRepository.findAll().size();

        // Update the approvalDetails
        approvalDetails.setRemarks(UPDATED_REMARKS);
        approvalDetails.setApprovedDate(UPDATED_APPROVED_DATE);
        approvalDetails.setApprovedEmpNo(UPDATED_APPROVED_EMP_NO);
        approvalDetails.setApprovedEmpName(UPDATED_APPROVED_EMP_NAME);
        approvalDetails.setApprovedEmpDesig(UPDATED_APPROVED_EMP_DESIG);

        restApprovalDetailsMockMvc.perform(put("/api/approvalDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(approvalDetails)))
                .andExpect(status().isOk());

        // Validate the ApprovalDetails in the database
        List<ApprovalDetails> approvalDetailss = approvalDetailsRepository.findAll();
        assertThat(approvalDetailss).hasSize(databaseSizeBeforeUpdate);
        ApprovalDetails testApprovalDetails = approvalDetailss.get(approvalDetailss.size() - 1);
        assertThat(testApprovalDetails.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testApprovalDetails.getApprovedDate()).isEqualTo(UPDATED_APPROVED_DATE);
        assertThat(testApprovalDetails.getApprovedEmpNo()).isEqualTo(UPDATED_APPROVED_EMP_NO);
        assertThat(testApprovalDetails.getApprovedEmpName()).isEqualTo(UPDATED_APPROVED_EMP_NAME);
        assertThat(testApprovalDetails.getApprovedEmpDesig()).isEqualTo(UPDATED_APPROVED_EMP_DESIG);
    }

    @Test
    @Transactional
    public void deleteApprovalDetails() throws Exception {
        // Initialize the database
        approvalDetailsRepository.saveAndFlush(approvalDetails);

		int databaseSizeBeforeDelete = approvalDetailsRepository.findAll().size();

        // Get the approvalDetails
        restApprovalDetailsMockMvc.perform(delete("/api/approvalDetailss/{id}", approvalDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ApprovalDetails> approvalDetailss = approvalDetailsRepository.findAll();
        assertThat(approvalDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

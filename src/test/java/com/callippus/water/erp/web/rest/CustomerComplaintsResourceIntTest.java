package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CustomerComplaints;
import com.callippus.water.erp.repository.CustomerComplaintsRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the CustomerComplaintsResource REST controller.
 *
 * @see CustomerComplaintsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CustomerComplaintsResourceIntTest {

    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";
    private static final String DEFAULT_RELEVANT_DOC = "AAAAA";
    private static final String UPDATED_RELEVANT_DOC = "BBBBB";
    private static final String DEFAULT_COMPLAINT_BY = "AAAAA";
    private static final String UPDATED_COMPLAINT_BY = "BBBBB";

    private static final LocalDate DEFAULT_COMPLAINT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPLAINT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";

    private static final BigDecimal DEFAULT_ADJUSTMENT_AMT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ADJUSTMENT_AMT = new BigDecimal(2);
    private static final String DEFAULT_ADJUSTMENT_BILL_ID = "AAAAA";
    private static final String UPDATED_ADJUSTMENT_BILL_ID = "BBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    @Inject
    private CustomerComplaintsRepository customerComplaintsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCustomerComplaintsMockMvc;

    private CustomerComplaints customerComplaints;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerComplaintsResource customerComplaintsResource = new CustomerComplaintsResource();
        ReflectionTestUtils.setField(customerComplaintsResource, "customerComplaintsRepository", customerComplaintsRepository);
        this.restCustomerComplaintsMockMvc = MockMvcBuilders.standaloneSetup(customerComplaintsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        customerComplaints = new CustomerComplaints();
        customerComplaints.setRemarks(DEFAULT_REMARKS);
        customerComplaints.setRelevantDoc(DEFAULT_RELEVANT_DOC);
        customerComplaints.setComplaintBy(DEFAULT_COMPLAINT_BY);
        customerComplaints.setComplaintDate(DEFAULT_COMPLAINT_DATE);
        customerComplaints.setCan(DEFAULT_CAN);
        customerComplaints.setAdjustmentAmt(DEFAULT_ADJUSTMENT_AMT);
        customerComplaints.setAdjustmentBillId(DEFAULT_ADJUSTMENT_BILL_ID);
        customerComplaints.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createCustomerComplaints() throws Exception {
        int databaseSizeBeforeCreate = customerComplaintsRepository.findAll().size();

        // Create the CustomerComplaints

        restCustomerComplaintsMockMvc.perform(post("/api/customerComplaintss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customerComplaints)))
                .andExpect(status().isCreated());

        // Validate the CustomerComplaints in the database
        List<CustomerComplaints> customerComplaintss = customerComplaintsRepository.findAll();
        assertThat(customerComplaintss).hasSize(databaseSizeBeforeCreate + 1);
        CustomerComplaints testCustomerComplaints = customerComplaintss.get(customerComplaintss.size() - 1);
        assertThat(testCustomerComplaints.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testCustomerComplaints.getRelevantDoc()).isEqualTo(DEFAULT_RELEVANT_DOC);
        assertThat(testCustomerComplaints.getComplaintBy()).isEqualTo(DEFAULT_COMPLAINT_BY);
        assertThat(testCustomerComplaints.getComplaintDate()).isEqualTo(DEFAULT_COMPLAINT_DATE);
        assertThat(testCustomerComplaints.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testCustomerComplaints.getAdjustmentAmt()).isEqualTo(DEFAULT_ADJUSTMENT_AMT);
        assertThat(testCustomerComplaints.getAdjustmentBillId()).isEqualTo(DEFAULT_ADJUSTMENT_BILL_ID);
        assertThat(testCustomerComplaints.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllCustomerComplaintss() throws Exception {
        // Initialize the database
        customerComplaintsRepository.saveAndFlush(customerComplaints);

        // Get all the customerComplaintss
        restCustomerComplaintsMockMvc.perform(get("/api/customerComplaintss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(customerComplaints.getId().intValue())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].relevantDoc").value(hasItem(DEFAULT_RELEVANT_DOC.toString())))
                .andExpect(jsonPath("$.[*].complaintBy").value(hasItem(DEFAULT_COMPLAINT_BY.toString())))
                .andExpect(jsonPath("$.[*].complaintDate").value(hasItem(DEFAULT_COMPLAINT_DATE.toString())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].adjustmentAmt").value(hasItem(DEFAULT_ADJUSTMENT_AMT.intValue())))
                .andExpect(jsonPath("$.[*].adjustmentBillId").value(hasItem(DEFAULT_ADJUSTMENT_BILL_ID.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    public void getCustomerComplaints() throws Exception {
        // Initialize the database
        customerComplaintsRepository.saveAndFlush(customerComplaints);

        // Get the customerComplaints
        restCustomerComplaintsMockMvc.perform(get("/api/customerComplaintss/{id}", customerComplaints.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(customerComplaints.getId().intValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.relevantDoc").value(DEFAULT_RELEVANT_DOC.toString()))
            .andExpect(jsonPath("$.complaintBy").value(DEFAULT_COMPLAINT_BY.toString()))
            .andExpect(jsonPath("$.complaintDate").value(DEFAULT_COMPLAINT_DATE.toString()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.adjustmentAmt").value(DEFAULT_ADJUSTMENT_AMT.intValue()))
            .andExpect(jsonPath("$.adjustmentBillId").value(DEFAULT_ADJUSTMENT_BILL_ID.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerComplaints() throws Exception {
        // Get the customerComplaints
        restCustomerComplaintsMockMvc.perform(get("/api/customerComplaintss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerComplaints() throws Exception {
        // Initialize the database
        customerComplaintsRepository.saveAndFlush(customerComplaints);

		int databaseSizeBeforeUpdate = customerComplaintsRepository.findAll().size();

        // Update the customerComplaints
        customerComplaints.setRemarks(UPDATED_REMARKS);
        customerComplaints.setRelevantDoc(UPDATED_RELEVANT_DOC);
        customerComplaints.setComplaintBy(UPDATED_COMPLAINT_BY);
        customerComplaints.setComplaintDate(UPDATED_COMPLAINT_DATE);
        customerComplaints.setCan(UPDATED_CAN);
        customerComplaints.setAdjustmentAmt(UPDATED_ADJUSTMENT_AMT);
        customerComplaints.setAdjustmentBillId(UPDATED_ADJUSTMENT_BILL_ID);
        customerComplaints.setStatus(UPDATED_STATUS);

        restCustomerComplaintsMockMvc.perform(put("/api/customerComplaintss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customerComplaints)))
                .andExpect(status().isOk());

        // Validate the CustomerComplaints in the database
        List<CustomerComplaints> customerComplaintss = customerComplaintsRepository.findAll();
        assertThat(customerComplaintss).hasSize(databaseSizeBeforeUpdate);
        CustomerComplaints testCustomerComplaints = customerComplaintss.get(customerComplaintss.size() - 1);
        assertThat(testCustomerComplaints.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testCustomerComplaints.getRelevantDoc()).isEqualTo(UPDATED_RELEVANT_DOC);
        assertThat(testCustomerComplaints.getComplaintBy()).isEqualTo(UPDATED_COMPLAINT_BY);
        assertThat(testCustomerComplaints.getComplaintDate()).isEqualTo(UPDATED_COMPLAINT_DATE);
        assertThat(testCustomerComplaints.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testCustomerComplaints.getAdjustmentAmt()).isEqualTo(UPDATED_ADJUSTMENT_AMT);
        assertThat(testCustomerComplaints.getAdjustmentBillId()).isEqualTo(UPDATED_ADJUSTMENT_BILL_ID);
        assertThat(testCustomerComplaints.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteCustomerComplaints() throws Exception {
        // Initialize the database
        customerComplaintsRepository.saveAndFlush(customerComplaints);

		int databaseSizeBeforeDelete = customerComplaintsRepository.findAll().size();

        // Get the customerComplaints
        restCustomerComplaintsMockMvc.perform(delete("/api/customerComplaintss/{id}", customerComplaints.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CustomerComplaints> customerComplaintss = customerComplaintsRepository.findAll();
        assertThat(customerComplaintss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Customer;
import com.callippus.water.erp.repository.CustomerRepository;

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
 * Test class for the CustomerResource REST controller.
 *
 * @see CustomerResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CustomerResourceIntTest {


    private static final Float DEFAULT_PREV_METER_READING = 1F;
    private static final Float UPDATED_PREV_METER_READING = 2F;

    private static final Float DEFAULT_NEW_METER_READING = 1F;
    private static final Float UPDATED_NEW_METER_READING = 2F;

    private static final Boolean DEFAULT_ORGANIZATION = false;
    private static final Boolean UPDATED_ORGANIZATION = true;
    private static final String DEFAULT_ORGANIZATION_NAME = "AAAAA";
    private static final String UPDATED_ORGANIZATION_NAME = "BBBBB";
    private static final String DEFAULT_DESIGNATION = "AAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBB";
    private static final String DEFAULT_DEED_DOC = "AAAAA";
    private static final String UPDATED_DEED_DOC = "BBBBB";
    private static final String DEFAULT_AGREEMENT_DOC = "AAAAA";
    private static final String UPDATED_AGREEMENT_DOC = "BBBBB";
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";

    private static final LocalDate DEFAULT_REQUESTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUESTED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_CAN = "AAAAA";
    private static final String UPDATED_CAN = "BBBBB";
    private static final String DEFAULT_PREVIOUS_NAME = "AAAAA";
    private static final String UPDATED_PREVIOUS_NAME = "BBBBB";

    private static final Long DEFAULT_PREVIOUS_MOBILE = 1L;
    private static final Long UPDATED_PREVIOUS_MOBILE = 2L;
    private static final String DEFAULT_PREVIOUS_EMAIL = "AAAAA";
    private static final String UPDATED_PREVIOUS_EMAIL = "BBBBB";
    private static final String DEFAULT_NEW_EMAIL = "AAAAA";
    private static final String UPDATED_NEW_EMAIL = "BBBBB";
    private static final String DEFAULT_FIRST_NAME = "AAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBB";
    private static final String DEFAULT_MIDDLE_NAME = "AAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBB";
    private static final String DEFAULT_LAST_NAME = "AAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBB";

    private static final Long DEFAULT_MOBILE_NO = 1L;
    private static final Long UPDATED_MOBILE_NO = 2L;
    private static final String DEFAULT_ID_NUMBER = "AAAAA";
    private static final String UPDATED_ID_NUMBER = "BBBBB";
    private static final String DEFAULT_PHOTO = "AAAAA";
    private static final String UPDATED_PHOTO = "BBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final LocalDate DEFAULT_CHANGED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CHANGED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_CHANGE_TYPE = "AAAAA";
    private static final String UPDATED_CHANGE_TYPE = "BBBBB";

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCustomerMockMvc;

    private Customer customer;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CustomerResource customerResource = new CustomerResource();
        ReflectionTestUtils.setField(customerResource, "customerRepository", customerRepository);
        this.restCustomerMockMvc = MockMvcBuilders.standaloneSetup(customerResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        customer = new Customer();
        customer.setPrevMeterReading(DEFAULT_PREV_METER_READING);
        customer.setNewMeterReading(DEFAULT_NEW_METER_READING);
        customer.setOrganization(DEFAULT_ORGANIZATION);
        customer.setOrganizationName(DEFAULT_ORGANIZATION_NAME);
        customer.setDesignation(DEFAULT_DESIGNATION);
        customer.setDeedDoc(DEFAULT_DEED_DOC);
        customer.setAgreementDoc(DEFAULT_AGREEMENT_DOC);
        customer.setRemarks(DEFAULT_REMARKS);
        customer.setRequestedDate(DEFAULT_REQUESTED_DATE);
        customer.setCan(DEFAULT_CAN);
        customer.setPreviousName(DEFAULT_PREVIOUS_NAME);
        customer.setPreviousMobile(DEFAULT_PREVIOUS_MOBILE);
        customer.setPreviousEmail(DEFAULT_PREVIOUS_EMAIL);
        customer.setNewEmail(DEFAULT_NEW_EMAIL);
        customer.setFirstName(DEFAULT_FIRST_NAME);
        customer.setMiddleName(DEFAULT_MIDDLE_NAME);
        customer.setLastName(DEFAULT_LAST_NAME);
        customer.setMobileNo(DEFAULT_MOBILE_NO);
        customer.setIdNumber(DEFAULT_ID_NUMBER);
        customer.setPhoto(DEFAULT_PHOTO);
        customer.setStatus(DEFAULT_STATUS);
        customer.setChangedDate(DEFAULT_CHANGED_DATE);
        customer.setChangeType(DEFAULT_CHANGE_TYPE);
    }

    @Test
    @Transactional
    public void createCustomer() throws Exception {
        int databaseSizeBeforeCreate = customerRepository.findAll().size();

        // Create the Customer

        restCustomerMockMvc.perform(post("/api/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(status().isCreated());

        // Validate the Customer in the database
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeCreate + 1);
        Customer testCustomer = customers.get(customers.size() - 1);
        assertThat(testCustomer.getPrevMeterReading()).isEqualTo(DEFAULT_PREV_METER_READING);
        assertThat(testCustomer.getNewMeterReading()).isEqualTo(DEFAULT_NEW_METER_READING);
        assertThat(testCustomer.getOrganization()).isEqualTo(DEFAULT_ORGANIZATION);
        assertThat(testCustomer.getOrganizationName()).isEqualTo(DEFAULT_ORGANIZATION_NAME);
        assertThat(testCustomer.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
        assertThat(testCustomer.getDeedDoc()).isEqualTo(DEFAULT_DEED_DOC);
        assertThat(testCustomer.getAgreementDoc()).isEqualTo(DEFAULT_AGREEMENT_DOC);
        assertThat(testCustomer.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testCustomer.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
        assertThat(testCustomer.getCan()).isEqualTo(DEFAULT_CAN);
        assertThat(testCustomer.getPreviousName()).isEqualTo(DEFAULT_PREVIOUS_NAME);
        assertThat(testCustomer.getPreviousMobile()).isEqualTo(DEFAULT_PREVIOUS_MOBILE);
        assertThat(testCustomer.getPreviousEmail()).isEqualTo(DEFAULT_PREVIOUS_EMAIL);
        assertThat(testCustomer.getNewEmail()).isEqualTo(DEFAULT_NEW_EMAIL);
        assertThat(testCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCustomer.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCustomer.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testCustomer.getIdNumber()).isEqualTo(DEFAULT_ID_NUMBER);
        assertThat(testCustomer.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testCustomer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCustomer.getChangedDate()).isEqualTo(DEFAULT_CHANGED_DATE);
        assertThat(testCustomer.getChangeType()).isEqualTo(DEFAULT_CHANGE_TYPE);
    }

    @Test
    @Transactional
    public void getAllCustomers() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get all the customers
        restCustomerMockMvc.perform(get("/api/customers?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
                .andExpect(jsonPath("$.[*].prevMeterReading").value(hasItem(DEFAULT_PREV_METER_READING.doubleValue())))
                .andExpect(jsonPath("$.[*].newMeterReading").value(hasItem(DEFAULT_NEW_METER_READING.doubleValue())))
                .andExpect(jsonPath("$.[*].organization").value(hasItem(DEFAULT_ORGANIZATION.booleanValue())))
                .andExpect(jsonPath("$.[*].organizationName").value(hasItem(DEFAULT_ORGANIZATION_NAME.toString())))
                .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION.toString())))
                .andExpect(jsonPath("$.[*].deedDoc").value(hasItem(DEFAULT_DEED_DOC.toString())))
                .andExpect(jsonPath("$.[*].agreementDoc").value(hasItem(DEFAULT_AGREEMENT_DOC.toString())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
                .andExpect(jsonPath("$.[*].requestedDate").value(hasItem(DEFAULT_REQUESTED_DATE.toString())))
                .andExpect(jsonPath("$.[*].can").value(hasItem(DEFAULT_CAN.toString())))
                .andExpect(jsonPath("$.[*].previousName").value(hasItem(DEFAULT_PREVIOUS_NAME.toString())))
                .andExpect(jsonPath("$.[*].previousMobile").value(hasItem(DEFAULT_PREVIOUS_MOBILE.intValue())))
                .andExpect(jsonPath("$.[*].previousEmail").value(hasItem(DEFAULT_PREVIOUS_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].newEmail").value(hasItem(DEFAULT_NEW_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.intValue())))
                .andExpect(jsonPath("$.[*].idNumber").value(hasItem(DEFAULT_ID_NUMBER.toString())))
                .andExpect(jsonPath("$.[*].photo").value(hasItem(DEFAULT_PHOTO.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].changedDate").value(hasItem(DEFAULT_CHANGED_DATE.toString())))
                .andExpect(jsonPath("$.[*].changeType").value(hasItem(DEFAULT_CHANGE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.prevMeterReading").value(DEFAULT_PREV_METER_READING.doubleValue()))
            .andExpect(jsonPath("$.newMeterReading").value(DEFAULT_NEW_METER_READING.doubleValue()))
            .andExpect(jsonPath("$.organization").value(DEFAULT_ORGANIZATION.booleanValue()))
            .andExpect(jsonPath("$.organizationName").value(DEFAULT_ORGANIZATION_NAME.toString()))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION.toString()))
            .andExpect(jsonPath("$.deedDoc").value(DEFAULT_DEED_DOC.toString()))
            .andExpect(jsonPath("$.agreementDoc").value(DEFAULT_AGREEMENT_DOC.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.requestedDate").value(DEFAULT_REQUESTED_DATE.toString()))
            .andExpect(jsonPath("$.can").value(DEFAULT_CAN.toString()))
            .andExpect(jsonPath("$.previousName").value(DEFAULT_PREVIOUS_NAME.toString()))
            .andExpect(jsonPath("$.previousMobile").value(DEFAULT_PREVIOUS_MOBILE.intValue()))
            .andExpect(jsonPath("$.previousEmail").value(DEFAULT_PREVIOUS_EMAIL.toString()))
            .andExpect(jsonPath("$.newEmail").value(DEFAULT_NEW_EMAIL.toString()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.intValue()))
            .andExpect(jsonPath("$.idNumber").value(DEFAULT_ID_NUMBER.toString()))
            .andExpect(jsonPath("$.photo").value(DEFAULT_PHOTO.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.changedDate").value(DEFAULT_CHANGED_DATE.toString()))
            .andExpect(jsonPath("$.changeType").value(DEFAULT_CHANGE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get("/api/customers/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

		int databaseSizeBeforeUpdate = customerRepository.findAll().size();

        // Update the customer
        customer.setPrevMeterReading(UPDATED_PREV_METER_READING);
        customer.setNewMeterReading(UPDATED_NEW_METER_READING);
        customer.setOrganization(UPDATED_ORGANIZATION);
        customer.setOrganizationName(UPDATED_ORGANIZATION_NAME);
        customer.setDesignation(UPDATED_DESIGNATION);
        customer.setDeedDoc(UPDATED_DEED_DOC);
        customer.setAgreementDoc(UPDATED_AGREEMENT_DOC);
        customer.setRemarks(UPDATED_REMARKS);
        customer.setRequestedDate(UPDATED_REQUESTED_DATE);
        customer.setCan(UPDATED_CAN);
        customer.setPreviousName(UPDATED_PREVIOUS_NAME);
        customer.setPreviousMobile(UPDATED_PREVIOUS_MOBILE);
        customer.setPreviousEmail(UPDATED_PREVIOUS_EMAIL);
        customer.setNewEmail(UPDATED_NEW_EMAIL);
        customer.setFirstName(UPDATED_FIRST_NAME);
        customer.setMiddleName(UPDATED_MIDDLE_NAME);
        customer.setLastName(UPDATED_LAST_NAME);
        customer.setMobileNo(UPDATED_MOBILE_NO);
        customer.setIdNumber(UPDATED_ID_NUMBER);
        customer.setPhoto(UPDATED_PHOTO);
        customer.setStatus(UPDATED_STATUS);
        customer.setChangedDate(UPDATED_CHANGED_DATE);
        customer.setChangeType(UPDATED_CHANGE_TYPE);

        restCustomerMockMvc.perform(put("/api/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customers.get(customers.size() - 1);
        assertThat(testCustomer.getPrevMeterReading()).isEqualTo(UPDATED_PREV_METER_READING);
        assertThat(testCustomer.getNewMeterReading()).isEqualTo(UPDATED_NEW_METER_READING);
        assertThat(testCustomer.getOrganization()).isEqualTo(UPDATED_ORGANIZATION);
        assertThat(testCustomer.getOrganizationName()).isEqualTo(UPDATED_ORGANIZATION_NAME);
        assertThat(testCustomer.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
        assertThat(testCustomer.getDeedDoc()).isEqualTo(UPDATED_DEED_DOC);
        assertThat(testCustomer.getAgreementDoc()).isEqualTo(UPDATED_AGREEMENT_DOC);
        assertThat(testCustomer.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testCustomer.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
        assertThat(testCustomer.getCan()).isEqualTo(UPDATED_CAN);
        assertThat(testCustomer.getPreviousName()).isEqualTo(UPDATED_PREVIOUS_NAME);
        assertThat(testCustomer.getPreviousMobile()).isEqualTo(UPDATED_PREVIOUS_MOBILE);
        assertThat(testCustomer.getPreviousEmail()).isEqualTo(UPDATED_PREVIOUS_EMAIL);
        assertThat(testCustomer.getNewEmail()).isEqualTo(UPDATED_NEW_EMAIL);
        assertThat(testCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCustomer.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCustomer.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testCustomer.getIdNumber()).isEqualTo(UPDATED_ID_NUMBER);
        assertThat(testCustomer.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testCustomer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCustomer.getChangedDate()).isEqualTo(UPDATED_CHANGED_DATE);
        assertThat(testCustomer.getChangeType()).isEqualTo(UPDATED_CHANGE_TYPE);
    }

    @Test
    @Transactional
    public void deleteCustomer() throws Exception {
        // Initialize the database
        customerRepository.saveAndFlush(customer);

		int databaseSizeBeforeDelete = customerRepository.findAll().size();

        // Get the customer
        restCustomerMockMvc.perform(delete("/api/customers/{id}", customer.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeDelete - 1);
    }
}

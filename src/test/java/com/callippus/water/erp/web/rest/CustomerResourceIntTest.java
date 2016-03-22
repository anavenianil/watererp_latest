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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_REQUEST_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_REQUEST_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_REQUEST_DATE_STR = dateTimeFormatter.format(DEFAULT_REQUEST_DATE);
    private static final String DEFAULT_FIRST_NAME = "AAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBB";
    private static final String DEFAULT_MIDDLE_NAME = "AAAAA";
    private static final String UPDATED_MIDDLE_NAME = "BBBBB";
    private static final String DEFAULT_LAST_NAME = "AAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBB";
    private static final String DEFAULT_HOUSE_NO = "AAAAA";
    private static final String UPDATED_HOUSE_NO = "BBBBB";
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
    private static final String DEFAULT_TEL_OFFICE = "AAAAA";
    private static final String UPDATED_TEL_OFFICE = "BBBBB";
    private static final String DEFAULT_TEL_HOME = "AAAAA";
    private static final String UPDATED_TEL_HOME = "BBBBB";
    private static final String DEFAULT_MOBILE = "AAAAA";
    private static final String UPDATED_MOBILE = "BBBBB";
    private static final String DEFAULT_FILE_NUMBER = "AAAAA";
    private static final String UPDATED_FILE_NUMBER = "BBBBB";

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
        customer.setRequestDate(DEFAULT_REQUEST_DATE);
        customer.setFirstName(DEFAULT_FIRST_NAME);
        customer.setMiddleName(DEFAULT_MIDDLE_NAME);
        customer.setLastName(DEFAULT_LAST_NAME);
        customer.setHouseNo(DEFAULT_HOUSE_NO);
        customer.setGovtOfficialNo(DEFAULT_GOVT_OFFICIAL_NO);
        customer.setWard(DEFAULT_WARD);
        customer.setStreet(DEFAULT_STREET);
        customer.setPincode(DEFAULT_PINCODE);
        customer.setBlock(DEFAULT_BLOCK);
        customer.setArea(DEFAULT_AREA);
        customer.setSection(DEFAULT_SECTION);
        customer.setConstituency(DEFAULT_CONSTITUENCY);
        customer.setEmail(DEFAULT_EMAIL);
        customer.setTelOffice(DEFAULT_TEL_OFFICE);
        customer.setTelHome(DEFAULT_TEL_HOME);
        customer.setMobile(DEFAULT_MOBILE);
        customer.setFileNumber(DEFAULT_FILE_NUMBER);
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
        assertThat(testCustomer.getRequestDate()).isEqualTo(DEFAULT_REQUEST_DATE);
        assertThat(testCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testCustomer.getMiddleName()).isEqualTo(DEFAULT_MIDDLE_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testCustomer.getHouseNo()).isEqualTo(DEFAULT_HOUSE_NO);
        assertThat(testCustomer.getGovtOfficialNo()).isEqualTo(DEFAULT_GOVT_OFFICIAL_NO);
        assertThat(testCustomer.getWard()).isEqualTo(DEFAULT_WARD);
        assertThat(testCustomer.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testCustomer.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testCustomer.getBlock()).isEqualTo(DEFAULT_BLOCK);
        assertThat(testCustomer.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testCustomer.getSection()).isEqualTo(DEFAULT_SECTION);
        assertThat(testCustomer.getConstituency()).isEqualTo(DEFAULT_CONSTITUENCY);
        assertThat(testCustomer.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testCustomer.getTelOffice()).isEqualTo(DEFAULT_TEL_OFFICE);
        assertThat(testCustomer.getTelHome()).isEqualTo(DEFAULT_TEL_HOME);
        assertThat(testCustomer.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testCustomer.getFileNumber()).isEqualTo(DEFAULT_FILE_NUMBER);
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
                .andExpect(jsonPath("$.[*].requestDate").value(hasItem(DEFAULT_REQUEST_DATE_STR)))
                .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME.toString())))
                .andExpect(jsonPath("$.[*].middleName").value(hasItem(DEFAULT_MIDDLE_NAME.toString())))
                .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME.toString())))
                .andExpect(jsonPath("$.[*].houseNo").value(hasItem(DEFAULT_HOUSE_NO.toString())))
                .andExpect(jsonPath("$.[*].govtOfficialNo").value(hasItem(DEFAULT_GOVT_OFFICIAL_NO.toString())))
                .andExpect(jsonPath("$.[*].ward").value(hasItem(DEFAULT_WARD.toString())))
                .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
                .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.toString())))
                .andExpect(jsonPath("$.[*].block").value(hasItem(DEFAULT_BLOCK.toString())))
                .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA.toString())))
                .andExpect(jsonPath("$.[*].section").value(hasItem(DEFAULT_SECTION.toString())))
                .andExpect(jsonPath("$.[*].constituency").value(hasItem(DEFAULT_CONSTITUENCY.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].telOffice").value(hasItem(DEFAULT_TEL_OFFICE.toString())))
                .andExpect(jsonPath("$.[*].telHome").value(hasItem(DEFAULT_TEL_HOME.toString())))
                .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE.toString())))
                .andExpect(jsonPath("$.[*].fileNumber").value(hasItem(DEFAULT_FILE_NUMBER.toString())));
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
            .andExpect(jsonPath("$.requestDate").value(DEFAULT_REQUEST_DATE_STR))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME.toString()))
            .andExpect(jsonPath("$.middleName").value(DEFAULT_MIDDLE_NAME.toString()))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME.toString()))
            .andExpect(jsonPath("$.houseNo").value(DEFAULT_HOUSE_NO.toString()))
            .andExpect(jsonPath("$.govtOfficialNo").value(DEFAULT_GOVT_OFFICIAL_NO.toString()))
            .andExpect(jsonPath("$.ward").value(DEFAULT_WARD.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.toString()))
            .andExpect(jsonPath("$.block").value(DEFAULT_BLOCK.toString()))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA.toString()))
            .andExpect(jsonPath("$.section").value(DEFAULT_SECTION.toString()))
            .andExpect(jsonPath("$.constituency").value(DEFAULT_CONSTITUENCY.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.telOffice").value(DEFAULT_TEL_OFFICE.toString()))
            .andExpect(jsonPath("$.telHome").value(DEFAULT_TEL_HOME.toString()))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE.toString()))
            .andExpect(jsonPath("$.fileNumber").value(DEFAULT_FILE_NUMBER.toString()));
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
        customer.setRequestDate(UPDATED_REQUEST_DATE);
        customer.setFirstName(UPDATED_FIRST_NAME);
        customer.setMiddleName(UPDATED_MIDDLE_NAME);
        customer.setLastName(UPDATED_LAST_NAME);
        customer.setHouseNo(UPDATED_HOUSE_NO);
        customer.setGovtOfficialNo(UPDATED_GOVT_OFFICIAL_NO);
        customer.setWard(UPDATED_WARD);
        customer.setStreet(UPDATED_STREET);
        customer.setPincode(UPDATED_PINCODE);
        customer.setBlock(UPDATED_BLOCK);
        customer.setArea(UPDATED_AREA);
        customer.setSection(UPDATED_SECTION);
        customer.setConstituency(UPDATED_CONSTITUENCY);
        customer.setEmail(UPDATED_EMAIL);
        customer.setTelOffice(UPDATED_TEL_OFFICE);
        customer.setTelHome(UPDATED_TEL_HOME);
        customer.setMobile(UPDATED_MOBILE);
        customer.setFileNumber(UPDATED_FILE_NUMBER);

        restCustomerMockMvc.perform(put("/api/customers")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(customer)))
                .andExpect(status().isOk());

        // Validate the Customer in the database
        List<Customer> customers = customerRepository.findAll();
        assertThat(customers).hasSize(databaseSizeBeforeUpdate);
        Customer testCustomer = customers.get(customers.size() - 1);
        assertThat(testCustomer.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testCustomer.getMiddleName()).isEqualTo(UPDATED_MIDDLE_NAME);
        assertThat(testCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testCustomer.getHouseNo()).isEqualTo(UPDATED_HOUSE_NO);
        assertThat(testCustomer.getGovtOfficialNo()).isEqualTo(UPDATED_GOVT_OFFICIAL_NO);
        assertThat(testCustomer.getWard()).isEqualTo(UPDATED_WARD);
        assertThat(testCustomer.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testCustomer.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testCustomer.getBlock()).isEqualTo(UPDATED_BLOCK);
        assertThat(testCustomer.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testCustomer.getSection()).isEqualTo(UPDATED_SECTION);
        assertThat(testCustomer.getConstituency()).isEqualTo(UPDATED_CONSTITUENCY);
        assertThat(testCustomer.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testCustomer.getTelOffice()).isEqualTo(UPDATED_TEL_OFFICE);
        assertThat(testCustomer.getTelHome()).isEqualTo(UPDATED_TEL_HOME);
        assertThat(testCustomer.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testCustomer.getFileNumber()).isEqualTo(UPDATED_FILE_NUMBER);
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

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.SewerageRequest;
import com.callippus.water.erp.repository.SewerageRequestRepository;

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
 * Test class for the SewerageRequestResource REST controller.
 *
 * @see SewerageRequestResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SewerageRequestResourceIntTest {

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBB";
    private static final String DEFAULT_MOBILE_NO = "AAAAA";
    private static final String UPDATED_MOBILE_NO = "BBBBB";

    private static final LocalDate DEFAULT_REQUESTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUESTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_RECEIPT_NO = 1L;
    private static final Long UPDATED_RECEIPT_NO = 2L;
    private static final String DEFAULT_ADDRESS = "AAAAA";
    private static final String UPDATED_ADDRESS = "BBBBB";

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    private static final LocalDate DEFAULT_PAYMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PAYMENT_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_VEHICLE_NO = "AAAAA";
    private static final String UPDATED_VEHICLE_NO = "BBBBB";
    private static final String DEFAULT_DRIVER = "AAAAA";
    private static final String UPDATED_DRIVER = "BBBBB";

    private static final LocalDate DEFAULT_COMPLETION_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMPLETION_DATE = LocalDate.now(ZoneId.systemDefault());

    @Inject
    private SewerageRequestRepository sewerageRequestRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSewerageRequestMockMvc;

    private SewerageRequest sewerageRequest;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SewerageRequestResource sewerageRequestResource = new SewerageRequestResource();
        ReflectionTestUtils.setField(sewerageRequestResource, "sewerageRequestRepository", sewerageRequestRepository);
        this.restSewerageRequestMockMvc = MockMvcBuilders.standaloneSetup(sewerageRequestResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        sewerageRequest = new SewerageRequest();
        sewerageRequest.setCustomerName(DEFAULT_CUSTOMER_NAME);
        sewerageRequest.setMobileNo(DEFAULT_MOBILE_NO);
        sewerageRequest.setRequestedDate(DEFAULT_REQUESTED_DATE);
        sewerageRequest.setReceiptNo(DEFAULT_RECEIPT_NO);
        sewerageRequest.setAddress(DEFAULT_ADDRESS);
        sewerageRequest.setAmount(DEFAULT_AMOUNT);
        sewerageRequest.setPaymentDate(DEFAULT_PAYMENT_DATE);
        sewerageRequest.setVehicleNo(DEFAULT_VEHICLE_NO);
        sewerageRequest.setDriver(DEFAULT_DRIVER);
        sewerageRequest.setCompletionDate(DEFAULT_COMPLETION_DATE);
    }

    @Test
    @Transactional
    public void createSewerageRequest() throws Exception {
        int databaseSizeBeforeCreate = sewerageRequestRepository.findAll().size();

        // Create the SewerageRequest

        restSewerageRequestMockMvc.perform(post("/api/sewerageRequests")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerageRequest)))
                .andExpect(status().isCreated());

        // Validate the SewerageRequest in the database
        List<SewerageRequest> sewerageRequests = sewerageRequestRepository.findAll();
        assertThat(sewerageRequests).hasSize(databaseSizeBeforeCreate + 1);
        SewerageRequest testSewerageRequest = sewerageRequests.get(sewerageRequests.size() - 1);
        assertThat(testSewerageRequest.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testSewerageRequest.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testSewerageRequest.getRequestedDate()).isEqualTo(DEFAULT_REQUESTED_DATE);
        assertThat(testSewerageRequest.getReceiptNo()).isEqualTo(DEFAULT_RECEIPT_NO);
        assertThat(testSewerageRequest.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testSewerageRequest.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testSewerageRequest.getPaymentDate()).isEqualTo(DEFAULT_PAYMENT_DATE);
        assertThat(testSewerageRequest.getVehicleNo()).isEqualTo(DEFAULT_VEHICLE_NO);
        assertThat(testSewerageRequest.getDriver()).isEqualTo(DEFAULT_DRIVER);
        assertThat(testSewerageRequest.getCompletionDate()).isEqualTo(DEFAULT_COMPLETION_DATE);
    }

    @Test
    @Transactional
    public void getAllSewerageRequests() throws Exception {
        // Initialize the database
        sewerageRequestRepository.saveAndFlush(sewerageRequest);

        // Get all the sewerageRequests
        restSewerageRequestMockMvc.perform(get("/api/sewerageRequests?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(sewerageRequest.getId().intValue())))
                .andExpect(jsonPath("$.[*].customerName").value(hasItem(DEFAULT_CUSTOMER_NAME.toString())))
                .andExpect(jsonPath("$.[*].mobileNo").value(hasItem(DEFAULT_MOBILE_NO.toString())))
                .andExpect(jsonPath("$.[*].requestedDate").value(hasItem(DEFAULT_REQUESTED_DATE.toString())))
                .andExpect(jsonPath("$.[*].receiptNo").value(hasItem(DEFAULT_RECEIPT_NO.intValue())))
                .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
                .andExpect(jsonPath("$.[*].paymentDate").value(hasItem(DEFAULT_PAYMENT_DATE.toString())))
                .andExpect(jsonPath("$.[*].vehicleNo").value(hasItem(DEFAULT_VEHICLE_NO.toString())))
                .andExpect(jsonPath("$.[*].driver").value(hasItem(DEFAULT_DRIVER.toString())))
                .andExpect(jsonPath("$.[*].completionDate").value(hasItem(DEFAULT_COMPLETION_DATE.toString())));
    }

    @Test
    @Transactional
    public void getSewerageRequest() throws Exception {
        // Initialize the database
        sewerageRequestRepository.saveAndFlush(sewerageRequest);

        // Get the sewerageRequest
        restSewerageRequestMockMvc.perform(get("/api/sewerageRequests/{id}", sewerageRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(sewerageRequest.getId().intValue()))
            .andExpect(jsonPath("$.customerName").value(DEFAULT_CUSTOMER_NAME.toString()))
            .andExpect(jsonPath("$.mobileNo").value(DEFAULT_MOBILE_NO.toString()))
            .andExpect(jsonPath("$.requestedDate").value(DEFAULT_REQUESTED_DATE.toString()))
            .andExpect(jsonPath("$.receiptNo").value(DEFAULT_RECEIPT_NO.intValue()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.paymentDate").value(DEFAULT_PAYMENT_DATE.toString()))
            .andExpect(jsonPath("$.vehicleNo").value(DEFAULT_VEHICLE_NO.toString()))
            .andExpect(jsonPath("$.driver").value(DEFAULT_DRIVER.toString()))
            .andExpect(jsonPath("$.completionDate").value(DEFAULT_COMPLETION_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSewerageRequest() throws Exception {
        // Get the sewerageRequest
        restSewerageRequestMockMvc.perform(get("/api/sewerageRequests/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSewerageRequest() throws Exception {
        // Initialize the database
        sewerageRequestRepository.saveAndFlush(sewerageRequest);

		int databaseSizeBeforeUpdate = sewerageRequestRepository.findAll().size();

        // Update the sewerageRequest
        sewerageRequest.setCustomerName(UPDATED_CUSTOMER_NAME);
        sewerageRequest.setMobileNo(UPDATED_MOBILE_NO);
        sewerageRequest.setRequestedDate(UPDATED_REQUESTED_DATE);
        sewerageRequest.setReceiptNo(UPDATED_RECEIPT_NO);
        sewerageRequest.setAddress(UPDATED_ADDRESS);
        sewerageRequest.setAmount(UPDATED_AMOUNT);
        sewerageRequest.setPaymentDate(UPDATED_PAYMENT_DATE);
        sewerageRequest.setVehicleNo(UPDATED_VEHICLE_NO);
        sewerageRequest.setDriver(UPDATED_DRIVER);
        sewerageRequest.setCompletionDate(UPDATED_COMPLETION_DATE);

        restSewerageRequestMockMvc.perform(put("/api/sewerageRequests")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerageRequest)))
                .andExpect(status().isOk());

        // Validate the SewerageRequest in the database
        List<SewerageRequest> sewerageRequests = sewerageRequestRepository.findAll();
        assertThat(sewerageRequests).hasSize(databaseSizeBeforeUpdate);
        SewerageRequest testSewerageRequest = sewerageRequests.get(sewerageRequests.size() - 1);
        assertThat(testSewerageRequest.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testSewerageRequest.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testSewerageRequest.getRequestedDate()).isEqualTo(UPDATED_REQUESTED_DATE);
        assertThat(testSewerageRequest.getReceiptNo()).isEqualTo(UPDATED_RECEIPT_NO);
        assertThat(testSewerageRequest.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testSewerageRequest.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testSewerageRequest.getPaymentDate()).isEqualTo(UPDATED_PAYMENT_DATE);
        assertThat(testSewerageRequest.getVehicleNo()).isEqualTo(UPDATED_VEHICLE_NO);
        assertThat(testSewerageRequest.getDriver()).isEqualTo(UPDATED_DRIVER);
        assertThat(testSewerageRequest.getCompletionDate()).isEqualTo(UPDATED_COMPLETION_DATE);
    }

    @Test
    @Transactional
    public void deleteSewerageRequest() throws Exception {
        // Initialize the database
        sewerageRequestRepository.saveAndFlush(sewerageRequest);

		int databaseSizeBeforeDelete = sewerageRequestRepository.findAll().size();

        // Get the sewerageRequest
        restSewerageRequestMockMvc.perform(delete("/api/sewerageRequests/{id}", sewerageRequest.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SewerageRequest> sewerageRequests = sewerageRequestRepository.findAll();
        assertThat(sewerageRequests).hasSize(databaseSizeBeforeDelete - 1);
    }
}

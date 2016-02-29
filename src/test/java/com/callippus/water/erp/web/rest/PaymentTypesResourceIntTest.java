package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.PaymentTypes;
import com.callippus.water.erp.repository.PaymentTypesRepository;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the PaymentTypesResource REST controller.
 *
 * @see PaymentTypesResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class PaymentTypesResourceIntTest {

    private static final String DEFAULT_PAYMENT_MODE = "AAAAA";
    private static final String UPDATED_PAYMENT_MODE = "BBBBB";

    @Inject
    private PaymentTypesRepository paymentTypesRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restPaymentTypesMockMvc;

    private PaymentTypes paymentTypes;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PaymentTypesResource paymentTypesResource = new PaymentTypesResource();
        ReflectionTestUtils.setField(paymentTypesResource, "paymentTypesRepository", paymentTypesRepository);
        this.restPaymentTypesMockMvc = MockMvcBuilders.standaloneSetup(paymentTypesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        paymentTypes = new PaymentTypes();
        paymentTypes.setPaymentMode(DEFAULT_PAYMENT_MODE);
    }

    @Test
    @Transactional
    public void createPaymentTypes() throws Exception {
        int databaseSizeBeforeCreate = paymentTypesRepository.findAll().size();

        // Create the PaymentTypes

        restPaymentTypesMockMvc.perform(post("/api/paymentTypess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentTypes)))
                .andExpect(status().isCreated());

        // Validate the PaymentTypes in the database
        List<PaymentTypes> paymentTypess = paymentTypesRepository.findAll();
        assertThat(paymentTypess).hasSize(databaseSizeBeforeCreate + 1);
        PaymentTypes testPaymentTypes = paymentTypess.get(paymentTypess.size() - 1);
        assertThat(testPaymentTypes.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
    }

    @Test
    @Transactional
    public void checkPaymentModeIsRequired() throws Exception {
        int databaseSizeBeforeTest = paymentTypesRepository.findAll().size();
        // set the field null
        paymentTypes.setPaymentMode(null);

        // Create the PaymentTypes, which fails.

        restPaymentTypesMockMvc.perform(post("/api/paymentTypess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentTypes)))
                .andExpect(status().isBadRequest());

        List<PaymentTypes> paymentTypess = paymentTypesRepository.findAll();
        assertThat(paymentTypess).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPaymentTypess() throws Exception {
        // Initialize the database
        paymentTypesRepository.saveAndFlush(paymentTypes);

        // Get all the paymentTypess
        restPaymentTypesMockMvc.perform(get("/api/paymentTypess?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(paymentTypes.getId().intValue())))
                .andExpect(jsonPath("$.[*].paymentMode").value(hasItem(DEFAULT_PAYMENT_MODE.toString())));
    }

    @Test
    @Transactional
    public void getPaymentTypes() throws Exception {
        // Initialize the database
        paymentTypesRepository.saveAndFlush(paymentTypes);

        // Get the paymentTypes
        restPaymentTypesMockMvc.perform(get("/api/paymentTypess/{id}", paymentTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(paymentTypes.getId().intValue()))
            .andExpect(jsonPath("$.paymentMode").value(DEFAULT_PAYMENT_MODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPaymentTypes() throws Exception {
        // Get the paymentTypes
        restPaymentTypesMockMvc.perform(get("/api/paymentTypess/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePaymentTypes() throws Exception {
        // Initialize the database
        paymentTypesRepository.saveAndFlush(paymentTypes);

		int databaseSizeBeforeUpdate = paymentTypesRepository.findAll().size();

        // Update the paymentTypes
        paymentTypes.setPaymentMode(UPDATED_PAYMENT_MODE);

        restPaymentTypesMockMvc.perform(put("/api/paymentTypess")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(paymentTypes)))
                .andExpect(status().isOk());

        // Validate the PaymentTypes in the database
        List<PaymentTypes> paymentTypess = paymentTypesRepository.findAll();
        assertThat(paymentTypess).hasSize(databaseSizeBeforeUpdate);
        PaymentTypes testPaymentTypes = paymentTypess.get(paymentTypess.size() - 1);
        assertThat(testPaymentTypes.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
    }

    @Test
    @Transactional
    public void deletePaymentTypes() throws Exception {
        // Initialize the database
        paymentTypesRepository.saveAndFlush(paymentTypes);

		int databaseSizeBeforeDelete = paymentTypesRepository.findAll().size();

        // Get the paymentTypes
        restPaymentTypesMockMvc.perform(delete("/api/paymentTypess/{id}", paymentTypes.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PaymentTypes> paymentTypess = paymentTypesRepository.findAll();
        assertThat(paymentTypess).hasSize(databaseSizeBeforeDelete - 1);
    }
}

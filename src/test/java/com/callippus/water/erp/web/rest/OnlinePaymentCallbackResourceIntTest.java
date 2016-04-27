package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OnlinePaymentCallback;
import com.callippus.water.erp.repository.OnlinePaymentCallbackRepository;

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
 * Test class for the OnlinePaymentCallbackResource REST controller.
 *
 * @see OnlinePaymentCallbackResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OnlinePaymentCallbackResourceIntTest {

    private static final String DEFAULT_CURRENCY = "AAAAA";
    private static final String UPDATED_CURRENCY = "BBBBB";
    private static final String DEFAULT_PAYMENT_MODE = "AAAAA";
    private static final String UPDATED_PAYMENT_MODE = "BBBBB";
    private static final String DEFAULT_SERVICE_CODE = "AAAAA";
    private static final String UPDATED_SERVICE_CODE = "BBBBB";
    private static final String DEFAULT_MESSAGE = "AAAAA";
    private static final String UPDATED_MESSAGE = "BBBBB";
    private static final String DEFAULT_RESPONSE_CODE = "AAAAA";
    private static final String UPDATED_RESPONSE_CODE = "BBBBB";

    private static final Float DEFAULT_TOTAL_AMOUNT_PAID = 1F;
    private static final Float UPDATED_TOTAL_AMOUNT_PAID = 2F;
    private static final String DEFAULT_USER_DEFINED_FIELD = "AAAAA";
    private static final String UPDATED_USER_DEFINED_FIELD = "BBBBB";

    @Inject
    private OnlinePaymentCallbackRepository onlinePaymentCallbackRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOnlinePaymentCallbackMockMvc;

    private OnlinePaymentCallback onlinePaymentCallback;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OnlinePaymentCallbackResource onlinePaymentCallbackResource = new OnlinePaymentCallbackResource();
        ReflectionTestUtils.setField(onlinePaymentCallbackResource, "onlinePaymentCallbackRepository", onlinePaymentCallbackRepository);
        this.restOnlinePaymentCallbackMockMvc = MockMvcBuilders.standaloneSetup(onlinePaymentCallbackResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        onlinePaymentCallback = new OnlinePaymentCallback();
        onlinePaymentCallback.setCurrency(DEFAULT_CURRENCY);
        onlinePaymentCallback.setPaymentMode(DEFAULT_PAYMENT_MODE);
        onlinePaymentCallback.setServiceCode(DEFAULT_SERVICE_CODE);
        onlinePaymentCallback.setMessage(DEFAULT_MESSAGE);
        onlinePaymentCallback.setResponseCode(DEFAULT_RESPONSE_CODE);
        onlinePaymentCallback.setTotalAmountPaid(DEFAULT_TOTAL_AMOUNT_PAID);
        onlinePaymentCallback.setUserDefinedField(DEFAULT_USER_DEFINED_FIELD);
    }

    @Test
    @Transactional
    public void createOnlinePaymentCallback() throws Exception {
        int databaseSizeBeforeCreate = onlinePaymentCallbackRepository.findAll().size();

        // Create the OnlinePaymentCallback

        restOnlinePaymentCallbackMockMvc.perform(post("/api/onlinePaymentCallbacks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentCallback)))
                .andExpect(status().isCreated());

        // Validate the OnlinePaymentCallback in the database
        List<OnlinePaymentCallback> onlinePaymentCallbacks = onlinePaymentCallbackRepository.findAll();
        assertThat(onlinePaymentCallbacks).hasSize(databaseSizeBeforeCreate + 1);
        OnlinePaymentCallback testOnlinePaymentCallback = onlinePaymentCallbacks.get(onlinePaymentCallbacks.size() - 1);
        assertThat(testOnlinePaymentCallback.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testOnlinePaymentCallback.getPaymentMode()).isEqualTo(DEFAULT_PAYMENT_MODE);
        assertThat(testOnlinePaymentCallback.getServiceCode()).isEqualTo(DEFAULT_SERVICE_CODE);
        assertThat(testOnlinePaymentCallback.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testOnlinePaymentCallback.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testOnlinePaymentCallback.getTotalAmountPaid()).isEqualTo(DEFAULT_TOTAL_AMOUNT_PAID);
        assertThat(testOnlinePaymentCallback.getUserDefinedField()).isEqualTo(DEFAULT_USER_DEFINED_FIELD);
    }

    @Test
    @Transactional
    public void getAllOnlinePaymentCallbacks() throws Exception {
        // Initialize the database
        onlinePaymentCallbackRepository.saveAndFlush(onlinePaymentCallback);

        // Get all the onlinePaymentCallbacks
        restOnlinePaymentCallbackMockMvc.perform(get("/api/onlinePaymentCallbacks?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(onlinePaymentCallback.getId().intValue())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())))
                .andExpect(jsonPath("$.[*].paymentMode").value(hasItem(DEFAULT_PAYMENT_MODE.toString())))
                .andExpect(jsonPath("$.[*].serviceCode").value(hasItem(DEFAULT_SERVICE_CODE.toString())))
                .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
                .andExpect(jsonPath("$.[*].responseCode").value(hasItem(DEFAULT_RESPONSE_CODE.toString())))
                .andExpect(jsonPath("$.[*].totalAmountPaid").value(hasItem(DEFAULT_TOTAL_AMOUNT_PAID.doubleValue())))
                .andExpect(jsonPath("$.[*].userDefinedField").value(hasItem(DEFAULT_USER_DEFINED_FIELD.toString())));
    }

    @Test
    @Transactional
    public void getOnlinePaymentCallback() throws Exception {
        // Initialize the database
        onlinePaymentCallbackRepository.saveAndFlush(onlinePaymentCallback);

        // Get the onlinePaymentCallback
        restOnlinePaymentCallbackMockMvc.perform(get("/api/onlinePaymentCallbacks/{id}", onlinePaymentCallback.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(onlinePaymentCallback.getId().intValue()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()))
            .andExpect(jsonPath("$.paymentMode").value(DEFAULT_PAYMENT_MODE.toString()))
            .andExpect(jsonPath("$.serviceCode").value(DEFAULT_SERVICE_CODE.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.responseCode").value(DEFAULT_RESPONSE_CODE.toString()))
            .andExpect(jsonPath("$.totalAmountPaid").value(DEFAULT_TOTAL_AMOUNT_PAID.doubleValue()))
            .andExpect(jsonPath("$.userDefinedField").value(DEFAULT_USER_DEFINED_FIELD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOnlinePaymentCallback() throws Exception {
        // Get the onlinePaymentCallback
        restOnlinePaymentCallbackMockMvc.perform(get("/api/onlinePaymentCallbacks/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnlinePaymentCallback() throws Exception {
        // Initialize the database
        onlinePaymentCallbackRepository.saveAndFlush(onlinePaymentCallback);

		int databaseSizeBeforeUpdate = onlinePaymentCallbackRepository.findAll().size();

        // Update the onlinePaymentCallback
        onlinePaymentCallback.setCurrency(UPDATED_CURRENCY);
        onlinePaymentCallback.setPaymentMode(UPDATED_PAYMENT_MODE);
        onlinePaymentCallback.setServiceCode(UPDATED_SERVICE_CODE);
        onlinePaymentCallback.setMessage(UPDATED_MESSAGE);
        onlinePaymentCallback.setResponseCode(UPDATED_RESPONSE_CODE);
        onlinePaymentCallback.setTotalAmountPaid(UPDATED_TOTAL_AMOUNT_PAID);
        onlinePaymentCallback.setUserDefinedField(UPDATED_USER_DEFINED_FIELD);

        restOnlinePaymentCallbackMockMvc.perform(put("/api/onlinePaymentCallbacks")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentCallback)))
                .andExpect(status().isOk());

        // Validate the OnlinePaymentCallback in the database
        List<OnlinePaymentCallback> onlinePaymentCallbacks = onlinePaymentCallbackRepository.findAll();
        assertThat(onlinePaymentCallbacks).hasSize(databaseSizeBeforeUpdate);
        OnlinePaymentCallback testOnlinePaymentCallback = onlinePaymentCallbacks.get(onlinePaymentCallbacks.size() - 1);
        assertThat(testOnlinePaymentCallback.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testOnlinePaymentCallback.getPaymentMode()).isEqualTo(UPDATED_PAYMENT_MODE);
        assertThat(testOnlinePaymentCallback.getServiceCode()).isEqualTo(UPDATED_SERVICE_CODE);
        assertThat(testOnlinePaymentCallback.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testOnlinePaymentCallback.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testOnlinePaymentCallback.getTotalAmountPaid()).isEqualTo(UPDATED_TOTAL_AMOUNT_PAID);
        assertThat(testOnlinePaymentCallback.getUserDefinedField()).isEqualTo(UPDATED_USER_DEFINED_FIELD);
    }

    @Test
    @Transactional
    public void deleteOnlinePaymentCallback() throws Exception {
        // Initialize the database
        onlinePaymentCallbackRepository.saveAndFlush(onlinePaymentCallback);

		int databaseSizeBeforeDelete = onlinePaymentCallbackRepository.findAll().size();

        // Get the onlinePaymentCallback
        restOnlinePaymentCallbackMockMvc.perform(delete("/api/onlinePaymentCallbacks/{id}", onlinePaymentCallback.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OnlinePaymentCallback> onlinePaymentCallbacks = onlinePaymentCallbackRepository.findAll();
        assertThat(onlinePaymentCallbacks).hasSize(databaseSizeBeforeDelete - 1);
    }
}

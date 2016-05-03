package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OnlinePaymentResponse;
import com.callippus.water.erp.repository.OnlinePaymentResponseRepository;

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
 * Test class for the OnlinePaymentResponseResource REST controller.
 *
 * @see OnlinePaymentResponseResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OnlinePaymentResponseResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_RESPONSE_CODE = "AAAAA";
    private static final String UPDATED_RESPONSE_CODE = "BBBBB";

    private static final ZonedDateTime DEFAULT_RESPONSE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_RESPONSE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_RESPONSE_TIME_STR = dateTimeFormatter.format(DEFAULT_RESPONSE_TIME);
    private static final String DEFAULT_REDIRECT_URL = "AAAAA";
    private static final String UPDATED_REDIRECT_URL = "BBBBB";
    private static final String DEFAULT_MERCHANT_TXN_REF = "AAAAA";
    private static final String UPDATED_MERCHANT_TXN_REF = "BBBBB";

    @Inject
    private OnlinePaymentResponseRepository onlinePaymentResponseRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOnlinePaymentResponseMockMvc;

    private OnlinePaymentResponse onlinePaymentResponse;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OnlinePaymentResponseResource onlinePaymentResponseResource = new OnlinePaymentResponseResource();
        ReflectionTestUtils.setField(onlinePaymentResponseResource, "onlinePaymentResponseRepository", onlinePaymentResponseRepository);
        this.restOnlinePaymentResponseMockMvc = MockMvcBuilders.standaloneSetup(onlinePaymentResponseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        onlinePaymentResponse = new OnlinePaymentResponse();
        onlinePaymentResponse.setResponseCode(DEFAULT_RESPONSE_CODE);
        onlinePaymentResponse.setResponseTime(DEFAULT_RESPONSE_TIME);
        onlinePaymentResponse.setRedirectUrl(DEFAULT_REDIRECT_URL);
        onlinePaymentResponse.setMerchantTxnRef(DEFAULT_MERCHANT_TXN_REF);
    }

    @Test
    @Transactional
    public void createOnlinePaymentResponse() throws Exception {
        int databaseSizeBeforeCreate = onlinePaymentResponseRepository.findAll().size();

        // Create the OnlinePaymentResponse

        restOnlinePaymentResponseMockMvc.perform(post("/api/onlinePaymentResponses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentResponse)))
                .andExpect(status().isCreated());

        // Validate the OnlinePaymentResponse in the database
        List<OnlinePaymentResponse> onlinePaymentResponses = onlinePaymentResponseRepository.findAll();
        assertThat(onlinePaymentResponses).hasSize(databaseSizeBeforeCreate + 1);
        OnlinePaymentResponse testOnlinePaymentResponse = onlinePaymentResponses.get(onlinePaymentResponses.size() - 1);
        assertThat(testOnlinePaymentResponse.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testOnlinePaymentResponse.getResponseTime()).isEqualTo(DEFAULT_RESPONSE_TIME);
        assertThat(testOnlinePaymentResponse.getRedirectUrl()).isEqualTo(DEFAULT_REDIRECT_URL);
        assertThat(testOnlinePaymentResponse.getMerchantTxnRef()).isEqualTo(DEFAULT_MERCHANT_TXN_REF);
    }

    @Test
    @Transactional
    public void getAllOnlinePaymentResponses() throws Exception {
        // Initialize the database
        onlinePaymentResponseRepository.saveAndFlush(onlinePaymentResponse);

        // Get all the onlinePaymentResponses
        restOnlinePaymentResponseMockMvc.perform(get("/api/onlinePaymentResponses?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(onlinePaymentResponse.getId().intValue())))
                .andExpect(jsonPath("$.[*].responseCode").value(hasItem(DEFAULT_RESPONSE_CODE.toString())))
                .andExpect(jsonPath("$.[*].responseTime").value(hasItem(DEFAULT_RESPONSE_TIME_STR)))
                .andExpect(jsonPath("$.[*].redirectUrl").value(hasItem(DEFAULT_REDIRECT_URL.toString())))
                .andExpect(jsonPath("$.[*].merchantTxnRef").value(hasItem(DEFAULT_MERCHANT_TXN_REF.toString())));
    }

    @Test
    @Transactional
    public void getOnlinePaymentResponse() throws Exception {
        // Initialize the database
        onlinePaymentResponseRepository.saveAndFlush(onlinePaymentResponse);

        // Get the onlinePaymentResponse
        restOnlinePaymentResponseMockMvc.perform(get("/api/onlinePaymentResponses/{id}", onlinePaymentResponse.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(onlinePaymentResponse.getId().intValue()))
            .andExpect(jsonPath("$.responseCode").value(DEFAULT_RESPONSE_CODE.toString()))
            .andExpect(jsonPath("$.responseTime").value(DEFAULT_RESPONSE_TIME_STR))
            .andExpect(jsonPath("$.redirectUrl").value(DEFAULT_REDIRECT_URL.toString()))
            .andExpect(jsonPath("$.merchantTxnRef").value(DEFAULT_MERCHANT_TXN_REF.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingOnlinePaymentResponse() throws Exception {
        // Get the onlinePaymentResponse
        restOnlinePaymentResponseMockMvc.perform(get("/api/onlinePaymentResponses/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnlinePaymentResponse() throws Exception {
        // Initialize the database
        onlinePaymentResponseRepository.saveAndFlush(onlinePaymentResponse);

		int databaseSizeBeforeUpdate = onlinePaymentResponseRepository.findAll().size();

        // Update the onlinePaymentResponse
        onlinePaymentResponse.setResponseCode(UPDATED_RESPONSE_CODE);
        onlinePaymentResponse.setResponseTime(UPDATED_RESPONSE_TIME);
        onlinePaymentResponse.setRedirectUrl(UPDATED_REDIRECT_URL);
        onlinePaymentResponse.setMerchantTxnRef(UPDATED_MERCHANT_TXN_REF);

        restOnlinePaymentResponseMockMvc.perform(put("/api/onlinePaymentResponses")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentResponse)))
                .andExpect(status().isOk());

        // Validate the OnlinePaymentResponse in the database
        List<OnlinePaymentResponse> onlinePaymentResponses = onlinePaymentResponseRepository.findAll();
        assertThat(onlinePaymentResponses).hasSize(databaseSizeBeforeUpdate);
        OnlinePaymentResponse testOnlinePaymentResponse = onlinePaymentResponses.get(onlinePaymentResponses.size() - 1);
        assertThat(testOnlinePaymentResponse.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testOnlinePaymentResponse.getResponseTime()).isEqualTo(UPDATED_RESPONSE_TIME);
        assertThat(testOnlinePaymentResponse.getRedirectUrl()).isEqualTo(UPDATED_REDIRECT_URL);
        assertThat(testOnlinePaymentResponse.getMerchantTxnRef()).isEqualTo(UPDATED_MERCHANT_TXN_REF);
    }

    @Test
    @Transactional
    public void deleteOnlinePaymentResponse() throws Exception {
        // Initialize the database
        onlinePaymentResponseRepository.saveAndFlush(onlinePaymentResponse);

		int databaseSizeBeforeDelete = onlinePaymentResponseRepository.findAll().size();

        // Get the onlinePaymentResponse
        restOnlinePaymentResponseMockMvc.perform(delete("/api/onlinePaymentResponses/{id}", onlinePaymentResponse.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OnlinePaymentResponse> onlinePaymentResponses = onlinePaymentResponseRepository.findAll();
        assertThat(onlinePaymentResponses).hasSize(databaseSizeBeforeDelete - 1);
    }
}

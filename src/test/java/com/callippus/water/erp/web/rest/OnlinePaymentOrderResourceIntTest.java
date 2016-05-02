package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.OnlinePaymentOrder;
import com.callippus.water.erp.repository.OnlinePaymentOrderRepository;

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

import com.callippus.water.erp.domain.enumeration.PaymentMode;

/**
 * Test class for the OnlinePaymentOrderResource REST controller.
 *
 * @see OnlinePaymentOrderResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class OnlinePaymentOrderResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_SERVICE_CODE = "AAAAA";
    private static final String UPDATED_SERVICE_CODE = "BBBBB";

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;


    private static final PaymentMode DEFAULT_PAY_BY = PaymentMode.TIGOPESA;
    private static final PaymentMode UPDATED_PAY_BY = PaymentMode.CREDITCARD;
    private static final String DEFAULT_USER_DEFINED_FIELD = "AAAAA";
    private static final String UPDATED_USER_DEFINED_FIELD = "BBBBB";
    private static final String DEFAULT_EMAIL = "AAAAA";
    private static final String UPDATED_EMAIL = "BBBBB";

    private static final Long DEFAULT_PHONE = 5L;
    private static final Long UPDATED_PHONE = 6L;

    private static final ZonedDateTime DEFAULT_ORDER_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_ORDER_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_ORDER_TIME_STR = dateTimeFormatter.format(DEFAULT_ORDER_TIME);

    @Inject
    private OnlinePaymentOrderRepository onlinePaymentOrderRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restOnlinePaymentOrderMockMvc;

    private OnlinePaymentOrder onlinePaymentOrder;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        OnlinePaymentOrderResource onlinePaymentOrderResource = new OnlinePaymentOrderResource();
        ReflectionTestUtils.setField(onlinePaymentOrderResource, "onlinePaymentOrderRepository", onlinePaymentOrderRepository);
        this.restOnlinePaymentOrderMockMvc = MockMvcBuilders.standaloneSetup(onlinePaymentOrderResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        onlinePaymentOrder = new OnlinePaymentOrder();
        onlinePaymentOrder.setServiceCode(DEFAULT_SERVICE_CODE);
        onlinePaymentOrder.setAmount(DEFAULT_AMOUNT);
        onlinePaymentOrder.setPayBy(DEFAULT_PAY_BY);
        onlinePaymentOrder.setUserDefinedField(DEFAULT_USER_DEFINED_FIELD);
        onlinePaymentOrder.setEmail(DEFAULT_EMAIL);
        onlinePaymentOrder.setPhone(DEFAULT_PHONE);
        onlinePaymentOrder.setOrderTime(DEFAULT_ORDER_TIME);
    }

    @Test
    @Transactional
    public void createOnlinePaymentOrder() throws Exception {
        int databaseSizeBeforeCreate = onlinePaymentOrderRepository.findAll().size();

        // Create the OnlinePaymentOrder

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isCreated());

        // Validate the OnlinePaymentOrder in the database
        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeCreate + 1);
        OnlinePaymentOrder testOnlinePaymentOrder = onlinePaymentOrders.get(onlinePaymentOrders.size() - 1);
        assertThat(testOnlinePaymentOrder.getServiceCode()).isEqualTo(DEFAULT_SERVICE_CODE);
        assertThat(testOnlinePaymentOrder.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testOnlinePaymentOrder.getPayBy()).isEqualTo(DEFAULT_PAY_BY);
        assertThat(testOnlinePaymentOrder.getUserDefinedField()).isEqualTo(DEFAULT_USER_DEFINED_FIELD);
        assertThat(testOnlinePaymentOrder.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testOnlinePaymentOrder.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testOnlinePaymentOrder.getOrderTime()).isEqualTo(DEFAULT_ORDER_TIME);
    }

    @Test
    @Transactional
    public void checkServiceCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = onlinePaymentOrderRepository.findAll().size();
        // set the field null
        onlinePaymentOrder.setServiceCode(null);

        // Create the OnlinePaymentOrder, which fails.

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isBadRequest());

        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = onlinePaymentOrderRepository.findAll().size();
        // set the field null
        onlinePaymentOrder.setAmount(null);

        // Create the OnlinePaymentOrder, which fails.

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isBadRequest());

        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPayByIsRequired() throws Exception {
        int databaseSizeBeforeTest = onlinePaymentOrderRepository.findAll().size();
        // set the field null
        onlinePaymentOrder.setPayBy(null);

        // Create the OnlinePaymentOrder, which fails.

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isBadRequest());

        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUserDefinedFieldIsRequired() throws Exception {
        int databaseSizeBeforeTest = onlinePaymentOrderRepository.findAll().size();
        // set the field null
        onlinePaymentOrder.setUserDefinedField(null);

        // Create the OnlinePaymentOrder, which fails.

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isBadRequest());

        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = onlinePaymentOrderRepository.findAll().size();
        // set the field null
        onlinePaymentOrder.setEmail(null);

        // Create the OnlinePaymentOrder, which fails.

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isBadRequest());

        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = onlinePaymentOrderRepository.findAll().size();
        // set the field null
        onlinePaymentOrder.setPhone(null);

        // Create the OnlinePaymentOrder, which fails.

        restOnlinePaymentOrderMockMvc.perform(post("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isBadRequest());

        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllOnlinePaymentOrders() throws Exception {
        // Initialize the database
        onlinePaymentOrderRepository.saveAndFlush(onlinePaymentOrder);

        // Get all the onlinePaymentOrders
        restOnlinePaymentOrderMockMvc.perform(get("/api/onlinePaymentOrders?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(onlinePaymentOrder.getId().intValue())))
                .andExpect(jsonPath("$.[*].serviceCode").value(hasItem(DEFAULT_SERVICE_CODE.toString())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
                .andExpect(jsonPath("$.[*].payBy").value(hasItem(DEFAULT_PAY_BY.toString())))
                .andExpect(jsonPath("$.[*].userDefinedField").value(hasItem(DEFAULT_USER_DEFINED_FIELD.toString())))
                .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
                .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
                .andExpect(jsonPath("$.[*].orderTime").value(hasItem(DEFAULT_ORDER_TIME_STR)));
    }

    @Test
    @Transactional
    public void getOnlinePaymentOrder() throws Exception {
        // Initialize the database
        onlinePaymentOrderRepository.saveAndFlush(onlinePaymentOrder);

        // Get the onlinePaymentOrder
        restOnlinePaymentOrderMockMvc.perform(get("/api/onlinePaymentOrders/{id}", onlinePaymentOrder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(onlinePaymentOrder.getId().intValue()))
            .andExpect(jsonPath("$.serviceCode").value(DEFAULT_SERVICE_CODE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.payBy").value(DEFAULT_PAY_BY.toString()))
            .andExpect(jsonPath("$.userDefinedField").value(DEFAULT_USER_DEFINED_FIELD.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()))
            .andExpect(jsonPath("$.orderTime").value(DEFAULT_ORDER_TIME_STR));
    }

    @Test
    @Transactional
    public void getNonExistingOnlinePaymentOrder() throws Exception {
        // Get the onlinePaymentOrder
        restOnlinePaymentOrderMockMvc.perform(get("/api/onlinePaymentOrders/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOnlinePaymentOrder() throws Exception {
        // Initialize the database
        onlinePaymentOrderRepository.saveAndFlush(onlinePaymentOrder);

		int databaseSizeBeforeUpdate = onlinePaymentOrderRepository.findAll().size();

        // Update the onlinePaymentOrder
        onlinePaymentOrder.setServiceCode(UPDATED_SERVICE_CODE);
        onlinePaymentOrder.setAmount(UPDATED_AMOUNT);
        onlinePaymentOrder.setPayBy(UPDATED_PAY_BY);
        onlinePaymentOrder.setUserDefinedField(UPDATED_USER_DEFINED_FIELD);
        onlinePaymentOrder.setEmail(UPDATED_EMAIL);
        onlinePaymentOrder.setPhone(UPDATED_PHONE);
        onlinePaymentOrder.setOrderTime(UPDATED_ORDER_TIME);

        restOnlinePaymentOrderMockMvc.perform(put("/api/onlinePaymentOrders")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(onlinePaymentOrder)))
                .andExpect(status().isOk());

        // Validate the OnlinePaymentOrder in the database
        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeUpdate);
        OnlinePaymentOrder testOnlinePaymentOrder = onlinePaymentOrders.get(onlinePaymentOrders.size() - 1);
        assertThat(testOnlinePaymentOrder.getServiceCode()).isEqualTo(UPDATED_SERVICE_CODE);
        assertThat(testOnlinePaymentOrder.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testOnlinePaymentOrder.getPayBy()).isEqualTo(UPDATED_PAY_BY);
        assertThat(testOnlinePaymentOrder.getUserDefinedField()).isEqualTo(UPDATED_USER_DEFINED_FIELD);
        assertThat(testOnlinePaymentOrder.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testOnlinePaymentOrder.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testOnlinePaymentOrder.getOrderTime()).isEqualTo(UPDATED_ORDER_TIME);
    }

    @Test
    @Transactional
    public void deleteOnlinePaymentOrder() throws Exception {
        // Initialize the database
        onlinePaymentOrderRepository.saveAndFlush(onlinePaymentOrder);

		int databaseSizeBeforeDelete = onlinePaymentOrderRepository.findAll().size();

        // Get the onlinePaymentOrder
        restOnlinePaymentOrderMockMvc.perform(delete("/api/onlinePaymentOrders/{id}", onlinePaymentOrder.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<OnlinePaymentOrder> onlinePaymentOrders = onlinePaymentOrderRepository.findAll();
        assertThat(onlinePaymentOrders).hasSize(databaseSizeBeforeDelete - 1);
    }
}

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    private static final String DEFAULT_SERVICE_CODE = "AAAAA";
    private static final String UPDATED_SERVICE_CODE = "BBBBB";
    private static final String DEFAULT_AMOUNT = "AAAAA";
    private static final String UPDATED_AMOUNT = "BBBBB";
    private static final String DEFAULT_PAY_BY = "AAAAA";
    private static final String UPDATED_PAY_BY = "BBBBB";
    private static final String DEFAULT_USER_DEFINED_FIELD = "AAAAA";
    private static final String UPDATED_USER_DEFINED_FIELD = "BBBBB";

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
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.toString())))
                .andExpect(jsonPath("$.[*].payBy").value(hasItem(DEFAULT_PAY_BY.toString())))
                .andExpect(jsonPath("$.[*].userDefinedField").value(hasItem(DEFAULT_USER_DEFINED_FIELD.toString())));
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
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.toString()))
            .andExpect(jsonPath("$.payBy").value(DEFAULT_PAY_BY.toString()))
            .andExpect(jsonPath("$.userDefinedField").value(DEFAULT_USER_DEFINED_FIELD.toString()));
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

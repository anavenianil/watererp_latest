package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MerchantMaster;
import com.callippus.water.erp.repository.MerchantMasterRepository;

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
 * Test class for the MerchantMasterResource REST controller.
 *
 * @see MerchantMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MerchantMasterResourceIntTest {

    private static final String DEFAULT_MERCHANT_CODE = "AAAAA";
    private static final String UPDATED_MERCHANT_CODE = "BBBBB";
    private static final String DEFAULT_MERCHANT_NAME = "AAAAA";
    private static final String UPDATED_MERCHANT_NAME = "BBBBB";
    private static final String DEFAULT_MERCHANT_KEY = "AAAAA";
    private static final String UPDATED_MERCHANT_KEY = "BBBBB";
    private static final String DEFAULT_CURRENCY = "AAAAA";
    private static final String UPDATED_CURRENCY = "BBBBB";

    @Inject
    private MerchantMasterRepository merchantMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMerchantMasterMockMvc;

    private MerchantMaster merchantMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MerchantMasterResource merchantMasterResource = new MerchantMasterResource();
        ReflectionTestUtils.setField(merchantMasterResource, "merchantMasterRepository", merchantMasterRepository);
        this.restMerchantMasterMockMvc = MockMvcBuilders.standaloneSetup(merchantMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        merchantMaster = new MerchantMaster();
        merchantMaster.setMerchantCode(DEFAULT_MERCHANT_CODE);
        merchantMaster.setMerchantName(DEFAULT_MERCHANT_NAME);
        merchantMaster.setMerchantKey(DEFAULT_MERCHANT_KEY);
        merchantMaster.setCurrency(DEFAULT_CURRENCY);
    }

    @Test
    @Transactional
    public void createMerchantMaster() throws Exception {
        int databaseSizeBeforeCreate = merchantMasterRepository.findAll().size();

        // Create the MerchantMaster

        restMerchantMasterMockMvc.perform(post("/api/merchantMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(merchantMaster)))
                .andExpect(status().isCreated());

        // Validate the MerchantMaster in the database
        List<MerchantMaster> merchantMasters = merchantMasterRepository.findAll();
        assertThat(merchantMasters).hasSize(databaseSizeBeforeCreate + 1);
        MerchantMaster testMerchantMaster = merchantMasters.get(merchantMasters.size() - 1);
        assertThat(testMerchantMaster.getMerchantCode()).isEqualTo(DEFAULT_MERCHANT_CODE);
        assertThat(testMerchantMaster.getMerchantName()).isEqualTo(DEFAULT_MERCHANT_NAME);
        assertThat(testMerchantMaster.getMerchantKey()).isEqualTo(DEFAULT_MERCHANT_KEY);
        assertThat(testMerchantMaster.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
    }

    @Test
    @Transactional
    public void getAllMerchantMasters() throws Exception {
        // Initialize the database
        merchantMasterRepository.saveAndFlush(merchantMaster);

        // Get all the merchantMasters
        restMerchantMasterMockMvc.perform(get("/api/merchantMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(merchantMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].merchantCode").value(hasItem(DEFAULT_MERCHANT_CODE.toString())))
                .andExpect(jsonPath("$.[*].merchantName").value(hasItem(DEFAULT_MERCHANT_NAME.toString())))
                .andExpect(jsonPath("$.[*].merchantKey").value(hasItem(DEFAULT_MERCHANT_KEY.toString())))
                .andExpect(jsonPath("$.[*].currency").value(hasItem(DEFAULT_CURRENCY.toString())));
    }

    @Test
    @Transactional
    public void getMerchantMaster() throws Exception {
        // Initialize the database
        merchantMasterRepository.saveAndFlush(merchantMaster);

        // Get the merchantMaster
        restMerchantMasterMockMvc.perform(get("/api/merchantMasters/{id}", merchantMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(merchantMaster.getId().intValue()))
            .andExpect(jsonPath("$.merchantCode").value(DEFAULT_MERCHANT_CODE.toString()))
            .andExpect(jsonPath("$.merchantName").value(DEFAULT_MERCHANT_NAME.toString()))
            .andExpect(jsonPath("$.merchantKey").value(DEFAULT_MERCHANT_KEY.toString()))
            .andExpect(jsonPath("$.currency").value(DEFAULT_CURRENCY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMerchantMaster() throws Exception {
        // Get the merchantMaster
        restMerchantMasterMockMvc.perform(get("/api/merchantMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMerchantMaster() throws Exception {
        // Initialize the database
        merchantMasterRepository.saveAndFlush(merchantMaster);

		int databaseSizeBeforeUpdate = merchantMasterRepository.findAll().size();

        // Update the merchantMaster
        merchantMaster.setMerchantCode(UPDATED_MERCHANT_CODE);
        merchantMaster.setMerchantName(UPDATED_MERCHANT_NAME);
        merchantMaster.setMerchantKey(UPDATED_MERCHANT_KEY);
        merchantMaster.setCurrency(UPDATED_CURRENCY);

        restMerchantMasterMockMvc.perform(put("/api/merchantMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(merchantMaster)))
                .andExpect(status().isOk());

        // Validate the MerchantMaster in the database
        List<MerchantMaster> merchantMasters = merchantMasterRepository.findAll();
        assertThat(merchantMasters).hasSize(databaseSizeBeforeUpdate);
        MerchantMaster testMerchantMaster = merchantMasters.get(merchantMasters.size() - 1);
        assertThat(testMerchantMaster.getMerchantCode()).isEqualTo(UPDATED_MERCHANT_CODE);
        assertThat(testMerchantMaster.getMerchantName()).isEqualTo(UPDATED_MERCHANT_NAME);
        assertThat(testMerchantMaster.getMerchantKey()).isEqualTo(UPDATED_MERCHANT_KEY);
        assertThat(testMerchantMaster.getCurrency()).isEqualTo(UPDATED_CURRENCY);
    }

    @Test
    @Transactional
    public void deleteMerchantMaster() throws Exception {
        // Initialize the database
        merchantMasterRepository.saveAndFlush(merchantMaster);

		int databaseSizeBeforeDelete = merchantMasterRepository.findAll().size();

        // Get the merchantMaster
        restMerchantMasterMockMvc.perform(delete("/api/merchantMasters/{id}", merchantMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MerchantMaster> merchantMasters = merchantMasterRepository.findAll();
        assertThat(merchantMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

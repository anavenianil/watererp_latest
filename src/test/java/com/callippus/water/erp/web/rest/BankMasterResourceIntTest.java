package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.BankMaster;
import com.callippus.water.erp.repository.BankMasterRepository;

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
 * Test class for the BankMasterResource REST controller.
 *
 * @see BankMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BankMasterResourceIntTest {

    private static final String DEFAULT_BANK_CODE = "AAAAA";
    private static final String UPDATED_BANK_CODE = "BBBBB";
    private static final String DEFAULT_BANK_NAME = "AAAAA";
    private static final String UPDATED_BANK_NAME = "BBBBB";
    private static final String DEFAULT_BANK_BRANCH = "AAAAA";
    private static final String UPDATED_BANK_BRANCH = "BBBBB";
    private static final String DEFAULT_BANK_ACCOUNT = "AAAAA";
    private static final String UPDATED_BANK_ACCOUNT = "BBBBB";

    @Inject
    private BankMasterRepository bankMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBankMasterMockMvc;

    private BankMaster bankMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BankMasterResource bankMasterResource = new BankMasterResource();
        ReflectionTestUtils.setField(bankMasterResource, "bankMasterRepository", bankMasterRepository);
        this.restBankMasterMockMvc = MockMvcBuilders.standaloneSetup(bankMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        bankMaster = new BankMaster();
        bankMaster.setBankCode(DEFAULT_BANK_CODE);
        bankMaster.setBankName(DEFAULT_BANK_NAME);
        bankMaster.setBankBranch(DEFAULT_BANK_BRANCH);
        bankMaster.setBankAccount(DEFAULT_BANK_ACCOUNT);
    }

    @Test
    @Transactional
    public void createBankMaster() throws Exception {
        int databaseSizeBeforeCreate = bankMasterRepository.findAll().size();

        // Create the BankMaster

        restBankMasterMockMvc.perform(post("/api/bankMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bankMaster)))
                .andExpect(status().isCreated());

        // Validate the BankMaster in the database
        List<BankMaster> bankMasters = bankMasterRepository.findAll();
        assertThat(bankMasters).hasSize(databaseSizeBeforeCreate + 1);
        BankMaster testBankMaster = bankMasters.get(bankMasters.size() - 1);
        assertThat(testBankMaster.getBankCode()).isEqualTo(DEFAULT_BANK_CODE);
        assertThat(testBankMaster.getBankName()).isEqualTo(DEFAULT_BANK_NAME);
        assertThat(testBankMaster.getBankBranch()).isEqualTo(DEFAULT_BANK_BRANCH);
        assertThat(testBankMaster.getBankAccount()).isEqualTo(DEFAULT_BANK_ACCOUNT);
    }

    @Test
    @Transactional
    public void checkBankCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankMasterRepository.findAll().size();
        // set the field null
        bankMaster.setBankCode(null);

        // Create the BankMaster, which fails.

        restBankMasterMockMvc.perform(post("/api/bankMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bankMaster)))
                .andExpect(status().isBadRequest());

        List<BankMaster> bankMasters = bankMasterRepository.findAll();
        assertThat(bankMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBankNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = bankMasterRepository.findAll().size();
        // set the field null
        bankMaster.setBankName(null);

        // Create the BankMaster, which fails.

        restBankMasterMockMvc.perform(post("/api/bankMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bankMaster)))
                .andExpect(status().isBadRequest());

        List<BankMaster> bankMasters = bankMasterRepository.findAll();
        assertThat(bankMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBankMasters() throws Exception {
        // Initialize the database
        bankMasterRepository.saveAndFlush(bankMaster);

        // Get all the bankMasters
        restBankMasterMockMvc.perform(get("/api/bankMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(bankMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].bankCode").value(hasItem(DEFAULT_BANK_CODE.toString())))
                .andExpect(jsonPath("$.[*].bankName").value(hasItem(DEFAULT_BANK_NAME.toString())))
                .andExpect(jsonPath("$.[*].bankBranch").value(hasItem(DEFAULT_BANK_BRANCH.toString())))
                .andExpect(jsonPath("$.[*].bankAccount").value(hasItem(DEFAULT_BANK_ACCOUNT.toString())));
    }

    @Test
    @Transactional
    public void getBankMaster() throws Exception {
        // Initialize the database
        bankMasterRepository.saveAndFlush(bankMaster);

        // Get the bankMaster
        restBankMasterMockMvc.perform(get("/api/bankMasters/{id}", bankMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(bankMaster.getId().intValue()))
            .andExpect(jsonPath("$.bankCode").value(DEFAULT_BANK_CODE.toString()))
            .andExpect(jsonPath("$.bankName").value(DEFAULT_BANK_NAME.toString()))
            .andExpect(jsonPath("$.bankBranch").value(DEFAULT_BANK_BRANCH.toString()))
            .andExpect(jsonPath("$.bankAccount").value(DEFAULT_BANK_ACCOUNT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBankMaster() throws Exception {
        // Get the bankMaster
        restBankMasterMockMvc.perform(get("/api/bankMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBankMaster() throws Exception {
        // Initialize the database
        bankMasterRepository.saveAndFlush(bankMaster);

		int databaseSizeBeforeUpdate = bankMasterRepository.findAll().size();

        // Update the bankMaster
        bankMaster.setBankCode(UPDATED_BANK_CODE);
        bankMaster.setBankName(UPDATED_BANK_NAME);
        bankMaster.setBankBranch(UPDATED_BANK_BRANCH);
        bankMaster.setBankAccount(UPDATED_BANK_ACCOUNT);

        restBankMasterMockMvc.perform(put("/api/bankMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(bankMaster)))
                .andExpect(status().isOk());

        // Validate the BankMaster in the database
        List<BankMaster> bankMasters = bankMasterRepository.findAll();
        assertThat(bankMasters).hasSize(databaseSizeBeforeUpdate);
        BankMaster testBankMaster = bankMasters.get(bankMasters.size() - 1);
        assertThat(testBankMaster.getBankCode()).isEqualTo(UPDATED_BANK_CODE);
        assertThat(testBankMaster.getBankName()).isEqualTo(UPDATED_BANK_NAME);
        assertThat(testBankMaster.getBankBranch()).isEqualTo(UPDATED_BANK_BRANCH);
        assertThat(testBankMaster.getBankAccount()).isEqualTo(UPDATED_BANK_ACCOUNT);
    }

    @Test
    @Transactional
    public void deleteBankMaster() throws Exception {
        // Initialize the database
        bankMasterRepository.saveAndFlush(bankMaster);

		int databaseSizeBeforeDelete = bankMasterRepository.findAll().size();

        // Get the bankMaster
        restBankMasterMockMvc.perform(delete("/api/bankMasters/{id}", bankMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BankMaster> bankMasters = bankMasterRepository.findAll();
        assertThat(bankMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CashBookMaster;
import com.callippus.water.erp.repository.CashBookMasterRepository;

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
 * Test class for the CashBookMasterResource REST controller.
 *
 * @see CashBookMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CashBookMasterResourceIntTest {

    private static final String DEFAULT_CASH_BOOK_ENTRY_TYPE = "AAAAA";
    private static final String UPDATED_CASH_BOOK_ENTRY_TYPE = "BBBBB";

    @Inject
    private CashBookMasterRepository cashBookMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCashBookMasterMockMvc;

    private CashBookMaster cashBookMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CashBookMasterResource cashBookMasterResource = new CashBookMasterResource();
        ReflectionTestUtils.setField(cashBookMasterResource, "cashBookMasterRepository", cashBookMasterRepository);
        this.restCashBookMasterMockMvc = MockMvcBuilders.standaloneSetup(cashBookMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        cashBookMaster = new CashBookMaster();
        cashBookMaster.setCashBookEntryType(DEFAULT_CASH_BOOK_ENTRY_TYPE);
    }

    @Test
    @Transactional
    public void createCashBookMaster() throws Exception {
        int databaseSizeBeforeCreate = cashBookMasterRepository.findAll().size();

        // Create the CashBookMaster

        restCashBookMasterMockMvc.perform(post("/api/cashBookMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cashBookMaster)))
                .andExpect(status().isCreated());

        // Validate the CashBookMaster in the database
        List<CashBookMaster> cashBookMasters = cashBookMasterRepository.findAll();
        assertThat(cashBookMasters).hasSize(databaseSizeBeforeCreate + 1);
        CashBookMaster testCashBookMaster = cashBookMasters.get(cashBookMasters.size() - 1);
        assertThat(testCashBookMaster.getCashBookEntryType()).isEqualTo(DEFAULT_CASH_BOOK_ENTRY_TYPE);
    }

    @Test
    @Transactional
    public void checkCashBookEntryTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = cashBookMasterRepository.findAll().size();
        // set the field null
        cashBookMaster.setCashBookEntryType(null);

        // Create the CashBookMaster, which fails.

        restCashBookMasterMockMvc.perform(post("/api/cashBookMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cashBookMaster)))
                .andExpect(status().isBadRequest());

        List<CashBookMaster> cashBookMasters = cashBookMasterRepository.findAll();
        assertThat(cashBookMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCashBookMasters() throws Exception {
        // Initialize the database
        cashBookMasterRepository.saveAndFlush(cashBookMaster);

        // Get all the cashBookMasters
        restCashBookMasterMockMvc.perform(get("/api/cashBookMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(cashBookMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].cashBookEntryType").value(hasItem(DEFAULT_CASH_BOOK_ENTRY_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getCashBookMaster() throws Exception {
        // Initialize the database
        cashBookMasterRepository.saveAndFlush(cashBookMaster);

        // Get the cashBookMaster
        restCashBookMasterMockMvc.perform(get("/api/cashBookMasters/{id}", cashBookMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(cashBookMaster.getId().intValue()))
            .andExpect(jsonPath("$.cashBookEntryType").value(DEFAULT_CASH_BOOK_ENTRY_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCashBookMaster() throws Exception {
        // Get the cashBookMaster
        restCashBookMasterMockMvc.perform(get("/api/cashBookMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCashBookMaster() throws Exception {
        // Initialize the database
        cashBookMasterRepository.saveAndFlush(cashBookMaster);

		int databaseSizeBeforeUpdate = cashBookMasterRepository.findAll().size();

        // Update the cashBookMaster
        cashBookMaster.setCashBookEntryType(UPDATED_CASH_BOOK_ENTRY_TYPE);

        restCashBookMasterMockMvc.perform(put("/api/cashBookMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(cashBookMaster)))
                .andExpect(status().isOk());

        // Validate the CashBookMaster in the database
        List<CashBookMaster> cashBookMasters = cashBookMasterRepository.findAll();
        assertThat(cashBookMasters).hasSize(databaseSizeBeforeUpdate);
        CashBookMaster testCashBookMaster = cashBookMasters.get(cashBookMasters.size() - 1);
        assertThat(testCashBookMaster.getCashBookEntryType()).isEqualTo(UPDATED_CASH_BOOK_ENTRY_TYPE);
    }

    @Test
    @Transactional
    public void deleteCashBookMaster() throws Exception {
        // Initialize the database
        cashBookMasterRepository.saveAndFlush(cashBookMaster);

		int databaseSizeBeforeDelete = cashBookMasterRepository.findAll().size();

        // Get the cashBookMaster
        restCashBookMasterMockMvc.perform(delete("/api/cashBookMasters/{id}", cashBookMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CashBookMaster> cashBookMasters = cashBookMasterRepository.findAll();
        assertThat(cashBookMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

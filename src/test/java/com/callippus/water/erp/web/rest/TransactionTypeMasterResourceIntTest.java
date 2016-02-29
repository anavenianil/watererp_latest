package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.TransactionTypeMaster;
import com.callippus.water.erp.repository.TransactionTypeMasterRepository;

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
 * Test class for the TransactionTypeMasterResource REST controller.
 *
 * @see TransactionTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TransactionTypeMasterResourceIntTest {

    private static final String DEFAULT_TYPE_OF_TXN = "AAAAA";
    private static final String UPDATED_TYPE_OF_TXN = "BBBBB";

    @Inject
    private TransactionTypeMasterRepository transactionTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restTransactionTypeMasterMockMvc;

    private TransactionTypeMaster transactionTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TransactionTypeMasterResource transactionTypeMasterResource = new TransactionTypeMasterResource();
        ReflectionTestUtils.setField(transactionTypeMasterResource, "transactionTypeMasterRepository", transactionTypeMasterRepository);
        this.restTransactionTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(transactionTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        transactionTypeMaster = new TransactionTypeMaster();
        transactionTypeMaster.setTypeOfTxn(DEFAULT_TYPE_OF_TXN);
    }

    @Test
    @Transactional
    public void createTransactionTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = transactionTypeMasterRepository.findAll().size();

        // Create the TransactionTypeMaster

        restTransactionTypeMasterMockMvc.perform(post("/api/transactionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(transactionTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the TransactionTypeMaster in the database
        List<TransactionTypeMaster> transactionTypeMasters = transactionTypeMasterRepository.findAll();
        assertThat(transactionTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        TransactionTypeMaster testTransactionTypeMaster = transactionTypeMasters.get(transactionTypeMasters.size() - 1);
        assertThat(testTransactionTypeMaster.getTypeOfTxn()).isEqualTo(DEFAULT_TYPE_OF_TXN);
    }

    @Test
    @Transactional
    public void checkTypeOfTxnIsRequired() throws Exception {
        int databaseSizeBeforeTest = transactionTypeMasterRepository.findAll().size();
        // set the field null
        transactionTypeMaster.setTypeOfTxn(null);

        // Create the TransactionTypeMaster, which fails.

        restTransactionTypeMasterMockMvc.perform(post("/api/transactionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(transactionTypeMaster)))
                .andExpect(status().isBadRequest());

        List<TransactionTypeMaster> transactionTypeMasters = transactionTypeMasterRepository.findAll();
        assertThat(transactionTypeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTransactionTypeMasters() throws Exception {
        // Initialize the database
        transactionTypeMasterRepository.saveAndFlush(transactionTypeMaster);

        // Get all the transactionTypeMasters
        restTransactionTypeMasterMockMvc.perform(get("/api/transactionTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(transactionTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].typeOfTxn").value(hasItem(DEFAULT_TYPE_OF_TXN.toString())));
    }

    @Test
    @Transactional
    public void getTransactionTypeMaster() throws Exception {
        // Initialize the database
        transactionTypeMasterRepository.saveAndFlush(transactionTypeMaster);

        // Get the transactionTypeMaster
        restTransactionTypeMasterMockMvc.perform(get("/api/transactionTypeMasters/{id}", transactionTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(transactionTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.typeOfTxn").value(DEFAULT_TYPE_OF_TXN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTransactionTypeMaster() throws Exception {
        // Get the transactionTypeMaster
        restTransactionTypeMasterMockMvc.perform(get("/api/transactionTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTransactionTypeMaster() throws Exception {
        // Initialize the database
        transactionTypeMasterRepository.saveAndFlush(transactionTypeMaster);

		int databaseSizeBeforeUpdate = transactionTypeMasterRepository.findAll().size();

        // Update the transactionTypeMaster
        transactionTypeMaster.setTypeOfTxn(UPDATED_TYPE_OF_TXN);

        restTransactionTypeMasterMockMvc.perform(put("/api/transactionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(transactionTypeMaster)))
                .andExpect(status().isOk());

        // Validate the TransactionTypeMaster in the database
        List<TransactionTypeMaster> transactionTypeMasters = transactionTypeMasterRepository.findAll();
        assertThat(transactionTypeMasters).hasSize(databaseSizeBeforeUpdate);
        TransactionTypeMaster testTransactionTypeMaster = transactionTypeMasters.get(transactionTypeMasters.size() - 1);
        assertThat(testTransactionTypeMaster.getTypeOfTxn()).isEqualTo(UPDATED_TYPE_OF_TXN);
    }

    @Test
    @Transactional
    public void deleteTransactionTypeMaster() throws Exception {
        // Initialize the database
        transactionTypeMasterRepository.saveAndFlush(transactionTypeMaster);

		int databaseSizeBeforeDelete = transactionTypeMasterRepository.findAll().size();

        // Get the transactionTypeMaster
        restTransactionTypeMasterMockMvc.perform(delete("/api/transactionTypeMasters/{id}", transactionTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<TransactionTypeMaster> transactionTypeMasters = transactionTypeMasterRepository.findAll();
        assertThat(transactionTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

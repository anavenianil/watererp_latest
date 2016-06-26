package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CollectionTypeMaster;
import com.callippus.water.erp.repository.CollectionTypeMasterRepository;

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

import com.callippus.water.erp.domain.enumeration.TransactionType;

/**
 * Test class for the CollectionTypeMasterResource REST controller.
 *
 * @see CollectionTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CollectionTypeMasterResourceIntTest {

    private static final String DEFAULT_COLL_NAME = "AAAAA";
    private static final String UPDATED_COLL_NAME = "BBBBB";


    private static final TransactionType DEFAULT_TXN_TYPE = TransactionType.PAYMENT;
    private static final TransactionType UPDATED_TXN_TYPE = TransactionType.ADJUSTMENT;

    private static final Boolean DEFAULT_HAS_ACCOUNT_NO = false;
    private static final Boolean UPDATED_HAS_ACCOUNT_NO = true;
    private static final String DEFAULT_ACCOUNT_CODE = "AAAAA";
    private static final String UPDATED_ACCOUNT_CODE = "BBBBB";

    @Inject
    private CollectionTypeMasterRepository collectionTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCollectionTypeMasterMockMvc;

    private CollectionTypeMaster collectionTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CollectionTypeMasterResource collectionTypeMasterResource = new CollectionTypeMasterResource();
        ReflectionTestUtils.setField(collectionTypeMasterResource, "collectionTypeMasterRepository", collectionTypeMasterRepository);
        this.restCollectionTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(collectionTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        collectionTypeMaster = new CollectionTypeMaster();
        collectionTypeMaster.setCollName(DEFAULT_COLL_NAME);
        collectionTypeMaster.setTxnType(DEFAULT_TXN_TYPE);
        collectionTypeMaster.setHasAccountNo(DEFAULT_HAS_ACCOUNT_NO);
        collectionTypeMaster.setAccountCode(DEFAULT_ACCOUNT_CODE);
    }

    @Test
    @Transactional
    public void createCollectionTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = collectionTypeMasterRepository.findAll().size();

        // Create the CollectionTypeMaster

        restCollectionTypeMasterMockMvc.perform(post("/api/collectionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(collectionTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the CollectionTypeMaster in the database
        List<CollectionTypeMaster> collectionTypeMasters = collectionTypeMasterRepository.findAll();
        assertThat(collectionTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        CollectionTypeMaster testCollectionTypeMaster = collectionTypeMasters.get(collectionTypeMasters.size() - 1);
        assertThat(testCollectionTypeMaster.getCollName()).isEqualTo(DEFAULT_COLL_NAME);
        assertThat(testCollectionTypeMaster.getTxnType()).isEqualTo(DEFAULT_TXN_TYPE);
        assertThat(testCollectionTypeMaster.getHasAccountNo()).isEqualTo(DEFAULT_HAS_ACCOUNT_NO);
        assertThat(testCollectionTypeMaster.getAccountCode()).isEqualTo(DEFAULT_ACCOUNT_CODE);
    }

    @Test
    @Transactional
    public void checkTxnTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTypeMasterRepository.findAll().size();
        // set the field null
        collectionTypeMaster.setTxnType(null);

        // Create the CollectionTypeMaster, which fails.

        restCollectionTypeMasterMockMvc.perform(post("/api/collectionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(collectionTypeMaster)))
                .andExpect(status().isBadRequest());

        List<CollectionTypeMaster> collectionTypeMasters = collectionTypeMasterRepository.findAll();
        assertThat(collectionTypeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCollectionTypeMasters() throws Exception {
        // Initialize the database
        collectionTypeMasterRepository.saveAndFlush(collectionTypeMaster);

        // Get all the collectionTypeMasters
        restCollectionTypeMasterMockMvc.perform(get("/api/collectionTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(collectionTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].collName").value(hasItem(DEFAULT_COLL_NAME.toString())))
                .andExpect(jsonPath("$.[*].txnType").value(hasItem(DEFAULT_TXN_TYPE.toString())))
                .andExpect(jsonPath("$.[*].hasAccountNo").value(hasItem(DEFAULT_HAS_ACCOUNT_NO.booleanValue())))
                .andExpect(jsonPath("$.[*].accountCode").value(hasItem(DEFAULT_ACCOUNT_CODE.toString())));
    }

    @Test
    @Transactional
    public void getCollectionTypeMaster() throws Exception {
        // Initialize the database
        collectionTypeMasterRepository.saveAndFlush(collectionTypeMaster);

        // Get the collectionTypeMaster
        restCollectionTypeMasterMockMvc.perform(get("/api/collectionTypeMasters/{id}", collectionTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(collectionTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.collName").value(DEFAULT_COLL_NAME.toString()))
            .andExpect(jsonPath("$.txnType").value(DEFAULT_TXN_TYPE.toString()))
            .andExpect(jsonPath("$.hasAccountNo").value(DEFAULT_HAS_ACCOUNT_NO.booleanValue()))
            .andExpect(jsonPath("$.accountCode").value(DEFAULT_ACCOUNT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCollectionTypeMaster() throws Exception {
        // Get the collectionTypeMaster
        restCollectionTypeMasterMockMvc.perform(get("/api/collectionTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCollectionTypeMaster() throws Exception {
        // Initialize the database
        collectionTypeMasterRepository.saveAndFlush(collectionTypeMaster);

		int databaseSizeBeforeUpdate = collectionTypeMasterRepository.findAll().size();

        // Update the collectionTypeMaster
        collectionTypeMaster.setCollName(UPDATED_COLL_NAME);
        collectionTypeMaster.setTxnType(UPDATED_TXN_TYPE);
        collectionTypeMaster.setHasAccountNo(UPDATED_HAS_ACCOUNT_NO);
        collectionTypeMaster.setAccountCode(UPDATED_ACCOUNT_CODE);

        restCollectionTypeMasterMockMvc.perform(put("/api/collectionTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(collectionTypeMaster)))
                .andExpect(status().isOk());

        // Validate the CollectionTypeMaster in the database
        List<CollectionTypeMaster> collectionTypeMasters = collectionTypeMasterRepository.findAll();
        assertThat(collectionTypeMasters).hasSize(databaseSizeBeforeUpdate);
        CollectionTypeMaster testCollectionTypeMaster = collectionTypeMasters.get(collectionTypeMasters.size() - 1);
        assertThat(testCollectionTypeMaster.getCollName()).isEqualTo(UPDATED_COLL_NAME);
        assertThat(testCollectionTypeMaster.getTxnType()).isEqualTo(UPDATED_TXN_TYPE);
        assertThat(testCollectionTypeMaster.getHasAccountNo()).isEqualTo(UPDATED_HAS_ACCOUNT_NO);
        assertThat(testCollectionTypeMaster.getAccountCode()).isEqualTo(UPDATED_ACCOUNT_CODE);
    }

    @Test
    @Transactional
    public void deleteCollectionTypeMaster() throws Exception {
        // Initialize the database
        collectionTypeMasterRepository.saveAndFlush(collectionTypeMaster);

		int databaseSizeBeforeDelete = collectionTypeMasterRepository.findAll().size();

        // Get the collectionTypeMaster
        restCollectionTypeMasterMockMvc.perform(delete("/api/collectionTypeMasters/{id}", collectionTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CollectionTypeMaster> collectionTypeMasters = collectionTypeMasterRepository.findAll();
        assertThat(collectionTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

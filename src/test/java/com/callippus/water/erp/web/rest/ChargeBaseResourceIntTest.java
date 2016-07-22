package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ChargeBase;
import com.callippus.water.erp.repository.ChargeBaseRepository;

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
 * Test class for the ChargeBaseResource REST controller.
 *
 * @see ChargeBaseResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ChargeBaseResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAA";
    private static final String UPDATED_CODE = "BBBBB";
    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private ChargeBaseRepository chargeBaseRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restChargeBaseMockMvc;

    private ChargeBase chargeBase;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChargeBaseResource chargeBaseResource = new ChargeBaseResource();
        ReflectionTestUtils.setField(chargeBaseResource, "chargeBaseRepository", chargeBaseRepository);
        this.restChargeBaseMockMvc = MockMvcBuilders.standaloneSetup(chargeBaseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        chargeBase = new ChargeBase();
        chargeBase.setCode(DEFAULT_CODE);
        chargeBase.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createChargeBase() throws Exception {
        int databaseSizeBeforeCreate = chargeBaseRepository.findAll().size();

        // Create the ChargeBase

        restChargeBaseMockMvc.perform(post("/api/chargeBases")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(chargeBase)))
                .andExpect(status().isCreated());

        // Validate the ChargeBase in the database
        List<ChargeBase> chargeBases = chargeBaseRepository.findAll();
        assertThat(chargeBases).hasSize(databaseSizeBeforeCreate + 1);
        ChargeBase testChargeBase = chargeBases.get(chargeBases.size() - 1);
        assertThat(testChargeBase.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testChargeBase.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeBaseRepository.findAll().size();
        // set the field null
        chargeBase.setCode(null);

        // Create the ChargeBase, which fails.

        restChargeBaseMockMvc.perform(post("/api/chargeBases")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(chargeBase)))
                .andExpect(status().isBadRequest());

        List<ChargeBase> chargeBases = chargeBaseRepository.findAll();
        assertThat(chargeBases).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = chargeBaseRepository.findAll().size();
        // set the field null
        chargeBase.setName(null);

        // Create the ChargeBase, which fails.

        restChargeBaseMockMvc.perform(post("/api/chargeBases")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(chargeBase)))
                .andExpect(status().isBadRequest());

        List<ChargeBase> chargeBases = chargeBaseRepository.findAll();
        assertThat(chargeBases).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChargeBases() throws Exception {
        // Initialize the database
        chargeBaseRepository.saveAndFlush(chargeBase);

        // Get all the chargeBases
        restChargeBaseMockMvc.perform(get("/api/chargeBases?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(chargeBase.getId().intValue())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getChargeBase() throws Exception {
        // Initialize the database
        chargeBaseRepository.saveAndFlush(chargeBase);

        // Get the chargeBase
        restChargeBaseMockMvc.perform(get("/api/chargeBases/{id}", chargeBase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(chargeBase.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChargeBase() throws Exception {
        // Get the chargeBase
        restChargeBaseMockMvc.perform(get("/api/chargeBases/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChargeBase() throws Exception {
        // Initialize the database
        chargeBaseRepository.saveAndFlush(chargeBase);

		int databaseSizeBeforeUpdate = chargeBaseRepository.findAll().size();

        // Update the chargeBase
        chargeBase.setCode(UPDATED_CODE);
        chargeBase.setName(UPDATED_NAME);

        restChargeBaseMockMvc.perform(put("/api/chargeBases")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(chargeBase)))
                .andExpect(status().isOk());

        // Validate the ChargeBase in the database
        List<ChargeBase> chargeBases = chargeBaseRepository.findAll();
        assertThat(chargeBases).hasSize(databaseSizeBeforeUpdate);
        ChargeBase testChargeBase = chargeBases.get(chargeBases.size() - 1);
        assertThat(testChargeBase.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testChargeBase.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteChargeBase() throws Exception {
        // Initialize the database
        chargeBaseRepository.saveAndFlush(chargeBase);

		int databaseSizeBeforeDelete = chargeBaseRepository.findAll().size();

        // Get the chargeBase
        restChargeBaseMockMvc.perform(delete("/api/chargeBases/{id}", chargeBase.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ChargeBase> chargeBases = chargeBaseRepository.findAll();
        assertThat(chargeBases).hasSize(databaseSizeBeforeDelete - 1);
    }
}

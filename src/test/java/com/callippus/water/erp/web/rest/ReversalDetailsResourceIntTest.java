package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ReversalDetails;
import com.callippus.water.erp.repository.ReversalDetailsRepository;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the ReversalDetailsResource REST controller.
 *
 * @see ReversalDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReversalDetailsResourceIntTest {


    private static final LocalDate DEFAULT_CANCELLED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CANCELLED_DATE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_REMARKS = "AAAAA";
    private static final String UPDATED_REMARKS = "BBBBB";

    @Inject
    private ReversalDetailsRepository reversalDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReversalDetailsMockMvc;

    private ReversalDetails reversalDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReversalDetailsResource reversalDetailsResource = new ReversalDetailsResource();
        ReflectionTestUtils.setField(reversalDetailsResource, "reversalDetailsRepository", reversalDetailsRepository);
        this.restReversalDetailsMockMvc = MockMvcBuilders.standaloneSetup(reversalDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        reversalDetails = new ReversalDetails();
        reversalDetails.setCancelledDate(DEFAULT_CANCELLED_DATE);
        reversalDetails.setRemarks(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createReversalDetails() throws Exception {
        int databaseSizeBeforeCreate = reversalDetailsRepository.findAll().size();

        // Create the ReversalDetails

        restReversalDetailsMockMvc.perform(post("/api/reversalDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reversalDetails)))
                .andExpect(status().isCreated());

        // Validate the ReversalDetails in the database
        List<ReversalDetails> reversalDetailss = reversalDetailsRepository.findAll();
        assertThat(reversalDetailss).hasSize(databaseSizeBeforeCreate + 1);
        ReversalDetails testReversalDetails = reversalDetailss.get(reversalDetailss.size() - 1);
        assertThat(testReversalDetails.getCancelledDate()).isEqualTo(DEFAULT_CANCELLED_DATE);
        assertThat(testReversalDetails.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void checkCancelledDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = reversalDetailsRepository.findAll().size();
        // set the field null
        reversalDetails.setCancelledDate(null);

        // Create the ReversalDetails, which fails.

        restReversalDetailsMockMvc.perform(post("/api/reversalDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reversalDetails)))
                .andExpect(status().isBadRequest());

        List<ReversalDetails> reversalDetailss = reversalDetailsRepository.findAll();
        assertThat(reversalDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRemarksIsRequired() throws Exception {
        int databaseSizeBeforeTest = reversalDetailsRepository.findAll().size();
        // set the field null
        reversalDetails.setRemarks(null);

        // Create the ReversalDetails, which fails.

        restReversalDetailsMockMvc.perform(post("/api/reversalDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reversalDetails)))
                .andExpect(status().isBadRequest());

        List<ReversalDetails> reversalDetailss = reversalDetailsRepository.findAll();
        assertThat(reversalDetailss).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllReversalDetailss() throws Exception {
        // Initialize the database
        reversalDetailsRepository.saveAndFlush(reversalDetails);

        // Get all the reversalDetailss
        restReversalDetailsMockMvc.perform(get("/api/reversalDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(reversalDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].cancelledDate").value(hasItem(DEFAULT_CANCELLED_DATE.toString())))
                .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())));
    }

    @Test
    @Transactional
    public void getReversalDetails() throws Exception {
        // Initialize the database
        reversalDetailsRepository.saveAndFlush(reversalDetails);

        // Get the reversalDetails
        restReversalDetailsMockMvc.perform(get("/api/reversalDetailss/{id}", reversalDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(reversalDetails.getId().intValue()))
            .andExpect(jsonPath("$.cancelledDate").value(DEFAULT_CANCELLED_DATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingReversalDetails() throws Exception {
        // Get the reversalDetails
        restReversalDetailsMockMvc.perform(get("/api/reversalDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReversalDetails() throws Exception {
        // Initialize the database
        reversalDetailsRepository.saveAndFlush(reversalDetails);

		int databaseSizeBeforeUpdate = reversalDetailsRepository.findAll().size();

        // Update the reversalDetails
        reversalDetails.setCancelledDate(UPDATED_CANCELLED_DATE);
        reversalDetails.setRemarks(UPDATED_REMARKS);

        restReversalDetailsMockMvc.perform(put("/api/reversalDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reversalDetails)))
                .andExpect(status().isOk());

        // Validate the ReversalDetails in the database
        List<ReversalDetails> reversalDetailss = reversalDetailsRepository.findAll();
        assertThat(reversalDetailss).hasSize(databaseSizeBeforeUpdate);
        ReversalDetails testReversalDetails = reversalDetailss.get(reversalDetailss.size() - 1);
        assertThat(testReversalDetails.getCancelledDate()).isEqualTo(UPDATED_CANCELLED_DATE);
        assertThat(testReversalDetails.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void deleteReversalDetails() throws Exception {
        // Initialize the database
        reversalDetailsRepository.saveAndFlush(reversalDetails);

		int databaseSizeBeforeDelete = reversalDetailsRepository.findAll().size();

        // Get the reversalDetails
        restReversalDetailsMockMvc.perform(delete("/api/reversalDetailss/{id}", reversalDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ReversalDetails> reversalDetailss = reversalDetailsRepository.findAll();
        assertThat(reversalDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

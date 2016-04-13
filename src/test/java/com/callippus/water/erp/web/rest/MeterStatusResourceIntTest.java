package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MeterStatus;
import com.callippus.water.erp.repository.MeterStatusRepository;

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
 * Test class for the MeterStatusResource REST controller.
 *
 * @see MeterStatusResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MeterStatusResourceIntTest {

    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";

    @Inject
    private MeterStatusRepository meterStatusRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMeterStatusMockMvc;

    private MeterStatus meterStatus;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MeterStatusResource meterStatusResource = new MeterStatusResource();
        ReflectionTestUtils.setField(meterStatusResource, "meterStatusRepository", meterStatusRepository);
        this.restMeterStatusMockMvc = MockMvcBuilders.standaloneSetup(meterStatusResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        meterStatus = new MeterStatus();
        meterStatus.setStatus(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createMeterStatus() throws Exception {
        int databaseSizeBeforeCreate = meterStatusRepository.findAll().size();

        // Create the MeterStatus

        restMeterStatusMockMvc.perform(post("/api/meterStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(meterStatus)))
                .andExpect(status().isCreated());

        // Validate the MeterStatus in the database
        List<MeterStatus> meterStatuss = meterStatusRepository.findAll();
        assertThat(meterStatuss).hasSize(databaseSizeBeforeCreate + 1);
        MeterStatus testMeterStatus = meterStatuss.get(meterStatuss.size() - 1);
        assertThat(testMeterStatus.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void getAllMeterStatuss() throws Exception {
        // Initialize the database
        meterStatusRepository.saveAndFlush(meterStatus);

        // Get all the meterStatuss
        restMeterStatusMockMvc.perform(get("/api/meterStatuss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(meterStatus.getId().intValue())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void getMeterStatus() throws Exception {
        // Initialize the database
        meterStatusRepository.saveAndFlush(meterStatus);

        // Get the meterStatus
        restMeterStatusMockMvc.perform(get("/api/meterStatuss/{id}", meterStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(meterStatus.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMeterStatus() throws Exception {
        // Get the meterStatus
        restMeterStatusMockMvc.perform(get("/api/meterStatuss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMeterStatus() throws Exception {
        // Initialize the database
        meterStatusRepository.saveAndFlush(meterStatus);

		int databaseSizeBeforeUpdate = meterStatusRepository.findAll().size();

        // Update the meterStatus
        meterStatus.setStatus(UPDATED_STATUS);

        restMeterStatusMockMvc.perform(put("/api/meterStatuss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(meterStatus)))
                .andExpect(status().isOk());

        // Validate the MeterStatus in the database
        List<MeterStatus> meterStatuss = meterStatusRepository.findAll();
        assertThat(meterStatuss).hasSize(databaseSizeBeforeUpdate);
        MeterStatus testMeterStatus = meterStatuss.get(meterStatuss.size() - 1);
        assertThat(testMeterStatus.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void deleteMeterStatus() throws Exception {
        // Initialize the database
        meterStatusRepository.saveAndFlush(meterStatus);

		int databaseSizeBeforeDelete = meterStatusRepository.findAll().size();

        // Get the meterStatus
        restMeterStatusMockMvc.perform(delete("/api/meterStatuss/{id}", meterStatus.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MeterStatus> meterStatuss = meterStatusRepository.findAll();
        assertThat(meterStatuss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

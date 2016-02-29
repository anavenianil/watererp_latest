package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.StatusMaster;
import com.callippus.water.erp.repository.StatusMasterRepository;

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
 * Test class for the StatusMasterResource REST controller.
 *
 * @see StatusMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class StatusMasterResourceIntTest {

    private static final String DEFAULT_STATUS = "AAAAA";
    private static final String UPDATED_STATUS = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private StatusMasterRepository statusMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restStatusMasterMockMvc;

    private StatusMaster statusMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        StatusMasterResource statusMasterResource = new StatusMasterResource();
        ReflectionTestUtils.setField(statusMasterResource, "statusMasterRepository", statusMasterRepository);
        this.restStatusMasterMockMvc = MockMvcBuilders.standaloneSetup(statusMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        statusMaster = new StatusMaster();
        statusMaster.setStatus(DEFAULT_STATUS);
        statusMaster.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createStatusMaster() throws Exception {
        int databaseSizeBeforeCreate = statusMasterRepository.findAll().size();

        // Create the StatusMaster

        restStatusMasterMockMvc.perform(post("/api/statusMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(statusMaster)))
                .andExpect(status().isCreated());

        // Validate the StatusMaster in the database
        List<StatusMaster> statusMasters = statusMasterRepository.findAll();
        assertThat(statusMasters).hasSize(databaseSizeBeforeCreate + 1);
        StatusMaster testStatusMaster = statusMasters.get(statusMasters.size() - 1);
        assertThat(testStatusMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testStatusMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllStatusMasters() throws Exception {
        // Initialize the database
        statusMasterRepository.saveAndFlush(statusMaster);

        // Get all the statusMasters
        restStatusMasterMockMvc.perform(get("/api/statusMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(statusMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getStatusMaster() throws Exception {
        // Initialize the database
        statusMasterRepository.saveAndFlush(statusMaster);

        // Get the statusMaster
        restStatusMasterMockMvc.perform(get("/api/statusMasters/{id}", statusMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(statusMaster.getId().intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingStatusMaster() throws Exception {
        // Get the statusMaster
        restStatusMasterMockMvc.perform(get("/api/statusMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStatusMaster() throws Exception {
        // Initialize the database
        statusMasterRepository.saveAndFlush(statusMaster);

		int databaseSizeBeforeUpdate = statusMasterRepository.findAll().size();

        // Update the statusMaster
        statusMaster.setStatus(UPDATED_STATUS);
        statusMaster.setDescription(UPDATED_DESCRIPTION);

        restStatusMasterMockMvc.perform(put("/api/statusMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(statusMaster)))
                .andExpect(status().isOk());

        // Validate the StatusMaster in the database
        List<StatusMaster> statusMasters = statusMasterRepository.findAll();
        assertThat(statusMasters).hasSize(databaseSizeBeforeUpdate);
        StatusMaster testStatusMaster = statusMasters.get(statusMasters.size() - 1);
        assertThat(testStatusMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testStatusMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteStatusMaster() throws Exception {
        // Initialize the database
        statusMasterRepository.saveAndFlush(statusMaster);

		int databaseSizeBeforeDelete = statusMasterRepository.findAll().size();

        // Get the statusMaster
        restStatusMasterMockMvc.perform(delete("/api/statusMasters/{id}", statusMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<StatusMaster> statusMasters = statusMasterRepository.findAll();
        assertThat(statusMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ComplaintTypeMaster;
import com.callippus.water.erp.repository.ComplaintTypeMasterRepository;

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
 * Test class for the ComplaintTypeMasterResource REST controller.
 *
 * @see ComplaintTypeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ComplaintTypeMasterResourceIntTest {

    private static final String DEFAULT_COMPLAINT_TYPE = "AAAAA";
    private static final String UPDATED_COMPLAINT_TYPE = "BBBBB";

    @Inject
    private ComplaintTypeMasterRepository complaintTypeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restComplaintTypeMasterMockMvc;

    private ComplaintTypeMaster complaintTypeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ComplaintTypeMasterResource complaintTypeMasterResource = new ComplaintTypeMasterResource();
        ReflectionTestUtils.setField(complaintTypeMasterResource, "complaintTypeMasterRepository", complaintTypeMasterRepository);
        this.restComplaintTypeMasterMockMvc = MockMvcBuilders.standaloneSetup(complaintTypeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        complaintTypeMaster = new ComplaintTypeMaster();
        complaintTypeMaster.setComplaintType(DEFAULT_COMPLAINT_TYPE);
    }

    @Test
    @Transactional
    public void createComplaintTypeMaster() throws Exception {
        int databaseSizeBeforeCreate = complaintTypeMasterRepository.findAll().size();

        // Create the ComplaintTypeMaster

        restComplaintTypeMasterMockMvc.perform(post("/api/complaintTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(complaintTypeMaster)))
                .andExpect(status().isCreated());

        // Validate the ComplaintTypeMaster in the database
        List<ComplaintTypeMaster> complaintTypeMasters = complaintTypeMasterRepository.findAll();
        assertThat(complaintTypeMasters).hasSize(databaseSizeBeforeCreate + 1);
        ComplaintTypeMaster testComplaintTypeMaster = complaintTypeMasters.get(complaintTypeMasters.size() - 1);
        assertThat(testComplaintTypeMaster.getComplaintType()).isEqualTo(DEFAULT_COMPLAINT_TYPE);
    }

    @Test
    @Transactional
    public void getAllComplaintTypeMasters() throws Exception {
        // Initialize the database
        complaintTypeMasterRepository.saveAndFlush(complaintTypeMaster);

        // Get all the complaintTypeMasters
        restComplaintTypeMasterMockMvc.perform(get("/api/complaintTypeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(complaintTypeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].complaintType").value(hasItem(DEFAULT_COMPLAINT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getComplaintTypeMaster() throws Exception {
        // Initialize the database
        complaintTypeMasterRepository.saveAndFlush(complaintTypeMaster);

        // Get the complaintTypeMaster
        restComplaintTypeMasterMockMvc.perform(get("/api/complaintTypeMasters/{id}", complaintTypeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(complaintTypeMaster.getId().intValue()))
            .andExpect(jsonPath("$.complaintType").value(DEFAULT_COMPLAINT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingComplaintTypeMaster() throws Exception {
        // Get the complaintTypeMaster
        restComplaintTypeMasterMockMvc.perform(get("/api/complaintTypeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateComplaintTypeMaster() throws Exception {
        // Initialize the database
        complaintTypeMasterRepository.saveAndFlush(complaintTypeMaster);

		int databaseSizeBeforeUpdate = complaintTypeMasterRepository.findAll().size();

        // Update the complaintTypeMaster
        complaintTypeMaster.setComplaintType(UPDATED_COMPLAINT_TYPE);

        restComplaintTypeMasterMockMvc.perform(put("/api/complaintTypeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(complaintTypeMaster)))
                .andExpect(status().isOk());

        // Validate the ComplaintTypeMaster in the database
        List<ComplaintTypeMaster> complaintTypeMasters = complaintTypeMasterRepository.findAll();
        assertThat(complaintTypeMasters).hasSize(databaseSizeBeforeUpdate);
        ComplaintTypeMaster testComplaintTypeMaster = complaintTypeMasters.get(complaintTypeMasters.size() - 1);
        assertThat(testComplaintTypeMaster.getComplaintType()).isEqualTo(UPDATED_COMPLAINT_TYPE);
    }

    @Test
    @Transactional
    public void deleteComplaintTypeMaster() throws Exception {
        // Initialize the database
        complaintTypeMasterRepository.saveAndFlush(complaintTypeMaster);

		int databaseSizeBeforeDelete = complaintTypeMasterRepository.findAll().size();

        // Get the complaintTypeMaster
        restComplaintTypeMasterMockMvc.perform(delete("/api/complaintTypeMasters/{id}", complaintTypeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ComplaintTypeMaster> complaintTypeMasters = complaintTypeMasterRepository.findAll();
        assertThat(complaintTypeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

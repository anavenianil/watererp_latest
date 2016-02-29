package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ReAllotment;
import com.callippus.water.erp.repository.ReAllotmentRepository;

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
 * Test class for the ReAllotmentResource REST controller.
 *
 * @see ReAllotmentResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ReAllotmentResourceIntTest {


    @Inject
    private ReAllotmentRepository reAllotmentRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restReAllotmentMockMvc;

    private ReAllotment reAllotment;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReAllotmentResource reAllotmentResource = new ReAllotmentResource();
        ReflectionTestUtils.setField(reAllotmentResource, "reAllotmentRepository", reAllotmentRepository);
        this.restReAllotmentMockMvc = MockMvcBuilders.standaloneSetup(reAllotmentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        reAllotment = new ReAllotment();
    }

    @Test
    @Transactional
    public void createReAllotment() throws Exception {
        int databaseSizeBeforeCreate = reAllotmentRepository.findAll().size();

        // Create the ReAllotment

        restReAllotmentMockMvc.perform(post("/api/reAllotments")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reAllotment)))
                .andExpect(status().isCreated());

        // Validate the ReAllotment in the database
        List<ReAllotment> reAllotments = reAllotmentRepository.findAll();
        assertThat(reAllotments).hasSize(databaseSizeBeforeCreate + 1);
        ReAllotment testReAllotment = reAllotments.get(reAllotments.size() - 1);
    }

    @Test
    @Transactional
    public void getAllReAllotments() throws Exception {
        // Initialize the database
        reAllotmentRepository.saveAndFlush(reAllotment);

        // Get all the reAllotments
        restReAllotmentMockMvc.perform(get("/api/reAllotments?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(reAllotment.getId().intValue())));
    }

    @Test
    @Transactional
    public void getReAllotment() throws Exception {
        // Initialize the database
        reAllotmentRepository.saveAndFlush(reAllotment);

        // Get the reAllotment
        restReAllotmentMockMvc.perform(get("/api/reAllotments/{id}", reAllotment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(reAllotment.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReAllotment() throws Exception {
        // Get the reAllotment
        restReAllotmentMockMvc.perform(get("/api/reAllotments/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReAllotment() throws Exception {
        // Initialize the database
        reAllotmentRepository.saveAndFlush(reAllotment);

		int databaseSizeBeforeUpdate = reAllotmentRepository.findAll().size();

        // Update the reAllotment

        restReAllotmentMockMvc.perform(put("/api/reAllotments")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reAllotment)))
                .andExpect(status().isOk());

        // Validate the ReAllotment in the database
        List<ReAllotment> reAllotments = reAllotmentRepository.findAll();
        assertThat(reAllotments).hasSize(databaseSizeBeforeUpdate);
        ReAllotment testReAllotment = reAllotments.get(reAllotments.size() - 1);
    }

    @Test
    @Transactional
    public void deleteReAllotment() throws Exception {
        // Initialize the database
        reAllotmentRepository.saveAndFlush(reAllotment);

		int databaseSizeBeforeDelete = reAllotmentRepository.findAll().size();

        // Get the reAllotment
        restReAllotmentMockMvc.perform(delete("/api/reAllotments/{id}", reAllotment.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ReAllotment> reAllotments = reAllotmentRepository.findAll();
        assertThat(reAllotments).hasSize(databaseSizeBeforeDelete - 1);
    }
}

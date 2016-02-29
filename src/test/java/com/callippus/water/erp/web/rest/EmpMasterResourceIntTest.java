package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.EmpMaster;
import com.callippus.water.erp.repository.EmpMasterRepository;

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
 * Test class for the EmpMasterResource REST controller.
 *
 * @see EmpMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EmpMasterResourceIntTest {


    @Inject
    private EmpMasterRepository empMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restEmpMasterMockMvc;

    private EmpMaster empMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EmpMasterResource empMasterResource = new EmpMasterResource();
        ReflectionTestUtils.setField(empMasterResource, "empMasterRepository", empMasterRepository);
        this.restEmpMasterMockMvc = MockMvcBuilders.standaloneSetup(empMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        empMaster = new EmpMaster();
    }

    @Test
    @Transactional
    public void createEmpMaster() throws Exception {
        int databaseSizeBeforeCreate = empMasterRepository.findAll().size();

        // Create the EmpMaster

        restEmpMasterMockMvc.perform(post("/api/empMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(empMaster)))
                .andExpect(status().isCreated());

        // Validate the EmpMaster in the database
        List<EmpMaster> empMasters = empMasterRepository.findAll();
        assertThat(empMasters).hasSize(databaseSizeBeforeCreate + 1);
        EmpMaster testEmpMaster = empMasters.get(empMasters.size() - 1);
    }

    @Test
    @Transactional
    public void getAllEmpMasters() throws Exception {
        // Initialize the database
        empMasterRepository.saveAndFlush(empMaster);

        // Get all the empMasters
        restEmpMasterMockMvc.perform(get("/api/empMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(empMaster.getId().intValue())));
    }

    @Test
    @Transactional
    public void getEmpMaster() throws Exception {
        // Initialize the database
        empMasterRepository.saveAndFlush(empMaster);

        // Get the empMaster
        restEmpMasterMockMvc.perform(get("/api/empMasters/{id}", empMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(empMaster.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingEmpMaster() throws Exception {
        // Get the empMaster
        restEmpMasterMockMvc.perform(get("/api/empMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmpMaster() throws Exception {
        // Initialize the database
        empMasterRepository.saveAndFlush(empMaster);

		int databaseSizeBeforeUpdate = empMasterRepository.findAll().size();

        // Update the empMaster

        restEmpMasterMockMvc.perform(put("/api/empMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(empMaster)))
                .andExpect(status().isOk());

        // Validate the EmpMaster in the database
        List<EmpMaster> empMasters = empMasterRepository.findAll();
        assertThat(empMasters).hasSize(databaseSizeBeforeUpdate);
        EmpMaster testEmpMaster = empMasters.get(empMasters.size() - 1);
    }

    @Test
    @Transactional
    public void deleteEmpMaster() throws Exception {
        // Initialize the database
        empMasterRepository.saveAndFlush(empMaster);

		int databaseSizeBeforeDelete = empMasterRepository.findAll().size();

        // Get the empMaster
        restEmpMasterMockMvc.perform(delete("/api/empMasters/{id}", empMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<EmpMaster> empMasters = empMasterRepository.findAll();
        assertThat(empMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.CtegoryMaster;
import com.callippus.water.erp.repository.CtegoryMasterRepository;

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
 * Test class for the CtegoryMasterResource REST controller.
 *
 * @see CtegoryMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class CtegoryMasterResourceIntTest {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBB";

    @Inject
    private CtegoryMasterRepository ctegoryMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restCtegoryMasterMockMvc;

    private CtegoryMaster ctegoryMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CtegoryMasterResource ctegoryMasterResource = new CtegoryMasterResource();
        ReflectionTestUtils.setField(ctegoryMasterResource, "ctegoryMasterRepository", ctegoryMasterRepository);
        this.restCtegoryMasterMockMvc = MockMvcBuilders.standaloneSetup(ctegoryMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        ctegoryMaster = new CtegoryMaster();
        ctegoryMaster.setCategoryName(DEFAULT_CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void createCtegoryMaster() throws Exception {
        int databaseSizeBeforeCreate = ctegoryMasterRepository.findAll().size();

        // Create the CtegoryMaster

        restCtegoryMasterMockMvc.perform(post("/api/ctegoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ctegoryMaster)))
                .andExpect(status().isCreated());

        // Validate the CtegoryMaster in the database
        List<CtegoryMaster> ctegoryMasters = ctegoryMasterRepository.findAll();
        assertThat(ctegoryMasters).hasSize(databaseSizeBeforeCreate + 1);
        CtegoryMaster testCtegoryMaster = ctegoryMasters.get(ctegoryMasters.size() - 1);
        assertThat(testCtegoryMaster.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void getAllCtegoryMasters() throws Exception {
        // Initialize the database
        ctegoryMasterRepository.saveAndFlush(ctegoryMaster);

        // Get all the ctegoryMasters
        restCtegoryMasterMockMvc.perform(get("/api/ctegoryMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(ctegoryMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())));
    }

    @Test
    @Transactional
    public void getCtegoryMaster() throws Exception {
        // Initialize the database
        ctegoryMasterRepository.saveAndFlush(ctegoryMaster);

        // Get the ctegoryMaster
        restCtegoryMasterMockMvc.perform(get("/api/ctegoryMasters/{id}", ctegoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(ctegoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCtegoryMaster() throws Exception {
        // Get the ctegoryMaster
        restCtegoryMasterMockMvc.perform(get("/api/ctegoryMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCtegoryMaster() throws Exception {
        // Initialize the database
        ctegoryMasterRepository.saveAndFlush(ctegoryMaster);

		int databaseSizeBeforeUpdate = ctegoryMasterRepository.findAll().size();

        // Update the ctegoryMaster
        ctegoryMaster.setCategoryName(UPDATED_CATEGORY_NAME);

        restCtegoryMasterMockMvc.perform(put("/api/ctegoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ctegoryMaster)))
                .andExpect(status().isOk());

        // Validate the CtegoryMaster in the database
        List<CtegoryMaster> ctegoryMasters = ctegoryMasterRepository.findAll();
        assertThat(ctegoryMasters).hasSize(databaseSizeBeforeUpdate);
        CtegoryMaster testCtegoryMaster = ctegoryMasters.get(ctegoryMasters.size() - 1);
        assertThat(testCtegoryMaster.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
    }

    @Test
    @Transactional
    public void deleteCtegoryMaster() throws Exception {
        // Initialize the database
        ctegoryMasterRepository.saveAndFlush(ctegoryMaster);

		int databaseSizeBeforeDelete = ctegoryMasterRepository.findAll().size();

        // Get the ctegoryMaster
        restCtegoryMasterMockMvc.perform(delete("/api/ctegoryMasters/{id}", ctegoryMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<CtegoryMaster> ctegoryMasters = ctegoryMasterRepository.findAll();
        assertThat(ctegoryMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

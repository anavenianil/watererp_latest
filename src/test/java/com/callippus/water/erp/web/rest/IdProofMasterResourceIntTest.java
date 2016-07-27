package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.IdProofMaster;
import com.callippus.water.erp.repository.IdProofMasterRepository;

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
 * Test class for the IdProofMasterResource REST controller.
 *
 * @see IdProofMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class IdProofMasterResourceIntTest {

    private static final String DEFAULT_ID_PROOF = "AAAAA";
    private static final String UPDATED_ID_PROOF = "BBBBB";

    @Inject
    private IdProofMasterRepository idProofMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restIdProofMasterMockMvc;

    private IdProofMaster idProofMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        IdProofMasterResource idProofMasterResource = new IdProofMasterResource();
        ReflectionTestUtils.setField(idProofMasterResource, "idProofMasterRepository", idProofMasterRepository);
        this.restIdProofMasterMockMvc = MockMvcBuilders.standaloneSetup(idProofMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        idProofMaster = new IdProofMaster();
        idProofMaster.setIdProof(DEFAULT_ID_PROOF);
    }

    @Test
    @Transactional
    public void createIdProofMaster() throws Exception {
        int databaseSizeBeforeCreate = idProofMasterRepository.findAll().size();

        // Create the IdProofMaster

        restIdProofMasterMockMvc.perform(post("/api/idProofMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(idProofMaster)))
                .andExpect(status().isCreated());

        // Validate the IdProofMaster in the database
        List<IdProofMaster> idProofMasters = idProofMasterRepository.findAll();
        assertThat(idProofMasters).hasSize(databaseSizeBeforeCreate + 1);
        IdProofMaster testIdProofMaster = idProofMasters.get(idProofMasters.size() - 1);
        assertThat(testIdProofMaster.getIdProof()).isEqualTo(DEFAULT_ID_PROOF);
    }

    @Test
    @Transactional
    public void getAllIdProofMasters() throws Exception {
        // Initialize the database
        idProofMasterRepository.saveAndFlush(idProofMaster);

        // Get all the idProofMasters
        restIdProofMasterMockMvc.perform(get("/api/idProofMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(idProofMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].idProof").value(hasItem(DEFAULT_ID_PROOF.toString())));
    }

    @Test
    @Transactional
    public void getIdProofMaster() throws Exception {
        // Initialize the database
        idProofMasterRepository.saveAndFlush(idProofMaster);

        // Get the idProofMaster
        restIdProofMasterMockMvc.perform(get("/api/idProofMasters/{id}", idProofMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(idProofMaster.getId().intValue()))
            .andExpect(jsonPath("$.idProof").value(DEFAULT_ID_PROOF.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIdProofMaster() throws Exception {
        // Get the idProofMaster
        restIdProofMasterMockMvc.perform(get("/api/idProofMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIdProofMaster() throws Exception {
        // Initialize the database
        idProofMasterRepository.saveAndFlush(idProofMaster);

		int databaseSizeBeforeUpdate = idProofMasterRepository.findAll().size();

        // Update the idProofMaster
        idProofMaster.setIdProof(UPDATED_ID_PROOF);

        restIdProofMasterMockMvc.perform(put("/api/idProofMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(idProofMaster)))
                .andExpect(status().isOk());

        // Validate the IdProofMaster in the database
        List<IdProofMaster> idProofMasters = idProofMasterRepository.findAll();
        assertThat(idProofMasters).hasSize(databaseSizeBeforeUpdate);
        IdProofMaster testIdProofMaster = idProofMasters.get(idProofMasters.size() - 1);
        assertThat(testIdProofMaster.getIdProof()).isEqualTo(UPDATED_ID_PROOF);
    }

    @Test
    @Transactional
    public void deleteIdProofMaster() throws Exception {
        // Initialize the database
        idProofMasterRepository.saveAndFlush(idProofMaster);

		int databaseSizeBeforeDelete = idProofMasterRepository.findAll().size();

        // Get the idProofMaster
        restIdProofMasterMockMvc.perform(delete("/api/idProofMasters/{id}", idProofMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<IdProofMaster> idProofMasters = idProofMasterRepository.findAll();
        assertThat(idProofMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

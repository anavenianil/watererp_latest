package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.SewerageChargeMaster;
import com.callippus.water.erp.repository.SewerageChargeMasterRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the SewerageChargeMasterResource REST controller.
 *
 * @see SewerageChargeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class SewerageChargeMasterResourceIntTest {


    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    @Inject
    private SewerageChargeMasterRepository sewerageChargeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restSewerageChargeMasterMockMvc;

    private SewerageChargeMaster sewerageChargeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SewerageChargeMasterResource sewerageChargeMasterResource = new SewerageChargeMasterResource();
        ReflectionTestUtils.setField(sewerageChargeMasterResource, "sewerageChargeMasterRepository", sewerageChargeMasterRepository);
        this.restSewerageChargeMasterMockMvc = MockMvcBuilders.standaloneSetup(sewerageChargeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        sewerageChargeMaster = new SewerageChargeMaster();
        sewerageChargeMaster.setAmount(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createSewerageChargeMaster() throws Exception {
        int databaseSizeBeforeCreate = sewerageChargeMasterRepository.findAll().size();

        // Create the SewerageChargeMaster

        restSewerageChargeMasterMockMvc.perform(post("/api/sewerageChargeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerageChargeMaster)))
                .andExpect(status().isCreated());

        // Validate the SewerageChargeMaster in the database
        List<SewerageChargeMaster> sewerageChargeMasters = sewerageChargeMasterRepository.findAll();
        assertThat(sewerageChargeMasters).hasSize(databaseSizeBeforeCreate + 1);
        SewerageChargeMaster testSewerageChargeMaster = sewerageChargeMasters.get(sewerageChargeMasters.size() - 1);
        assertThat(testSewerageChargeMaster.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllSewerageChargeMasters() throws Exception {
        // Initialize the database
        sewerageChargeMasterRepository.saveAndFlush(sewerageChargeMaster);

        // Get all the sewerageChargeMasters
        restSewerageChargeMasterMockMvc.perform(get("/api/sewerageChargeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(sewerageChargeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void getSewerageChargeMaster() throws Exception {
        // Initialize the database
        sewerageChargeMasterRepository.saveAndFlush(sewerageChargeMaster);

        // Get the sewerageChargeMaster
        restSewerageChargeMasterMockMvc.perform(get("/api/sewerageChargeMasters/{id}", sewerageChargeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(sewerageChargeMaster.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSewerageChargeMaster() throws Exception {
        // Get the sewerageChargeMaster
        restSewerageChargeMasterMockMvc.perform(get("/api/sewerageChargeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSewerageChargeMaster() throws Exception {
        // Initialize the database
        sewerageChargeMasterRepository.saveAndFlush(sewerageChargeMaster);

		int databaseSizeBeforeUpdate = sewerageChargeMasterRepository.findAll().size();

        // Update the sewerageChargeMaster
        sewerageChargeMaster.setAmount(UPDATED_AMOUNT);

        restSewerageChargeMasterMockMvc.perform(put("/api/sewerageChargeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(sewerageChargeMaster)))
                .andExpect(status().isOk());

        // Validate the SewerageChargeMaster in the database
        List<SewerageChargeMaster> sewerageChargeMasters = sewerageChargeMasterRepository.findAll();
        assertThat(sewerageChargeMasters).hasSize(databaseSizeBeforeUpdate);
        SewerageChargeMaster testSewerageChargeMaster = sewerageChargeMasters.get(sewerageChargeMasters.size() - 1);
        assertThat(testSewerageChargeMaster.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void deleteSewerageChargeMaster() throws Exception {
        // Initialize the database
        sewerageChargeMasterRepository.saveAndFlush(sewerageChargeMaster);

		int databaseSizeBeforeDelete = sewerageChargeMasterRepository.findAll().size();

        // Get the sewerageChargeMaster
        restSewerageChargeMasterMockMvc.perform(delete("/api/sewerageChargeMasters/{id}", sewerageChargeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<SewerageChargeMaster> sewerageChargeMasters = sewerageChargeMasterRepository.findAll();
        assertThat(sewerageChargeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.Uom;
import com.callippus.water.erp.repository.UomRepository;

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
 * Test class for the UomResource REST controller.
 *
 * @see UomResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class UomResourceIntTest {

    private static final String DEFAULT_VALUE = "AAAAA";
    private static final String UPDATED_VALUE = "BBBBB";

    @Inject
    private UomRepository uomRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restUomMockMvc;

    private Uom uom;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UomResource uomResource = new UomResource();
        ReflectionTestUtils.setField(uomResource, "uomRepository", uomRepository);
        this.restUomMockMvc = MockMvcBuilders.standaloneSetup(uomResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        uom = new Uom();
        uom.setValue(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createUom() throws Exception {
        int databaseSizeBeforeCreate = uomRepository.findAll().size();

        // Create the Uom

        restUomMockMvc.perform(post("/api/uoms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(uom)))
                .andExpect(status().isCreated());

        // Validate the Uom in the database
        List<Uom> uoms = uomRepository.findAll();
        assertThat(uoms).hasSize(databaseSizeBeforeCreate + 1);
        Uom testUom = uoms.get(uoms.size() - 1);
        assertThat(testUom.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = uomRepository.findAll().size();
        // set the field null
        uom.setValue(null);

        // Create the Uom, which fails.

        restUomMockMvc.perform(post("/api/uoms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(uom)))
                .andExpect(status().isBadRequest());

        List<Uom> uoms = uomRepository.findAll();
        assertThat(uoms).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllUoms() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        // Get all the uoms
        restUomMockMvc.perform(get("/api/uoms?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(uom.getId().intValue())))
                .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.toString())));
    }

    @Test
    @Transactional
    public void getUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

        // Get the uom
        restUomMockMvc.perform(get("/api/uoms/{id}", uom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(uom.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUom() throws Exception {
        // Get the uom
        restUomMockMvc.perform(get("/api/uoms/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

		int databaseSizeBeforeUpdate = uomRepository.findAll().size();

        // Update the uom
        uom.setValue(UPDATED_VALUE);

        restUomMockMvc.perform(put("/api/uoms")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(uom)))
                .andExpect(status().isOk());

        // Validate the Uom in the database
        List<Uom> uoms = uomRepository.findAll();
        assertThat(uoms).hasSize(databaseSizeBeforeUpdate);
        Uom testUom = uoms.get(uoms.size() - 1);
        assertThat(testUom.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void deleteUom() throws Exception {
        // Initialize the database
        uomRepository.saveAndFlush(uom);

		int databaseSizeBeforeDelete = uomRepository.findAll().size();

        // Get the uom
        restUomMockMvc.perform(delete("/api/uoms/{id}", uom.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Uom> uoms = uomRepository.findAll();
        assertThat(uoms).hasSize(databaseSizeBeforeDelete - 1);
    }
}

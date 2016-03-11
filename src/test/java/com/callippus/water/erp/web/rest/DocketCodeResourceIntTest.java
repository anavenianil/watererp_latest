package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DocketCode;
import com.callippus.water.erp.repository.DocketCodeRepository;

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
 * Test class for the DocketCodeResource REST controller.
 *
 * @see DocketCodeResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DocketCodeResourceIntTest {

    private static final String DEFAULT_CODE = "AAAAA";
    private static final String UPDATED_CODE = "BBBBB";

    @Inject
    private DocketCodeRepository docketCodeRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDocketCodeMockMvc;

    private DocketCode docketCode;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DocketCodeResource docketCodeResource = new DocketCodeResource();
        ReflectionTestUtils.setField(docketCodeResource, "docketCodeRepository", docketCodeRepository);
        this.restDocketCodeMockMvc = MockMvcBuilders.standaloneSetup(docketCodeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        docketCode = new DocketCode();
        docketCode.setCode(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void createDocketCode() throws Exception {
        int databaseSizeBeforeCreate = docketCodeRepository.findAll().size();

        // Create the DocketCode

        restDocketCodeMockMvc.perform(post("/api/docketCodes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(docketCode)))
                .andExpect(status().isCreated());

        // Validate the DocketCode in the database
        List<DocketCode> docketCodes = docketCodeRepository.findAll();
        assertThat(docketCodes).hasSize(databaseSizeBeforeCreate + 1);
        DocketCode testDocketCode = docketCodes.get(docketCodes.size() - 1);
        assertThat(testDocketCode.getCode()).isEqualTo(DEFAULT_CODE);
    }

    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = docketCodeRepository.findAll().size();
        // set the field null
        docketCode.setCode(null);

        // Create the DocketCode, which fails.

        restDocketCodeMockMvc.perform(post("/api/docketCodes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(docketCode)))
                .andExpect(status().isBadRequest());

        List<DocketCode> docketCodes = docketCodeRepository.findAll();
        assertThat(docketCodes).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDocketCodes() throws Exception {
        // Initialize the database
        docketCodeRepository.saveAndFlush(docketCode);

        // Get all the docketCodes
        restDocketCodeMockMvc.perform(get("/api/docketCodes?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(docketCode.getId().intValue())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())));
    }

    @Test
    @Transactional
    public void getDocketCode() throws Exception {
        // Initialize the database
        docketCodeRepository.saveAndFlush(docketCode);

        // Get the docketCode
        restDocketCodeMockMvc.perform(get("/api/docketCodes/{id}", docketCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(docketCode.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDocketCode() throws Exception {
        // Get the docketCode
        restDocketCodeMockMvc.perform(get("/api/docketCodes/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDocketCode() throws Exception {
        // Initialize the database
        docketCodeRepository.saveAndFlush(docketCode);

		int databaseSizeBeforeUpdate = docketCodeRepository.findAll().size();

        // Update the docketCode
        docketCode.setCode(UPDATED_CODE);

        restDocketCodeMockMvc.perform(put("/api/docketCodes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(docketCode)))
                .andExpect(status().isOk());

        // Validate the DocketCode in the database
        List<DocketCode> docketCodes = docketCodeRepository.findAll();
        assertThat(docketCodes).hasSize(databaseSizeBeforeUpdate);
        DocketCode testDocketCode = docketCodes.get(docketCodes.size() - 1);
        assertThat(testDocketCode.getCode()).isEqualTo(UPDATED_CODE);
    }

    @Test
    @Transactional
    public void deleteDocketCode() throws Exception {
        // Initialize the database
        docketCodeRepository.saveAndFlush(docketCode);

		int databaseSizeBeforeDelete = docketCodeRepository.findAll().size();

        // Get the docketCode
        restDocketCodeMockMvc.perform(delete("/api/docketCodes/{id}", docketCode.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DocketCode> docketCodes = docketCodeRepository.findAll();
        assertThat(docketCodes).hasSize(databaseSizeBeforeDelete - 1);
    }
}

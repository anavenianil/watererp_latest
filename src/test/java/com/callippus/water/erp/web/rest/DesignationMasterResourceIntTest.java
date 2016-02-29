package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.DesignationMaster;
import com.callippus.water.erp.repository.DesignationMasterRepository;

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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the DesignationMasterResource REST controller.
 *
 * @see DesignationMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DesignationMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    private static final Integer DEFAULT_ORDER_NO = 1;
    private static final Integer UPDATED_ORDER_NO = 2;
    private static final String DEFAULT_SERVICE_TYPE = "AAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBB";
    private static final String DEFAULT_CODE = "AAAAA";
    private static final String UPDATED_CODE = "BBBBB";
    private static final String DEFAULT_DESIGALIAS = "AAAAA";
    private static final String UPDATED_DESIGALIAS = "BBBBB";

    @Inject
    private DesignationMasterRepository designationMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDesignationMasterMockMvc;

    private DesignationMaster designationMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DesignationMasterResource designationMasterResource = new DesignationMasterResource();
        ReflectionTestUtils.setField(designationMasterResource, "designationMasterRepository", designationMasterRepository);
        this.restDesignationMasterMockMvc = MockMvcBuilders.standaloneSetup(designationMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        designationMaster = new DesignationMaster();
        designationMaster.setName(DEFAULT_NAME);
        designationMaster.setCreationDate(DEFAULT_CREATION_DATE);
        designationMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        designationMaster.setDescription(DEFAULT_DESCRIPTION);
        designationMaster.setOrderNo(DEFAULT_ORDER_NO);
        designationMaster.setServiceType(DEFAULT_SERVICE_TYPE);
        designationMaster.setCode(DEFAULT_CODE);
        designationMaster.setDesigalias(DEFAULT_DESIGALIAS);
    }

    @Test
    @Transactional
    public void createDesignationMaster() throws Exception {
        int databaseSizeBeforeCreate = designationMasterRepository.findAll().size();

        // Create the DesignationMaster

        restDesignationMasterMockMvc.perform(post("/api/designationMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(designationMaster)))
                .andExpect(status().isCreated());

        // Validate the DesignationMaster in the database
        List<DesignationMaster> designationMasters = designationMasterRepository.findAll();
        assertThat(designationMasters).hasSize(databaseSizeBeforeCreate + 1);
        DesignationMaster testDesignationMaster = designationMasters.get(designationMasters.size() - 1);
        assertThat(testDesignationMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDesignationMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testDesignationMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testDesignationMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDesignationMaster.getOrderNo()).isEqualTo(DEFAULT_ORDER_NO);
        assertThat(testDesignationMaster.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testDesignationMaster.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDesignationMaster.getDesigalias()).isEqualTo(DEFAULT_DESIGALIAS);
    }

    @Test
    @Transactional
    public void getAllDesignationMasters() throws Exception {
        // Initialize the database
        designationMasterRepository.saveAndFlush(designationMaster);

        // Get all the designationMasters
        restDesignationMasterMockMvc.perform(get("/api/designationMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(designationMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].orderNo").value(hasItem(DEFAULT_ORDER_NO)))
                .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE.toString())))
                .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
                .andExpect(jsonPath("$.[*].desigalias").value(hasItem(DEFAULT_DESIGALIAS.toString())));
    }

    @Test
    @Transactional
    public void getDesignationMaster() throws Exception {
        // Initialize the database
        designationMasterRepository.saveAndFlush(designationMaster);

        // Get the designationMaster
        restDesignationMasterMockMvc.perform(get("/api/designationMasters/{id}", designationMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(designationMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.orderNo").value(DEFAULT_ORDER_NO))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.desigalias").value(DEFAULT_DESIGALIAS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDesignationMaster() throws Exception {
        // Get the designationMaster
        restDesignationMasterMockMvc.perform(get("/api/designationMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDesignationMaster() throws Exception {
        // Initialize the database
        designationMasterRepository.saveAndFlush(designationMaster);

		int databaseSizeBeforeUpdate = designationMasterRepository.findAll().size();

        // Update the designationMaster
        designationMaster.setName(UPDATED_NAME);
        designationMaster.setCreationDate(UPDATED_CREATION_DATE);
        designationMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        designationMaster.setDescription(UPDATED_DESCRIPTION);
        designationMaster.setOrderNo(UPDATED_ORDER_NO);
        designationMaster.setServiceType(UPDATED_SERVICE_TYPE);
        designationMaster.setCode(UPDATED_CODE);
        designationMaster.setDesigalias(UPDATED_DESIGALIAS);

        restDesignationMasterMockMvc.perform(put("/api/designationMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(designationMaster)))
                .andExpect(status().isOk());

        // Validate the DesignationMaster in the database
        List<DesignationMaster> designationMasters = designationMasterRepository.findAll();
        assertThat(designationMasters).hasSize(databaseSizeBeforeUpdate);
        DesignationMaster testDesignationMaster = designationMasters.get(designationMasters.size() - 1);
        assertThat(testDesignationMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDesignationMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testDesignationMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testDesignationMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDesignationMaster.getOrderNo()).isEqualTo(UPDATED_ORDER_NO);
        assertThat(testDesignationMaster.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testDesignationMaster.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDesignationMaster.getDesigalias()).isEqualTo(UPDATED_DESIGALIAS);
    }

    @Test
    @Transactional
    public void deleteDesignationMaster() throws Exception {
        // Initialize the database
        designationMasterRepository.saveAndFlush(designationMaster);

		int databaseSizeBeforeDelete = designationMasterRepository.findAll().size();

        // Get the designationMaster
        restDesignationMasterMockMvc.perform(delete("/api/designationMasters/{id}", designationMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<DesignationMaster> designationMasters = designationMasterRepository.findAll();
        assertThat(designationMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.MaterialMaster;
import com.callippus.water.erp.repository.MaterialMasterRepository;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the MaterialMasterResource REST controller.
 *
 * @see MaterialMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MaterialMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_MATERIAL_NAME = "AAAAA";
    private static final String UPDATED_MATERIAL_NAME = "BBBBB";
    private static final String DEFAULT_CONSUMABLE_FLAG = "AAAAA";
    private static final String UPDATED_CONSUMABLE_FLAG = "BBBBB";
    private static final String DEFAULT_UOM_ID = "AAAAA";
    private static final String UPDATED_UOM_ID = "BBBBB";

    private static final Long DEFAULT_CATEGORY_ID = 1L;
    private static final Long UPDATED_CATEGORY_ID = 2L;

    private static final Long DEFAULT_SUB_CATEGORY_ID = 1L;
    private static final Long UPDATED_SUB_CATEGORY_ID = 2L;

    private static final Long DEFAULT_ITEM_CODE_ID = 1L;
    private static final Long UPDATED_ITEM_CODE_ID = 2L;

    private static final Long DEFAULT_ITEM_SUB_CODE_ID = 1L;
    private static final Long UPDATED_ITEM_SUB_CODE_ID = 2L;
    private static final String DEFAULT_RATE_CONTRACT_FLAG = "AAAAA";
    private static final String UPDATED_RATE_CONTRACT_FLAG = "BBBBB";

    private static final BigDecimal DEFAULT_UNIT_RATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNIT_RATE = new BigDecimal(2);
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final ZonedDateTime DEFAULT_CREATION_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_CREATION_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_CREATION_DATE_STR = dateTimeFormatter.format(DEFAULT_CREATION_DATE);

    private static final ZonedDateTime DEFAULT_LAST_MODIFIED_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_LAST_MODIFIED_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_LAST_MODIFIED_DATE_STR = dateTimeFormatter.format(DEFAULT_LAST_MODIFIED_DATE);

    private static final BigDecimal DEFAULT_COMPANY_CODE_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_COMPANY_CODE_ID = new BigDecimal(2);

    @Inject
    private MaterialMasterRepository materialMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMaterialMasterMockMvc;

    private MaterialMaster materialMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MaterialMasterResource materialMasterResource = new MaterialMasterResource();
        ReflectionTestUtils.setField(materialMasterResource, "materialMasterRepository", materialMasterRepository);
        this.restMaterialMasterMockMvc = MockMvcBuilders.standaloneSetup(materialMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        materialMaster = new MaterialMaster();
        materialMaster.setMaterialName(DEFAULT_MATERIAL_NAME);
        materialMaster.setConsumableFlag(DEFAULT_CONSUMABLE_FLAG);
        materialMaster.setUomId(DEFAULT_UOM_ID);
        materialMaster.setCategoryId(DEFAULT_CATEGORY_ID);
        materialMaster.setSubCategoryId(DEFAULT_SUB_CATEGORY_ID);
        materialMaster.setItemCodeId(DEFAULT_ITEM_CODE_ID);
        materialMaster.setItemSubCodeId(DEFAULT_ITEM_SUB_CODE_ID);
        materialMaster.setRateContractFlag(DEFAULT_RATE_CONTRACT_FLAG);
        materialMaster.setUnitRate(DEFAULT_UNIT_RATE);
        materialMaster.setDescription(DEFAULT_DESCRIPTION);
        materialMaster.setStatus(DEFAULT_STATUS);
        materialMaster.setCreationDate(DEFAULT_CREATION_DATE);
        materialMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        materialMaster.setCompanyCodeId(DEFAULT_COMPANY_CODE_ID);
    }

    @Test
    @Transactional
    public void createMaterialMaster() throws Exception {
        int databaseSizeBeforeCreate = materialMasterRepository.findAll().size();

        // Create the MaterialMaster

        restMaterialMasterMockMvc.perform(post("/api/materialMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(materialMaster)))
                .andExpect(status().isCreated());

        // Validate the MaterialMaster in the database
        List<MaterialMaster> materialMasters = materialMasterRepository.findAll();
        assertThat(materialMasters).hasSize(databaseSizeBeforeCreate + 1);
        MaterialMaster testMaterialMaster = materialMasters.get(materialMasters.size() - 1);
        assertThat(testMaterialMaster.getMaterialName()).isEqualTo(DEFAULT_MATERIAL_NAME);
        assertThat(testMaterialMaster.getConsumableFlag()).isEqualTo(DEFAULT_CONSUMABLE_FLAG);
        assertThat(testMaterialMaster.getUomId()).isEqualTo(DEFAULT_UOM_ID);
        assertThat(testMaterialMaster.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testMaterialMaster.getSubCategoryId()).isEqualTo(DEFAULT_SUB_CATEGORY_ID);
        assertThat(testMaterialMaster.getItemCodeId()).isEqualTo(DEFAULT_ITEM_CODE_ID);
        assertThat(testMaterialMaster.getItemSubCodeId()).isEqualTo(DEFAULT_ITEM_SUB_CODE_ID);
        assertThat(testMaterialMaster.getRateContractFlag()).isEqualTo(DEFAULT_RATE_CONTRACT_FLAG);
        assertThat(testMaterialMaster.getUnitRate()).isEqualTo(DEFAULT_UNIT_RATE);
        assertThat(testMaterialMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMaterialMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testMaterialMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testMaterialMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testMaterialMaster.getCompanyCodeId()).isEqualTo(DEFAULT_COMPANY_CODE_ID);
    }

    @Test
    @Transactional
    public void getAllMaterialMasters() throws Exception {
        // Initialize the database
        materialMasterRepository.saveAndFlush(materialMaster);

        // Get all the materialMasters
        restMaterialMasterMockMvc.perform(get("/api/materialMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(materialMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].materialName").value(hasItem(DEFAULT_MATERIAL_NAME.toString())))
                .andExpect(jsonPath("$.[*].consumableFlag").value(hasItem(DEFAULT_CONSUMABLE_FLAG.toString())))
                .andExpect(jsonPath("$.[*].uomId").value(hasItem(DEFAULT_UOM_ID.toString())))
                .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID.intValue())))
                .andExpect(jsonPath("$.[*].subCategoryId").value(hasItem(DEFAULT_SUB_CATEGORY_ID.intValue())))
                .andExpect(jsonPath("$.[*].itemCodeId").value(hasItem(DEFAULT_ITEM_CODE_ID.intValue())))
                .andExpect(jsonPath("$.[*].itemSubCodeId").value(hasItem(DEFAULT_ITEM_SUB_CODE_ID.intValue())))
                .andExpect(jsonPath("$.[*].rateContractFlag").value(hasItem(DEFAULT_RATE_CONTRACT_FLAG.toString())))
                .andExpect(jsonPath("$.[*].unitRate").value(hasItem(DEFAULT_UNIT_RATE.intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].companyCodeId").value(hasItem(DEFAULT_COMPANY_CODE_ID.intValue())));
    }

    @Test
    @Transactional
    public void getMaterialMaster() throws Exception {
        // Initialize the database
        materialMasterRepository.saveAndFlush(materialMaster);

        // Get the materialMaster
        restMaterialMasterMockMvc.perform(get("/api/materialMasters/{id}", materialMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(materialMaster.getId().intValue()))
            .andExpect(jsonPath("$.materialName").value(DEFAULT_MATERIAL_NAME.toString()))
            .andExpect(jsonPath("$.consumableFlag").value(DEFAULT_CONSUMABLE_FLAG.toString()))
            .andExpect(jsonPath("$.uomId").value(DEFAULT_UOM_ID.toString()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.subCategoryId").value(DEFAULT_SUB_CATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.itemCodeId").value(DEFAULT_ITEM_CODE_ID.intValue()))
            .andExpect(jsonPath("$.itemSubCodeId").value(DEFAULT_ITEM_SUB_CODE_ID.intValue()))
            .andExpect(jsonPath("$.rateContractFlag").value(DEFAULT_RATE_CONTRACT_FLAG.toString()))
            .andExpect(jsonPath("$.unitRate").value(DEFAULT_UNIT_RATE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.companyCodeId").value(DEFAULT_COMPANY_CODE_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMaterialMaster() throws Exception {
        // Get the materialMaster
        restMaterialMasterMockMvc.perform(get("/api/materialMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaterialMaster() throws Exception {
        // Initialize the database
        materialMasterRepository.saveAndFlush(materialMaster);

		int databaseSizeBeforeUpdate = materialMasterRepository.findAll().size();

        // Update the materialMaster
        materialMaster.setMaterialName(UPDATED_MATERIAL_NAME);
        materialMaster.setConsumableFlag(UPDATED_CONSUMABLE_FLAG);
        materialMaster.setUomId(UPDATED_UOM_ID);
        materialMaster.setCategoryId(UPDATED_CATEGORY_ID);
        materialMaster.setSubCategoryId(UPDATED_SUB_CATEGORY_ID);
        materialMaster.setItemCodeId(UPDATED_ITEM_CODE_ID);
        materialMaster.setItemSubCodeId(UPDATED_ITEM_SUB_CODE_ID);
        materialMaster.setRateContractFlag(UPDATED_RATE_CONTRACT_FLAG);
        materialMaster.setUnitRate(UPDATED_UNIT_RATE);
        materialMaster.setDescription(UPDATED_DESCRIPTION);
        materialMaster.setStatus(UPDATED_STATUS);
        materialMaster.setCreationDate(UPDATED_CREATION_DATE);
        materialMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        materialMaster.setCompanyCodeId(UPDATED_COMPANY_CODE_ID);

        restMaterialMasterMockMvc.perform(put("/api/materialMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(materialMaster)))
                .andExpect(status().isOk());

        // Validate the MaterialMaster in the database
        List<MaterialMaster> materialMasters = materialMasterRepository.findAll();
        assertThat(materialMasters).hasSize(databaseSizeBeforeUpdate);
        MaterialMaster testMaterialMaster = materialMasters.get(materialMasters.size() - 1);
        assertThat(testMaterialMaster.getMaterialName()).isEqualTo(UPDATED_MATERIAL_NAME);
        assertThat(testMaterialMaster.getConsumableFlag()).isEqualTo(UPDATED_CONSUMABLE_FLAG);
        assertThat(testMaterialMaster.getUomId()).isEqualTo(UPDATED_UOM_ID);
        assertThat(testMaterialMaster.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testMaterialMaster.getSubCategoryId()).isEqualTo(UPDATED_SUB_CATEGORY_ID);
        assertThat(testMaterialMaster.getItemCodeId()).isEqualTo(UPDATED_ITEM_CODE_ID);
        assertThat(testMaterialMaster.getItemSubCodeId()).isEqualTo(UPDATED_ITEM_SUB_CODE_ID);
        assertThat(testMaterialMaster.getRateContractFlag()).isEqualTo(UPDATED_RATE_CONTRACT_FLAG);
        assertThat(testMaterialMaster.getUnitRate()).isEqualTo(UPDATED_UNIT_RATE);
        assertThat(testMaterialMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMaterialMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testMaterialMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testMaterialMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testMaterialMaster.getCompanyCodeId()).isEqualTo(UPDATED_COMPANY_CODE_ID);
    }

    @Test
    @Transactional
    public void deleteMaterialMaster() throws Exception {
        // Initialize the database
        materialMasterRepository.saveAndFlush(materialMaster);

		int databaseSizeBeforeDelete = materialMasterRepository.findAll().size();

        // Get the materialMaster
        restMaterialMasterMockMvc.perform(delete("/api/materialMasters/{id}", materialMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<MaterialMaster> materialMasters = materialMasterRepository.findAll();
        assertThat(materialMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

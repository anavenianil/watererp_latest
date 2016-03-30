package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemSubCodeMaster;
import com.callippus.water.erp.repository.ItemSubCodeMasterRepository;

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
 * Test class for the ItemSubCodeMasterResource REST controller.
 *
 * @see ItemSubCodeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemSubCodeMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));


    private static final Long DEFAULT_ITEM_CODE_ID = 1L;
    private static final Long UPDATED_ITEM_CODE_ID = 2L;
    private static final String DEFAULT_ITEM_SUB_CODE = "AAAAA";
    private static final String UPDATED_ITEM_SUB_CODE = "BBBBB";
    private static final String DEFAULT_ITEM_NAME = "AAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBB";
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

    private static final Long DEFAULT_ITEM_CCODE_ID = 1L;
    private static final Long UPDATED_ITEM_CCODE_ID = 2L;

    private static final Long DEFAULT_ITEM_CATEGORY_ID = 1L;
    private static final Long UPDATED_ITEM_CATEGORY_ID = 2L;

    private static final Long DEFAULT_ITEM_SUB_CATEGORY_ID = 1L;
    private static final Long UPDATED_ITEM_SUB_CATEGORY_ID = 2L;

    @Inject
    private ItemSubCodeMasterRepository itemSubCodeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemSubCodeMasterMockMvc;

    private ItemSubCodeMaster itemSubCodeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemSubCodeMasterResource itemSubCodeMasterResource = new ItemSubCodeMasterResource();
        ReflectionTestUtils.setField(itemSubCodeMasterResource, "itemSubCodeMasterRepository", itemSubCodeMasterRepository);
        this.restItemSubCodeMasterMockMvc = MockMvcBuilders.standaloneSetup(itemSubCodeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemSubCodeMaster = new ItemSubCodeMaster();
        itemSubCodeMaster.setItemCodeId(DEFAULT_ITEM_CODE_ID);
        itemSubCodeMaster.setItemSubCode(DEFAULT_ITEM_SUB_CODE);
        itemSubCodeMaster.setItemName(DEFAULT_ITEM_NAME);
        itemSubCodeMaster.setDescription(DEFAULT_DESCRIPTION);
        itemSubCodeMaster.setStatus(DEFAULT_STATUS);
        itemSubCodeMaster.setCreationDate(DEFAULT_CREATION_DATE);
        itemSubCodeMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        itemSubCodeMaster.setItemCcodeId(DEFAULT_ITEM_CCODE_ID);
        itemSubCodeMaster.setItemCategoryId(DEFAULT_ITEM_CATEGORY_ID);
        itemSubCodeMaster.setItemSubCategoryID(DEFAULT_ITEM_SUB_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void createItemSubCodeMaster() throws Exception {
        int databaseSizeBeforeCreate = itemSubCodeMasterRepository.findAll().size();

        // Create the ItemSubCodeMaster

        restItemSubCodeMasterMockMvc.perform(post("/api/itemSubCodeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemSubCodeMaster)))
                .andExpect(status().isCreated());

        // Validate the ItemSubCodeMaster in the database
        List<ItemSubCodeMaster> itemSubCodeMasters = itemSubCodeMasterRepository.findAll();
        assertThat(itemSubCodeMasters).hasSize(databaseSizeBeforeCreate + 1);
        ItemSubCodeMaster testItemSubCodeMaster = itemSubCodeMasters.get(itemSubCodeMasters.size() - 1);
        assertThat(testItemSubCodeMaster.getItemCodeId()).isEqualTo(DEFAULT_ITEM_CODE_ID);
        assertThat(testItemSubCodeMaster.getItemSubCode()).isEqualTo(DEFAULT_ITEM_SUB_CODE);
        assertThat(testItemSubCodeMaster.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemSubCodeMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemSubCodeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemSubCodeMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testItemSubCodeMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testItemSubCodeMaster.getItemCcodeId()).isEqualTo(DEFAULT_ITEM_CCODE_ID);
        assertThat(testItemSubCodeMaster.getItemCategoryId()).isEqualTo(DEFAULT_ITEM_CATEGORY_ID);
        assertThat(testItemSubCodeMaster.getItemSubCategoryID()).isEqualTo(DEFAULT_ITEM_SUB_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void checkItemCategoryIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = itemSubCodeMasterRepository.findAll().size();
        // set the field null
        itemSubCodeMaster.setItemCategoryId(null);

        // Create the ItemSubCodeMaster, which fails.

        restItemSubCodeMasterMockMvc.perform(post("/api/itemSubCodeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemSubCodeMaster)))
                .andExpect(status().isBadRequest());

        List<ItemSubCodeMaster> itemSubCodeMasters = itemSubCodeMasterRepository.findAll();
        assertThat(itemSubCodeMasters).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllItemSubCodeMasters() throws Exception {
        // Initialize the database
        itemSubCodeMasterRepository.saveAndFlush(itemSubCodeMaster);

        // Get all the itemSubCodeMasters
        restItemSubCodeMasterMockMvc.perform(get("/api/itemSubCodeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemSubCodeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].itemCodeId").value(hasItem(DEFAULT_ITEM_CODE_ID.intValue())))
                .andExpect(jsonPath("$.[*].itemSubCode").value(hasItem(DEFAULT_ITEM_SUB_CODE.toString())))
                .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].itemCcodeId").value(hasItem(DEFAULT_ITEM_CCODE_ID.intValue())))
                .andExpect(jsonPath("$.[*].itemCategoryId").value(hasItem(DEFAULT_ITEM_CATEGORY_ID.intValue())))
                .andExpect(jsonPath("$.[*].itemSubCategoryID").value(hasItem(DEFAULT_ITEM_SUB_CATEGORY_ID.intValue())));
    }

    @Test
    @Transactional
    public void getItemSubCodeMaster() throws Exception {
        // Initialize the database
        itemSubCodeMasterRepository.saveAndFlush(itemSubCodeMaster);

        // Get the itemSubCodeMaster
        restItemSubCodeMasterMockMvc.perform(get("/api/itemSubCodeMasters/{id}", itemSubCodeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemSubCodeMaster.getId().intValue()))
            .andExpect(jsonPath("$.itemCodeId").value(DEFAULT_ITEM_CODE_ID.intValue()))
            .andExpect(jsonPath("$.itemSubCode").value(DEFAULT_ITEM_SUB_CODE.toString()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.itemCcodeId").value(DEFAULT_ITEM_CCODE_ID.intValue()))
            .andExpect(jsonPath("$.itemCategoryId").value(DEFAULT_ITEM_CATEGORY_ID.intValue()))
            .andExpect(jsonPath("$.itemSubCategoryID").value(DEFAULT_ITEM_SUB_CATEGORY_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItemSubCodeMaster() throws Exception {
        // Get the itemSubCodeMaster
        restItemSubCodeMasterMockMvc.perform(get("/api/itemSubCodeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemSubCodeMaster() throws Exception {
        // Initialize the database
        itemSubCodeMasterRepository.saveAndFlush(itemSubCodeMaster);

		int databaseSizeBeforeUpdate = itemSubCodeMasterRepository.findAll().size();

        // Update the itemSubCodeMaster
        itemSubCodeMaster.setItemCodeId(UPDATED_ITEM_CODE_ID);
        itemSubCodeMaster.setItemSubCode(UPDATED_ITEM_SUB_CODE);
        itemSubCodeMaster.setItemName(UPDATED_ITEM_NAME);
        itemSubCodeMaster.setDescription(UPDATED_DESCRIPTION);
        itemSubCodeMaster.setStatus(UPDATED_STATUS);
        itemSubCodeMaster.setCreationDate(UPDATED_CREATION_DATE);
        itemSubCodeMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        itemSubCodeMaster.setItemCcodeId(UPDATED_ITEM_CCODE_ID);
        itemSubCodeMaster.setItemCategoryId(UPDATED_ITEM_CATEGORY_ID);
        itemSubCodeMaster.setItemSubCategoryID(UPDATED_ITEM_SUB_CATEGORY_ID);

        restItemSubCodeMasterMockMvc.perform(put("/api/itemSubCodeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemSubCodeMaster)))
                .andExpect(status().isOk());

        // Validate the ItemSubCodeMaster in the database
        List<ItemSubCodeMaster> itemSubCodeMasters = itemSubCodeMasterRepository.findAll();
        assertThat(itemSubCodeMasters).hasSize(databaseSizeBeforeUpdate);
        ItemSubCodeMaster testItemSubCodeMaster = itemSubCodeMasters.get(itemSubCodeMasters.size() - 1);
        assertThat(testItemSubCodeMaster.getItemCodeId()).isEqualTo(UPDATED_ITEM_CODE_ID);
        assertThat(testItemSubCodeMaster.getItemSubCode()).isEqualTo(UPDATED_ITEM_SUB_CODE);
        assertThat(testItemSubCodeMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemSubCodeMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemSubCodeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemSubCodeMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testItemSubCodeMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testItemSubCodeMaster.getItemCcodeId()).isEqualTo(UPDATED_ITEM_CCODE_ID);
        assertThat(testItemSubCodeMaster.getItemCategoryId()).isEqualTo(UPDATED_ITEM_CATEGORY_ID);
        assertThat(testItemSubCodeMaster.getItemSubCategoryID()).isEqualTo(UPDATED_ITEM_SUB_CATEGORY_ID);
    }

    @Test
    @Transactional
    public void deleteItemSubCodeMaster() throws Exception {
        // Initialize the database
        itemSubCodeMasterRepository.saveAndFlush(itemSubCodeMaster);

		int databaseSizeBeforeDelete = itemSubCodeMasterRepository.findAll().size();

        // Get the itemSubCodeMaster
        restItemSubCodeMasterMockMvc.perform(delete("/api/itemSubCodeMasters/{id}", itemSubCodeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemSubCodeMaster> itemSubCodeMasters = itemSubCodeMasterRepository.findAll();
        assertThat(itemSubCodeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

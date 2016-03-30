package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemSubCategoryMaster;
import com.callippus.water.erp.repository.ItemSubCategoryMasterRepository;

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
 * Test class for the ItemSubCategoryMasterResource REST controller.
 *
 * @see ItemSubCategoryMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemSubCategoryMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_ITEM_SUB_CATEGORY_CODE = "AAAAA";
    private static final String UPDATED_ITEM_SUB_CATEGORY_CODE = "BBBBB";
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
    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_CATEGORY_CODE = "AAAAA";
    private static final String UPDATED_CATEGORY_CODE = "BBBBB";

    @Inject
    private ItemSubCategoryMasterRepository itemSubCategoryMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemSubCategoryMasterMockMvc;

    private ItemSubCategoryMaster itemSubCategoryMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemSubCategoryMasterResource itemSubCategoryMasterResource = new ItemSubCategoryMasterResource();
        ReflectionTestUtils.setField(itemSubCategoryMasterResource, "itemSubCategoryMasterRepository", itemSubCategoryMasterRepository);
        this.restItemSubCategoryMasterMockMvc = MockMvcBuilders.standaloneSetup(itemSubCategoryMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemSubCategoryMaster = new ItemSubCategoryMaster();
        itemSubCategoryMaster.setItemSubCategoryCode(DEFAULT_ITEM_SUB_CATEGORY_CODE);
        itemSubCategoryMaster.setDescription(DEFAULT_DESCRIPTION);
        itemSubCategoryMaster.setStatus(DEFAULT_STATUS);
        itemSubCategoryMaster.setCreationDate(DEFAULT_CREATION_DATE);
        itemSubCategoryMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        itemSubCategoryMaster.setName(DEFAULT_NAME);
        itemSubCategoryMaster.setCategoryCode(DEFAULT_CATEGORY_CODE);
    }

    @Test
    @Transactional
    public void createItemSubCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = itemSubCategoryMasterRepository.findAll().size();

        // Create the ItemSubCategoryMaster

        restItemSubCategoryMasterMockMvc.perform(post("/api/itemSubCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemSubCategoryMaster)))
                .andExpect(status().isCreated());

        // Validate the ItemSubCategoryMaster in the database
        List<ItemSubCategoryMaster> itemSubCategoryMasters = itemSubCategoryMasterRepository.findAll();
        assertThat(itemSubCategoryMasters).hasSize(databaseSizeBeforeCreate + 1);
        ItemSubCategoryMaster testItemSubCategoryMaster = itemSubCategoryMasters.get(itemSubCategoryMasters.size() - 1);
        assertThat(testItemSubCategoryMaster.getItemSubCategoryCode()).isEqualTo(DEFAULT_ITEM_SUB_CATEGORY_CODE);
        assertThat(testItemSubCategoryMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemSubCategoryMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemSubCategoryMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testItemSubCategoryMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testItemSubCategoryMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemSubCategoryMaster.getCategoryCode()).isEqualTo(DEFAULT_CATEGORY_CODE);
    }

    @Test
    @Transactional
    public void getAllItemSubCategoryMasters() throws Exception {
        // Initialize the database
        itemSubCategoryMasterRepository.saveAndFlush(itemSubCategoryMaster);

        // Get all the itemSubCategoryMasters
        restItemSubCategoryMasterMockMvc.perform(get("/api/itemSubCategoryMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemSubCategoryMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].itemSubCategoryCode").value(hasItem(DEFAULT_ITEM_SUB_CATEGORY_CODE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].categoryCode").value(hasItem(DEFAULT_CATEGORY_CODE.toString())));
    }

    @Test
    @Transactional
    public void getItemSubCategoryMaster() throws Exception {
        // Initialize the database
        itemSubCategoryMasterRepository.saveAndFlush(itemSubCategoryMaster);

        // Get the itemSubCategoryMaster
        restItemSubCategoryMasterMockMvc.perform(get("/api/itemSubCategoryMasters/{id}", itemSubCategoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemSubCategoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.itemSubCategoryCode").value(DEFAULT_ITEM_SUB_CATEGORY_CODE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.categoryCode").value(DEFAULT_CATEGORY_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemSubCategoryMaster() throws Exception {
        // Get the itemSubCategoryMaster
        restItemSubCategoryMasterMockMvc.perform(get("/api/itemSubCategoryMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemSubCategoryMaster() throws Exception {
        // Initialize the database
        itemSubCategoryMasterRepository.saveAndFlush(itemSubCategoryMaster);

		int databaseSizeBeforeUpdate = itemSubCategoryMasterRepository.findAll().size();

        // Update the itemSubCategoryMaster
        itemSubCategoryMaster.setItemSubCategoryCode(UPDATED_ITEM_SUB_CATEGORY_CODE);
        itemSubCategoryMaster.setDescription(UPDATED_DESCRIPTION);
        itemSubCategoryMaster.setStatus(UPDATED_STATUS);
        itemSubCategoryMaster.setCreationDate(UPDATED_CREATION_DATE);
        itemSubCategoryMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        itemSubCategoryMaster.setName(UPDATED_NAME);
        itemSubCategoryMaster.setCategoryCode(UPDATED_CATEGORY_CODE);

        restItemSubCategoryMasterMockMvc.perform(put("/api/itemSubCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemSubCategoryMaster)))
                .andExpect(status().isOk());

        // Validate the ItemSubCategoryMaster in the database
        List<ItemSubCategoryMaster> itemSubCategoryMasters = itemSubCategoryMasterRepository.findAll();
        assertThat(itemSubCategoryMasters).hasSize(databaseSizeBeforeUpdate);
        ItemSubCategoryMaster testItemSubCategoryMaster = itemSubCategoryMasters.get(itemSubCategoryMasters.size() - 1);
        assertThat(testItemSubCategoryMaster.getItemSubCategoryCode()).isEqualTo(UPDATED_ITEM_SUB_CATEGORY_CODE);
        assertThat(testItemSubCategoryMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemSubCategoryMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemSubCategoryMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testItemSubCategoryMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testItemSubCategoryMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemSubCategoryMaster.getCategoryCode()).isEqualTo(UPDATED_CATEGORY_CODE);
    }

    @Test
    @Transactional
    public void deleteItemSubCategoryMaster() throws Exception {
        // Initialize the database
        itemSubCategoryMasterRepository.saveAndFlush(itemSubCategoryMaster);

		int databaseSizeBeforeDelete = itemSubCategoryMasterRepository.findAll().size();

        // Get the itemSubCategoryMaster
        restItemSubCategoryMasterMockMvc.perform(delete("/api/itemSubCategoryMasters/{id}", itemSubCategoryMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemSubCategoryMaster> itemSubCategoryMasters = itemSubCategoryMasterRepository.findAll();
        assertThat(itemSubCategoryMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

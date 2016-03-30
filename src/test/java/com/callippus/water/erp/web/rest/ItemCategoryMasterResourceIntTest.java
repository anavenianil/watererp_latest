package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemCategoryMaster;
import com.callippus.water.erp.repository.ItemCategoryMasterRepository;

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
 * Test class for the ItemCategoryMasterResource REST controller.
 *
 * @see ItemCategoryMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemCategoryMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
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
    private static final String DEFAULT_CATEGORY_CODE = "AAAAA";
    private static final String UPDATED_CATEGORY_CODE = "BBBBB";

    @Inject
    private ItemCategoryMasterRepository itemCategoryMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemCategoryMasterMockMvc;

    private ItemCategoryMaster itemCategoryMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemCategoryMasterResource itemCategoryMasterResource = new ItemCategoryMasterResource();
        ReflectionTestUtils.setField(itemCategoryMasterResource, "itemCategoryMasterRepository", itemCategoryMasterRepository);
        this.restItemCategoryMasterMockMvc = MockMvcBuilders.standaloneSetup(itemCategoryMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemCategoryMaster = new ItemCategoryMaster();
        itemCategoryMaster.setName(DEFAULT_NAME);
        itemCategoryMaster.setDescription(DEFAULT_DESCRIPTION);
        itemCategoryMaster.setStatus(DEFAULT_STATUS);
        itemCategoryMaster.setCreationDate(DEFAULT_CREATION_DATE);
        itemCategoryMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        itemCategoryMaster.setCategoryCode(DEFAULT_CATEGORY_CODE);
    }

    @Test
    @Transactional
    public void createItemCategoryMaster() throws Exception {
        int databaseSizeBeforeCreate = itemCategoryMasterRepository.findAll().size();

        // Create the ItemCategoryMaster

        restItemCategoryMasterMockMvc.perform(post("/api/itemCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemCategoryMaster)))
                .andExpect(status().isCreated());

        // Validate the ItemCategoryMaster in the database
        List<ItemCategoryMaster> itemCategoryMasters = itemCategoryMasterRepository.findAll();
        assertThat(itemCategoryMasters).hasSize(databaseSizeBeforeCreate + 1);
        ItemCategoryMaster testItemCategoryMaster = itemCategoryMasters.get(itemCategoryMasters.size() - 1);
        assertThat(testItemCategoryMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemCategoryMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemCategoryMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemCategoryMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testItemCategoryMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testItemCategoryMaster.getCategoryCode()).isEqualTo(DEFAULT_CATEGORY_CODE);
    }

    @Test
    @Transactional
    public void getAllItemCategoryMasters() throws Exception {
        // Initialize the database
        itemCategoryMasterRepository.saveAndFlush(itemCategoryMaster);

        // Get all the itemCategoryMasters
        restItemCategoryMasterMockMvc.perform(get("/api/itemCategoryMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemCategoryMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].categoryCode").value(hasItem(DEFAULT_CATEGORY_CODE.toString())));
    }

    @Test
    @Transactional
    public void getItemCategoryMaster() throws Exception {
        // Initialize the database
        itemCategoryMasterRepository.saveAndFlush(itemCategoryMaster);

        // Get the itemCategoryMaster
        restItemCategoryMasterMockMvc.perform(get("/api/itemCategoryMasters/{id}", itemCategoryMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemCategoryMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.categoryCode").value(DEFAULT_CATEGORY_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemCategoryMaster() throws Exception {
        // Get the itemCategoryMaster
        restItemCategoryMasterMockMvc.perform(get("/api/itemCategoryMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCategoryMaster() throws Exception {
        // Initialize the database
        itemCategoryMasterRepository.saveAndFlush(itemCategoryMaster);

		int databaseSizeBeforeUpdate = itemCategoryMasterRepository.findAll().size();

        // Update the itemCategoryMaster
        itemCategoryMaster.setName(UPDATED_NAME);
        itemCategoryMaster.setDescription(UPDATED_DESCRIPTION);
        itemCategoryMaster.setStatus(UPDATED_STATUS);
        itemCategoryMaster.setCreationDate(UPDATED_CREATION_DATE);
        itemCategoryMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        itemCategoryMaster.setCategoryCode(UPDATED_CATEGORY_CODE);

        restItemCategoryMasterMockMvc.perform(put("/api/itemCategoryMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemCategoryMaster)))
                .andExpect(status().isOk());

        // Validate the ItemCategoryMaster in the database
        List<ItemCategoryMaster> itemCategoryMasters = itemCategoryMasterRepository.findAll();
        assertThat(itemCategoryMasters).hasSize(databaseSizeBeforeUpdate);
        ItemCategoryMaster testItemCategoryMaster = itemCategoryMasters.get(itemCategoryMasters.size() - 1);
        assertThat(testItemCategoryMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemCategoryMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemCategoryMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemCategoryMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testItemCategoryMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testItemCategoryMaster.getCategoryCode()).isEqualTo(UPDATED_CATEGORY_CODE);
    }

    @Test
    @Transactional
    public void deleteItemCategoryMaster() throws Exception {
        // Initialize the database
        itemCategoryMasterRepository.saveAndFlush(itemCategoryMaster);

		int databaseSizeBeforeDelete = itemCategoryMasterRepository.findAll().size();

        // Get the itemCategoryMaster
        restItemCategoryMasterMockMvc.perform(delete("/api/itemCategoryMasters/{id}", itemCategoryMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemCategoryMaster> itemCategoryMasters = itemCategoryMasterRepository.findAll();
        assertThat(itemCategoryMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

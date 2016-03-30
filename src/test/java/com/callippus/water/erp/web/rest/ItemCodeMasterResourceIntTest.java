package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemCodeMaster;
import com.callippus.water.erp.repository.ItemCodeMasterRepository;

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
 * Test class for the ItemCodeMasterResource REST controller.
 *
 * @see ItemCodeMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemCodeMasterResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME.withZone(ZoneId.of("Z"));

    private static final String DEFAULT_ITEM_CODE = "AAAAA";
    private static final String UPDATED_ITEM_CODE = "BBBBB";
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

    @Inject
    private ItemCodeMasterRepository itemCodeMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemCodeMasterMockMvc;

    private ItemCodeMaster itemCodeMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemCodeMasterResource itemCodeMasterResource = new ItemCodeMasterResource();
        ReflectionTestUtils.setField(itemCodeMasterResource, "itemCodeMasterRepository", itemCodeMasterRepository);
        this.restItemCodeMasterMockMvc = MockMvcBuilders.standaloneSetup(itemCodeMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemCodeMaster = new ItemCodeMaster();
        itemCodeMaster.setItemCode(DEFAULT_ITEM_CODE);
        itemCodeMaster.setItemName(DEFAULT_ITEM_NAME);
        itemCodeMaster.setDescription(DEFAULT_DESCRIPTION);
        itemCodeMaster.setStatus(DEFAULT_STATUS);
        itemCodeMaster.setCreationDate(DEFAULT_CREATION_DATE);
        itemCodeMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void createItemCodeMaster() throws Exception {
        int databaseSizeBeforeCreate = itemCodeMasterRepository.findAll().size();

        // Create the ItemCodeMaster

        restItemCodeMasterMockMvc.perform(post("/api/itemCodeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemCodeMaster)))
                .andExpect(status().isCreated());

        // Validate the ItemCodeMaster in the database
        List<ItemCodeMaster> itemCodeMasters = itemCodeMasterRepository.findAll();
        assertThat(itemCodeMasters).hasSize(databaseSizeBeforeCreate + 1);
        ItemCodeMaster testItemCodeMaster = itemCodeMasters.get(itemCodeMasters.size() - 1);
        assertThat(testItemCodeMaster.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testItemCodeMaster.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemCodeMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemCodeMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemCodeMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testItemCodeMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void getAllItemCodeMasters() throws Exception {
        // Initialize the database
        itemCodeMasterRepository.saveAndFlush(itemCodeMaster);

        // Get all the itemCodeMasters
        restItemCodeMasterMockMvc.perform(get("/api/itemCodeMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemCodeMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].itemCode").value(hasItem(DEFAULT_ITEM_CODE.toString())))
                .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)));
    }

    @Test
    @Transactional
    public void getItemCodeMaster() throws Exception {
        // Initialize the database
        itemCodeMasterRepository.saveAndFlush(itemCodeMaster);

        // Get the itemCodeMaster
        restItemCodeMasterMockMvc.perform(get("/api/itemCodeMasters/{id}", itemCodeMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemCodeMaster.getId().intValue()))
            .andExpect(jsonPath("$.itemCode").value(DEFAULT_ITEM_CODE.toString()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR));
    }

    @Test
    @Transactional
    public void getNonExistingItemCodeMaster() throws Exception {
        // Get the itemCodeMaster
        restItemCodeMasterMockMvc.perform(get("/api/itemCodeMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCodeMaster() throws Exception {
        // Initialize the database
        itemCodeMasterRepository.saveAndFlush(itemCodeMaster);

		int databaseSizeBeforeUpdate = itemCodeMasterRepository.findAll().size();

        // Update the itemCodeMaster
        itemCodeMaster.setItemCode(UPDATED_ITEM_CODE);
        itemCodeMaster.setItemName(UPDATED_ITEM_NAME);
        itemCodeMaster.setDescription(UPDATED_DESCRIPTION);
        itemCodeMaster.setStatus(UPDATED_STATUS);
        itemCodeMaster.setCreationDate(UPDATED_CREATION_DATE);
        itemCodeMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);

        restItemCodeMasterMockMvc.perform(put("/api/itemCodeMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemCodeMaster)))
                .andExpect(status().isOk());

        // Validate the ItemCodeMaster in the database
        List<ItemCodeMaster> itemCodeMasters = itemCodeMasterRepository.findAll();
        assertThat(itemCodeMasters).hasSize(databaseSizeBeforeUpdate);
        ItemCodeMaster testItemCodeMaster = itemCodeMasters.get(itemCodeMasters.size() - 1);
        assertThat(testItemCodeMaster.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testItemCodeMaster.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemCodeMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemCodeMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemCodeMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testItemCodeMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
    }

    @Test
    @Transactional
    public void deleteItemCodeMaster() throws Exception {
        // Initialize the database
        itemCodeMasterRepository.saveAndFlush(itemCodeMaster);

		int databaseSizeBeforeDelete = itemCodeMasterRepository.findAll().size();

        // Get the itemCodeMaster
        restItemCodeMasterMockMvc.perform(delete("/api/itemCodeMasters/{id}", itemCodeMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemCodeMaster> itemCodeMasters = itemCodeMasterRepository.findAll();
        assertThat(itemCodeMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}

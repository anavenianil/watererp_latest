package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemDetails;
import com.callippus.water.erp.repository.ItemDetailsRepository;

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
 * Test class for the ItemDetailsResource REST controller.
 *
 * @see ItemDetailsResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemDetailsResourceIntTest {

    private static final String DEFAULT_ITEM_CODE = "AAAAA";
    private static final String UPDATED_ITEM_CODE = "BBBBB";
    private static final String DEFAULT_ITEM_NAME = "AAAAA";
    private static final String UPDATED_ITEM_NAME = "BBBBB";
    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_SIZE = "AAAAA";
    private static final String UPDATED_SIZE = "BBBBB";

    private static final Integer DEFAULT_ITEM_QUANTITY = 1;
    private static final Integer UPDATED_ITEM_QUANTITY = 2;

    private static final Float DEFAULT_UNIT_PRICE = 1F;
    private static final Float UPDATED_UNIT_PRICE = 2F;

    @Inject
    private ItemDetailsRepository itemDetailsRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemDetailsMockMvc;

    private ItemDetails itemDetails;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemDetailsResource itemDetailsResource = new ItemDetailsResource();
        ReflectionTestUtils.setField(itemDetailsResource, "itemDetailsRepository", itemDetailsRepository);
        this.restItemDetailsMockMvc = MockMvcBuilders.standaloneSetup(itemDetailsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemDetails = new ItemDetails();
        itemDetails.setItemCode(DEFAULT_ITEM_CODE);
        itemDetails.setItemName(DEFAULT_ITEM_NAME);
        itemDetails.setItemDescription(DEFAULT_ITEM_DESCRIPTION);
        itemDetails.setSize(DEFAULT_SIZE);
        itemDetails.setItemQuantity(DEFAULT_ITEM_QUANTITY);
        itemDetails.setUnitPrice(DEFAULT_UNIT_PRICE);
    }

    @Test
    @Transactional
    public void createItemDetails() throws Exception {
        int databaseSizeBeforeCreate = itemDetailsRepository.findAll().size();

        // Create the ItemDetails

        restItemDetailsMockMvc.perform(post("/api/itemDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemDetails)))
                .andExpect(status().isCreated());

        // Validate the ItemDetails in the database
        List<ItemDetails> itemDetailss = itemDetailsRepository.findAll();
        assertThat(itemDetailss).hasSize(databaseSizeBeforeCreate + 1);
        ItemDetails testItemDetails = itemDetailss.get(itemDetailss.size() - 1);
        assertThat(testItemDetails.getItemCode()).isEqualTo(DEFAULT_ITEM_CODE);
        assertThat(testItemDetails.getItemName()).isEqualTo(DEFAULT_ITEM_NAME);
        assertThat(testItemDetails.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testItemDetails.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testItemDetails.getItemQuantity()).isEqualTo(DEFAULT_ITEM_QUANTITY);
        assertThat(testItemDetails.getUnitPrice()).isEqualTo(DEFAULT_UNIT_PRICE);
    }

    @Test
    @Transactional
    public void getAllItemDetailss() throws Exception {
        // Initialize the database
        itemDetailsRepository.saveAndFlush(itemDetails);

        // Get all the itemDetailss
        restItemDetailsMockMvc.perform(get("/api/itemDetailss?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemDetails.getId().intValue())))
                .andExpect(jsonPath("$.[*].itemCode").value(hasItem(DEFAULT_ITEM_CODE.toString())))
                .andExpect(jsonPath("$.[*].itemName").value(hasItem(DEFAULT_ITEM_NAME.toString())))
                .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
                .andExpect(jsonPath("$.[*].itemQuantity").value(hasItem(DEFAULT_ITEM_QUANTITY)))
                .andExpect(jsonPath("$.[*].unitPrice").value(hasItem(DEFAULT_UNIT_PRICE.doubleValue())));
    }

    @Test
    @Transactional
    public void getItemDetails() throws Exception {
        // Initialize the database
        itemDetailsRepository.saveAndFlush(itemDetails);

        // Get the itemDetails
        restItemDetailsMockMvc.perform(get("/api/itemDetailss/{id}", itemDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemDetails.getId().intValue()))
            .andExpect(jsonPath("$.itemCode").value(DEFAULT_ITEM_CODE.toString()))
            .andExpect(jsonPath("$.itemName").value(DEFAULT_ITEM_NAME.toString()))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
            .andExpect(jsonPath("$.itemQuantity").value(DEFAULT_ITEM_QUANTITY))
            .andExpect(jsonPath("$.unitPrice").value(DEFAULT_UNIT_PRICE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItemDetails() throws Exception {
        // Get the itemDetails
        restItemDetailsMockMvc.perform(get("/api/itemDetailss/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemDetails() throws Exception {
        // Initialize the database
        itemDetailsRepository.saveAndFlush(itemDetails);

		int databaseSizeBeforeUpdate = itemDetailsRepository.findAll().size();

        // Update the itemDetails
        itemDetails.setItemCode(UPDATED_ITEM_CODE);
        itemDetails.setItemName(UPDATED_ITEM_NAME);
        itemDetails.setItemDescription(UPDATED_ITEM_DESCRIPTION);
        itemDetails.setSize(UPDATED_SIZE);
        itemDetails.setItemQuantity(UPDATED_ITEM_QUANTITY);
        itemDetails.setUnitPrice(UPDATED_UNIT_PRICE);

        restItemDetailsMockMvc.perform(put("/api/itemDetailss")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemDetails)))
                .andExpect(status().isOk());

        // Validate the ItemDetails in the database
        List<ItemDetails> itemDetailss = itemDetailsRepository.findAll();
        assertThat(itemDetailss).hasSize(databaseSizeBeforeUpdate);
        ItemDetails testItemDetails = itemDetailss.get(itemDetailss.size() - 1);
        assertThat(testItemDetails.getItemCode()).isEqualTo(UPDATED_ITEM_CODE);
        assertThat(testItemDetails.getItemName()).isEqualTo(UPDATED_ITEM_NAME);
        assertThat(testItemDetails.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testItemDetails.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testItemDetails.getItemQuantity()).isEqualTo(UPDATED_ITEM_QUANTITY);
        assertThat(testItemDetails.getUnitPrice()).isEqualTo(UPDATED_UNIT_PRICE);
    }

    @Test
    @Transactional
    public void deleteItemDetails() throws Exception {
        // Initialize the database
        itemDetailsRepository.saveAndFlush(itemDetails);

		int databaseSizeBeforeDelete = itemDetailsRepository.findAll().size();

        // Get the itemDetails
        restItemDetailsMockMvc.perform(delete("/api/itemDetailss/{id}", itemDetails.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemDetails> itemDetailss = itemDetailsRepository.findAll();
        assertThat(itemDetailss).hasSize(databaseSizeBeforeDelete - 1);
    }
}

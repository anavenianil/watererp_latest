package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemRequired;
import com.callippus.water.erp.repository.ItemRequiredRepository;

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
 * Test class for the ItemRequiredResource REST controller.
 *
 * @see ItemRequiredResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemRequiredResourceIntTest {

    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";
    private static final String DEFAULT_UNIT = "AAAAA";
    private static final String UPDATED_UNIT = "BBBBB";

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    private static final BigDecimal DEFAULT_RATE_PER_SHS = new BigDecimal(1);
    private static final BigDecimal UPDATED_RATE_PER_SHS = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_AMOUNT = new BigDecimal(2);

    @Inject
    private ItemRequiredRepository itemRequiredRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemRequiredMockMvc;

    private ItemRequired itemRequired;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemRequiredResource itemRequiredResource = new ItemRequiredResource();
        ReflectionTestUtils.setField(itemRequiredResource, "itemRequiredRepository", itemRequiredRepository);
        this.restItemRequiredMockMvc = MockMvcBuilders.standaloneSetup(itemRequiredResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemRequired = new ItemRequired();
        itemRequired.setDescription(DEFAULT_DESCRIPTION);
        itemRequired.setUnit(DEFAULT_UNIT);
        itemRequired.setQuantity(DEFAULT_QUANTITY);
        itemRequired.setRatePerShs(DEFAULT_RATE_PER_SHS);
        itemRequired.setAmount(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void createItemRequired() throws Exception {
        int databaseSizeBeforeCreate = itemRequiredRepository.findAll().size();

        // Create the ItemRequired

        restItemRequiredMockMvc.perform(post("/api/itemRequireds")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemRequired)))
                .andExpect(status().isCreated());

        // Validate the ItemRequired in the database
        List<ItemRequired> itemRequireds = itemRequiredRepository.findAll();
        assertThat(itemRequireds).hasSize(databaseSizeBeforeCreate + 1);
        ItemRequired testItemRequired = itemRequireds.get(itemRequireds.size() - 1);
        assertThat(testItemRequired.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemRequired.getUnit()).isEqualTo(DEFAULT_UNIT);
        assertThat(testItemRequired.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
        assertThat(testItemRequired.getRatePerShs()).isEqualTo(DEFAULT_RATE_PER_SHS);
        assertThat(testItemRequired.getAmount()).isEqualTo(DEFAULT_AMOUNT);
    }

    @Test
    @Transactional
    public void getAllItemRequireds() throws Exception {
        // Initialize the database
        itemRequiredRepository.saveAndFlush(itemRequired);

        // Get all the itemRequireds
        restItemRequiredMockMvc.perform(get("/api/itemRequireds?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemRequired.getId().intValue())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].unit").value(hasItem(DEFAULT_UNIT.toString())))
                .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)))
                .andExpect(jsonPath("$.[*].ratePerShs").value(hasItem(DEFAULT_RATE_PER_SHS.intValue())))
                .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())));
    }

    @Test
    @Transactional
    public void getItemRequired() throws Exception {
        // Initialize the database
        itemRequiredRepository.saveAndFlush(itemRequired);

        // Get the itemRequired
        restItemRequiredMockMvc.perform(get("/api/itemRequireds/{id}", itemRequired.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemRequired.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.unit").value(DEFAULT_UNIT.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY))
            .andExpect(jsonPath("$.ratePerShs").value(DEFAULT_RATE_PER_SHS.intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingItemRequired() throws Exception {
        // Get the itemRequired
        restItemRequiredMockMvc.perform(get("/api/itemRequireds/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemRequired() throws Exception {
        // Initialize the database
        itemRequiredRepository.saveAndFlush(itemRequired);

		int databaseSizeBeforeUpdate = itemRequiredRepository.findAll().size();

        // Update the itemRequired
        itemRequired.setDescription(UPDATED_DESCRIPTION);
        itemRequired.setUnit(UPDATED_UNIT);
        itemRequired.setQuantity(UPDATED_QUANTITY);
        itemRequired.setRatePerShs(UPDATED_RATE_PER_SHS);
        itemRequired.setAmount(UPDATED_AMOUNT);

        restItemRequiredMockMvc.perform(put("/api/itemRequireds")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemRequired)))
                .andExpect(status().isOk());

        // Validate the ItemRequired in the database
        List<ItemRequired> itemRequireds = itemRequiredRepository.findAll();
        assertThat(itemRequireds).hasSize(databaseSizeBeforeUpdate);
        ItemRequired testItemRequired = itemRequireds.get(itemRequireds.size() - 1);
        assertThat(testItemRequired.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemRequired.getUnit()).isEqualTo(UPDATED_UNIT);
        assertThat(testItemRequired.getQuantity()).isEqualTo(UPDATED_QUANTITY);
        assertThat(testItemRequired.getRatePerShs()).isEqualTo(UPDATED_RATE_PER_SHS);
        assertThat(testItemRequired.getAmount()).isEqualTo(UPDATED_AMOUNT);
    }

    @Test
    @Transactional
    public void deleteItemRequired() throws Exception {
        // Initialize the database
        itemRequiredRepository.saveAndFlush(itemRequired);

		int databaseSizeBeforeDelete = itemRequiredRepository.findAll().size();

        // Get the itemRequired
        restItemRequiredMockMvc.perform(delete("/api/itemRequireds/{id}", itemRequired.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemRequired> itemRequireds = itemRequiredRepository.findAll();
        assertThat(itemRequireds).hasSize(databaseSizeBeforeDelete - 1);
    }
}

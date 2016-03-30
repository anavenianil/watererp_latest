package com.callippus.water.erp.web.rest;

import com.callippus.water.erp.Application;
import com.callippus.water.erp.domain.ItemCompanyMaster;
import com.callippus.water.erp.repository.ItemCompanyMasterRepository;

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
 * Test class for the ItemCompanyMasterResource REST controller.
 *
 * @see ItemCompanyMasterResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ItemCompanyMasterResourceIntTest {

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
    private static final String DEFAULT_COMPANY_CODE = "AAAAA";
    private static final String UPDATED_COMPANY_CODE = "BBBBB";

    @Inject
    private ItemCompanyMasterRepository itemCompanyMasterRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restItemCompanyMasterMockMvc;

    private ItemCompanyMaster itemCompanyMaster;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ItemCompanyMasterResource itemCompanyMasterResource = new ItemCompanyMasterResource();
        ReflectionTestUtils.setField(itemCompanyMasterResource, "itemCompanyMasterRepository", itemCompanyMasterRepository);
        this.restItemCompanyMasterMockMvc = MockMvcBuilders.standaloneSetup(itemCompanyMasterResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        itemCompanyMaster = new ItemCompanyMaster();
        itemCompanyMaster.setName(DEFAULT_NAME);
        itemCompanyMaster.setDescription(DEFAULT_DESCRIPTION);
        itemCompanyMaster.setStatus(DEFAULT_STATUS);
        itemCompanyMaster.setCreationDate(DEFAULT_CREATION_DATE);
        itemCompanyMaster.setLastModifiedDate(DEFAULT_LAST_MODIFIED_DATE);
        itemCompanyMaster.setCompanyCode(DEFAULT_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void createItemCompanyMaster() throws Exception {
        int databaseSizeBeforeCreate = itemCompanyMasterRepository.findAll().size();

        // Create the ItemCompanyMaster

        restItemCompanyMasterMockMvc.perform(post("/api/itemCompanyMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemCompanyMaster)))
                .andExpect(status().isCreated());

        // Validate the ItemCompanyMaster in the database
        List<ItemCompanyMaster> itemCompanyMasters = itemCompanyMasterRepository.findAll();
        assertThat(itemCompanyMasters).hasSize(databaseSizeBeforeCreate + 1);
        ItemCompanyMaster testItemCompanyMaster = itemCompanyMasters.get(itemCompanyMasters.size() - 1);
        assertThat(testItemCompanyMaster.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testItemCompanyMaster.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testItemCompanyMaster.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testItemCompanyMaster.getCreationDate()).isEqualTo(DEFAULT_CREATION_DATE);
        assertThat(testItemCompanyMaster.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testItemCompanyMaster.getCompanyCode()).isEqualTo(DEFAULT_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void getAllItemCompanyMasters() throws Exception {
        // Initialize the database
        itemCompanyMasterRepository.saveAndFlush(itemCompanyMaster);

        // Get all the itemCompanyMasters
        restItemCompanyMasterMockMvc.perform(get("/api/itemCompanyMasters?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(itemCompanyMaster.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
                .andExpect(jsonPath("$.[*].creationDate").value(hasItem(DEFAULT_CREATION_DATE_STR)))
                .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE_STR)))
                .andExpect(jsonPath("$.[*].companyCode").value(hasItem(DEFAULT_COMPANY_CODE.toString())));
    }

    @Test
    @Transactional
    public void getItemCompanyMaster() throws Exception {
        // Initialize the database
        itemCompanyMasterRepository.saveAndFlush(itemCompanyMaster);

        // Get the itemCompanyMaster
        restItemCompanyMasterMockMvc.perform(get("/api/itemCompanyMasters/{id}", itemCompanyMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(itemCompanyMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.creationDate").value(DEFAULT_CREATION_DATE_STR))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE_STR))
            .andExpect(jsonPath("$.companyCode").value(DEFAULT_COMPANY_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingItemCompanyMaster() throws Exception {
        // Get the itemCompanyMaster
        restItemCompanyMasterMockMvc.perform(get("/api/itemCompanyMasters/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateItemCompanyMaster() throws Exception {
        // Initialize the database
        itemCompanyMasterRepository.saveAndFlush(itemCompanyMaster);

		int databaseSizeBeforeUpdate = itemCompanyMasterRepository.findAll().size();

        // Update the itemCompanyMaster
        itemCompanyMaster.setName(UPDATED_NAME);
        itemCompanyMaster.setDescription(UPDATED_DESCRIPTION);
        itemCompanyMaster.setStatus(UPDATED_STATUS);
        itemCompanyMaster.setCreationDate(UPDATED_CREATION_DATE);
        itemCompanyMaster.setLastModifiedDate(UPDATED_LAST_MODIFIED_DATE);
        itemCompanyMaster.setCompanyCode(UPDATED_COMPANY_CODE);

        restItemCompanyMasterMockMvc.perform(put("/api/itemCompanyMasters")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemCompanyMaster)))
                .andExpect(status().isOk());

        // Validate the ItemCompanyMaster in the database
        List<ItemCompanyMaster> itemCompanyMasters = itemCompanyMasterRepository.findAll();
        assertThat(itemCompanyMasters).hasSize(databaseSizeBeforeUpdate);
        ItemCompanyMaster testItemCompanyMaster = itemCompanyMasters.get(itemCompanyMasters.size() - 1);
        assertThat(testItemCompanyMaster.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testItemCompanyMaster.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testItemCompanyMaster.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testItemCompanyMaster.getCreationDate()).isEqualTo(UPDATED_CREATION_DATE);
        assertThat(testItemCompanyMaster.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testItemCompanyMaster.getCompanyCode()).isEqualTo(UPDATED_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void deleteItemCompanyMaster() throws Exception {
        // Initialize the database
        itemCompanyMasterRepository.saveAndFlush(itemCompanyMaster);

		int databaseSizeBeforeDelete = itemCompanyMasterRepository.findAll().size();

        // Get the itemCompanyMaster
        restItemCompanyMasterMockMvc.perform(delete("/api/itemCompanyMasters/{id}", itemCompanyMaster.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ItemCompanyMaster> itemCompanyMasters = itemCompanyMasterRepository.findAll();
        assertThat(itemCompanyMasters).hasSize(databaseSizeBeforeDelete - 1);
    }
}
